package ca.jetsphere.core.email.broker;

import ca.jetsphere.core.email.FileAttachmentList;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class SendMailQueuedMessage
{
    String             smqm_from      ;
    List               smqm_to        ;
    List               smqm_cc        ;
    List               smqm_bcc       ;
    String             smqm_personal  ;
    String             smqm_subject   ;
    String             smqm_message   ;
    FileAttachmentList smqm_file_list ;

    /**
     *
     */

    public SendMailQueuedMessage() { clear(); }

    /**
     *
     */

    public SendMailQueuedMessage ( String from, List to, String subject, String message )

    { this(); setFrom ( from ); setTo ( to ); setSubject ( subject ); setMessage ( message ); }

    /**
     *
     */

    public SendMailQueuedMessage ( String from, String personal, List to, List cc, List bcc, String subject, String message, FileAttachmentList list )

    { this ( from, to, subject, message ); setCc ( cc ); setBcc ( bcc ); setPersonal ( personal ); setFileList ( list ); }

    /**
     *
     */

    public void clear()
    {
    setFrom     ( "" );
    setTo       ( new ArrayList() );
    setCc       ( new ArrayList() );
    setBcc      ( new ArrayList() );
    setPersonal ( "" );
    setSubject  ( "" );
    setMessage  ( "" );
    setFileList ( null );
    }

    /**
     *
     */

    public String             getFrom    () { return smqm_from      ; }
    public String             getPersonal() { return smqm_personal  ; }
    public List               getTo      () { return smqm_to        ; }
    public List               getCc      () { return smqm_cc        ; }
    public List               getBcc     () { return smqm_bcc       ; }
    public String             getSubject () { return smqm_subject   ; }
    public String             getMessage () { return smqm_message   ; }
    public FileAttachmentList getFileList() { return smqm_file_list ; }

    /**
     *
     */

    public void setFrom     ( String             smqm_from      ) { this.smqm_from      = smqm_from      ; }
    public void setPersonal ( String             smqm_personal  ) { this.smqm_personal  = smqm_personal  ; }
    public void setTo       ( List               smqm_to        ) { this.smqm_to        = smqm_to        ; }
    public void setCc       ( List               smqm_cc        ) { this.smqm_cc        = smqm_cc        ; }
    public void setBcc      ( List               smqm_bcc       ) { this.smqm_bcc       = smqm_bcc       ; }
    public void setSubject  ( String             smqm_subject   ) { this.smqm_subject   = smqm_subject   ; }
    public void setMessage  ( String             smqm_message   ) { this.smqm_message   = smqm_message   ; }
    public void setFileList ( FileAttachmentList smqm_file_list ) { this.smqm_file_list = smqm_file_list ; }

}
