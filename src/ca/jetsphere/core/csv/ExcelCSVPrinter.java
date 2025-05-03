package ca.jetsphere.core.csv;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

/**
 *
 */

public class ExcelCSVPrinter implements CSVPrint
{

    protected boolean autoFlush     = true;
    protected boolean alwaysQuote   = false;
    protected boolean error         = false;
    protected char    delimiterChar = ',';
    protected char    quoteChar     = '"';
    protected Writer  out;
    protected boolean newLine       = true;

    /**
     *
     */

    public ExcelCSVPrinter ( OutputStream out ) { this.out = new OutputStreamWriter ( out ); }

    /**
     *
     */

    public ExcelCSVPrinter ( Writer out ) { this.out = out; }

    /**
     *
     */

    public ExcelCSVPrinter ( Writer out, boolean alwaysQuote, boolean autoFlush )

    { this.out = out; setAlwaysQuote ( alwaysQuote ); setAutoFlush ( autoFlush ); }

    /**
     *
     */

    public ExcelCSVPrinter ( Writer out, char quote, char delimiter ) throws BadDelimiterException, BadQuoteException

    { this.out = out; changeQuote ( quote ); changeDelimiter ( delimiter ); }

    /**
     *
     */

    public ExcelCSVPrinter ( Writer out, char quote, char delimiter, boolean alwaysQuote, boolean autoFlush ) throws BadDelimiterException, BadQuoteException

    { this.out = out; changeQuote ( quote ); changeDelimiter ( delimiter ); setAlwaysQuote ( alwaysQuote ); setAutoFlush ( autoFlush ); }

    /**
     *
     */

    public void changeDelimiter ( char newDelimiter ) throws BadDelimiterException
    {
    if ( delimiterChar == newDelimiter ) return;

    if ( newDelimiter == '\n' || newDelimiter == '\r' || newDelimiter == delimiterChar || newDelimiter == quoteChar ) throw new BadDelimiterException ();

    delimiterChar = newDelimiter;
    }

    /**
     *
     */

    public void changeQuote ( char newQuote ) throws BadQuoteException
    {
    if ( quoteChar == newQuote ) return;

    if ( newQuote == '\n' || newQuote == '\r' || newQuote == delimiterChar || newQuote == quoteChar ) throw new BadQuoteException ();

    quoteChar = newQuote;
    }

    /**
     *
     */

    public void println ( String value )

    { try { writeln ( value ); } catch ( IOException iox ) { error = true; } }

    /**
     *
     */

    public void writeln ( String value ) throws IOException

    { try { write ( value ); writeln (); } catch ( IOException iox ) { error = true; throw iox; } }

    /**
     *
     */

    public void println ()

    { try { writeln (); } catch ( IOException iox ) { error = true; } }

    /**
     *
     */

    public void writeln () throws IOException
    {
    try {

      out.write ( "\n" );

      if ( autoFlush ) flush ();

      newLine = true;

    } catch ( IOException iox ) { error = true; throw iox; }

    }

    /**
     *
     */

    public void println ( String[] values )

    { try { writeln ( values ); } catch ( IOException iox ) { error = true; } }

    /**
     *
     */

    public void writeln ( String[] values ) throws IOException

    { try { print ( values ); writeln (); } catch ( IOException iox ) { error = true; throw iox; } }

    /**
     *
     */

    public void print ( String[] values )

    { try { write ( values ); } catch ( IOException iox ) { error = true; } }

    /**
     *
     */

    public void write ( String[] values ) throws IOException
    {
    try {

      for ( int i = 0; i < values.length; i++ ) write ( values[ i ] );

    } catch ( IOException iox ) { error = true; throw iox;}

    }

    /**
     *
     */

    public void println ( String[][] values )

    { try { writeln ( values ); } catch ( IOException iox ) { error = true; } }

    /**
     *
     */

    public void writeln ( String[][] values ) throws IOException
    {
    try {

      for ( int i = 0; i < values.length; i++ ) writeln ( values[ i ] );

      if ( values.length == 0 ) writeln ();

    } catch ( IOException iox ) { error = true; throw iox; }

    }

    /**
     *
     */

    public void printlnComment ( String comment ) { println (); }

    /**
     *
     */

    public void writelnComment ( String comment ) throws IOException { writeln (); }

    /**
     *
     */

    public void print ( String value )

    { try { write ( value ); } catch ( IOException iox ) { error = true; } }

    /**
     *
     */

    public void write ( String value ) throws IOException
    {
    try {

      if ( value == null ) value = "";

      boolean quote = false;

      if ( alwaysQuote ) quote = true;

      else
      {
      if ( value.length () > 0 )
      {
      for ( int i = 0; i < value.length (); i++ )
      {
      char c = value.charAt ( i );

      if ( c == quoteChar || c == delimiterChar || c == '\n' || c == '\r' ) quote = true;
      }

      } else { if ( newLine ) quote = true; }

      }
      if ( newLine ) newLine = false; else out.write ( delimiterChar );

      if ( quote ) out.write ( escapeAndQuote ( value ) ); else out.write ( value );

      if ( autoFlush )flush ();

    } catch ( IOException iox ) { error = true; throw iox;}

    }

    /**
     *
     */

    private String escapeAndQuote ( String value )
    {
    String s = StringHelper.replace ( value, String.valueOf ( quoteChar ), String.valueOf ( quoteChar ) + String.valueOf ( quoteChar ) );

    return ( new StringBuilder ( 2 + s.length () ) ).append ( quoteChar ).append ( s ).append ( quoteChar ).toString ();
    }

    /**
     *
     */

    public void flush () throws IOException { out.flush (); }

    /**
     *
     */

    public void close () throws IOException { out.close (); }

    /**
     *
     */

    public boolean checkError ()
    {
    try {

      if ( error ) return true;

      flush ();

      if ( error ) return true;

      if ( out instanceof PrintWriter ) error = ( ( PrintWriter ) out ).checkError ();

    } catch ( IOException iox ) { error = true; }

    return error;
    }

    /**
     *
     */

    public void setAlwaysQuote ( boolean alwaysQuote ) { this.alwaysQuote = alwaysQuote; }

    /**
     *
     */

    public void setAutoFlush ( boolean autoFlush ) { this.autoFlush = autoFlush; }

}
