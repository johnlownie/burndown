package ca.jetsphere.core.tier0.backbone.bu;

import java.sql.Timestamp;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

/**
 *
 */

public class BuDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, Bu bu ) throws Exception

    { return delete ( jdbc, bu, "delete from jet_base_bu where bu_id = " + bu.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Bu bu = ( Bu ) bolt;

    String s = "insert into jet_base_bu "
             + "( bu_uuid, bu_period_id, bu_parent_id, bu_parent_uuid, bu_depth, bu_lineage, bu_ordinal, bu_type_id, bu_name, bu_short_name, bu_address, bu_city, bu_province, bu_postal_code, bu_phone, bu_fax, bu_url, bu_notes, bu_last_update, bu_created )"
             + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "bu_id" } );

    bu.setUuid ( UUID.get() ); bu.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); bu.setLastUpdate ( bu.getCreated() );

    ps.setString    (  1, bu.getUuid       () );
    ps.setInt       (  2, bu.getPeriodId   () );
    ps.setInt       (  3, bu.getParentId   () );
    ps.setString    (  4, bu.getParentUuid () );
    ps.setInt       (  5, bu.getDepth      () );
    ps.setString    (  6, bu.getLineage    () );
    ps.setInt       (  7, bu.getOrdinal    () );
    ps.setInt       (  8, bu.getTypeId     () );
    ps.setString    (  9, bu.getName       () );
    ps.setString    ( 10, bu.getShortName  () );
    ps.setString    ( 11, bu.getAddress    () );
    ps.setString    ( 12, bu.getCity       () );
    ps.setString    ( 13, bu.getProvince   () );
    ps.setString    ( 14, bu.getPostalCode () );
    ps.setString    ( 15, bu.getPhone      () );
    ps.setString    ( 16, bu.getFax        () );
    ps.setString    ( 17, bu.getUrl        () );
    ps.setString    ( 18, bu.getNotes      () );
    ps.setTimestamp ( 19, bu.getLastUpdate () );
    ps.setTimestamp ( 20, bu.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Bu bu = ( Bu ) bolt;

    String s = "update jet_base_bu "
             + "set bu_uuid = ?, bu_period_id = ?, bu_parent_id = ?, bu_parent_uuid = ?, bu_depth = ?, bu_lineage = ?, bu_ordinal = ?, bu_type_id = ?, bu_name = ?, bu_short_name = ?, bu_address = ?, bu_city = ?, bu_province = ?, bu_postal_code = ?, bu_phone = ?, bu_fax = ?, bu_url = ?, bu_notes = ?, bu_last_update = ? "
             + "where bu_id = ?";

    ps.setStatement ( s );

    bu.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, bu.getUuid       () );
    ps.setInt       (  2, bu.getPeriodId   () );
    ps.setInt       (  3, bu.getParentId   () );
    ps.setString    (  4, bu.getParentUuid () );
    ps.setInt       (  5, bu.getDepth      () );
    ps.setString    (  6, bu.getLineage    () );
    ps.setInt       (  7, bu.getOrdinal    () );
    ps.setInt       (  8, bu.getTypeId     () );
    ps.setString    (  9, bu.getName       () );
    ps.setString    ( 10, bu.getShortName  () );
    ps.setString    ( 11, bu.getAddress    () );
    ps.setString    ( 12, bu.getCity       () );
    ps.setString    ( 13, bu.getProvince   () );
    ps.setString    ( 14, bu.getPostalCode () );
    ps.setString    ( 15, bu.getPhone      () );
    ps.setString    ( 16, bu.getFax        () );
    ps.setString    ( 17, bu.getUrl        () );
    ps.setString    ( 18, bu.getNotes      () );
    ps.setTimestamp ( 19, bu.getLastUpdate () );
    ps.setInt       ( 20, bu.getId         () );
    }

}
