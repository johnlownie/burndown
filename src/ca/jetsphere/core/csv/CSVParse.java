package ca.jetsphere.core.csv;

import java.io.IOException;

/**
 *
 */

public interface CSVParse
{
    /**
     *
     */

    public String nextValue() throws IOException ;

    /**
     *
     */

    public int lastLineNumber() ;

    /**
     *
     */

    public String[] getLine() throws IOException ;

    /**
     *
     */

    public int getLastLineNumber() ;

    /**
     *
     */

    public String[][] getAllValues() throws IOException ;

    /**
     *
     */

    public void changeDelimiter ( char newDelim ) throws BadDelimiterException ;

    /**
     *
     */

    public void changeQuote ( char newQuote ) throws BadQuoteException ;

    /**
     *
     */

    public void close() throws IOException ;

}
