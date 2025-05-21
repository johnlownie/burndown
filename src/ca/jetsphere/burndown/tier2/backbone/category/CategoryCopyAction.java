package ca.jetsphere.burndown.tier2.backbone.category;

import ca.jetsphere.burndown.tier1.backbone.category.Category;
import ca.jetsphere.burndown.tier1.backbone.category.CategoryCopy;
import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.bolt.BoltCopyMap;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;

import org.apache.struts.action.ActionForward;


/**
 *
 */
public class CategoryCopyAction extends AbstractDoAction
{
    /**
     *
     */
    public String getKey() { return Category.key (); }
    
    /**
     *
     */
    public ActionForward insert ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm();

    CategoryCopy categoryCopy = new CategoryCopy ( new BoltCopyMap() );
    
    categoryCopy.copy ( jdbc, qaf.getApplicationId(), qaf.getRoleId() );
    
    return getForward ( store );
    }

    /**
     *
     */
    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Company company = CompanySession.getSelected ( store.getRequest () );
    
    ApplicationSession applications = ApplicationSession.query ( jdbc, store.getRequest(), company.getId(), false );

    return getForward ( store );
    }
}
