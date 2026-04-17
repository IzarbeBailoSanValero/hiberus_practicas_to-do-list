package task.web.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	    category ="task",            
	    scope = ExtendedObjectClassDefinition.Scope.GROUP  
	)
	@Meta.OCD( //metaocd es como un contenedor de configuracion de osgi 
			//META OCD --> DEFINE EL FORMULARIO DE CONFIGURACION
	    id = "task.web.configuration.GroupConfig",  //  Identificador único de la configuración. Debe ser el nombre completo de la clase . Si no → la config no funciona
	    localization = "content/Language",             // Archivo de idiomas, tinee que ser la ruta relativa
	    name = "task.config.group"                    // nombre que se muestra en la UI , clave i18n
	)
	public interface GroupConfig {
		//META AD --> DEFINE LOS CAMPOS DEL FOMRULARIO
	//Si pones required = true sin deflt, el admin DEBE configurar el campo o la app falla al arrancar.
	 
	    
	    
	    @Meta.AD(deflt = "Esta es mi prase configurada a nivel de group", required = false)
	    String groupParagraph();
	    
	}
