
package task.admin.web.action;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.model.Task;
import es.test.service.TaskLocalService;
import task.admin.web.constants.TaskAdminWebPortletKeys;

//SOLO VISIBLE PARA ADMINISTRADORES DEL SITIO
//filtra por groupId
//ROL REQUERIDO: ADMINISTRATOR
//validación de seguridad: está loggeado + es administrador

@Component(immediate = true, property = { "javax.portlet.name=" + TaskAdminWebPortletKeys.TASKADMINWEB,
		"mvc.command.name=" + TaskAdminWebPortletKeys.DEFAULT }, service = MVCRenderCommand.class)


public class TaskListMVCRenderCommand implements MVCRenderCommand {

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

			return null; //para que me devuelva a l apgina que estaba y no m pete porque me pide un String (sisolo pusier return error porque no es void)
		}

		// obtengo groupId para contexto
		long groupId = themeDisplay.getScopeGroupId();

		// capturo parámetros de búsqueda
		String keywords = ParamUtil.getString(renderRequest, "keywords", StringPool.BLANK);
		String searchType = ParamUtil.getString(renderRequest, "searchType", "all");

		// meto parametros de paginación
		int delta = ParamUtil.getInteger(renderRequest, "delta", 5); // por defecto visualización de 5 en 5
		int cur = ParamUtil.getInteger(renderRequest, "cur", 1); // por defecto pagina 1

		// calculo start y end --> como index0, la pagina 2 empieza con el elemento 5 y
		// acaba con el 10
		int start = (cur - 1) * delta;
		int end = start + delta;

		// lógica para búsqueda
		List<Task> tasks = new ArrayList<>();
		int total = 0;

		if (searchType.equals("accurate")) {
			tasks = _taskLocalService.getTaskByTitle(groupId, keywords, start, end);
			total = _taskLocalService.getTasksCountByTitle(groupId, keywords);

		} else if (searchType.equals("elastic")) {
			tasks = _taskLocalService.getTasksByKeywords(groupId, keywords, start, end);
			total = _taskLocalService.getTasksCountByKeywords(groupId, keywords);

		} else {
			tasks = _taskLocalService.getTasksByGroup(groupId, start, end);
			total = _taskLocalService.getTasksCountByGroup(groupId);
		}

		// pasamos los resultados a la jsp: los meto al renderRequest
		renderRequest.setAttribute("tasks",tasks != null ? tasks : new ArrayList<>() ); // La lista ya filtrada y recortada
		renderRequest.setAttribute("total", total); // para mostrar bien la paginación
		renderRequest.setAttribute("keywords",  keywords != null ? keywords : ""); // Para que el input no se vacíe al buscar
		renderRequest.setAttribute("searchType", searchType != null ? searchType : "all");
		
		
		// como se vuelve a renderizar la pagina si paso las keywords y el searchtype s
		// eve la muestra de lo que has buscado en lugar de resetearse

		// mando a la vista
		return "/admin_view.jsp";

	}

}
