package ca.jetsphere.core.tier0.backbone.role_right;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.tree.TreeSession;

/**
 *
 */

abstract public class RoleRightMap extends TreeSession
{
    /**
     *
     */

    public RoleRightMap() { super(); }

    /**
     *
     */

    public RoleRightMap ( JDBC jdbc, int id ) { this(); query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int id ) { query ( jdbc, getQuery ( id ) ); treeify(); }

    /**
     *
     */

    public String getKey() { return RoleRight.key(); }

    /**
     *
     */

    protected String getQuery ( int id )

    { return "select * from jet_base_role_right left join jet_base_action on role_right_action_id = action_id where role_right_role_id = " + id; }

}
