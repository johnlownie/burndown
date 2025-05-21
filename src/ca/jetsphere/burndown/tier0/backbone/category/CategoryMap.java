package ca.jetsphere.burndown.tier0.backbone.category;

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

    public void find ( JDBC jdbc, int application_id ) { query ( jdbc, getQuery ( application_id ) ); treeify(); }

    /**
     *
     */

    public String getKey() { return Category.key (); }

    /**
     *
     */

    protected String getQuery ( int id )

    { return "select * from jet_burndown_category where category_application_id = " + id; }

}
