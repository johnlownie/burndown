package ca.jetsphere.core.tier1.backbone.policy;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.policy.PolicyMap;

/**
 *
 */

public class PolicySession extends PolicyMap implements ISessionObject
{
    /**
     *
     */

    public PolicySession() { super(); }

    /**
     *
     */

    public PolicySession ( JDBC jdbc, int company_id ) { super ( jdbc, company_id ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception

    { add ( new Policy ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return Policy.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Policy() ); }

    /**
     *
     */

    public String [] fields() { return Policy.fields(); }

    /**
     *
     */

    static public PolicySession getInstance ( HttpServletRequest request )

    { return ( PolicySession ) SessionCache.getSessionObject ( request, Policy.key(), PolicySession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public Policy getSelected ( HttpServletRequest request ) { return ( Policy ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public PolicySession query ( JDBC jdbc, HttpServletRequest request, int company_id, boolean blank )

    { PolicySession session = getInstance ( request ); session.find ( jdbc, company_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, Policy selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }


}

