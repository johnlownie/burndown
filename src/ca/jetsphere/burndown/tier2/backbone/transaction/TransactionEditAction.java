package ca.jetsphere.burndown.tier2.backbone.transaction;

import ca.jetsphere.burndown.tier1.backbone.category.CategorySession;
import ca.jetsphere.burndown.tier1.backbone.transaction.Transaction;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import org.apache.struts.action.ActionForward;

/**
 *
 */
public class TransactionEditAction extends AbstractEditAction
{
    /**
     *
     */
    public String getKey() { return Transaction.key(); }

    /**
     *
     */
    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    Company company = CompanySession.getSelected ( store.getRequest() );

    CategorySession.query ( jdbc, store.getRequest(), company.getId(), true );

    return store.getForward ( "failure" );
    }
}