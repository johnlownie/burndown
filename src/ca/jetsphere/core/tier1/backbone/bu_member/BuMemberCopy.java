package ca.jetsphere.core.tier1.backbone.bu_member;

import java.util.Iterator;

import ca.jetsphere.core.bolt.BoltCopyMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

public class BuMemberCopy
{
    BoltCopyMap copyMap;

    /**
     *
     */

    public BuMemberCopy ( BoltCopyMap copyMap ) { this.copyMap = copyMap; }

    /**
     *
     */

    public void copy ( JDBC jdbc, int at$bu_id, int to$bu_id ) throws Exception
    {
    BuMemberSession buMembers = new BuMemberSession ( jdbc, at$bu_id );

    Iterator it = buMembers.iterator ( true );

    while ( it.hasNext() )

      copy ( jdbc, to$bu_id, ( BuMember ) it.next() );
    }

    /**
     *
     */

    public void copy ( JDBC jdbc, int to$bu_id, BuMember buMember ) throws Exception
    {
    int at$bu_member_id = buMember.getId();

    buMember.setBuId ( to$bu_id ); buMember.copySave ( jdbc );

    int to$bu_member_id = buMember.getId();

    copyMap.put ( BuMember.key(), at$bu_member_id, to$bu_member_id );
    }

}

