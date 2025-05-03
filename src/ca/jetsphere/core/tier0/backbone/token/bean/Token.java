package ca.jetsphere.core.tier0.backbone.token.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * 
 */

abstract public class Token extends Bolt implements IBean
{
    int       token_id          ;
    String    token_uuid        ;
    int       token_identifier  ;
    Timestamp token_expiry      ;
    Timestamp token_last_update ;
    Timestamp token_created     ;

    /**
     * 
     */
    
    public void clear()
    {
    setId         (  -1  );
    setUuid       (  ""  );
    setIdentifier (  -1  );
    setExpiry     ( null );
    setLastUpdate ( null );
    setCreated    ( null );
    }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "token_id"          ) );
    setUuid       ( rs.getString    ( "token_uuid"        ) );
    setIdentifier ( rs.getInt       ( "token_identifier"  ) );
    setExpiry     ( rs.getTimestamp ( "token_expiry"      ) );
    setLastUpdate ( rs.getTimestamp ( "token_last_update" ) );
    setCreated    ( rs.getTimestamp ( "token_created"     ) );
    }

    /**
     *
     */

    public int       getId          () { return token_id           ; }
    public String    getUuid        () { return token_uuid         ; }
    public int       getIdentifier  () { return token_identifier   ; }
    public Timestamp getExpiry      () { return token_expiry       ; }
    public Timestamp getLastUpdate  () { return token_last_update  ; }
    public Timestamp getCreated     () { return token_created      ; }

    /**
     *
     */

    static public String key() { return "token"; }

    /**
     *
     */

    public void setId          ( int       token_id           ) { this.token_id           = token_id          ; }
    public void setUuid        ( String    token_uuid         ) { this.token_uuid         = token_uuid        ; }
    public void setIdentifier  ( int       token_identifier   ) { this.token_identifier   = token_identifier  ; }
    public void setExpiry      ( Timestamp token_expiry       ) { this.token_expiry       = token_expiry      ; }
    public void setLastUpdate  ( Timestamp token_last_update  ) { this.token_last_update  = token_last_update ; }
    public void setCreated     ( Timestamp token_created      ) { this.token_created      = token_created     ; }

}
