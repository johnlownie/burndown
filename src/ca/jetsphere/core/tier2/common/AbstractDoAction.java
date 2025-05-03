package ca.jetsphere.core.tier2.common;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;

import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

abstract public class AbstractDoAction extends AbstractAction
{
    /**
     *
     */

    public ActionForward add ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    HttpServletRequest request = store.getRequest();

    ISessionObject iso = SessionCache.getSessionObject ( request, getKey() );

    iso.clearSelected ( request );

    return store.getForward ( "insert" );
    }

    /**
     *
     */

    public ActionForward back ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception

    { return DockYard.contains ( store.getMapping().findForwards(), "back" ) ? store.getForward ( "back" ) : store.getForward ( "failure" ); }

    /**
     *
     */

    public ActionForward clear ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception

    { return query ( jdbc, store, errors ); }

    /**
     *
     */

    public ActionForward delete ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm();

    deletes ( jdbc, store.getRequest(), getKey(), new String[] { qaf.getDelete() }, errors );

    return errors.forward ( store );
    }

    /**
     *
     */

    public Errors deletes ( JDBC jdbc, HttpServletRequest request, String key, String[] deletes, Errors errors ) throws Exception
    {
    ISessionObject iso = SessionCache.getSessionObject ( request, key );

    if ( iso == null || deletes == null ) return errors;

    for ( int cc = 0; cc < deletes.length; cc ++ )
    {
    Bolt bolt = iso.getBoltByUuid ( deletes [ cc ] );

    if ( bolt == null ) continue; else errors = delete ( jdbc, bolt, request, errors );

    if ( ! errors.isEmpty() ) break;
    }

    return errors;
    }

    /**
     *
     */

    public ActionForward edit ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm();

    if ( !DockYard.isWhiteSpace ( qaf.getEdit() ) ) return store.getForward ( "edit", "?edit=true&id=" + qaf.getEdit() + "&_=" + System.currentTimeMillis() );

    return  getForward ( store );
    }

    /**
     *
     */

    abstract public String getKey();

    /**
     *
     */

    public ActionForward role ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm();

    if ( !DockYard.isWhiteSpace ( qaf.getRole() ) ) return store.getForward ( "role", "?role=true&id=" + qaf.getRole() );

    return getForward ( store );
    }

}
