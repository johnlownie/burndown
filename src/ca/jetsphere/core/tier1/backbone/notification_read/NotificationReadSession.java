package ca.jetsphere.core.tier1.backbone.notification_read;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.notification_read.NotificationReadMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */
public class NotificationReadSession extends NotificationReadMap implements ISessionObject
{
    /**
     *
     */

    public NotificationReadSession() { super (); }

    /**
     *
     */

    public NotificationReadSession ( JDBC jdbc, int dummy_id ) { super ( jdbc, dummy_id ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new NotificationRead ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return null; }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new NotificationRead () ); }

    /**
     *
     */

    static public NotificationReadSession getInstance ( HttpServletRequest request )

    { return ( NotificationReadSession ) SessionCache.getSessionObject ( request, NotificationRead.key (), NotificationReadSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public NotificationRead getSelected ( HttpServletRequest request ) { return ( NotificationRead ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    public String [] fields() { return null; }

    /**
     *
     */

    static public NotificationReadSession query ( JDBC jdbc, HttpServletRequest request, int notification_id, boolean blank )

    { NotificationReadSession session = getInstance ( request ); session.find ( jdbc, notification_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, NotificationRead selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}