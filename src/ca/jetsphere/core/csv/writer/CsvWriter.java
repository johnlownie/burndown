package ca.jetsphere.core.csv.writer;

import ca.jetsphere.core.bolt.rs.ResultSetBolt;
import ca.jetsphere.core.bolt.rs.ResultSetBoltMap;
import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.csv.CsvYard;
import ca.jetsphere.core.csv.ExcelCSVPrinter;

import javax.servlet.http.HttpServletRequest;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 *
 */

public class CsvWriter
{
    HttpServletRequest request; Locale locale; ResultSetBoltMap rsbl;

    /**
     *
     */

    public CsvWriter ( HttpServletRequest request, ResultSetBoltMap rsbl )

    { this.request = request; this.locale = request.getLocale(); this.rsbl = rsbl; }

    /**
     *
     */

    public CsvWriter ( Locale locale, ResultSetBoltMap rsbl )

    { this.locale = locale; this.rsbl = rsbl; }

    /**
     *
     */

    public List getCaptions()
    {
    List list = new ArrayList();

    try {

        for ( int cc = 0; cc < rsbl.getCaptions().length; cc ++ )

            rsbl.setCaption ( cc, Caption.get ( locale, rsbl.getCaption ( cc ) ) );

        return Arrays.asList ( rsbl.getCaptions() );

    } catch ( Exception e ) { return list; }

    }

    /**
     *
     */

    public List getList ( ResultSetBolt bolt ) throws Exception

    { bolt.setLocale ( locale ); return bolt.list(); }

    /**
     *
     */

    public void print ( Writer writer ) throws Exception

    { print ( writer, true ); }

    /**
     *
     */

    public void print ( Writer writer, boolean doCaptions ) throws Exception
    {
    ExcelCSVPrinter $writer = new ExcelCSVPrinter ( writer );

    if ( doCaptions ) CsvYard.csv ( $writer, getCaptions() );

    Iterator it = rsbl.iterator ( true );

    while ( it.hasNext() )

        CsvYard.csv ( $writer, getList ( ( ResultSetBolt ) it.next() ) );
    }

}
