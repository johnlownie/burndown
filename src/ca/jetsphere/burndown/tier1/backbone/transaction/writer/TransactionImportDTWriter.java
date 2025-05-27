package ca.jetsphere.burndown.tier1.backbone.transaction.writer;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.tier2.table.DataTableWriter;

/**
 *
 */
public class TransactionImportDTWriter extends DataTableWriter
{
    /**
     *
     */
    public TransactionImportDTWriter ( BoltMap bolts, String[] headers, String[] fields )

    { super ( bolts, headers, fields ); }
}
