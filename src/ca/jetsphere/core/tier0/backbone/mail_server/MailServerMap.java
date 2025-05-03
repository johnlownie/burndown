package ca.jetsphere.core.tier0.backbone.mail_server;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class MailServerMap extends BoltMap
{
    /**
     *
     */

    public MailServerMap() { super(); }

    /**
     *
     */

    public MailServerMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int company_id )

    { query ( jdbc, "select * from jet_base_mail_server" + ( company_id > 0 ? " where mail_server_company_id = " + company_id : "" ) ); }

    /**
     *
     */

    public MailServer getMailServer ( int id ) { return ( MailServer ) get ( id ); }

    /**
     *
     */

    public String getKey() { return MailServer.key(); }

}
