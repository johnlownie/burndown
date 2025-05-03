package ca.jetsphere.core.tier0.backbone.company_member.foreign;

import ca.jetsphere.core.tier0.backbone.company.Company;

/**
 *
 */

abstract public class CompanyMember extends ca.jetsphere.core.tier0.backbone.company_member.bean.CompanyMember
{
    /**
     *
     */

    static public Class container() { return Company.class; }

}
