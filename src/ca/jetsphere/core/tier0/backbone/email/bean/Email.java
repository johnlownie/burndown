package ca.jetsphere.core.tier0.backbone.email.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

abstract public class Email extends Bolt implements IBean
{
    int       email_id          ;
    String    email_uuid        ;
    int       email_type_id     ;
    String    email_from        ;
    String    email_personal    ;
    String    email_to          ;
    int       email_to_bu_id    ;
    String    email_cc          ;
    int       email_cc_bu_id    ;
    String    email_bcc         ;
    int       email_bcc_bu_id   ;
    String    email_subject     ;
    String    email_message     ;
    String    email_attachment  ;
    int       email_status_id   ;
    Timestamp email_last_update ;
    Timestamp email_created     ;

    /**
     *
     */

    public void clear()
    {
    setId         (  -1   );
    setUuid       (  ""   );
    setTypeId     (  -1   );
    setFrom       (  ""   );
    setPersonal   (  ""   );
    setTo         (  ""   );
    setToBuId     (  -1   );
    setCc         (  ""   );
    setCcBuId     (  -1   );
    setBcc        (  ""   );
    setBccBuId    (  -1   );
    setSubject    (  ""   );
    setMessage    (  ""   );
    setAttachment (  ""   );
    setStatusId   (  -1   );
    setLastUpdate ( null  );
    setCreated    ( null  );
    }

    /**
     *
     */

    public void copy ( Bolt email )
    {
    setId         ( ( ( Email ) email ).getId         () );
    setUuid       ( ( ( Email ) email ).getUuid       () );
    setTypeId     ( ( ( Email ) email ).getTypeId     () );
    setFrom       ( ( ( Email ) email ).getFrom       () );
    setPersonal   ( ( ( Email ) email ).getPersonal   () );
    setTo         ( ( ( Email ) email ).getTo         () );
    setToBuId     ( ( ( Email ) email ).getToBuId     () );
    setCc         ( ( ( Email ) email ).getCc         () );
    setCcBuId     ( ( ( Email ) email ).getCcBuId     () );
    setBcc        ( ( ( Email ) email ).getBcc        () );
    setBccBuId    ( ( ( Email ) email ).getBccBuId    () );
    setSubject    ( ( ( Email ) email ).getSubject    () );
    setMessage    ( ( ( Email ) email ).getMessage    () );
    setAttachment ( ( ( Email ) email ).getAttachment () );
    setStatusId   ( ( ( Email ) email ).getStatusId   () );
    setLastUpdate ( ( ( Email ) email ).getLastUpdate () );
    setCreated    ( ( ( Email ) email ).getCreated    () );
    }

    /**
     *
     */

    public void copy ( Email email )
    {
    setId         ( email.getId         () );
    setUuid       ( email.getUuid       () );
    setTypeId     ( email.getTypeId     () );
    setFrom       ( email.getFrom       () );
    setPersonal   ( email.getPersonal   () );
    setTo         ( email.getTo         () );
    setToBuId     ( email.getToBuId     () );
    setCc         ( email.getCc         () );
    setCcBuId     ( email.getCcBuId     () );
    setBcc        ( email.getBcc        () );
    setBccBuId    ( email.getBccBuId    () );
    setSubject    ( email.getSubject    () );
    setMessage    ( email.getMessage    () );
    setAttachment ( email.getAttachment () );
    setStatusId   ( email.getStatusId   () );
    setLastUpdate ( email.getLastUpdate () );
    setCreated    ( email.getCreated    () );
    }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "email_id"          ) );
    setUuid       ( rs.getString    ( "email_uuid"        ) );
    setTypeId     ( rs.getInt       ( "email_type_id"     ) );
    setFrom       ( rs.getString    ( "email_from"        ) );
    setPersonal   ( rs.getString    ( "email_personal"    ) );
    setTo         ( rs.getString    ( "email_to"          ) );
    setToBuId     ( rs.getInt       ( "email_to_bu_id"    ) );
    setCc         ( rs.getString    ( "email_cc"          ) );
    setCcBuId     ( rs.getInt       ( "email_cc_bu_id"    ) );
    setBcc        ( rs.getString    ( "email_bcc"         ) );
    setBccBuId    ( rs.getInt       ( "email_bcc_bu_id"   ) );
    setSubject    ( rs.getString    ( "email_subject"     ) );
    setMessage    ( rs.getString    ( "email_message"     ) );
    setAttachment ( rs.getString    ( "email_attachment"  ) );
    setStatusId   ( rs.getInt       ( "email_status_id"   ) );
    setLastUpdate ( rs.getTimestamp ( "email_last_update" ) );
    setCreated    ( rs.getTimestamp ( "email_created"     ) );
    }

    /**
     *
     */

    static public String key() { return "email"; }

    /**
     *
     */

    public int       getId         () { return email_id          ; }
    public String    getUuid       () { return email_uuid        ; }
    public int       getTypeId     () { return email_type_id     ; }
    public String    getFrom       () { return email_from        ; }
    public String    getPersonal   () { return email_personal    ; }
    public String    getTo         () { return email_to          ; }
    public int       getToBuId     () { return email_to_bu_id    ; }
    public String    getCc         () { return email_cc          ; }
    public int       getCcBuId     () { return email_cc_bu_id    ; }
    public String    getBcc        () { return email_bcc         ; }
    public int       getBccBuId    () { return email_bcc_bu_id   ; }
    public String    getSubject    () { return email_subject     ; }
    public String    getMessage    () { return email_message     ; }
    public String    getAttachment () { return email_attachment  ; }
    public int       getStatusId   () { return email_status_id   ; }
    public Timestamp getLastUpdate () { return email_last_update ; }
    public Timestamp getCreated    () { return email_created     ; }

    /**
     *
     */

    public void setId         ( int       email_id          ) { this.email_id          = email_id          ; }
    public void setUuid       ( String    email_uuid        ) { this.email_uuid        = email_uuid        ; }
    public void setTypeId     ( int       email_type_id     ) { this.email_type_id     = email_type_id     ; }
    public void setFrom       ( String    email_from        ) { this.email_from        = email_from        ; }
    public void setTo         ( String    email_to          ) { this.email_to          = email_to          ; }
    public void setPersonal   ( String    email_personal    ) { this.email_personal    = email_personal    ; }
    public void setToBuId     ( int       email_to_bu_id    ) { this.email_to_bu_id    = email_to_bu_id    ; }
    public void setCc         ( String    email_cc          ) { this.email_cc          = email_cc          ; }
    public void setCcBuId     ( int       email_cc_bu_id    ) { this.email_cc_bu_id    = email_cc_bu_id    ; }
    public void setBcc        ( String    email_bcc         ) { this.email_bcc         = email_bcc         ; }
    public void setBccBuId    ( int       email_bcc_bu_id   ) { this.email_bcc_bu_id   = email_bcc_bu_id   ; }
    public void setSubject    ( String    email_subject     ) { this.email_subject     = email_subject     ; }
    public void setMessage    ( String    email_message     ) { this.email_message     = email_message     ; }
    public void setAttachment ( String    email_attachment  ) { this.email_attachment  = email_attachment  ; }
    public void setStatusId   ( int       email_status_id   ) { this.email_status_id   = email_status_id   ; }
    public void setLastUpdate ( Timestamp email_last_update ) { this.email_last_update = email_last_update ; }
    public void setCreated    ( Timestamp email_created     ) { this.email_created     = email_created     ; }

}
