package ca.jetsphere.core.tier2.backbone.application;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.period.PeriodSession;
import ca.jetsphere.core.tier1.backbone.role.RoleSession;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;

import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class ApplicationEditAction extends AbstractEditAction
{
    /**
     *
     */

    public String getKey() { return Application.key(); }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    Application application = ApplicationSession.getSelected ( store.getRequest() );

    PeriodSession.query ( jdbc, store.getRequest(), -1, application.getId(), true );

    RoleSession.query ( jdbc, store.getRequest(), application.getId(), true );

    return getForward ( store );
    }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors )
    {
    Application application = ( Application ) bolt;

    Company company = CompanySession.getSelected ( request );

    application.setCompanyId ( company.getId() );
    }

}