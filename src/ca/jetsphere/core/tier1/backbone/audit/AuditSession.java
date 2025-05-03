package ca.jetsphere.core.tier1.backbone.audit;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.audit.AuditMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 * 
 */

public class AuditSession extends AuditMap implements ISessionObject
{
    /**
     *
     */

    public AuditSession() { super(); }

    /**
     *
     */

    public AuditSession ( JDBC jdbc, int user_id ) { super ( jdbc, user_id ); }

    /**
     *
     */

    public AuditSession ( JDBC jdbc, String query ) { super(); query ( jdbc, query ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Audit ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return Audit.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Audit() ); }

    /**
     *
     */

    public String [] fields() { return Audit.fields(); }

    /**
     *
     */

    static public AuditSession getInstance ( HttpServletRequest request )

    { return ( AuditSession ) SessionCache.getSessionObject ( request, Audit.key (), AuditSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public Audit getSelected ( HttpServletRequest request ) { return ( Audit ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public AuditSession query ( JDBC jdbc, HttpServletRequest request, int user_id, boolean blank )

    { AuditSession session = getInstance ( request ); session.find ( jdbc, user_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, Audit selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}

