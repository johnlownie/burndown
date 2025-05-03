package ca.jetsphere.core.tier0.backbone.audit;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */

public class AuditDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, Audit audit ) throws Exception

    { return delete ( jdbc, audit, "delete from jet_base_audit where audit_id = " + audit.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Audit audit = ( Audit ) bolt;

    String s = "insert into jet_base_audit "
            + "( audit_uuid, audit_member_id, audit_ip, audit_session_id, audit_path, audit_query_string, audit_method, audit_errors, audit_start, audit_stop, audit_elapsed, audit_last_update, audit_created )"
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "audit_id" } );

    audit.setUuid ( UUID.get() ); audit.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); audit.setLastUpdate ( audit.getCreated() );

    ps.setString    (  1, audit.getUuid        () );
    ps.setInt       (  2, audit.getMemberId    () );
    ps.setString    (  3, audit.getIp          () );
    ps.setString    (  4, audit.getSessionId   () );
    ps.setString    (  5, audit.getPath        () );
    ps.setString    (  6, audit.getQueryString () );
    ps.setString    (  7, audit.getMethod      () );
    ps.setString    (  8, audit.getErrors      () );
    ps.setTimestamp (  9, audit.getStart       () );
    ps.setTimestamp ( 10, audit.getStop        () );
    ps.setLong      ( 11, audit.getElapsed     () );
    ps.setTimestamp ( 12, audit.getLastUpdate  () );
    ps.setTimestamp ( 13, audit.getCreated     () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception {}

}
