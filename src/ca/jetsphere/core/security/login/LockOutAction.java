package ca.jetsphere.core.security.login;

import ca.jetsphere.core.common.Clock;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.security.RequestProcessor;
import ca.jetsphere.core.tier2.common.AbstractAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;

import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class LockOutAction extends AbstractAction
{
    /**
     *
     */

    public ActionForward execute ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    HttpServletRequest request = store.getRequest();

    if ( request.getParameter ( "cancel" ) != null ) return cancel ( store );

    if ( request.getParameter ( "start"  ) != null ) return start  ( store );

    return getForward ( store );
    }

    /**
     *
     */

    public ActionForward cancel ( ActionStore store )
    {
    RequestProcessor.getLockOut ().cancel();

    HttpServletRequest request = store.getRequest();

    Clock clock = ( Clock ) request.getSession().getAttribute ( "[clock]" );

    clock.clear();

    return store.getForward ( "success" );
    }

    /**
     *
     */

    public ActionForward getForward ( ActionStore store )
    {
    HttpServletRequest request = store.getRequest();

    Clock clock = ( Clock ) request.getSession().getAttribute ( "[clock]" );

    if ( clock == null )

        request.getSession().setAttribute ( "[clock]", new Clock ( "lockout", "hhmmss", false ) );

    return store.getForward ( "failure" );
    }

    /**
     *
     */

    public ActionForward start ( ActionStore store )
    {
    HttpServletRequest request = store.getRequest();

    Clock clock = ( Clock ) request.getSession().getAttribute ( "[clock]" );

    clock.options ( request );

    RequestProcessor.getLockOut().start ( clock.getMillis() );

    return store.getForward ( "success" );
    }

}
