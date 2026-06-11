package ca.jetsphere.burndown.tier1.backbone.transaction;

import ca.jetsphere.burndown.tier1.backbone.account.AccountYard;
import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.csv.ExcelCSVParser;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.upload.FormFile;

/**
 *
 */
public class TransactionYard
{
    static public int DEFAULT_CATEGORY = 1;
    static public int TYPE_DEBIT = 1;
    static public int TYPE_CREDIT = 2;
    
    /**
     * 
     */
    static public boolean exists ( Transaction transaction )

    { return DockYard.toInteger ( QueryYard.query ( getExistsQuery ( transaction ), 1 ) ) > 0; }
  
    /**
     * 
     */
    static public boolean exists ( JDBC jdbc, Transaction transaction )

    { return QueryYard.query ( jdbc, getExistsQuery ( transaction ) ) > 0; }
    
    /**
     * 
     */
    static public TransactionSession filter ( TransactionSession transactions, int type_id )
    {
    TransactionSession filtered = new TransactionSession();
    
    Iterator it = transactions.iterator();
    
    while ( it.hasNext() )
    {
    Transaction transaction = ( Transaction ) it.next();
    
    if      ( type_id == 1 &&  transaction.isFixed() ) { filtered.add ( transaction ); }

    else if ( type_id == 2 && !transaction.isFixed() ) { filtered.add ( transaction ); }
    }
    
    return filtered;
    }
    
    /**
     * 
     */
    static public int getCategoryIdBySimilarTransaction ( JDBC jdbc, String name )
    {
    Transaction similar = new Transaction();
    
    String query = "select * from jet_burndown_transaction where transaction_name like " + DockYard.quote ( name.substring ( 0, name.length() > 14 ? 14 : name.length() ) + "%" ) + " limit 1";
    
    similar.query ( jdbc, query );
    
    return similar.isValid() ? similar.getCategoryId() : DEFAULT_CATEGORY;
    }
  
    /**
     * 
     */
    static public String getExistsQuery ( Transaction transaction )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select count(1)" );

    sb.append ( " from jet_burndown_transaction" );
    
    sb.append ( " inner join jet_burndown_account on account_id = transaction_account_id" );

    sb.append ( " where transaction_name = " + DockYard.quote ( transaction.getName() ) );
    
    if ( transaction.getAccount().length() > 8 )
    {
    sb.append ( " and (account_number like " + DockYard.quote ( "%" + transaction.getAccount().substring ( transaction.getAccount().length() - 8 ) ) );
    
    sb.append ( " or account_secondary like " + DockYard.quote ( "%" + transaction.getAccount().substring ( transaction.getAccount().length() - 8 ) ) + ")" );
    }
    
    sb.append ( " and transaction_type = " + transaction.getType () );
    
    sb.append ( " and transaction_amount = " + transaction.getAmount () );
    
//    sb.append ( " and transaction_fitid = " + DockYard.quote ( transaction.getFitId () ) );
    
    sb.append ( " and transaction_date = " + DockYard.quote ( transaction.getDate ().toString() ) );
    
    return sb.toString();
    }
    
    /**
     * 
     */
    static public String getLatestQuery ( int application_id, int period_id )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select jet_burndown_transaction.* from jet_burndown_transaction inner join jet_base_period on period_id = transaction_period_id" );
    
    if ( period_id == 0 )
    {
    sb.append ( " where transaction_date > DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 12 MONTH), '%Y-%m-01')" );
    sb.append ( " and period_application_id = " + application_id );
    } else {
    sb.append ( " where transaction_period_id = " + period_id );
    }
     
    sb.append ( " and transaction_type = 1 order by transaction_date desc limit 20" );
   
    return sb.toString();
    }

    /**
     *
     */
    static public String getQuery ( int period_id, int account_id, int category_id, int type_id, boolean toggle, String start_date, String end_date )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select t1.* from jet_burndown_category" );
    
    if ( category_id > 0 )
    {
    sb.append ( " inner join (" );
    
    sb.append ( " select concat(category_lineage, lpad(category_ordinal, 2, 0), '/') as 'ParentLineage' from jet_burndown_category where category_id = " + category_id );
    
    sb.append ( " ) as table1 on (category_id = " + category_id + " or category_lineage like table1.ParentLineage)" );
    }

    sb.append ( " inner join jet_burndown_transaction t1 on transaction_category_id = category_id" );
    
    if ( toggle ) { sb.append ( " inner join jet_burndown_transaction t2 on t2.transaction_id != t1.transaction_id and t2.transaction_date = t1.transaction_date and t2.transaction_amount = t1.transaction_amount and t2.transaction_name = t1.transaction_name" ); }
     
    sb.append ( " where t1.transaction_period_id = " + period_id );

    if ( toggle ) { sb.append ( " and t2.transaction_period_id = " + period_id ); }

    if ( account_id > 0 ) sb.append ( " and t1.transaction_account_id = " + account_id );

    if ( type_id > 0 ) sb.append ( " and t1.transaction_type = " + type_id );

    if ( !DockYard.isWhiteSpace ( start_date ) ) sb.append ( " and t1.transaction_date >= " + DockYard.quote ( start_date ) );

    if ( !DockYard.isWhiteSpace ( end_date   ) ) sb.append ( " and t1.transaction_date <= " + DockYard.quote ( end_date ) );
    
    return sb.toString();
    }
    
    /**
     * 
     */
    static public int maxMonth ( JDBC jdbc, int period_id, String start_date, String end_date )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select month(max(transaction_date)) from jet_burndown_transaction where transaction_period_id = " + period_id + " and transaction_date >= " + DockYard.quote ( start_date ) + " and transaction_date <= " + DockYard.quote ( end_date ) );
    
    return QueryYard.query ( jdbc, sb.toString() );
    }
  
    /**
     *
     */
    static public void parseAttachment ( JDBC jdbc, TransactionSession transactions, FormFile formFile )
    {
    if ( formFile == null || formFile.getFileSize() == 0 ) return;
    
    switch ( formFile.getContentType() ) {
        
    case "text/csv" : parseAttachmentCsv ( jdbc, transactions, formFile );
        
    default         : parseAttachmentOfx ( jdbc, transactions, formFile );
    }
    }
  
    /**
     *
     */
    static public void parseAttachmentCsv ( JDBC jdbc, TransactionSession transactions, FormFile formFile )
    {
    if ( formFile == null || formFile.getFileSize() == 0 ) return;

    try {
    
        String[][] values = ExcelCSVParser.parse ( new InputStreamReader ( formFile.getInputStream(), StandardCharsets.UTF_8 ) );
        
        if ( values == null || values[0].length == 0 ) return;
        
        String[] parts = formFile.getFileName().split ( "-" ); String accountName = parts[4];
        
        int accountId = AccountYard.getId ( jdbc, accountName );
        
        for ( int i = 1; i < values[0].length; i++ ) {
            
            Transaction transaction = new Transaction();
            
            transaction.setId ( i ); transaction.setAccountId ( accountId ); transaction.setAccount ( accountName );
            
            for ( int j = 0; j < values[i].length; j++ ) {
                
                if ( j == 0 ) {
                    
                    Date date = CalendarYard.getDate ( values[i][j], "yyyy-MM-dd" );
                    
                    if ( date == null ) continue;
                    
                    transaction.setDateAsString ( values[i][j] );
                }
                
                if ( j == 2 ) { 
                    
                    transaction.setName ( values[i][j] );
                    
                    int similar = TransactionYard.getCategoryIdBySimilarTransaction ( jdbc, transaction.getName() );
                    
                    transaction.setCategoryId ( similar );

                    if ( similar > 0 ) { try { transaction.foreign ( jdbc ); } catch ( Exception e ) { Common.trace ( e ); } }
                }
                
                if ( j == 3 ) {
                    
                    int amount = DockYard.toInt ( DockYard.toDouble ( values[i][j] ) * 100 );
                    
                    transaction.setAmount ( amount );
                    
                    transaction.setType ( amount > 0 ? TYPE_CREDIT : TYPE_DEBIT );
                    
                }
            }
            
            transactions.add ( transaction );
        }

    } catch ( Exception e ) { Common.trace ( e ); }

    }
  
    /**
     *
     */
    static public void parseAttachmentOfx ( JDBC jdbc, TransactionSession transactions, FormFile formFile )
    {
    if ( formFile == null || formFile.getFileSize() == 0 ) return;

    try {
    
        OfxParser ofxParser = new OfxParser();
    
        ofxParser.parse ( jdbc, transactions, formFile.getInputStream() );

    } catch ( Exception e ) { Common.trace ( e ); }

    }

    /**
     *
     */
    static public TransactionSession set ( JDBC jdbc, HttpServletRequest request, int period_id, int account_id, int category_id, int type_id, boolean toggle, String start_date, String end_date )
    {
    TransactionSession transactions = TransactionSession.getInstance ( request );
    
    String query = getQuery ( period_id, account_id, category_id, type_id, toggle, start_date, end_date );
    
    transactions.clear();
    
    transactions.query ( jdbc, query );
    
    return transactions;
    }
    
    /**
     * 
     */
    static public void setUncategorized ( JDBC jdbc, int category_id, String name )
    {
    String query = "select * from jet_burndown_transaction where transaction_category_id = 1 and transaction_name like '%" + name.substring ( 0, name.length() > 10 ? 10 : name.length() ) + "%'";
    
    TransactionSession transactions = new TransactionSession(); transactions.query( jdbc, query );
    
    Iterator it = transactions.iterator();
    
    while ( it.hasNext() )
    { 
    Transaction transaction = ( Transaction ) it.next(); transaction.setCategoryId ( category_id ); 
    
    try {
        
        transaction.update ( jdbc );
    
    } catch ( Exception e ) { Common.trace ( e ); }
    
    } }
}
