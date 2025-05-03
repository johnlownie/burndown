package ca.jetsphere.core.tier0.backbone.brand;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 * 
 */

public class BrandDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, Brand brand ) throws Exception

    { return delete ( jdbc, brand, "delete from jet_brand where brand_id = " + brand.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Brand brand = ( Brand ) bolt;

    String s = "insert into jet_brand "
            + "( brand_uuid, brand_company_id, brand_name, brand_url, brand_logo, brand_background, brand_copyright, brand_default, brand_last_update, brand_created )"
            + "values (?,?,?,?,?,?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "brand_id" } );

    brand.setUuid ( UUID.get () ); brand.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); brand.setLastUpdate ( brand.getCreated() );

    ps.setString    (  1, brand.getUuid       () );
    ps.setInt       (  2, brand.getCompanyId  () );
    ps.setString    (  3, brand.getName       () );
    ps.setString    (  4, brand.getUrl        () );
    ps.setString    (  5, brand.getLogo       () );
    ps.setString    (  6, brand.getBackground () );
    ps.setString    (  7, brand.getCopyright  () );
    ps.setBoolean   (  8, brand.getDefault    () );
    ps.setTimestamp (  9, brand.getLastUpdate () );
    ps.setTimestamp ( 10, brand.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Brand brand = ( Brand ) bolt;

    String s = "update jet_brand "
            + "set brand_uuid = ?, brand_company_id = ?, brand_name = ?, brand_url = ?, brand_logo = ?, brand_background = ?, brand_copyright = ?, brand_default = ?, brand_last_update = ? "
            + "where brand_id = ?";

    ps.setStatement ( s );

    brand.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, brand.getUuid       () );
    ps.setInt       (  2, brand.getCompanyId  () );
    ps.setString    (  3, brand.getName       () );
    ps.setString    (  4, brand.getUrl        () );
    ps.setString    (  5, brand.getLogo       () );
    ps.setString    (  6, brand.getBackground () );
    ps.setString    (  7, brand.getCopyright  () );
    ps.setBoolean   (  8, brand.getDefault    () );
    ps.setTimestamp (  9, brand.getLastUpdate () );
    ps.setInt       ( 10, brand.getId         () );
    }

}
