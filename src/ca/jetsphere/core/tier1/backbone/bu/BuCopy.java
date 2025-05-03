package ca.jetsphere.core.tier1.backbone.bu;

import java.util.Iterator;

import ca.jetsphere.core.bolt.BoltCopyMap;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.bu_member.BuMemberCopy;

/**
 *
 */

public class BuCopy
{
    BoltCopyMap copyMap;

    /**
     *
     */

    public BuCopy ( BoltCopyMap copyMap ) { this.copyMap = copyMap; }

    /**
     *
     */

    public void copy ( JDBC jdbc, int at$bu_set_id, int to$bu_set_id ) throws Exception
    {
    Synchronize.set ( jdbc, at$bu_set_id );

    BuYard.delete ( jdbc, to$bu_set_id );

    BuSession bus = new BuSession ( jdbc, at$bu_set_id );

    Iterator it = bus.iterator ( false );

    while ( it.hasNext() )

      copy ( jdbc, to$bu_set_id, ( Bu ) it.next() );

    square ( jdbc, to$bu_set_id );

    Lineage.set ( jdbc, to$bu_set_id );
    }

    /**
     *
     */

    public void copy ( JDBC jdbc, int to$period_id, Bu bu ) throws Exception
    {
    int at$bu_id = bu.getId();

    bu.setPeriodId ( to$period_id ); bu.copySave ( jdbc );

    int to$bu_id = bu.getId();

    copyMap.put ( Bu.key(), at$bu_id, to$bu_id );

    new BuMemberCopy ( copyMap ).copy ( jdbc, at$bu_id, to$bu_id );
    }

    /**
     *
     */

    public void square ( JDBC jdbc, int to$bu_set_id ) throws Exception
    {
    BuSession bus = new BuSession ( jdbc, to$bu_set_id );

    Iterator it = bus.iterator ( false );

    while ( it.hasNext() )

      square ( jdbc, to$bu_set_id, ( Bu ) it.next() );
    }

    /**
     *
     */

    public void square ( JDBC jdbc, int to$bu_set_id, Bu bu ) throws Exception
    {
    int parent_id = copyMap.get ( Bu.key(), bu.getParentId() );

    bu.setParentId ( parent_id );

    bu.save ( jdbc );
    }

}

