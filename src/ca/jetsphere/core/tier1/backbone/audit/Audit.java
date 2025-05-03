package ca.jetsphere.core.tier1.backbone.audit;

import ca.jetsphere.core.common.*;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;
import ca.jetsphere.core.tier1.system.knock.Knock;
import ca.jetsphere.core.tier2.common.Errors;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Iterator;

/**
 * 
 */

public class Audit extends ca.jetsphere.core.tier0.backbone.audit.Audit
{
    StopWatch stopWatch = new StopWatch(); String audit_username;

    /**
     *
     */

    public Audit() { super(); }

    /**
     *
     */

    public Audit ( JDBC jdbc, int id ) { super ( jdbc, id ); }

    /**
     *
     */

    public Audit ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    public Audit ( HttpServletRequest request )
    {
    User user = UserYard.whoAmI ( request );

    start (); setIp ( DockYard.getIp ( request ) ); setSessionId ( request.getRequestedSessionId() ); setPath ( request.getServletPath() ); setQueryString ( request.getQueryString() ); if ( getQueryString() == null ) setQueryString ( "" ); setMethod(request.getMethod());

    if ( user != null ) setMemberId ( user.getId() );
    }

    /**
     *
     */

    static public String [] captions() { return new String [] { "name", "audit.ip", "audit.session.id", "audit.path", "audit.query.string", "audit.method", "audit.errors", "last.update" }; }

    /**
     *
     */

    static public String [] fields()

    { return new String [] { "audit_member_id", "audit_ip", "audit_session_id", "audit_path", "audit_query_string", "audit_method", "audit_errors", "audit_last_update" }; }

    /**
     *
     */

    public void foreign ( JDBC jdbc )
    {
    if ( getMemberId() > 0 ) setUsername ( new User ( jdbc, getMemberId () ).getUsername() );
    }

    /**
     *
     */

    public Object get ( String s )
    {
    if ( "audit_member_id" .equals ( s ) ) return getMemberId() > 0 ? getUsername() : "";

    return super.get ( s );
    }

    /**
     *
     */

    public String getKey     () { return key()          ; }
    public String getUsername() { return audit_username ; }

    /**
     *
     */

    static public Timestamp now() { return new Timestamp ( System.currentTimeMillis() ); }

    /**
     *
     */

    public void start() { setStart ( now() ); stopWatch.reset(); }

    /**
     *
     */

    public void stop() { setStop ( now() ); setElapsed ( stopWatch.getElapsed() ); }

    /**
     *
     */

    public void save ( JDBC jdbc, HttpServletRequest request ) throws Exception

    { if ( shouldAudit ( request ) ) { stop(); super.save ( jdbc, true ); trace ( request ); } }

    /*
     *
     */

    public void setErrors ( HttpServletRequest request, Errors errors )
    {
    if ( errors.isEmpty() ) return;

    StringBuilder sb = new StringBuilder();

    Iterator it =  errors.iterator();

    while ( it.hasNext() )
    {
    ActionMessage message = ( ActionMessage ) it.next();

    if ( sb.length() > 0 ) sb.append ( " - " ); String s = "";

    try {

        s = message.getValues() == null || message.getValues().length == 0 ? Caption.get ( request, message.getKey() ) : Caption.get ( request, message.getKey(), ( String[] ) message.getValues()[ 0 ] );

    } catch ( Exception e ) { s = message.getKey(); }

    sb.append ( s );
    }

    setErrors ( sb.toString() );
    }

    /**
     *
     */

    public void setUsername ( String audit_username ) { this.audit_username = audit_username; }

    /**
     *
     */

    static public boolean shouldAudit ( HttpServletRequest request )
    {
    if ( DockYard.compareTo ( request.getServletPath(), "/ajax_lockout.do" ) == 0 ) return false;

    return "true".equalsIgnoreCase ( Knock.get ( "AUDIT-TRAIL" ) );
    }

    /**
     *
     */

    public void trace ( HttpServletRequest request )
    {
    String qs = ! DockYard.isWhiteSpace ( getQueryString() ) ? " [ " + getQueryString() + " ]" : "";

    Common.info ( "[ AUDIT ] " + getPath() + "[ SESSIONID ] " + request.getRequestedSessionId() + qs + " " + stopWatch.trace() );
    }

}
