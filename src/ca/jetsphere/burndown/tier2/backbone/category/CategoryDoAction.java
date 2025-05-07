package ca.jetsphere.burndown.tier2.backbone.category;

import ca.jetsphere.burndown.tier1.backbone.category.Category;
import ca.jetsphere.burndown.tier1.backbone.category.CategorySession;
import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.tree.OpenSet;
import ca.jetsphere.core.tier1.tree.TreeYard;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.tree.DataTableTreeWriter;

import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;
import net.sf.json.JSONObject;

/**
 *
 */
public class CategoryDoAction extends AbstractDoAction
{
    /**
     *
     */
    public ActionForward close ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        boolean expand = DockYard.toBoolean ( store.getRequest(), "expand" );

        CategorySession categories = CategorySession.getInstance ( store.getRequest() );

        if ( expand ) TreeYard.expand ( qaf.getOpenSet(), categories.getRoot() ); else TreeYard.collapse ( qaf.getOpenSet(), categories.getRoot() );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "success", errors.isEmpty() );

        out.write ( jsonObject.toString() );
        
        DockYard.setAttribute ( store.getRequest(), "openset", qaf.getOpenSet(), true );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { return null; }
    }

    /**
     *
     */
    public ActionForward export ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        int id = DockYard.toInteger ( store.getRequest(), "nodeId" );

        CategorySession categories = CategorySession.getInstance ( store.getRequest() );

        Category roleRight = ( Category ) categories.get ( id );

        qaf.getOpenSet().toggle ( roleRight.getId() );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "success", errors.isEmpty() );

        out.write ( jsonObject.toString() );
        
        DockYard.setAttribute ( store.getRequest(), "openset", qaf.getOpenSet(), true );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { return null; }
    }

    /**
     *
     */
    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        CategorySession categories = CategorySession.query ( jdbc, store.getRequest(), qaf.getCompanyId(), false );

        TreeYard.collapse ( qaf.getOpenSet(), categories.getRoot() );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "success", errors.isEmpty() );

//        JsonWriter jsonWriter = new JsonWriter ( categories );

//        String s = jsonWriter.get ( store.getRequest(), id );

        out.write ( jsonObject.toString() );
        
        DockYard.setAttribute ( store.getRequest(), "openset", qaf.getOpenSet(), true );
        
    } catch ( Exception e ) { Common.trace ( e ); }

    finally { return null; }
    }

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

        OpenSet openSet = ( OpenSet ) DockYard.getAttribute ( store.getRequest(), "openset", true );
        
        if ( openSet == null ) openSet = qaf.getOpenSet();

        CategorySession categories = CategorySession.getInstance ( store.getRequest() );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableTreeWriter dataTableTreeWriter = new DataTableTreeWriter ( categories, Category.captions(), Category.fields() );

        dataTableTreeWriter.print ( out, store.getRequest(), openSet );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */
    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Company company = CompanySession.getSelected ( store.getRequest () );

    qaf.getOpenSet().clear();

    CategorySession categories = CategorySession.query ( jdbc, store.getRequest(), company.getId(), false );

    return getForward ( store );
    }
}
