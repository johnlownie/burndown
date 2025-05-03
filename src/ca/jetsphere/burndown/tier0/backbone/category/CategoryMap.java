package ca.jetsphere.burndown.tier0.backbone.category;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.tree.TreeSession;

/**
 *
 */

abstract public class CategoryMap extends TreeSession
{
    /**
     *
     */

    public CategoryMap() { super(); }

    /**
     *
     */

    public CategoryMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int company_id )
    {
    String query = "select jet_burndown_category.* from jet_burndown_category";

    if ( company_id > 0 ) query += " where category_company_id = " + company_id;

    query ( jdbc, query );
    }

    /**
     *
     */

    public String getKey() { return Category.key (); }

}
