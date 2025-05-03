package ca.jetsphere.core.tier1.backbone.period;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.period.PeriodMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class PeriodSession extends PeriodMap implements ISessionObject
{
    /**
     *
     */

    public PeriodSession() { super(); }

    /**
     *
     */

    public PeriodSession ( JDBC jdbc, int application_id ) { super ( jdbc, application_id ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Period ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return Period.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Period() ); }

    /**
     *
     */

    public String [] fields() { return Period.fields(); }

    /**
     *
     */

    static public PeriodSession getInstance ( HttpServletRequest request )

    { return ( PeriodSession ) SessionCache.getSessionObject ( request, Period.key(), PeriodSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public Period getSelected ( HttpServletRequest request ) { return ( Period ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public PeriodSession query ( JDBC jdbc, HttpServletRequest request, int company_id, int application_id, boolean blank )

    { PeriodSession session = getInstance ( request ); session.find ( jdbc, company_id, application_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, Period selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}


