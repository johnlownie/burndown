package ca.jetsphere.burndown.tier2.roles.adm.dashboard;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import org.apache.struts.action.ActionForward;

/**
 *
 */
public class DashboardDoAction extends AbstractDoAction
{
    /**
     *
     */
    public String getKey() { return "dashboard"; }

    /**
     *
     */
    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    return getForward ( store );
    }
}
