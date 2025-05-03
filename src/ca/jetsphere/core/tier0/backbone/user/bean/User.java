package ca.jetsphere.core.tier0.backbone.user.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

abstract public class User extends Bolt implements IBean
{
    int       user_id          ;
    String    user_uuid        ;
    String    user_username    ;
    String    user_password    ;
    int       user_status      ;
    Timestamp user_last_login  ;
    String    user_role_ids    ;
    String    user_notes       ;
    Timestamp user_last_update ;
    Timestamp user_created     ;

    /**
     *
     */

    public void clear()
    {
    setId          (  -1  );
    setUuid        (  ""  );
    setUsername    (  ""  );
    setPassword    (  ""  );
    setStatus      (  -1  );
    setLastLogin   ( null );
    setRoleIds     (  ""  );
    setNotes       (  ""  );
    setLastUpdate  ( null );
    setCreated     ( null );
    }

    /**
     *
     */

    public void copy ( Bolt user )
    {
    setId          ( ( ( User ) user ).getId        () );
    setUuid        ( ( ( User ) user ).getUuid      () );
    setUsername    ( ( ( User ) user ).getUsername  () );
    setPassword    ( ( ( User ) user ).getPassword  () );
    setStatus      ( ( ( User ) user ).getStatus    () );
    setLastLogin   ( ( ( User ) user ).getLastLogin () );
    setRoleIds     ( ( ( User ) user ).getRoleIds   () );
    setNotes       ( ( ( User ) user ).getNotes     () );
    setLastUpdate  ( ( ( User ) user ).getLastUpdate() );
    setCreated     ( ( ( User ) user ).getCreated   () );
    }

    /**
     *
     */

    public void copy ( User user )
    {
    setId         ( user.getId         () );
    setUuid       ( user.getUuid       () );
    setUsername   ( user.getUsername   () );
    setPassword   ( user.getPassword   () );
    setStatus     ( user.getStatus     () );
    setLastLogin  ( user.getLastLogin  () );
    setRoleIds    ( user.getRoleIds    () );
    setNotes      ( user.getNotes      () );
    setLastUpdate ( user.getLastUpdate () );
    setCreated    ( user.getCreated    () );
    }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "user_id"          ) );
    setUuid       ( rs.getString    ( "user_uuid"        ) );
    setUsername   ( rs.getString    ( "user_username"    ) );
    setPassword   ( rs.getString    ( "user_password"    ) );
    setStatus     ( rs.getInt       ( "user_status"      ) );
    setLastLogin  ( rs.getTimestamp ( "user_last_login"  ) );
    setRoleIds    ( rs.getString    ( "user_role_ids"    ) );
    setNotes      ( rs.getString    ( "user_notes"       ) );
    setLastUpdate ( rs.getTimestamp ( "user_last_update" ) );
    setCreated    ( rs.getTimestamp ( "user_created"     ) );
    }

    /**
     *
     */

    public int       getId          () { return user_id           ; }
    public String    getUuid        () { return user_uuid         ; }
    public String    getUsername    () { return user_username     ; }
    public String    getPassword    () { return user_password     ; }
    public int       getStatus      () { return user_status       ; }
    public Timestamp getLastLogin   () { return user_last_login   ; }
    public String    getRoleIds     () { return user_role_ids     ; }
    public String    getNotes       () { return user_notes        ; }
    public Timestamp getLastUpdate  () { return user_last_update  ; }
    public Timestamp getCreated     () { return user_created      ; }

    /**
     *
     */

    static public String key() { return "user"; }

    /**
     *
     */

    public void setId          ( int       user_id           ) { this.user_id           = user_id          ; }
    public void setUuid        ( String    user_uuid         ) { this.user_uuid         = user_uuid        ; }
    public void setUsername    ( String    user_username     ) { this.user_username     = user_username    ; }
    public void setPassword    ( String    user_password     ) { this.user_password     = user_password    ; }
    public void setStatus      ( int       user_status       ) { this.user_status       = user_status      ; }
    public void setLastLogin   ( Timestamp user_last_login   ) { this.user_last_login   = user_last_login  ; }
    public void setRoleIds     ( String    user_role_ids     ) { this.user_role_ids     = user_role_ids    ; }
    public void setNotes       ( String    user_notes        ) { this.user_notes        = user_notes       ; }
    public void setLastUpdate  ( Timestamp user_last_update  ) { this.user_last_update  = user_last_update ; }
    public void setCreated     ( Timestamp user_created      ) { this.user_created      = user_created     ; }

}
