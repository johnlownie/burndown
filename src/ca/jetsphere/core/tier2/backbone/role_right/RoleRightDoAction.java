package ca.jetsphere.core.tier2.backbone.role_right;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.role.RoleSession;
import ca.jetsphere.core.tier1.backbone.role_right.RoleRight;
import ca.jetsphere.core.tier1.backbone.role_right.RoleRightSession;
import ca.jetsphere.core.tier1.tree.TreeYard;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.common.QueryActionForm;
import ca.jetsphere.core.tier2.tree.DataTableTreeWriter;

import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;

/**
 *
 */

public class RoleRightDoAction extends AbstractDoAction
{
    /**
     *
     */

    public ActionForward close ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        boolean expand = DockYard.toBoolean ( store.getRequest(), "expand" );

        RoleRightSession roleRights = RoleRightSession.getInstance ( store.getRequest() );

        if ( expand ) TreeYard.expand ( qaf.getOpenSet(), roleRights.getRoot() ); else TreeYard.collapse ( qaf.getOpenSet(), roleRights.getRoot() );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "success", errors.isEmpty() );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward export ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        int id = DockYard.toInteger ( store.getRequest(), "nodeId" );

        RoleRightSession roleRights = RoleRightSession.getInstance ( store.getRequest() );

        RoleRight roleRight = ( RoleRight ) roleRights.get ( id );

        qaf.getOpenSet().toggle ( roleRight.getId() );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "success", errors.isEmpty() );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        RoleRightSession roleRights = RoleRightSession.query ( jdbc, store.getRequest(), qaf.getRoleId(), false );

        TreeYard.collapse ( qaf.getOpenSet(), roleRights.getRoot() );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "success", errors.isEmpty() );

//        JsonWriter jsonWriter = new JsonWriter ( roleRights );

//        String s = jsonWriter.get ( store.getRequest(), id );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { return null; }
    }

    /**
     *
     */

    public String getKey() { return RoleRight.key(); }

    /**
     *
     */

    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        RoleRightSession roleRights = RoleRightSession.getInstance ( store.getRequest() );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableTreeWriter tableWriter = new DataTableTreeWriter ( roleRights, RoleRight.captions(), RoleRight.fields() );

        tableWriter.print ( out, store.getRequest(), qaf.getOpenSet() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Application application = ApplicationSession.getSelected ( store.getRequest() );

    qaf.getOpenSet().clear();

    if ( application != null && application.isValid() ) qaf.setApplicationId ( application.getId() );

    RoleSession.query ( jdbc, store.getRequest(), qaf.getApplicationId(), false );

    return getForward ( store );
    }

}
