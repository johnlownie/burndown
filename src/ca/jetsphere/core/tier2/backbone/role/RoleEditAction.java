package ca.jetsphere.core.tier2.backbone.role;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.role.Role;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class RoleEditAction extends AbstractEditAction
{
    /**
     *
     */

    public String getKey() { return Role.key(); }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception
    {
    Role role = ( Role ) bolt;

    Company company = CompanySession.getSelected ( request );

    role.setCompanyId ( company.getId() );
    }

}
