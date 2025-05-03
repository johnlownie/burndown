package ca.jetsphere.core.tier0.backbone.application.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

abstract public class Application extends Bolt implements IBean
{
    int       application_id          ;
    String    application_uuid        ;
    int       application_company_id  ;
    String    application_name        ;
    String    application_notes       ;
    boolean   application_default     ;
    int       application_period_id   ;
    int       application_role_id     ;
    boolean   application_closed      ;
    int       application_eula        ;
    Timestamp application_last_update ;
    Timestamp application_created     ;

    /**
     *
     */

    public void clear()
    {
    setId         (  -1   );
    setUuid       (  ""   );
    setCompanyId  (  -1   );
    setName       (  ""   );
    setNotes      (  ""   );
    setDefault    ( false );
    setPeriodId   (  -1   );
    setRoleId     (  -1   );
    setClosed     ( false );
    setEula       (   0   );
    setLastUpdate ( null  );
    setCreated    ( null  );
    }

    /**
     *
     */

    public void copy ( Bolt application )
    {
    setId         ( ( ( Application ) application ).getId        () );
    setUuid       ( ( ( Application ) application ).getUuid      () );
    setCompanyId  ( ( ( Application ) application ).getCompanyId () );
    setName       ( ( ( Application ) application ).getName      () );
    setNotes      ( ( ( Application ) application ).getNotes     () );
    setDefault    ( ( ( Application ) application ).getDefault   () );
    setPeriodId   ( ( ( Application ) application ).getPeriodId  () );
    setRoleId     ( ( ( Application ) application ).getRoleId    () );
    setClosed     ( ( ( Application ) application ).getClosed    () );
    setEula       ( ( ( Application ) application ).getEula      () );
    setLastUpdate ( ( ( Application ) application ).getLastUpdate() );
    setCreated    ( ( ( Application ) application ).getCreated   () );
    }

    /**
     *
     */

    public int       getId         () { return application_id          ; }
    public String    getUuid       () { return application_uuid        ; }
    public int       getCompanyId  () { return application_company_id  ; }
    public String    getName       () { return application_name        ; }
    public String    getNotes      () { return application_notes       ; }
    public boolean   getDefault    () { return application_default     ; }
    public int       getPeriodId   () { return application_period_id   ; }
    public int       getRoleId     () { return application_role_id     ; }
    public boolean   getClosed     () { return application_closed      ; }
    public int       getEula       () { return application_eula        ; }
    public Timestamp getLastUpdate () { return application_last_update ; }
    public Timestamp getCreated    () { return application_created     ; }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId          ( rs.getInt       ( "application_id"          ) );
    setUuid        ( rs.getString    ( "application_uuid"        ) );
    setCompanyId   ( rs.getInt       ( "application_company_id"  ) );
    setName        ( rs.getString    ( "application_name"        ) );
    setNotes       ( rs.getString    ( "application_notes"       ) );
    setDefault     ( rs.getBoolean   ( "application_default"     ) );
    setPeriodId    ( rs.getInt       ( "application_period_id"   ) );
    setRoleId      ( rs.getInt       ( "application_role_id"     ) );
    setClosed      ( rs.getBoolean   ( "application_closed"      ) );
    setEula        ( rs.getInt       ( "application_eula"        ) );
    setLastUpdate  ( rs.getTimestamp ( "application_last_update" ) );
    setCreated     ( rs.getTimestamp ( "application_created"     ) );
    }

    /**
     *
     */

    static public String key() { return "application"; }

    /**
     *
     */

    public void setId         ( int       application_id          ) { this.application_id          = application_id          ; }
    public void setUuid       ( String    application_uuid        ) { this.application_uuid        = application_uuid        ; }
    public void setCompanyId  ( int       application_company_id  ) { this.application_company_id  = application_company_id  ; }
    public void setName       ( String    application_name        ) { this.application_name        = application_name        ; }
    public void setNotes      ( String    application_notes       ) { this.application_notes       = application_notes       ; }
    public void setDefault    ( boolean   application_default     ) { this.application_default     = application_default     ; }
    public void setPeriodId   ( int       application_period_id   ) { this.application_period_id   = application_period_id   ; }
    public void setRoleId     ( int       application_role_id     ) { this.application_role_id     = application_role_id     ; }
    public void setClosed     ( boolean   application_closed      ) { this.application_closed      = application_closed      ; }
    public void setEula       ( int       application_eula        ) { this.application_eula        = application_eula        ; }
    public void setLastUpdate ( Timestamp application_last_update ) { this.application_last_update = application_last_update ; }
    public void setCreated    ( Timestamp application_created     ) { this.application_created     = application_created     ; }

}
