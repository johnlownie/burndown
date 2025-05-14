package ca.jetsphere.burndown.tier2.backbone.account;

import ca.jetsphere.burndown.tier1.backbone.account.Account;
import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public class AccountEditAction extends AbstractEditAction
{
    /**
     *
     */
    public String getKey() { return Account.key(); }

    /**
     *
     */
    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception
    {
    Account account = ( Account ) bolt; Company company = CompanySession.getSelected ( request );

    account.setCompanyId ( company.getId() );
    }
}