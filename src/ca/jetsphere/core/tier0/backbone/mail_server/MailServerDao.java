package ca.jetsphere.core.tier0.backbone.mail_server;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */

public class MailServerDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, MailServer mail_server ) throws Exception

    { return delete ( jdbc, mail_server, "delete from jet_base_mail_server where mail_server_id = " + mail_server.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
        MailServer server = ( MailServer ) bolt;

        String s = "insert into jet_base_mail_server "
                + "( mail_server_uuid, mail_server_company_id, mail_server_host, mail_server_type, mail_server_port, mail_server_login, mail_server_password, mail_server_tls, mail_server_ssl, mail_server_default, mail_server_last_update, mail_server_created )"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?)";

        ps.setStatement ( s, new String [] { "mail_server_id" } );

        server.setUuid ( UUID.get() ); server.setCreated ( new Timestamp( System.currentTimeMillis() ) ); server.setLastUpdate ( server.getCreated() );

        ps.setString    (  1, server.getUuid       () );
        ps.setInt       (  2, server.getCompanyId  () );
        ps.setString    (  3, server.getHost       () );
        ps.setInt       (  4, server.getType       () );
        ps.setInt       (  5, server.getPort       () );
        ps.setString    (  6, server.getLogin      () );
        ps.setString    (  7, server.getPassword   () );
        ps.setBoolean   (  8, server.getTls        () );
        ps.setBoolean   (  9, server.getSsl        () );
        ps.setBoolean   ( 10, server.getDefault    () );
        ps.setTimestamp ( 11, server.getLastUpdate () );
        ps.setTimestamp ( 12, server.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    MailServer server = ( MailServer ) bolt;

    String s = "update jet_base_mail_server "
            + "set mail_server_uuid = ?, mail_server_company_id = ?, mail_server_host = ?, mail_server_type = ?, mail_server_port = ?, mail_server_login = ?, mail_server_password = ?, mail_server_tls = ?, mail_server_ssl = ?, mail_server_default = ?, mail_server_last_update = ? "
            + "where mail_server_id = ?";

    ps.setStatement ( s );

    server.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, server.getUuid       () );
    ps.setInt       (  2, server.getCompanyId  () );
    ps.setString    (  3, server.getHost       () );
    ps.setInt       (  4, server.getType       () );
    ps.setInt       (  5, server.getPort       () );
    ps.setString    (  6, server.getLogin      () );
    ps.setString    (  7, server.getPassword() );
    ps.setBoolean   (  8, server.getTls        () );
    ps.setBoolean   (  9, server.getSsl        () );
    ps.setBoolean   ( 10, server.getDefault    () );
    ps.setTimestamp ( 11, server.getLastUpdate () );
    ps.setInt       ( 12, server.getId         () );
    }

}
