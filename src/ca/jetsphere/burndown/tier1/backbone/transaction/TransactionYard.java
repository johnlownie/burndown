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
    static public boolean exists ( JDBC jdbc, Transaction transaction )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select count(1)" );

    sb.append ( " from jet_burndown_transaction" );
    
    sb.append ( " inner join jet_burndown_account on account_id = transaction_account_id" );

    sb.append ( " where transaction_name = " + DockYard.quote ( transaction.getName() ) );
    
    if ( transaction.getAccount().length() > 4 ) sb.append ( " and account_number like " + DockYard.quote ( "%" + transaction.getAccount().substring ( transaction.getAccount().length() - 4 ) ) );
    
    sb.append ( " and transaction_type = " + transaction.getType () );
    
    sb.append ( " and transaction_amount = " + transaction.getAmount () );
    
    sb.append ( " and transaction_fitid = " + DockYard.quote ( transaction.getFitId () ) );
    
    sb.append ( " and transaction_date = " + DockYard.quote ( transaction.getDate ().toString() ) );
    
    return QueryYard.query ( jdbc, sb.toString() ) > 0;
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
    static public void setLatest ( JDBC jdbc, HttpServletRequest request, int period_id )
    {
    TransactionSession transactions = TransactionSession.getInstance ( request );
    
    String query = "select * from jet_burndown_transaction where transaction_period_id = " + period_id + " order by transaction_date desc limit 20";

    transactions.query ( jdbc, query );
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
