package ca.jetsphere.core.tier1.backbone.policy;

import java.sql.ResultSet;

import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

public class Policy extends ca.jetsphere.core.tier0.backbone.policy.Policy
{
    /**
     *
     */

    public Policy() { super(); clear(); }

    /**
     *
     */

    public Policy ( JDBC jdbc, int policy_id ) throws Exception { super ( jdbc, policy_id ); }

    /**
     *
     */

    public Policy ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "name", "value", "last.update", "actions" }; }

    /**
     *
     */

    static public String [] fields()

    { return new String [] { "policy_name", "policy_value", "policy_last_update", "policy_uuid" }; }

}
