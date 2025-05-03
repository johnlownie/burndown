package ca.jetsphere.core.tier0.backbone.company_member;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */

public class CompanyMemberDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, CompanyMember companyMember ) throws Exception

    { return delete ( jdbc, companyMember, "delete from jet_base_company_member where company_member_id = " + companyMember.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    CompanyMember companyMember = ( CompanyMember ) bolt;

    String s = "insert into jet_base_company_member "
            + "( company_member_uuid, company_member_user_id, company_member_company_id, company_member_firstname, company_member_lastname, company_member_street, company_member_city, company_member_province, company_member_postal_code, company_member_home_phone, company_member_mobile_phone, company_member_work_phone, company_member_work_fax, company_member_email, company_member_avatar_id, company_member_subscribed, company_member_last_update, company_member_created )"
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "company_member_id" } );

    companyMember.setUuid ( UUID.get() ); companyMember.setCreated ( new Timestamp( System.currentTimeMillis() ) ); companyMember.setLastUpdate ( companyMember.getCreated() );

    ps.setString    (  1, companyMember.getUuid            () );
    ps.setInt       (  2, companyMember.getUserId          () );
    ps.setInt       (  3, companyMember.getCompanyId       () );
    ps.setString    (  4, companyMember.getFirstname       () );
    ps.setString    (  5, companyMember.getLastname        () );
    ps.setString    (  6, companyMember.getStreet          () );
    ps.setString    (  7, companyMember.getCity            () );
    ps.setString    (  8, companyMember.getProvince        () );
    ps.setString    (  9, companyMember.getPostalCode      () );
    ps.setString    ( 10, companyMember.getHomePhone       () );
    ps.setString    ( 11, companyMember.getMobilePhone     () );
    ps.setString    ( 12, companyMember.getWorkPhone       () );
    ps.setString    ( 13, companyMember.getWorkFax         () );
    ps.setString    ( 14, companyMember.getEmail           () );
    ps.setInt       ( 15, companyMember.getAvatarId        () );
    ps.setBoolean   ( 16, companyMember.getSubscribed      () );
    ps.setTimestamp ( 17, companyMember.getLastUpdate      () );
    ps.setTimestamp ( 18, companyMember.getCreated         () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    CompanyMember companyMember = ( CompanyMember ) bolt;

    String s = "update jet_base_company_member "
            + "set company_member_uuid = ?, company_member_user_id = ?, company_member_company_id = ?, company_member_firstname = ?, company_member_lastname = ?, company_member_street = ?, company_member_city = ?, company_member_province = ?, company_member_postal_code = ?, company_member_home_phone= ?, company_member_mobile_phone = ?, company_member_work_phone = ?, company_member_work_fax = ?, company_member_email = ?, company_member_avatar_id = ?, company_member_subscribed = ?, company_member_last_update = ? "
            + "where company_member_id = ?";

    ps.setStatement ( s );

    companyMember.setLastUpdate ( new Timestamp ( System.currentTimeMillis()));

    ps.setString    (  1, companyMember.getUuid        () );
    ps.setInt       (  2, companyMember.getUserId      () );
    ps.setInt       (  3, companyMember.getCompanyId   () );
    ps.setString    (  4, companyMember.getFirstname   () );
    ps.setString    (  5, companyMember.getLastname    () );
    ps.setString    (  6, companyMember.getStreet      () );
    ps.setString    (  7, companyMember.getCity        () );
    ps.setString    (  8, companyMember.getProvince    () );
    ps.setString    (  9, companyMember.getPostalCode  () );
    ps.setString    ( 10, companyMember.getHomePhone   () );
    ps.setString    ( 11, companyMember.getMobilePhone () );
    ps.setString    ( 12, companyMember.getWorkPhone   () );
    ps.setString    ( 13, companyMember.getWorkFax     () );
    ps.setString    ( 14, companyMember.getEmail       () );
    ps.setInt       ( 15, companyMember.getAvatarId    () );
    ps.setBoolean   ( 16, companyMember.getSubscribed  () );
    ps.setTimestamp ( 17, companyMember.getLastUpdate  () );
    ps.setInt       ( 18, companyMember.getId          () );
    }

}
