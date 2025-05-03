package ca.jetsphere.core.tier1.backbone.user.registration;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.email.broker.SendMailBrokerYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.token.Token;
import ca.jetsphere.core.tier1.backbone.token.TokenYard;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class RegistrationYard
{
    /**
     *
     */

    static public String getContent ( HttpServletRequest request, String username, String token_uuid )
    {
    StringBuilder sb = new StringBuilder();

    String link = "<a href=\"" + DockYard.getURL ( request, "register.do", "?vkey=" + token_uuid + "&resend=true" ) + "\"><u class=\"text-primary text-bold\">" + Caption.get ( request, "click.here" ) + "</u></a>";

    sb.append ( "<p>" + Caption.get ( request, "register.sent.email.1", new String[] { username } ) + "</p>" );

    sb.append ( "<p>" + Caption.get ( request, "register.sent.email.2" ) + "</p>" );

    sb.append ( "<p>" + Caption.get ( request, "register.sent.email.3", new String[] { link } ) + "</p>" );

    return sb.toString();
    }

    /**
     *
     */

    static public String getContent ( JDBC jdbc, HttpServletRequest request, User user, Errors errors )
    {
    Token token = TokenYard.getByIdentifier ( jdbc, user.getId() );

    if ( token == null || !token.isValid() ) { errors.add ( "error.registration.expired.key" ); return ""; }

    return getContent ( request, user.getUsername(), token.getUuid() );
    }

    /**
     *
     */

    static public void resend ( JDBC jdbc, HttpServletRequest request, User user, Errors errors )
    {
    if ( user == null || !user.isValid() ) { errors.add ( "error.registration.expired.key" ); return; }

    Token token = TokenYard.getByIdentifier ( jdbc, user.getId() );

    if ( token == null || !token.isValid() ) { errors.add ( "error.registration.expired.key" ); return; }

    sendVerificationMessage ( jdbc, request, user, token.getUuid(), errors );

    if ( !errors.isEmpty() ) { errors.add ( "error.registration.resend.email" ); return; }
    }

    /**
     *
     */

    static public void send ( JDBC jdbc, HttpServletRequest request, User user, Errors errors )
    {
    if ( user == null || !user.isValid() ) { errors.add ( "error.registration.expired.key" ); return; }

    String token = TokenYard.get ( jdbc, user.getId(), 30 );

    user.setOpenIdIdentifier ( token );

    sendVerificationMessage ( jdbc, request, user, token, errors );

    if ( !errors.isEmpty() ) { errors.add ( "username", "error.registration.resend.email" ); return; }
    }

    /**
     *
     */

    static public void sendVerificationMessage ( JDBC jdbc, HttpServletRequest request, User user, String token_uuid, Errors errors )
    {
    VerificationMessage verificationMessage = new VerificationMessage ( jdbc, request, errors ); verificationMessage.set ( user, token_uuid );

    if ( errors.isEmpty() ) SendMailBrokerYard.send ( request.getServletContext(), verificationMessage );
    }

}
