package ca.jetsphere.core.tier0.backbone.role_right;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;
import ca.jetsphere.core.tier1.tree.Tree;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class RoleRight extends ca.jetsphere.core.tier0.backbone.role_right.foreign.RoleRight
{
    /**
     *
     */

    public RoleRight() { clear(); }

    /**
     *
     */

    public RoleRight ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getRoleRightDao().delete ( jdbc, this ); }

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

    public void insert ( JDBC jdbc ) throws Exception { Registry.getRoleRightDao().insert ( jdbc, this ); }

    /**
     *
     */

    public void paste ( Tree tree )

    { RoleRight parent = ( RoleRight ) tree; setRoleId ( parent.getRoleId() ); pasteBelow ( parent ); }

    /**
     *
     */

    public Tree twin() { return null; }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getRoleRightDao().update ( jdbc, this ); }

}
