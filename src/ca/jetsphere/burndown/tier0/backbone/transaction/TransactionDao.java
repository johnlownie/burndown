package ca.jetsphere.burndown.tier0.backbone.transaction;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */
public class TransactionDao extends AbstractDao
{
    /**
     *
     */
    public boolean delete ( JDBC jdbc, Transaction transaction) throws Exception

    {  return delete ( jdbc, transaction, "delete from jet_burndown_transaction where transaction_id = " + transaction.getId() ); }

    /**
     *
     */
    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Transaction transaction = ( Transaction ) bolt;

    String s = "insert into jet_burndown_transaction" +
            " ( transaction_uuid, transaction_period_id, transaction_name, transaction_bank_id, transaction_account_id, transaction_account_type, transaction_type, transaction_amount, transaction_memo, transaction_fitid, transaction_date, transaction_last_update, transaction_created )" +
            " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

    ps.setStatement ( s, new String [] { "transaction_id" } );

    transaction.setUuid ( UUID.get () ); transaction.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); transaction.setLastUpdate ( transaction.getCreated() );

    ps.setString    (  1, transaction.getUuid        () );
    ps.setInt       (  2, transaction.getPeriodId    () );
    ps.setString    (  3, transaction.getName        () );
    ps.setString    (  4, transaction.getBankId      () );
    ps.setString    (  5, transaction.getAccountId   () );
    ps.setString    (  6, transaction.getAccountType () );
    ps.setInt       (  7, transaction.getType        () );
    ps.setInt       (  8, transaction.getAmount      () );
    ps.setString    (  9, transaction.getMemo        () );
    ps.setString    ( 10, transaction.getFitId       () );
    ps.setDate      ( 11, transaction.getDate        () );
    ps.setTimestamp ( 12, transaction.getLastUpdate  () );
    ps.setTimestamp ( 13, transaction.getCreated     () );
    }

    /**
     *
     */
    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Transaction transaction = ( Transaction ) bolt;

    String s = "update jet_burndown_transaction set transaction_uuid = ?, transaction_period_id = ?, transaction_name = ?, transaction_bank_id = ?, transaction_account_id = ?, transaction_account_type = ?, transaction_type = ?, transaction_amount = ?, transaction_memo = ?, transaction_fitid = ?, transaction_date = ?, transaction_last_update = ? where transaction_id = ?";

    ps.setStatement ( s );

    transaction.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, transaction.getUuid       () );
    ps.setInt       (  2, transaction.getPeriodId   () );
    ps.setString    (  3, transaction.getName       () );
    ps.setString    (  4, transaction.getBankId      () );
    ps.setString    (  5, transaction.getAccountId   () );
    ps.setString    (  6, transaction.getAccountType () );
    ps.setInt       (  7, transaction.getType        () );
    ps.setInt       (  8, transaction.getAmount      () );
    ps.setString    (  9, transaction.getMemo        () );
    ps.setString    ( 10, transaction.getFitId       () );
    ps.setDate      ( 11, transaction.getDate        () );
    ps.setTimestamp ( 12, transaction.getLastUpdate  () );
    ps.setInt       ( 13, transaction.getId         () );
    }
}
