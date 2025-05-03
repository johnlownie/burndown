package ca.jetsphere.core.csv;

import java.io.IOException;

/**
 *
 */

public interface CSVPrint
{
    /**
     *
     */

    public void changeDelimiter ( char newDelimiter ) throws BadDelimiterException;

    /**
     *
     */

    public void changeQuote ( char newQuote ) throws BadQuoteException;

    /**
     *
     */

    public void setAutoFlush ( boolean autoFlush );

    /**
     *
     */

    public boolean checkError ();

    /**
     *
     */

    public void println ( String value );

    /**
     *
     */

    public void writeln ( String value ) throws IOException;

    /**
     *
     */

    public void println ();

    /**
     *
     */

    public void writeln () throws IOException;

    /**
     *
     */

    public void println ( String[] values );

    /**
     *
     */

    public void writeln ( String[] values ) throws IOException;

    /**
     *
     */

    public void println ( String[][] values );

    /**
     *
     */

    public void writeln ( String[][] values ) throws IOException;

    /**
     *
     */

    public void printlnComment ( String comment );

    /**
     *
     */

    public void writelnComment ( String comment ) throws IOException;

    /**
     *
     */

    public void print ( String value );

    /**
     *
     */

    public void write ( String value ) throws IOException;

    /**
     *
     */

    public void flush () throws IOException;

    /**
     *
     */

    public void close () throws IOException;

    /**
     *
     */

    public void print ( String[] values );

    /**
     *
     */

    public void write ( String[] values ) throws IOException;

    /**
     *
     */

    public void setAlwaysQuote ( boolean alwaysQuote );

}
