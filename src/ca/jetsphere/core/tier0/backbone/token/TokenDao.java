package ca.jetsphere.core.tier0.backbone.token;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */

public class TokenDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, Token token ) throws Exception

    {  return delete ( jdbc, token, "delete from jet_base_token where token_id = " + token.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Token token = ( Token ) bolt;

    String s = "insert into jet_base_token ( token_uuid, token_identifier, token_expiry, token_last_update, token_created ) values (?, ?, ?, ?, ?)";

    ps.setStatement ( s, new String [] { "token_id" } );

    token.setUuid ( UUID.get () ); token.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); token.setLastUpdate ( token.getCreated() );

    if ( token.getExpiry() == null ) { token.setExpiry ( new Timestamp ( System.currentTimeMillis() + 24 * 60 * 60 * 1000 ) ); }

    ps.setString    ( 1, token.getUuid       () );
    ps.setInt       ( 2, token.getIdentifier () );
    ps.setTimestamp ( 3, token.getExpiry     () );
    ps.setTimestamp ( 4, token.getLastUpdate () );
    ps.setTimestamp ( 5, token.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Token token = ( Token ) bolt;

    String s = "update jet_base_token set token_uuid = ?, token_identifier = ?, token_expiry = ?, token_last_update = ? where token_id = ?";

    ps.setStatement ( s );

    token.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    ( 1, token.getUuid       () );
    ps.setInt       ( 2, token.getIdentifier () );
    ps.setTimestamp ( 3, token.getExpiry     () );
    ps.setTimestamp ( 4, token.getLastUpdate () );
    ps.setInt       ( 5, token.getId         () );
    }

}
