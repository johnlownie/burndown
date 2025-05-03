package ca.jetsphere.core.tier2.tree;

import ca.jetsphere.core.tier1.tree.Tree;
import ca.jetsphere.core.tier1.tree.TreeSession;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 *
 */

public class JsonWriter
{
    TreeSession trees;

    /**
     *
     */

    public JsonWriter ( TreeSession trees ) { this.trees = trees; }

    /**
     *
     */

    public String get ( HttpServletRequest request, int parent_id ) throws Exception
    {
    StringBuilder sb = new StringBuilder();

    Iterator it = trees.iterator ( true );

    while ( it.hasNext() )
    {
    Tree tree = ( Tree ) it.next();

    if ( tree.getParentId() != parent_id ) continue;

    if ( sb.length() > 0 ) sb.append ( ", " ); sb.append ( "{\"id\":" + tree.getId() + ",\"name\": \"" + tree.getText ( request ) + "\",\"level\":" + tree.getDepth() + ",\"type\":\"default\"}" );
    }

    return "{\"nodes\":[" + sb.toString() + "]}";
    }

}
