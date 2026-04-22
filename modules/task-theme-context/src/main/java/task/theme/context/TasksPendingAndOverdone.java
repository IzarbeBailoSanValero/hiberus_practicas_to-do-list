package task.theme.context;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.service.TaskLocalService;

@Component(
		service = TasksPendingAndOverdone.class)
public class TasksPendingAndOverdone {
	
	 @Reference
	    private TaskLocalService _taskLocalService;

	    public int getPendingTasks(long userId, long groupId) {
	        return (int) _taskLocalService.getTasksPendingCount(new Date(), userId, groupId);
	    }

	    public int getOverdueTasks(long userId, long groupId) {
	        return (int) _taskLocalService.getTasksOverdueCount(new Date(), userId, groupId);
	    }

}
