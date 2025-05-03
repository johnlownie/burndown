package ca.jetsphere.core.tier1.backbone.company;

import ca.jetsphere.core.jdbc.JDBC;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class CompanyYard
{
    /**
     *
     */

    static public Company get1st ( JDBC jdbc )
    {
    CompanySession companies = new CompanySession();

    companies.find ( jdbc, -1 );

    return ( Company ) companies.get1st();
    }

    /**
     *
     */

    static public Company getDefault ( JDBC jdbc )
    {
    CompanySession companies = new CompanySession();

    companies.query ( jdbc, "select * from jet_base_company where company_default = 1" );

    Company company = companies.size() == 0 ? get1st ( jdbc ) : ( Company ) companies.get1st();

    return company;
    }
    /**
     *
     */

    static public void setDefault ( JDBC jdbc, HttpServletRequest request )

    { CompanySession.setSelected ( request, getDefault ( jdbc ) ); }

}
