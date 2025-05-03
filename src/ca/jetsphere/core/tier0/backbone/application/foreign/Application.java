package ca.jetsphere.core.tier0.backbone.application.foreign;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.company.Company;

/**
 *
 */

abstract public class Application extends ca.jetsphere.core.tier0.backbone.application.bean.Application
{
    Company company;

    /**
     *
     */

    static public Class container() { return Company.class; }

    /**
     *
     */

    public void foreign ( JDBC jdbc ) throws Exception

    { setCompany ( new Company ( jdbc, getCompanyId() ) ); }

    /**
     *
     */

    public Company getCompany() { return company; }

    /**
     *
     */

    public void setCompany ( Company company ) { this.company = company; }

}