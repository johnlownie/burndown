package ca.jetsphere.core.tier2.backbone.period;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.period.Period;
import ca.jetsphere.core.tier1.backbone.period.PeriodSession;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.common.QueryActionForm;
import ca.jetsphere.core.tier2.table.DataTableWriter;

import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;

/**
 *
 */

public class PeriodDoAction extends AbstractDoAction
{
    /**
     *
     */

    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        int company_id = DockYard.toInteger ( store.getRequest (), "companyId" );

        ApplicationSession applications = ApplicationSession.query ( jdbc, store.getRequest(), company_id, false );

        store.getResponse().setContentType ( "text/html" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        out.write ( DockYard.getOptions ( store.getRequest(), applications, "all.applications" ) );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public String getKey() { return Period.key(); }

    /**
     *
     */

    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        ApplicationSession applications = ApplicationSession.query ( jdbc, store.getRequest(), qaf.getCompanyId(), false );

        PeriodSession periods = PeriodSession.query ( jdbc, store.getRequest(), qaf.getCompanyId(), qaf.getApplicationId(), false );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableWriter dataTableWriter = new DataTableWriter( periods, Period.captions(), Period.fields() );

        dataTableWriter.print ( out, store.getRequest() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm();

    ApplicationSession applications = ApplicationSession.query ( jdbc, store.getRequest(), qaf.getCompanyId(), false );

    PeriodSession periods = PeriodSession.query ( jdbc, store.getRequest(), qaf.getCompanyId(), qaf.getApplicationId(), false );

    return getForward ( store );
    }

}
