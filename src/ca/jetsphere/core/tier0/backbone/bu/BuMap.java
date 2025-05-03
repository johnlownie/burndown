package ca.jetsphere.core.tier0.backbone.bu;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.tree.TreeSession;

/**
 *
 */

abstract public class BuMap extends TreeSession
{
    /**
     *
     */

    public BuMap() { super(); }

    /**
     *
     */

    public BuMap ( JDBC jdbc, int id ) { this(); query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int period_id )
    {
    String query = "select * from jet_base_bu";

    if ( period_id > 0 ) query += " where bu_period_id = " + period_id; else

    query ( jdbc,  query );

    treeify();
    }

    /**
     *
     */

    public String getKey() { return Bu.key(); }

    /**
     *
     */

    public Bu getBu ( int id ) { return ( Bu ) getTree ( id ); }

}
