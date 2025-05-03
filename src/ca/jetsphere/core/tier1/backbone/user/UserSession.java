package ca.jetsphere.core.tier1.backbone.user;

import ca.jetsphere.core.bolt.BoltComparator;
import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.user.UserMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class UserSession extends UserMap implements ISessionObject
{
    /**
     *
     */

    public UserSession() { super(); }

    /**
     *
     */

    public UserSession ( JDBC jdbc, int company_id )  { this(); query ( jdbc, company_id ); }

    /**
     *
     */

    public UserSession ( JDBC jdbc, String query ) { this(); query ( jdbc, query ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new User ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return User.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new User() ); }

    /**
     *
     */

    public String [] fields() { return User.fields(); }

    /**
     *
     */

    public BoltComparator getComparator() { return new UserComparator(); }

    /**
     *
     */

    static public UserSession getInstance ( HttpServletRequest request )

    { return ( UserSession ) SessionCache.getSessionObject ( request, User.key(), UserSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public User getSelected ( HttpServletRequest request ) { return ( User ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public UserSession query ( JDBC jdbc, HttpServletRequest request, int company_id, boolean blank )

    { UserSession session = getInstance ( request ); session.find ( jdbc, company_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, User selected ) { getInstance ( request ).setNonQualifiedSelected ( request, selected ); }

}
