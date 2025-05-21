package ca.jetsphere.core.tier2.backbone.user;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.role.RoleYard;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserSession;
import ca.jetsphere.core.tier1.backbone.user.UserYard;
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

public class UserDoAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return User.key(); }

    /**
     *
     */

    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm(); qaf.setDtCount ( UserYard.getCount ( jdbc ) ); qaf.setDts ( store.getRequest() );

        UserSession users = UserYard.get ( jdbc, store.getRequest(), qaf.getDtStart(), qaf.getDtLength(), qaf.getDtOrderColumn(), qaf.getDtOrderDir(), qaf.getDtSearch() );

        qaf.setDtFiltered ( UserYard.getCount ( jdbc, qaf.getDtSearch() ) );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableWriter dataTableWriter = new DataTableWriter ( users, User.captions(), User.fields() );

        dataTableWriter.print ( out, store.getRequest(), qaf.getDtDraw(), qaf.getDtCount(), qaf.getDtFiltered(), qaf.getDtStart(), Math.min ( qaf.getDtLength(), qaf.getDtFiltered() - qaf.getDtStart() ) );

    } catch ( Exception e ) { Common.trace( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Company company = CompanySession.getSelected ( store.getRequest () );

    RoleYard.getCompanyRoles ( jdbc, store.getRequest(), true );
    
    ApplicationSession.query ( jdbc, store.getRequest(), company.getId(), true );

    return getForward ( store );
    }

    /**
     *
     */

    public ActionForward role ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm();

    if ( !DockYard.isWhiteSpace ( qaf.getRole() ) ) return store.getForward ( "role", "?role=true&id=" + qaf.getRole() );

    return  getForward ( store );
    }

}
