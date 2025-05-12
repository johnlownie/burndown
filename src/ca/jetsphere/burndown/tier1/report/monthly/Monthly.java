package ca.jetsphere.burndown.tier1.report.monthly;

import ca.jetsphere.burndown.tier1.backbone.category.CategoryYard;
import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.bolt.rs.ResultSetBolt;
import ca.jetsphere.core.bolt.rs.ResultSetBoltMap;
import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.Pair;
import ca.jetsphere.core.jdbc.JDBC;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Monthly
{
    /**
     *
     */
    public Pair[] getMonthDates ( String start_date, int num_of_months )
    {
    Pair[] months = new Pair [ num_of_months ];

    QueryActionForm qaf = new QueryActionForm();

    for ( int i = 0; i < num_of_months; i++ )
    {
    String month_start = CalendarYard.getFirstDayOfMonth ( start_date ); String month_end = CalendarYard.getLastDayOfMonth ( start_date );

    months [ i ] = new Pair ( month_start, month_end );

    start_date = CalendarYard.getNextMonth ( start_date );
    }

    return months;
    }

    /**
     *
     */
    public String getMonthsQuery ( Pair[] monthDates )
    {
    StringBuilder sb = new StringBuilder();

    for ( Pair monthDate: monthDates )
    {
    List<String> monthNames = CalendarYard.getMonthsBetween ( ( String ) monthDate.getKey(), ( String ) monthDate.getValue(), "yyyy-MM-dd", "MMM" );
    
    sb.append ( ", sum(if(transaction_date >= " + DockYard.quote ( ( String ) monthDate.getKey() ) + " and transaction_date <= " + DockYard.quote ( ( String ) monthDate.getValue() ) );
    sb.append ( ", transaction_amount" );
    sb.append ( ", 0)) as " + DockYard.quote ( monthNames.size() > 0 ? monthNames.get ( 0 ) : "???" ) );
    }

    sb.append ( ", sum(transaction_amount) as 'Total'" );

    return sb.toString();
    }
    
    /**
     * 
     */
    private String getQuery ( int category_id, String category_name, Pair[] monthDates, int period_id, int transaction_type, String start_date, String end_date )
    {
    StringBuilder sb = new StringBuilder();

    sb.append ( "select " + DockYard.quote ( category_name ) + " as 'Category'" );

    sb.append ( getMonthsQuery ( monthDates ) );
    
    sb.append ( " from jet_burndown_category " );
    sb.append ( " inner join (" );
    sb.append ( " select concat(category_lineage, lpad(category_ordinal, 2, 0), '/') as 'ParentLineage' from jet_burndown_category where category_id = " + category_id );
    sb.append ( " ) as table1 on (category_id = " + category_id + " or category_lineage like table1.ParentLineage)" );
    sb.append ( " inner join jet_burndown_transaction on transaction_category_id = category_id" );
    sb.append ( " where transaction_period_id = " + period_id );
    sb.append ( " and transaction_date >= " + DockYard.quote ( start_date ) + " and transaction_date <= " + DockYard.quote ( end_date ) );
    sb.append ( transaction_type > 0 ? " and transaction_type = " + transaction_type : "" );
    sb.append ( " and category_included" );
    sb.append ( " group by " + DockYard.quote ( category_name ) );

    return sb.toString();
    }
    
    /**
     * 
     */
    private void getReport ( JDBC jdbc, String query, ResultSetBoltMap rsbm )
    {
    ResultSetBoltMap categories = new ResultSetBoltMap ( jdbc, query );
    
    Iterator it = categories.iterator();
    
    while ( it.hasNext() )
    
    {
        ResultSetBolt rsb = ( ResultSetBolt ) it.next(); 
    
    rsbm.add ( rsbm.size(), rsb ); 
    }

    rsbm.setCaptions ( categories.getCaptions() );
    }
    
    /**
     * 
     */
    private ResultSetBoltMap getReport ( JDBC jdbc, int company_id, int period_id, int transaction_type, String start_date, String end_date )
    {
    ResultSetBoltMap rsbm = new ResultSetBoltMap();
    
    List<String> monthNames = CalendarYard.getMonthsBetween ( start_date, end_date, "yyyy-MM-dd", "MMM" );

    Pair[] monthDates = getMonthDates ( start_date, monthNames.size() );
    
    LinkedHashMap<Integer, String> categories = ( LinkedHashMap ) CategoryYard.getTreeNames ( jdbc, company_id );
    
    categories.forEach((category_id, category_name) -> {
        
        String query = getQuery ( category_id, category_name, monthDates, period_id, transaction_type, start_date, end_date );
     
        getReport ( jdbc, query, rsbm );

    });

    return rsbm;
    }

    /**
     *
     */
    public ResultSetBoltMap getReport ( JDBC jdbc, QueryActionForm qaf ) throws Exception

    { return getReport ( jdbc, qaf.getCompanyId(), qaf.getPeriodId(), qaf.getTypeId(), qaf.getStartDateAsString(), qaf.getEndDateAsString() ); }
}
