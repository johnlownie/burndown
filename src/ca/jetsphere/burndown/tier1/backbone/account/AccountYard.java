package ca.jetsphere.burndown.tier1.backbone.account;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;

import java.util.Comparator;

/**
 *
 */
public class AccountYard 
{
    /**
     *
     */
	
    static public Comparator BY_NAME = new Comparator()
    {
    public int compare ( Object o1, Object o2 )
    {
    Account a1 = ( Account ) o1; Account a2 = ( Account ) o2;
	
    return DockYard.compareTo ( a1.getName(), a2.getName() );
    }
    };
    
    /**
     * 
     */
    
    static public int getId ( JDBC jdbc, String account_number )
    {
    String query = "select * from jet_burndown_account where account_number like '%" + account_number + "%' or account_secondary like '%" + account_number + "%'";
    
    Account account = new Account(); account.query ( jdbc, query );
    
    return account.getId();
    }
    
    /**
     * 
     */
            
    static public String getNumber ( int account_id )
    {
    String query = "select concat(account_name, ' - *', substring(account_number, -4, 4), case when account_secondary != '' then concat(' / *', substring(account_secondary, -4, 4)) else '' end) from jet_burndown_account where account_id = " + account_id;
    
    return QueryYard.query ( query, 1 );
    }
    
    /**
     * 
     */
    
    static public Account getOrCreate ( JDBC jdbc, String number, String type, String bank_id )
    {
    Account account = new Account(); StringBuilder sb = new StringBuilder();
    
    sb.append ( "select * from jet_burndown_account" );
    
    sb.append ( " where (account_number like " + DockYard.quote ( "%" + number.substring ( number.length() - 4 ) ) );
    
    sb.append ( " or account_secondary like " + DockYard.quote ( "%" + number.substring ( number.length() - 4 ) ) + ")" ); 
            
    if ( !DockYard.isWhiteSpace ( type    ) ) sb.append ( " and account_type = " + DockYard.quote ( type ) );
    
    if ( !DockYard.isWhiteSpace ( bank_id ) ) sb.append ( " and account_bank_id = " + DockYard.quote ( bank_id ) );
    
    account.query ( jdbc, sb.toString() );
    
    if ( account.isValid() ) return account;
    
    account.clear(); account.setName ( "???" ); account.setNumber ( number ); account.setType ( type ); account.setBankId ( bank_id );
    
    try { account.save ( jdbc ); } catch ( Exception e ) { Common.trace ( e ); }
    
    return account;
    }
}
