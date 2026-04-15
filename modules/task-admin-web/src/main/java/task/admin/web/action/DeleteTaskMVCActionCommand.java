package task.admin.web.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.service.TaskLocalService;
import task.admin.web.constants.TaskAdminWebPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + TaskAdminWebPortletKeys.TASKADMINWEB,
		"mvc.command.name=" + TaskAdminWebPortletKeys.DELETE }, service = MVCActionCommand.class)

public class DeleteTaskMVCActionCommand extends BaseMVCActionCommand {

	@Reference
	private TaskLocalService _taskLocalService;

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		// obtengo themedisplay para sacar contexto
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		// SEGURIDAD: usuario loggueado + administrador
		if (!themeDisplay.isSignedIn() || !themeDisplay.getPermissionChecker().isOmniadmin()) {
			SessionErrors.add(actionRequest, "error.permission");
			hideDefaultErrorMessage(actionRequest);
			// en la jsp tendré que poner <liferay-ui:error key="permission-error"
			// message="No tienes permisos para acceder a esta sección." />
			return;
		}

		// PARAMETROS CLAVE
		long taskId = ParamUtil.getLong(actionRequest, "taskId", 0);

		if (taskId <= 0) {
			SessionErrors.add(actionRequest, "error.task.not-found");
			hideDefaultErrorMessage(actionRequest);
			return;
		}

		try {
			_taskLocalService.deleteTask(taskId);
			SessionMessages.add(actionRequest, "success.deleted");
			
		} catch (Exception e) {
			SessionErrors.add(actionRequest, "error.task.delete");
			hideDefaultErrorMessage(actionRequest);
		}
		
		//vuelvo a renderizar lista
		actionResponse.getRenderParameters().setValue("mvcRenderCommandName", TaskAdminWebPortletKeys.DEFAULT);

	}

}
