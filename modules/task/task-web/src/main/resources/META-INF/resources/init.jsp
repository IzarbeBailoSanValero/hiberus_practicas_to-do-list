<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %>


<%@ page import="es.test.model.Task" %> <!-- por qué tengo que importarlo?? auque esté dentro del módulo Task no se reconoce implicitamente -->
<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import= "com.liferay.portal.kernel.util.Constants" %>

<%@ page import="task.web.constants.TaskWebPortletKeys" %>


<liferay-theme:defineObjects /><!-- esto me da acceso al themedisplay, renderrequest, renderresponse... -->

<portlet:defineObjects />