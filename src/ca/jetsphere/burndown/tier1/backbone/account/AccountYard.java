package ca.jetsphere.burndown.tier1.backbone.account;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;

/**
 *
 */
public class AccountYard 
{
    /**
     * 
     */
    static public String getNumber ( int account_id )
    {
    String query = "select concat(account_name, ' - *', substring(account_number, -4, 4)) from jet_burndown_account where account_id = " + account_id;
    
    return QueryYard.query ( query, 1 );
    }
    
    /**
     * 
     */
    static public Account getOrCreate ( JDBC jdbc, String number, String type, String bank_id )
    {
    Account account = new Account();
    
    String query = "select * from jet_burndown_account where account_number = " + DockYard.quote ( number ) + " and account_type = " + DockYard.quote ( type ) + " and account_bank_id = " + DockYard.quote ( bank_id );
    
    account.query ( jdbc, query );
    
    if ( account.isValid() ) return account;
    
    account.clear(); account.setName ( "???" ); account.setNumber ( number ); account.setType ( type ); account.setBankId ( bank_id );
    
    try { account.save ( jdbc ); } catch ( Exception e ) { Common.trace ( e ); }
    
    return account;
    }
}
