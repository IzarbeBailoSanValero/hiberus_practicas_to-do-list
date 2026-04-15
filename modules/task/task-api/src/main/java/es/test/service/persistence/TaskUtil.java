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

package es.test.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import es.test.model.Task;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the task service. This utility wraps <code>es.test.service.persistence.impl.TaskPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TaskPersistence
 * @generated
 */
public class TaskUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Task task) {
		getPersistence().clearCache(task);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Task> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Task> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Task> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Task> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Task> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Task update(Task task) {
		return getPersistence().update(task);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Task update(Task task, ServiceContext serviceContext) {
		return getPersistence().update(task, serviceContext);
	}

	/**
	 * Returns all the tasks where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching tasks
	 */
	public static List<Task> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the tasks where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of matching tasks
	 */
	public static List<Task> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the tasks where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks
	 */
	public static List<Task> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Task> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tasks where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks
	 */
	public static List<Task> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Task> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task
	 * @throws NoSuchTaskException if a matching task could not be found
	 */
	public static Task findByGroupId_First(
			long groupId, OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task, or <code>null</code> if a matching task could not be found
	 */
	public static Task fetchByGroupId_First(
		long groupId, OrderByComparator<Task> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task
	 * @throws NoSuchTaskException if a matching task could not be found
	 */
	public static Task findByGroupId_Last(
			long groupId, OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last task in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task, or <code>null</code> if a matching task could not be found
	 */
	public static Task fetchByGroupId_Last(
		long groupId, OrderByComparator<Task> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the tasks before and after the current task in the ordered set where groupId = &#63;.
	 *
	 * @param taskId the primary key of the current task
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next task
	 * @throws NoSuchTaskException if a task with the primary key could not be found
	 */
	public static Task[] findByGroupId_PrevAndNext(
			long taskId, long groupId,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupId_PrevAndNext(
			taskId, groupId, orderByComparator);
	}

	/**
	 * Removes all the tasks where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of tasks where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching tasks
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the tasks where groupId = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param completed the completed
	 * @return the matching tasks
	 */
	public static List<Task> findByGroupIdCompleted(
		long groupId, boolean completed) {

		return getPersistence().findByGroupIdCompleted(groupId, completed);
	}

	/**
	 * Returns a range of all the tasks where groupId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param completed the completed
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of matching tasks
	 */
	public static List<Task> findByGroupIdCompleted(
		long groupId, boolean completed, int start, int end) {

		return getPersistence().findByGroupIdCompleted(
			groupId, completed, start, end);
	}

	/**
	 * Returns an ordered range of all the tasks where groupId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param completed the completed
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks
	 */
	public static List<Task> findByGroupIdCompleted(
		long groupId, boolean completed, int start, int end,
		OrderByComparator<Task> orderByComparator) {

		return getPersistence().findByGroupIdCompleted(
			groupId, completed, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tasks where groupId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param completed the completed
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks
	 */
	public static List<Task> findByGroupIdCompleted(
		long groupId, boolean completed, int start, int end,
		OrderByComparator<Task> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupIdCompleted(
			groupId, completed, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first task in the ordered set where groupId = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task
	 * @throws NoSuchTaskException if a matching task could not be found
	 */
	public static Task findByGroupIdCompleted_First(
			long groupId, boolean completed,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupIdCompleted_First(
			groupId, completed, orderByComparator);
	}

	/**
	 * Returns the first task in the ordered set where groupId = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task, or <code>null</code> if a matching task could not be found
	 */
	public static Task fetchByGroupIdCompleted_First(
		long groupId, boolean completed,
		OrderByComparator<Task> orderByComparator) {

		return getPersistence().fetchByGroupIdCompleted_First(
			groupId, completed, orderByComparator);
	}

	/**
	 * Returns the last task in the ordered set where groupId = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task
	 * @throws NoSuchTaskException if a matching task could not be found
	 */
	public static Task findByGroupIdCompleted_Last(
			long groupId, boolean completed,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupIdCompleted_Last(
			groupId, completed, orderByComparator);
	}

	/**
	 * Returns the last task in the ordered set where groupId = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task, or <code>null</code> if a matching task could not be found
	 */
	public static Task fetchByGroupIdCompleted_Last(
		long groupId, boolean completed,
		OrderByComparator<Task> orderByComparator) {

		return getPersistence().fetchByGroupIdCompleted_Last(
			groupId, completed, orderByComparator);
	}

	/**
	 * Returns the tasks before and after the current task in the ordered set where groupId = &#63; and completed = &#63;.
	 *
	 * @param taskId the primary key of the current task
	 * @param groupId the group ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next task
	 * @throws NoSuchTaskException if a task with the primary key could not be found
	 */
	public static Task[] findByGroupIdCompleted_PrevAndNext(
			long taskId, long groupId, boolean completed,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupIdCompleted_PrevAndNext(
			taskId, groupId, completed, orderByComparator);
	}

	/**
	 * Removes all the tasks where groupId = &#63; and completed = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param completed the completed
	 */
	public static void removeByGroupIdCompleted(
		long groupId, boolean completed) {

		getPersistence().removeByGroupIdCompleted(groupId, completed);
	}

	/**
	 * Returns the number of tasks where groupId = &#63; and completed = &#63;.
	 *
	 * @param groupId the group ID
	 * @param completed the completed
	 * @return the number of matching tasks
	 */
	public static int countByGroupIdCompleted(long groupId, boolean completed) {
		return getPersistence().countByGroupIdCompleted(groupId, completed);
	}

	/**
	 * Returns all the tasks where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the matching tasks
	 */
	public static List<Task> findByGroupIdTitle(long groupId, String title) {
		return getPersistence().findByGroupIdTitle(groupId, title);
	}

	/**
	 * Returns a range of all the tasks where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of matching tasks
	 */
	public static List<Task> findByGroupIdTitle(
		long groupId, String title, int start, int end) {

		return getPersistence().findByGroupIdTitle(groupId, title, start, end);
	}

	/**
	 * Returns an ordered range of all the tasks where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks
	 */
	public static List<Task> findByGroupIdTitle(
		long groupId, String title, int start, int end,
		OrderByComparator<Task> orderByComparator) {

		return getPersistence().findByGroupIdTitle(
			groupId, title, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tasks where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks
	 */
	public static List<Task> findByGroupIdTitle(
		long groupId, String title, int start, int end,
		OrderByComparator<Task> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupIdTitle(
			groupId, title, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first task in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task
	 * @throws NoSuchTaskException if a matching task could not be found
	 */
	public static Task findByGroupIdTitle_First(
			long groupId, String title,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupIdTitle_First(
			groupId, title, orderByComparator);
	}

	/**
	 * Returns the first task in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task, or <code>null</code> if a matching task could not be found
	 */
	public static Task fetchByGroupIdTitle_First(
		long groupId, String title, OrderByComparator<Task> orderByComparator) {

		return getPersistence().fetchByGroupIdTitle_First(
			groupId, title, orderByComparator);
	}

	/**
	 * Returns the last task in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task
	 * @throws NoSuchTaskException if a matching task could not be found
	 */
	public static Task findByGroupIdTitle_Last(
			long groupId, String title,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupIdTitle_Last(
			groupId, title, orderByComparator);
	}

	/**
	 * Returns the last task in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task, or <code>null</code> if a matching task could not be found
	 */
	public static Task fetchByGroupIdTitle_Last(
		long groupId, String title, OrderByComparator<Task> orderByComparator) {

		return getPersistence().fetchByGroupIdTitle_Last(
			groupId, title, orderByComparator);
	}

	/**
	 * Returns the tasks before and after the current task in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param taskId the primary key of the current task
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next task
	 * @throws NoSuchTaskException if a task with the primary key could not be found
	 */
	public static Task[] findByGroupIdTitle_PrevAndNext(
			long taskId, long groupId, String title,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupIdTitle_PrevAndNext(
			taskId, groupId, title, orderByComparator);
	}

	/**
	 * Removes all the tasks where groupId = &#63; and title = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 */
	public static void removeByGroupIdTitle(long groupId, String title) {
		getPersistence().removeByGroupIdTitle(groupId, title);
	}

	/**
	 * Returns the number of tasks where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the number of matching tasks
	 */
	public static int countByGroupIdTitle(long groupId, String title) {
		return getPersistence().countByGroupIdTitle(groupId, title);
	}

	/**
	 * Returns all the tasks where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching tasks
	 */
	public static List<Task> findByGroupIdUserId(long groupId, long userId) {
		return getPersistence().findByGroupIdUserId(groupId, userId);
	}

	/**
	 * Returns a range of all the tasks where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of matching tasks
	 */
	public static List<Task> findByGroupIdUserId(
		long groupId, long userId, int start, int end) {

		return getPersistence().findByGroupIdUserId(
			groupId, userId, start, end);
	}

	/**
	 * Returns an ordered range of all the tasks where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks
	 */
	public static List<Task> findByGroupIdUserId(
		long groupId, long userId, int start, int end,
		OrderByComparator<Task> orderByComparator) {

		return getPersistence().findByGroupIdUserId(
			groupId, userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tasks where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks
	 */
	public static List<Task> findByGroupIdUserId(
		long groupId, long userId, int start, int end,
		OrderByComparator<Task> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupIdUserId(
			groupId, userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first task in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task
	 * @throws NoSuchTaskException if a matching task could not be found
	 */
	public static Task findByGroupIdUserId_First(
			long groupId, long userId,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupIdUserId_First(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the first task in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task, or <code>null</code> if a matching task could not be found
	 */
	public static Task fetchByGroupIdUserId_First(
		long groupId, long userId, OrderByComparator<Task> orderByComparator) {

		return getPersistence().fetchByGroupIdUserId_First(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the last task in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task
	 * @throws NoSuchTaskException if a matching task could not be found
	 */
	public static Task findByGroupIdUserId_Last(
			long groupId, long userId,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupIdUserId_Last(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the last task in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task, or <code>null</code> if a matching task could not be found
	 */
	public static Task fetchByGroupIdUserId_Last(
		long groupId, long userId, OrderByComparator<Task> orderByComparator) {

		return getPersistence().fetchByGroupIdUserId_Last(
			groupId, userId, orderByComparator);
	}

	/**
	 * Returns the tasks before and after the current task in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param taskId the primary key of the current task
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next task
	 * @throws NoSuchTaskException if a task with the primary key could not be found
	 */
	public static Task[] findByGroupIdUserId_PrevAndNext(
			long taskId, long groupId, long userId,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupIdUserId_PrevAndNext(
			taskId, groupId, userId, orderByComparator);
	}

	/**
	 * Removes all the tasks where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	public static void removeByGroupIdUserId(long groupId, long userId) {
		getPersistence().removeByGroupIdUserId(groupId, userId);
	}

	/**
	 * Returns the number of tasks where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching tasks
	 */
	public static int countByGroupIdUserId(long groupId, long userId) {
		return getPersistence().countByGroupIdUserId(groupId, userId);
	}

	/**
	 * Returns all the tasks where groupId = &#63; and userId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @return the matching tasks
	 */
	public static List<Task> findByGroupIdUserIdTitle(
		long groupId, long userId, String title) {

		return getPersistence().findByGroupIdUserIdTitle(
			groupId, userId, title);
	}

	/**
	 * Returns a range of all the tasks where groupId = &#63; and userId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of matching tasks
	 */
	public static List<Task> findByGroupIdUserIdTitle(
		long groupId, long userId, String title, int start, int end) {

		return getPersistence().findByGroupIdUserIdTitle(
			groupId, userId, title, start, end);
	}

	/**
	 * Returns an ordered range of all the tasks where groupId = &#63; and userId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks
	 */
	public static List<Task> findByGroupIdUserIdTitle(
		long groupId, long userId, String title, int start, int end,
		OrderByComparator<Task> orderByComparator) {

		return getPersistence().findByGroupIdUserIdTitle(
			groupId, userId, title, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tasks where groupId = &#63; and userId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tasks
	 */
	public static List<Task> findByGroupIdUserIdTitle(
		long groupId, long userId, String title, int start, int end,
		OrderByComparator<Task> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupIdUserIdTitle(
			groupId, userId, title, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first task in the ordered set where groupId = &#63; and userId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task
	 * @throws NoSuchTaskException if a matching task could not be found
	 */
	public static Task findByGroupIdUserIdTitle_First(
			long groupId, long userId, String title,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupIdUserIdTitle_First(
			groupId, userId, title, orderByComparator);
	}

	/**
	 * Returns the first task in the ordered set where groupId = &#63; and userId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task, or <code>null</code> if a matching task could not be found
	 */
	public static Task fetchByGroupIdUserIdTitle_First(
		long groupId, long userId, String title,
		OrderByComparator<Task> orderByComparator) {

		return getPersistence().fetchByGroupIdUserIdTitle_First(
			groupId, userId, title, orderByComparator);
	}

	/**
	 * Returns the last task in the ordered set where groupId = &#63; and userId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task
	 * @throws NoSuchTaskException if a matching task could not be found
	 */
	public static Task findByGroupIdUserIdTitle_Last(
			long groupId, long userId, String title,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupIdUserIdTitle_Last(
			groupId, userId, title, orderByComparator);
	}

	/**
	 * Returns the last task in the ordered set where groupId = &#63; and userId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task, or <code>null</code> if a matching task could not be found
	 */
	public static Task fetchByGroupIdUserIdTitle_Last(
		long groupId, long userId, String title,
		OrderByComparator<Task> orderByComparator) {

		return getPersistence().fetchByGroupIdUserIdTitle_Last(
			groupId, userId, title, orderByComparator);
	}

	/**
	 * Returns the tasks before and after the current task in the ordered set where groupId = &#63; and userId = &#63; and title = &#63;.
	 *
	 * @param taskId the primary key of the current task
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next task
	 * @throws NoSuchTaskException if a task with the primary key could not be found
	 */
	public static Task[] findByGroupIdUserIdTitle_PrevAndNext(
			long taskId, long groupId, long userId, String title,
			OrderByComparator<Task> orderByComparator)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByGroupIdUserIdTitle_PrevAndNext(
			taskId, groupId, userId, title, orderByComparator);
	}

	/**
	 * Removes all the tasks where groupId = &#63; and userId = &#63; and title = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 */
	public static void removeByGroupIdUserIdTitle(
		long groupId, long userId, String title) {

		getPersistence().removeByGroupIdUserIdTitle(groupId, userId, title);
	}

	/**
	 * Returns the number of tasks where groupId = &#63; and userId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param title the title
	 * @return the number of matching tasks
	 */
	public static int countByGroupIdUserIdTitle(
		long groupId, long userId, String title) {

		return getPersistence().countByGroupIdUserIdTitle(
			groupId, userId, title);
	}

	/**
	 * Caches the task in the entity cache if it is enabled.
	 *
	 * @param task the task
	 */
	public static void cacheResult(Task task) {
		getPersistence().cacheResult(task);
	}

	/**
	 * Caches the tasks in the entity cache if it is enabled.
	 *
	 * @param tasks the tasks
	 */
	public static void cacheResult(List<Task> tasks) {
		getPersistence().cacheResult(tasks);
	}

	/**
	 * Creates a new task with the primary key. Does not add the task to the database.
	 *
	 * @param taskId the primary key for the new task
	 * @return the new task
	 */
	public static Task create(long taskId) {
		return getPersistence().create(taskId);
	}

	/**
	 * Removes the task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param taskId the primary key of the task
	 * @return the task that was removed
	 * @throws NoSuchTaskException if a task with the primary key could not be found
	 */
	public static Task remove(long taskId)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().remove(taskId);
	}

	public static Task updateImpl(Task task) {
		return getPersistence().updateImpl(task);
	}

	/**
	 * Returns the task with the primary key or throws a <code>NoSuchTaskException</code> if it could not be found.
	 *
	 * @param taskId the primary key of the task
	 * @return the task
	 * @throws NoSuchTaskException if a task with the primary key could not be found
	 */
	public static Task findByPrimaryKey(long taskId)
		throws es.test.exception.NoSuchTaskException {

		return getPersistence().findByPrimaryKey(taskId);
	}

	/**
	 * Returns the task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param taskId the primary key of the task
	 * @return the task, or <code>null</code> if a task with the primary key could not be found
	 */
	public static Task fetchByPrimaryKey(long taskId) {
		return getPersistence().fetchByPrimaryKey(taskId);
	}

	/**
	 * Returns all the tasks.
	 *
	 * @return the tasks
	 */
	public static List<Task> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of tasks
	 */
	public static List<Task> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tasks
	 */
	public static List<Task> findAll(
		int start, int end, OrderByComparator<Task> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of tasks
	 */
	public static List<Task> findAll(
		int start, int end, OrderByComparator<Task> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the tasks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of tasks.
	 *
	 * @return the number of tasks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TaskPersistence getPersistence() {
		return _persistence;
	}

	private static volatile TaskPersistence _persistence;

}