package ca.jetsphere.core.tier1.tree;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */

abstract public class Tree extends Bolt
{
    ArrayList tree_children ;
    int       tree_depth     ;

    /**
     *
     */

    public Tree() { super(); tree_children = null; }

    /**
     *
     */

    public int compareTo ( Object o )
    {
    Tree tree = ( Tree ) o;

    if ( getDepth() != tree.getDepth() )

        return new Integer ( getDepth() ).compareTo ( new Integer ( tree.getDepth() ) );

    if ( getOrdinal() != tree.getOrdinal() )

        return new Integer ( getOrdinal() ).compareTo ( new Integer ( tree.getOrdinal() ) );

    return DockYard.compareTo ( getName(), tree.getName() );
    }

    /**
     *
     */

    public void addChild ( Tree child )

    { if ( tree_children == null ) tree_children = new ArrayList(); tree_children.add ( child ); }

    /**
     *
     */

    public void clearChildren() { if ( tree_children != null ) tree_children.clear(); }

    /**
     *
     */

    public Object get ( String s ) { return super.get ( s ); }

    /**
     *
     */

    public String getTableName()
    {
    StringBuilder sb = new StringBuilder();

    sb.append ( "<span>" );
    sb.append ( "<span class=\"node-icon-handle ui-draggable-handle\">&zwnj;</span>" );
//    sb.append ( "<span class=\"node-draggable-pointer\">&zwnj;</span>" );
    sb.append ( "<span class=\"node-indent\" style=\"margin-left: " + ( getDepth() * 16 ) + "px;\">&zwnj;</span>" );
    sb.append ( "<span class=\"node-icon-ce icon\"" + ( isLeaf() ? " style=\"visibility: hidden;\"" : "" ) + "></span>" );
    sb.append ( "<span class=\"node-icon-selected icon\"></span>" );
    sb.append ( "<span class=\"node-icon-type\"></span>" );
    sb.append ( "<span class=\"node-name\">" + getName() + "</span>" );
    sb.append ( "</span>" );


    return sb.toString();
    }

    /**
     *
     */

    public Tree getChild ( int cc )

    { return tree_children != null ? ( Tree ) tree_children.get ( cc ) : null; }

    /**
     *
     */

    public int getChildCount() { return tree_children != null ? tree_children.size() : 0; }

    /**
     *
     */

    public int getCount()
    {
    int count = 1;

    for ( int cc = 0; cc < getChildCount(); cc ++ ) count += getChild ( cc ).getCount();

    return count;
    }

    /**
     *
     */

    public int getCount ( int depth )
    {
    if ( getDepth() > depth ) return 0;

    int count = 1;

    for ( int cc = 0; cc < getChildCount(); cc ++ ) count += getChild ( cc ).getCount ( depth );

    return count;
    }

    /**
     *
     */

    public String getLineage() { return ""; }

    /**
     *
     */

    public String getName() { return new Integer ( getId() ).toString(); }

    /**
     *
     */

    abstract public int getOrdinal();

    /**
     *
     */

    abstract public int getParentId();

    /**
     *
     */

    abstract public String getParentUuid();

    /**
     *
     */

    abstract public String getText ( HttpServletRequest request );

    /**
     *
     */

    public String getTreeName()
    {
    StringBuilder sb = new StringBuilder();

    for ( int cc = 0; cc < getDepth(); cc ++ ) sb.append ( "... " );

    sb.append ( DockYard.clipAt ( getName(), 75 ) );

    return sb.toString();
    }

    /**
     *
     */

    public int getType() { return 0; }

    /**
     *
     */

    abstract public String getUuid();

    /**
     *
     */

    public boolean hasChild ( Tree tree )

    { return tree_children != null ? tree_children.contains ( tree ) : false; }

    /**
     *
     */

    public boolean isLeaf() { return tree_children == null || tree_children.size() == 0; }

    /**
     *
     */

    public boolean isRoot() { return getParentId() == 0; }

    /**
     *
     */

    public int leafCount()
    {
    int count = 0;

    if ( getChildCount() == 0 ) return 1;

    for ( int cc = 0; cc < getChildCount(); cc ++ )

        count += ( ( Tree ) getChild ( cc ) ).leafCount();

    return count;
    }

    /**
     *
     */

    abstract public void paste ( Tree tree );

    /**
     *
     */

    public void pasteBelow ( Tree parent )

    { setParentId ( parent.getId() ); setParentUuid ( parent.getUuid() ); setOrdinal ( parent.getChildCount() ); setDepth ( parent.getDepth() + 1 ); setLineage ( parent.getLineage() + parent.getId() + "/" );  }

    /**
     *
     */

    public void removeChild ( Tree child ) { if ( tree_children != null ) tree_children.remove ( child ); }

    /**
     *
     */

    public void reorder( JDBC jdbc ) throws Exception
    {
    for (int cc = 0; cc < tree_children.size(); cc ++ )
    {
    Tree child = ( Tree ) tree_children.get ( cc );

    child.setOrdinal ( cc );

    child.save ( jdbc );
    }

    }

    /**
     *
     */

    public void reorder ( JDBC jdbc, Tree drag, Tree drop ) throws Exception
    {
    if ( ! tree_children.contains ( drag ) ) return;

    if ( ! tree_children.contains ( drop ) ) return;

    Collections.sort(tree_children);

    tree_children.remove ( drag ); tree_children.add ( tree_children.indexOf ( drop ), drag );

    reorder ( jdbc );
    }

    /**
     *
     */

    public void reorder ( JDBC jdbc, Tree drag, Tree drop, String point ) throws Exception
    {
    if ( ! tree_children.contains ( drag ) ) return;

    if ( ! tree_children.contains ( drop ) ) return;

    Collections.sort (tree_children);

    tree_children.remove ( drag );

    int cc = tree_children.indexOf ( drop );

    boolean below = DockYard.compareTo ( point, "below" ) == 0;

    if ( below ) cc += 1;

    if ( cc > tree_children.size() ) tree_children.add ( drag ); else tree_children.add ( cc, drag );

    reorder ( jdbc );
    }

    /**
     *
     */

    public void setLineage ( String lineage ) {}

    /**
     *
     */

    abstract public void setOrdinal ( int ordinal );

    /**
     *
     */

    abstract public void setParentId ( int parent_id );

    /**
     *
     */

    abstract public void setParentUuid ( String parent_uuid );

    /**
     *
     */

    public int size()
    {
    int size = 1;

    for ( int cc = 0; cc < getChildCount(); cc ++ )

        size += getChild ( cc ).size();

    return size;
    }

    /**
     *
     */

    abstract public Tree twin();

    /**
     *
     */

    public List    getChildren   () { return tree_children  ; }
    public int     getDepth      () { return tree_depth     ; }

    /**
     *
     */

    public void setDepth     ( int     tree_depth     ) { this.tree_depth     = tree_depth     ; }

}