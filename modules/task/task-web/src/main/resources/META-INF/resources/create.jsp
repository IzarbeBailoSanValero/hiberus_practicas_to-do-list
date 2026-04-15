<%@ include file="init.jsp" %>

<portlet:actionURL name="/task/add" var="addTaskActionURL" />

<aui:form action="<%= addTaskActionURL %>" name="Addform" method="post">

    <aui:fieldset label="task-add-new-task">

        <aui:input 
            name="title" 
            label="task-add-title"
            required="true">

            <aui:validator 
                name="required" 
                errorMessage="task-add-title-required"
            />
        </aui:input>

        <aui:input 
            name="description" 
            label="task-add-description"
        />

        <aui:input 
            name="dueDate" 
            label="task-add-due-date"
            type="date" 
        />

    </aui:fieldset>

   <clay:button displayType="success" type="submit">
    <liferay-ui:message key="task-add-save" />
</clay:button>


</aui:form>
