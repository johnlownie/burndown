package ca.jetsphere.core.tier0.backbone.audit;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class Audit extends ca.jetsphere.core.tier0.backbone.audit.foreign.Audit
{
    /**
     *
     */

    public Audit() { clear(); }

    /**
     *
     */

    public Audit ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getAuditDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getAuditDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getAuditDao().update ( jdbc, this ); }

}