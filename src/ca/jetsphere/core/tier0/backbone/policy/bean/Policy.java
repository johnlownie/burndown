package ca.jetsphere.core.tier0.backbone.policy.bean;

import java.sql.ResultSet;
import java.sql.Timestamp;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

/**
 *
 */

abstract public class Policy extends Bolt implements IBean
{
    int       policy_id           ;
    String    policy_uuid         ;
    int       policy_company_id   ;
    String    policy_name         ;
    int       policy_value        ;
    Timestamp policy_last_update  ;
    Timestamp policy_created      ;

    /**
     *
     */

    public void clear()
    {
    setId         (  -1  );
    setUuid       (  ""  );
    setCompanyId  (  -1  );
    setName       (  ""  );
    setValue      (  -1  );
    setLastUpdate ( null );
    setCreated    ( null );
    }

    /**
     *
     */

    public void copy ( Bolt policy )
    {
    setId         ( ( ( Policy ) policy ).getId        () );
    setUuid       ( ( ( Policy ) policy ).getUuid      () );
    setCompanyId  ( ( ( Policy ) policy ).getCompanyId () );
    setName       ( ( ( Policy ) policy ).getName      () );
    setValue      ( ( ( Policy ) policy ).getValue     () );
    setLastUpdate ( ( ( Policy ) policy ).getLastUpdate() );
    setCreated    ( ( ( Policy ) policy ).getCreated   () );
    }

    /**
     *
     */

    public void copy ( Policy policy )
    {
    setId           ( policy.getId         () );
    setUuid         ( policy.getUuid       () );
    setCompanyId    ( policy.getCompanyId  () );
    setName         ( policy.getName       () );
    setValue        ( policy.getValue      () );
    setLastUpdate   ( policy.getLastUpdate () );
    setCreated      ( policy.getCreated    () );
    }

    /**
     *
     */

    public int       getId         () { return policy_id           ; }
    public String    getUuid       () { return policy_uuid         ; }
    public int       getCompanyId  () { return policy_company_id   ; }
    public String    getName       () { return policy_name         ; }
    public int       getValue      () { return policy_value        ; }
    public Timestamp getLastUpdate () { return policy_last_update  ; }
    public Timestamp getCreated    () { return policy_created      ; }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "policy_id"           ) );
    setUuid       ( rs.getString    ( "policy_uuid"         ) );
    setCompanyId  ( rs.getInt       ( "policy_company_id"   ) );
    setName       ( rs.getString    ( "policy_name"         ) );
    setValue      ( rs.getInt       ( "policy_value"        ) );
    setLastUpdate ( rs.getTimestamp ( "policy_last_update"  ) );
    setCreated    ( rs.getTimestamp ( "policy_created"      ) );
    }

    /**
     *
     */

    static public String key() { return "policy"; }

    /**
     *
     */

    public void setId         ( int       policy_id           ) { this.policy_id           = policy_id           ; }
    public void setUuid       ( String    policy_uuid         ) { this.policy_uuid         = policy_uuid         ; }
    public void setCompanyId  ( int       policy_company_id   ) { this.policy_company_id   = policy_company_id   ; }
    public void setName       ( String    policy_name         ) { this.policy_name         = policy_name         ; }
    public void setValue      ( int       policy_value        ) { this.policy_value        = policy_value        ; }
    public void setLastUpdate ( Timestamp policy_last_update  ) { this.policy_last_update  = policy_last_update  ; }
    public void setCreated    ( Timestamp policy_created      ) { this.policy_created      = policy_created      ; }

}
