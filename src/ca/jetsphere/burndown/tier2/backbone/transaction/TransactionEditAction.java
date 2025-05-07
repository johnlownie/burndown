package ca.jetsphere.burndown.tier2.backbone.transaction;

import ca.jetsphere.burndown.tier1.backbone.category.CategorySession;
import ca.jetsphere.burndown.tier1.backbone.transaction.Transaction;
import ca.jetsphere.burndown.tier1.backbone.transaction.TransactionYard;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import java.io.PrintWriter;
import net.sf.json.JSONObject;
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
    public ActionForward jupdate ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        Transaction transaction = ( Transaction ) store.getForm();
        
        update ( jdbc, store.getRequest(), transaction, errors );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        errors.forward ( store );
        
        // update uncategorized transactions with same name
        TransactionYard.setUncategorized ( jdbc, transaction.getCategoryId(), transaction.getName() );

        jsonObject.put ( "success", errors.isEmpty() );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

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