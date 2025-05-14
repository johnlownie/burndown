package ca.jetsphere.burndown.tier1.roles.mgr.dashboard;

import ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession;
import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public class DashboardYard
{
    /**
     *
     */
    static public String getByCategory ( JDBC jdbc, int period_id )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "[" );
    
    Map<Integer, String> categories = QueryYard.getIntStringMap ( jdbc, getByCategoryQuery ( period_id ), 2, 1 );
    
    int total = getTotal ( categories ); boolean firstCall = true;
    
    for ( Map.Entry<Integer, String> entry : categories.entrySet() )
    {
    int value = entry.getKey(); 
    
    sb.append ( firstCall ? "" : ", " );
    
    sb.append ( "{label: " + DockYard.quote ( entry.getValue() ) + ", value: " + Math.floor ( ( ( double ) value / total * 100 ) ) + "}" );
    
    firstCall = false;
    }
    
    sb.append ( "]" );
    
    return sb.toString();
    }
    
    /**
     *
     */
    static public String getByMonth ( JDBC jdbc, int period_id )
    {
    StringBuilder sb = new StringBuilder(); boolean firstCall = true;
    
    sb.append ( "[" );
    
    Map<Integer, String> categories = QueryYard.getIntStringMap ( jdbc, getByMonthQuery ( period_id ), 2, 1 );
    
    for ( Map.Entry<Integer, String> entry : categories.entrySet() )
    {
    sb.append ( firstCall ? "" : ", " );
    
    sb.append ( "{ month: " + DockYard.quote ( entry.getValue() ) + ", amount: " + Math.abs ( Math.floor ( entry.getKey() / 100 ) ) + "}" );
    
    firstCall = false;
    }
    
    sb.append ( "]" );
    
    return sb.toString();
    }
    
    /**
     * 
     */
    static private int getTotal ( Map<Integer, String> map ) 
    {
    int total = 0;
    
    for ( Integer value : map.keySet() )
        
    { total += value; }
    
    return total;
    }
    
    /**
     *
     */
    static private String getByCategoryQuery ( int period_id )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select p.category_name as 'label', sum(transaction_amount) as 'value'" );
    sb.append ( " from jet_burndown_category p" );
    sb.append ( " inner join jet_burndown_category c on (c.category_id = p.category_id or c.category_parent_id = p.category_id or c.category_lineage like concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/%'))" );
    sb.append ( " inner join jet_burndown_transaction on transaction_category_id = c.category_id" );
    sb.append ( " where transaction_period_id = " + period_id );
    sb.append ( " and p.category_depth = 1" );
    sb.append ( " and p.category_included and c.category_included" );
    sb.append ( " group by p.category_name" );
    sb.append ( " order by concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/')" );

    return sb.toString();
    }
    
    /**
     *
     */
    static private String getByMonthQuery ( int period_id )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select upper(substring(date_format(date, '%M'), 1, 3)) as 'Month'" );
//    sb.append ( " , p.category_name as 'Category'" );
    sb.append ( " , sum(transaction_amount) as 'Amount'" );
    sb.append ( " from jet_base_date" );
    sb.append ( " inner join jet_burndown_transaction on transaction_date = date" );
    sb.append ( " inner join jet_burndown_category c on c.category_id = transaction_category_id" );
    sb.append ( " inner join jet_burndown_category p on (p.category_id = c.category_id or c.category_lineage like concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/%'))" );
    sb.append ( " where transaction_period_id = " + period_id );
    sb.append ( " and p.category_depth = 1" );
    sb.append ( " and p.category_included and c.category_included" );
    sb.append ( " group by upper(substring(date_format(date, '%M'), 1, 3))" );
//    sb.append ( " group by upper(substring(date_format(date, '%M'), 1, 3)), p.category_name" );
    sb.append ( " order by date" );
    
    return sb.toString();
    }
    
    /**
     * 
     */
    static public void setTransactionSession ( JDBC jdbc, HttpServletRequest request, int period_id, String month )
    {
    TransactionSession transactions = TransactionSession.getInstance ( request );
    
    String now = CalendarYard.now(); String d = now.substring ( 0, 5 ) + month + "-01";
    
    String start_date = CalendarYard.formatDate ( d, "yyyy-MMM-dd", "yyyy-MM-dd" ); String end_date = CalendarYard.getLastDayOfMonth ( start_date );
    
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select jet_burndown_transaction.* from jet_burndown_category" );
    sb.append ( " inner join jet_burndown_transaction on transaction_category_id = category_id" );
    sb.append ( " where transaction_period_id = " + period_id );
    sb.append ( " and transaction_date >= " + DockYard.quote ( start_date ) );
    sb.append ( " and transaction_date <= " + DockYard.quote ( end_date ) );
    sb.append ( " and category_included" );
    
    transactions.query ( jdbc, sb.toString() );
    }
}
