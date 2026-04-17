<%@ include file="init.jsp"%>

<!-- CONFIGURACIÓN DE SISTEMA (no puedo llamarla solo config porque choca con una que se crea automaticamente portletcongif en defineobject)-->
<%
SystemConfig systemConfig = (SystemConfig) request.getAttribute(SystemConfig.class.getName());
GroupConfig groupConfig = (GroupConfig)  request.getAttribute(GroupConfig.class.getName());

String myParagraphFromSystem = "";
String myParagraphFromGroup = "";



if (systemConfig != null) {
	myParagraphFromSystem = systemConfig.systemParagraph();
	myParagraphFromGroup = groupConfig.groupParagraph();
}
%>

<h4>Escrito desde System settings: <%= myParagraphFromSystem %></h4>
<hr>
<h4>Escrito desde System settings: <%= myParagraphFromGroup %></h4>
<hr>

<!-- RECUPERAR DATOS DEL RENDERCOMMAND + LEER LAS PREFERENCIAS DEL SITE (lo casteo porque devuelve Object)-->
<%
	List<Task> tasks = (List<Task>) renderRequest.getAttribute("tasks");
	int totalTasks = (int) renderRequest.getAttribute("total");
	String keywords = (String) renderRequest.getAttribute("keywords");
	String searchType = (String) renderRequest.getAttribute("searchType");
	
	 String portletTitle =(String)  renderRequest.getAttribute("portletTitle");
	 int tasksPerPage = (int) renderRequest.getAttribute("tasksPerPage");
	 
%>







<div class="tasks-container">

	<h1><liferay-ui:message key="${portletTitle}" /></h1>
	


	<%-- CREAR NUEVA TAREA --%>
	<portlet:renderURL var="createTaskURL">
		<portlet:param name="mvcRenderCommandName" value="/task/create_form" />
	</portlet:renderURL>
	<a class="btn btn-success mb-3" href="${createTaskURL}"><liferay-ui:message key="task-new" /></a>


	<!-- genero la url de búsqueda con placeholders , uso valores literales para ser directamente sustituidos después con el javascript -->
	<liferay-portlet:renderURL var="filterRenderURL">
		<liferay-portlet:param name="mvcRenderCommandName" value="/" />
		<liferay-portlet:param name="keywords" value="KEYWORDS_PLACEHOLDER" />
		<liferay-portlet:param name="searchType"
			value="SEARCHTYPE_PLACEHOLDER" />
	</liferay-portlet:renderURL>


	<!-- FORMULARIO DE CRITERIOS DE BÚSQUEDA -->
	<!-- recoger con javascript los valores escritos por el usuario. 
	Siempre que uso javascript no se añade automaticamente el namespace del portlet y so puede hacer que el id no sea único. 
	Debo añadirlo manualmente para poder acceder a los elementosbyId -->

	<!-- tengoq ue evitar el evento submit tradicional porque eso recargaría toda al página, utilizo aui que ademas añade namespace automaticamente -->
	<!-- siempre en formularios: consejo de Raquel -->
	<!--  consejo de Raquel : lo usamos para enviar información por parámetros desde fomrualrios a renderurl. esto es lo mismo que no poner nada pero lo hacemos apra saber que pasaremos los params por javascript -->
	<!-- siempre que usamos eventos onX o javascript debemos añadir el namespace a mano para que no haya conflicto -->

	<aui:form name="taskFilterForm" method="post"
		action="javascript:void(0)"
		onSubmit='<%=liferayPortletResponse.getNamespace() + "filterResults()"%>'>
		<aui:row>
			<aui:col md="4">
				<label><liferay-ui:message key="filter-keywords" /></label>
				<input type="text"
					name='<%=renderResponse.getNamespace() + "keywords"%>'
					id='<%=renderResponse.getNamespace() + "keywordsInput"%>' />
				<!-- siempre que use input normal lo tengoq ue poner el namespace-->
			</aui:col>

			<aui:col md="4">
				<aui:select name="searchType" id="searchTypeSelect"
					label="filter-type">
					<aui:option label="search-type-all" value="all"
						selected='<%=searchType.equals("all")%>' />
					<aui:option label="search-type-accurate" value="accurate"
						selected='<%=searchType.equals("accurate")%>' />
					<aui:option label="search-type-elastic" value="elastic"
						selected='<%=searchType.equals("elastic")%>' />
				</aui:select>

			</aui:col>


			<aui:col md="4">
			<!-- value busca en i18n -->
				<aui:button type="submit" value="filter-submit" cssClass="btn-primary" />
			</aui:col>

		</aui:row>
	</aui:form>

	<!-- SEARCH CONTAINER -->
	<liferay-ui:search-container delta="${tasksPerPage}" total="<%=totalTasks%>">
		<liferay-ui:search-container-results results="<%=tasks%>" />
		<liferay-ui:search-container-row className="es.test.model.Task"
			modelVar="task">
			<!-- el atributo name busca tambien en i18n -->
			<liferay-ui:search-container-column-text name="task-title"
				property="title" />


			<liferay-ui:search-container-column-text name="task-status">
			
				<span class="label <%=task.isCompleted() ? "label-success" : "label-warning"%>">
                    <liferay-ui:message
                        key='<%=task.isCompleted() ? "status-completed" : "status-pending"%>' />
                </span>
			
			</liferay-ui:search-container-column-text>


			<liferay-ui:search-container-column-text name="task-actions">
				<div class="d-flex">
					<%-- Borrar --%>
					<!-- el atributo message también busca en i18n -->
					<portlet:actionURL name="/task/delete" var="deleteTaskURL">
						<portlet:param name="taskId"
							value="<%=String.valueOf(task.getTaskId())%>" />
					</portlet:actionURL>
					<liferay-ui:icon message="action-delete"
						url="<%=deleteTaskURL.toString()%>" icon="trash"
						cssClass="icon-trash-big" />

					<%-- Completar --%>
					<c:if test="<%=!task.isCompleted()%>">
						<portlet:actionURL name="/task/complete" var="completeTaskURL">
							<portlet:param name="taskId"
								value="<%=String.valueOf(task.getTaskId())%>" />
						</portlet:actionURL>
						<!-- label en clay también busca en i18n -->
						<aui:form action="${completeTaskURL}" method="post">
							<clay:button displayType="success" type="submit"
								label="action-complete" />
						</aui:form>
					</c:if>
				</div>
			</liferay-ui:search-container-column-text>


		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>

<!-- OPCIÓN PARA EXPORTAR A PDR Y DESCARGAR -->

<portlet:resourceURL 
    var="exportExcelURL" 
    id="<%= TaskWebPortletKeys.EXPORT_EXCEL %>" />

<!-- un resourceURL funciona como una petición GET, y un <a> dispara GET de forma natural. -->
<a href="${exportExcelURL}" target="_blank" class="btn btn-primary">Exportar Excel</a>





















<aui:script>
//liferayprovide: regstra la funcion, hace que cargue cuando liferay este listo.

Liferay.provide(window, '<portlet:namespace />filterResults', function(){

console.log("entra en la funcion")

//consigue la url con als instrucciones para el renderizado
var filterRenderURL = "${filterRenderURL}";


//genero las url para reemplazar --> con al sintaxis de jquery de raquel me da error, no me lo reconoce, solución: usar javascript puro

// 1. Obtener valores con jQuery
    // Usamos val() que sirve tanto para inputs como para selects
    var keywordsValue = $('#<portlet:namespace />keywordsInput').val();
    var searchTypeValue = $('#<portlet:namespace />searchTypeSelect').val();

    console.log("Keywords :", keywordsValue);
    console.log("SearchType :", searchTypeValue);


//reemplazo placeholders por lo escrito por el usuario
filterRenderURL = filterRenderURL.replace("KEYWORDS_PLACEHOLDER", encodeURIComponent(keywordsValue));
filterRenderURL = filterRenderURL.replace("SEARCHTYPE_PLACEHOLDER", encodeURIComponent(searchTypeValue));

//obtengo el formulario
var form = $('#<portlet:namespace />taskFilterForm');

//envío formulario
window.location.href = filterRenderURL;


 });
</aui:script>
