package ca.jetsphere.core.tier2.backbone.company;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
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
public class CompanyDoAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return Company.key(); }

    /**
     *
     */

    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        CompanySession companies = CompanySession.query ( jdbc, store.getRequest(), -1, false );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableWriter dataTableWriter = new DataTableWriter ( companies, Company.captions(), Company.fields() );

        dataTableWriter.print ( out, store.getRequest() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception

    { return getForward ( store ); }

}