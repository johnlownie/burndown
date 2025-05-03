package ca.jetsphere.core.tier2.backbone.notification;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.notification.Notification;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class NotificationEditAction extends AbstractEditAction
{
    /**
     *
     */

    public String getKey() { return Notification.key(); }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception {}

}
