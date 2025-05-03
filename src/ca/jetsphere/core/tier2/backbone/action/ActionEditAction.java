package ca.jetsphere.core.tier2.backbone.action;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.action.Action;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class ActionEditAction extends AbstractEditAction
{
    /**
     *
     */

    public String getKey() { return Action.key(); }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors )
    {
    Action action = ( Action ) bolt;

    Company company = CompanySession.getSelected ( request );

    action.setCompanyId ( company.getId() );
    }

}
