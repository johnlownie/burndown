package ca.jetsphere.burndown.tier2.report.monthly;

import ca.jetsphere.burndown.tier1.report.monthly.Monthly;
import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.bolt.rs.ResultSetBolt;
import ca.jetsphere.core.bolt.rs.ResultSetBoltMap;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.period.PeriodSession;
import ca.jetsphere.core.tier2.common.AbstractAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import org.apache.struts.action.ActionForward;

/**
 *
 */
public class MonthlyReportAction extends AbstractAction
{
    /**
     *
     */
    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm();

    int period_id = qaf.getPeriodId () <= 0 ? PeriodSession.getSelected ( store.getRequest() ).getId() : qaf.getPeriodId();

    qaf.setPeriodId ( period_id );

    return getForward ( store );
    }

    /**
     *
     */
    public ActionForward report ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm();

    ResultSetBoltMap rsbm = new Monthly().getReport ( jdbc, qaf );

    DockYard.setAttribute ( store.getRequest(), ResultSetBolt.key(), rsbm, false );

    return getForward ( store );
    }
}
