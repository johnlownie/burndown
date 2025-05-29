package ca.jetsphere.burndown.tier2.backbone.transaction;

import ca.jetsphere.burndown.tier1.backbone.account.AccountSession;
import ca.jetsphere.burndown.tier1.backbone.category.CategorySession;
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
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.period.PeriodSession;

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

        TransactionSession transactions = TransactionYard.set ( jdbc, store.getRequest(), qaf.getPeriodId(), qaf.getAccountId(), qaf.getCategoryId(), qaf.getTypeId(), qaf.getToggle(), qaf.getStartDateAsString(), qaf.getEndDateAsString() );

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
    QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Application application = ApplicationSession.getSelected ( store.getRequest() );

    int period_id = qaf.getPeriodId () <= 0 ? PeriodSession.getSelected ( store.getRequest () ).getId() : qaf.getPeriodId ();

    qaf.setPeriodId ( period_id );

    String today = CalendarYard.now ( "yyyy-MM-dd" );

    qaf.setStartDateAsString ( CalendarYard.getFirstDayOfYear ( today ) ); qaf.setEndDateAsString ( CalendarYard.getLastDayOfYear ( today ) );

    AccountSession.query ( jdbc, store.getRequest(), application.getId(), true );
    
    CategorySession.query ( jdbc, store.getRequest(), application.getId(), true );

    TransactionSession.query ( jdbc, store.getRequest(), qaf.getPeriodId(), false );

    return getForward ( store );
    }
}
