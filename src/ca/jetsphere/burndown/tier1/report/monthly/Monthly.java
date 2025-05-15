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
    private String getQuery ( Pair[] monthDates, int period_id, int transaction_type, String start_date, String end_date )
    {
    StringBuilder sb = new StringBuilder();

    sb.append ( "select concat(p.category_id, ':', p.category_depth, ':', p.category_name) as 'Category'" );

    sb.append ( getMonthsQuery ( monthDates ) );
    
    sb.append ( " from jet_burndown_category p" );
    sb.append ( " inner join jet_burndown_category c on c.category_id = p.category_id or c.category_lineage like concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/%')" );
    sb.append ( " inner join jet_burndown_transaction on transaction_category_id = c.category_id" );
//    sb.append ( " where p.category_depth > 0" );
    sb.append ( " where p.category_included and c.category_included" );
    sb.append ( " and transaction_period_id = " + period_id );
    sb.append ( " and transaction_date >= " + DockYard.quote ( start_date ) + " and transaction_date <= " + DockYard.quote ( end_date ) );
    sb.append ( transaction_type > 0 ? " and transaction_type = " + transaction_type : "" );
    sb.append ( " group by concat(p.category_id, ':', p.category_depth, ':', p.category_name)" );
    sb.append ( " order by concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/'), c.category_ordinal" );

    return sb.toString();
    }

    /**
     *
     */
    public ResultSetBoltMap getReport ( JDBC jdbc, QueryActionForm qaf ) throws Exception
    { 
    List<String> monthNames = CalendarYard.getMonthsBetween ( qaf.getStartDateAsString(), qaf.getEndDateAsString(), "yyyy-MM-dd", "MMM" );

    Pair[] monthDates = CalendarYard.getMonthDates ( qaf.getStartDateAsString(), monthNames.size() );

    String query = getQuery ( monthDates, qaf.getPeriodId(), qaf.getTypeId(), qaf.getStartDateAsString(), qaf.getEndDateAsString() );
       
    return new ResultSetBoltMap ( jdbc, query );
    }
}
