package ca.jetsphere.core.tier1.backbone.notification;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.notification.NotificationMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */
public class NotificationSession extends NotificationMap implements ISessionObject
{
    /**
     *
     */

    public NotificationSession() { super (); }

    /**
     *
     */

    public NotificationSession ( JDBC jdbc, int company_id ) { super ( jdbc, company_id ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Notification ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return null; }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Notification () ); }

    /**
     *
     */

    static public NotificationSession getInstance ( HttpServletRequest request )

    { return ( NotificationSession ) SessionCache.getSessionObject ( request, Notification.key (), NotificationSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public Notification getSelected ( HttpServletRequest request ) { return ( Notification ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    public String [] fields() { return null; }

    /**
     *
     */

    static public NotificationSession query ( JDBC jdbc, HttpServletRequest request, int user_id, boolean blank )

    { NotificationSession session = getInstance ( request ); session.find ( jdbc, user_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, Notification selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}