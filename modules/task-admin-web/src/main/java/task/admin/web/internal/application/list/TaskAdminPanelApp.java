//HE PROBADO MUCHAS SINTAXIS, ASÍ EN TASKADMINPANEL Y TASKADMINWEBPORTLET ES LA UNICA QUE NO ME DA ROBLEMAS DE LIFERAY7.4 Y LA MANERA EN LA QUE COGE LAS COSAS
package task.admin.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.PortletLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import task.admin.web.constants.TaskAdminWebPortletKeys;

@Component(
    immediate = true,
    property = {
        "panel.app.order:Integer=200",
        "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
    },
    service = PanelApp.class
)
public class TaskAdminPanelApp extends BasePanelApp {

    @Override
    public String getPortletId() {
        return TaskAdminWebPortletKeys.TASKADMINWEB;
    }

    @Override
    public Portlet getPortlet() {
        return _portletLocalService.getPortletById(getPortletId());
    }

    @Reference
    private PortletLocalService _portletLocalService;
}
