package ca.jetsphere.burndown.tier1.backbone.account;

import ca.jetsphere.core.jdbc.JDBC;

import java.sql.ResultSet;

/**
 *
 */

public class Account extends ca.jetsphere.burndown.tier0.backbone.account.Account
{
    /**
     *
     */
    public Account() { super(); }

    /**
     *
     */

    public Account ( Account account ) { this(); copy ( account ); }

    /**
     *
     */
    public Account ( JDBC jdbc )

    { super(); query ( jdbc, "select * from jet_burndown_account"); }

    /**
     *
     */
    public Account ( JDBC jdbc, int account_id ) throws Exception { super ( jdbc, account_id ); }

    /**
     *
     */
    public Account ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */
    static public String [] captions() { return new String [] { "account.name", "account.number", "last.update", "actions" }; }

    /**
     *
     */
    static public String [] fields() { return new String [] { "account_name", "account_number", "account_last_update", "account_uuid" }; }
}
