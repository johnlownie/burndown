package ca.jetsphere.core.tier0.backbone.token;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class Token extends ca.jetsphere.core.tier0.backbone.token.foreign.Token
{
    /**
     *
     */

    public Token() { clear(); }

    /**
     *
     */

    public Token ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getTokenDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getTokenDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getTokenDao().update ( jdbc, this ); }
}
