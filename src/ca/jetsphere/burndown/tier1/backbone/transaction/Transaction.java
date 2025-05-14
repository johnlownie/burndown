package ca.jetsphere.burndown.tier1.backbone.transaction;

import ca.jetsphere.burndown.tier1.backbone.category.CategoryYard;
import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;

import java.sql.ResultSet;

/**
 *
 */

public class Transaction extends ca.jetsphere.burndown.tier0.backbone.transaction.Transaction
{
    /**
     *
     */
    public Transaction() { super(); }

    /**
     *
     */
    public Transaction ( JDBC jdbc )

    { super(); query ( jdbc, "select * from jet_burndown_transaction"); }

    /**
     *
     */
    public Transaction ( JDBC jdbc, int transaction_id ) throws Exception { super ( jdbc, transaction_id ); }

    /**
     *
     */
    public Transaction ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); foreign ( jdbc ); }

    /**
     *
     */
    static public String [] captions()

    { return new String [] { "transaction.date", "transaction.type", "transaction.amount", "transaction.name", "transaction.category", "transaction.account.id", "actions" }; }


    /**
     *
     */
    static public String [] captions_dashboard()

    { return new String [] { "transaction.date", "transaction.type", "transaction.amount", "transaction.name", "transaction.category", "transaction.account.id" }; }

    /**
     *
     */
    static public String [] captions_import()

    { return new String [] { "transaction.date", "transaction.type", "transaction.amount", "transaction.name", "transaction.category", "transaction.fit.id", "transaction.bank.id", "transaction.account.id", "transaction.account.type" }; }

    @Override
    public boolean equals( Object o )
    {
    if ( this == o ) return true;
    
    if ( !( o instanceof Transaction ) ) return false;

    Transaction that = ( Transaction ) o;

    if ( getType() != that.getType() ) return false;
        
    if ( getAmount() !=  that.getAmount() ) return false;
        
    if ( getName() != null ? !getName().equals ( that.getName() ) : that.getName() != null ) return false;
        
    if ( getMemo() != null ? !getMemo().equals ( that.getMemo() ) : that.getMemo() != null ) return false;
        
    if ( getDate() != null ? !getDate().equals ( that.getDate() ) : that.getDate() != null ) return false;
        
    return !( getFitId() != null ? !getFitId().equals ( that.getFitId() ) : that.getFitId() != null );
    }

    /**
     *
     */
    static public String [] fields()

    { return new String [] { "transaction_date", "transaction_type", "transaction_amount", "transaction_name", "transaction_category_id", "transaction_account_id", "transaction_uuid" }; }

    /**
     *
     */
    static public String [] fields_dashboard()

    { return new String [] { "transaction_date", "transaction_type", "transaction_amount", "transaction_name", "transaction_category_id", "transaction_account_id" }; }

    /**
     *
     */
    static public String [] fields_import()

    { return new String [] { "transaction_date", "transaction_type", "transaction_amount", "transaction_name", "transaction_category_id", "transaction_fitid", "transaction_bank_id", "transaction_account_id", "transaction_account_type" }; }

    /**
     *
     */

    public Object get ( String s )
    {
    if ( "transaction_amount"      .equals ( s ) ) return DockYard.toMoney ( getAmount() );
    
    if ( "transaction_category_id" .equals ( s ) ) return CategoryYard.getTreeName( getCategoryId() );

    if ( "transaction_type"        .equals ( s ) ) return getTypeAsString ( getType() );

    return super.get ( s );
    }
    
    /**
     *
     */
    public String getKey() { return key(); }
    
    /**
     * 
     */
    public String getTypeAsString ( int type )
    {
    switch ( type )
    {
        case 1 : return "DEBIT";
        
        case 2 : return "CREDIT";
        
        default: return "???";
    } }

    @Override
    public int hashCode() 
    {
//    int result = getType() != 0 ? getType().hashCode() : 0;
        
//    result = 31 * result + ( getAmount() != 0 ? getAmount().hashCode() : 0 );
        
    int result = getName() != null ? getName().hashCode() : 0;
        
    result = 31 * result + ( getMemo() != null ? getMemo().hashCode() : 0 );
        
    result = 31 * result + ( getDate() != null ? getDate().hashCode() : 0 );
        
    result = 31 * result + ( getFitId() != null ? getFitId().hashCode() : 0 );
        
    return result;
    }
    
    /**
     * 
     */
    public String getDateAsString () { return CalendarYard.getDateTimeFormat ( getDate(), "yyyy-MM-dd" ); }
    public void   setDateAsString ( String date ) { setDate ( new java.sql.Date ( CalendarYard.getDate ( date, "yyyy-MM-dd" ).getTime() ) ); }
}
