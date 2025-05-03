package ca.jetsphere.burndown.tier2.backbone.category;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.table.DataTableWriter;

import ca.jetsphere.burndown.tier1.backbone.category.Category;
import ca.jetsphere.burndown.tier1.backbone.category.CategorySession;
import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;

import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;

/**
 *
 */
public class CategoryDoAction extends AbstractDoAction
{
    /**
     *
     */
    public String getKey() { return Category.key (); }

    /**
     *
     */
    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        CategorySession categorys = CategorySession.query ( jdbc, store.getRequest(), qaf.getCompanyId(), false );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableWriter dataTableWriter = new DataTableWriter ( categorys, Category.captions(), Category.fields() );

        dataTableWriter.print ( out, store.getRequest() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */
    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Company company = CompanySession.getSelected ( store.getRequest () );

    CategorySession.query ( jdbc, store.getRequest(), company.getId(), false );

    return getForward ( store );
    }
}
