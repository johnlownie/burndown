package ca.jetsphere.burndown.tier1.backbone.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import ca.jetsphere.core.tier1.backbone.period.PeriodSession;

/**
 *
 */

public class Lineage
{
    /**
     *
     */
    static public List getLineage ( Category category )
    {
    ArrayList list = new ArrayList();

    StringTokenizer st = new StringTokenizer ( category.getLineage(), "/" );

    while ( st.hasMoreTokens() )

    { String t = st.nextToken(); if ( ! DockYard.isWhiteSpace ( t ) ) list.add ( t ); }

    Collections.reverse ( list );

    return list;
    }

    /**
     *
     */
    static public String getMySql ( int application_id )
    {
    StringBuffer sb = new StringBuffer();
    
    sb.append ( "update jet_burndown_category as c " );
    sb.append ( " inner join jet_burndown_category as p on p.category_id = c.category_parent_id" );
    sb.append ( " set c.category_depth = p.category_depth + 1, c.category_lineage = concat(p.category_lineage, lpad(p.category_ordinal, 2, 0), '/' )" );
    sb.append ( " where p.category_depth >= 0" );
    sb.append ( " and p.category_lineage is not null" );
    sb.append ( " and c.category_depth is null" );
    sb.append ( " and p.category_application_id = 2" );
    sb.append ( " and c.category_application_id = 2" );

    return sb.toString();
    }

    /**
     *
     */
    static public boolean okay ( JDBC jdbc, int application_id ) throws Exception
    {
    CategorySession categorys = new CategorySession ( jdbc, application_id );

    Iterator it = categorys.iterator ( false );

    while ( it.hasNext() )

    { Category category = ( Category ) it.next(); if ( ! okay ( jdbc, category ) ) return false; }

    return true;
    }

    /**
     *
     */
    static public boolean okay ( JDBC jdbc, Category category ) throws Exception
    {
    List list = getLineage ( category );

    Iterator it = list.iterator();

    while ( it.hasNext() )
    {
    String s = ( String ) it.next();

    Category parent = new Category ( jdbc, category.getParentId() );

    if ( DockYard.toInteger ( s ) != parent.getId() )

    { Common.trace ( "BAD LINEAGE: " + category.getId() ); return false; }

    category = parent;
    }

    return true;
    }

    /**
     *
     */
    static public void reset ( JDBC jdbc, int application_id ) throws Exception

    { QueryYard.update ( jdbc, "update jet_burndown_category set category_lineage = null, category_depth = null where category_application_id = " + application_id, false ); }

    /**
     *
     */
    static public void set ( JDBC jdbc, HttpServletRequest request ) throws Exception
    {
    int period_id = PeriodSession.getRequestedId ( request );

    set ( jdbc, period_id );
    }

    /**
     *
     */
    static public void set ( JDBC jdbc, int application_id ) throws Exception
    {
    reset ( jdbc, application_id );

    Common.trace ( "SET LINEAGE: BU-SET # " + application_id );

    QueryYard.update ( jdbc, "update jet_burndown_category set category_lineage = '/', category_depth = 0 where category_parent_id = 0 and category_application_id = " + application_id, false );

    String query = getMySql ( application_id );

    QueryYard.update ( jdbc, query, true );
    }
}
