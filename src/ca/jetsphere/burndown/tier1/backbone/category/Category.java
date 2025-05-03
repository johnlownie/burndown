package ca.jetsphere.burndown.tier1.backbone.category;

import ca.jetsphere.core.jdbc.JDBC;

import java.sql.ResultSet;

/**
 *
 */

public class Category extends ca.jetsphere.burndown.tier0.backbone.category.Category
{
    /**
     *
     */
    public Category() { super(); }

    /**
     *
     */
    public Category ( JDBC jdbc )

    { super(); query ( jdbc, "select * from jet_burndown_category"); }

    /**
     *
     */
    public Category ( JDBC jdbc, int category_id ) throws Exception { super ( jdbc, category_id ); }

    /**
     *
     */
    public Category ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */
    static public String [] captions()

    { return new String [] { "category.name", "last.update", "actions" }; }

    /**
     *
     */
    static public String [] fields()

    { return new String [] { "category_name", "category_last_update", "category_uuid" }; }

    /**
     *
     */
    public String getKey() { return key(); }
}
