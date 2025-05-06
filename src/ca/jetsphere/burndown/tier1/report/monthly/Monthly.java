package ca.jetsphere.burndown.tier1.report.monthly;

import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.bolt.rs.ResultSetBoltMap;
import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.Pair;
import ca.jetsphere.core.jdbc.JDBC;
import java.util.List;

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
    public String getQuery ( int period_id, int category_id, int transaction_type, String start_date, String end_date )
    {
    StringBuilder sb = new StringBuilder();
    
    List<String> monthNames = CalendarYard.getMonthsBetween ( start_date, end_date, "yyyy-MM-dd", "MMM" );

    Pair[] monthDates = getMonthDates ( start_date, monthNames.size() );

    sb.append ( "select category_name as 'Category'" );

    sb.append ( getMonthsQuery ( monthDates ) );
    
    sb.append ( " from jet_burndown_category " );
    sb.append ( " inner join jet_burndown_transaction on transaction_category_id = category_id" );
    sb.append ( " where transaction_period_id = " + period_id );
    sb.append ( " and transaction_date >= " + DockYard.quote ( start_date ) + " and transaction_date <= " + DockYard.quote ( end_date ) );
    sb.append ( category_id > 0 ? " and transaction_category_id = " + category_id : "" );
    sb.append ( transaction_type > 0 ? " and transaction_type = " + transaction_type : "" );
    sb.append ( " group by category_id" );
    sb.append ( " order by category_lineage" );

    return sb.toString();
    }

    /**
     *
     */
    public ResultSetBoltMap getReport ( JDBC jdbc, QueryActionForm qaf ) throws Exception
    {
    String query = getQuery ( qaf.getPeriodId(), qaf.getCategoryId(), qaf.getTypeId(), qaf.getStartDateAsString(), qaf.getEndDateAsString() );

    return new ResultSetBoltMap ( jdbc, query );
    }
}
