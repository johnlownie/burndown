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
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select jet_burndown_transaction.* from jet_burndown_category" );
    
    if ( category_id > 0 )
    {
    sb.append ( " inner join (" );
    
    sb.append ( " select concat(category_lineage, lpad(category_ordinal, 2, 0), '/') as 'ParentLineage' from jet_burndown_category where category_id = " + category_id );
    
    sb.append ( " ) as table1 on (category_id = " + category_id + " or category_lineage like table1.ParentLineage)" );
    }

    sb.append ( " inner join jet_burndown_transaction on transaction_category_id = category_id" );
    
    sb.append ( " where transaction_period_id = " + period_id );

    if ( type_id > 0 ) sb.append ( " and transaction_type = " + type_id );
    
    query ( jdbc, sb.toString() );
    }

    /**
     *
     */
    public String getKey() { return Transaction.key (); }
}
