package task.web.action;



import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.service.TaskLocalService;
import task.web.constants.TaskWebPortletKeys;


@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=" + TaskWebPortletKeys.TASKWEB,
	        "mvc.command.name=" + TaskWebPortletKeys.DELETE
	    },
	    service = MVCActionCommand.class
	)

public class DeleteTaskMVCActionCommand extends BaseMVCActionCommand{

	@Reference
	private TaskLocalService _taskLocalService; // iny dep
	
	@Override
	public void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		
		try {
			
			
		//conseguir el id	
			long taskId = ParamUtil.getLong(actionRequest, "taskId");
				
			System.out.println("ID recibido: " + taskId);
		
		//borrado
		_taskLocalService.deleteTask(taskId);
		

	} catch (Exception e) {
		throw new PortletException(e);
	}



	}
}
