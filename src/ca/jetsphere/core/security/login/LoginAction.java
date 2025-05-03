package ca.jetsphere.core.security.login;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.token.TokenYard;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;
import ca.jetsphere.core.tier1.backbone.user.registration.RegistrationYard;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 *
 */

public class LoginAction extends AbstractLoginAction
{
    /**
     *
     */

    public String getFields ( HttpServletRequest request, Errors errors )
    {
    StringBuilder sb = new StringBuilder();

    Iterator it = errors.iterator();

    while ( it.hasNext() )
    {
    ActionMessage message = ( ActionMessage ) it.next();

    if ( sb.length() > 0 ) sb.append ( "<br/>" ); sb.append ( Caption.get ( request, message.getKey() ) );
    }

    return sb.toString();
    }

    /**
     *
     */

    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        ActionForward forward = validate ( jdbc, store, errors );

        boolean isPending = DockYard.equalsIgnoreCase ( "pending", forward.getName() );

        String content = isPending ? RegistrationYard.getContent ( jdbc, store.getRequest(), ( User ) store.getForm(), errors ) : "";

        String fields = getFields ( store.getRequest(), errors );

        String path = forward.getPath(); String url = DockYard.getURL ( store.getRequest (), path.substring ( 1 ) );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        JSONObject response = new JSONObject();

        response.put ( "url", url ); response.put ( "content", content ); response.put ( "fields", fields );  response.put ( "pending", isPending ); response.put ( "success", errors.isEmpty () );

        out.write ( response.toString () );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    protected ActionForward forward ( ActionStore store, String s )
    {
    String forward    = DockYard.getToken ( s, 1, "|" );

    String parameters = DockYard.getToken ( s, 2, "|" );

    return DockYard.isWhiteSpace ( parameters ) ?  store.getForward ( forward ) : store.getForward ( forward, parameters );
    }

    /**
     *
     */

    final protected ActionForward validate ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        resetDefaults ( jdbc, store.getRequest() ); User login = ( User ) store.getForm();

        String forward = validate ( jdbc, login, errors );

        if ( ! "success".equals ( forward ) ) return forward ( store, forward );

        User user = UserYard.getByUsername ( jdbc, login.getUsername() );

        setCookies ( store.getRequest(), store.getResponse(), user );

        setSession ( jdbc, store.getRequest(), user );

        return getForward ( store );

    } catch ( Exception e ) { Common.trace ( e ); return errors.forward ( "error.login.query" ); }

    }

    /**
     *
     */

    final protected String validate ( JDBC jdbc, User user, Errors errors ) throws Exception
    {
    if ( ! LoginYard.isValid   ( jdbc, user ) ) { errors.add ( "error.login.invalid" ); return "invalid" ; }

    if (   LoginYard.isFrozen  ( jdbc, user ) ) { errors.add ( "error.login.frozen"  ); return "invalid" ; }

    if (   LoginYard.isPending ( jdbc, user ) ) { errors.add ( "error.login.pending" ); return "pending" ; }

    return "success";
    }

}
