package ca.jetsphere.burndown.tier2.backbone.transaction;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.table.DataTableWriter;

import ca.jetsphere.burndown.tier1.backbone.transaction.Transaction;
import ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession;
import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;

import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;

/**
 *
 */
public class TransactionDoAction extends AbstractDoAction
{
    /**
     *
     */
    public String getKey() { return Transaction.key (); }

    /**
     *
     */
    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        TransactionSession transactions = TransactionSession.query ( jdbc, store.getRequest(), qaf.getPeriodId(), false );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableWriter dataTableWriter = new DataTableWriter ( transactions, Transaction.captions(), Transaction.fields() );

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

    TransactionSession.query ( jdbc, store.getRequest(), qaf.getPeriodId(), false );

    return getForward ( store );
    }
}
