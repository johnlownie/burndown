package ca.jetsphere.burndown.tier2.backbone.category;

import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.burndown.tier1.backbone.category.Category;
import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier2.common.Errors;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public class CategoryEditAction extends AbstractEditAction
{
    /**
     *
     */
    public String getKey() { return Category.key(); }

    /**
     *
     */
    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors )
    {
    Category category = ( Category ) bolt;

    Company company = CompanySession.getSelected ( request );

    category.setCompanyId ( company.getId() );
    }
}