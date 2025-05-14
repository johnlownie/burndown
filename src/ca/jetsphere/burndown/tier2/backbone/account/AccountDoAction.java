package ca.jetsphere.burndown.tier2.backbone.account;

import ca.jetsphere.burndown.tier1.backbone.account.Account;
import ca.jetsphere.burndown.tier1.backbone.account.AccountSession;
import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.table.DataTableWriter;

import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;

/**
 *
 */
public class AccountDoAction extends AbstractDoAction
{
    /**
     *
     */
    public String getKey() { return Account.key (); }

    /**
     *
     */
    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Company company = CompanySession.getSelected ( store.getRequest () );

        AccountSession accounts = AccountSession.query ( jdbc, store.getRequest(), company.getId(), false );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableWriter dataTableWriter = new DataTableWriter ( accounts, Account.captions(), Account.fields() );

        dataTableWriter.print ( out, store.getRequest() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */
    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Company company = CompanySession.getSelected ( store.getRequest () );

    AccountSession accounts = AccountSession.query ( jdbc, store.getRequest(), company.getId(), false );

    return getForward ( store );
    }
}
