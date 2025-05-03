package ca.jetsphere.core.tier2.backbone.company;

import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier2.common.AbstractEditAction;

/**
 *
 */

public class CompanyEditAction extends AbstractEditAction
{
    /**
     *
     */

    public String getKey() { return Company.key(); }

}
