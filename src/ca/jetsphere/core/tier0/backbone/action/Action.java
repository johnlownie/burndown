package ca.jetsphere.core.tier0.backbone.action;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class Action extends ca.jetsphere.core.tier0.backbone.action.foreign.Action
{
    /**
     *
     */

    public Action() { clear(); }

    /**
     *
     */

    public Action ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getActionDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getActionDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getActionDao().update ( jdbc, this ); }

}
