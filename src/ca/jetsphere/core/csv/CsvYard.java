package ca.jetsphere.core.csv;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;

import java.util.Iterator;
import java.util.List;

/**
 *
 */

public class CsvYard
{
    /**
     *
     */

    static public String csv ( String s )

    { s = DockYard.strip ( s ); if ( s != null ) s = s.replaceAll ( "\"", "\"\"" ); return "\"" + s + "\""; }

    /**
     *
     */

    static public void csv ( ExcelCSVPrinter ecsvp, List list )
    {
    try {

        Iterator it = list.iterator();

        while ( it.hasNext() )

        { Object o = it.next(); ecsvp.write ( o != null ? o.toString() : "" ); }

        ecsvp.writeln ( "" );

    } catch ( Exception e ) { Common.trace ( e ); }

    }

}
