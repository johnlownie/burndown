package ca.jetsphere.core.tier0.backbone.policy;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class Policy extends ca.jetsphere.core.tier0.backbone.policy.foreign.Policy
{
    /**
     *
     */

    public Policy() { clear(); }

    /**
     *
     */

    public Policy ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getPolicyDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getPolicyDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getPolicyDao().update ( jdbc, this ); }

}
