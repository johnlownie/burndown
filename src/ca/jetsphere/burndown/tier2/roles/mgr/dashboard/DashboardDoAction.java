package ca.jetsphere.burndown.tier2.roles.mgr.dashboard;

import ca.jetsphere.burndown.tier1.backbone.transaction.Transaction;
import ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession;
import ca.jetsphere.burndown.tier1.roles.mgr.dashboard.DashboardYard;
import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.table.DataTableWriter;
import java.io.PrintWriter;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForward;

/**
 *
 */
public class DashboardDoAction extends AbstractDoAction
{
    /**
     *
     */
    public ActionForward export ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        String topic = DockYard.getParameter ( store.getRequest(), "topic" );
        
        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );
        
        DashboardYard.setTransactionsByCategory ( jdbc, store.getRequest(), qaf.getPeriodId(), topic );

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

        String topic = DockYard.getParameter ( store.getRequest(), "topic" );
        
        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );
        
        DashboardYard.setTransactionsByMonth ( jdbc, store.getRequest(), qaf.getPeriodId(), topic );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "success", errors.isEmpty() );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { return null; }
    }

    /**
     *
     */
    public String getKey() { return "dashboard"; }

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

        DataTableWriter dataTableWriter = new DataTableWriter ( transactions, Transaction.captions_dashboard(), Transaction.fields_dashboard() );

        dataTableWriter.print ( out, store.getRequest() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */
    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    TransactionSession transactions = TransactionSession.getInstance ( store.getRequest() );
    
    transactions.clear();
    
    return getForward ( store );
    }

    /**
     *
     */

    public ActionForward report ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();
        
        String categoryData = DashboardYard.getByCategory ( jdbc, qaf.getPeriodId() );
        
        String monthData = DashboardYard.getByMonth ( jdbc, qaf.getPeriodId() );
//        String monthData = "[{ month: 'Brazil', nb: 5, e: 1 }, { month: 'Italy', nb: 4, e: 2 }, { month: 'Germany', nb: 4, e: 4 }, { month: 'Uruguay', nb: 2, e: 9 }, { month: 'Argentina', nb: 2, e: 10 }]";
//        String monthData = "[{ month: 'JAN', amount: -3247}, { month: 'FEB', amount: -6563}, { month: 'MAR', amount: -8267}, { month: 'APR', amount: -12419}, { month: 'MAY', amount: -2575}]";

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "categoryData", categoryData); jsonObject.put ( "monthData", monthData);

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }
}
