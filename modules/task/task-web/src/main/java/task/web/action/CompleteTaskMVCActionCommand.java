package task.web.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.model.Task;
import es.test.service.TaskLocalService;
import task.web.constants.TaskWebPortletKeys;

@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=" + TaskWebPortletKeys.TASKWEB,
	        "mvc.command.name="  + TaskWebPortletKeys.COMPLETE
	    },
	    service = MVCActionCommand.class
	)

public class CompleteTaskMVCActionCommand extends BaseMVCActionCommand{
	
	@Reference
	private TaskLocalService _taskLocalService; //iny dep
	
	
	
	
	//actionRequest: Es el objeto que contiene lo que el usuario ha enviado. (el taskId)
	//ActionResponse: instrucciones al navegadir ->  sirve para decirle al portal qué hacer después de que la lógica termine (ej. redirige a esta pagina)
	public void doProcessAction (
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortletException{
		
		try {
		//recoger el id de la tarea completada
		//por qué usar ParamUtil. Habitualment epodría hacer request.getParameter("taskId") pero entonces si alguien envía una eptición sin taskId me devuelve null. con ParamUtil.getLong(request, "taskId") si el parámetro no está devuelve un 0, sin romper app
		long taskId = ParamUtil.getLong(actionRequest, "taskId");
		
		//fabricar el serviceContext, se hace con serviceContextFactory, pasando la clase con al que trabajo y el request actual
		//La "fábrica" (ServiceContextFactory) recorre el request y va buscando valores específicos:Los copia al ServiceContext.
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Task.class.getName(), actionRequest);
		
		//updatear la tarea en la base de datos como completada
		 _taskLocalService.completeTask(taskId, serviceContext);
		
		
		
		//El repitando de la pagina se ejecuta automaticamente. despues de la fase de acción -> actualiza la base de datos -> redibuja TaskListMVCRenderCommand
		
		
		}catch(Exception e) {
			throw new PortletException(e);
		}
		
		
		
	}





}
