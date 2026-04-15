package task.user.web.actions;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
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
import task.user.web.constants.TaskUserWebPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + TaskUserWebPortletKeys.TASKUSERWEB,
		"mvc.command.name=" + TaskUserWebPortletKeys.DEFAULT }, service = MVCRenderCommand.class)

//filtra por groupId y por userId (igual a él)
// ROLE REQUWERIDO: USER --> USUARIO (AUTENTICADO)
// La diferencia clave respecto al portlet de admin está en que en lugar de
// cargar todas las tareas del grupo, filtra solo las del usuario autenticado
// usando su userId.
public class TaskListMVCRenderCommand implements MVCRenderCommand {

	@Reference
	private TaskLocalService _taskLocalService;
	private static final Log _log = LogFactoryUtil.getLog(TaskListMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		_log.debug("LLEGO AL render COMMAND");

		// obtengo el group id y el userId para hacer consultas bbdd --> Si no existen,
		// se les asigna una cadena vacia
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();

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

		long groupId = themeDisplay.getScopeGroupId();
		long userId = themeDisplay.getUserId();

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

		// obtengo las tareas (solo las visibles para el usuario) MEDIANTE FINDER
		List<Task> myTasks = new ArrayList<>();
		int total = 0;

		if (permissionChecker.isOmniadmin()) {

			if (searchType.equals("accurate")) {
				myTasks = _taskLocalService.getTaskByTitle(groupId, keywords, start, end);
				total = _taskLocalService.getTasksCountByTitle(groupId, keywords);

			} else if (searchType.equals("elastic")) {
				myTasks = _taskLocalService.getTasksByKeywords(groupId, keywords, start, end);
				total = _taskLocalService.getTasksCountByKeywords(groupId, keywords);

			} else {
				myTasks = _taskLocalService.getTasksByGroup(groupId, start, end);
				total = _taskLocalService.getTasksCountByGroup(groupId);
			}
			
		} else {
			if (searchType.equals("accurate")) {
				// BÚSQUEDA EXACTA
				myTasks = _taskLocalService.getTasksByTitleAndUser(groupId, userId, keywords, start, end);
				total = _taskLocalService.getTasksCountByTitleAndUser(groupId, userId, keywords);

			} else if (searchType.equals("elastic")) {
				// BÚSQUEDA FLEXIBLE
				myTasks = _taskLocalService.getTasksByKeywords(groupId, userId, keywords, start, end);
				total = _taskLocalService.getTasksCountByKeywords(groupId, userId, keywords);

			} else {
				// TODAS DEL USUARIO
				myTasks = _taskLocalService.findByGroupIdUserId(groupId, userId, start, end);
				total = _taskLocalService.getTasksCountByGroupIdUserId(groupId, userId);
			}
		}

		// pasamos los resultados a la jsp: los meto al renderRequest
		renderRequest.setAttribute("tasks", myTasks); // La lista ya filtrada y recortada
		renderRequest.setAttribute("total", total); // para mostrar bien la paginación
		renderRequest.setAttribute("keywords", keywords); // Para que el input no se vacíe al buscar
		renderRequest.setAttribute("searchType", searchType);
		// como se vuelve a renderizar la pagina si paso las keywords y el searchtype s
		// eve la muestra de lo que has buscado en lugar de resetearse

		// mando a la vista
		return "/user_view.jsp";

	}

}
