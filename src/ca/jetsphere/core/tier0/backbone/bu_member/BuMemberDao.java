package ca.jetsphere.core.tier0.backbone.bu_member;

import java.sql.Timestamp;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

/**
 *
 */

public class BuMemberDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, BuMember buMember ) throws Exception

    { return delete ( jdbc, buMember, "delete from jet_base_bu_member where bu_member_id = " + buMember.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    BuMember buMember = ( BuMember ) bolt;

    String s = "insert into jet_base_bu_member "
             + "( bu_member_uuid, bu_member_bu_id, bu_member_company_member_id, bu_member_role_ids, bu_member_last_update, bu_member_created )"
             + "values (?,?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "bu_member_id" } );

    buMember.setUuid ( UUID.get() ); buMember.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); buMember.setLastUpdate ( buMember.getCreated() );

    ps.setString    ( 1, buMember.getUuid            () );
    ps.setInt       ( 2, buMember.getBuId            () );
    ps.setInt       ( 3, buMember.getCompanyMemberId () );
    ps.setString    ( 4, buMember.getRoleIds         () );
    ps.setTimestamp ( 5, buMember.getLastUpdate      () );
    ps.setTimestamp ( 6, buMember.getCreated         () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    BuMember buMember = ( BuMember ) bolt;

    String s = "update jet_base_bu_member "
             + "set bu_member_uuid = ?, bu_member_bu_id = ?, bu_member_company_member_id = ?, bu_member_role_ids = ?, bu_member_last_update = ? "
             + "where bu_member_id = ?";

    ps.setStatement ( s );

    buMember.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    ( 1, buMember.getUuid            () );
    ps.setInt       ( 2, buMember.getBuId            () );
    ps.setInt       ( 3, buMember.getCompanyMemberId () );
    ps.setString    ( 4, buMember.getRoleIds         () );
    ps.setTimestamp ( 5, buMember.getLastUpdate      () );
    ps.setInt       ( 6, buMember.getId              () );
    }

}
