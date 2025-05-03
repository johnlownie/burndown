package ca.jetsphere.core.tier0.tree;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 */

abstract public class TreeMap extends BoltMap
{
    Tree root;

    /**
     *
     */

    public TreeMap() { super(); clear(); }

    /**
     *
     */

    public void clear() { super.clear(); root = null; }

    /**
     *
     */

    public void clearChildren()

    { Iterator it = iterator ( false ); while ( it.hasNext() ) { Tree tree = ( Tree ) it.next(); tree.clearChildren(); } }

    /**
     *
     */

    public void depth()

    { if ( getRoot() != null ) { getRoot().setDepth ( 0 ); depth ( getRoot() ); } }

    /**
     *
     */

    public void depth ( Tree parent )
    {
    if ( parent != null )

        for ( int cc = 0; cc < parent.getChildCount(); cc ++ )

        { Tree child = parent.getChild ( cc ); child.setDepth ( parent.getDepth() + 1 ); depth ( child ); }

    }

    /**
     *
     */

    public void dragDrop ( JDBC jdbc, Tree drag, Tree drop ) throws Exception

    { dragDrop ( jdbc, drag, drop, false ); }

    /**
     *
     */

    public void dragDrop ( JDBC jdbc, Tree drag, Tree drop, boolean after ) throws Exception
    {
    if ( drag == null || !drag.isValid() || drop == null || !drop.isValid() ) return;

    Tree parent = getParent ( drag );

    if ( parent == null || !parent.isValid() ) return;

    if ( isSibling ( drag, drop ) ) parent.reorder ( jdbc, drag, drop, after ); else move ( jdbc, drop, drag );
    }

    /**
     *
     */

    public Bolt get1st() { return getRoot() != null ? getRoot() : super.get1st(); }

    /**
     *
     */

    public TreeMap getBonsai ( TreeMap bonzai, List list )
    {
    try {

        Iterator it = list.iterator();

        while ( it.hasNext() )
        {
        Tree tree = ( Tree ) it.next();

        while ( tree != null ) { bonzai.add ( tree ); tree = getParent ( tree ); }
        }

        bonzai.treeify();

    } catch ( Exception e ) { Common.trace ( e ); }

    return bonzai;
    }

    /**
     *
     */

    public Tree getParent ( Tree tree )

    { return tree != null ? getTree ( tree.getParentId() ) : null; }

    /**
     *
     */

    public Tree getRoot() { return root; }

    /**
     *
     */

    public Tree getTree ( int id ) { return ( Tree ) get ( id ); }

    /**
     *
     */

    public boolean hasRoot() { return getRoot() != null; }

    /**
     *
     */

    public void insert ( JDBC jdbc, Tree tree ) throws Exception

    { tree.setId ( -1 ); tree.save ( jdbc ); add ( tree ); }

    /**
     *
     */

    public void insertTree ( JDBC jdbc, Tree parent ) throws Exception
    {
    insert ( jdbc, parent );

    for ( int cc = 0; cc < parent.getChildCount(); cc ++ )

    { Tree child = ( Tree ) parent.getChild ( cc ); child.paste ( parent ); insertTree ( jdbc, child ); }

    }

    /**
     *
     */

    public boolean isAncestor ( Tree ancestor, Tree tree )
    {
    if ( ancestor != null )

        do {

            if ( tree == null ) break; if ( ancestor.hasChild ( tree ) ) return true; tree = getParent ( tree );

        } while ( true );

    return false;
    }

    /**
     *
     */

    public boolean isSibling ( Tree t1, Tree t2 )

    { return t1.getId() != t2.getId() && t1.getParentId() == t2.getParentId(); }

    /**
     *
     */

    public Iterator iterator ( boolean sort ) { return list ( sort ).iterator(); }

    /**
     *
     */

    public void move ( JDBC jdbc, Tree parent, Tree child ) throws Exception
    {
    if ( parent == null || child == null ) return;

    if (  isAncestor ( child , parent ) ) return;

    Tree xparent = getParent ( child ); if ( xparent != null ) xparent.removeChild ( child ); child.setParentId ( parent.getId() ); child.setOrdinal ( parent.getChildCount() ); parent.addChild ( child );

    setLineage ( jdbc, parent, child );
    }

    /**
     *
     */

    abstract public void paste ( Tree parent, Tree child );

    /**
     *
     */

    public Iterator treerator()
    {
    ArrayList list = new ArrayList();

    treerator ( list, getRoot() );

    return list.iterator();
    }

    /**
     *
     */

    public void treerator ( List list, Tree tree )
    {
    list.add ( tree );

    for ( int cc = 0; cc < tree.getChildCount(); cc ++ )

        treerator ( list, tree.getChild ( cc ) );
    }

    /**
     *
     */

    static public void setLineage ( JDBC jdbc, Tree parent, Tree child ) throws Exception
    {
    child.setDepth ( parent.getDepth() + 1 );

    child.setLineage ( parent.getLineage() + parent.getId() + "/" );

    child.save ( jdbc );

    for ( int cc = 0; cc < child.getChildCount(); cc ++ )
    {
    Tree grandChild = ( Tree ) child.getChild ( cc );

    setLineage ( jdbc, child, grandChild );
    }

    }

    /**
     *
     */

    public void setRoot ( Tree root ) { this.root = root; }

    /**
     *
     */

    public void sort()
    {
    Iterator it = iterator ( false );

    while ( it.hasNext() )
    {
    Tree tree = ( Tree ) it.next();

    if ( tree.getChildCount() > 0 ) Collections.sort ( tree.getChildren() );
    }

    }

    /**
     *
     */

    public void treeify()
    {
    clearChildren();

    Iterator it = iterator ( false );

    while ( it.hasNext() )
    {
    Tree tree = ( Tree ) it.next();

    if ( 0 >= tree.getParentId() ) setRoot ( tree ); else

    { Tree parent = ( Tree ) get ( tree.getParentId() ); if ( parent != null ) { parent.addChild ( tree ); } }

    }

    sort(); depth();
    }

}
