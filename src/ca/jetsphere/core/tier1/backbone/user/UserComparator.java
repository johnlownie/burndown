package ca.jetsphere.core.tier1.backbone.user;

import ca.jetsphere.core.bolt.BoltComparator;
import ca.jetsphere.core.common.DockYard;

/**
 * 
 */

public class UserComparator extends BoltComparator
{
    /**
     *
     */

    public UserComparator() {}

    /**
     *
     */

    public int compareTo ( Class c, Object o1, Object o2 )
    {
    if ( c != String.class ) return super.compareTo ( c, o1, o2 );

    try {

        String s1 = ( String ) o1; if ( s1 == null ) s1 = "";

        String s2 = ( String ) o2; if ( s2 == null ) s2 = "";

        return DockYard.compareTo ( s1.toLowerCase(), s2.toLowerCase() );

    } catch ( Exception e ) {}

    return 0;
    }

}
