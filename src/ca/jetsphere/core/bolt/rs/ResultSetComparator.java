package ca.jetsphere.core.bolt.rs;

import ca.jetsphere.core.bolt.BoltComparator;

/**
 *
 */

public class ResultSetComparator extends BoltComparator
{
    /**
     *
     */

    public ResultSetComparator() {}

    /**
     *
     */

    public int compareTo ( String s, Object o1, Object o2 )
    {
    try {

        ResultSetBolt rsb1 = ( ResultSetBolt ) o1;

        ResultSetBolt rsb2 = ( ResultSetBolt ) o2;

        o1 = rsb1.get ( s ) ; o2 = rsb2.get ( s );

        return super.compareTo ( o1.getClass(), o1, o2 );

    } catch ( Exception e ) {}

    return 0;
    }

}
