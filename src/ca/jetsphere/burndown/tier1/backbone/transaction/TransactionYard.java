package ca.jetsphere.burndown.tier1.backbone.transaction;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import java.util.Iterator;
import org.apache.struts.upload.FormFile;

/**
 *
 */
public class TransactionYard
{
    /**
     * 
     */
    static public boolean exists ( JDBC jdbc, Transaction transaction )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select count(1) from jet_burndown_transaction where transaction_name = " + DockYard.quote ( transaction.getName() ) );
    
    sb.append ( " and transaction_account_id = " + DockYard.quote ( transaction.getAccountId () ) );
    
    sb.append ( " and transaction_type = " + transaction.getType () );
    
    sb.append ( " and transaction_amount = " + transaction.getAmount () );
    
    sb.append ( " and transaction_fitid = " + DockYard.quote ( transaction.getFitId () ) );
    
    sb.append ( " and transaction_date = " + DockYard.quote ( transaction.getDate ().toString() ) );
    
    return QueryYard.query ( jdbc, sb.toString() ) > 0;
    }
    
    /**
     * 
     */
    static public int getCategoryIdBySimilarTransaction ( JDBC jdbc, Transaction transaction )
    {
    StringBuilder sb = new StringBuilder(); Transaction similar = new Transaction();
    
    sb.append ( "select * from jet_burndown_transaction where transaction_name = " + DockYard.quote ( transaction.getName() ) );
    
    sb.append ( " and transaction_account_id = " + DockYard.quote ( transaction.getAccountId () ) );
    
    sb.append ( " and transaction_type = " + transaction.getType () );
    
    sb.append ( " and transaction_fitid = " + DockYard.quote ( transaction.getFitId () ) );
    
    sb.append ( " and transaction_date = " + DockYard.quote ( transaction.getDate ().toString() ) );
    
    similar.query ( jdbc, sb.toString() );
    
    return similar.isValid() ? similar.getCategoryId() : -1;
    }
 
    /**
     *
     */
    static public void parseAttachment ( TransactionSession transactions, FormFile formFile )
    {
    if ( formFile == null || formFile.getFileSize() == 0 ) return;

    try {
    
        OfxParser ofxParser = new OfxParser();
    
        ofxParser.parse ( transactions, formFile.getInputStream() );

    } catch ( Exception e ) { Common.trace ( e ); }

    }
    
    /**
     * 
     */
    static public void setUncategorized ( JDBC jdbc, int category_id, String name )
    {
    String query = "select * from jet_burndown_transaction where transaction_category_id = 1 and transaction_name like '%" + name.substring ( 0, 4 ) + "%'";
    
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
