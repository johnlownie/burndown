package ca.jetsphere.core.tier2.backbone.user.profile;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMember;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;

/**
 *
 */

public class UserProfileDoAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return User.key (); }

    /**
     *
     */

    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        CompanyMember companyMember = ( CompanyMember ) store.getForm();

        update ( jdbc, store.getRequest(), companyMember, errors );

        JSONObject fields = errors.getFields ( store.getRequest() );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        JSONObject response = new JSONObject();

        response.put ( "fields", fields ); response.put ( "success", errors.isEmpty() );

        out.write ( response.toString() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    User whoAmI = UserYard.whoAmI ( store.getRequest() ); User user = new User();

    user.setActionForm ( store.getRequest() );

    whoAmI.getCompanyMember().setActionForm ( store.getRequest () );

    return getForward ( store );
    }

}
