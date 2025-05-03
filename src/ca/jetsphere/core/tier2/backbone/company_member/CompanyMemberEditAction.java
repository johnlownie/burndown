package ca.jetsphere.core.tier2.backbone.company_member;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMember;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class CompanyMemberEditAction extends AbstractEditAction
{
    /**
     *
     */

    public String getKey() { return CompanyMember.key(); }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception
    {
    CompanyMember companyMember = ( CompanyMember ) bolt; Company company = CompanySession.getSelected ( request );

    companyMember.setCompanyId ( company.getId() );
    }

}
