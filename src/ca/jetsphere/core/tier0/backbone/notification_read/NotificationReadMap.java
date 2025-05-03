package ca.jetsphere.core.tier0.backbone.notification_read;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class NotificationReadMap extends BoltMap
{
    /**
     *
     */

    public NotificationReadMap() { super(); }

    /**
     *
     */

    public NotificationReadMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int notification_id )
    {
    String query = "select * from jet_base_notification_read";

    if ( notification_id > 0 ) query += " where notification_read_notification_id = " + notification_id;

    query ( jdbc, query );
    }

    /**
     *
     */

    public NotificationRead getNotificationRead ( int id ) { return ( NotificationRead ) get ( id ); }

    /**
     *
     */

    public String getKey() { return NotificationRead.key(); }

}
