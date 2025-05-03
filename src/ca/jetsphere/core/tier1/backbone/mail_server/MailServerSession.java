package ca.jetsphere.core.tier1.backbone.mail_server;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.mail_server.MailServerMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class MailServerSession extends MailServerMap implements ISessionObject
{
    /**
     *
     */

    public MailServerSession() { super(); }

    /**
     *
     */

    public MailServerSession ( JDBC jdbc, int company_id ) { super ( jdbc, company_id ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new MailServer ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return MailServer.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new MailServer() ); }

    /**
     *
     */

    public String [] fields() { return MailServer.fields(); }

    /**
     *
     */

    static public MailServerSession getInstance ( HttpServletRequest request )

    { return ( MailServerSession ) SessionCache.getSessionObject (request, MailServer.key(), MailServerSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public MailServer getSelected ( HttpServletRequest request ) { return ( MailServer ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public MailServerSession query ( JDBC jdbc, HttpServletRequest request, int company_id, boolean blank )

    { MailServerSession session = getInstance ( request ); session.find ( jdbc, company_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, MailServer selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}
