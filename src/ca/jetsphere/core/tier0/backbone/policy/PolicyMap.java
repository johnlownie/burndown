package ca.jetsphere.core.tier0.backbone.policy;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class PolicyMap extends BoltMap
{
    /**
     *
     */

    public PolicyMap() { super (); }

    /**
     *
     */

    public PolicyMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int company_id )
    {
    String query = "select * from jet_base_policy";

    if ( company_id > -1 )  query += " where policy_company_id = " + company_id;

    query ( jdbc, query );
    }

    /**
     *
     */

    public String getKey() { return Policy.key(); }

}

