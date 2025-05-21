package ca.jetsphere.burndown.tier2.backbone.category;

import ca.jetsphere.burndown.tier1.backbone.category.Category;
import ca.jetsphere.burndown.tier1.backbone.category.CategorySession;
import ca.jetsphere.burndown.tier1.backbone.category.CategoryYard;
import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.tree.TreeYard;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForward;

/**
 *
 */
public class CategoryEditAction extends AbstractEditAction
{
    /**
     *
     */
    public ActionForward delete ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        String uuid = DockYard.getParameter ( store.getRequest(), "csrf" );

        if ( DockYard.isWhiteSpace ( uuid ) ) errors.add ( "error.category.cut" );

        CategorySession categories = CategorySession.getInstance ( store.getRequest() ); categories.clearSelected ( store.getRequest() );

        Category child = ( Category ) categories.getBoltByUuid ( uuid );

        if ( child == null || !child.isValid() ) errors.add ( "error.category.cut" );

        Category parent = ( Category ) categories.get ( child.getParentId() );

        if ( parent == null || !parent.isValid() ) errors.add ( "error.category.cut" );

        if ( errors.isEmpty() ) { TreeYard.cut ( jdbc, parent, child ); categories.remove ( child ); }

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "success", errors.isEmpty() );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { return null; }
    }

    /**
     *
     */
    protected void finito ( JDBC jdbc, HttpServletRequest request, Bolt bolt, boolean isUpdate, Errors errors ) throws Exception
    {
    if ( isUpdate ) return;

    Category category = ( Category ) bolt; category.foreign ( jdbc );

    CategorySession categories = CategorySession.getInstance ( request );

    categories.add ( category ); categories.treeify();
    }

    /**
     *
     */
    public String getKey() { return Category.key(); }

    /**
     *
     */
    public ActionForward insert ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {
        
        CategorySession categories = CategorySession.getInstance ( store.getRequest() ); categories.clearSelected ( store.getRequest() );

        String uuid = DockYard.getParameter ( store.getRequest(), "csrf" );

        boolean is_before = DockYard.toBoolean ( store.getRequest(), "before" ); boolean is_child = DockYard.toBoolean ( store.getRequest(), "child" );

        if ( DockYard.isWhiteSpace ( uuid ) ) return null;

        Category selected = ( Category ) categories.getBoltByUuid ( uuid ); Category form = ( Category ) categories.getSelected();

        Category sibling = new Category(); sibling.copy ( selected );

        if ( sibling != null && sibling.isValid() ) sibling.getPayload ( jdbc );

        form.clear(); form.setParentId ( is_child ? sibling.getId() : sibling.getParentId() ); form.setInsertType ( is_before ? 1 : 2 );
        
    } catch ( Exception e ) { Common.trace ( e ); }

    finally { return query ( jdbc, store, errors ); }
    }

    /**
     *
     */
    public ActionForward paste ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        String uuid = DockYard.getParameter ( store.getRequest(), "csrf" );

        if ( DockYard.isWhiteSpace ( uuid ) ) errors.add ( "error.category.paste" );

        CategorySession roleRights = CategorySession.getInstance ( store.getRequest() );

        Category parent = ( Category ) roleRights.getBoltByUuid ( uuid );

        if ( parent == null || !parent.isValid() )  errors.add ( "error.category.paste" );

        Category child = ( Category ) DockYard.getAttribute ( store.getRequest(), "[copyTree]" );

        if ( child == null || !child.isValid() )  errors.add ( "error.category.paste" );

        if ( errors.isEmpty() ) TreeYard.paste ( jdbc, roleRights, parent, child );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "success", errors.isEmpty() );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */
    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception
    {
    Category category = ( Category ) bolt; Category parent = new Category ( jdbc, category.getParentId() );

    if ( parent == null || ! parent.isValid() ) return;

    category.setApplicationId ( parent.getApplicationId() ); category.setParentUuid ( parent.getUuid() );

    category.setDepth ( parent.getDepth() + 1 ); category.setLineage ( parent.getLineage() + DockYard.zeroPad ( parent.getOrdinal(), 2 ) + "/" );

    if ( category.getOrdinal() < 0 ) category.setOrdinal ( CategoryYard.getCount ( jdbc, parent.getApplicationId(), parent.getId() ) );
    }
}