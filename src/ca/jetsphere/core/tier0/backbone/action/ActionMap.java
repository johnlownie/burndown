package ca.jetsphere.core.tier0.backbone.action;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class ActionMap extends BoltMap
{
    /**
     *
     */

    public ActionMap() { super(); }

    /**
     *
     */

    public ActionMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int company_id )

    { query ( jdbc, "select * from jet_base_action" + ( company_id > 0 ? " where action_company_id = " + company_id : "" ) ); }

    /**
     *
     */

    public Action getAction ( int id ) { return ( Action ) get ( id ); }

    /**
     *
     */

    public String getKey() { return Action.key(); }

}
