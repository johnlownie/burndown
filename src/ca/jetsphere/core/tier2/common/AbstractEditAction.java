package ca.jetsphere.core.tier2.common;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 *
 */

abstract public class AbstractEditAction extends AbstractAction
{
    /**
     *
     */

    public ActionForward execute ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    if ( attach  ( store.getRequest() ) ) return attach  ( jdbc, store, errors );
    if ( back    ( store.getRequest() ) ) return back    ( jdbc, store, errors );
    if ( batch   ( store.getRequest() ) ) return batch   ( jdbc, store, errors );
    if ( delete  ( store.getRequest() ) ) return delete  ( jdbc, store, errors );
    if ( edit    ( store.getRequest() ) ) return edit    ( jdbc, store, errors );
    if ( error   ( store.getRequest() ) ) return error   ( jdbc, store, errors );
    if ( fetch   ( store.getRequest() ) ) return fetch   ( jdbc, store, errors );
    if ( insert  ( store.getRequest() ) ) return insert  ( jdbc, store, errors );
    if ( json    ( store.getRequest() ) ) return json    ( jdbc, store, errors );
    if ( jupdate ( store.getRequest() ) ) return jupdate ( jdbc, store, errors );
    if ( next    ( store.getRequest() ) ) return next    ( jdbc, store, errors );
    if ( print   ( store.getRequest() ) ) return print   ( jdbc, store, errors );
    if ( save    ( store.getRequest() ) ) return save    ( jdbc, store, errors );
    if ( update  ( store.getRequest() ) ) return update  ( jdbc, store, errors );

    return super.execute ( jdbc, store, errors );
    }

    /**
     *
     */

    public ActionForward batch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    Bolt form = ( Bolt ) store.getForm(); form.clear();

    return store.getForward ( "batch" );
    }

    /**
     *
     */

    public ActionForward delete ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    delete ( jdbc, ( Bolt ) store.getForm(), store.getRequest(), errors );

    return errors.forward ( store );
    }

    /**
     *
     */

    public ActionForward edit ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    HttpServletRequest request = store.getRequest();

    ISessionObject iso = SessionCache.getSessionObject ( request, getKey() ); iso.clearSelected ( request );

    String uuid = DockYard.getParameter ( request, "csrf" );

    if ( !DockYard.isWhiteSpace ( uuid ) ) iso.setQualifiedSelected ( request, uuid );

    Bolt bolt = iso.getSelected(); Bolt form = ( Bolt ) store.getForm();

    if ( bolt != null && bolt.isValid() ) { bolt.getPayload ( jdbc ); } // form.copy ( bolt ); }

    else { form.clear(); }

    return query ( jdbc, store, errors );
    }

    /**
     *
     */

    abstract public String getKey();

    /**
     *
     */

    public ActionForward insert ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    update ( jdbc, store.getRequest(), ( Bolt ) store.getForm(), errors );

    return errors.forward ( store );
    }

    /**
     *
     */

    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        ISessionObject iso = SessionCache.getSessionObject ( store.getRequest(), getKey () );

        String uuid = DockYard.getParameter ( store.getRequest(), "id" );

        Bolt bolt = iso.getBoltByUuid ( uuid );

        iso.setQualifiedSelected ( store.getRequest(), bolt );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        String json = new Gson().toJson ( bolt );

        out.write ( json );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward jupdate ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        update ( jdbc, store.getRequest(), ( Bolt ) store.getForm(), errors );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        errors.forward ( store );

        jsonObject.put ( "success", errors.isEmpty() );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward move ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    Bolt form = ( Bolt ) store.getForm(); form.clear();

    return store.getForward ( "move" );
    }

    /**
     *
     */

    public ActionForward test ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    HttpServletRequest request = store.getRequest();

    ISessionObject iso = SessionCache.getSessionObject ( request, getKey() ); iso.clearSelected ( request );

    String uuid = DockYard.getParameter ( request, "csrf" );

    if ( !DockYard.isWhiteSpace ( uuid ) ) iso.setQualifiedSelected ( request, uuid );

    Bolt bolt = iso.getSelected(); Bolt form = ( Bolt ) store.getForm();

    if ( bolt != null && bolt.isValid() ) { bolt.getPayload ( jdbc ); } // form.copy ( bolt ); }

    else { form.clear(); }

    return store.getForward ( "test" );
    }

    /**
     *
     */

    public ActionForward update ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    update ( jdbc, store.getRequest(), ( Bolt ) store.getForm(), errors );

    return errors.forward ( store );
    }

}
