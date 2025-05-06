package ca.jetsphere.burndown.tier0.backbone.category;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.burndown.tier0.Registry;
import ca.jetsphere.core.tier1.tree.Tree;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class Category extends ca.jetsphere.burndown.tier0.backbone.category.foreign.Category
{
    /**
     *
     */
    public Category() { clear(); }

    /**
     *
     */
    public Category ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */
    public void delete ( JDBC jdbc ) throws Exception { Registry.getCategoryDao().delete ( jdbc, this ); }

    /**
     *
     */
    public String getKey() { return key(); }

    /**
     *
     */
    public String getText ( HttpServletRequest request ) { return null; }

    /**
     *
     */
    public void insert ( JDBC jdbc ) throws Exception { Registry.getCategoryDao().insert ( jdbc, this ); }

    /**
     *
     */
    public void paste ( Tree tree )

    { Category parent = ( Category ) tree; setCompanyId ( parent.getCompanyId() ); pasteBelow ( parent ); }

    /**
     *
     */
    public Tree twin() { return null; }

    /**
     *
     */
    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getCategoryDao().update ( jdbc, this ); }
}
