package ca.jetsphere.burndown.tier1.backbone.transaction.writer;

import ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier2.table.TableWriter;

/**
 *
 */
public class TransactionImportWriter extends TableWriter
{
    /**
     *
     */
    public TransactionImportWriter ( TransactionSession transactions, String[] headers, String[] fields )

    { super ( transactions, headers, fields ); }

    /**
     *
     */
    protected void columns ( int row )

    { for ( int column = 1; column < getColumns(); column ++ ) column ( row, column ); }

    /**
     *
     */

    protected void headers()

    { write ( "<thead><tr>" ); for ( int column = 1; column < getColumns(); column ++ ) header ( column ); write ( "</tr></thead>" ); }

    /**
     * 
     */
    private boolean isDuplicate ( int row )
    
    { return DockYard.toBoolean ( getRowColumn ( row, 1 ).toString() ); }
    
    /**
     *
     */
    protected void row ( int row )

    { write ( "<tr" + ( isDuplicate ( row ) ? " class='duplicate'" : "" ) + ">" ); columns ( row ); write ( "</tr>" ); }
}
