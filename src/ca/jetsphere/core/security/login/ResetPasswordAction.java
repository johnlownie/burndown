package ca.jetsphere.core.security.login;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.email.EmailYard;
import ca.jetsphere.core.email.broker.SendMailBrokerYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.security.hash.PasswordHash;
import ca.jetsphere.core.tier1.backbone.token.Token;
import ca.jetsphere.core.tier1.backbone.token.TokenYard;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 *
 */

public class ResetPasswordAction extends AbstractLoginAction
{
    /**
     *
     */

    protected String getContent ( JDBC jdbc, HttpServletRequest request, User user, String url, String uuid )
    {
    StringBuilder sb = new StringBuilder();

    String link = "<a href=\"" + url + "?vkey=" + uuid + "&resend=true\"><u class=\"text-primary text-bold\">" + Caption.get ( request, "click.here" ) + "</u></a>";

    sb.append ( "<p>" + Caption.get ( request, "password.reset.sent.email.1", new String[] { user.getUsername() } ) + "</p>" );

    sb.append ( "<p>" + Caption.get ( request, "password.reset.sent.email.2" ) + "</p>" );

//    sb.append ( "<p>" + Caption.get ( request, "password.reset.sent.email.3", new String[] { link } ) + "</p>" );

    return sb.toString();
    }

    /**
     *
     */

    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        User user = ( User ) store.getForm(); String content = "";

        JSONObject fields = validate ( jdbc, store.getRequest(), user );

        if ( fields.isEmpty() )
        {
        User x = UserYard.getByEmail ( jdbc, user.getUsername() ); String token = TokenYard.get ( jdbc, x.getId(), 30 );

        String url = store.getForward ( "success" ).getPath();

        content = getContent ( jdbc, store.getRequest (), x, DockYard.getURL ( store.getRequest (), url.substring ( 1 ) ), token );

        ResetPasswordMessage message = new ResetPasswordMessage ( jdbc, store.getRequest(), errors ); message.set ( user, token );

        if ( errors.isEmpty() ) SendMailBrokerYard.send ( store.getRequest().getServletContext(), message );
        }

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        JSONObject jsonObject = new JSONObject();

        if ( fields.isEmpty() ) jsonObject.put ( "content", content ); else jsonObject.put ( "fields", fields ); jsonObject.put ( "success", fields.isEmpty() );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception
    {
    User form = ( User ) bolt; Token token = TokenYard.getByUuid ( jdbc, form.getUuid() );

    if ( token == null ) { errors.add ( "error.password.reset.expired.key" ); return; }

    User user = new User ( jdbc, token.getIdentifier() );

    if ( user == null || ! user.isValid() ) { errors.add ( "password", "error.password.change.invalid.user" ); return; }

    form.copy ( user ); form.setPassword ( PasswordHash.createHash ( form.getNewPassword() ) );
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
    User form = ( User ) bolt;

    Token token = TokenYard.getByUuid ( jdbc, form.getUuid() );

    if ( token == null ) { errors.add ( "error.password.reset.expired.key" ); return; }

    User user = new User ( jdbc, token.getIdentifier() );

    if ( user == null || ! user.isValid() ) { errors.add ( "password", "error.password.change.invalid.user" ); return; }

    if ( DockYard.compareTo ( user.getNewPassword(), user.getConfirmPassword() ) != 0 ) { errors.add ( "newPassword", "error.password.change.nomatch" ); return; }
    }

    /**
     *
     */

    protected JSONObject validate ( JDBC jdbc, HttpServletRequest request, User user )
    {
    JSONObject jsonObject = new JSONObject(); String address = user.getUsername();

    if ( ! EmailYard.isValid ( address ) )

    { jsonObject.put ( "username", Caption.get ( request, "error.password.reset.invalid.email" ) ); return jsonObject; }

    User x = UserYard.getByEmail ( jdbc, address );

    if ( x == null || !x.isValid () )

    jsonObject.put ( "username", Caption.get ( request, "error.password.reset.no.match", new String[] { address } ) );

    return jsonObject;
    }

    /**
     *
     */

    public ActionForward verify ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    setDefaults ( jdbc, store.getRequest() );

    String uuid = DockYard.getParameter ( store.getRequest(), "vkey" );

    boolean isResend = DockYard.toBoolean ( DockYard.getParameter ( store.getRequest(), "resend" ) );

    if ( DockYard.isWhiteSpace ( uuid ) ) { errors.add ( "error.password.reset.invalid.key" ); return store.getForward ( "invalid", "", true ); }

    Token token = TokenYard.getByUuid ( jdbc, uuid );

    if ( token == null ) { errors.add ( "error.password.reset.expired.key" ); return store.getForward ( "invalid", "", true ); }

    User user = new User ( jdbc, token.getIdentifier () );

    if ( user == null || ! user.isValid() ) return store.getForward ( "invalid" );

    if ( isResend ) return store.getForward ( "success", "?token=" + uuid );

    return store.getForward ( "change" );
    }

}
