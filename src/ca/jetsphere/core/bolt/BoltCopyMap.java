package ca.jetsphere.core.bolt;

import ca.jetsphere.core.common.Common;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class BoltCopyMap
{
    Map map = new HashMap(); int size; boolean trace;

    /**
     *
     */

    public BoltCopyMap() { this ( false ); }

    /**
     *
     */

    public BoltCopyMap ( boolean trace ) { clear(); this.trace = trace; }

    /**
     *
     */

    private int awol ( String key, int at )

    { if ( at > 0 ) Common.trace ( "[ COPY MAP : AWOL ] < " + size + " > ( " + key + " ) " + at ); return at; }

    /**
     *
     */

    public void clear() { map.clear(); size = 0; }

    /**
     *
     */

    public int get ( Bolt bolt ) { return get ( bolt.getKey(), bolt.getId() ); }

    /**
     *
     */

    public int get ( String key, int at )
    {
    if ( trace ) Common.trace ( "[ COPY MAP : GET ] < " + size + " > ( " + key + " ) " + at );

    Map copyMap = ( Map ) map.get ( key ); if ( copyMap == null ) return awol ( key, at ); Integer to = ( Integer ) copyMap.get ( new Integer ( at ) ); if ( to == null ) return awol ( key, at ); return to.intValue();
    }

    /**
     *
     */

    public void put ( Bolt at, Bolt to ) { put ( at.getKey(), at.getId(), to.getId() ); }

    /**
     *
     */

    public void put ( String key, int at, int to )
    {
    Map copyMap = ( Map ) map.get ( key ); if ( copyMap == null ) map.put ( key, copyMap = new HashMap() ); copyMap.put ( new Integer ( at ), new Integer ( to ) );

    size += 1; if ( trace ) Common.trace ( "[ COPY MAP : PUT ] < " + size + " > ( " + key + " ) " + at + " > " + to );
    }
}
