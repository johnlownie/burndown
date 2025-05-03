package ca.jetsphere.core.tier2.common;

import ca.jetsphere.core.bolt.rs.ResultSetBolt;
import ca.jetsphere.core.bolt.rs.ResultSetBoltMap;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 */

public class ReadOnlyQueryAction extends AbstractAction
{
    /**
     *
     */

    public ActionForward execute ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    HttpServletRequest request = store.getRequest();

    if ( ! DockYard.isWhiteSpace ( request.getParameter ( "query" ) ) ) return query ( jdbc, store, errors );

    return store.getForward ( "failure" );
    }

    /**
     *
     */

    protected String getQuery ( HttpServletRequest request )
    {
    String query = request.getParameter ( "query" );

    if ( ! DockYard.isWhiteSpace ( query ) && ! query.startsWith ( "select" ) ) return "";

    return query;
    }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    HttpServletRequest request = store.getRequest(); HttpServletResponse response = store.getResponse();

    DockYard.setAttribute ( request, ResultSetBolt.key(), null, false );

    DockYard.setAttribute ( request, "query", "", false );

    String query = getQuery ( request );

    if ( DockYard.isWhiteSpace ( query ) ) return errors.forward();

    ResultSetBoltMap rsbl = new ResultSetBoltMap ( jdbc, query );

    DockYard.setAttribute ( request, ResultSetBolt.key(), rsbl, false );

    DockYard.setAttribute ( request, "query", request.getParameter ( "query" ), false );

    return errors.forward();
    }

}
