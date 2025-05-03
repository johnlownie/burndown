package ca.jetsphere.core.tier0.backbone.policy;

import java.sql.Timestamp;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

/**
 *
 */

public class PolicyDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, Policy policy ) throws Exception

    { return delete ( jdbc, policy, "delete from jet_base_policy where policy_id = " + policy.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Policy policy = ( Policy ) bolt;

    String s = "insert into jet_base_policy "
             + "( policy_uuid, policy_company_id, policy_name, policy_value, policy_last_update, policy_created )"
             + "values ( ?, ?, ?, ?, ?, ? )";

    ps.setStatement ( s, new String [] { "policy_id" } );

    policy.setUuid ( UUID.get() ); policy.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); policy.setLastUpdate ( policy.getCreated() );

    ps.setString    ( 1, policy.getUuid       () );
    ps.setInt       ( 2, policy.getCompanyId  () );
    ps.setString    ( 3, policy.getName       () );
    ps.setInt       ( 4, policy.getValue      () );
    ps.setTimestamp ( 5, policy.getLastUpdate () );
    ps.setTimestamp ( 6, policy.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Policy policy = ( Policy ) bolt;

    String s = "update jet_base_policy "
              + "set policy_uuid = ?, policy_company_id = ?, policy_name = ?, policy_value = ?, policy_last_update = ? "
              + "where policy_id = ?";

    ps.setStatement ( s );

    policy.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    ( 1, policy.getUuid       () );
    ps.setInt       ( 2, policy.getCompanyId  () );
    ps.setString    ( 3, policy.getName       () );
    ps.setInt       ( 4, policy.getValue      () );
    ps.setTimestamp ( 5, policy.getLastUpdate () );
    ps.setInt       ( 6, policy.getId         () );
    }

}
