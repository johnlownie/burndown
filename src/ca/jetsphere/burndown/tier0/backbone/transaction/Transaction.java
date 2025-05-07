package ca.jetsphere.burndown.tier0.backbone.transaction;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.burndown.tier0.Registry;
import java.sql.ResultSet;

/**
 *
 */
public class Transaction extends ca.jetsphere.burndown.tier0.backbone.transaction.foreign.Transaction
{
    /**
     *
     */
    public Transaction() { clear(); }

    /**
     *
     */
    public Transaction ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */
    public void delete ( JDBC jdbc ) throws Exception { Registry.getTransactionDao().delete ( jdbc, this ); }

    /**
     *
     */
    public String getKey() { return key(); }

    /**
     *
     */
    public void insert ( JDBC jdbc ) throws Exception { Registry.getTransactionDao().insert ( jdbc, this ); }

    /**
     *
     */
    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getTransactionDao().update ( jdbc, this ); }
}
