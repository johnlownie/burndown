package ca.jetsphere.burndown.tier1.roles.mgr.dashboard;

import ca.jetsphere.burndown.tier1.backbone.category.Category;
import ca.jetsphere.burndown.tier1.backbone.category.CategoryYard;
import ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession;
import ca.jetsphere.burndown.tier1.backbone.transaction.TransactionYard;
import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.Pair;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import ca.jetsphere.core.tier1.backbone.period.Period;
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
    static public String getByCategory ( JDBC jdbc, int period_id, int application_id, Category category, String start_date, String end_date )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "[" );
    
    String query = getByCategoryQuery ( jdbc, period_id, application_id, category, start_date, end_date );
    
    Map<Integer, String> categories = QueryYard.getIntStringMap ( jdbc, query, 2, 1 );
    
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
    static private String getByCategoryQuery ( JDBC jdbc, int period_id, int application_id, Category category, String start_date, String end_date )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select p.category_name as 'label', sum(transaction_amount) as 'value'" );
    sb.append ( " from jet_burndown_category p" );
    sb.append ( " inner join jet_burndown_category c on (c.category_id = p.category_id or c.category_parent_id = p.category_id or c.category_lineage like concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/%'))" );
    sb.append ( " inner join jet_burndown_transaction on transaction_category_id = c.category_id" );
    sb.append ( " where transaction_period_id = " + period_id );
    
    if ( !DockYard.isWhiteSpace ( start_date ) ) { sb.append ( " and transaction_date >= " + DockYard.quote ( start_date ) + " and transaction_date <= " + DockYard.quote ( end_date ) ); }
    
    sb.append ( " and p.category_application_id = " + application_id );
    
    if ( category.isValid() ) { sb.append ( " and p.category_parent_id = " + category.getId() ); }
    
    sb.append ( " and p.category_depth = " + ( category.getDepth() + 1 ) );
    sb.append ( " and p.category_included and c.category_included" );
    sb.append ( " group by p.category_name" );
    sb.append ( " order by concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/')" );

    return sb.toString();
    }
    
    /**
     *
     */
    static public String getByIncome ( JDBC jdbc, int application_id, int period_id, Category category, String start_date, String end_date )
    {
    String query = getByIncomeQuery ( application_id, period_id, category );
    
    return QueryYard.query ( jdbc, query, 1 );
    }
    
    /**
     *
     */
    static private String getByIncomeQuery ( int application_id, int period_id, Category category )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select concat('[', group_concat(t.MyObject), ']')" );
    sb.append ( " from (" );
    sb.append ( "  select json_object(" );
    sb.append ( "    'month', upper(substring(date_format(date, '%M'), 1, 3))," );
    sb.append ( "    'income', floor(abs(sum(if(transaction_type = 2, transaction_amount, 0))) / 100)," );
    sb.append ( "    'spending', floor(abs(sum(if(transaction_type = 1, transaction_amount, 0))) / 100)" );
    sb.append ( "  ) as 'MyObject' " );
    sb.append ( "  from jet_base_date" );
    sb.append ( "  inner join jet_burndown_transaction on transaction_date = date" );
    sb.append ( "  inner join jet_burndown_category c on c.category_id = transaction_category_id" );
    sb.append ( "  inner join jet_burndown_category p on (p.category_id = c.category_id or c.category_lineage like concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/%'))" );
    sb.append ( "  where transaction_period_id = " + period_id );

    if ( category.isValid() ) { sb.append ( "  and p.category_parent_id = " + category.getId() ); }

    sb.append ( "  and p.category_application_id = " + application_id );
    sb.append ( "  and c.category_application_id = " + application_id );
    sb.append ( "  and p.category_depth = " + ( category.getDepth() + 1 ) );
    sb.append ( "  group by upper(substring(date_format(date, '%M'), 1, 3))" );
    sb.append ( "  order by date" );
    sb.append ( ") as t" );
    
    return sb.toString();
    }
    
    /**
     *
     */
    static public String getByMonth ( JDBC jdbc, int application_id, int period_id, Category category, String start_date, String end_date )
    {
    String query = getByMonthQuery ( application_id, period_id, category );
    
    return QueryYard.query ( jdbc, query, 1 );
    }
    
    /**
     *
     */
    static private String getByMonthQuery ( int application_id, int period_id, Category category )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select concat('[', group_concat(t.MyObject), ']')" );
    sb.append ( " from (" );
    sb.append ( "  select json_object(" );
    
    if ( period_id == 0 )
    {
    sb.append ( "    'month', concat(upper(substring(date_format(date, '%M'), 1, 3)), ' ', date_format(date, '%Y')), " );
    } else {
    sb.append ( "    'month', upper(substring(date_format(date, '%M'), 1, 3))," );
    }
    
    sb.append ( "    'fixed', floor(abs(sum(if(c.category_fixed, transaction_amount, 0))) / 100)," );
    sb.append ( "    'discretionary', floor(abs(sum(if(!c.category_fixed, transaction_amount, 0))) / 100)" );
    sb.append ( "  ) as 'MyObject' " );
    sb.append ( "  from jet_base_date" );
    sb.append ( "  inner join jet_burndown_transaction on transaction_date = date" );
    sb.append ( "  inner join jet_base_period on period_id = transaction_period_id" );
    sb.append ( "  inner join jet_burndown_category c on c.category_id = transaction_category_id" );
    sb.append ( "  inner join jet_burndown_category p on (p.category_id = c.category_id or c.category_lineage like concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/%'))" );
    
    if ( period_id == 0 )
    {
    sb.append ( "  where date > DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 12 MONTH), '%Y-%m-01')" );
    sb.append ( "  and period_application_id = " + application_id );
    } else {
    sb.append ( "  where transaction_period_id = " + period_id );
    }
    
    sb.append ( "  and transaction_type = 1" );

    if ( category.isValid() ) { sb.append ( "  and p.category_parent_id = " + category.getId() ); }

    sb.append ( "  and p.category_application_id = " + application_id );
    sb.append ( "  and c.category_application_id = " + application_id );
    sb.append ( "  and p.category_depth = " + ( category.getDepth() + 1 ) );
    sb.append ( "  and p.category_included and c.category_included" );
    sb.append ( "  group by upper(substring(date_format(date, '%M'), 1, 3))" );
    sb.append ( "  order by date" );
    sb.append ( " ) as t" );
    
    return sb.toString();
    }
    
    /**
     * 
     */
    static public Category getCategory ( JDBC jdbc, String category_name )
    {
    Category category = new Category(); 
    
    if ( !DockYard.isWhiteSpace ( category_name ) ) 
    {
    category.query ( jdbc, "select * from jet_burndown_category where category_name like " + DockYard.quote( category_name ) );
    
    if ( category.getDepth() > 1 || !CategoryYard.hasChildren ( jdbc, category.getId() ) ) category.clear();
    }
    
    return category;
    }
    
    /**
     * 
     */
    static public Pair getMonthDates ( JDBC jdbc, int period_id, String month )
    {
    if ( DockYard.isWhiteSpace ( month ) ) return new Pair ( "", "" );
    
    Period period = new Period ( jdbc, period_id );
    
    if ( !period.isValid() ) return new Pair ( "", "" );
    
    String now = period.getStartDateAsString();
    
    String start_date = now.substring ( 0, 5 ) + CalendarYard.getMonthAsString ( month ) + "-01";
    
    String end_date = CalendarYard.getLastDayOfMonth ( start_date );
        
    return new Pair ( start_date, end_date );
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
    static public String setTransactions ( JDBC jdbc, HttpServletRequest request, int application_id, int period_id, String category_name, String start_date, String end_date )
    {
    TransactionSession transactions = TransactionSession.getInstance ( request ); String query;
    
    if ( DockYard.isWhiteSpace ( category_name ) && DockYard.isWhiteSpace ( start_date ) )
    
    { query = TransactionYard.getLatestQuery ( period_id ); }
    
    else
        
    { query =  getTransactionsQuery ( application_id, period_id, category_name, start_date, end_date ); }
    
    transactions.query ( jdbc, query );
    
    String title = "";
    
    if ( !DockYard.isWhiteSpace ( category_name ) ) title = category_name;
    
    if ( !DockYard.isWhiteSpace ( start_date ) ) title += !DockYard.isWhiteSpace ( title ) ? "/" + start_date + " to " + end_date : start_date + " to " + end_date;
    
    if ( DockYard.isWhiteSpace ( title ) ) title = Caption.get ( request, "latest" );

    return title;
    }

    /**
     * 
     */
    static private String getTransactionsQuery ( int application_id, int period_id, String category_name, String start_date, String end_date )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select jet_burndown_transaction.*" );
    sb.append ( " from jet_burndown_category p" );
    sb.append ( " inner join jet_burndown_category c on c.category_id = p.category_id or c.category_lineage like concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/%')" );
    sb.append ( " inner join jet_burndown_transaction on transaction_category_id = c.category_id " );
    
    if ( !DockYard.isWhiteSpace ( category_name ) ) sb.append ( " and p.category_name = " + DockYard.quote ( category_name ) );
    
    sb.append ( " and p.category_application_id = " + application_id + " and c.category_application_id = " + application_id );
    sb.append ( " and p.category_included and c.category_included" );
    sb.append ( " and transaction_period_id = " + period_id );
    sb.append ( " and transaction_type = 1" );

    if ( !DockYard.isWhiteSpace ( start_date ) ) { sb.append ( " and transaction_date >= " + DockYard.quote ( start_date ) ); sb.append ( " and transaction_date <= " + DockYard.quote ( end_date ) ); }

    sb.append ( " order by transaction_name, transaction_date" );
    
    return sb.toString();
    }
    
    /**
     * 
     */
    static private void setTransactionsByMonth ( JDBC jdbc, HttpServletRequest request, int period_id, String start_date, String end_date )
    {
    TransactionSession transactions = TransactionSession.getInstance ( request );
    
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select jet_burndown_transaction.* from jet_burndown_category" );
    sb.append ( " inner join jet_burndown_transaction on transaction_category_id = category_id" );
    sb.append ( " where transaction_period_id = " + period_id );
    sb.append ( " and transaction_type = 1" );
    sb.append ( " and transaction_date >= " + DockYard.quote ( start_date ) );
    sb.append ( " and transaction_date <= " + DockYard.quote ( end_date ) );
    sb.append ( " and category_included" );
    sb.append ( " order by transaction_name, transaction_date" );
    
    transactions.query ( jdbc, sb.toString() );
    }
}
