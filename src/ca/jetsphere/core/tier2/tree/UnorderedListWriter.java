package ca.jetsphere.core.tier2.tree;

import ca.jetsphere.core.tier1.tree.Tree;
import ca.jetsphere.core.tier1.tree.TreeSession;

import javax.servlet.http.HttpServletRequest;
import java.io.Writer;

/**
 *
 */

public class UnorderedListWriter
{
    protected TreeSession trees; protected String name;

    /**
     *
     */

    public UnorderedListWriter ( TreeSession trees, String name ) { super(); this.trees = trees; this.name = name; }

    /**
     *
     */

    public String getName() { return name; }

    /**
     *
     */

    public void print ( Writer writer, HttpServletRequest request ) throws Exception

    { if ( this.trees.getRoot() == null ) return; writer.write ( "<ul id=\"tree1\">" ); tree ( writer, request, this.trees.getRoot() ); writer.write ( "</ul>" ); }

    /**
     *
     */

    protected void tree ( Writer writer, HttpServletRequest request, Tree tree ) throws Exception
    {
    writer.write ( "<li>" ); writer.write ( tree.getName() );

    if ( ! tree.isLeaf() ) { writer.write ( "<ul>" ); for ( int i = 0; i < tree.getChildCount(); i++ ) tree ( writer, request, tree.getChild ( i ) ); writer.write ( "</ul>" ); }

    writer.write ( "</li>" );
    }

}
