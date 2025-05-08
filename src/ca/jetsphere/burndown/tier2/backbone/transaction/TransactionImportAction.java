package ca.jetsphere.burndown.tier2.backbone.transaction;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.table.DataTableWriter;

import ca.jetsphere.burndown.tier1.backbone.transaction.Transaction;
import ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession;
import ca.jetsphere.burndown.tier1.backbone.transaction.TransactionYard;
import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.tier1.backbone.period.Period;
import ca.jetsphere.core.tier1.backbone.period.PeriodSession;

import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;
import java.util.Iterator;

/**
 *
 */
public class TransactionImportAction extends AbstractDoAction
{
    /**
     *
     */
    public String getKey() { return Transaction.key (); }

    /**
     *
     */

    public ActionForward improt ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    TransactionSession transactions = TransactionSession.getInstance ( store.getRequest() ); Period period = PeriodSession.getSelected ( store.getRequest() );
    
    Iterator it = transactions.iterator();
    
    while ( it.hasNext() )
    {
    Transaction transaction = ( Transaction ) it.next();
    
    if ( TransactionYard.exists ( jdbc, transaction ) ) continue;
    
    if ( !CalendarYard.isBetween ( transaction.getDateAsString(), period.getStartDateAsString(), period.getEndDateAsString() ) ) continue;
    
    transaction.setId ( -1 ); transaction.setPeriodId ( period.getId() ); transaction.save ( jdbc );
    }

    return store.getForward ( "success" );
    }

    /**
     *
     */
    public ActionForward items ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm();
    
    TransactionSession transactions = TransactionSession.getInstance ( store.getRequest() );

    TransactionYard.parseAttachment ( jdbc, transactions, qaf.getFormFile() );
    
    return getForward ( store );
    }
    
    /**
     *
     */
    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();
    
        TransactionSession transactions = TransactionSession.getInstance ( store.getRequest() );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableWriter dataTableWriter = new DataTableWriter ( transactions, Transaction.captions_import(), Transaction.fields_import() );

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
    
    TransactionSession transactions = TransactionSession.getInstance ( store.getRequest() );
    
    transactions.clear();

    return getForward ( store );
    }
}
