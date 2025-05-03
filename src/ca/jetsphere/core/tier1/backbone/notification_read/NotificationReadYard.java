package ca.jetsphere.core.tier1.backbone.notification_read;

import ca.jetsphere.core.jdbc.JDBC;

import java.util.Iterator;

/**
 *
 */

public class NotificationReadYard
{
    /**
     *
     */

    static public void delete ( JDBC jdbc, int notification_id ) throws Exception
    {
    NotificationReadSession notificationReads = new NotificationReadSession ( jdbc, notification_id );

    Iterator it = notificationReads.iterator();

    while ( it.hasNext() )

    { NotificationRead notificationRead = ( NotificationRead ) it.next(); notificationRead.delete ( jdbc ); }

    }
}
