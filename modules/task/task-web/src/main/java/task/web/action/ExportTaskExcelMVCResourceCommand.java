package task.web.action;



import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.test.model.Task;
import es.test.service.TaskLocalService;
import task.web.constants.TaskWebPortletKeys;
import task.web.util.ExcelUtil;


@Component(immediate = true, property = { "javax.portlet.name=" + TaskWebPortletKeys.TASKWEB,
		"mvc.command.name=" + TaskWebPortletKeys.EXPORT_EXCEL }, service = MVCResourceCommand.class)
public class ExportTaskExcelMVCResourceCommand extends BaseMVCResourceCommand {

	@Reference
	private TaskLocalService _taskLocalService;

	private static final Log _log = LogFactoryUtil.getLog(ExportTaskExcelMVCResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		
		_log.info("ENTRA EN EXPORT EXCEL");

		// 1. Cabeceras de columnas , Lista simple con los nombres de las columnas
		
		//locale para poder acceder desde java al language properties
		Locale locale = resourceRequest.getLocale();

		List<String> headers = Arrays.asList(
		    LanguageUtil.get(locale, "task.export.id"),
		    LanguageUtil.get(locale, "task.export.title"),
		    LanguageUtil.get(locale, "task.export.status"),
		    LanguageUtil.get(locale, "task.export.date")
		);

		// 2. obtener las tareas y convertirlas en filas para excel

		// genero lista de filas
		Map<String, Object[]> rows = new TreeMap<>();
		int i = 0;

		// recupero las tareas
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();

		List<Task> tasks = _taskLocalService.getTaskByGroup(groupId);

		// convertir cada tarea en una fla
		for (Task t : tasks) {
		    rows.put(String.valueOf(i++), new Object[]{
		        String.valueOf(t.getTaskId()),  
		        t.getTitle(),
		        t.getDescription(),
		        t.getCompleted() ? "Done" : "Pending",
		        t.getDueDate() != null ? t.getDueDate().toString() : ""
		    });
		}
		
		//3. generar archivo excel y enviarlo como descarga
		  // ExcelUtil.exportexcel() recibe headers y filas
        XSSFWorkbook excel = ExcelUtil.exportexcel(resourceRequest, headers, rows);

        // Convertimos el Excel en bytes para enviarlo al navegador
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        excel.write(baos);

        // Enviar el archivo al usuario como descarga
        PortletResponseUtil.sendFile(
        		resourceRequest,
        		resourceResponse,
            "tasks.xlsx",                 // Nombre del archivo
            baos.toByteArray(),               // Contenido del archivo
            ContentTypes.APPLICATION_VND_MS_EXCEL
        );
    }

	}


