package ca.jetsphere.core.tier1.backbone.notification;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.notification_read.NotificationReadYard;
import ca.jetsphere.core.tier1.backbone.user.User;

import java.sql.ResultSet;

/**
 * 
 */
public class Notification extends ca.jetsphere.core.tier0.backbone.notification.Notification
{
    String notification_username;

    /**
     *
     */

    public Notification() { super(); setDefaults(); }

    /**
     *
     */

    public Notification ( JDBC jdbc, int notification_id ) { super ( jdbc, notification_id ); }

    /**
     *
     */

    public Notification ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "user", "type", "icon", "title", "last.update", "actions" }; }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception

    { NotificationReadYard.delete ( jdbc, getId() ); super.delete ( jdbc ); }

    /**
     *
     */

    static public String [] fields()

    { return new String [] { "notification_user_id", "notification_type", "notification_icon", "notification_title", "notification_last_update", "notification_uuid" }; }

    /**
     *
     */

    public void foreign ( JDBC jdbc ) throws Exception

    { setUsername ( new User (  jdbc, getUserId() ).getUsername() ); }

    /**
     *
     */

    public Object get ( String s )
    {
    if ( "notification_user_id".equals ( s ) ) return getUserId() > 0 ? getUsername() : "All Users";

    return super.get ( s );
    }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void setDefaults()
    {
    this.setRoleId        ( 3 );
    this.setBuId          ( -1 );
    this.setHref          ( "/my_dashboard.do");
    this.setUntilAsString ( "2020-06-30" );
    this.setActive        ( true );
    }

    /**
     *
     */

    public String getUsername() { return notification_username; }

    /**
     *
     */

    public void setUsername ( String notification_username ) { this.notification_username = notification_username ; }

}
