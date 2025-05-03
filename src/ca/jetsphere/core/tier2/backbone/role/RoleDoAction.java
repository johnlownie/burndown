package ca.jetsphere.core.tier2.backbone.role;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.role.Role;
import ca.jetsphere.core.tier1.backbone.role.RoleSession;
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

public class RoleDoAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return Role.key(); }

    /**
     *
     */

    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        Application application = ApplicationSession.getSelected ( store.getRequest() );

        RoleSession roles = RoleSession.query ( jdbc, store.getRequest(), application.getId(), false );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableWriter dataTableWriter = new DataTableWriter( roles, Role.captions(), Role.fields() );

        dataTableWriter.print ( out, store.getRequest() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception

    { return getForward ( store ); }

}