package ca.jetsphere.burndown.tier2.backbone.transaction;

import ca.jetsphere.burndown.tier1.backbone.account.AccountSession;
import ca.jetsphere.burndown.tier1.backbone.category.CategorySession;
import ca.jetsphere.burndown.tier1.backbone.transaction.Transaction;
import ca.jetsphere.burndown.tier1.backbone.transaction.TransactionYard;
import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.period.PeriodSession;
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
    Application application = ApplicationSession.getSelected ( store.getRequest() );

    AccountSession.query ( jdbc, store.getRequest(), application.getId(), true );
    
    CategorySession.query ( jdbc, store.getRequest(), application.getId(), true );

    return store.getForward ( "failure" );
    }

    /**
     *
     */
    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception
    {
    Transaction transaction = ( Transaction ) bolt; int period_id = PeriodSession.getSelected ( request ).getId();
    
    transaction.setPeriodId ( period_id );
    }
}