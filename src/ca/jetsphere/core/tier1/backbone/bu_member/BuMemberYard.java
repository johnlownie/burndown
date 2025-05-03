package ca.jetsphere.core.tier1.backbone.bu_member;

import ca.jetsphere.core.jdbc.JDBC;

import java.util.Iterator;

/**
 *
 */

public class BuMemberYard
{
    /**
     *
     */

    static public void delete ( JDBC jdbc, int bu_id ) throws Exception
    {
    BuMemberSession buMembers = new BuMemberSession ( jdbc, bu_id );

    Iterator it = buMembers.iterator ( false );

    while ( it.hasNext() )

    { BuMember buMember = ( BuMember ) it.next(); buMember.delete ( jdbc ); }

    }

    /**
     *
     */

    static public BuMemberSession get ( JDBC jdbc, int period_id, int company_member_id )
    {
    BuMemberSession buMembers = new BuMemberSession();

    String query = "select * from jet_base_bu inner join jet_base_bu_member on bu_member_bu_id = bu_id where bu_period_id = " + period_id + " and bu_member_company_member_id = " + company_member_id;

    buMembers.query ( jdbc, query );

    return buMembers;
    }

    /**
     *
     */

    static public BuMember getBuMember ( JDBC jdbc, int bu_id, int company_member_id )
    {
    BuMember buMember = new BuMember();

    String query = "select * from jet_base_bu_member where bu_member_bu_id = " + bu_id + " and bu_member_company_member_id = " + company_member_id;

    buMember.query ( jdbc, query );

    return buMember;
    }

}

