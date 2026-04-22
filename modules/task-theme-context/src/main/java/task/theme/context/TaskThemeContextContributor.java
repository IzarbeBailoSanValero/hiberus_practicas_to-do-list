package task.theme.context;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.template.TemplateContextContributor;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	    property = "type=" + TemplateContextContributor.TYPE_GLOBAL,
	    service = TemplateContextContributor.class
	)


public class TaskThemeContextContributor implements TemplateContextContributor {

	@Reference
	private TasksPendingAndOverdone _tasksPendingAndOverdone;

	private static final Log _log = LogFactoryUtil.getLog(TaskThemeContextContributor.class);

	@Override
	public void prepare(Map<String, Object> contextObjects, HttpServletRequest httpServletRequest) {
		// en su método prepareTemplateContext, consulta el servicio de tareas para
		// obtener los contadores del usuario actual
		// El parametro httpServletRequest del método te da acceso al contexto de la
		// petición HTTP, desde el cual puedes obtener el usuario en sesión con
		// PortalUtil.

		_log.info(">>> TaskThemeContextContributor ejecutándose");
		
		try {
			long groupId = PortalUtil.getScopeGroupId(httpServletRequest);

			long userId = PortalUtil.getUserId(httpServletRequest);
			
			int pendingTaskCount = _tasksPendingAndOverdone.getPendingTasks(userId, groupId);
            int overdueTaskCount = _tasksPendingAndOverdone.getOverdueTasks(userId, groupId);

			if (userId == 0)
				return;
			
			//compruebo que se calculan bien
			_log.info(">>> userId=" + userId + " pending=" + pendingTaskCount + " overdue=" + overdueTaskCount);

			// Inyectar en el contexto del tema

			contextObjects.put("pendingTaskCount", pendingTaskCount);
			contextObjects.put("overdueTaskCount", overdueTaskCount);
			
		
		} catch (Exception e) {
			// no propagar la excepción para que no reviente
			// el bloque try/catch es obligatorio. Si el contributor lanza una excepción no
			// capturada, Liferay puede abortar el renderizado de la página entera. Siempre
			// captura las excepciones y logéalas en lugar de propagarlas.
			_log.error("Error retrieving task counts", e);

		}

	}

}
