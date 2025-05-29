package ca.jetsphere.burndown.tier1.backbone.account;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.DockYard;
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
    static public String [] captions() { return new String [] { "account.name", "account.type", "account.number", "account.bank.id", "account.url", "last.update", "actions" }; }

    /**
     *
     */
    static public String [] fields() { return new String [] { "account_name", "account_type", "account_number", "account_bank_id", "account_url", "account_last_update", "account_uuid" }; }

    /**
     *
     */
    public Object get ( String s )
    {
    if ( "account_url" .equals ( s ) ) return DockYard.getHref ( Caption.get ( "sign.in" ), getUrl(), true );

    return super.get ( s );
    }
    
    /**
     * 
     */
    public String getNameNumber() { return getName() + " - *" + getNumber().substring ( getNumber().length() - 4 ); }
}
