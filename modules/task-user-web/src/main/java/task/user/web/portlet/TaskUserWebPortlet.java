package task.user.web.portlet;

import task.user.web.constants.TaskUserWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author IzarbeBailoAysa
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=my.category",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=TaskUserWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.name=" + TaskUserWebPortletKeys.TASKUSERWEB,
		  "javax.portlet.init-param.view-template=/user_view.jsp", 
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TaskUserWebPortlet extends MVCPortlet {
}