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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.test.model.Task;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Task. This utility wraps
 * <code>es.test.service.impl.TaskLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TaskLocalService
 * @generated
 */
public class TaskLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>es.test.service.impl.TaskLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * CREAR TAREA - setteo a true active
	 */
	public static Task addTask(
			long groupId, long assignedUserId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			String title, String description, java.util.Date dueDate)
		throws PortalException {

		return getService().addTask(
			groupId, assignedUserId, serviceContext, title, description,
			dueDate);
	}

	public static Task addTask(
			long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			String title, String description, java.util.Date dueDate)
		throws PortalException {

		return getService().addTask(
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
	public static Task addTask(Task task) {
		return getService().addTask(task);
	}

	/**
	 * COMPLETAR TAREA
	 */
	public static Task completeTask(
			long taskId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().completeTask(taskId, serviceContext);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new task with the primary key. Does not add the task to the database.
	 *
	 * @param taskId the primary key for the new task
	 * @return the new task
	 */
	public static Task createTask(long taskId) {
		return getService().createTask(taskId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	public static Task deleteTask(long taskId) throws PortalException {
		return getService().deleteTask(taskId);
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
	public static Task deleteTask(Task task) {
		return getService().deleteTask(task);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static Task fetchTask(long taskId) {
		return getService().fetchTask(taskId);
	}

	/**
	 * BUSCAR TODOS POR GROUPID_USERID (FINDER) --USER
	 */
	public static List<Task> findByGroupIdUserId(
		long groupId, long userId, int start, int end) {

		return getService().findByGroupIdUserId(groupId, userId, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the task with the primary key.
	 *
	 * @param taskId the primary key of the task
	 * @return the task
	 * @throws PortalException if a task with the primary key could not be found
	 */
	public static Task getTask(long taskId) throws PortalException {
		return getService().getTask(taskId);
	}

	public static List<Task> getTaskByGroup(long groupId) {
		return getService().getTaskByGroup(groupId);
	}

	/**
	 * BUSQUEDA EXACTA X TITULO (FINDER) --ADMIN
	 */
	public static List<Task> getTaskByTitle(long groupId, String title) {
		return getService().getTaskByTitle(groupId, title);
	}

	public static List<Task> getTaskByTitle(
		long groupId, String title, int start, int end) {

		return getService().getTaskByTitle(groupId, title, start, end);
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
	public static List<Task> getTasks(int start, int end) {
		return getService().getTasks(start, end);
	}

	/**
	 * BUSCAR TODOS POR GROUP (FINDER) --ADMIN
	 */
	public static List<Task> getTasksByGroup(long groupId, int start, int end) {
		return getService().getTasksByGroup(groupId, start, end);
	}

	/**
	 * BUSQUEDA FLEXIBLE X TITULO (DYNAMIC QUERY) --ADMIN + USER
	 */
	public static List<Task> getTasksByKeywords(
		long groupId, Long userId, String keywords, int start, int end) {

		return getService().getTasksByKeywords(
			groupId, userId, keywords, start, end);
	}

	public static List<Task> getTasksByKeywords(
		long groupId, String keywords, int start, int end) {

		return getService().getTasksByKeywords(groupId, keywords, start, end);
	}

	public static List<Task> getTasksByTitleAndUser(
		long groupId, long userId, String title) {

		return getService().getTasksByTitleAndUser(groupId, userId, title);
	}

	/**
	 * BUSQUEDA EXACTA X TITULO + USER (FINDER) --USER
	 */
	public static List<Task> getTasksByTitleAndUser(
		long groupId, long userId, String title, int start, int end) {

		return getService().getTasksByTitleAndUser(
			groupId, userId, title, start, end);
	}

	/**
	 * Returns the number of tasks.
	 *
	 * @return the number of tasks
	 */
	public static int getTasksCount() {
		return getService().getTasksCount();
	}

	public static int getTasksCountByGroup(long groupId) {
		return getService().getTasksCountByGroup(groupId);
	}

	public static int getTasksCountByGroupIdUserId(long groupId, long userId) {
		return getService().getTasksCountByGroupIdUserId(groupId, userId);
	}

	public static int getTasksCountByKeywords(
		long groupId, Long userId, String keywords) {

		return getService().getTasksCountByKeywords(groupId, userId, keywords);
	}

	public static int getTasksCountByKeywords(long groupId, String keywords) {
		return getService().getTasksCountByKeywords(groupId, keywords);
	}

	public static int getTasksCountByTitle(long groupId, String title) {
		return getService().getTasksCountByTitle(groupId, title);
	}

	public static int getTasksCountByTitleAndUser(
		long groupId, long userId, String title) {

		return getService().getTasksCountByTitleAndUser(groupId, userId, title);
	}

	public static List<Task> getTasksOverdue(
		java.util.Date cutoffDate, long userId, long groupId) {

		return getService().getTasksOverdue(cutoffDate, userId, groupId);
	}

	public static long getTasksOverdueCount(
		java.util.Date cutoffDate, long userId, long groupId) {

		return getService().getTasksOverdueCount(cutoffDate, userId, groupId);
	}

	public static List<Task> getTasksPending(
		java.util.Date cutoffDate, long userId, long groupId) {

		return getService().getTasksPending(cutoffDate, userId, groupId);
	}

	public static long getTasksPendingCount(
		java.util.Date cutoffDate, long userId, long groupId) {

		return getService().getTasksPendingCount(cutoffDate, userId, groupId);
	}

	public static List<Task> getTasksToDesactivate(java.util.Date cutoffDate) {
		return getService().getTasksToDesactivate(cutoffDate);
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
	public static Task updateTask(Task task) {
		return getService().updateTask(task);
	}

	public static TaskLocalService getService() {
		return _service;
	}

	private static volatile TaskLocalService _service;

}