package ca.jetsphere.core.tier1.backbone.bu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import ca.jetsphere.core.tier1.tree.Tree;
import ca.jetsphere.core.tier1.tree.TreeMap;
import ca.jetsphere.core.tier1.tree.TreeYard;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class BuYard
{
    /**
     *
     */

    static public Comparator BY_ORDINAL = new Comparator()
    {
    public int compare ( Object o1, Object o2 )
    {
    Bu g1 = ( Bu ) o1; Bu g2 = ( Bu ) o2;

    int cc = DockYard.compareTo ( g1.getOrdinal(), g2.getOrdinal() );

    if ( cc != 0 ) return cc;

    return DockYard.compareTo ( g1.getName(), g2.getName() );
    }

    };

    /**
     *
     */

    static public void delete ( JDBC jdbc, int period_id ) throws Exception
    {
    BuSession bus = new BuSession ( jdbc, period_id );

    Iterator it = bus.iterator ( false );

    while ( it.hasNext() )

    { Bu bu = ( Bu ) it.next(); bu.delete ( jdbc ); }

    }

    /**
     *
     */

    static public int getCount ( int period_id, int parent_id )

    { return QueryYard.query ( "select count(1) from jet_base_bu where bu_period_id = " + period_id + " and bu_parent_id = " + parent_id ); }

    /**
     *
     */

    static public Bu getDummy() { Bu bu = new Bu(); bu.setId ( Integer.MAX_VALUE ); return bu; }

    /**
     *
     */

    static public List getBus ( BuSession bus, boolean leavesOnly )
    {
    int root_id = -1;

    try { root_id = bus.getRoot().getId(); } catch ( Exception e ) {}

    return getBus ( bus, root_id, leavesOnly );
    }

    /**
     *
     */

    static public List getBus ( BuSession bus, int bu_id, boolean leavesOnly )
    {
    List list = new ArrayList();

    try {

      TreeYard.getNodes ( bus.getBu ( bu_id ), list, leavesOnly );

    } catch ( Exception e ) { Common.trace ( e ); }

    return list;
    }

    /**
     *
     */

    static public BuSession getBusByBuIds ( BuSession bus, String [] bu_ids ) throws Exception
    {
    BuSession session = new BuSession();

    if ( bu_ids != null )

    for ( int cc = 0; cc < bu_ids.length; cc ++ )
    {
    int bu_id = DockYard.toInteger ( bu_ids [ cc ] );

    session.add ( getBus ( bus, bu_id, true ) );
    }

    return session;
    }

    /**
     *
     */

    static public List getBus ( BuSession bus, int bu_id )

    { return getBus ( bus, bu_id, false ); }

    /**
     * 
     */
    
    static public BuSession getByParent ( JDBC jdbc, HttpServletRequest request, int parent_id )
    {
    BuSession bus = BuSession.getInstance ( request );
        
    String query = "select * from jet_base_bu where bu_parent_id = " + parent_id;
        
    bus.query ( jdbc, query );
        
    return bus;
    }

    /**
     *
     */

    static public boolean isLeaf ( TreeMap trees, int tree_id )
    {
    if ( trees == null ) return false;

    Tree tree = ( Tree ) trees.get ( tree_id );

    return tree != null ? tree.isLeaf() : false;
    }

}
