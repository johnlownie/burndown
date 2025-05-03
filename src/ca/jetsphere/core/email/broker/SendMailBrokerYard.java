package ca.jetsphere.core.email.broker;

import ca.jetsphere.core.email.FileAttachmentList;

import javax.servlet.ServletContext;
import java.util.List;

/**
 *
 */

public class SendMailBrokerYard
{
    /**
     *
     */

    static public boolean send ( ServletContext servletContext, SendMailQueuedMessage message )
    {
    SendMailBroker broker = ( SendMailBroker ) servletContext.getAttribute ( "SendMail Broker" );

    if ( broker != null ) broker.getQueue().push ( message );

    return broker != null;
    }

    /**
     *
     */

    static public boolean send ( ServletContext servletContext, String from, List to, String subject, String message )
    {
    SendMailBroker broker = ( SendMailBroker ) servletContext.getAttribute ( "SendMail Broker" );

    if ( broker != null ) broker.getQueue().push ( new SendMailQueuedMessage ( from, to, subject, message ) );

    return broker != null;
    }

    /**
     *
     */

    static public void send ( ServletContext servletContext, String from, List to, List cc, List bcc, String personal, String subject, String message, FileAttachmentList list )
    {
    SendMailBroker broker = ( SendMailBroker ) servletContext.getAttribute ( "SendMail Broker" );

    if ( broker != null ) broker.getQueue().push ( new SendMailQueuedMessage ( from, personal, to, cc, bcc, subject, message, list ) );
    }

}
