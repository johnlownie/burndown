package ca.jetsphere.core.tier0.backbone.notification;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class NotificationMap extends BoltMap
{
    /**
     *
     */

    public NotificationMap() { super(); }

    /**
     *
     */

    public NotificationMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int user_id )
    {
    String query = "select * from jet_base_notification";

    if ( user_id > 0 ) query += " where notification_user_id = " + user_id;

    query ( jdbc, query );
    }

    /**
     *
     */

    public Notification getNotification ( int id ) { return ( Notification ) get ( id ); }

    /**
     *
     */

    public String getKey() { return Notification.key(); }

}
