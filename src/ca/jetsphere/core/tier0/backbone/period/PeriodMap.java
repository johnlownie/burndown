package ca.jetsphere.core.tier0.backbone.period;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class PeriodMap extends BoltMap
{
    /**
     *
     */

    public PeriodMap() { super(); }

    /**
     *
     */

    public PeriodMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int company_id, int application_id )
    {
    String query = "select * from jet_base_period";

    if ( application_id > 0 ) query += " where period_application_id = " + application_id; else

    if ( company_id     > 0 ) query += " inner join jet_base_application on application_id = period_application_id where application_company_id = " + company_id;

    query ( jdbc, query );
    }

    /**
     *
     */

    public Period getPeriod ( int id ) { return ( Period ) get ( id ); }

    /**
     *
     */

    public String getKey() { return Period.key(); }

}
