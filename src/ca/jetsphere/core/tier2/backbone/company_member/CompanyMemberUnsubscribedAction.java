package ca.jetsphere.core.tier2.backbone.company_member;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMember;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMemberYard;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import org.apache.struts.action.ActionForward;

/**
 *
 */

public class CompanyMemberUnsubscribedAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return CompanyMember.key(); }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    String uuid = DockYard.getParameter ( store.getRequest(), "csrf" );

    if ( DockYard.isWhiteSpace ( uuid ) ) return store.getForward ( "success" );

    CompanyMember companyMember = CompanyMemberYard.getByUuid ( jdbc, uuid );

    if ( companyMember == null || ! companyMember.isValid() ) return store.getForward ( "success" );

    companyMember.setSubscribed ( false ); companyMember.save ( jdbc );

    return super.getForward ( store );
    }

}
