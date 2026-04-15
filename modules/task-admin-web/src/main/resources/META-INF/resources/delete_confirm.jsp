<%@ include file="/admin_init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<liferay-ui:error key="error.permission"
	message="error.permission" />
<liferay-ui:error key="error.task.not-found"message="error.task.not-found" />

<%
	Task task = (Task) renderRequest.getAttribute("task");
pageContext.setAttribute("task", task); //para poder usar el formateador de fecha

%>


<!-- url de cancelar y aceptar -->
<portlet:actionURL name="<%=TaskAdminWebPortletKeys.DELETE%>"
	var="deleteURL" />

<portlet:renderURL var="cancelURL">
	<portlet:param name="mvcRenderCommandName"
		value="<%=TaskAdminWebPortletKeys.DEFAULT%>" />
</portlet:renderURL>



<!-- Contenido -->
<div style="max-width: 600px; margin: 20px auto; padding: 20px; border: 1px solid black;">
	<h2><liferay-ui:message key="task.details" /></h2>

	<h5 class="task-title"><strong>
		<%=task.getTitle()%></strong>
	</h5>

	<c:if
		test="<%=task.getDescription() != null && !task.getDescription().isEmpty()%>">
		<p>
			<liferay-ui:message key="task.description" />
			:
			<%=task.getDescription()%>
		<p>
	</c:if>

	<c:if test="<%=task.getDueDate() != null%>">
		<p>
		  <strong><liferay-ui:message key="task.due-date" />:</strong>
        <fmt:formatDate value="${task.dueDate}" pattern="dd/MM/yyyy" />
		<p>
	</c:if>

<p>
    <liferay-ui:message key="task.status" />:
    <liferay-ui:message key="<%= task.isCompleted() ? "task.status.completed" : "task.status.pending" %>" />
</p>



</div>

 <hr style="margin: 20px 0;" />




<!-- formulario -->
<aui:form action="<%=deleteURL%>" method="post">

	<!-- envío de Id -->
	<aui:input type="hidden" name="taskId" value="<%=task.getTaskId()%>" />

	<h2><liferay-ui:message key="confirm.delete.question" /></h2>
	<div class="btn-group">
		<aui:button type="submit" value="action.delete" cssClass="btn btn-danger" />
		<aui:button type="button" value="task.cancel" cssClass="btn btn-secondary" href="<%= cancelURL %>" />
	</div>
</aui:form>