package ca.jetsphere.core.tier0.backbone.company;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */

public class CompanyDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, Company company ) throws Exception
    {
    // CompanyMemberDelete.delete ( jdbc, company.getId() ); ApplicationDelete.delete ( jdbc, company.getId() ); ActionDelete.delete ( jdbc, company.getId() );

    return delete ( jdbc, company, "delete from jet_base_company where company_id = " + company.getId() );
    }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Company company = ( Company ) bolt;

    String s = "insert into jet_base_company "
            + "( company_uuid, company_name, company_street, company_city, company_province, company_postal_code, company_phone, company_fax, company_url, company_email, company_default, company_last_update, company_created )"
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "company_id" } );

    company.setUuid ( UUID.get() ); company.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); company.setLastUpdate ( company.getCreated() );

    ps.setString    (  1, company.getUuid       () );
    ps.setString    (  2, company.getName       () );
    ps.setString    (  3, company.getStreet     () );
    ps.setString    (  4, company.getCity       () );
    ps.setString    (  5, company.getProvince   () );
    ps.setString    (  6, company.getPostalCode () );
    ps.setString    (  7, company.getPhone      () );
    ps.setString    (  8, company.getFax        () );
    ps.setString    (  9, company.getUrl        () );
    ps.setString    ( 10, company.getEmail      () );
    ps.setBoolean   ( 11, company.getDefault    () );
    ps.setTimestamp ( 12, company.getLastUpdate () );
    ps.setTimestamp ( 13, company.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Company company = ( Company ) bolt;

    String s = "update jet_base_company "
            + "set company_uuid = ?, company_name = ?, company_street = ?, company_city = ?, company_province= ?, company_postal_code = ?, company_phone = ?, company_fax = ?, company_url = ?, company_email = ?, company_default = ?, company_last_update = ? "
            + "where company_id = ?";

    ps.setStatement ( s );

    company.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, company.getUuid       () );
    ps.setString    (  2, company.getName       () );
    ps.setString    (  3, company.getStreet     () );
    ps.setString    (  4, company.getCity       () );
    ps.setString    (  5, company.getProvince   () );
    ps.setString    (  6, company.getPostalCode () );
    ps.setString    (  7, company.getPhone      () );
    ps.setString    (  8, company.getFax        () );
    ps.setString    (  9, company.getUrl        () );
    ps.setString    ( 10, company.getEmail      () );
    ps.setBoolean   ( 11, company.getDefault    () );
    ps.setTimestamp ( 12, company.getLastUpdate () );
    ps.setInt       ( 13, company.getId         () );
    }

}
