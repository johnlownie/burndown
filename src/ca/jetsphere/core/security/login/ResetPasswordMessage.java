package ca.jetsphere.core.security.login;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier1.system.knock.Knock;
import ca.jetsphere.core.email.broker.SendMailQueuedMessage;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class ResetPasswordMessage extends SendMailQueuedMessage
{
    JDBC jdbc; HttpServletRequest request; Errors errors;

    /**
     *
     */

    public ResetPasswordMessage ( JDBC jdbc, HttpServletRequest request, Errors errors )

    { this.jdbc = jdbc; this.request = request; this.errors = errors; }

    /**
     *
     */

    protected List getCc ( Company company )
    {
    ArrayList list = new ArrayList ();

//    if ( !DockYard.isWhiteSpace ( company.getEmail () ) ) list.add ( company.getEmail () );

    return list;
    }

    /**
     *
     */

    protected String getFrom ( Company company )

    { return company == null || !company.isValid () || DockYard.isWhiteSpace ( company.getEmail () ) ? Knock.get ( "SMTP-MAIL-SERVER-SENDER" ) : company.getEmail (); }

    /**
     *
     */

    public String getMessage ( Company company, String uuid )
    {
    StringBuilder sb = new StringBuilder ();

    sb.append ( "<style>p { margin: 1em 0em 0em 0em; }</style>" );

    sb.append ( "<p>" );

    sb.append ( Caption.get ( request, "password.reset.email.verify.1", new String[] { company.getName () } ) );

    sb.append ( "</p><p>" );

    sb.append ( Caption.get ( request, "password.reset.email.verify.2" ) );

    sb.append ( "</p><p>" );

    String url = DockYard.getURL ( request, "reset_password.do", "?vkey=" + uuid );

    sb.append ( "<a href=\"" + url + "\">" + url + "</a>" );

    sb.append ( "</p><p>" );

    sb.append ( Caption.get ( request, "password.reset.email.verify.3" ) );

    sb.append ( "&nbsp;&nbsp;" );

    sb.append ( Caption.get ( request, "password.reset.email.verify.4", new String[] { "<a href=\"mailto:" + company.getEmail () + "\">" + company.getEmail () + "</a>" } ) );

    sb.append ( "</p><p>" );

    sb.append ( Caption.get ( request, "password.reset.email.verify.5", new String[] { company.getName () } ) );

    sb.append ( "</p>" );

    return sb.toString ();
    }

    /**
     *
     */

    protected String getPersonal ( Company company )

    { return Caption.get ( request, "order.from.email", new String[] { company.getName () } ); }

    /**
     *
     */

    protected String getSubject ( Company company )

    { return Caption.get ( request, "password.reset.email.verify.subject", new String[] { company.getName () } ); }

    /**
     *
     */

    protected List getTo ( User user )
    {
    ArrayList list = new ArrayList ();

    list.add ( user.getUsername () );

    return list;
    }

    /**
     *
     */

    public void set ( User user, String uuid )
    {
    Company company = CompanySession.getSelected ( request );

    setFrom     ( getFrom     ( company       ) );
    setTo       ( getTo       ( user          ) );
    setCc       ( getCc       ( company       ) );
    setPersonal ( getPersonal ( company       ) );
    setSubject  ( getSubject  ( company       ) );
    setMessage  ( getMessage  ( company, uuid ) );
    }

}