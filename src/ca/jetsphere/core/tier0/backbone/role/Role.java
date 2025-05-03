package ca.jetsphere.core.tier0.backbone.role;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class Role extends ca.jetsphere.core.tier0.backbone.role.foreign.Role
{
    /**
     *
     */

    public Role() { clear(); }

    /**
     *
     */

    public Role ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getRoleDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getRoleDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getRoleDao().update ( jdbc, this ); }

}
