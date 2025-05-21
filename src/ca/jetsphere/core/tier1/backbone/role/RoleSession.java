package ca.jetsphere.core.tier1.backbone.role;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.role.RoleMap;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class RoleSession extends RoleMap implements ISessionObject
{
    /**
     *
     */

    public RoleSession () { super(); }

    /**
     *
     */

    public RoleSession ( JDBC jdbc, int company_id ) throws Exception

    { super ( jdbc, company_id ); }

    /**
     *
     */

    public RoleSession ( JDBC jdbc, String query ) throws Exception

    { super(); query ( jdbc, query ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Role ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return Role.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Role() ); }

    /**
     *
     */

    public String [] fields() { return Role.fields(); }

    /**
     *
     */

    static public RoleSession getInstance ( HttpServletRequest request )

    { return ( RoleSession ) SessionCache.getSessionObject ( request, Role.key(), RoleSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public Role getSelected ( HttpServletRequest request ) { return ( Role ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public RoleSession query ( JDBC jdbc, HttpServletRequest request, boolean blank )

    { RoleSession session = getInstance ( request ); session.find ( jdbc, CompanySession.getSelected ( request ) ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public RoleSession query ( JDBC jdbc, HttpServletRequest request, int company_id, boolean blank )

    { RoleSession session = getInstance ( request ); session.find ( jdbc, company_id ); session.options ( request, blank ); return session; }

//    { RoleSession session = RoleSession.query ( jdbc, request, blank ); RoleYard.inclusiveFilter ( session, type ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public RoleSession query ( JDBC jdbc, HttpServletRequest request, int type, String alias, boolean blank )

    { RoleSession session = RoleSession.query ( jdbc, request, blank ); RoleYard.inclusiveFilter ( session, type ); RoleYard.inclusiveFilter ( session, alias ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, Role selected ) { getInstance ( request ).setNonQualifiedSelected ( request, selected ); }

}
