package ca.jetsphere.core.tier0.backbone.role;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class RoleMap extends BoltMap
{
    /**
     *
     */

    public RoleMap() { super(); }

    /**
     *
     */

    public RoleMap ( JDBC jdbc, int id ) throws Exception { query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int company_id )
    {
    String query = "select jet_base_role.* from jet_base_role";

    if ( company_id > 0 ) query += " where role_company_id = " + company_id;

    query ( jdbc, query );
    }

    /**
     *
     */

    public String getKey() { return Role.key(); }

    /**
     *
     */

    public Role getRole ( int id ) { return ( Role ) get ( id ); }

}
