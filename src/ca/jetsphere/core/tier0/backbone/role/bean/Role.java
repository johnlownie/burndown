package ca.jetsphere.core.tier0.backbone.role.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

abstract public class Role extends Bolt implements IBean
{
    int       role_id          ;
    String    role_uuid        ;
    int       role_company_id  ;
    int       role_type        ;
    String    role_alias       ;
    String    role_name        ;
    String    role_notes       ;
    boolean   role_read_only   ;
    int       role_closure     ;
    int       role_ordinal     ;
    Timestamp role_last_update ;
    Timestamp role_created     ;

    /**
     *
     */

    public void clear()
    {
    setId         (  -1   );
    setUuid       (  ""   );
    setCompanyId  (  -1   );
    setType       (  -1   );
    setName       (  ""   );
    setAlias      (  ""   );
    setNotes      (  ""   );
    setReadOnly   ( false );
    setClosure    (   0   );
    setOrdinal    (   0   );
    setLastUpdate ( null  );
    setCreated    ( null  );
    }

    /**
     *
     */

    public void copy ( Bolt role )
    {
    setId         ( ( ( Role ) role ).getId         () );
    setUuid       ( ( ( Role ) role ).getUuid       () );
    setCompanyId  ( ( ( Role ) role ).getCompanyId  () );
    setType       ( ( ( Role ) role ).getType       () );
    setName       ( ( ( Role ) role ).getName       () );
    setAlias      ( ( ( Role ) role ).getAlias      () );
    setNotes      ( ( ( Role ) role ).getNotes      () );
    setReadOnly   ( ( ( Role ) role ).getReadOnly   () );
    setClosure    ( ( ( Role ) role ).getClosure    () );
    setOrdinal    ( ( ( Role ) role ).getOrdinal    () );
    setLastUpdate ( ( ( Role ) role ).getLastUpdate () );
    setCreated    ( ( ( Role ) role ).getCreated    () );
    }

    /**
     *
     */

    public int       getId         () { return role_id          ; }
    public String    getUuid       () { return role_uuid        ; }
    public int       getCompanyId  () { return role_company_id  ; }
    public int       getType       () { return role_type        ; }
    public String    getAlias      () { return role_alias       ; }
    public String    getName       () { return role_name        ; }
    public String    getNotes      () { return role_notes       ; }
    public boolean   getReadOnly   () { return role_read_only   ; }
    public int       getClosure    () { return role_closure     ; }
    public int       getOrdinal    () { return role_ordinal     ; }
    public Timestamp getLastUpdate () { return role_last_update ; }
    public Timestamp getCreated    () { return role_created     ; }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "role_id"          ) );
    setUuid       ( rs.getString    ( "role_uuid"        ) );
    setCompanyId  ( rs.getInt       ( "role_company_id"  ) );
    setType       ( rs.getInt       ( "role_type"        ) );
    setAlias      ( rs.getString    ( "role_alias"       ) );
    setName       ( rs.getString    ( "role_name"        ) );
    setNotes      ( rs.getString    ( "role_notes"       ) );
    setReadOnly   ( rs.getBoolean   ( "role_read_only"   ) );
    setClosure    ( rs.getInt       ( "role_closure"     ) );
    setOrdinal    ( rs.getInt       ( "role_ordinal"     ) );
    setLastUpdate ( rs.getTimestamp ( "role_last_update" ) );
    setCreated    ( rs.getTimestamp ( "role_created"     ) );
    }

    /**
     *
     */

    static public String key() { return "role"; }

    /**
     *
     */

    public void setId         ( int       role_id          ) { this.role_id          = role_id          ; }
    public void setUuid       ( String    role_uuid        ) { this.role_uuid        = role_uuid        ; }
    public void setCompanyId  ( int       role_company_id  ) { this.role_company_id  = role_company_id  ; }
    public void setType       ( int       role_type        ) { this.role_type        = role_type        ; }
    public void setAlias      ( String    role_alias       ) { this.role_alias       = role_alias       ; }
    public void setName       ( String    role_name        ) { this.role_name        = role_name        ; }
    public void setNotes      ( String    role_notes       ) { this.role_notes       = role_notes       ; }
    public void setReadOnly   ( boolean   role_read_only   ) { this.role_read_only   = role_read_only   ; }
    public void setClosure    ( int       role_closure     ) { this.role_closure     = role_closure     ; }
    public void setOrdinal    ( int       role_ordinal     ) { this.role_ordinal     = role_ordinal     ; }
    public void setLastUpdate ( Timestamp role_last_update ) { this.role_last_update = role_last_update ; }
    public void setCreated    ( Timestamp role_created     ) { this.role_created     = role_created     ; }

}
