package ca.jetsphere.core.tier1.backbone.email;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.email.EmailMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class EmailSession extends EmailMap implements ISessionObject
{
    /**
     *
     */

    public EmailSession() { super(); }

    /**
     *
     */

    public EmailSession ( JDBC jdbc, int id ) { super ( jdbc, id ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Email ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return Email.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Email() ); }

    /**
     *
     */

    public String [] fields() { return Email.fields(); }

    /**
     *
     */

    static public EmailSession getInstance ( HttpServletRequest request )

    { return ( EmailSession ) SessionCache.getSessionObject ( request, Email.key(), EmailSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public Email getSelected ( HttpServletRequest request ) { return ( Email ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public EmailSession query ( JDBC jdbc, HttpServletRequest request, int email_type_id, boolean blank )

    { EmailSession session = getInstance ( request ); session.find ( jdbc, email_type_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, Email selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}
