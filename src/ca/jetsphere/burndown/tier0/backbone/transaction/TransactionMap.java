package ca.jetsphere.burndown.tier0.backbone.transaction;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;
import java.sql.ResultSet;

/**
 *
 */

abstract public class TransactionMap extends BoltMap
{
    /**
     *
     */
    public TransactionMap() { super(); }

    /**
     *
     */
    public TransactionMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */
    public void find ( JDBC jdbc, int period_id )
    {
    String query = "select jet_burndown_transaction.* from jet_burndown_transaction";

    if ( period_id > 0 ) query += " where transaction_period_id = " + period_id;

    query ( jdbc, query );
    }

    /**
     *
     */
    public String getKey() { return Transaction.key (); }
}
