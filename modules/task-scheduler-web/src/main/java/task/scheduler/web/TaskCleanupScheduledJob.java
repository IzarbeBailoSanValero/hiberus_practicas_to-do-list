package task.scheduler.web;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.model.Task;
import es.test.service.TaskLocalService;

@Component(
	    property = {
	        "dispatch.task.executor.name=" + TaskCleanupScheduledJob.KEY,
	        "dispatch.task.executor.type=" + TaskCleanupScheduledJob.KEY
	    },
	    service = DispatchTaskExecutor.class
	)
	public class TaskCleanupScheduledJob extends BaseDispatchTaskExecutor {

	public static final String KEY = "task-cleanup";

	
	@Reference
	private TaskLocalService _taskLocalService;
	
	private static final Log _log = LogFactoryUtil.getLog(TaskCleanupScheduledJob.class);

	@Override
	public void doExecute(DispatchTrigger dispatchTrigger, DispatchTaskExecutorOutput dispatchTaskExecutorOutput)
			throws IOException, PortalException {

		if (_log.isInfoEnabled()) {
			_log.info("Invoking #doExecute(DispatchTrigger, " + "DispatchTaskExecutorOutput)");
		}

		// CALCULAR LA FECHA DE CORTE: HOY MENOS 15 DiAS
		Calendar calendar = CalendarFactoryUtil.getCalendar(); // Crea un objeto calnedario basado en al fecha de hoy
		calendar.add(Calendar.DAY_OF_YEAR, -0); // Modifica ese objeto calendario, lo lleva 15 días atrás
		Date cutoffDate = calendar.getTime(); // día de corte = día en el que está basado el calendario
		_log.info("Task cleanup job iniciado. Fecha de corte: " + cutoffDate);

		// BUSCO EN AL DYNAMIC QUERY

		try {
			
			List<Task> tasksToDesactivate = _taskLocalService.getTasksToDesactivate(cutoffDate);
			_log.info("tareas a desactivar: " + tasksToDesactivate);

			if (tasksToDesactivate.isEmpty()) {
				
				String msg = "No hay tareas a inactivar.";
				_log.info(msg);
				dispatchTaskExecutorOutput.setOutput(msg);
				return;
			}

			
			// marcarlas como inactivas
			int count = 0;
			for (Task task : tasksToDesactivate) {
				task.setActive(false);
				_taskLocalService.updateTask(task);
				count++;
			}

			// registro de resultados
			String msg = "Tareas desactivadas: " + count;
			_log.info(msg);
			dispatchTaskExecutorOutput.setOutput(msg);

		} catch (Exception e) {
			_log.error("error recuperando tareas a eliminar", e);
			dispatchTaskExecutorOutput.setError(e.getMessage());
		}

	}

	@Override
	public String getName() {
		return "TaskCleanupScheduledJob";
	}

}
