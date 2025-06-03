package ca.jetsphere.burndown.tier1.backbone.transaction;

import ca.jetsphere.burndown.tier1.backbone.account.AccountYard;
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
    private String  bank_id       ;
    private String  account       ;
    private String  account_type  ;
    private String  category_name ;
    private boolean fixed ;
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

    { return new String [] { "transaction.date", "transaction.name", "transaction.account", "transaction.category", "transaction.type", "transaction.amount", "actions" }; }


    /**
     *
     */
    static public String [] captions_dashboard()

    { return new String [] { "transaction.name", "transaction.account", "transaction.category", "transaction.date", "transaction.amount" }; }

    /**
     *
     */
    static public String [] captions_import()

    { return new String [] { "transaction.date", "transaction.name", "transaction.account.id", "transaction.category", "transaction.type", "transaction.amount", "transaction.fit.id", "transaction.bank.id", "transaction.account.type", "possible.duplicate" }; }

    /**
     *
     */
    static public String [] captions_import_headers()

    { return new String [] { "transaction.date", "transaction.name", "transaction.account.id", "transaction.category", "transaction.type", "transaction.amount", "transaction.fit.id", "transaction.bank.id", "transaction.account.type" }; }

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

    { return new String [] { "transaction_date", "transaction_name", "transaction_account_id", "transaction_category_id", "transaction_type", "transaction_amount", "transaction_uuid" }; }

    /**
     *
     */
    static public String [] fields_dashboard()

    { return new String [] { "transaction_name", "transaction_account_id", "transaction_category_id", "transaction_date", "transaction_amount" }; }

    /**
     *
     */
    static public String [] fields_import()

    { return new String [] { "transaction_date", "transaction_name", "account", "transaction_category_id", "transaction_type", "transaction_amount", "transaction_fitid", "bank_id", "account_type", "possible_duplicate" }; }

    /**
     *
     */
    static public String [] fields_import_headers()

    { return new String [] { "transaction_date", "transaction_type", "transaction_amount", "transaction_name", "transaction_category_id", "transaction_fitid", "bank_id", "account", "account_type" }; }

    /**
     *
     */
    public void foreign ( JDBC jdbc ) throws Exception 
    {
    String s = CategoryYard.getTreeNameFixed ( jdbc, getCategoryId() );
    
    setCategoryName ( DockYard.getToken ( s, 1, "|" ) ); setFixed ( DockYard.toBoolean ( DockYard.getToken ( s, 2, "|" ) ) );
    }
    
    /**
     *
     */

    public Object get ( String s )
    {
    if ( "transaction_amount"      .equals ( s ) ) return DockYard.toMoney ( getAmount() );
    
    if ( "transaction_account_id"  .equals ( s ) ) return AccountYard.getNumber ( getAccountId() );
    
    if ( "transaction_category_id" .equals ( s ) ) return getCategoryName();

    if ( "transaction_fixed"       .equals ( s ) ) return isFixed();

    if ( "transaction_type"        .equals ( s ) ) return getTypeAsString ( getType() );
    
    if ( "possible_duplicate"      .equals ( s ) ) { return TransactionYard.exists ( this ); }

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

    public String  getAccount      () { return account       ; }
    public String  getAccountType  () { return account_type  ; }
    public String  getBankId       () { return bank_id       ; }
    public String  getCategoryName () { return category_name ; }
    public String  getDateAsString () { return CalendarYard.getDateTimeFormat ( getDate(), "yyyy-MM-dd" ); }
    public boolean isFixed         () { return fixed ; }
 
    public void setAccount       ( String  account       ) { this.account       = account       ; }
    public void setAccountType   ( String  account_type  ) { this.account_type  = account_type  ; }
    public void setBankId        ( String  bank_id       ) { this.bank_id       = bank_id       ; }
    public void setCategoryName  ( String  category_name ) { this.category_name = category_name ; }
    public void setDateAsString  ( String  date          ) { setDate ( new java.sql.Date ( CalendarYard.getDate ( date, "yyyy-MM-dd" ).getTime() ) ); }
    public void setFixed         ( boolean fixed         ) { this.fixed         = fixed         ; }
}
