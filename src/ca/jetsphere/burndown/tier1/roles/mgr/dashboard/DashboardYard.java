package ca.jetsphere.burndown.tier1.roles.mgr.dashboard;

import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.Pair;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import java.util.List;
import java.util.Map;

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
    
    Map<Integer, String> categories = QueryYard.getIntStringMap ( jdbc, getByCategoryQuery ( period_id ), 2, 1 );
    
    for ( Map.Entry<Integer, String> entry : categories.entrySet() )
    {
    int value = entry.getKey(); 
    
    sb.append ( firstCall ? "" : ", " );
    
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
    
    String end_date = CalendarYard.now(); String start_date = CalendarYard.getFirstDayOfYear ( end_date );
    
    List<String> monthNames = CalendarYard.getMonthsBetween ( start_date, end_date, "yyyy-MM-dd", "MMM" );

    Pair[] monthDates = CalendarYard.getMonthDates ( start_date, monthNames.size() );

    String query = getByMonthQuery ( monthDates, period_id, 1, start_date, end_date );

    return sb.toString();
    }
    
    /**
     * 
     */
    static private String getByMonthQuery ( Pair[] monthDates, int period_id, int transaction_type, String start_date, String end_date )
    {
    StringBuilder sb = new StringBuilder();

    sb.append ( "select concat(p.category_depth, ':', p.category_name) as 'Category'" );

    sb.append ( getByMonthsQuery ( monthDates ) );
    
    sb.append ( " from jet_burndown_category p" );
    sb.append ( " inner join jet_burndown_category c on (c.category_id = p.category_id or c.category_parent_id = p.category_id or c.category_lineage like concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/%'))" );
    sb.append ( " inner join jet_burndown_transaction on transaction_category_id = c.category_id" );
    sb.append ( " where transaction_period_id = " + period_id );
    sb.append ( " and transaction_date >= " + DockYard.quote ( start_date ) + " and transaction_date <= " + DockYard.quote ( end_date ) );
    sb.append ( transaction_type > 0 ? " and transaction_type = " + transaction_type : "" );
    sb.append ( " and p.category_depth = 1" );
    sb.append ( " and p.category_included and c.category_included" );
    sb.append ( " group by p.category_name" );
    sb.append ( " order by concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/')" );

    return sb.toString();
    }
    /**
     *
     */
    static public String getByMonthsQuery ( Pair[] monthDates )
    {
    StringBuilder sb = new StringBuilder();

    for ( Pair monthDate: monthDates )
    {
    List<String> monthNames = CalendarYard.getMonthsBetween ( ( String ) monthDate.getKey(), ( String ) monthDate.getValue(), "yyyy-MM-dd", "MMM" );
    
    sb.append ( ", sum(if(transaction_date >= " + DockYard.quote ( ( String ) monthDate.getKey() ) + " and transaction_date <= " + DockYard.quote ( ( String ) monthDate.getValue() ) );
    sb.append ( ", transaction_amount" );
    sb.append ( ", 0)) as " + DockYard.quote ( monthNames.size() > 0 ? monthNames.get ( 0 ) : "???" ) );
    }

    return sb.toString();
    }
}
