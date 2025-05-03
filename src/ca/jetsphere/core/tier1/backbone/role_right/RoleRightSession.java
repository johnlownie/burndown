package ca.jetsphere.core.tier1.backbone.role_right;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.role_right.RoleRightMap;
import ca.jetsphere.core.tier1.tree.Tree;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class RoleRightSession extends RoleRightMap implements ISessionObject
{
    /**
     *
     */

    public RoleRightSession() { super(); }

    /**
     *
     */

    public RoleRightSession ( JDBC jdbc, int role_id ) { this(); find ( jdbc, role_id ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception  { add ( new RoleRight ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return RoleRight.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new RoleRight() ); }

    /**
     *
     */

    public String [] fields() { return RoleRight.fields(); }

    /**
     *
     */

    public String getKey() { return RoleRight.key(); }

    /**
     *
     */

    public void paste ( Tree parent, Tree child )
    {
    RoleRight p = ( RoleRight ) parent; RoleRight c = ( RoleRight ) child;

    c.setRoleId ( p.getRoleId() ); c.setParentId ( p.getId() ); c.setParentUuid ( p.getUuid() ); c.setOrdinal ( p.getChildCount() );

    p.addChild ( c );
    }

    /**
     *
     */

    static public RoleRightSession getInstance ( HttpServletRequest request )

    { return ( RoleRightSession ) SessionCache.getSessionObject ( request, RoleRight.key(), RoleRightSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public RoleRight getSelected ( HttpServletRequest request ) { return ( RoleRight ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public RoleRightSession query ( JDBC jdbc, HttpServletRequest request, int role_id, boolean blank )

    { RoleRightSession session = getInstance ( request ); session.find ( jdbc, role_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, RoleRight selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}
