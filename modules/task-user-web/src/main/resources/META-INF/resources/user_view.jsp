<!-- USUARIO -->
<%@ include file="user_init.jsp" %>


<!--MENSAJES-->
<liferay-ui:error key="error.task.not-found" message="error.task.not-found" />
<liferay-ui:success key="success.completed" message="success.completed" />
<liferay-ui:error key="error.permission" message="error.permission" />



<%
	//si task es nulo lo inicializao como-- array vacío
	List<Task> tasks = (List<Task>) renderRequest.getAttribute("tasks");
	if (tasks == null)
		tasks = new ArrayList<>();
	//Lo pongo como integer rimero porque si intento convertir a  int un null me pone NPExcep. Sí me deja convertirlo a Integer
	Integer totalTasksObj = (Integer) renderRequest.getAttribute("total");
	int totalTasks = (totalTasksObj != null) ? totalTasksObj : 0;
	
	String keywords = (String) renderRequest.getAttribute("keywords");
	if (keywords == null)
		keywords = "";

	String searchType = (String) renderRequest.getAttribute("searchType");
	if (searchType == null)
		searchType = "all";
	
	
	
%>




<div class="tasks-container">

	<h1>
	<liferay-ui:message key="page.title" />
	</h1>




	<!-- FORMULARIO DE CRITERIOS DE BÚSQUEDA -->
	<!-- genero la url de búsqueda con placeholders , uso valores literales para ser directamente sustituidos después con el javascript -->
	<liferay-portlet:renderURL var="filterRenderURL">
		<liferay-portlet:param name="mvcRenderCommandName" value="/" />
		<liferay-portlet:param name="keywords" value="KEYWORDS_PLACEHOLDER" />
		<liferay-portlet:param name="searchType"
			value="SEARCHTYPE_PLACEHOLDER" />
	</liferay-portlet:renderURL>



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
				<label><liferay-ui:message key="filter.keywords" /></label>
				<input type="text"
					name='<%=renderResponse.getNamespace() + "keywords"%>'
					id='<%=renderResponse.getNamespace() + "keywordsInput"%>' />
				<!-- siempre que use input normal lo tengoq ue poner el namespace-->
			</aui:col>

			<!--necesito este orden de comparación Así  el NPE desaparece.  -->
			<aui:col md="4">
				<aui:select name="searchType" id="searchTypeSelect"
					label="filter.type">
					<aui:option label="search-type-all" value="all"
						selected='<%="all".equals(searchType)%>' />

					<aui:option label="search-type-accurate" value="accurate"
						selected='<%="accurate".equals(searchType)%>' />

					<aui:option label="search-type-elastic" value="elastic"
						selected='<%="elastic".equals(searchType)%>' />
				</aui:select>

			</aui:col>


			<aui:col md="4">
				<!-- value busca en i18n -->
				<aui:button type="submit" value="filter.submit"
					cssClass="btn-primary" />
			</aui:col>

		</aui:row>
	</aui:form>


	<!-- SEARCH CONTAINER -->

	<liferay-ui:search-container delta="5" total="<%=totalTasks%>">
		<liferay-ui:search-container-results results="<%=tasks%>" />
		<liferay-ui:search-container-row className="es.test.model.Task"
			modelVar="task">
			<!-- el atributo name busca tambien en i18n -->
			<liferay-ui:search-container-column-text name="task.title"
				property="title" />


			<liferay-ui:search-container-column-text name="task.status">

				<span
					class="label <%=task.isCompleted() ? "label-success" : "label-warning"%>">
					<liferay-ui:message
						key='<%=task.isCompleted() ? "status.completed" : "status.pending"%>' />
				</span>

			</liferay-ui:search-container-column-text>


			<liferay-ui:search-container-column-text name="task.actions">
				<div class="d-flex">
				
					<%-- Completar --%>
					<c:if test="<%=!task.isCompleted()%>">
						<portlet:actionURL name="/task/complete" var="completeTaskURL">
							<portlet:param name="taskId"
								value="<%=String.valueOf(task.getTaskId())%>" />
						</portlet:actionURL>




						<!-- label en clay también busca en i18n -->
						<aui:form action="${completeTaskURL}" method="post">
							<clay:button displayType="success" type="submit" label="action.complete" />

						</aui:form>
					</c:if>
				</div>
			</liferay-ui:search-container-column-text>


		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>


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

