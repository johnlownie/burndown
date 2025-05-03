package ca.jetsphere.core.tier2.backbone.role_right;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.action.ActionSession;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.role_right.RoleRight;
import ca.jetsphere.core.tier1.backbone.role_right.RoleRightSession;
import ca.jetsphere.core.tier1.backbone.role_right.RoleRightYard;
import ca.jetsphere.core.tier1.tree.TreeYard;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 *
 */

public class RoleRightEditAction extends AbstractEditAction
{
    /**
     *
     */

    public ActionForward copy ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        String uuid = DockYard.getParameter ( store.getRequest(), "csrf" );

        if ( DockYard.isWhiteSpace ( uuid ) ) errors.add ( "error.role.right.copy" );

        RoleRightSession roleRights = RoleRightSession.getInstance ( store.getRequest() );

        RoleRight roleRight = ( RoleRight ) roleRights.getBoltByUuid ( uuid );

        if ( roleRight == null || !roleRight.isValid() ) errors.add ( "error.role.right.copy" );

        RoleRight copyTree = ( RoleRight ) TreeYard.copy ( roleRight );

        DockYard.setAttribute ( store.getRequest(), "[copyTree]", errors.isEmpty() ? copyTree : null );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "success", errors.isEmpty() );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward delete ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        String uuid = DockYard.getParameter ( store.getRequest(), "csrf" );

        if ( DockYard.isWhiteSpace ( uuid ) ) errors.add ( "error.role.right.cut" );

        RoleRightSession roleRights = RoleRightSession.getInstance ( store.getRequest() ); roleRights.clearSelected ( store.getRequest() );

        RoleRight child = ( RoleRight ) roleRights.getBoltByUuid ( uuid );

        if ( child == null || !child.isValid() ) errors.add ( "error.role.right.cut" );

        RoleRight parent = ( RoleRight ) roleRights.get ( child.getParentId() );

        if ( parent == null || !parent.isValid() ) errors.add ( "error.role.right.cut" );

        if ( errors.isEmpty() ) { TreeYard.cut ( jdbc, parent, child ); roleRights.remove ( child ); }

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

    protected void finito ( JDBC jdbc, HttpServletRequest request, Bolt bolt, boolean isUpdate, Errors errors ) throws Exception
    {
    if ( isUpdate ) return;

    RoleRight roleRight = ( RoleRight ) bolt; roleRight.foreign ( jdbc );

    RoleRightSession roleRights = RoleRightSession.getInstance ( request );

    roleRights.add ( roleRight ); roleRights.treeify();
    }

    /**
     *
     */

    public String getKey() { return RoleRight.key(); }

    /**
     *
     */

    public ActionForward insert ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        RoleRightSession roleRights = RoleRightSession.getInstance ( store.getRequest() ); roleRights.clearSelected ( store.getRequest() );

        String uuid = DockYard.getParameter ( store.getRequest(), "csrf" );

        boolean is_before = DockYard.toBoolean ( store.getRequest(), "before" ); boolean is_child = DockYard.toBoolean ( store.getRequest(), "child" );

        if ( DockYard.isWhiteSpace ( uuid ) ) return null;

        RoleRight selected = ( RoleRight ) roleRights.getBoltByUuid ( uuid ); RoleRight form = ( RoleRight ) roleRights.getSelected();

        RoleRight sibling = new RoleRight(); sibling.copy ( selected );

        if ( sibling != null && sibling.isValid() ) sibling.getPayload ( jdbc );

        form.clear(); form.setParentId ( is_child ? sibling.getId() : sibling.getParentId() ); form.setInsertType ( is_before ? 1 : 2 );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { return query ( jdbc, store, errors ); }
    }

    /**
     *
     */

    public ActionForward paste ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        String uuid = DockYard.getParameter ( store.getRequest(), "csrf" );

        if ( DockYard.isWhiteSpace ( uuid ) ) errors.add ( "error.role.right.paste" );

        RoleRightSession roleRights = RoleRightSession.getInstance ( store.getRequest() );

        RoleRight parent = ( RoleRight ) roleRights.getBoltByUuid ( uuid );

        if ( parent == null || !parent.isValid() )  errors.add ( "error.role.right.paste" );

        RoleRight child = ( RoleRight ) DockYard.getAttribute ( store.getRequest(), "[copyTree]" );

        if ( child == null || !child.isValid() )  errors.add ( "error.role.right.paste" );

        if ( errors.isEmpty() ) TreeYard.paste ( jdbc, roleRights, parent, child );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "success", errors.isEmpty() );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    Company company = CompanySession.getSelected ( store.getRequest() );

    ActionSession.query ( jdbc, store.getRequest(), company.getId(), true );

    return store.getForward ( "failure" );
    }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception
    {
    RoleRight right = ( RoleRight ) bolt; RoleRight parent = new RoleRight ( jdbc, right.getParentId() );

    if ( parent == null || ! parent.isValid() ) return;

    right.setRoleId ( parent.getRoleId() ); right.setParentUuid ( parent.getUuid() );

    right.setDepth ( parent.getDepth() + 1 ); right.setLineage ( parent.getLineage() + parent.getId() + "/" );

    if ( right.getOrdinal() < 0 )

        right.setOrdinal ( RoleRightYard.getCount ( jdbc, parent.getRoleId(), parent.getId() ) );
    }

}
