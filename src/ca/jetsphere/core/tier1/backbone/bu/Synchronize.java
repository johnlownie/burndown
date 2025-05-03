package ca.jetsphere.core.tier1.backbone.bu;

import java.util.Iterator;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

public class Synchronize
{
    /**
     *
     */

    static public void set ( JDBC jdbc, int bu_set_id ) throws Exception
    {
    BuSession bus = new BuSession ( jdbc, bu_set_id );

    Iterator it = bus.iterator ( false );

    while ( it.hasNext() )
    {
    Bu bu = ( Bu ) it.next();

    if ( bu.getParentId() == 0 ) continue ;

    Bu parent = new Bu ( jdbc, bu.getParentId() );

    if ( DockYard.compareTo ( parent.getUuid(), bu.getParentUuid() ) == 0 ) continue;

    bu.setParentUuid ( parent.getUuid() );

    bu.update ( jdbc );
    } }

}
