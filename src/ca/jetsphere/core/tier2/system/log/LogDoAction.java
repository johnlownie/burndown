package ca.jetsphere.core.tier2.system.log;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.system.log.Log;
import ca.jetsphere.core.tier1.system.log.LogSession;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;

import org.apache.struts.action.ActionForward;

/**
 *
 */

public class LogDoAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return Log.key (); }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    LogSession.query ( store.getRequest (), false );

    return getForward ( store );
    }

}
