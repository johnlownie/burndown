package ca.jetsphere.burndown.tier0.backbone.account;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */
public class AccountDao extends AbstractDao
{
    /**
     *
     */
    public boolean delete ( JDBC jdbc, Account account) throws Exception

    {  return delete ( jdbc, account, "delete from jet_burndown_account where account_id = " + account.getId() ); }

    /**
     *
     */
    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Account account = ( Account ) bolt;

    String s = "insert into jet_burndown_account" +
            " ( account_uuid, account_application_id, account_name, account_type, account_number, account_bank_id, account_last_update, account_created )" +
            " values ( ?, ?, ?, ?, ?, ?, ?, ? )";

    ps.setStatement ( s, new String [] { "account_id" } );

    account.setUuid ( UUID.get () ); account.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); account.setLastUpdate ( account.getCreated() );

    ps.setString    (  1, account.getUuid          () );
    ps.setInt       (  2, account.getApplicationId () );
    ps.setString    (  3, account.getName          () );
    ps.setString    (  4, account.getType          () );
    ps.setString    (  5, account.getNumber        () );
    ps.setString    (  6, account.getBankId        () );
    ps.setTimestamp (  7, account.getLastUpdate    () );
    ps.setTimestamp (  8, account.getCreated       () );
    }

    /**
     *
     */
    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Account account = ( Account ) bolt;

    String s = "update jet_burndown_account set account_uuid = ?, account_application_id = ?, account_name = ?, account_type = ?, account_number = ?, account_bank_id = ?, account_last_update = ? where account_id = ?";

    ps.setStatement ( s );

    account.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, account.getUuid          () );
    ps.setInt       (  2, account.getApplicationId () );
    ps.setString    (  3, account.getName          () );
    ps.setString    (  4, account.getType          () );
    ps.setString    (  5, account.getNumber        () );
    ps.setString    (  6, account.getBankId        () );
    ps.setTimestamp (  7, account.getLastUpdate    () );
    ps.setInt       (  8, account.getId            () );
    }
}
