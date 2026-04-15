<%@ include file="init.jsp"%>

<%
	//1º LEO LAS PREFERENCIAS ACTUALES DE LA INSTANCIA DEL PORTLET
	////APARECE COMO UNA VARIABLE DISPONIBLE DIRECTAMENTE QUE EN REALIDAD ESTÁ EN AL BASE DE DATOS
	////SE GUARDA SIEMPRE COMO STRING, ALS TENEMOS QUE CASTEAR AQUÍ
	////intenta acceder a portlettitle y si no lo encuentra pone valor por defecto
	String portletTitle = portletPreferences.getValue("portletTitle", "Gestión de tareas");
	int tasksPerPage = Integer.parseInt(portletPreferences.getValue("tasksPerPage", "5"));
	boolean showCompleted = Boolean.parseBoolean(portletPreferences.getValue("showCompleted", "true"));
%>

<!-- 2º URLS ESPECIALES PARA CONFIGURACIÓN 
 sirven para generar URLs especiales que apuntan a la pantalla de configuración del portlet, no a la vista normal.
llevan configuration true porque así liferay distingue que no es una url nomrla de portlet sino una de configuración
- action : para guardar preferencias -> se envía desde el formulario en la pantalla de configuración -> llama a processAction de ConfigurationAction
- render : se usa para mostrar la configuración -> llama a jsp de configuración definido
-->
<liferay-portlet:actionURL portletConfiguration="<%=true%>"
	var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%=true%>"
	var="configurationRenderURL" />


<!-- 3º GENERO FORMULARIO -->
<liferay-frontend:edit-form action="<%=configurationActionURL%>"
	method="post" name="fm" >

<%-- Campos ocultos obligatorios
	 - REDIRECT : Indica a Liferay a dónde debe volver el usuario después de procesar la acción
	 - CMD : Indica a Liferay qué operación debe ejecutar cuando se envía el formulario. (update)
	 - liferay-frontend:edit-form-body -> Es el “cuerpo” del formulario de configuración dentro del panel de Configuration del portlet. hay edit-form, edit-form-body y edit-form-footer
	  --%>
<aui:input name="redirect" type="hidden"
	value="${configurationRenderURL}" />
<aui:input name="<%=Constants.CMD%>" type="hidden"
	value="<%=Constants.UPDATE%>" />

<liferay-frontend:edit-form-body>
	<div class="panel-group panel-group-flush">
		<liferay-frontend:fieldset label="Configuración de visualización"
			collapsible="<%=true%>" collapsed="<%=false%>">

			<!-- TÍTULO -->
			<aui:input type="text" name="portletTitle" value="<%=portletTitle%>"
				helpMessage="título del portlet" />
			<!-- PAGINACIÓN -->
			<aui:input type="number" name="tasksPerPage" value="<%=tasksPerPage%>"
				min="1" max="50" helpMessage="elementos por pagina" />
			<!-- VER COMPLETADOS -->
			<aui:input type="checkbox" name="showCompleted"
				label="Mostrar tareas completadas" checked="<%=showCompleted%>"
				helpMessage="aparecen o no las tareas completadas" />
		</liferay-frontend:fieldset>

	</div>
</liferay-frontend:edit-form-body>
<liferay-frontend:edit-form-footer>
	<aui:button type="submit" value="Guardar" />
	<aui:button type="cancel" />
</liferay-frontend:edit-form-footer>

</liferay-frontend:edit-form>