package ca.jetsphere.burndown.tier2.roles.mgr.dashboard;

import ca.jetsphere.burndown.tier1.roles.mgr.dashboard.DashboardYard;
import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import java.io.PrintWriter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForward;

/**
 *
 */
public class DashboardDoAction extends AbstractDoAction
{
    /**
     *
     */
    public String getKey() { return "dashboard"; }

    /**
     *
     */
    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    return getForward ( store );
    }

    /**
     *
     */

    public ActionForward report ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();
        
        String categoryData = DashboardYard.getByCategory ( jdbc, qaf.getPeriodId() );
        
        String monthData = "[{ month: 'Brazil', nb: 5, e: 1 }, { month: 'Italy', nb: 4, e: 2 }, { month: 'Germany', nb: 4, e: 4 }, { month: 'Uruguay', nb: 2, e: 9 }, { month: 'Argentina', nb: 2, e: 10 }]";

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "categoryData", categoryData); jsonObject.put ( "monthData", monthData);

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }
}
