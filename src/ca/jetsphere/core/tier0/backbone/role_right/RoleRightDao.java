package ca.jetsphere.core.tier0.backbone.role_right;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */

public class RoleRightDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, RoleRight right ) throws Exception

    { return delete ( jdbc, right, "delete from jet_base_role_right where role_right_id = " + right.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    RoleRight right = ( RoleRight ) bolt;

    String s = "insert into jet_base_role_right "
            + "( role_right_uuid, role_right_role_id, role_right_parent_id, role_right_parent_uuid, role_right_depth, role_right_lineage, role_right_ordinal, role_right_name, role_right_action_id, role_right_hidden, role_right_notes, role_right_last_update, role_right_created )"
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "role_right_id" } );

    right.setUuid ( UUID.get() ); right.setCreated ( new Timestamp( System.currentTimeMillis() ) ); right.setLastUpdate ( right.getCreated() );

    ps.setString    (  1, right.getUuid       () );
    ps.setInt       (  2, right.getRoleId     () );
    ps.setInt       (  3, right.getParentId   () );
    ps.setString    (  4, right.getParentUuid () );
    ps.setInt       (  5, right.getDepth      () );
    ps.setString    (  6, right.getLineage    () );
    ps.setInt       (  7, right.getOrdinal    () );
    ps.setString    (  8, right.getName       () );
    ps.setInt       (  9, right.getActionId   () );
    ps.setBoolean   ( 10, right.getHidden     () );
    ps.setString    ( 11, right.getNotes      () );
    ps.setTimestamp ( 12, right.getLastUpdate () );
    ps.setTimestamp ( 13, right.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    RoleRight right = ( RoleRight ) bolt;

    String s = "update jet_base_role_right "
            + "set role_right_uuid =?, role_right_role_id = ?, role_right_parent_id = ?, role_right_parent_uuid = ?, role_right_depth = ?, role_right_lineage = ?, role_right_ordinal = ?, role_right_name = ?, role_right_action_id = ?, role_right_hidden = ?, role_right_notes = ?, role_right_last_update = ? "
            + "where role_right_id = ?";

    ps.setStatement ( s );

    right.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, right.getUuid       () );
    ps.setInt       (  2, right.getRoleId     () );
    ps.setInt       (  3, right.getParentId   () );
    ps.setString    (  4, right.getParentUuid () );
    ps.setInt       (  5, right.getDepth      () );
    ps.setString    (  6, right.getLineage    () );
    ps.setInt       (  7, right.getOrdinal    () );
    ps.setString    (  8, right.getName       () );
    ps.setInt       (  9, right.getActionId   () );
    ps.setBoolean   ( 10, right.getHidden     () );
    ps.setString    ( 11, right.getNotes      () );
    ps.setTimestamp ( 12, right.getLastUpdate () );
    ps.setInt       ( 13, right.getId         () );
    }

}
