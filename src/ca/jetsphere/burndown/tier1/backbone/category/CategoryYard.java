package ca.jetsphere.burndown.tier1.backbone.category;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import java.util.Comparator;
import java.util.Map;

/**
 *
 */

public class CategoryYard
{
    /**
     *
     */
    static public Comparator BY_ORDINAL = new Comparator()
    {
    public int compare ( Object o1, Object o2 )
    {
    Category c1 = ( Category ) o1; Category c2 = ( Category ) o2;

    return DockYard.compareTo ( c1.getLineage() + DockYard.zeroPad ( c1.getOrdinal(), 2 ), c2.getLineage() + DockYard.zeroPad ( c2.getOrdinal(), 2 ) );
    }

    };

    /**
     *
     */
    static public int getCount ( JDBC jdbc, int company_id, int parent_id )

    { return QueryYard.query ( jdbc, "select count(1) from jet_burndown_category where category_company_id = " + company_id + " and category_parent_id = " + parent_id ); }

    /**
     *
     */
    static public String getTreeName ( int category_id )
    {
    StringBuilder sb = new StringBuilder();
    
    sb.append ( "select if(p.category_depth < 1, c.category_name, concat(p.category_name, ': ', c.category_name))" );
    sb.append ( " from jet_burndown_category c" );
    sb.append ( " inner join jet_burndown_category p on p.category_id = c.category_parent_id" );
    sb.append ( " where c.category_id = " + category_id );
    
    return QueryYard.query ( sb.toString(), 1 );
    }
    
    /**
     * 
     */
    static public Map<Integer, String> getTreeNames ( JDBC jdbc, int company_id )
    {
    String query = "select category_id, concat(category_depth, ':', category_name) from jet_burndown_category where category_company_id = " + company_id + " and category_included order by concat(category_lineage, lpad(category_ordinal, 2, 0))";
    
    return QueryYard.getIntStringMap ( jdbc, query, 1, 2 );
    }
    
    /**
     * 
     */
    static public boolean hasChildren ( JDBC jdbc, int category_id )
    {
    String query = "select count(1) from jet_burndown_category where category_parent_id = " + category_id;
    
    return QueryYard.query ( jdbc, query ) > 0;
    }
}
