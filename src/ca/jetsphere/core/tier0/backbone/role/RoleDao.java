package ca.jetsphere.core.tier0.backbone.role;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *.
 */

public class RoleDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, Role role ) throws Exception

    { return delete ( jdbc, role, "delete from jet_base_role where role_id = " + role.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Role role = ( Role ) bolt;

    String s = "insert into jet_base_role "
            + "( role_uuid, role_company_id, role_type, role_alias, role_name, role_notes, role_read_only, role_closure, role_ordinal, role_last_update, role_created )"
            + "values (?,?,?,?,?,?,?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "role_id" } );

    role.setUuid ( UUID.get() ); role.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); role.setLastUpdate ( role.getCreated() );

    ps.setString    (  1, role.getUuid       () );
    ps.setInt       (  2, role.getCompanyId  () );
    ps.setInt       (  3, role.getType       () );
    ps.setString    (  4, role.getAlias      () );
    ps.setString    (  5, role.getName       () );
    ps.setString    (  6, role.getNotes      () );
    ps.setBoolean   (  7, role.getReadOnly   () );
    ps.setInt       (  8, role.getClosure    () );
    ps.setInt       (  9, role.getOrdinal    () );
    ps.setTimestamp ( 10, role.getLastUpdate () );
    ps.setTimestamp ( 11, role.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Role role = ( Role ) bolt;

    String s = "update jet_base_role "
            + "set role_uuid = ?, role_company_id = ?, role_type = ?, role_alias = ?, role_name = ?, role_notes = ?, role_read_only = ?, role_closure = ?, role_ordinal = ?, role_last_update = ? "
            + "where role_id = ?";

    ps.setStatement ( s );

    role.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, role.getUuid       () );
    ps.setInt       (  2, role.getCompanyId  () );
    ps.setInt       (  3, role.getType       () );
    ps.setString    (  4, role.getAlias      () );
    ps.setString    (  5, role.getName       () );
    ps.setString    (  6, role.getNotes      () );
    ps.setBoolean   (  7, role.getReadOnly   () );
    ps.setInt       (  8, role.getClosure    () );
    ps.setInt       (  9, role.getOrdinal    () );
    ps.setTimestamp ( 10, role.getLastUpdate () );
    ps.setInt       ( 11, role.getId         () );
    }

}
