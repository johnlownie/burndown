package ca.jetsphere.core.tier1.backbone.mail_server;

import ca.jetsphere.core.jdbc.JDBC;

import java.sql.ResultSet;

/**
 *
 */

public class MailServer extends ca.jetsphere.core.tier0.backbone.mail_server.MailServer
{
    /**
     *
     */

    public MailServer() { super(); }

    /**
     *
     */

    public MailServer ( JDBC jdbc )

    { super(); query ( jdbc, "select * from jet_base_mail_server where mail_server_default"); }

    /**
     *
     */

    public MailServer ( JDBC jdbc, int mail_server_id ) throws Exception { super ( jdbc, mail_server_id ); }

    /**
     *
     */

    public MailServer ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions()

    { return new String [] { "mail.server", "mail.type", "mail.port", "login", "mail.tls", "mail.ssl", "last-update", "actions" }; }

    /**
     *
     */

    static public String [] fields()

    { return new String [] { "mail_server_host", "mail_server_type", "mail_server_port", "mail_server_login", "mail_server_tls", "mail_server_ssl", "mail_server_last_update", "mail_server_uuid" }; }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public String getName() { return getHost(); }

}
