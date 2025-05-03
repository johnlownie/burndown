package ca.jetsphere.core.tier2.backbone.brand;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.brand.Brand;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class BrandEditAction extends AbstractEditAction
{
    /**
     *
     */

    public String getKey() { return Brand.key (); }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors )
    {
    Brand brand = ( Brand ) bolt;

    Company company = CompanySession.getSelected ( request );

    brand.setCompanyId ( company.getId() );
    }

}