package ca.jetsphere.core.tier2.backbone.policy;

import ca.jetsphere.core.tier1.backbone.policy.Policy;
import ca.jetsphere.core.tier2.common.AbstractEditAction;

/**
 *
 */

public final class PolicyEditAction extends AbstractEditAction
{
    /**
     *
     */

    public String getKey() { return Policy.key(); }

}
