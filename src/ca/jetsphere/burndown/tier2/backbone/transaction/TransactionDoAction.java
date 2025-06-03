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
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.application.ApplicationYard;
import ca.jetsphere.core.tier1.backbone.period.Period;
import ca.jetsphere.core.tier1.backbone.period.PeriodSession;

import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;
import net.sf.json.JSONObject;

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
    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {
    
        QueryActionForm qaf = ( QueryActionForm ) store.getForm(); JSONObject jsonObject = new JSONObject();
    
        Application application = ApplicationSession.getSelected ( store.getRequest() ); Period selected = PeriodSession.getSelected ( store.getRequest() );
        
        if ( !ApplicationYard.hasPeriod ( jdbc, application.getId(), qaf.getPeriodId() ) ) 
        
        { qaf.setPeriodId ( selected.getId() ); qaf.setStartDateAsString ( selected.getStartDateAsString() ); qaf.setEndDateAsString ( selected.getEndDateAsString() ); }
        
        if ( qaf.getPeriodId() != selected.getId() )
        {
        Period period = new Period ( jdbc, qaf.getPeriodId() ); 
        
        qaf.setStartDateAsString ( period.getStartDateAsString() ); qaf.setEndDateAsString ( period.getEndDateAsString() );
        
        PeriodSession.setSelected ( store.getRequest(), period );

        jsonObject.put ( "startDate", period.getStartDateAsString() ); jsonObject.put ( "endDate", period.getEndDateAsString() );
        }

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        out.write ( jsonObject.toString() );
        
    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

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
    
    Period period = PeriodSession.getSelected ( store.getRequest() );

    int period_id = qaf.getPeriodId () <= 0 ? period.getId() : qaf.getPeriodId ();

    qaf.setPeriodId ( period_id );

    qaf.setStartDateAsString ( period.getStartDateAsString() ); qaf.setEndDateAsString ( period.getEndDateAsString() );

    AccountSession.query ( jdbc, store.getRequest(), application.getId(), true );
    
    CategorySession.query ( jdbc, store.getRequest(), application.getId(), true );

    TransactionSession.query ( jdbc, store.getRequest(), qaf.getPeriodId(), false );

    return getForward ( store );
    }
}
