package ca.jetsphere.core.tier0.backbone.action;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */

public class ActionDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, Action action ) throws Exception

    { return delete ( jdbc, action, "delete from jet_base_action where action_id = " + action.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Action action = ( Action ) bolt;

    String s = "insert into jet_base_action "
            + "( action_uuid, action_company_id, action_name, action_href, action_tooltip, action_notes, action_public, action_last_update, action_created )"
            + "values (?,?,?,?,?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "action_id" } );

    action.setUuid ( UUID.get() ); action.setCreated ( new Timestamp( System.currentTimeMillis() ) ); action.setLastUpdate ( action.getCreated() );

    ps.setString    ( 1, action.getUuid       () );
    ps.setInt       ( 2, action.getCompanyId  () );
    ps.setString    ( 3, action.getName       () );
    ps.setString    ( 4, action.getHref       () );
    ps.setString    ( 5, action.getToolTip    () );
    ps.setString    ( 6, action.getNotes      () );
    ps.setBoolean   ( 7, action.getPublic     () );
    ps.setTimestamp ( 8, action.getLastUpdate () );
    ps.setTimestamp ( 9, action.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Action action = ( Action ) bolt;

    String s = "update jet_base_action "
            + "set action_uuid = ?, action_company_id = ?, action_name = ?, action_href = ?, action_tooltip = ?, action_notes = ?, action_public = ?, action_last_update = ? "
            + "where action_id = ?";

    ps.setStatement ( s );

    action.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    ( 1, action.getUuid       () );
    ps.setInt       ( 2, action.getCompanyId  () );
    ps.setString    ( 3, action.getName       () );
    ps.setString    ( 4, action.getHref       () );
    ps.setString    ( 5, action.getToolTip    () );
    ps.setString    ( 6, action.getNotes      () );
    ps.setBoolean   ( 7, action.getPublic     () );
    ps.setTimestamp ( 8, action.getLastUpdate () );
    ps.setInt       ( 9, action.getId         () );
    }

}
