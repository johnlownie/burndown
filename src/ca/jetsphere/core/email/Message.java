package ca.jetsphere.core.email;

import ca.jetsphere.core.common.DockYard;
import org.apache.struts.action.ActionForm;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 */

public class Message extends ActionForm
{
    List recipients;

    String message_sender    ;
    String message_signature ;
    String message_subject   ;
    String message_text      ;
    String message_to        ;

    /**
     *
     */

    public Message() { setRecipients ( new ArrayList() ); clear(); }

    /**
     *
     */

    public void addRecipient ( String recipient )  { recipients.add ( recipient ); }

    /**
     *
     */

    public void clear()
    {
    recipients.clear();
    setSender    ( "" );
    setSignature ( "" );
    setSubject   ( "" );
    setText      ( "" );
    setTo        ( "" );
    }

    /**
     *
     */

    public void clearRecipients() { recipients.clear(); }

    /**
     *
     */

    public List getRecipients() { return recipients; }

    /**
     *
     */

    public String getRecipientsAsText()
    {
    StringBuilder sb = new StringBuilder();

    for ( int cc = 0; cc < recipients.size(); cc++ )

    { if ( cc > 0 ) sb.append ( "; " ); sb.append ( recipients.get ( cc ) ); }

    return sb.toString();
    }

    /**
     *
     */

    public String getSender    () { return message_sender    ; }
    public String getSignature () { return message_signature ; }
    public String getSubject   () { return message_subject   ; }
    public String getText      () { return message_text      ; }
    public String getTo        () { return message_to        ; }

    /**
     *
     */

    public boolean isEmpty() { return recipients.isEmpty(); }

    /**
     *
     */

    public void replace ( String regex, String replacement )

    { if ( !DockYard.isWhiteSpace ( getText() ) ) setText ( getText().replaceAll ( regex, replacement ) ); }

    /**
     *
     */

    public void setRecipients()
    {
    StringTokenizer st = new StringTokenizer ( getTo (), ";" );

    while ( st.hasMoreTokens() )

    { String to = DockYard.trimAll ( st.nextToken() ); addRecipient ( to ); }

    }

    /**
     *
     */

    public void setRecipients ( List recipients )  { this.recipients = recipients; }

    /**
     *
     */

    public void setSender    ( String message_sender    ) { this.message_sender    = message_sender    ; }
    public void setSignature ( String message_signature ) { this.message_signature = message_signature ; }
    public void setSubject   ( String message_subject   ) { this.message_subject   = message_subject   ; }
    public void setText      ( String message_text      ) { this.message_text      = message_text      ; }
    public void setTo        ( String message_to        ) { this.message_to        = message_to        ; }

}