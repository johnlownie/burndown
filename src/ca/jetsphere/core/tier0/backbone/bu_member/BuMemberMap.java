package ca.jetsphere.core.tier0.backbone.bu_member;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class BuMemberMap extends BoltMap
{
    /**
     *
     */

    public BuMemberMap() { super(); }

    /**
     *
     */

    public BuMemberMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int period_id, int bu_id )
    {
    String query = "select jet_base_bu_member.* from jet_base_bu_member";

    if ( bu_id     > 0 ) query += " where bu_member_bu_id = " + bu_id; else

    if ( period_id > 0 ) query += " inner join jet_base_bu on bu_id = bu_member_bu_id where bu_period_id = " + period_id;

    query ( jdbc, query );
    }

    /**
     *
     */

    public String getKey() { return BuMember.key(); }

    /**
     *
     */

    public BuMember getBuMember ( int id ) { return ( BuMember ) get ( id ); }

}

