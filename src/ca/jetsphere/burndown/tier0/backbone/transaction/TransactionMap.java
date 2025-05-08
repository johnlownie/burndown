package ca.jetsphere.burndown.tier0.backbone.transaction;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;

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
    public void find ( JDBC jdbc, int period_id, int type_id, int category_id )
    {
    String query = "select jet_burndown_transaction.* from jet_burndown_transaction inner join jet_burndown_category on category_id = transaction_category_id";

    if ( period_id > 0 ) query += " where transaction_period_id = " + period_id;

    if ( type_id > 0 ) query += " and transaction_type = " + type_id;
    
    if ( category_id > 0 ) query += " and (category_id = " + category_id + " or category_lineage like '%/" + DockYard.zeroPad ( category_id, 2 ) + "/%')";

    query ( jdbc, query );
    }

    /**
     *
     */
    public String getKey() { return Transaction.key (); }
}
