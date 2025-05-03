package ca.jetsphere.core.tier2.backbone.audit;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.audit.Audit;
import ca.jetsphere.core.tier1.backbone.audit.AuditSession;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.user.UserSession;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.common.QueryActionForm;

import org.apache.struts.action.ActionForward;

/**
 *
 */

public class AuditDoAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return Audit.key (); }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Company company = CompanySession.getSelected ( store.getRequest () );

    UserSession users = UserSession.query ( jdbc, store.getRequest(), company.getId(), false );

    AuditSession.query ( jdbc, store.getRequest (), qaf.getUserId(), false );

    return getForward ( store );
    }

}