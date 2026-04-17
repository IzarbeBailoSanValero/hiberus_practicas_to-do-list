package task.web.action;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.model.Task;
import es.test.service.TaskLocalService;
import task.web.configuration.GroupConfig;
import task.web.configuration.SystemConfig;
import task.web.constants.TaskWebPortletKeys;

@Component(configurationPid = "task.web.configuration.SystemConfig", immediate = true, property = {
		"javax.portlet.name=" + TaskWebPortletKeys.TASKWEB,
		"mvc.command.name=" + TaskWebPortletKeys.DEFAULT }, service = MVCRenderCommand.class)

public class TaskListMVCRenderCommand implements MVCRenderCommand {

	@Reference
	private TaskLocalService _taskLocalService;

	private static final Log _log = LogFactoryUtil.getLog(TaskListMVCRenderCommand.class); 

	@Reference
	private ConfigurationProvider _configurationProvider; // Servicio central de Liferay para leer configs de todos los
															// scopes. Inyectado por OSGi.



	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		_log.info("entro en el render");

		// 1. LEO LAS PREFERENCIAS DE CONFIGURACIÓN DEL PORTLET
		PortletPreferences preferences = renderRequest.getPreferences();
		String portletTitle = preferences.getValue("portletTitle", "portlet.title");
		int tasksPerPage = Integer.parseInt(preferences.getValue("tasksPerPage", "5"));
		boolean showCompleted = Boolean.parseBoolean(preferences.getValue("showCompleted", "true"));
		


		//// 1. LEO LAS PREFERENCIAS DE CONFIGURACIÓN DEL SYSTEM --> LAS GUARDO EN EL
		//// REQUEST PARA HACERLAS ACCESIBLES DESDE LA JSP
		try {
			SystemConfig systemConfig = _configurationProvider.getSystemConfiguration(SystemConfig.class);

			renderRequest.setAttribute(SystemConfig.class.getName(), systemConfig);
		} catch (Exception e) {
			_log.error("Error obteniendo configuración a nivel de sistema", e);
			e.printStackTrace();
		}
		
		
		//2. PREFERENCIAS DE CONFIGURACIÓN A NIVEL DE SITE. EN LA DOCUMENTACIÓN INYECTAN CON @REFERENCE PORTAL PARA OBTENER EL GROUPID PERO COMO YO LO TENÍA SACADO CON THEMEDISPPLAY VOY A USAR MI MÉTODO
		// La UI de configuracion GROUP esta en Site Settings. 
	
		// DATOS DE CONTEXTO
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		
		//LEO PREFERENCIAS Y LAS IYECTO AL REQUEST
		try {
	    GroupConfig groupConfig =
	            _configurationProvider.getGroupConfiguration(     
	                GroupConfig.class, groupId);
	     
	    renderRequest.setAttribute(GroupConfig.class.getName(), groupConfig);
		}catch (Exception e) {
			_log.error("Error obteniendo configuración a nivel de site", e);
			e.printStackTrace();
		}
		
		

		// PARÁMETROS DE BÚSQUEDA
		String keywords = ParamUtil.getString(renderRequest, "keywords", StringPool.BLANK);
		String searchType = ParamUtil.getString(renderRequest, "searchType", "all");

		// PAGINACIÓN
		int delta = tasksPerPage;
		int cur = ParamUtil.getInteger(renderRequest, "cur", 1); // por defecto pagina 1
		// calculo start y end --> como index0, la pagina 2 empieza con el elemento 5 y
		// acaba con el 10
		int start = (cur - 1) * delta;
		int end = start + delta;

		// LÓGICA DE BÚSQUEDA
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

		// FILTRAR POR showCompleted
		if (!showCompleted) {
			List<Task> filteredTasks = new ArrayList<Task>();

			for (Task task : tasks) {
				if (!task.isCompleted()) {
					filteredTasks.add(task);
				}
			}

			tasks = filteredTasks;
			total = tasks.size();
		}

		// PASAR DATOS AL REQUEST
		renderRequest.setAttribute("tasks", tasks); // La lista ya filtrada y recortada
		renderRequest.setAttribute("total", total); // para mostrar bien la paginación
		renderRequest.setAttribute("keywords", keywords); // Para que el input no se vacíe al buscar
		renderRequest.setAttribute("searchType", searchType);
		// como se vuelve a renderizar la pagina si paso las keywords y el searchtype s
		// eve la muestra de lo que has buscado en lugar de resetearse

		renderRequest.setAttribute("portletTitle", portletTitle);
		renderRequest.setAttribute("showCompleted", showCompleted);
		renderRequest.setAttribute("tasksPerPage", tasksPerPage);

		return "/view.jsp";
	}
}