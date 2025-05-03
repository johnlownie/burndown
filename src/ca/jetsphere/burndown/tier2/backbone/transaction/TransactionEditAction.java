package ca.jetsphere.burndown.tier2.backbone.transaction;

import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.burndown.tier1.backbone.transaction.Transaction;

/**
 *
 */
public class TransactionEditAction extends AbstractEditAction
{
    /**
     *
     */
    public String getKey() { return Transaction.key(); }
}