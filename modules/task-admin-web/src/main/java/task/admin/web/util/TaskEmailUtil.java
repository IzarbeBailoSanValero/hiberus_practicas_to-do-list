package task.admin.web.util;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PrefsPropsUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true,

		service = { TaskEmailUtil.class })
public class TaskEmailUtil {
	/**
	 * centraliza toda la logica de envio de emial. laregistro con component y la
	 * inyectare cnreference.
	 */

	private static final Log _log = LogFactoryUtil.getLog(TaskEmailUtil.class);

	private static final String TEMPLATE_PATH = "email/templates/assigned-task-template.html";
	
	
	/**
	 * CONSTRUYE EMAIL + LO ENVÍA
	 * @param actionRequest
	 * @param themeDisplay
	 * @param userMail
	 * @param userName
	 * @param taskName
	 * @param taskDescription
	 * @param taskDueDate
	 * @return
	 */
	public boolean sendEmail(ActionRequest actionRequest,ThemeDisplay themeDisplay, String userMail, String userName, String taskName, String taskDescription, String taskDueDate) {
		try {
			//CONSTRUYO OBJETO MAILMESSAGE
			MailMessage mailMessage = new MailMessage();
			
			InternetAddress toAddress = new InternetAddress(userMail, true);
			mailMessage.setTo(toAddress);
			
			InternetAddress fromAddress = new InternetAddress(PrefsPropsUtil.getString(themeDisplay.getCompanyId(), "admin.email.from.address"),true);
			mailMessage.setFrom(fromAddress);
			
			////asunto
			ResourceBundle rb = ResourceBundle.getBundle(
					 "content/Language", actionRequest.getLocale());
			
			String subject = LanguageUtil.get(rb, "email.subject");
			mailMessage.setSubject(subject);

			
			////cuerpo con los parametros sustitudos
			String body = createBody(rb,userMail,  userName,  taskName,  taskDescription,  taskDueDate);
			mailMessage.setBody(body);
			
			
			////formato (PARA NO VER RAW HTML)
			mailMessage.setHTMLFormat(true);
			
			//ENVÍO MALMESSAGE - a través de su service, mailservice
			MailServiceUtil.sendEmail(mailMessage);
			_log.info("Email enviado correctamente a: " + userMail);
			return true;
			
			
		} catch (AddressException e) {
			_log.error("Invalid email " + userMail, e);
		} catch (Exception e) {
			_log.error("Error sending email ");
		}
		return false;
	}

	
	
	
	/**
	 * CARGA PLANTILLA + SUSTITUYE PARÁMETROS (JAVA + I18N)
	 * @param rb
	 * @param userMail
	 * @param userName
	 * @param taskName
	 * @param taskDescription
	 * @param taskDueDate
	 * @return
	 */
	private String createBody (ResourceBundle rb ,String userMail, String userName, String taskName, String taskDescription, String taskDueDate) {
		
		String template = chargeTemplate(TEMPLATE_PATH);
		
		if (template.isEmpty()) {
			 return "<p>No se pudo cargar la plantilla del email.</p>";
			
			 }

		
		// --- Marcadores de etiquetas i18n ---> en todos los mails
		 // "email.greeting" contiene "Hola, {0}" en español,
		 // y {0} se sustituye por el nombre del usuario con format().
		
		 template = template.replace("[$EMAIL_GREETING$]",LanguageUtil.format(rb, "email.greeting",new String[]{userName}));
		 template = template.replace("[$EMAIL_DUE_DATE$]",LanguageUtil.get(rb, "email.label.date"));
		 template = template.replace("[$EMAIL_DESCRIPTION$]",LanguageUtil.get(rb, "email.label.desc"));
		 template = template.replace("[$EMAIL_TITLE$]",LanguageUtil.get(rb, "email.label.title"));
		 template = template.replace("[$EMAIL_SUBJECT$]",LanguageUtil.get(rb, "email.label.subject"));
		 template = template.replace("[$EMAIL_FOOTER$]",LanguageUtil.get(rb, "email.footer"));
		 
		 // --- Marcadores de variables Java ---> de esta tarea ne concreto
		 template = template.replace("[$TASK_NAME$]", taskName);
		 template = template.replace("[$TASK_DESCRIPTION$]", taskDescription);
		 template = template.replace("[$TASK_DUE_DATE$]", taskDueDate);
		
		
		
		
		 return template;

	}
	
	
	
	
	/**
	 * LEE HTML TEMPLATE + LO DEVUELVE COMO STRING
	 * ell html template se lee desde el classpath
	 * classloader busca el fichero con respecto a src/main/resources. ClassLoader es el encargado de buscar y cargar archivos que están dentro del módulo (JAR).
	 * InputStream,InputStream → permite leer su contenido. Un InputStream es una forma de leer datos secuencialmente (byte a byte)..Si encuentra el fichero → te da el contenido Si no → devuelve null
	 
	 *Porque Java trabaja a bajo nivel con archivos:Primero: flujo de bytes (InputStream) Luego: tú lo conviertes a String
	 *
	 * @param templatePath
	 * @return
	 */
	private String chargeTemplate(String templatePath) {
		String template = StringPool.BLANK;
		ClassLoader classLoader = TaskEmailUtil.class.getClassLoader();
		InputStream is = classLoader.getResourceAsStream(templatePath);
		
		if (is == null) {
			 _log.error("Plantilla no encontrada en: " + templatePath);
			 return template;
		}
		
		try {
			
			template = new String(readBiteTransformToByteArray(is));
		} catch (IOException e) {
			_log.error("Error reading " + templatePath + " JSON file");
		} catch (Exception e) {
			_log.error("There was an error reading the email template " + e.toString());
		}
		
		return template;
		}
		
	
	
	
	/**
	 * Convierte un InputStream en un array de bytes leyendo byte a byte
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private byte[] readBiteTransformToByteArray(InputStream is) throws IOException { 
		
		//Crear contenedor en memoria
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		
		//voy leyendo
		//Devuelve un byte (0–255) o -1 cuando ya no hay más datos (fin del archivo)
		for (int b = is.read(); b != -1; b = is.read()) {
			//Va añadiendo cada byte leído a la “caja”
			  baos.write(b);
		}
		//convertir en array = Devuelve todo como: byte[]
		return baos.toByteArray(); 
	}

}
