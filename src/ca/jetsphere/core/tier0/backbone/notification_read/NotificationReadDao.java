package ca.jetsphere.core.tier0.backbone.notification_read;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */
public class NotificationReadDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, NotificationRead notification_read ) throws Exception

    { return delete ( jdbc, notification_read, "delete from jet_base_notification_read where notification_read_id = " + notification_read.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    NotificationRead notification_read = ( NotificationRead ) bolt;

    String s = "insert into jet_base_notification_read "
            + "( notification_read_uuid, notification_read_notification_id, notification_read_user_id, notification_read_last_update, notification_read_created )"
            + "values (?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "notification_read_id" } );

    notification_read.setUuid ( UUID.get () ); notification_read.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); notification_read.setLastUpdate ( notification_read.getCreated() );

    ps.setString    ( 1, notification_read.getUuid           () );
    ps.setInt       ( 2, notification_read.getNotificationId () );
    ps.setInt       ( 3, notification_read.getUserId         () );
    ps.setTimestamp ( 4, notification_read.getLastUpdate     () );
    ps.setTimestamp ( 5, notification_read.getCreated        () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    NotificationRead notification_read = ( NotificationRead ) bolt;

    String s = "update jet_base_notification_read "
            + "set notification_read_uuid = ?, notification_read_notification_id = ?, notification_read_user_id = ?, notification_read_last_update = ? "
            + "where notification_read_id = ?";

    ps.setStatement ( s );

    notification_read.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    ( 1, notification_read.getUuid           () );
    ps.setInt       ( 2, notification_read.getNotificationId () );
    ps.setInt       ( 3, notification_read.getUserId         () );
    ps.setTimestamp ( 4, notification_read.getLastUpdate     () );
    ps.setInt       ( 5, notification_read.getId             () );
    }

}
