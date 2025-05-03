package ca.jetsphere.core.csv;

import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Vector;

/**
 *
 */

public class ExcelCSVParser implements CSVParse
{
    /**
     *
     */

    private InputStream inStream; private Reader inReader; private String tokenCache;

    private ExcelCSVLexer lexer;

    private int lineCache; private int lastLine = -1;

    /**
     *
     */

    public ExcelCSVParser ( InputStream in, char delimiter ) throws BadDelimiterException

    { inStream = in; lexer = new ExcelCSVLexer(in); changeDelimiter ( delimiter ); }

    /**
     *
     */

    public ExcelCSVParser ( InputStream in )

    { inStream = in; lexer = new ExcelCSVLexer(in); }

    /**
     *
     */

    public ExcelCSVParser ( Reader in, char delimiter ) throws BadDelimiterException

    { inReader = in; lexer = new ExcelCSVLexer(in); changeDelimiter ( delimiter ); }

    /**
     *
     */

    public ExcelCSVParser ( Reader in )

    { inReader = in; lexer = new ExcelCSVLexer(in); }

    /**
     *
     */

    public void close () throws IOException

    { if ( inStream != null ) inStream.close (); if ( inReader != null ) inReader.close (); }

    /**
     *
     */

    public String nextValue() throws IOException
    {
    if ( tokenCache == null )

    { tokenCache = lexer.getNextToken (); lineCache = lexer.getLineNumber (); }

    lastLine = lineCache; String result = tokenCache; tokenCache = null;

    return result;
    }

    /**
     *
     */

    public int lastLineNumber() { return lastLine; }

    /**
     *
     */

    public String[] getLine() throws IOException
    {
    int lineNumber = -1;

    Vector v = new Vector();

    if ( tokenCache != null ) { v.add ( tokenCache ); lineNumber = lineCache; }

    while ( ( tokenCache = lexer.getNextToken () ) != null && ( lineNumber == -1 || lexer.getLineNumber () == lineNumber ) )

    { v.add ( tokenCache ); lineNumber = lexer.getLineNumber ();}

    if ( v.size () == 0 ) return null;

    lastLine = lineNumber; lineCache = lexer.getLineNumber (); String[] result = new String [ v.size () ];

    return ( ( String[] ) v.toArray ( result ) );
    }

    /**
     *
     */

    public String[][] getAllValues() throws IOException
    {
    Vector v = new Vector();

    String[] line;

    while ( ( line = getLine () ) != null ) v.add ( line );

    if ( v.size () == 0 ) return null;

    String[][] result = new String [ v.size () ][];

    return ( ( String[][] ) v.toArray ( result ) );
    }

    /**
     *
     */

    public void changeDelimiter ( char newDelim ) throws BadDelimiterException

    { lexer.changeDelimiter ( newDelim ); }

    /**
     *
     */

    public void changeQuote ( char newQuote ) throws BadQuoteException

    { lexer.changeQuote ( newQuote ); }

    /**
     *
     */

    public void setCommentStart ( String commentDelims ) { lexer.setCommentStart ( commentDelims ); }

    /**
     *
     */

    public int getLastLineNumber () { return lastLine; }

    /**
     *
     */

    public static String[][] parse ( String s )
    {
    try {

      return ( new ExcelCSVParser ( new StringReader ( s ) ) ).getAllValues ();

    } catch ( IOException x ) { return null; }

    }

    /**
     *
     */

    public static String[][] parse ( String s, char delimiter ) throws BadDelimiterException
    {
    try {

      return ( new ExcelCSVParser ( new StringReader ( s ), delimiter ) ).getAllValues ();

    } catch ( IOException x ) { return null; }

    }

    /**
     *
     */

    public static String[][] parse ( Reader in ) throws IOException

    { return ( new ExcelCSVParser ( in ) ).getAllValues (); }

    /**
     *
     */

    public static String[][] parse ( Reader in, char delimiter ) throws IOException, BadDelimiterException

    { return ( new ExcelCSVParser ( in, delimiter ) ).getAllValues (); }
}
