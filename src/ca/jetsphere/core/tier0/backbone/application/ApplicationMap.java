package ca.jetsphere.core.tier0.backbone.application;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class ApplicationMap extends BoltMap
{
    /**
     *
     */

    public ApplicationMap() { super(); }

    /**
     *
     */

    public ApplicationMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int company_id )

    { query ( jdbc, "select * from jet_base_application" + ( company_id > 0 ? " where application_company_id = " + company_id : "" ) ); }

    /**
     *
     */

    public String getKey() { return Application.key(); }

}
