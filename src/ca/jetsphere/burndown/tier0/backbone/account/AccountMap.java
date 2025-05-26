package ca.jetsphere.burndown.tier0.backbone.account;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class AccountMap extends BoltMap
{
    /**
     *
     */
    public AccountMap() { super(); }

    /**
     *
     */
    public AccountMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */
    public void find ( JDBC jdbc, int application_id )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select jet_burndown_account.* from jet_burndown_account where account_application_id = " + application_id );

    query ( jdbc, sb.toString() );
    }

    /**
     *
     */
    public String getKey() { return Account.key (); }
}
