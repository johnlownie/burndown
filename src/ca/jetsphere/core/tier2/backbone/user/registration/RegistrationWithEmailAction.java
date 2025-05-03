package ca.jetsphere.core.tier2.backbone.user.registration;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.security.hash.PasswordHash;
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.application.ApplicationYard;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.company.CompanyYard;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMember;
import ca.jetsphere.core.tier1.backbone.policy.PolicySession;
import ca.jetsphere.core.tier1.backbone.role.Role;
import ca.jetsphere.core.tier1.backbone.token.Token;
import ca.jetsphere.core.tier1.backbone.token.TokenYard;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.common.Status;
import ca.jetsphere.core.tier1.backbone.user.registration.RegisterValidate;
import ca.jetsphere.core.tier1.backbone.user.registration.RegistrationYard;
import ca.jetsphere.core.tier2.backbone.user.UserEditAction;
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

public class RegistrationWithEmailAction extends UserEditAction
{
    /**
     *
     */

    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        User applicant = ( User ) store.getForm();

        update ( jdbc, store.getRequest(), applicant, errors );

        if ( errors.isEmpty() ) RegistrationYard.send ( jdbc, store.getRequest(), applicant, errors );

        JSONObject fields = errors.getFields ( store.getRequest() );

        String content = RegistrationYard.getContent ( store.getRequest(), applicant.getUsername(), applicant.getOpenIdIdentifier() );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        JSONObject response = new JSONObject();

        response.put ( "content", content ); response.put ( "fields", fields ); response.put ( "success", errors.isEmpty() );

        out.write ( response.toString() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    CompanyYard.setDefault ( jdbc, store.getRequest() );

    Company company = CompanySession.getSelected ( store.getRequest() );

    PolicySession.query ( jdbc, store.getRequest(), company.getId (), false );

    return super.getForward ( store );
    }

    /**
     *
     */

    protected void setCompanyMember ( JDBC jdbc, HttpServletRequest request, User user ) throws Exception
    {
    Company company = CompanyYard.getDefault ( jdbc );

    CompanyMember companyMember = new CompanyMember(); user.setCompanyMember ( companyMember );

    companyMember.setUserId ( user.getId() ); companyMember.setCompanyId ( company.getId() );

    companyMember.setFirstname ( DockYard.getToken ( user.getNotes(), 1, " " ) ); companyMember.setLastname ( DockYard.getRemaining ( user.getNotes(), " " ) );

    companyMember.setEmail ( user.getUsername () ); companyMember.setAvatarId ( 1 );

    companyMember.save ( jdbc );
    }

    /**
     *
     */

    protected void setDefaultRole ( JDBC jdbc, HttpServletRequest request, User user ) throws Exception
    {
    Application application = ApplicationYard.getDefaultApplication ( jdbc, request );

    Role role = new Role ( jdbc, application.getRoleId() );

    user.setRoleIds ( DockYard.toString ( role.getId() ) );
    }

    /**
     *
     */

    public ActionForward update ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    User user = ( User ) store.getForm();

    update ( jdbc, store.getRequest(), user, errors );

    if ( !errors.isEmpty() ) return errors.forward ( store );

    return store.getForward ( "success", "?uuid=" + user.getUuid () );
    }

    /**
     *
     */

    public Errors update ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception
    {
    jdbc.setAutoCommit ( false );

    try {

        User applicant = ( User ) bolt;

        String g_recaptcha_response = DockYard.getParameter ( request, "g-recaptcha-response" );

        RegisterValidate registerValidate = new RegisterValidate();

        registerValidate.validate ( jdbc, applicant, g_recaptcha_response, errors ); if ( ! errors.isEmpty() ) { applicant.clear(); return errors; }

        applicant.setStatus ( Status.PENDING );

        applicant.setPassword ( PasswordHash.createHash ( applicant.getNewPassword () ) ); applicant.setNotes ( applicant.getFullname() );

        applicant.save ( jdbc );

        jdbc.commit();

    } catch ( Exception e ) { jdbc.rollback(); Common.trace ( e ); }

    finally { jdbc.setAutoCommit ( true ); }

    return errors;
    }

    /**
     *
     */

    public ActionForward verify ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    query ( jdbc, store, errors );

    String uuid = DockYard.getParameter ( store.getRequest(), "vkey" );

    boolean isResend = DockYard.toBoolean ( DockYard.getParameter ( store.getRequest(), "resend" ) );

    if ( DockYard.isWhiteSpace ( uuid ) ) { errors.add ( "error.registration.invalid.key" ); return store.getForward ( "invalid", "", false ); }

    Token token = TokenYard.getByUuid ( jdbc, uuid );

    if ( token == null ) { errors.add ( "error.registration.expired.key" ); return store.getForward ( "invalid", "", false ); }

    User user = new User ( jdbc, token.getIdentifier() );

    if ( user == null || ! user.isValid() || user.getStatus() != Status.PENDING ) return store.getForward ( "invalid", "", false );

    if ( isResend )

    { RegistrationYard.resend ( jdbc, store.getRequest(), user, errors ); errors.add ( "register.email.resent" ); return store.getForward ( "invalid", "", false );}

    setCompanyMember ( jdbc, store.getRequest(), user ); setDefaultRole ( jdbc, store.getRequest(), user ); user.setNotes ( "" ); user.setStatus ( Status.ACTIVE );

    user.update ( jdbc );

    return store.getForward ( "welcome", "?verified=true" );
    }

}
