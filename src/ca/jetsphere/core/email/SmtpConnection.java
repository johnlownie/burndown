package ca.jetsphere.core.email;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.mail_server.MailServer;
import ca.jetsphere.core.tier1.backbone.mail_server.MailServerYard;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 *
 */

public class SmtpConnection
{
    Session session; MailServer server;

    /**
     *
     */

    public SmtpConnection()
    {
    JDBC jdbc = new JDBC();

    try {

        this.server = MailServerYard.getDefaultSmtp ( jdbc );

        setSession ( getSession ( getProperties() ) );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    class SmtpAuthenticator extends javax.mail.Authenticator

    { protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication ( server.getLogin(), server.getPassword() ); } }

    /**
     *
     */

    public Properties getProperties()
    {
    Properties properties = new Properties();

    properties.put ( "mail.smtp.host"          , server.getHost() );
    properties.put ( "mail.smtp.port"          , "" + server.getPort() );
    properties.put ( "mail.transport.protocol" , "smtp"  );
    properties.put ( "mail.smtp.auth"          , "false" );
    properties.put ( "mail.debug"              , "false" );

    if ( ! DockYard.isWhiteSpace ( server.getLogin() ) )
    {
    properties.put ( "mail.user", server.getLogin() );
    properties.put ( "mail.smtp.auth", "true" );
    }

    if ( server.getTls() )
    {
    properties.put ( "mail.transport.protocol"          , "smtp"  );
    properties.put ( "mail.smtp.socketFactory.port"     , "" + server.getPort() );
    properties.put ( "mail.smtp.socketFactory.fallback" , "false" );
    properties.put ( "mail.smtp.starttls.enable"        , "true"  );
    }

    if ( server.getSsl() )
    {
    properties.put ( "mail.smtp.ssl", "true" );
    properties.put ( "mail.smtp.socketFactory.class"    , "javax.net.ssl.SSLSocketFactory" );
    }

    trace ( server, properties );

    return properties;
    }

    /**
     *
     */

    public Session getSession() { return session; }

    /**
     *
     */

    public Session getSession ( Properties properties )

    { return ! DockYard.isWhiteSpace ( server.getLogin() ) ? Session.getInstance ( properties, new SmtpAuthenticator() ) : Session.getDefaultInstance ( properties, null ); }

    /**
     *
     */

    public void setSession ( Session session  ) { this.session = session ; }

    /**
     *
     */

    public void trace ( MailServer server, Properties properties )
    {
        Common.debug ( "[ server.id   ] " + server.getId   () );
        Common.debug ( "[ server.host ] " + server.getHost () );
        Common.debug ( "[ server.type ] " + server.getType () );
        Common.debug ( "[ server.port ] " + server.getPort () );

        Common.debug ( "[ mail.smtp.host          ] " + properties.get ( "mail.smtp.host"          ) );
        Common.debug ( "[ mail.smtp.port          ] " + properties.get ( "mail.smtp.port"          ) );
        Common.debug ( "[ mail.transport.protocol ] " + properties.get ( "mail.transport.protocol" ) );
        Common.debug ( "[ mail.smtp.auth          ] " + properties.get ( "mail.smtp.auth"          ) );
        Common.debug ( "[ mail.debug              ] " + properties.get ( "mail.debug"              ) );
    }

}
