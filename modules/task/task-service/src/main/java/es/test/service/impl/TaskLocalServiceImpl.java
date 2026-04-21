/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package es.test.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.model.Task;
import es.test.service.base.TaskLocalServiceBaseImpl;

/**
 * Esta es la clase de Servicio donde voy a crear todos los métodos. La web
 * pedira aquí una tarea, aquí haré las comprobaciones etc y lo guardaré en
 * bbdd. Esta clase implementa la logica de negocio perincipal. Aqui no se hace
 * codigo ed portlet, es reglas de negocio. no hay acceso directo a request y
 * response. Es llamado por actionCommand (lo inyecto ahí por inyeccion de
 * dependencias con reference. llama a persistence?) Al hacer ServiceBuilder
 * automaticamente se crea crud básico. add + update + delete + fetch + get -->
 * aquí se añaden validaciones + reglas de negocio + lógica personalizada
 * 
 * @author Hiberus Digital Business
 * @author Izarbe Bailo AYsa
 */
@Component(property = "model.class.name=es.test.model.Task", service = AopService.class)
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {

	@Reference
	private GroupLocalService _groupLocalService;

	/**
	 * CREAR TAREA - setteo a true active
	 */
	public Task addTask(long groupId, long assignedUserId, ServiceContext serviceContext, String title,
			String description, Date dueDate) throws PortalException {

		// valido que group existe
		Group group = _groupLocalService.getGroup(groupId); // groupLocalService lo crea liferay automaticamente, lo
															// heredamos de la clase base. la clase group viene de
															// fabrica. igual que user.
		// solo están disponibles automáticamente los services que pertenecen al propio
		// módulo, en este caso Task. si quiero usar group lo tengo que importar

		// valido título no vació
		if (Validator.isNull(title) || Validator.isBlank(title)) {
			throw new PortalException("el título es obligatorio");
		}

		User assignedUser = userLocalService.getUser(assignedUserId);

		// genero id --> creo registro (create) --> lo relleno (update)
		long taskId = counterLocalService.increment(Task.class.getName());

		Task task = taskPersistence.create(taskId);

		task.setGroupId(groupId);
		task.setCompanyId(group.getCompanyId());
		task.setUserId(assignedUser.getUserId());
		task.setUserName(assignedUser.getFullName());

		task.setCreateDate(serviceContext.getCreateDate(new Date()));
		task.setModifiedDate(serviceContext.getModifiedDate(new Date()));

		task.setTitle(title);
		task.setDescription(description);
		task.setDueDate(dueDate);
		task.setCompleted(false);
		task.setCompletedDate(null);
		task.setActive(true);

		return taskPersistence.update(task);
	}

	@Override
	public Task addTask(long groupId, ServiceContext serviceContext, String title, String description, Date dueDate)
			throws PortalException {
		Group group = _groupLocalService.getGroup(groupId);

		if (Validator.isNull(title)) {
			throw new PortalException("el título es obligatorio");
		}

		// usuario creador
		User user = userLocalService.getUser(serviceContext.getUserId());

		long taskId = counterLocalService.increment(Task.class.getName());

		Task task = taskPersistence.create(taskId);

		task.setGroupId(groupId);
		task.setCompanyId(group.getCompanyId());

		task.setUserId(user.getUserId());
		task.setUserName(user.getFullName());

		task.setCreateDate(serviceContext.getCreateDate(new Date()));
		task.setModifiedDate(serviceContext.getModifiedDate(new Date()));

		task.setTitle(title);
		task.setDescription(description);
		task.setDueDate(dueDate);
		task.setCompleted(false);
		task.setCompletedDate(null);
		task.setActive(true);

		return taskPersistence.update(task);
	}

	/**
	 * COMPLETAR TAREA
	 */
	public Task completeTask(long taskId, ServiceContext serviceContext) throws PortalException {
		// valido que tarea existe
		Task task = taskPersistence.fetchByPrimaryKey(taskId);

		if (task == null) {
		    throw new PortalException("Task no existe");
		}

		// actualizo campos pertinentes --> updateo
		task.setCompleted(true);
		task.setModifiedDate(new Date());
		task.setCompletedDate(new Date());
		return taskPersistence.update(task);
	}

	/**
	 * BUSCAR TODOS POR GROUP (FINDER) --ADMIN
	 */

	public List<Task> getTasksByGroup(long groupId, int start, int end) {
		return taskPersistence.findByGroupId(groupId, start, end);
	}

	public int getTasksCountByGroup(long groupId) {
		return taskPersistence.countByGroupId(groupId);
	}

	public List<Task> getTaskByGroup(long groupId) {
		return taskPersistence.findByGroupId(groupId);
	}

	/**
	 * BUSCAR TODOS POR GROUPID_USERID (FINDER) --USER
	 */
	public List<Task> findByGroupIdUserId(long groupId, long userId, int start, int end) {
		return taskPersistence.findByGroupIdUserId(groupId, userId, true, start, end);
	}

	public int getTasksCountByGroupIdUserId(long groupId, long userId) {
		return taskPersistence.countByGroupIdUserId(groupId, userId, true);
	}

	/**
	 * BUSQUEDA EXACTA X TITULO (FINDER) --ADMIN
	 */

	@Override
	public List<Task> getTaskByTitle(long groupId, String title) {
		return taskPersistence.findByGroupIdTitle(groupId, title);
	}

	public List<Task> getTaskByTitle(long groupId, String title, int start, int end) {
		return taskPersistence.findByGroupIdTitle(groupId, title, start, end);
	}

	public int getTasksCountByTitle(long groupId, String title) {
		return taskPersistence.countByGroupIdTitle(groupId, title);
	}

	/**
	 * BUSQUEDA EXACTA X TITULO + USER (FINDER) --USER
	 */
	public List<Task> getTasksByTitleAndUser(long groupId, long userId, String title, int start, int end) {
		return taskPersistence.findByGroupIdUserIdTitle(groupId, userId, title, true, start, end);
	}

	public int getTasksCountByTitleAndUser(long groupId, long userId, String title) {
		return taskPersistence.countByGroupIdUserIdTitle(groupId, userId, title, true);
	}

	public List<Task> getTasksByTitleAndUser(long groupId, long userId, String title) {
		return taskPersistence.findByGroupIdUserIdTitle(groupId, userId, title, true);
	}

	/**
	 * BUSQUEDA FLEXIBLE X TITULO (DYNAMIC QUERY) --ADMIN + USER
	 * 
	 */
	public List<Task> getTasksByKeywords(long groupId, Long userId, String keywords, int start, int end) {

		// si se envía lista vacía, devuelvo todos
		if (Validator.isNull(keywords)) {
			if (userId != null) {
				return findByGroupIdUserId(groupId, userId, start, end);
			}
			return getTasksByGroup(groupId, start, end);
		}

		// construyo query
		DynamicQuery query = _buildKeywordsQuery(groupId, userId, keywords);

		// busco
		return taskPersistence.findWithDynamicQuery(query, start, end);

	}

	// COUNT de dynamic query
	public int getTasksCountByKeywords(long groupId, Long userId, String keywords) {
		if (Validator.isNull(keywords)) {
			if (userId != null) {
				return getTasksCountByGroupIdUserId(groupId, userId);
			}
			return getTasksCountByGroup(groupId);
		}

		DynamicQuery query = _buildKeywordsQuery(groupId, userId, keywords);
		return (int) taskPersistence.countWithDynamicQuery(query); // Returns the number of rows that match the dynamic
																	// query.
	}

	@Override
	public List<Task> getTasksByKeywords(long groupId, String keywords, int start, int end) {

		// si se envía lista vacía, devuelvo todos
		if (Validator.isNull(keywords)) {
			return getTasksByGroup(groupId, start, end);
		}

		// construyo query
		DynamicQuery query = _buildKeywordsQuery(groupId, null, keywords);

		// busco
		return taskPersistence.findWithDynamicQuery(query, start, end);

	}

	@Override
	public int getTasksCountByKeywords(long groupId, String keywords) {
		if (Validator.isNull(keywords)) {

			return getTasksCountByGroup(groupId);
		}
		return 0;
	}

	/**
	 * PRIVADO, CONSTRUYE CONSULTA QUERY PARA DQ
	 * 
	 * @throws Exception
	 */
	private DynamicQuery _buildKeywordsQuery(long groupId, Long userId, String keywords) {

		DynamicQuery query = DynamicQueryFactoryUtil.forClass(Task.class, getClass().getClassLoader());

		PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();

		// obligado
		Property groupIdProperty = PropertyFactoryUtil.forName("groupId");
		query.add(groupIdProperty.eq(groupId));

		//permisos
		if (!permissionChecker.isOmniadmin()) {
			query.add(RestrictionsFactoryUtil.eq("active", true));
		}
		
		// opcional
		if (userId != null) {

			query.add(PropertyFactoryUtil.forName("userId").eq(userId));
		}

		if (Validator.isNotNull(keywords)) {
			Property titleProperty = PropertyFactoryUtil.forName("title");
			query.add(titleProperty.like("%" + keywords + "%"));
		}

		return query;
	}

	/**
	 * BUSQUEDA FLEXIBLE - TAREAS A DESACTIVAR (DYNAMIC QUERY) --ESTAN ACTIVAS +
	 * ESTaN COMPLETADAS + HACE MAS DE 15 DiAS
	 */
	private DynamicQuery _buildTasksToDesactivateQuery(Date cutoffDate) {
		// CONSTRUYO LA QUERY

		DynamicQuery query = DynamicQueryFactoryUtil.forClass(Task.class, getClass().getClassLoader());

		query.add(RestrictionsFactoryUtil.eq("completed", true));
		query.add(RestrictionsFactoryUtil.eq("active", true));
		query.add(RestrictionsFactoryUtil.isNotNull("completedDate"));
		query.add(RestrictionsFactoryUtil.le("completedDate", cutoffDate));

		return query;
	}

	public List<Task> getTasksToDesactivate(Date cutoffDate) {
		DynamicQuery query = _buildTasksToDesactivateQuery(cutoffDate);
		return taskPersistence.findWithDynamicQuery(query);
	}

	

}