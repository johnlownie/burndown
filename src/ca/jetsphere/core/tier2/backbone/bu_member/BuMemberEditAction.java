package ca.jetsphere.core.tier2.backbone.bu_member;

import ca.jetsphere.core.tier1.backbone.application.ApplicationYard;
import org.apache.struts.action.ActionForward;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.bu_member.BuMember;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMemberYard;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;

/**
 *
 */

public class BuMemberEditAction extends AbstractEditAction
{
    /**
     *
     */

    public String getKey() { return BuMember.key (); }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    Application application = ApplicationYard.getDefaultApplication ( jdbc, store.getRequest() );

    CompanyMemberYard.setActiveMembers ( jdbc, store.getRequest(), application.getCompanyId() );

    return store.getForward ( "failure" );
    }

}
