package ca.jetsphere.burndown.tier2.roles.mgr.dashboard;

import ca.jetsphere.burndown.tier1.backbone.category.Category;
import ca.jetsphere.burndown.tier1.backbone.transaction.Transaction;
import ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession;
import ca.jetsphere.burndown.tier1.backbone.transaction.TransactionYard;
import ca.jetsphere.burndown.tier1.roles.mgr.dashboard.DashboardYard;
import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.Pair;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
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
    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Application application = ApplicationSession.getSelected ( store.getRequest() );

        String category_name = DockYard.getParameter ( store.getRequest(), "category" );
        
        String month = DockYard.getParameter ( store.getRequest(), "month" );
        
        Category category = DashboardYard.getCategory ( jdbc, category_name );
        
        Pair monthDates = DashboardYard.getMonthDates ( jdbc, qaf.getPeriodId(), month ); String start_date = ( String ) monthDates.getKey(); String end_date = ( String ) monthDates.getValue();
        
        String categoryData = DashboardYard.getByCategory ( jdbc, qaf.getPeriodId(), category, start_date, end_date );
        
        String monthData = DashboardYard.getByMonth ( jdbc, application.getId(), qaf.getPeriodId(), category, start_date, end_date );

        String transactionsTitle = DashboardYard.setTransactions ( jdbc, store.getRequest(), qaf.getPeriodId(), category.getName(), start_date, end_date );
        
        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );
        
        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "categoryTitle", Caption.get ( store.getRequest(), "mgr.dashboard.by.category") + ( !DockYard.isWhiteSpace ( month ) ? ": " + month : category.isValid() ? ": " + category.getName() : "" ) );

        jsonObject.put ( "categoryData", categoryData);
        
        jsonObject.put ( "monthTitle", Caption.get ( store.getRequest(), "mgr.dashboard.by.month") + ( category.isValid() ? ": " + category.getName() : "" ) );

        jsonObject.put ( "monthData", monthData);
        
        jsonObject.put ( "transactionsTitle", Caption.get ( store.getRequest(), "mgr.dashboard.by.transactions.debit") + ( !DockYard.isWhiteSpace ( transactionsTitle ) ? ": " + transactionsTitle : "" ) );
        
        jsonObject.put ( "showReset", category.isValid() || !DockYard.isWhiteSpace ( month ) );

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
        
        TransactionSession filtered = qaf.getTypeId() > 0 ? TransactionYard.filter ( transactions, qaf.getTypeId() ) : transactions;
        
        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableWriter dataTableWriter = new DataTableWriter ( filtered, Transaction.captions_dashboard(), Transaction.fields_dashboard() );

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
