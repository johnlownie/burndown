package ca.jetsphere.core.tier2.backbone.policy;

import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import org.apache.struts.action.ActionForward;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.policy.Policy;
import ca.jetsphere.core.tier1.backbone.policy.PolicySession;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.common.QueryActionForm;

/**
 *
 */

public final class PolicyDoAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return Policy.key(); }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Company company = CompanySession.getSelected ( store.getRequest () );

    PolicySession.query ( jdbc, store.getRequest(), company.getId(), false );

    return getForward ( store );
    }

}
