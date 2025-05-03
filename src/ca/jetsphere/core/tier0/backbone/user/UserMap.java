package ca.jetsphere.core.tier0.backbone.user;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class UserMap extends BoltMap
{
    /**
     *
     */

    public UserMap() { super(); }

    /**
     *
     */

    public UserMap ( JDBC jdbc, int id ) { query ( jdbc, -1 ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int id ) { query ( jdbc, "select * from jet_base_user" ); }

    /**
     *
     */

    public String getKey() { return User.key(); }

}