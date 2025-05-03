package ca.jetsphere.core.tier0.backbone.token;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class TokenMap extends BoltMap
{
    /**
     *
     */

    public TokenMap() { super(); }

    /**
     *
     */

    public TokenMap ( JDBC jdbc, int id ) { query ( jdbc, -1 ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int id ) { query ( jdbc, "select * from jet_base_token" ); }

    /**
     *
     */

    public String getKey() { return Token.key (); }

}
