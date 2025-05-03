package ca.jetsphere.core.tier0.backbone.email;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */

public class EmailDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, Email email ) throws Exception

    { return delete ( jdbc, email, "delete from jet_base_email where email_id = " + email.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Email email = ( Email ) bolt;

    String s = "insert into jet_base_email "
            + "( email_uuid, email_type_id, email_from, email_personal, email_to, email_to_bu_id, email_cc, email_cc_bu_id, email_bcc, email_bcc_bu_id, email_subject, email_message, email_attachment, email_status_id, email_last_update, email_created )"
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "email_id" } );

    email.setUuid ( UUID.get() ); email.setCreated ( new Timestamp( System.currentTimeMillis() ) ); email.setLastUpdate ( email.getCreated() );

    ps.setString    (  1, email.getUuid       () );
    ps.setInt       (  2, email.getTypeId     () );
    ps.setString    (  3, email.getFrom       () );
    ps.setString    (  4, email.getPersonal   () );
    ps.setString    (  5, email.getTo         () );
    ps.setInt       (  6, email.getToBuId     () );
    ps.setString    (  7, email.getCc         () );
    ps.setInt       (  8, email.getCcBuId     () );
    ps.setString    (  9, email.getBcc        () );
    ps.setInt       ( 10, email.getBccBuId    () );
    ps.setString    ( 11, email.getSubject    () );
    ps.setString    ( 12, email.getMessage    () );
    ps.setString    ( 13, email.getAttachment () );
    ps.setInt       ( 14, email.getStatusId   () );
    ps.setTimestamp ( 15, email.getLastUpdate () );
    ps.setTimestamp ( 16, email.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Email email = ( Email ) bolt;

    String s = "update jet_base_email "
            + "set email_uuid = ?, email_type_id = ?, email_from = ?, email_personal = ?, email_to = ?, email_to_bu_id = ?, email_cc = ?, email_cc_bu_id = ?, email_bcc = ?, email_bcc_bu_id = ?, email_subject = ?, email_message = ?, email_attachment = ?, email_status_id = ?, email_last_update = ? "
            + "where email_id = ?";

    ps.setStatement ( s );

    email.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, email.getUuid       () );
    ps.setInt       (  2, email.getTypeId     () );
    ps.setString    (  3, email.getFrom       () );
    ps.setString    (  4, email.getPersonal   () );
    ps.setString    (  5, email.getTo         () );
    ps.setInt       (  6, email.getToBuId     () );
    ps.setString    (  7, email.getCc         () );
    ps.setInt       (  8, email.getCcBuId     () );
    ps.setString    (  9, email.getBcc        () );
    ps.setInt       ( 10, email.getBccBuId    () );
    ps.setString    ( 11, email.getSubject    () );
    ps.setString    ( 12, email.getMessage    () );
    ps.setString    ( 13, email.getAttachment () );
    ps.setInt       ( 14, email.getStatusId   () );
    ps.setTimestamp ( 15, email.getLastUpdate () );
    ps.setInt       ( 16, email.getId         () );
    }

}
