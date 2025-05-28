package ca.jetsphere.burndown.tier0.backbone.category;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */
public class CategoryDao extends AbstractDao
{
    /**
     *
     */
    public boolean delete ( JDBC jdbc, Category category) throws Exception

    {  return delete ( jdbc, category, "delete from jet_burndown_category where category_id = " + category.getId() ); }

    /**
     *
     */
    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Category category = ( Category ) bolt;

    String s = "insert into jet_burndown_category" +
            " ( category_uuid, category_application_id, category_parent_id, category_parent_uuid, category_depth, category_lineage, category_ordinal, category_name, category_included, category_fixed, category_last_update, category_created )" +
            " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

    ps.setStatement ( s, new String [] { "category_id" } );

    category.setUuid ( UUID.get () ); category.setCreated ( new Timestamp ( System.currentTimeMillis() ) ); category.setLastUpdate ( category.getCreated() );

    ps.setString    (  1, category.getUuid          () );
    ps.setInt       (  2, category.getApplicationId () );
    ps.setInt       (  3, category.getParentId      () );
    ps.setString    (  4, category.getParentUuid    () );
    ps.setInt       (  5, category.getDepth         () );
    ps.setString    (  6, category.getLineage       () );
    ps.setInt       (  7, category.getOrdinal       () );
    ps.setString    (  8, category.getName          () );
    ps.setBoolean   (  9, category.getIncluded      () );
    ps.setBoolean   ( 10, category.getFixed         () );
    ps.setTimestamp ( 11, category.getLastUpdate    () );
    ps.setTimestamp ( 12, category.getCreated       () );
    }

    /**
     *
     */
    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Category category = ( Category ) bolt;

    String s = "update jet_burndown_category set category_uuid = ?, category_application_id = ?, category_parent_id = ?, category_parent_uuid = ?, category_depth = ?, category_lineage = ?, category_ordinal = ?, category_name = ?, category_included = ?, category_fixed = ?, category_last_update = ? where category_id = ?";

    ps.setStatement ( s );

    category.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, category.getUuid          () );
    ps.setInt       (  2, category.getApplicationId () );
    ps.setInt       (  3, category.getParentId      () );
    ps.setString    (  4, category.getParentUuid    () );
    ps.setInt       (  5, category.getDepth         () );
    ps.setString    (  6, category.getLineage       () );
    ps.setInt       (  7, category.getOrdinal       () );
    ps.setString    (  8, category.getName          () );
    ps.setBoolean   (  9, category.getIncluded      () );
    ps.setBoolean   ( 10, category.getFixed         () );
    ps.setTimestamp ( 11, category.getLastUpdate    () );
    ps.setInt       ( 12, category.getId            () );
    }
}
