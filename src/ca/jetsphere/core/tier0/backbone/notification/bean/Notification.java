package ca.jetsphere.core.tier0.backbone.notification.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 */

abstract public class Notification extends Bolt implements IBean
{
    int       notification_id          ;
    String    notification_uuid        ;
    String    notification_type        ;
    String    notification_icon        ;
    String    notification_title       ;
    String    notification_message     ;
    String    notification_container   ;
    int       notification_timer       ;
    int       notification_user_id     ;
    int       notification_role_id     ;
    int       notification_bu_id       ;
    String    notification_href        ;
    Date      notification_until       ;
    boolean   notification_active      ;
    Timestamp notification_last_update ;
    Timestamp notification_created     ;

    /**
     *
     */

    public void clear()
    {
    setId         (  -1   );
    setUuid       (  ""   );
    setType       (  ""   );
    setIcon       (  ""   );
    setTitle      (  ""   );
    setMessage    (  ""   );
    setContainer  (  ""   );
    setTimer      (  -1   );
    setUserId     (  -1   );
    setRoleId     (  -1   );
    setBuId       (  -1   );
    setHref       (  ""   );
    setUntil      ( null  );
    setActive     ( false );
    setLastUpdate ( null  );
    setCreated    ( null  );
    }

    /**
     *
     */


    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "notification_id"          ) );
    setUuid       ( rs.getString    ( "notification_uuid"        ) );
    setType       ( rs.getString    ( "notification_type"        ) );
    setIcon       ( rs.getString    ( "notification_icon"        ) );
    setTitle      ( rs.getString    ( "notification_title"       ) );
    setMessage    ( rs.getString    ( "notification_message"     ) );
    setContainer  ( rs.getString    ( "notification_container"   ) );
    setTimer      ( rs.getInt       ( "notification_timer"       ) );
    setUserId     ( rs.getInt       ( "notification_user_id"     ) );
    setRoleId     ( rs.getInt       ( "notification_role_id"     ) );
    setBuId       ( rs.getInt       ( "notification_bu_id"       ) );
    setHref       ( rs.getString    ( "notification_href"        ) );
    setUntil      ( rs.getDate      ( "notification_until"       ) );
    setActive     ( rs.getBoolean   ( "notification_active"      ) );
    setLastUpdate ( rs.getTimestamp ( "notification_last_update" ) );
    setCreated    ( rs.getTimestamp ( "notification_created"     ) );
    }

    /**
     *
     */

    static public String key() { return "notification"; }

    /**
     *
     */

    public int       getId         () { return notification_id          ; }
    public String    getUuid       () { return notification_uuid        ; }
    public String    getType       () { return notification_type        ; }
    public String    getIcon       () { return notification_icon        ; }
    public String    getTitle      () { return notification_title       ; }
    public String    getMessage    () { return notification_message     ; }
    public String    getContainer  () { return notification_container   ; }
    public int       getTimer      () { return notification_timer       ; }
    public int       getUserId     () { return notification_user_id     ; }
    public int       getRoleId     () { return notification_role_id     ; }
    public int       getBuId       () { return notification_bu_id       ; }
    public String    getHref       () { return notification_href        ; }
    public Date      getUntil      () { return notification_until       ; }
    public boolean   getActive     () { return notification_active      ; }
    public Timestamp getLastUpdate () { return notification_last_update ; }
    public Timestamp getCreated    () { return notification_created     ; }

    /**
     *
     */

    public void setId         ( int       notification_id          ) { this.notification_id          = notification_id          ; }
    public void setUuid       ( String    notification_uuid        ) { this.notification_uuid        = notification_uuid        ; }
    public void setType       ( String    notification_type        ) { this.notification_type        = notification_type        ; }
    public void setIcon       ( String    notification_icon        ) { this.notification_icon        = notification_icon        ; }
    public void setTitle      ( String    notification_title       ) { this.notification_title       = notification_title       ; }
    public void setMessage    ( String    notification_message     ) { this.notification_message     = notification_message     ; }
    public void setContainer  ( String    notification_container   ) { this.notification_container   = notification_container   ; }
    public void setTimer      ( int       notification_timer       ) { this.notification_timer       = notification_timer       ; }
    public void setUserId     ( int       notification_user_id     ) { this.notification_user_id     = notification_user_id     ; }
    public void setRoleId     ( int       notification_role_id     ) { this.notification_role_id     = notification_role_id     ; }
    public void setBuId       ( int       notification_bu_id       ) { this.notification_bu_id       = notification_bu_id       ; }
    public void setHref       ( String    notification_href        ) { this.notification_href        = notification_href        ; }
    public void setUntil      ( Date      notification_until       ) { this.notification_until       = notification_until       ; }
    public void setActive     ( boolean   notification_active      ) { this.notification_active      = notification_active      ; }
    public void setLastUpdate ( Timestamp notification_last_update ) { this.notification_last_update = notification_last_update ; }
    public void setCreated    ( Timestamp notification_created     ) { this.notification_created     = notification_created     ; }

}
