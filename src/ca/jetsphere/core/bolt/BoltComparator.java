package ca.jetsphere.core.bolt;

import ca.jetsphere.core.common.Common;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 */

public class BoltComparator implements java.util.Comparator
{
    String sort []; boolean ascend;

    /**
     *
     */

    public BoltComparator() {}

    /**
     *
     */

    public BoltComparator ( String sort [], boolean ascend )

    { setSort ( sort, ascend ); }

    /**
     *
     */

    public int compare ( Object o1, Object o2 )
    {
    try {

        return ascend ? compareTo ( sort, o1, o2 ) : compareTo ( sort, o2, o1 );

    } catch ( Exception e ) { Common.trace ( e ); }

    return 0;
    }

    /**
     *
     */

    public int compareTo ( String sort [], Object o1, Object o2 )
    {
    if ( sort != null )

        for ( int cc = 0; cc < sort.length; cc ++ )

        { int x = compareTo ( sort [ cc ], o1, o2 ); if ( x != 0 ) return x; }

    return 0;
    }

    /**
     *
     */

    public int compareTo ( String s, Object o1, Object o2 )
    {
    try {

        Field field = getField ( o1, s ); field.setAccessible ( true );

        return compareTo ( field.getType(), field.get ( o1 ), field.get ( o2 ) );

    } catch ( Exception e ) {}

    try {

        Object s1 = ( ( Bolt ) o1 ).get ( s );

        Object s2 = ( ( Bolt ) o2 ).get ( s );

        return compareTo ( s1.getClass(), s1, s2 );

    } catch ( Exception x ) {}

    return 0;
    }

    /**
     *
     */

    public int compareTo ( Class c, Object o1, Object o2 )
    {
    if ( c == int       .class ) return ( ( Integer    ) o1 ).compareTo ( ( Integer    ) o2 );
    if ( c == Integer   .class ) return ( ( Integer    ) o1 ).compareTo ( ( Integer    ) o2 );
    if ( c == BigDecimal.class ) return ( ( BigDecimal ) o1 ).compareTo ( ( BigDecimal ) o2 );
    if ( c == String    .class ) return ( ( String     ) o1 ).compareTo ( ( String     ) o2 );
    if ( c == Timestamp.class ) return ( ( Timestamp  ) o1 ).compareTo ( ( Timestamp  ) o2 );
    if ( c == Character .class ) return ( ( Character  ) o1 ).compareTo ( ( Character  ) o2 );
    if ( c == Float     .class ) return ( ( Float      ) o1 ).compareTo ( ( Float      ) o2 );
    if ( c == Long      .class ) return ( ( Long       ) o1 ).compareTo ( ( Long       ) o2 );
    if ( c == Double    .class ) return ( ( Double     ) o1 ).compareTo ( ( Double     ) o2 );

    return 0;
    }

    /**
     *
     */

    static public Field getField ( Object o, String s )
    {
    Class c = o.getClass();

    while ( c != null )

        try {

            return c.getDeclaredField ( s );

        } catch ( Exception e ) { c = c.getSuperclass(); }

    return null;
    }

    /**
     *
     */

    public void setSort ( String sort [], boolean ascend )

    { this.sort = sort; this.ascend = ascend; }

}
