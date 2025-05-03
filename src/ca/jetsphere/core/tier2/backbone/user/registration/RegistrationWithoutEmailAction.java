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
import ca.jetsphere.core.tier1.backbone.user.UserYard;
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

public class RegistrationWithoutEmailAction extends UserEditAction
{
    /**
     *
     */

    public ActionForward commit  ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        String username = DockYard.getParameter ( store.getRequest(), "username" );

        if ( DockYard.isWhiteSpace ( username ) ) return null;

        User x = UserYard.getByUsername ( jdbc, username );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "valid", ! x.isValid() );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    jdbc.setAutoCommit ( false );

    try {

        User applicant = ( User ) store.getForm();

        String g_recaptcha_response = DockYard.getParameter ( store.getRequest(), "g-recaptcha-response" );

        RegisterValidate registerValidate = new RegisterValidate();

        registerValidate.validate ( jdbc, applicant, g_recaptcha_response, errors );

        JSONObject fields = errors.getFields ( store.getRequest() );

        if ( errors.isEmpty() )
        {
        applicant.setStatus ( Status.ACTIVE ); applicant.setPassword ( PasswordHash.createHash ( applicant.getNewPassword() ) );

        setDefaultRole ( jdbc, store.getRequest(), applicant );

        applicant.save ( jdbc );

        setCompanyMember ( jdbc, store.getRequest(), applicant );

        jdbc.commit();
        }

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        if ( fields.isEmpty() ) jsonObject.put ( "url", "login.do?verified=true" ); else jsonObject.put ( "fields", fields ); jsonObject.put ( "success", fields.isEmpty() );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { jdbc.rollback(); Common.trace ( e ); }

    finally { jdbc.setAutoCommit ( true ); }

    return null;
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

    companyMember.setFirstname ( DockYard.getToken ( user.getFullname(), 1, " " ) ); companyMember.setLastname ( DockYard.getRemaining ( user.getFullname(), " " ) );

    companyMember.setMobilePhone ( user.getPhone() ); companyMember.setEmail ( user.getUsername () ); companyMember.setAvatarId ( 1 );

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

    return store.getForward ( "success", "?verified=true" );
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

        applicant.setStatus ( Status.ACTIVE ); applicant.setPassword ( PasswordHash.createHash ( applicant.getNewPassword () ) );

        setDefaultRole ( jdbc, request, applicant );

        applicant.save ( jdbc );

        setCompanyMember ( jdbc, request, applicant );

        jdbc.commit();

    } catch ( Exception e ) { jdbc.rollback(); Common.trace ( e ); }

    finally { jdbc.setAutoCommit ( true ); }

    return errors;
    }

}
