package task.admin.web.action;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.model.Task;
import es.test.service.TaskLocalService;
import task.admin.web.constants.TaskAdminWebPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + TaskAdminWebPortletKeys.TASKADMINWEB,
		"mvc.command.name=" + TaskAdminWebPortletKeys.SAVE_TASK }, service = MVCActionCommand.class)

public class SaveTaskMVCActionCommand extends BaseMVCActionCommand {

	@Reference
	private TaskLocalService _taskLocalService;
	private static final Log _log = LogFactoryUtil.getLog(SaveTaskMVCActionCommand.class);
	

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		// obtengo themedisplay para sacar contexto
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		// SEGURIDAD: usuario loggueado + administrador
		if (!themeDisplay.isSignedIn() || !themeDisplay.getPermissionChecker().isOmniadmin()) {
			SessionErrors.add(actionRequest, "error.permission");
			hideDefaultErrorMessage(actionRequest);
			// en la jsp tendre que poner liferay-ui:error key="permission-error"
			// message="No tienes permisos para acceder a esta seccion." 
			return;
		}

		// CONTEXTO
		// Crear DateFormat correcto segun la locale del usuario
		DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

		long groupId = themeDisplay.getScopeGroupId();

		ServiceContext serviceContext = ServiceContextFactory.getInstance(Task.class.getName(), actionRequest);

		// PARAMETROS CLAVE
		long taskId = ParamUtil.getLong(actionRequest, "taskId", 0);

		String taskTitle = ParamUtil.getString(actionRequest, "title", StringPool.BLANK);
		String taskDescription = ParamUtil.getString(actionRequest, "description", StringPool.BLANK);
		Date taskDueDate = ParamUtil.getDate(actionRequest, "dueDate", dateFormat);
		String rawDueDate = ParamUtil.getString(actionRequest, "dueDate", "VACIO");
		_log.info(">>> rawDueDate recibido: " + rawDueDate);
		_log.info(">>> taskDueDate parseado: " + taskDueDate);
		long taskUserId = ParamUtil.getLong(actionRequest, "assignedUserId", 0);
		 _log.info(taskUserId);
		
		// Validacion titulo
		if (taskTitle.isEmpty()) {
			SessionErrors.add(actionRequest, "error.title.required");
			hideDefaultErrorMessage(actionRequest);
			return;
		}

		try {
			if (taskId <= 0) {
				_taskLocalService.addTask(groupId,taskUserId, serviceContext, taskTitle, taskDescription, taskDueDate);
			} else {
				Task task = _taskLocalService.fetchTask(taskId);

				if (task == null) {
					SessionErrors.add(actionRequest, "error.task.not-found");
					hideDefaultErrorMessage(actionRequest);
					return;
				}

				task.setTitle(taskTitle);
				task.setDescription(taskDescription);
				task.setDueDate(taskDueDate);
				task.setUserId(taskUserId);
				_taskLocalService.updateTask(task);
			}

			SessionMessages.add(actionRequest, "success.saved");
			

			// para renderizar la lista encesito volver a ejecutar rendercommand
			actionResponse.getRenderParameters().setValue("mvcRenderCommandName", TaskAdminWebPortletKeys.DEFAULT);
		} catch (Exception e) {

			if (taskId <= 0) {
				SessionErrors.add(actionRequest, "error.task.create");
				hideDefaultErrorMessage(actionRequest);
			} else {
				SessionErrors.add(actionRequest, "error.task.update");
				hideDefaultErrorMessage(actionRequest);
			}

			actionResponse.getRenderParameters().setValue("mvcRenderCommandName", TaskAdminWebPortletKeys.DEFAULT);
		}

	}
}