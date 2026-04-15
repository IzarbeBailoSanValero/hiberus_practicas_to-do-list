package task.user.web.actions;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.model.Task;
import es.test.service.TaskLocalService;
import task.user.web.constants.TaskUserWebPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + TaskUserWebPortletKeys.TASKUSERWEB,
		"mvc.command.name=" + TaskUserWebPortletKeys.COMPLETE }, service = MVCActionCommand.class)

public class CompleteTaskMVCActionCommand extends BaseMVCActionCommand {

	@Reference
	private TaskLocalService _taskLocalService;
	
	private static final Log _log =
		    LogFactoryUtil.getLog(CompleteTaskMVCActionCommand.class);

	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		_log.debug("LLEGO AL ACTION COMMAND");

		// obtengo themedisplay para sacar contexto
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		// SEGURIDAD: usuario loggueado + su propia tarea 
		  // 1. Comprobar login
        if (!themeDisplay.isSignedIn()) {
            SessionErrors.add(actionRequest, "error.permission");
            hideDefaultErrorMessage(actionRequest);
            return;
        }
        _log.info("estoy loggeado");
		//--> si no está loggeado, el ID = 0 asignado por liferay
		User currentUser =  themeDisplay.getUser();
		long currentUserId = currentUser.getUserId();

		
		

		//si no es el mismo usuario que intenta acceder: recupero taskId --> saco el task --> saco el userId de la task y lo comparo con current
		long taskId = ParamUtil.getLong(actionRequest, "taskId", 0);
		
		  if (taskId <= 0) {
	            SessionErrors.add(actionRequest, "error.task.not-found");
	            hideDefaultErrorMessage(actionRequest);
	            return;
	        }
			
		// Recuperar la tarea
	        Task task = _taskLocalService.fetchTask(taskId);

	        if (task == null) {
	            SessionErrors.add(actionRequest, "error.task.not-found");
	            hideDefaultErrorMessage(actionRequest);
	            return;
	        }

	        // Validar que la tarea pertenece al usuario actual
	        if (task.getUserId() != currentUserId) {
	            SessionErrors.add(actionRequest, "error.permission");
	            hideDefaultErrorMessage(actionRequest);
	            return;
	        }
	        _log.info("se ha verificado mi id");
	        
	        
	        try {
	            ServiceContext serviceContext = ServiceContextFactory.getInstance(
	                Task.class.getName(), actionRequest);

	            _taskLocalService.completeTask(taskId, serviceContext);

	            SessionMessages.add(actionRequest, "success.completed");
	            

	        } catch (Exception e) {
	            SessionErrors.add(actionRequest, "error.task.complete");
	            hideDefaultErrorMessage(actionRequest);
	        }
		}
		
		
		
		
		
		
	
		
		
		
		
		
		
		
	}

