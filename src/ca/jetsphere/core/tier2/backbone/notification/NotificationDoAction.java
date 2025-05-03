package ca.jetsphere.core.tier2.backbone.notification;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.notification.Notification;
import ca.jetsphere.core.tier1.backbone.notification.NotificationSession;
import ca.jetsphere.core.tier1.backbone.period.Period;
import ca.jetsphere.core.tier1.backbone.user.UserSession;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.common.QueryActionForm;
import ca.jetsphere.core.tier2.table.DataTableWriter;
import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;

/**
 *
 */

public class NotificationDoAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return Notification.key(); }

    /**
     *
     */

    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        NotificationSession notifications = NotificationSession.query ( jdbc, store.getRequest(), -1, false );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableWriter dataTableWriter = new DataTableWriter ( notifications, Notification.captions(), Notification.fields() );

        dataTableWriter.print ( out, store.getRequest() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm();

    UserSession.query ( jdbc, store.getRequest(), qaf.getCompanyId(), false );

    return getForward ( store );
    }

}
