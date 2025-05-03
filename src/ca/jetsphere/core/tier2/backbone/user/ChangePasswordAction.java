package ca.jetsphere.core.tier2.backbone.user;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.security.hash.PasswordHash;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserSession;
import ca.jetsphere.core.tier1.backbone.user.UserYard;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 *
 */

public class ChangePasswordAction extends AbstractEditAction
{
    /**
     *
     */

    public String getKey() { return User.key(); }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors )
    {
    User whoamI = UserYard.whoAmI ( store.getRequest() );

    UserSession users = UserSession.getInstance ( store.getRequest() );

    users.setNonQualifiedSelected ( store.getRequest(), whoamI );

    return getForward ( store );
    }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception
    {
    User user = ( User ) bolt; User whoAmI = UserYard.whoAmI ( request );

    user.copy ( whoAmI ); user.setPassword ( PasswordHash.createHash ( user.getNewPassword() ) );
    }

    /**
     *
     */

    public ActionForward update ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        User user = ( User ) store.getForm();

        update ( jdbc, store.getRequest(), user, errors );

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

    protected void validate ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception
    {
    User user = ( User ) bolt; User whoAmI = UserYard.whoAmI ( request );

    if ( whoAmI == null || !whoAmI.isValid() ) { errors.add ( "password", "error.password.change.invalid.user" ); return; }

    if ( ! PasswordHash.validatePassword ( user.getPassword(), whoAmI.getPassword () ) ) { errors.add ( "password", "error.password.change.invalid.current" ); return; }

    if ( DockYard.compareTo ( user.getNewPassword(), user.getConfirmPassword() ) != 0 ) { errors.add ( "newPassword", "error.password.change.nomatch" ); return; }
    }

}
