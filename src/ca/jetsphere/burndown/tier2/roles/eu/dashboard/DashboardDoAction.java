package ca.jetsphere.burndown.tier2.roles.eu.dashboard;

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
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForward;

/**
 *
 */
public class DashboardDoAction extends AbstractDoAction
{
    private String category_name;
    private String month_name;
    
    /**
     * 
     */
    private void clear()

    { setCategoryName ( "" ); setMonthName ( "" ); }

    /**
     *
     */
    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Application application = ApplicationSession.getSelected ( store.getRequest() );

        setNames ( store.getRequest() );
        
        Category category = DashboardYard.getCategory ( jdbc, getCategoryName() );
        
        Pair monthDates = DashboardYard.getMonthDates ( jdbc, qaf.getPeriodId(), getMonthName() );
        
        String start_date = ( String ) monthDates.getKey(); String end_date = ( String ) monthDates.getValue();
        
        String categoryData = DashboardYard.getByCategory ( jdbc, qaf.getPeriodId(), category, start_date, end_date );
        
        String monthData = DashboardYard.getByMonth ( jdbc, application.getId(), qaf.getPeriodId(), category, start_date, end_date );

        String transactionsTitle = DashboardYard.setTransactions ( jdbc, store.getRequest(), application.getId(), qaf.getPeriodId(), category.getName(), start_date, end_date );
        
        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );
        
        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();
        
        String categoryTitle = !DockYard.isWhiteSpace ( getMonthName() ) ? ": " + getMonthName() : category.isValid() ? ": " + category.getName() : "";
        
        String monthTitle = category.isValid() ? ": " + category.getName() : "";

        jsonObject.put ( "categoryTitle", Caption.get ( store.getRequest(), "mgr.dashboard.by.category") + categoryTitle );
        
        jsonObject.put ( "monthTitle", Caption.get ( store.getRequest(), "mgr.dashboard.by.month") + monthTitle );

        jsonObject.put ( "categoryData", categoryData);

        jsonObject.put ( "monthData", monthData);
        
        jsonObject.put ( "transactionsTitle", Caption.get ( store.getRequest(), "mgr.dashboard.by.transactions.debit") + ( !DockYard.isWhiteSpace ( transactionsTitle ) ? ": " + transactionsTitle : "" ) );
        
        jsonObject.put ( "showCatReset", category.isValid() );

        jsonObject.put ( "showMthReset", !DockYard.isWhiteSpace ( getMonthName() ) );

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
    { 
    clear();
    
    return getForward ( store ); 
    }
    
    /**
     * 
     */
    private void setNames ( HttpServletRequest request )
    {
    boolean reset = DockYard.toBoolean ( DockYard.getParameter ( request, "reset" ) );
    
    String category_name = DockYard.getParameter ( request, "category" ); 
    
    String month_name = DockYard.getParameter ( request, "month" );
    
    if ( reset ) clear();
    
    if ( !DockYard.isWhiteSpace ( category_name ) ) { setCategoryName ( category_name ); }
    
    if ( !DockYard.isWhiteSpace ( month_name    ) ) { setMonthName    ( month_name    ); }
    }
    
    /**
     * 
     */
    private String getCategoryName() { return this.category_name; }
    private String getMonthName   () { return this.month_name   ; }
    
    private void setCategoryName ( String category_name ) { this.category_name = category_name ;}
    private void setMonthName    ( String month_name    ) { this.month_name    = month_name    ;}
}
