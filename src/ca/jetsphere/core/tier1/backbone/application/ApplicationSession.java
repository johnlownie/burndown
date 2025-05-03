package ca.jetsphere.core.tier1.backbone.application;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.application.ApplicationMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class ApplicationSession extends ApplicationMap implements ISessionObject
{
    /**
     *
     */

    public ApplicationSession () { super(); }

    /**
     *
     */

    public ApplicationSession ( JDBC jdbc, int company_id ) { super ( jdbc, company_id ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Application ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return Application.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Application() ); }

    /**
     *
     */

    public String [] fields() { return Application.fields(); }

    /**
     *
     */

    static public ApplicationSession getInstance ( HttpServletRequest request )

    { return ( ApplicationSession ) SessionCache.getSessionObject ( request, Application.key(), ApplicationSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public Application getSelected ( HttpServletRequest request ) { return ( Application ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public ApplicationSession query ( JDBC jdbc, HttpServletRequest request, int company_id, boolean blank )

    { ApplicationSession session = getInstance ( request ); session.find ( jdbc, company_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, Application selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}
