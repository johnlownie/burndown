package ca.jetsphere.core.tier0.backbone.audit;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class AuditMap extends BoltMap
{
    /**
     *
     */

    public AuditMap() { super(); }

    /**
     *
     */

    public AuditMap ( JDBC jdbc, int user_id ) { query ( jdbc, user_id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int user_id )
    {
    String query = "select * from jet_base_audit";

    if ( user_id > 0 ) query += " where audit_member_id = " + user_id;

    query ( jdbc, query );
    }

    /**
     *
     */

    public String getKey() { return Audit.key(); }

}
