package ca.jetsphere.core.common;

/**
 *
 */

public class Pair implements Comparable
{
    Object key, value;

    /**
     *
     */

    public Pair ( Object key, Object value )

    { setKey ( key ); setValue ( value ); }

    /**
     *
     */

    public int compareTo ( Object o )
    {
    Pair pair = ( Pair ) o;

    return DockYard.compareTo ( ( String ) getValue(), ( String ) pair.getValue() );
    }

    /**
     *
     */

    public Object getKey() { return key; }

    /**
     *
     */

    public int getKeyAsInt() { return ( ( Integer ) key ).intValue(); }

    /**
     *
     */

    public Object getValue() { return value; }

    /**
     *
     */

    public int getValueAsInt() { return ( ( Integer ) value ).intValue(); }

    /**
     *
     */

    public void setKey ( Object key )

    { this.key = key; }

    /**
     *
     */

    public void setValue ( Object value )

    { this.value = value; }

}
