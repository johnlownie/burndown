package ca.jetsphere.core.email;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Message;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 */

public class SendEmail
{
    SmtpConnection connection; List to; List cc; List bcc; String from, signature, subject;

    /**
     *
     */

    private SendEmail() { setConnection ( new SmtpConnection() ); setTo ( new ArrayList() ); setCc ( new ArrayList() ); setBcc ( new ArrayList() ); }

    /**
     *
     */

    public SendEmail ( String from, String to, String subject ) { this ( from, from, to, subject ); }

    /**
     *
     */

    public SendEmail ( String from, List to, String subject ) { this ( from, from, to, subject ); }

    /**
     *
     */

    public SendEmail ( String from, String signature, List to, String subject )

    { this(); setFrom ( from ); setSignature ( signature ); setTo ( to ); setSubject ( subject ); }

    /**
     *
     */

    public SendEmail ( String from, String signature, List to, List cc, List bcc, String subject )

    { this(); setFrom ( from ); setSignature ( signature ); setTo ( to ); setCc ( cc ); setBcc ( bcc ); setSubject ( subject ); }

    /**
     *
     */

    public SendEmail ( String from, String signature, String to, String subject )

    { this(); setFrom ( from ); setSignature ( signature ); setTo ( to ); setSubject ( subject ); }

    /**
     *
     */

    public void addBcc ( Message message, String recipient ) throws Exception

    { message.addRecipient ( Message.RecipientType.BCC, new InternetAddress ( recipient ) ); }

    /**
     *
     */

    public void addCc ( Message message, String recipient ) throws Exception

    { message.addRecipient ( Message.RecipientType.CC, new InternetAddress ( recipient ) ); }

    /**
     *
     */

    public void addTo ( Message message, String recipient ) throws Exception

    { message.addRecipient ( Message.RecipientType.TO, new InternetAddress ( recipient ) ); }

    /**
     *
     */

    public void addRecipients ( Message message ) throws Exception
    {
    Iterator it = getTo().iterator(); while ( it.hasNext() ) addTo ( message, ( String ) it.next() );

    it = getCc().iterator(); while ( it.hasNext() ) addCc ( message, ( String ) it.next() );
    }

    /**
     *
     */

    public void forward ( Message message, List recipients ) throws Exception

    { message.setRecipients ( Message.RecipientType.TO, ( Address [] ) null ); setTo ( recipients ); addRecipients ( message ); send ( message ); }

    /**
     *
     */

    public String getAddresses ( Message message ) throws Exception
    {
    StringBuilder sb = new StringBuilder();

    Address[] address = message.getAllRecipients();

    if ( address == null ) return "";

    for ( int cc = 0; cc < address.length; cc ++ ) sb.append ( address [ cc ].toString() ).append ( " " );

    return sb.toString();
    }

    /**
     *
     */

    public MimeBodyPart getFileBodyPart ( String filename, String filePath ) throws Exception
    {
    MimeBodyPart mbp = new MimeBodyPart();

    FileDataSource fds = new FileDataSource ( filePath );

    mbp.setFileName ( filename ); mbp.setDataHandler ( new DataHandler( fds ) );

    return mbp;
    }

    /**
     *
     */

    public MimeMessage getMessage() throws Exception
    {
    MimeMessage message = new MimeMessage ( connection.getSession() );

    if ( DockYard.isWhiteSpace ( getSignature() ) )

        message.setFrom ( new InternetAddress ( getFrom(), false ) );

    else

        message.setFrom ( new InternetAddress ( getFrom(), getSignature() ) );

    addRecipients ( message ); message.setSubject ( getSubject(), "UTF-8" ); message.saveChanges();

    return message;
    }

    /**
     *
     */

    public MimeMessage getMessage ( String text, FileAttachmentList list, boolean html ) throws Exception
    {
    MimeMultipart mp = new MimeMultipart();

    mp.addBodyPart ( getTextBodyPart ( text, html ) );

    list.addTo ( mp );

    MimeMessage message = getMessage();

    message.setContent ( mp ); message.setSentDate ( new Date() ); message.saveChanges();

    return message;
    }

    /**
     *
     */

    public MimeMessage getMimeMessage() { return new MimeMessage ( connection.getSession() ); }

    /**
     *
     */

    public MimeBodyPart getTextBodyPart ( String text, boolean html ) throws Exception
    {
    MimeBodyPart mps = new MimeBodyPart();

    if ( html ) mps.setContent ( text, "text/html; charset=utf-8" ); else mps.setText ( text, "UTF-8" );

    return mps;
    }

    /**
     *
     */

    public void send ( ca.jetsphere.core.email.Message message ) throws Exception
    {
    if ( DockYard.isWhiteSpace ( getFrom() ) ) setFrom ( message.getSender() );

    send ( message.getText() );
    }

    /**
     *
     */

    public void send ( String text, boolean html ) throws Exception
    {
    MimeMultipart mp = new MimeMultipart();

    mp.addBodyPart ( getTextBodyPart ( text, html ) );

    MimeMessage message = getMessage();

    message.setContent ( mp ); message.setSentDate ( new Date() );

    if ( html ) message.setContent ( text, "text/html; charset=utf-8" ); else message.setText ( text, "UTF-8" );

    message.saveChanges();

    send ( message );
    }

    /**
     *
     */

    public void send ( String text ) throws Exception { send ( text, false ); }

    /**
     *
     */

    public void send ( String text, FileAttachmentList list ) throws Exception { send ( text, list, false ); }

    /**
     *
     */

    public void send ( String text, FileAttachmentList list, boolean html ) throws Exception
    {
    MimeMultipart mp = new MimeMultipart();

    mp.addBodyPart ( getTextBodyPart ( text, html ) );

    list.addTo ( mp );

    MimeMessage message = getMessage();

    message.setContent ( mp ); message.setSentDate ( new Date() ); message.saveChanges();

    send ( message );
    }

    /**
     *
     */

    public void send ( Message message ) throws Exception

    { if ( getBcc().size() > 0 ) sendBatch ( message ); else sendSingle ( message ); }

    /**
     *
     */

    public void sendBatch ( Message message ) throws Exception
    {
    for ( int cc = 0; cc < getBcc().size(); cc += 25 )
    {
    List<String> sub = getBcc().subList ( cc, Math.min ( getBcc().size() ,cc + 25 ) );

    message.setRecipients ( Message.RecipientType.BCC, null );

    for ( String s: sub )  addBcc ( message, s );

    try { Transport.send ( message ); } catch ( Exception e ) { Common.trace ( e ); throw ( e ); } finally { trace ( message ); }
    } }

    /**
     *
     */

    public void sendSingle ( Message message ) throws Exception

    { try { Transport.send ( message ); } catch ( Exception e ) { Common.trace ( e ); throw ( e ); } finally { trace ( message ); } }

    /**
     *
     */

    public void setTo ( String to ) { List list = new ArrayList(); list.add ( to ); setTo ( list ); }


    /**
     *
     */

    public void trace ( Message message ) throws Exception

    { Common.info ( "E-MAIL [ TO ] " + getAddresses ( message ) + " [ SUBJECT ] " + message.getSubject() ); }

    /**
     *
     */

    public List   getBcc       () { return bcc       ; }
    public List   getCc        () { return cc        ; }
    public String getFrom      () { return from      ; }
    public String getSignature () { return signature ; }
    public String getSubject   () { return subject   ; }
    public List   getTo        () { return to        ; }

    /**
     *
     */

    public void setBcc        ( List           bcc        ) { this.bcc        = bcc        ; }
    public void setCc         ( List           cc         ) { this.cc         = cc         ; }
    public void setConnection ( SmtpConnection connection ) { this.connection = connection ; }
    public void setFrom       ( String         from       ) { this.from       = from       ; }
    public void setSignature  ( String         signature  ) { this.signature  = signature  ; }
    public void setSubject    ( String         subject    ) { this.subject    = subject    ; }
    public void setTo         ( List           to         ) { this.to         = to         ; }

}
