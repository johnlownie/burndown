package ca.jetsphere.core.tier0.backbone.user;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */

public class UserDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, User user ) throws Exception

    {  return delete ( jdbc, user, "delete from jet_base_user where user_id = " + user.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    User user = ( User ) bolt;

    String s = "insert into jet_base_user ( user_uuid, user_username, user_password, user_status, user_last_login, user_role_ids, user_notes, user_last_update, user_created )" +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    ps.setStatement ( s, new String [] { "user_id" } );

    user.setUuid ( UUID.get() ); user.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); user.setLastUpdate ( user.getCreated() );

    ps.setString    ( 1, user.getUuid       () );
    ps.setString    ( 2, user.getUsername   () );
    ps.setString    ( 3, user.getPassword   () );
    ps.setInt       ( 4, user.getStatus     () );
    ps.setTimestamp ( 5, user.getLastLogin  () );
    ps.setString    ( 6, user.getRoleIds    () );
    ps.setString    ( 7, user.getNotes      () );
    ps.setTimestamp ( 8, user.getLastUpdate () );
    ps.setTimestamp ( 9, user.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    User user = ( User ) bolt;

    String s = "update jet_base_user set user_uuid = ?, user_username = ?, user_password = ?, user_status = ?, user_last_login = ?, user_role_ids = ?, user_notes = ?, user_last_update = ? where user_id = ?";

    ps.setStatement ( s );

    user.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    ( 1, user.getUuid       () );
    ps.setString    ( 2, user.getUsername   () );
    ps.setString    ( 3, user.getPassword   () );
    ps.setInt       ( 4, user.getStatus     () );
    ps.setTimestamp ( 5, user.getLastLogin  () );
    ps.setString    ( 6, user.getRoleIds    () );
    ps.setString    ( 7, user.getNotes      () );
    ps.setTimestamp ( 8, user.getLastUpdate () );
    ps.setInt       ( 9, user.getId         () );
}

}
