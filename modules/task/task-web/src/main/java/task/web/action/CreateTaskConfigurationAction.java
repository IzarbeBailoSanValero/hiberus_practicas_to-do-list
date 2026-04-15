package task.web.action;


import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

import task.web.constants.TaskWebPortletKeys;

@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=" + TaskWebPortletKeys.TASKWEB
	    },
	    service = ConfigurationAction.class
	)


public class CreateTaskConfigurationAction extends DefaultConfigurationAction{

	
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {
		
		
		// 1. Capturo valores del formulario
		  String portletTitle = ParamUtil.getString(actionRequest, "portletTitle", "Gestión de Tareas");
		  int tasksPerPage = ParamUtil.getInteger(actionRequest, "tasksPerPage", 5);
		  boolean showCompleted = ParamUtil.getBoolean(actionRequest, "showCompleted", true);
		
		  // 2. Obtener objeto preferencias del portlet
	        PortletPreferences preferences = actionRequest.getPreferences();
	        
	        // 3. Guardar los valores
	        preferences.setValue("portletTitle", portletTitle);
	        preferences.setValue("tasksPerPage", String.valueOf(tasksPerPage));
	        preferences.setValue("showCompleted", String.valueOf(showCompleted));
		
	        // 4. Persistir en BBDD
	        preferences.store();
		
		
		// 5. esto hace que funcione el redirect y se complete el flujo bine
		super.processAction(portletConfig, actionRequest, actionResponse);
	}
	
	//6. establecer pagina jsp de configuración
   //Cuando el usuario abra la pantalla de configuración del portlet, usa este JSP
	public String getJspPath(javax.portlet.PortletRequest portletRequest) {
	    return "/configuration.jsp";
	}
	
	
}
