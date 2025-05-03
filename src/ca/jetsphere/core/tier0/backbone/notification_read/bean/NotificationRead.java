package ca.jetsphere.core.tier0.backbone.notification_read.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * 
 */

abstract public class NotificationRead extends Bolt implements IBean
{
    int       notification_read_id              ;
    String    notification_read_uuid            ;
    int       notification_read_notification_id ;
    int       notification_read_user_id         ;
    Timestamp notification_read_last_update     ;
    Timestamp notification_read_created         ;

    /**
     *
     */

    public void clear()
    {
    setId              (  -1   );
    setUuid            (  ""   );
    setNotificationId  (  -1   );
    setUserId          (  -1   );
    setLastUpdate      ( null  );
    setCreated         ( null  );
    }

    /**
     *
     */


    public void get ( ResultSet rs ) throws Exception
    {
    setId              ( rs.getInt       ( "notification_read_id"              ) );
    setUuid            ( rs.getString    ( "notification_read_uuid"            ) );
    setNotificationId  ( rs.getInt       ( "notification_read_notification_id" ) );
    setUserId          ( rs.getInt       ( "notification_read_user_id"         ) );
    setLastUpdate      ( rs.getTimestamp ( "notification_read_last_update"     ) );
    setCreated         ( rs.getTimestamp ( "notification_read_created"         ) );
    }

    /**
     *
     */

    static public String key() { return "notification_read"; }

    /**
     *
     */

    public int       getId             () { return notification_read_id              ; }
    public String    getUuid           () { return notification_read_uuid            ; }
    public int       getNotificationId () { return notification_read_notification_id ; }
    public int       getUserId         () { return notification_read_user_id         ; }
    public Timestamp getLastUpdate     () { return notification_read_last_update     ; }
    public Timestamp getCreated        () { return notification_read_created         ; }

    /**
     *
     */

    public void setId              ( int       notification_read_id              ) { this.notification_read_id              = notification_read_id              ; }
    public void setUuid            ( String    notification_read_uuid            ) { this.notification_read_uuid            = notification_read_uuid            ; }
    public void setNotificationId  ( int       notification_read_notification_id ) { this.notification_read_notification_id = notification_read_notification_id ; }
    public void setUserId          ( int       notification_read_user_id         ) { this.notification_read_user_id         = notification_read_user_id         ; }
    public void setLastUpdate      ( Timestamp notification_read_last_update     ) { this.notification_read_last_update     = notification_read_last_update     ; }
    public void setCreated         ( Timestamp notification_read_created         ) { this.notification_read_created         = notification_read_created         ; }
}
