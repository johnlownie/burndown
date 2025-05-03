package ca.jetsphere.core.tier1.backbone.mail_server;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.mail_server.common.Type;

/**
 *
 */

public class MailServerYard
{
    /**
     *
     */

    static public MailServer getDefaultPop3 ( JDBC jdbc )
    {
    MailServer mailServer = new MailServer();

    String query = "select * from jet_base_mail_server where mail_server_default and mail_server_type = " + Type.POP;

    mailServer.query ( jdbc, query );

    return mailServer;
    }

    /**
     *
     */

    static public MailServer getDefaultSmtp ( JDBC jdbc )
    {
    MailServer mailServer = new MailServer();

    String query = "select * from jet_base_mail_server where mail_server_default and mail_server_type = " + Type.SMTP;

    mailServer.query ( jdbc, query );

    return mailServer;
    }

    /**
     *
     */

    static public MailServer getPop3ByLogin ( JDBC jdbc, String login )
    {
    MailServer mailServer = new MailServer();

    String query = "select * from jet_base_mail_server where mail_server_login = " + DockYard.quote ( login ) + " and mail_server_type = " + Type.POP;

    mailServer.query ( jdbc, query );

    return mailServer;
    }

}
