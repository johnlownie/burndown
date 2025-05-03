package ca.jetsphere.core.tier0.backbone.audit.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

abstract public class Audit extends Bolt implements IBean
{
    int       audit_id            ;
    String    audit_uuid          ;
    int       audit_member_id     ;
    String    audit_ip            ;
    String    audit_session_id    ;
    String    audit_path          ;
    String    audit_query_string  ;
    String    audit_method        ;
    String    audit_errors        ;
    Timestamp audit_start         ;
    Timestamp audit_stop          ;
    long      audit_elapsed       ;
    Timestamp audit_last_update   ;
    Timestamp audit_created       ;

    /**
     *
     */

    public void clear()
    {
    setId          (  -1  );
    setUuid        (  ""  );
    setMemberId    (  -1  );
    setIp          (  ""  );
    setSessionId   (  ""  );
    setPath        (  ""  );
    setQueryString (  ""  );
    setMethod      (  ""  );
    setErrors      (  ""  );
    setStart       ( ( Timestamp ) null );
    setStop        ( ( Timestamp ) null );
    setElapsed     (   0  );
    setLastUpdate  ( ( Timestamp ) null );
    setCreated     ( null );
    }

    /**
     *
     */

    public void copy ( Audit audit )
    {
    setId          ( audit.getId          () );
    setUuid        ( audit.getUuid        () );
    setMemberId    ( audit.getMemberId    () );
    setIp          ( audit.getIp          () );
    setSessionId   ( audit.getSessionId   () );
    setPath        ( audit.getPath        () );
    setQueryString ( audit.getQueryString () );
    setMethod      ( audit.getMethod      () );
    setErrors      ( audit.getErrors      () );
    setStart       ( audit.getStart       () );
    setStop        ( audit.getStop        () );
    setElapsed     ( audit.getElapsed     () );
    setLastUpdate  ( audit.getLastUpdate  () );
    setCreated     ( audit.getCreated     () );
    }

    /**
     *
     */

    public int       getId          () { return audit_id           ; }
    public String    getUuid        () { return audit_uuid         ; }
    public int       getMemberId    () { return audit_member_id    ; }
    public String    getIp          () { return audit_ip           ; }
    public String    getSessionId   () { return audit_session_id   ; }
    public String    getPath        () { return audit_path         ; }
    public String    getQueryString () { return audit_query_string ; }
    public String    getMethod      () { return audit_method       ; }
    public String    getErrors      () { return audit_errors       ; }
    public Timestamp getStart       () { return audit_start        ; }
    public Timestamp getStop        () { return audit_stop         ; }
    public long      getElapsed     () { return audit_elapsed      ; }
    public Timestamp getLastUpdate  () { return audit_last_update  ; }
    public Timestamp getCreated     () { return audit_created      ; }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception 
    {
    setId          ( rs.getInt         ( "audit_id"           ) );
    setUuid        ( rs.getString      ( "audit_uuid"         ) );
    setMemberId    ( rs.getInt         ( "audit_member_id"    ) );
    setIp          ( rs.getString      ( "audit_ip"           ) );
    setSessionId   ( rs.getString      ( "audit_session_id"   ) );
    setPath        ( rs.getString      ( "audit_path"         ) );
    setQueryString ( rs.getString      ( "audit_query_string" ) );
    setMethod      ( rs.getString      ( "audit_method"       ) );
    setErrors      ( rs.getString      ( "audit_errors"       ) );
    setStart       ( rs.getTimestamp   ( "audit_start"        ) );
    setStop        ( rs.getTimestamp   ( "audit_stop"         ) );
    setElapsed     ( rs.getInt         ( "audit_elapsed"      ) );
    setLastUpdate  ( rs.getTimestamp   ( "audit_last_update"  ) );
    setCreated     ( rs.getTimestamp   ( "audit_created"      ) );
    }

    /**
     *
     */

    static public String key() { return "audit"; }

    /**
     *
     */

    public void setId          ( int       audit_id           ) { this.audit_id           = audit_id           ; }
    public void setUuid        ( String    audit_uuid         ) { this.audit_uuid         = audit_uuid         ; }
    public void setMemberId    ( int       audit_member_id    ) { this.audit_member_id    = audit_member_id    ; }
    public void setIp          ( String    audit_ip           ) { this.audit_ip           = audit_ip           ; }
    public void setSessionId   ( String    audit_session_id   ) { this.audit_session_id   = audit_session_id   ; }
    public void setPath        ( String    audit_path         ) { this.audit_path         = audit_path         ; }
    public void setQueryString ( String    audit_query_string ) { this.audit_query_string = audit_query_string ; }
    public void setMethod      ( String    audit_method       ) { this.audit_method       = audit_method       ; }
    public void setErrors      ( String    audit_errors       ) { this.audit_errors       = audit_errors       ; }
    public void setStart       ( Timestamp audit_start        ) { this.audit_start        = audit_start        ; }
    public void setStop        ( Timestamp audit_stop         ) { this.audit_stop         = audit_stop         ; }
    public void setElapsed     ( long      audit_elapsed      ) { this.audit_elapsed      = audit_elapsed      ; }
    public void setLastUpdate  ( Timestamp audit_last_update  ) { this.audit_last_update  = audit_last_update  ; }
    public void setCreated     ( Timestamp audit_created      ) { this.audit_created      = audit_created      ; }

}