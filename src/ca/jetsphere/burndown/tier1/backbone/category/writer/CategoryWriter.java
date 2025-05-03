package ca.jetsphere.burndown.tier1.backbone.category.writer;

import ca.jetsphere.burndown.tier1.backbone.category.CategorySession;
import ca.jetsphere.core.tier1.tree.Tree;
import ca.jetsphere.core.tier2.tree.TreeTableWriter;

/**
 *
 */
public class CategoryWriter extends TreeTableWriter
{
    /**
     *
     */
    public CategoryWriter ( CategorySession categories, String[] headers, String[] fields )

    { super ( categories, headers, fields ); }

    /**
     *
     */
    protected void columns ( Tree tree )

    { for ( int column = 2; column < getColumns(); column ++ ) column ( tree, column ); }

    /**
     *
     */
    protected void headers()

    { write ( "<thead><tr>" ); for ( int column = 2; column < getColumns(); column ++ ) header ( column ); write ( "</tr></thead>" ); }
}