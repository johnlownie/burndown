package ca.jetsphere.core.tier1.backbone.role_right.writer;

import ca.jetsphere.core.tier1.backbone.role_right.RoleRightSession;
import ca.jetsphere.core.tier1.tree.Tree;
import ca.jetsphere.core.tier2.tree.TreeTableWriter;

/**
 *
 */

public class RoleRightWriter extends TreeTableWriter
{
    /**
     *
     */

    public RoleRightWriter ( RoleRightSession roleRights, String[] headers, String[] fields )

    { super ( roleRights, headers, fields ); }

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