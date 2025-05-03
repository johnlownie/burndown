package ca.jetsphere.core.tier1.tree;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 */

public class OpenSet implements Serializable
{
    HashMap map;

    /**
     *
     */

    public OpenSet() { map = new HashMap(); }

    /**
     *
     */

    public void add ( int key ) { map.put ( "" + key, "" + key ); }

    /**
     *
     */

    public void add ( int key, boolean add ) { if ( add ) add ( key ); else remove ( key ); }

    /**
     *
     */

    public void clear() { map.clear(); }

    /**
     *
     */

    public boolean contains ( int key ) { return map.containsKey ( "" + key ); }

    /**
     *
     */

    public void toggle ( int key )

    { if ( key < 0 ) return; if ( contains ( key ) ) remove ( key ); else add ( key ); }

    /**
     *
     */

    public void remove ( int key ) { map.remove ( "" + key ); }

    /**
     *
     */

    public int size() { return map.size(); }

}
