package ca.jetsphere.core.tier0.backbone.action.foreign;

import ca.jetsphere.core.tier1.backbone.company.Company;

/**
 *
 */

abstract public class Action extends ca.jetsphere.core.tier0.backbone.action.bean.Action
{
    Company company;

    /**
     *
     */

    static public Class container() { return Company.class; }

    /**
     *
     */

    public Company getCompany() { return company; }

    /**
     *
     */

    public void setCompany ( Company company ) { this.company = company; }

}
