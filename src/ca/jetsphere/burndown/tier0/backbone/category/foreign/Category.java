package ca.jetsphere.burndown.tier0.backbone.category.foreign;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.company.Company;

/**
 * 
 */
abstract public class Category extends ca.jetsphere.burndown.tier0.backbone.category.bean.Category
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
