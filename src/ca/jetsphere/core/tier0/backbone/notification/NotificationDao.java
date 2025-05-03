package ca.jetsphere.core.tier0.backbone.notification;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */
public class NotificationDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, Notification notification ) throws Exception

    { return delete ( jdbc, notification, "delete from jet_base_notification where notification_id = " + notification.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Notification notification = ( Notification ) bolt;

    String s = "insert into jet_base_notification "
            + "( notification_uuid, notification_type, notification_icon, notification_title, notification_message, notification_container, notification_timer, notification_user_id, notification_role_id, notification_bu_id, notification_href, notification_until, notification_active, notification_last_update, notification_created )"
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "notification_id" } );

    notification.setUuid ( UUID.get () ); notification.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); notification.setLastUpdate ( notification.getCreated() );

    ps.setString    (  1, notification.getUuid       () );
    ps.setString    (  2, notification.getType       () );
    ps.setString    (  3, notification.getIcon       () );
    ps.setString    (  4, notification.getTitle      () );
    ps.setString    (  5, notification.getMessage    () );
    ps.setString    (  6, notification.getContainer  () );
    ps.setInt       (  7, notification.getTimer      () );
    ps.setInt       (  8, notification.getUserId     () );
    ps.setInt       (  9, notification.getRoleId     () );
    ps.setInt       ( 10, notification.getBuId       () );
    ps.setString    ( 11, notification.getHref       () );
    ps.setDate      ( 12, new java.sql.Date ( notification.getUntil().getTime() ) );
    ps.setBoolean   ( 13, notification.getActive     () );
    ps.setTimestamp ( 14, notification.getLastUpdate () );
    ps.setTimestamp ( 15, notification.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Notification notification = ( Notification ) bolt;

    String s = "update jet_base_notification "
            + "set notification_uuid = ?, notification_type = ?, notification_icon = ?, notification_title = ?, notification_message = ?, notification_container = ?, notification_timer = ?, notification_user_id = ?, notification_role_id = ?, notification_bu_id = ?, notification_url = ?, notification_until = ?, notification_active = ?, notification_last_update = ? "
            + "where notification_id = ?";

    ps.setStatement ( s );

    notification.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, notification.getUuid       () );
    ps.setString    (  2, notification.getType       () );
    ps.setString    (  3, notification.getIcon       () );
    ps.setString    (  4, notification.getTitle      () );
    ps.setString    (  5, notification.getMessage    () );
    ps.setString    (  6, notification.getContainer  () );
    ps.setInt       (  7, notification.getTimer      () );
    ps.setInt       (  8, notification.getUserId     () );
    ps.setInt       (  9, notification.getRoleId     () );
    ps.setInt       ( 10, notification.getBuId       () );
    ps.setString    ( 11, notification.getHref       () );
    ps.setDate      ( 12, new java.sql.Date ( notification.getUntil().getTime() ) );
    ps.setBoolean   ( 13, notification.getActive     () );
    ps.setTimestamp ( 14, notification.getLastUpdate () );
    ps.setInt       ( 15, notification.getId         () );
    }

}
