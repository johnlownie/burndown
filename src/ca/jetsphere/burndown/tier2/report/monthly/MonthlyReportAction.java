package ca.jetsphere.burndown.tier2.report.monthly;

import ca.jetsphere.burndown.tier1.report.monthly.Monthly;
import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.bolt.rs.ResultSetBolt;
import ca.jetsphere.core.bolt.rs.ResultSetBoltMap;
import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
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
    
    String today = CalendarYard.now ( "yyyy-MM-dd" );

    qaf.setPeriodId ( period_id ); 
    
    qaf.setStartDateAsString ( CalendarYard.getFirstDayOfYear ( today ) ); qaf.setEndDateAsString ( CalendarYard.getLastDayOfYear ( today ) );

    return getForward ( store );
    }

    /**
     *
     */
    public ActionForward report ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Company company = CompanySession.getSelected ( store.getRequest () );
    
    qaf.setCompanyId ( company.getId() );

    ResultSetBoltMap rsbm = new Monthly().getReport ( jdbc, qaf );

    DockYard.setAttribute ( store.getRequest(), ResultSetBolt.key(), rsbm, false );

    return getForward ( store );
    }
}
