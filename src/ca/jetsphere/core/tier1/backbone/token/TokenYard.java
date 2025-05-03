package ca.jetsphere.core.tier1.backbone.token;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 */

public class TokenYard
{
    /**
     *
     */

    static public String get ( JDBC jdbc )

    { return get ( jdbc, -1 ); }

    /**
     *
     */

    static public String get ( JDBC jdbc, int identifier )

    { return get ( jdbc, identifier, 60 ); }

    /**
     *
     */

    static public String get ( JDBC jdbc, int identifier, long expiry )
    {
    Token token = new Token();

    try {

        token.setIdentifier ( identifier );

        if ( expiry > 0 ) token.setExpiry ( new Timestamp ( System.currentTimeMillis() + ( expiry * 60 * 1000 ) ) );

        token.save ( jdbc );

        return token.getUuid();

    } catch ( Exception e ) { Common.trace ( e ); return null; }

    }

    /**
     *
     */

    static synchronized public Token getByIdentifier ( JDBC jdbc, int id )

    { return getToken ( jdbc, "select * from jet_base_token where token_identifier = ? ", id ); }

    /**
     *
     */

    static synchronized public Token getByUuid ( JDBC jdbc, String uuid )

    { return getToken ( jdbc, "select * from jet_base_token where token_uuid = ? ", uuid ); }

    /**
     *
     */

    static private Token getToken ( JDBC jdbc, String query, int parameter )
    {
    PreparedStatement ps = null; ResultSet rs = null;

    try {

        ps = jdbc.prepareStatement ( query ); ps.setInt ( 1, parameter ); rs = ps.executeQuery(); return rs.next() ? new Token ( jdbc, rs ) : null;

    } catch ( Exception e ) { Common.trace ( e ); return null; }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( ps != null ) ps.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    static private Token getToken ( JDBC jdbc, String query, String parameter )
    {
    PreparedStatement ps = null; ResultSet rs = null;

    try {

        ps = jdbc.prepareStatement ( query ); ps.setString ( 1, parameter ); rs = ps.executeQuery(); return rs.next() ? new Token ( jdbc, rs ) : null;

    } catch ( Exception e ) { Common.trace ( e ); return null; }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( ps != null ) ps.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    static public boolean isValid ( JDBC jdbc, String uuid )

    { return isValid ( jdbc, uuid, -1 ); }

    /**
     *
     */

    static public boolean isValid ( JDBC jdbc, String uuid, int identifier )
    {
    Token token = getByUuid ( jdbc, uuid );

    if ( token == null || !token.isValid() ) return false;

    boolean valid = token.getIdentifier() == identifier && !token.isExpired();

    try { if ( token != null && token.isValid() ) token.delete ( jdbc ); } catch ( Exception e ) { Common.trace ( e ); }

    return valid;
    }

}
