package ca.jetsphere.core.tier0.backbone.mail_server;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class MailServer extends ca.jetsphere.core.tier0.backbone.mail_server.foreign.MailServer
{
    /**
     *
     */

    public MailServer() { clear(); }

    /**
     *
     */

    public MailServer ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getMailServerDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getMailServerDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getMailServerDao().update ( jdbc, this ); }

}
