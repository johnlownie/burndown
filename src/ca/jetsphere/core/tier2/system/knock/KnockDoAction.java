package ca.jetsphere.core.tier2.system.knock;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import org.apache.struts.action.ActionForward;

/**
 *
 */

public class KnockDoAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return "Knock"; }

    /**
     *
     */

    public ActionForward query (JDBC jdbc, ActionStore store, Errors errors ) throws Exception

    { return getForward ( store ); }

}
