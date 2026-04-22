<%@ include file="admin_init.jsp"%>

<!-- URL de cancelar (la defin antes del form porque sino no me redirige bien) -->
<portlet:renderURL var="cancelURL">
	<portlet:param name="mvcRenderCommandName"
		value="<%=TaskAdminWebPortletKeys.DEFAULT%>" />
</portlet:renderURL>

<portlet:actionURL name="<%=TaskAdminWebPortletKeys.SAVE_TASK%>"
	var="saveTaskActionURL" />

<div class="container-fluid container-form-view">
	<div class="sheet sheet-lg">

		<aui:form action="<%=saveTaskActionURL%>" name="taskForm"
			method="post">
			<aui:fieldset label="task.form-title">
				<%
					Task task = (Task) request.getAttribute("task");
							List<User> normalUsers = (List<User>) request.getAttribute("normalUsers");

							String dueDateValue = "";
							if (task != null && task.getDueDate() != null) {
								java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
								dueDateValue = sdf.format(task.getDueDate());
							}
				%>

				<aui:input type="hidden" name="taskId"
					value="<%=(task != null) ? task.getTaskId() : 0%>" />

				<aui:input name="title" label="task.title" required="true"
					value="<%=(task != null) ? task.getTitle() : ""%>">
					<aui:validator name="required" errorMessage="error.title.required" />
				</aui:input>

				<aui:input name="description" label="task.description"
					value="<%=(task != null) ? task.getDescription() : ""%>">
				</aui:input>

				<aui:input name="dueDate" label="task.due-date" type="date"
					value="<%=dueDateValue%>">
				</aui:input>

				<aui:select name="assignedUserId" label="user.assignation">
					<c:forEach var="user" items="${normalUsers}">
						<aui:option value="${user.userId}"
							selected="${task != null && task.userId == user.userId}">${user.fullName}</aui:option>


					</c:forEach>
				</aui:select>
			</aui:fieldset>

		
			<aui:button-row>
				<aui:button type="submit" value="task.save"/>
				<aui:button href="${cancelURL}" value="task.cancel"/>
			</aui:button-row>
			
		</aui:form>

		

	</div>
</div>


<script>
	console.log("hola")
	console.log("${task.getTitle()}")
	
</script>

