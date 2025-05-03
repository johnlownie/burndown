package ca.jetsphere.core.tier1.backbone.user.registration;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier1.system.knock.Knock;
import ca.jetsphere.core.email.broker.SendMailQueuedMessage;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.company.CompanyYard;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class VerificationMessage extends SendMailQueuedMessage
{
    JDBC jdbc; HttpServletRequest request; Errors errors;

    /**
     *
     */

    public VerificationMessage( JDBC jdbc, HttpServletRequest request, Errors errors )

    { this.jdbc = jdbc; this.request = request; this.errors = errors; }

    /**
     *
     */

    protected List getCc ( Company company )
    {
    ArrayList list = new ArrayList();

    if ( !DockYard.isWhiteSpace ( company.getEmail() ) ) list.add ( company.getEmail() );

    return list;
    }

    /**
     *
     */

    protected String getFrom ( Company company )

    { return company == null || ! company.isValid() || DockYard.isWhiteSpace ( company.getEmail () ) ? Knock.get ( "SMTP-MAIL-SERVER-SENDER" ) : company.getEmail(); }

    /**
     *
     */

    public String getMessage ( Company company, User user, String token_uuid )
    {
    StringBuilder sb = new StringBuilder();

    sb.append ( "<style>p { margin: 1em 0em 0em 0em; }</style>" );

    sb.append ( "<strong>" );

    sb.append ( Caption.get ( request, "register.email.verify.2", new String[] { user.getNotes () } ) );

    sb.append ( "</strong>" );

    sb.append ( "<p>" );

    sb.append ( Caption.get ( request, "register.email.verify.3", new String[] { company.getName() } ) );

    sb.append ( "</p><p>" );

    sb.append ( Caption.get ( request, "register.email.verify.4" ) );

    sb.append ( "</p><p>" );

    String url = DockYard.getURL ( request, "register.do", "?vkey=" + token_uuid );

    sb.append ( "<a href=\"" + url + "\">" + url + "</a>" );

    sb.append ( "</p><p>" );

    sb.append ( Caption.get ( request, "register.email.verify.5" ) );

    sb.append ( "&nbsp;&nbsp;" );

    sb.append ( Caption.get ( request, "register.email.verify.6", new String[] { "<a href=\"mailto:" + company.getEmail() + "\">" + company.getEmail() + "</a>" } ) );

    sb.append ( "</p><p>" );

    sb.append ( Caption.get ( request, "register.email.verify.7", new String[] { company.getName() } ) );

    sb.append ( "</p><p>" );

    sb.append ( Caption.get ( request, "register.email.verify.8" ) );

    sb.append ( "</p>" );

    return sb.toString();
    }

    /**
     *
     */

    protected String getPersonal ( Company company )

    { return Caption.get ( request, "order.from.email", new String[] { company.getName() } ); }

    /**
     *
     */

    protected String getSubject ( Company company )

    { return Caption.get ( request, "register.email.verify.subject", new String[] { company.getName() } ); }

    /**
     *
     */

    protected List getTo ( User user )
    {
    ArrayList list = new ArrayList();

    list.add ( user.getUsername() );

    return list;
    }

    /**
     *
     */

    public void set ( User user, String token_uuid )
    {
    Company company = CompanySession.getSelected ( request );

    if ( company == null || ! company.isValid() )

    { CompanyYard.setDefault ( jdbc, request ); company = CompanySession.getSelected ( request ); }

    setFrom     ( getFrom     ( company                   ) );
    setTo       ( getTo       ( user                      ) );
    setCc       ( getCc       ( company                   ) );
    setPersonal ( getPersonal ( company                   ) );
    setSubject  ( getSubject  ( company                   ) );
    setMessage  ( getMessage  ( company, user, token_uuid ) );
    }

}
