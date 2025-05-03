package ca.jetsphere.core.tier1.tree;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.tree.Tree;
import ca.jetsphere.core.tier1.tree.TreeMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 */

public class TreeYard
{
    /**
     *
     */

    static public void collapse ( OpenSet openSet, Tree tree )
    {
    if ( tree == null ) return;

    openSet.remove ( tree.getId() );

    for ( int cc = 0; cc < tree.getChildCount(); cc++ )

        collapse ( openSet, tree.getChild ( cc ) );
    }

    /**
     *
     */

    static public Tree copy ( Tree tree )
    {
    if ( tree == null ) return null;

    Tree copy = tree.twin();

    for ( int cc = 0; cc < tree.getChildCount(); cc ++ )

        copy.addChild ( copy ( tree.getChild ( cc ) ) );

    return copy;
    }

    /**
     *
     */

    static public void cut ( JDBC jdbc, Tree parent, Tree child )
    {
    if ( parent == null || child == null ) return;

    try {

        delete ( jdbc, child );

        parent.removeChild ( child );

        parent.reorder ( jdbc );

    } catch ( Exception e ) { Common.trace ( e ); }

    }

    /**
     *
     */

    static public void delete ( JDBC jdbc, Tree tree ) throws Exception
    {
    if ( tree == null ) return;

    for ( int cc = 0; cc < tree.getChildCount(); cc ++ )

        delete ( jdbc, tree.getChild ( cc ) );

    tree.delete ( jdbc );
    }

    /**
     *
     */

    static public void expand ( OpenSet openSet, Tree tree )
    {
    if ( tree == null ) return;

    openSet.add ( tree.getId() );

    for ( int cc = 0; cc < tree.getChildCount(); cc++ )

        expand ( openSet, tree.getChild ( cc ) );
    }

    /**
     *
     */

    static public List getLeaves ( Tree tree, boolean sort )

    { List leaves = getLeaves ( tree, new ArrayList() ); if ( sort ) Collections.sort ( leaves ); return leaves; }

    /**
     *
     */

    static public List getLeaves ( Tree tree, List list )
    {
    if ( tree != null )

        if ( tree.isLeaf() ) list.add ( tree ); else

            for ( int cc = 0; cc < tree.getChildCount(); cc ++ )

                getLeaves ( tree.getChild ( cc ), list );

    return list;
    }

    /**
     *
     */

    static public List getLeaves ( TreeMap trees, boolean sort ) { return getLeaves ( trees.getRoot(), sort ); }

    /**
     *
     */

    static public void getNodes ( Tree tree, List list, boolean leavesOnly )
    {
    if ( tree == null ) return;

    if ( ! leavesOnly || tree.isLeaf() )

        list.add ( tree );

    for ( int cc = 0; cc < tree.getChildCount(); cc ++ )

        getNodes ( tree.getChild ( cc ), list, leavesOnly );
    }

    /**
     *
     */

    static public void paste ( JDBC jdbc, TreeMap trees, Tree parent, Tree child )
    {
    try {

        trees.paste ( parent, child );

        trees.insertTree ( jdbc, child );

        TreeMap.setLineage ( jdbc, parent, child );

    } catch ( Exception e ) { Common.trace ( e ); }

    }

}
