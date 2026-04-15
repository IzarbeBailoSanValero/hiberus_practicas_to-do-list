package task.web.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import task.web.constants.TaskWebPortletKeys;



@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=" + TaskWebPortletKeys.TASKWEB,
	        "mvc.command.name=" + TaskWebPortletKeys.CREATE_FORM
	    },
	    service = MVCRenderCommand.class
	)

public class CreateTaskMVCRenderCommand implements MVCRenderCommand{
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException{
		return "/create.jsp";
	}
	
}