package ca.jetsphere.core.tier2.system.log;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.system.log.Log;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class LogEditAction extends AbstractEditAction
{
    /**
     *
     */

    public ActionForward edit ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    HttpServletRequest request = store.getRequest();

    ISessionObject iso = SessionCache.getSessionObject ( request, getKey () );

    int id = DockYard.toInteger ( request, "id" );

    iso.setQualifiedSelected ( request, id );

    Log log = ( Log ) iso.getSelected();

    log.setText();

    return getForward ( store );
    }

    /**
     *
     */

    public String getKey() { return Log.key(); }

}
