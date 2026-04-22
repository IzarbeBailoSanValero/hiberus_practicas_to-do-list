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

package es.test.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TaskLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TaskLocalService
 * @generated
 */
public class TaskLocalServiceWrapper
	implements ServiceWrapper<TaskLocalService>, TaskLocalService {

	public TaskLocalServiceWrapper() {
		this(null);
	}

	public TaskLocalServiceWrapper(TaskLocalService taskLocalService) {
		_taskLocalService = taskLocalService;
	}

	/**
	 * CREAR TAREA - setteo a true active
	 */
	@Override
	public es.test.model.Task addTask(
			long groupId, long assignedUserId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			String title, String description, java.util.Date dueDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskLocalService.addTask(
			groupId, assignedUserId, serviceContext, title, description,
			dueDate);
	}

	@Override
	public es.test.model.Task addTask(
			long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			String title, String description, java.util.Date dueDate)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskLocalService.addTask(
			groupId, serviceContext, title, description, dueDate);
	}

	/**
	 * Adds the task to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param task the task
	 * @return the task that was added
	 */
	@Override
	public es.test.model.Task addTask(es.test.model.Task task) {
		return _taskLocalService.addTask(task);
	}

	/**
	 * COMPLETAR TAREA
	 */
	@Override
	public es.test.model.Task completeTask(
			long taskId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskLocalService.completeTask(taskId, serviceContext);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new task with the primary key. Does not add the task to the database.
	 *
	 * @param taskId the primary key for the new task
	 * @return the new task
	 */
	@Override
	public es.test.model.Task createTask(long taskId) {
		return _taskLocalService.createTask(taskId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param taskId the primary key of the task
	 * @return the task that was removed
	 * @throws PortalException if a task with the primary key could not be found
	 */
	@Override
	public es.test.model.Task deleteTask(long taskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskLocalService.deleteTask(taskId);
	}

	/**
	 * Deletes the task from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param task the task
	 * @return the task that was removed
	 */
	@Override
	public es.test.model.Task deleteTask(es.test.model.Task task) {
		return _taskLocalService.deleteTask(task);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _taskLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _taskLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _taskLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _taskLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>es.test.model.impl.TaskModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _taskLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>es.test.model.impl.TaskModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _taskLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _taskLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _taskLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public es.test.model.Task fetchTask(long taskId) {
		return _taskLocalService.fetchTask(taskId);
	}

	/**
	 * BUSCAR TODOS POR GROUPID_USERID (FINDER) --USER
	 */
	@Override
	public java.util.List<es.test.model.Task> findByGroupIdUserId(
		long groupId, long userId, int start, int end) {

		return _taskLocalService.findByGroupIdUserId(
			groupId, userId, start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _taskLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _taskLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _taskLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the task with the primary key.
	 *
	 * @param taskId the primary key of the task
	 * @return the task
	 * @throws PortalException if a task with the primary key could not be found
	 */
	@Override
	public es.test.model.Task getTask(long taskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _taskLocalService.getTask(taskId);
	}

	@Override
	public java.util.List<es.test.model.Task> getTaskByGroup(long groupId) {
		return _taskLocalService.getTaskByGroup(groupId);
	}

	/**
	 * BUSQUEDA EXACTA X TITULO (FINDER) --ADMIN
	 */
	@Override
	public java.util.List<es.test.model.Task> getTaskByTitle(
		long groupId, String title) {

		return _taskLocalService.getTaskByTitle(groupId, title);
	}

	@Override
	public java.util.List<es.test.model.Task> getTaskByTitle(
		long groupId, String title, int start, int end) {

		return _taskLocalService.getTaskByTitle(groupId, title, start, end);
	}

	/**
	 * Returns a range of all the tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>es.test.model.impl.TaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of tasks
	 */
	@Override
	public java.util.List<es.test.model.Task> getTasks(int start, int end) {
		return _taskLocalService.getTasks(start, end);
	}

	/**
	 * BUSCAR TODOS POR GROUP (FINDER) --ADMIN
	 */
	@Override
	public java.util.List<es.test.model.Task> getTasksByGroup(
		long groupId, int start, int end) {

		return _taskLocalService.getTasksByGroup(groupId, start, end);
	}

	/**
	 * BUSQUEDA FLEXIBLE X TITULO (DYNAMIC QUERY) --ADMIN + USER
	 */
	@Override
	public java.util.List<es.test.model.Task> getTasksByKeywords(
		long groupId, Long userId, String keywords, int start, int end) {

		return _taskLocalService.getTasksByKeywords(
			groupId, userId, keywords, start, end);
	}

	@Override
	public java.util.List<es.test.model.Task> getTasksByKeywords(
		long groupId, String keywords, int start, int end) {

		return _taskLocalService.getTasksByKeywords(
			groupId, keywords, start, end);
	}

	@Override
	public java.util.List<es.test.model.Task> getTasksByTitleAndUser(
		long groupId, long userId, String title) {

		return _taskLocalService.getTasksByTitleAndUser(groupId, userId, title);
	}

	/**
	 * BUSQUEDA EXACTA X TITULO + USER (FINDER) --USER
	 */
	@Override
	public java.util.List<es.test.model.Task> getTasksByTitleAndUser(
		long groupId, long userId, String title, int start, int end) {

		return _taskLocalService.getTasksByTitleAndUser(
			groupId, userId, title, start, end);
	}

	/**
	 * Returns the number of tasks.
	 *
	 * @return the number of tasks
	 */
	@Override
	public int getTasksCount() {
		return _taskLocalService.getTasksCount();
	}

	@Override
	public int getTasksCountByGroup(long groupId) {
		return _taskLocalService.getTasksCountByGroup(groupId);
	}

	@Override
	public int getTasksCountByGroupIdUserId(long groupId, long userId) {
		return _taskLocalService.getTasksCountByGroupIdUserId(groupId, userId);
	}

	@Override
	public int getTasksCountByKeywords(
		long groupId, Long userId, String keywords) {

		return _taskLocalService.getTasksCountByKeywords(
			groupId, userId, keywords);
	}

	@Override
	public int getTasksCountByKeywords(long groupId, String keywords) {
		return _taskLocalService.getTasksCountByKeywords(groupId, keywords);
	}

	@Override
	public int getTasksCountByTitle(long groupId, String title) {
		return _taskLocalService.getTasksCountByTitle(groupId, title);
	}

	@Override
	public int getTasksCountByTitleAndUser(
		long groupId, long userId, String title) {

		return _taskLocalService.getTasksCountByTitleAndUser(
			groupId, userId, title);
	}

	@Override
	public java.util.List<es.test.model.Task> getTasksOverdue(
		java.util.Date cutoffDate, long userId, long groupId) {

		return _taskLocalService.getTasksOverdue(cutoffDate, userId, groupId);
	}

	@Override
	public long getTasksOverdueCount(
		java.util.Date cutoffDate, long userId, long groupId) {

		return _taskLocalService.getTasksOverdueCount(
			cutoffDate, userId, groupId);
	}

	@Override
	public java.util.List<es.test.model.Task> getTasksPending(
		java.util.Date cutoffDate, long userId, long groupId) {

		return _taskLocalService.getTasksPending(cutoffDate, userId, groupId);
	}

	@Override
	public long getTasksPendingCount(
		java.util.Date cutoffDate, long userId, long groupId) {

		return _taskLocalService.getTasksPendingCount(
			cutoffDate, userId, groupId);
	}

	@Override
	public java.util.List<es.test.model.Task> getTasksToDesactivate(
		java.util.Date cutoffDate) {

		return _taskLocalService.getTasksToDesactivate(cutoffDate);
	}

	/**
	 * Updates the task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param task the task
	 * @return the task that was updated
	 */
	@Override
	public es.test.model.Task updateTask(es.test.model.Task task) {
		return _taskLocalService.updateTask(task);
	}

	@Override
	public TaskLocalService getWrappedService() {
		return _taskLocalService;
	}

	@Override
	public void setWrappedService(TaskLocalService taskLocalService) {
		_taskLocalService = taskLocalService;
	}

	private TaskLocalService _taskLocalService;

}