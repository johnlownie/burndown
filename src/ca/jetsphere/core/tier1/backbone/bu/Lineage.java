package ca.jetsphere.core.tier1.backbone.bu;

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

    static public List getLineage ( Bu bu )
    {
    ArrayList list = new ArrayList();

    StringTokenizer st = new StringTokenizer ( bu.getLineage(), "/" );

    while ( st.hasMoreTokens() )

    { String t = st.nextToken(); if ( ! DockYard.isWhiteSpace ( t ) ) list.add ( t ); }

    Collections.reverse ( list );

    return list;
    }

    /**
     *
     */

    static public String getMySql ( int bu_set_id )

    { return "update os_bu as t inner join os_bu as p on t.bu_parent_id = p.bu_id set t.bu_depth = p.bu_depth + 1, t.bu_lineage = concat(p.bu_lineage, t.bu_parent_id, '/' ) where p.bu_depth >= 0 and p.bu_lineage is not null and t.bu_depth is null and p.bu_bu_set_id = " + bu_set_id + " and t.bu_bu_set_id = " + bu_set_id; };

    /**
     *
     */

    static public boolean okay ( JDBC jdbc, int bu_set_id ) throws Exception
    {
    BuSession bus = new BuSession ( jdbc, bu_set_id );

    Iterator it = bus.iterator ( false );

    while ( it.hasNext() )

    { Bu bu = ( Bu ) it.next(); if ( ! okay ( jdbc, bu ) ) return false; }

    return true;
    }

    /**
     *
     */

    static public boolean okay ( JDBC jdbc, Bu bu ) throws Exception
    {
    List list = getLineage ( bu );

    Iterator it = list.iterator();

    while ( it.hasNext() )
    {
    String s = ( String ) it.next();

    Bu parent = new Bu ( jdbc, bu.getParentId() );

    if ( DockYard.toInteger ( s ) != parent.getId() )

    { Common.trace ( "BAD LINEAGE: " + bu.getId() ); return false; }

    bu = parent;
    }

    return true;
    }

    /**
     *
     */

    static public void reset ( JDBC jdbc, int bu_set_id ) throws Exception

    { QueryYard.update ( jdbc, "update os_bu set bu_lineage = null, bu_depth = null where bu_bu_set_id = " + bu_set_id, false ); }

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

    static public void set ( JDBC jdbc, int period_id ) throws Exception
    {
    reset ( jdbc, period_id );

    Common.trace ( "SET LINEAGE: BU-SET # " + period_id );

    QueryYard.update ( jdbc, "update os_bu set bu_lineage = '/', bu_depth = 0 where bu_parent_id = 0 and bu_bu_set_id = " + period_id, false );

    String query = getMySql ( period_id );

    QueryYard.update ( jdbc, query, true );
    }

}
