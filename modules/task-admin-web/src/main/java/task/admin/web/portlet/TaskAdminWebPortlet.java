package task.admin.web.portlet;

import task.admin.web.constants.TaskAdminWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=my.category",
        "com.liferay.portlet.header-portlet-css=/css/main.css",
        "com.liferay.portlet.instanceable=false",
        "javax.portlet.display-name=TaskAdminWeb",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/admin_view.jsp",
        "javax.portlet.name=" + TaskAdminWebPortletKeys.TASKADMINWEB,
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)
public class TaskAdminWebPortlet extends MVCPortlet {
}
