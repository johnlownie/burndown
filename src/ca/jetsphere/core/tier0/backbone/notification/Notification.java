package ca.jetsphere.core.tier0.backbone.notification;

import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 * 
 */

public class Notification extends ca.jetsphere.core.tier0.backbone.notification.foreign.Notification
{
    /**
     *
     */

    public Notification() { clear(); }

    /**
     *
     */

    public Notification ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getNotificationDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public String getUntilAsString() { return CalendarYard.getDateTimeFormat ( getUntil(), "yyyy-MM-dd" ); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getNotificationDao().insert ( jdbc, this ); }

    /**
     *
     */

    public void setUntilAsString ( String date ) { setUntil ( CalendarYard.getDate ( date, "yyyy-MM-dd" ) ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getNotificationDao().update ( jdbc, this ); }

}
