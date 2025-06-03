package ca.jetsphere.burndown.tier1.backbone.transaction;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.upload.FormFile;

/**
 *
 */
public class TransactionYard
{
    static public int DEFAULT_CATEGORY = 1;
    
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
    StringBuilder sb = new StringBuilder(); Transaction similar = new Transaction();
    
    sb.append ( "select * from jet_burndown_transaction where transaction_name like " + DockYard.quote ( name.substring ( 0, name.length() > 14 ? 14 : name.length() ) + "%" ) + " limit 1" );
    
    similar.query ( jdbc, sb.toString() );
    
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
    
    if ( transaction.getAccount().length() > 4 ) sb.append ( " and account_number like " + DockYard.quote ( "%" + transaction.getAccount().substring ( transaction.getAccount().length() - 4 ) ) );
    
    sb.append ( " and transaction_type = " + transaction.getType () );
    
    sb.append ( " and transaction_amount = " + transaction.getAmount () );
    
//    sb.append ( " and transaction_fitid = " + DockYard.quote ( transaction.getFitId () ) );
    
    sb.append ( " and transaction_date = " + DockYard.quote ( transaction.getDate ().toString() ) );
    
    return sb.toString();
    }
    
    /**
     * 
     */
    static public String getLatestQuery ( int period_id )

    { return "select * from jet_burndown_transaction where transaction_period_id = " + period_id + " and transaction_type = 1 order by transaction_date desc limit 20";}

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
    static public void parseAttachment ( JDBC jdbc, TransactionSession transactions, FormFile formFile )
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
