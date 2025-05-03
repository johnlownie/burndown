package ca.jetsphere.core.tier0.backbone.notification_read;

import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class NotificationRead extends ca.jetsphere.core.tier0.backbone.notification_read.foreign.NotificationRead
{
    /**
     *
     */

    public NotificationRead() { clear(); }

    /**
     *
     */

    public NotificationRead ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getNotificationReadDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getNotificationReadDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getNotificationReadDao().update ( jdbc, this ); }

}
