package task.web.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	    category ="task",               //Controla en que seccion/categoria de System Settings aparece la UI. Si no se especifica, aparece en 'Other > Third Party'.
	    scope = ExtendedObjectClassDefinition.Scope.SYSTEM  // Una sola instancia en toda la plataforma.
	)
	@Meta.OCD( //metaocd es como un contenedor de configuracion de osgi 
			//META OCD --> DEFINE EL FORMULARIO DE CONFIGURACION
	    id = "task.web.configuration.SystemConfig",  //  Identificador único de la configuración. Debe ser el nombre completo de la clase . Si no → la config no funciona
	    localization = "content/Language",             // Archivo de idiomas, tinee que ser la ruta relativa
	    name = "task.config.system"                    // nombre que se muestra en la UI , clave i18n
	)
	public interface SystemConfig {
		//META AD --> DEFINE LOS CAMPOS DEL FOMRULARIO
	//Si pones required = true sin deflt, el admin DEBE configurar el campo o la app falla al arrancar.
	 
	    @Meta.AD(deflt = "https://api.global.com", required = false)  
	    String apiUrl();
	 
	    @Meta.AD(deflt = "true", required = false)
	    boolean enableFeature();
	    
	    @Meta.AD(deflt = "Esta es mi prase configurada a nivel de System", required = false)
	    String systemParagraph();
	    
	}
