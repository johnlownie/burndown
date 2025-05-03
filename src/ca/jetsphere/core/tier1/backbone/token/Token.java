package ca.jetsphere.core.tier1.backbone.token;

import ca.jetsphere.core.jdbc.JDBC;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

public class Token extends ca.jetsphere.core.tier0.backbone.token.Token
{

    /**
     *
     */

    public Token() { super(); }

    /**
     *
     */

    public Token ( JDBC jdbc, int user_id ) { super ( jdbc, user_id ); }

    /**
     *
     */

    public Token ( JDBC jdbc, ResultSet rs ) throws Exception  { this(); get ( jdbc, rs ); }

    /**
     *
     */

    public boolean isExpired() { return getExpiry().before ( new Timestamp ( System.currentTimeMillis() ) ); }

}
