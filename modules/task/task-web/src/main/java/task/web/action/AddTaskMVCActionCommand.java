package task.web.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;
import java.util.Date;

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
	        "mvc.command.name=" + TaskWebPortletKeys.ADD
	    },
	    service = MVCActionCommand.class
	)

public class AddTaskMVCActionCommand extends BaseMVCActionCommand {

	@Reference
	private TaskLocalService _taskLocalService; // iny dep

	public void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

		try {

			// fabricar el serviceContext, se hace con serviceContextFactory, pasando la
			// clase con al que trabajo y el request actual
			// La "fábrica" (ServiceContextFactory) recorre el request y va buscando valores
			// específicos:Los copia al ServiceContext.
			ServiceContext serviceContext = ServiceContextFactory.getInstance(Task.class.getName(), actionRequest);

			// obtengo themedisplay para sacar el groupid
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = themeDisplay.getScopeGroupId();

			// Crear DateFormat correcto según la locale del usuario
			DateFormat dateFormat = DateFormatFactoryUtil.getDate(themeDisplay.getLocale());

			// obtengo aprametros del formulario
			String taskTitle = ParamUtil.getString(actionRequest, "title");
			String taskDescription = ParamUtil.getString(actionRequest, "description");
			Date taskDueDate = ParamUtil.getDate(actionRequest, "dueDate",  dateFormat);

			// añado la tarea
			_taskLocalService.addTask(groupId, serviceContext, taskTitle, taskDescription, taskDueDate);

			

		} catch (Exception e) {
			System.out.println("entrando a portlet exception");
			throw new PortletException(e);
		}

	}

	
}
