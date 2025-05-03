package ca.jetsphere.core.security.login;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier2.common.AbstractAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import org.apache.struts.action.ActionForward;

/**
 *
 */

public class LogoutAction extends AbstractAction
{
    /**
     *
     */

    public ActionForward execute ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    logout ( jdbc, store, errors );

    store.getRequest().getSession().invalidate();

    return store.getForward ( "success" );
    }

    /**
     *
     */

    public void logout ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

    } catch ( Exception e ) { Common.trace ( e ); }

    }

}