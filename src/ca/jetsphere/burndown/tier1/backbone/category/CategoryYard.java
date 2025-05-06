package ca.jetsphere.burndown.tier1.backbone.category;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;

/**
 *
 */

public class CategoryYard
{
    /**
     *
     */
    static public int getCount ( JDBC jdbc, int company_id, int parent_id )

    { return QueryYard.query ( jdbc, "select count(1) from jet_burndown_category where category_company_id = " + company_id + " and category_parent_id = " + parent_id ); }

    /**
     *
     */
    static public String getTreeName ( int parent_id, String name )
    {
    String parent_name = QueryYard.query( "select category_name from jet_burndown_category where category_id = " + parent_id, 1 );
    
    return parent_name + ": " + name;
    }
}
