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
            " ( transaction_uuid, transaction_period_id, transaction_category_id, transaction_name, transaction_bank_id, transaction_account_id, transaction_account_type, transaction_type, transaction_amount, transaction_memo, transaction_fitid, transaction_date, transaction_last_update, transaction_created )" +
            " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

    ps.setStatement ( s, new String [] { "transaction_id" } );

    transaction.setUuid ( UUID.get () ); transaction.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); transaction.setLastUpdate ( transaction.getCreated() );

    ps.setString    (  1, transaction.getUuid        () );
    ps.setInt       (  2, transaction.getPeriodId    () );
    ps.setInt       (  3, transaction.getCategoryId  () );
    ps.setString    (  4, transaction.getName        () );
    ps.setString    (  5, transaction.getBankId      () );
    ps.setString    (  6, transaction.getAccountId   () );
    ps.setString    (  7, transaction.getAccountType () );
    ps.setInt       (  8, transaction.getType        () );
    ps.setInt       (  9, transaction.getAmount      () );
    ps.setString    ( 10, transaction.getMemo        () );
    ps.setString    ( 11, transaction.getFitId       () );
    ps.setDate      ( 12, transaction.getDate        () );
    ps.setTimestamp ( 13, transaction.getLastUpdate  () );
    ps.setTimestamp ( 14, transaction.getCreated     () );
    }

    /**
     *
     */
    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Transaction transaction = ( Transaction ) bolt;

    String s = "update jet_burndown_transaction set transaction_uuid = ?, transaction_period_id = ?, transaction_category_id = ?, transaction_name = ?, transaction_bank_id = ?, transaction_account_id = ?, transaction_account_type = ?, transaction_type = ?, transaction_amount = ?, transaction_memo = ?, transaction_fitid = ?, transaction_date = ?, transaction_last_update = ? where transaction_id = ?";

    ps.setStatement ( s );

    transaction.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, transaction.getUuid        () );
    ps.setInt       (  2, transaction.getPeriodId    () );
    ps.setInt       (  3, transaction.getCategoryId  () );
    ps.setString    (  4, transaction.getName        () );
    ps.setString    (  5, transaction.getBankId      () );
    ps.setString    (  6, transaction.getAccountId   () );
    ps.setString    (  7, transaction.getAccountType () );
    ps.setInt       (  8, transaction.getType        () );
    ps.setInt       (  9, transaction.getAmount      () );
    ps.setString    ( 10, transaction.getMemo        () );
    ps.setString    ( 11, transaction.getFitId       () );
    ps.setDate      ( 12, transaction.getDate        () );
    ps.setTimestamp ( 13, transaction.getLastUpdate  () );
    ps.setInt       ( 14, transaction.getId          () );
    }
}
