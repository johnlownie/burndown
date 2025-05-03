package ca.jetsphere.burndown.tier1.backbone.transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 *
 */
public class OfxParser
{
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyyMMddHHmmss" );

    /**
     * 
     */
    private class Collector 
    {
        private List<String> openTags = new LinkedList<>();
        private List<Transaction> transactions = new LinkedList<>();
        private List<BankAccount> bankAccounts = new LinkedList<>();

        /**
         * 
         */
        public void end() {}

        /**
         * 
         */
        public void openTag ( String tag )
        {
        openTags.add ( tag );
        
        if ( tag.equals ( "STMTTRN" ) ) 
        
        { transactions.add ( new Transaction() ); }
        
        else if ( tag.equals ( "BANKACCTFROM" ) || tag.equals ( "CCACCTFROM" ) )
                
        { bankAccounts.add ( new BankAccount() ); }
        
        }

        /**
         * 
         */
        public void closeTag ( String tag )
        {
        while( !lastOpenTag().equals ( tag ) )
        
        { closeTag ( lastOpenTag() ); }
        
        openTags.remove ( openTags.size() - 1 );
        }

        /**
         * 
         */
        public void text ( String text )
        {
            if ( lastOpenTag().equals ( "BANKID" ) ) {
                lastBankAccount().setBankId ( text );
            } else if ( lastOpenTag().equals ( "ACCTID" ) ) {
                lastBankAccount().setAccountId ( text );
            }  else if ( lastOpenTag().equals ( "ACCTTYPE" ) ) {
                lastBankAccount().setAccountType ( text );
            } else if ( lastOpenTag().equals ( "TRNTYPE" ) ) {
                lastTransaction().setBankId ( lastBankAccount().getBankId() );
                lastTransaction().setAccountId ( lastBankAccount().getAccountId() );
                lastTransaction().setAccountType ( lastBankAccount().getAccountType() );
                
                switch ( text ) {
                    case "DEBIT": lastTransaction().setType ( 1 ); break;
                    case "CREDIT": lastTransaction().setType ( 2 ); break;
                }
            } else if ( lastOpenTag().equals ( "TRNAMT" ) ) {
                lastTransaction().setAmount ( ( int ) ( Double.parseDouble ( text ) * 100 ) );
//                if ( text.startsWith ( "-" ) ) {
//                    lastTransaction().setType ( 1 );
//                    lastTransaction().setAmount ( ( int ) ( Double.parseDouble ( text.substring ( 1 ) ) * 100 ) );
//                } else if ( text.startsWith ( "+" ) ) {
//                    lastTransaction().setType ( 2 );
//                    lastTransaction().setAmount ( ( int ) ( Double.parseDouble ( text.substring ( 1 ) ) * 100 ) );
//                } else {
//                    lastTransaction().setType ( 2 );
//                    lastTransaction().setAmount ( ( int ) ( Double.parseDouble ( text.substring ( 1 ) ) * 100 ) );
//                    throw new UnsupportedOperationException();
//                }
            } else if ( lastOpenTag().equals ( "NAME" ) ) {
                lastTransaction().setName ( text );
            } else if ( lastOpenTag().equals ( "MEMO" ) ) {
                lastTransaction().setMemo ( text );
            } else if ( lastOpenTag().equals ( "DTPOSTED" ) ) {
                LocalDate date = LocalDate.parse ( text.substring ( 0, 14 ), formatter );
                lastTransaction().setDate ( java.sql.Date.valueOf ( date ) );
            } else if ( lastOpenTag().equals ( "FITID" ) ) {
                lastTransaction().setFitId ( text );
            } 
        }

        /**
         * 
         */
        private String lastOpenTag()
        
        { return openTags.isEmpty() ? "" : openTags.get ( openTags.size() - 1 ); }

        /**
         * 
         */
        private BankAccount lastBankAccount() 
        
        { return bankAccounts.isEmpty() ? null : bankAccounts.get ( bankAccounts.size() - 1 ); }

        /**
         * 
         */
        private Transaction lastTransaction() 
        
        { return transactions.isEmpty() ? null : transactions.get ( transactions.size() - 1 ); }
    }

    /**
     * 
     */
    private boolean startsWith ( String content, int position, String s ) 
    
    { return content.length() >= position + s.length() && content.substring ( position, position + s.length() ).equals ( s ); }

    /**
     * 
     */
    private void processInput ( String content, int position, Collector collector )
    {
    if ( content.length() == position ) 
    
    { collector.end(); } 
    
    else if ( startsWith ( content, position, "</" ) ) 
    {
        int close = content.indexOf ( ">", position );
        
        collector.closeTag ( content.substring ( position + 2, close ) );
        
        processInput ( content, close + 1, collector );
        
    } else if ( startsWith ( content, position, "<" ) ) {
            
        int close = content.indexOf ( ">", position );
            
        collector.openTag ( content.substring ( position + 1, close ) );
            
        processInput ( content, close + 1, collector );
        
    } else {
            
        int next = content.indexOf ( "<", position );
            
        if( next == -1 ) { next=content.length(); }
            
        String text = content.substring ( position, next ).trim();
            
        if ( !text.isEmpty() ) { collector.text ( text ); }
            
        processInput ( content, next, collector );

    } }

    /**
     * 
     */
    public void parse ( TransactionSession transactions, InputStream is ) throws IOException
    {
    Collector collector = new Collector();
    
    String content = readAll( is );
        
    processInput ( content, 0, collector );
        
    ListIterator it = collector.transactions.listIterator();
    
    while ( it.hasNext() )
    {
    int id = it.nextIndex();
    
    Transaction transaction = ( Transaction ) it.next(); 
    
    transaction.setId ( id ); 
    
    transactions.add ( transaction ); }
    }

    /**
     * 
     */
    private String readAll ( InputStream is ) throws IOException
    {
    BufferedReader reader = new BufferedReader ( new InputStreamReader ( is ) );
    
    String line = null; StringBuilder stringBuilder = new StringBuilder();
        
    String ls = System.getProperty("line.separator");
    
    try {
        while ( ( line=reader.readLine() ) != null ) 
        
        { stringBuilder.append ( line ); stringBuilder.append ( ls ); }
            
        return stringBuilder.toString();
        
    } finally { reader.close(); }
    
    }
}
