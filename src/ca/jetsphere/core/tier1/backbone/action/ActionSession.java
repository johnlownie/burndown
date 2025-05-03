package ca.jetsphere.core.tier1.backbone.action;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.action.ActionMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class ActionSession extends ActionMap implements ISessionObject
{
    /**
     *
     */

    public ActionSession() { super(); }

    /**
     *
     */

    public ActionSession ( JDBC jdbc, int company_id ) { super ( jdbc, company_id ); }

    /**
     *
     */

    public ActionSession ( JDBC jdbc, String query ) { super(); query ( jdbc, query ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Action ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return Action.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Action() ); }

    /**
     *
     */

    public String [] fields() { return Action.fields(); }

    /**
     *
     */

    static public ActionSession getInstance ( HttpServletRequest request )

    { return ( ActionSession ) SessionCache.getSessionObject ( request, Action.key(), ActionSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public Action getSelected ( HttpServletRequest request ) { return ( Action ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public ActionSession query ( JDBC jdbc, HttpServletRequest request, int company_id, boolean blank )

    { ActionSession session = getInstance ( request ); session.find ( jdbc, company_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, Action selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}

