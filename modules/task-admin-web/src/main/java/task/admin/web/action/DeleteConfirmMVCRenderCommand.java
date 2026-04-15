package task.admin.web.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.model.Task;
import es.test.service.TaskLocalService;
import task.admin.web.constants.TaskAdminWebPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + TaskAdminWebPortletKeys.TASKADMINWEB,
		"mvc.command.name=" + TaskAdminWebPortletKeys.DELETE_CONFIRM }, service = MVCRenderCommand.class)

public class DeleteConfirmMVCRenderCommand implements MVCRenderCommand {

	@Reference
	private TaskLocalService _taskLocalService;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		// obtengo themeDisplay para infromación de usuario y grupo
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		// SEGURIDAD: usuario loggueado + administrador
		if (!themeDisplay.isSignedIn() || !themeDisplay.getPermissionChecker().isOmniadmin()) {
			SessionErrors.add(renderRequest, "error.permission");
			// en la jsp tendré que poner <liferay-ui:error key="permission-error"
			// message="No tienes permisos para acceder a esta sección." />

			return null; // para que me devuelva a l apgina que estaba y no m pete porque me pide un
							// String (sisolo pusier return error porque no es void)
		}

		// obtener taskId
		long taskId = ParamUtil.getLong(renderRequest, "taskId", 0);

		if (taskId <= 0) {
			SessionErrors.add(renderRequest, "error.task.not-found");
			return null;
		}

		// Cargar la tarea
		Task task = _taskLocalService.fetchTask(taskId);

		if (task == null) {
			SessionErrors.add(renderRequest, "error.task.not-found");
			return null;
		}

		//paso el taskId a la delete_confirm_jsp. para mostrar los datos
		renderRequest.setAttribute("task", task);
		
		//redirijo
		 return TaskAdminWebPortletKeys.JSP_DELETE_CONFIRM;
		
		
		
		
		
		
		
	}
	
	

}
