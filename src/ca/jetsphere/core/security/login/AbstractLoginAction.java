package ca.jetsphere.core.security.login;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.security.SecurityYard;
import ca.jetsphere.core.security.cookie.CookieYard;
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.application.ApplicationYard;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.company.CompanyYard;
import ca.jetsphere.core.tier1.backbone.period.Period;
import ca.jetsphere.core.tier1.backbone.period.PeriodSession;
import ca.jetsphere.core.tier1.backbone.policy.PolicySession;
import ca.jetsphere.core.tier1.backbone.role.RoleYard;
import ca.jetsphere.core.tier1.backbone.token.TokenYard;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;
import ca.jetsphere.core.tier2.common.AbstractAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.tabs.TabBar;
import ca.jetsphere.core.tier2.tabs.TabSet;
import ca.jetsphere.core.tier2.tabs.TabYard;

import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */

abstract public class AbstractLoginAction extends AbstractAction
{
    /**
     *
     */

    public ActionForward getForward ( ActionStore store )

    { return new ActionForward ( TabYard.getForward ( store.getRequest() ).getPath(), true ); }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    setDefaults ( jdbc, store.getRequest() );

    return super.getForward ( store );
    }

    /**
     *
     */

    protected void resetDefaults ( JDBC jdbc, HttpServletRequest request ) throws Exception

    { if ( ! UserYard.whoAmI ( request ).isValid() ) { SecurityYard.clearSession ( request ); setDefaults ( jdbc, request ); } }

    /**
     *
     */

    protected void setApplication ( JDBC jdbc, HttpServletRequest request, User user ) throws Exception

    { ApplicationYard.setApplications ( jdbc, request, user ); ApplicationYard.setDefaultApplication ( jdbc, request, user ); }

    /**
     *
     */

    protected void setCompany ( JDBC jdbc, HttpServletRequest request, int company_id ) throws Exception
    {
    CompanySession.query ( jdbc, request, company_id, false );

    if ( company_id > 0 ) CompanySession.setSelected ( request, new Company ( jdbc, company_id ) );
    }

    /**
     *
     */

    protected void setCookies ( HttpServletRequest request, HttpServletResponse response, User user ) throws Exception
    {
    String value = user.getStayLoggedIn() ? DockYard.toString ( user.getStayLoggedIn() ) : null;

    int max_age = user.getStayLoggedIn() ? 365 * 24 * 60 * 60 : 0;

    CookieYard.addCookie ( response, "rememberme", value, max_age, "/", "" );

    value = user.getStayLoggedIn() ? user.getUuid() : null;

    CookieYard.addCookie ( response, "UID", value, max_age, "/", "" );
    }

    /**
     *
     */

    protected void setDefaults ( JDBC jdbc, HttpServletRequest request ) throws Exception
    {
    CompanyYard.setDefault ( jdbc, request );

    Company company = CompanySession.getSelected ( request );

    PolicySession.query ( jdbc, request, company.getId(), false );
    }

    /**
     *
     */

    protected void setPeriod ( JDBC jdbc, HttpServletRequest request ) throws Exception
    {
    Application application = ApplicationSession.getSelected ( request );

    PeriodSession.query ( jdbc, request, -1, application.getId(), false );

    PeriodSession.setSelected ( request, new Period ( jdbc, application.getPeriodId() ) );
    }

    /**
     *
     */

    protected void setSession ( JDBC jdbc, HttpServletRequest request, User user ) throws Exception
    {
    setCompany ( jdbc, request, user.getCompanyMember().getCompanyId() );

    setApplication ( jdbc, request, user );

    setPeriod ( jdbc, request );

    setTabBar ( jdbc, request, user );

    user.setIsAdministrator ( RoleYard.hasRole ( jdbc, request, user, new String[] { "SA", "SE", "SR", "LICENSOR" } ) );

    UserYard.whoAmI ( request, user );

    UserYard.setLastLogin ( jdbc, user );
    }

    /**
     *
     */

    protected void setTabBar ( JDBC jdbc, HttpServletRequest request, User user ) throws Exception
    {
    TabSet tabSet = TabYard.getTabs ( jdbc, request, user );

    TabBar tabBar = new TabBar ( tabSet );

    TabYard.setTabBar ( request, tabBar );
    }

}