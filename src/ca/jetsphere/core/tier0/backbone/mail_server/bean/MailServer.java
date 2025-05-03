package ca.jetsphere.core.tier0.backbone.mail_server.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

abstract public class MailServer extends Bolt implements IBean
{
    int       mail_server_id          ;
    String    mail_server_uuid        ;
    int       mail_server_company_id  ;
    String    mail_server_host        ;
    int       mail_server_type        ;
    int       mail_server_port        ;
    String    mail_server_login       ;
    String    mail_server_password    ;
    boolean   mail_server_tls         ;
    boolean   mail_server_ssl         ;
    boolean   mail_server_default     ;
    Timestamp mail_server_last_update ;
    Timestamp mail_server_created     ;

    /**
     *
     */

    public void clear()
    {
    setId         (  -1   );
    setUuid       (  ""   );
    setCompanyId  (  -1   );
    setHost       (  ""   );
    setType       (   0   );
    setPort       (   0   );
    setLogin      (  ""   );
    setPassword   (  ""   );
    setTls        ( false );
    setSsl        ( false );
    setDefault    ( false );
    setLastUpdate ( null  );
    setCreated    ( null  );
    }

    /**
     *
     */

    public void copy ( Bolt mailServer )
    {
    setId         ( ( ( MailServer ) mailServer ).getId        () );
    setUuid       ( ( ( MailServer ) mailServer ).getUuid      () );
    setCompanyId  ( ( ( MailServer ) mailServer ).getCompanyId () );
    setHost       ( ( ( MailServer ) mailServer ).getHost      () );
    setType       ( ( ( MailServer ) mailServer ).getType      () );
    setPort       ( ( ( MailServer ) mailServer ).getPort      () );
    setLogin      ( ( ( MailServer ) mailServer ).getLogin     () );
    setPassword   ( ( ( MailServer ) mailServer ).getPassword  () );
    setTls        ( ( ( MailServer ) mailServer ).getTls       () );
    setSsl        ( ( ( MailServer ) mailServer ).getSsl       () );
    setDefault    ( ( ( MailServer ) mailServer ).getDefault   () );
    setLastUpdate ( ( ( MailServer ) mailServer ).getLastUpdate() );
    setCreated    ( ( ( MailServer ) mailServer ).getCreated   () );
    }

    /**
     *
     */

    public void copy ( MailServer mailServer )
    {
    setId         ( mailServer.getId         () );
    setUuid       ( mailServer.getUuid       () );
    setCompanyId  ( mailServer.getCompanyId  () );
    setHost       ( mailServer.getHost       () );
    setType       ( mailServer.getType       () );
    setPort       ( mailServer.getPort       () );
    setLogin      ( mailServer.getLogin      () );
    setPassword   ( mailServer.getPassword   () );
    setTls        ( mailServer.getTls        () );
    setSsl        ( mailServer.getSsl        () );
    setDefault    ( mailServer.getDefault    () );
    setLastUpdate ( mailServer.getLastUpdate () );
    setCreated    ( mailServer.getCreated    () );
    }

    /**
     *
     */

    public int       getId         () { return mail_server_id          ; }
    public String    getUuid       () { return mail_server_uuid        ; }
    public int       getCompanyId  () { return mail_server_company_id  ; }
    public String    getHost       () { return mail_server_host        ; }
    public int       getType       () { return mail_server_type        ; }
    public int       getPort       () { return mail_server_port        ; }
    public String    getLogin      () { return mail_server_login       ; }
    public String    getPassword   () { return mail_server_password    ; }
    public boolean   getTls        () { return mail_server_tls         ; }
    public boolean   getSsl        () { return mail_server_ssl         ; }
    public boolean   getDefault    () { return mail_server_default     ; }
    public Timestamp getLastUpdate () { return mail_server_last_update ; }
    public Timestamp getCreated    () { return mail_server_created     ; }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "mail_server_id"          ) );
    setUuid       ( rs.getString    ( "mail_server_uuid"        ) );
    setCompanyId  ( rs.getInt       ( "mail_server_company_id"  ) );
    setHost       ( rs.getString    ( "mail_server_host"        ) );
    setType       ( rs.getInt       ( "mail_server_type"        ) );
    setPort       ( rs.getInt       ( "mail_server_port"        ) );
    setLogin      ( rs.getString    ( "mail_server_login"       ) );
    setPassword   ( rs.getString("mail_server_password") );
    setTls        ( rs.getBoolean   ( "mail_server_tls"         ) );
    setSsl        ( rs.getBoolean   ( "mail_server_ssl"         ) );
    setDefault    ( rs.getBoolean   ( "mail_server_default"     ) );
    setLastUpdate ( rs.getTimestamp ( "mail_server_last_update" ) );
    setCreated    ( rs.getTimestamp ( "mail_server_created"     ) );
    }

    /**
     *
     */

    static public String key() { return "mail_server"; }

    /**
     *
     */

    public void setId         ( int       mail_server_id          ) { this.mail_server_id          = mail_server_id          ; }
    public void setUuid       ( String    mail_server_uuid        ) { this.mail_server_uuid        = mail_server_uuid        ; }
    public void setCompanyId  ( int       mail_server_company_id  ) { this.mail_server_company_id  = mail_server_company_id  ; }
    public void setHost       ( String    mail_server_host        ) { this.mail_server_host        = mail_server_host        ; }
    public void setType       ( int       mail_server_type        ) { this.mail_server_type        = mail_server_type        ; }
    public void setPort       ( int       mail_server_port        ) { this.mail_server_port        = mail_server_port        ; }
    public void setLogin      ( String    mail_server_login       ) { this.mail_server_login       = mail_server_login       ; }
    public void setPassword   ( String    mail_server_password    ) { this.mail_server_password    = mail_server_password    ; }
    public void setTls        ( boolean   mail_server_tls         ) { this.mail_server_tls         = mail_server_tls         ; }
    public void setSsl        ( boolean   mail_server_ssl         ) { this.mail_server_ssl         = mail_server_ssl         ; }
    public void setDefault    ( boolean   mail_server_default     ) { this.mail_server_default     = mail_server_default     ; }
    public void setLastUpdate ( Timestamp mail_server_last_update ) { this.mail_server_last_update = mail_server_last_update ; }
    public void setCreated    ( Timestamp mail_server_created     ) { this.mail_server_created     = mail_server_created     ; }

}
