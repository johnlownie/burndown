package ca.jetsphere.core.tier2.common;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.Common;
import net.sf.json.JSONObject;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Iterator;

/**
 *
 */

public class Errors extends Action
{
    ActionMapping mapping; HttpServletRequest request; ActionErrors errors;

    /**
     *
     */

    public Errors() { this ( ( HttpServletRequest ) null ); }

    /**
     *
     */

    public Errors ( HttpServletRequest request )

    { super(); this.request = request; errors = new ActionErrors(); }

    /**
     *
     */

    public Errors ( ActionMapping mapping, HttpServletRequest request )

    { this ( request ); this.mapping = mapping; }

    /**
     *
     */

    public Errors ( ActionErrors errors, HttpServletRequest request )

    { this ( request ); if ( errors != null ) this.errors = errors; }

    /**
     *
     */

    public Errors ( ActionStore store ) { this ( store.getMapping(), store.getRequest() ); }

    /**
     *
     */

    public Errors add ( ActionMessage error )

    { errors.add ( ActionErrors.GLOBAL_MESSAGE, error ); saveErrors(); return this; }

    /**
     *
     */

    public Errors add ( String error ) { add ( new ActionMessage ( error ) ); return this; }

    /**
     *
     */

    public Errors add ( String key, String value )

    { errors.add ( ActionErrors.GLOBAL_MESSAGE, new ActionMessage ( key, value ) ); saveErrors(); return this; }

    /**
     *
     */

    public Errors add ( String key, String value, Object o )

    { errors.add ( key, new ActionMessage ( value, o ) ); saveErrors(); return this; }

    /**
     *
     */

    public Errors add ( String key, String value, Object o, Object p )

    { errors.add ( key, new ActionMessage ( value, o, p ) ); saveErrors(); return this; }

    /**
     *
     */

    public Errors add ( String error, Object o )

    { add ( new ActionMessage ( error, o ) ); return this; }

    /**
     *
     */

    public void add ( ActionErrors errors )

    { Iterator it = errors.get(); while ( it.hasNext() ) this.errors.add ( ActionErrors.GLOBAL_MESSAGE, ( ActionMessage ) it.next() ); }

    /**
     *
     */

    public void add ( Object o, Exception e )
    {
    Common.trace ( o.getClass().getName(), e );

    if ( e.getClass() == SQLException.class )
    {
    SQLException ex = ( SQLException ) e;

    String errorKey = "error.sql.mysql." + ex.getErrorCode();

    add ( errorKey );
    }

    }

    /**
     *
     */

    public void clearErrors()

    { if ( request != null ) saveMessages ( request, errors = new ActionErrors() ); }

    /**
     *
     */

    public ActionForward forward()

    { saveErrors(); return mapping != null ? mapping.findForward ( "failure" ) : null; }

    /**
     *
     */

    public ActionForward forward ( ActionMessage error ) { add ( error ); return forward(); }

    /**
     *
     */

    public ActionForward forward ( ActionMapping mapping ) { return mapping.findForward ( isEmpty() ? "success" : "failure" ); }

    /**
     *
     */

    public ActionForward forward ( String error ) { add ( error ); return forward(); }

    /**
     *
     */

    public ActionForward forward ( String error, Object o ) { add ( error, o ); return forward(); }

    /**
     *
     */

    public ActionForward forward ( ActionStore store ) { return forward ( store.getMapping() ); }

    /**
     *
     */

    public ActionMessage get1st()
    {
    Iterator it = iterator();

    if ( it.hasNext() )
    {
    ActionMessage message = ( ActionMessage ) it.next();

    return message;
    }

    return null;
    }

    /**
     *
     */

    public JSONObject getFields ( HttpServletRequest request )
    {
    JSONObject fields = new JSONObject();

    Iterator it = iterator();

    while ( it.hasNext() )

    { ActionMessage message = ( ActionMessage ) it.next(); fields.put ( message.getKey(), Caption.get ( request, ( String ) message.getValues() [0] ) ); }

    return fields;
    }

    /**
     *
     */

    public boolean isEmpty() { return errors.isEmpty(); }

    /**
     *
     */

    public Iterator iterator() { return errors.get(); }

    /**
     *
     */

    public void saveErrors()

    { if ( request != null && ! errors.isEmpty() ) try { saveMessages ( request, errors ); } catch ( Exception e ) { Common.trace ( e ); } }

    /**
     *
     */

    public void saveErrors ( HttpServletRequest request ) { this.request = request; saveErrors(); }

    /**
     *
     */

    public int size() { return errors.size(); }

}
