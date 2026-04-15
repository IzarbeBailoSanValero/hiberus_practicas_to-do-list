package task.admin.web.action;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.model.Task;
import es.test.service.TaskLocalService;
import task.admin.web.constants.TaskAdminWebPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + TaskAdminWebPortletKeys.TASKADMINWEB,
		"mvc.command.name=" + TaskAdminWebPortletKeys.CREATE_EDIT_FORM }, service = MVCRenderCommand.class)


public class AddEditTasFormMVCRenderCommand implements MVCRenderCommand {

	@Reference
	private TaskLocalService _taskLocalService;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		// obtengo themeDisplay para infromación de usuario y grupo
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		// SEGURIDAD: usuario loggueado + administrador
		if (!themeDisplay.isSignedIn() || !themeDisplay.getPermissionChecker().isOmniadmin()) {
			SessionErrors.add(renderRequest, "error.permission");
			// en la jsp tendré que poner <liferay-ui:error key="permission-error" message="No tienes permisos para acceder a esta sección." />
			// permiso, si no está loggeado error
			if (!themeDisplay.isSignedIn()) {
			    HttpServletResponse response = PortalUtil.getHttpServletResponse(renderResponse);
			    String homeURL = themeDisplay.getURLHome();

			    try {
			        response.sendRedirect(homeURL);
			        return null; // Detenemos el render si la redirección funciona
			    } catch (IOException e) {
			        // Aquí manejas el error si la redirección falla
			        e.printStackTrace(); 
			    }
			}
		}
		
		//editar/crear? Lo relleno vacío con valores existentes
        long taskId = ParamUtil.getLong(renderRequest, "taskId", 0);
        
   
        if(taskId > 0) {
        	Task task = _taskLocalService.fetchTask(taskId);
        	
        	 // Pasamos la tarea al JSP
            renderRequest.setAttribute("task", task);
        }
        
        
        
        //gestiono parte pública: OBTENGO LISTA DE EMPLEADOSA LOS QUE ASIGNAR TAREAS
        List<User> allUsers = UserLocalServiceUtil.getUsers(-1, -1); // es (start, end)--> -1 -1 esuna convención de liferay que muestra todos los registros sin paginar
        
        List<User> normalUsers = new ArrayList<>();
        
        for (User user : allUsers) {
        	if(PortalUtil.isOmniadmin(user)) {
        		continue;
        	}
        	
        	normalUsers.add(user);
        }
        
        renderRequest.setAttribute("normalUsers", normalUsers);
        
        
        
        return TaskAdminWebPortletKeys.JSP_CREATE_EDIT_TASK;
	}
}






















































