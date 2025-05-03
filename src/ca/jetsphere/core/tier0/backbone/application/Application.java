package ca.jetsphere.core.tier0.backbone.application;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class Application extends ca.jetsphere.core.tier0.backbone.application.foreign.Application
{
    /**
     *
     */

    public Application() { clear(); }

    /**
     *
     */

    public Application ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getApplicationDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getApplicationDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getApplicationDao().update ( jdbc, this ); }

}