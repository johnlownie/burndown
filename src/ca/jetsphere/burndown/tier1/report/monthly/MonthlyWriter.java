package ca.jetsphere.burndown.tier1.report.monthly;

import ca.jetsphere.burndown.tier2.backbone.common.QueryActionForm;
import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier2.table.TableWriter;

/**
 *
 */
public class MonthlyWriter extends TableWriter
{
    String report_type; int column_total[];

    /**
     *
     */
    public MonthlyWriter ( BoltMap bolts, String[] captions, String[] fields, String report_type )

    { super ( bolts, captions, fields ); setType ( report_type ); setTotal ( new int [ captions.length ] ); }

    /**
     *
     */
    protected void column ( int row, int column )
    {
    Object o = getRowColumn ( row, column );

    int value = DockYard.toInt ( o ); if ( column > 0 ) column_total [ column ] += value;

    if ( column > 0 ) format ( row, column ); else formatCategory ( row, column );
    }

    /**
     *
     */
    protected void footer()

    { write ( "<tfoot class=\"bg-gray text-bold\">" ); write ( getTotals() ); write ( "</tfoot>" ); }

    /**
     *
     */
    public void format ( int row, int column )
    {
    int c = DockYard.toInt ( getRowColumn ( row, column ) );

    write ( "<td>" ); write ( DockYard.toMoney ( DockYard.toInt ( c ) ) ); write ( "</td>" );
    }

    /**
     *
     */
    public void formatCategory ( int row, int column )
    {
    String category = ( String ) getRowColumn ( row, column );
    
    int depth = DockYard.toInt ( DockYard.getToken ( category, 1 ) );
    
    String name = DockYard.getToken ( category, 2 );
    
    for ( int i = 0; i < depth; i++ )
        
    { name = "--- " + name; }

    write ( "<td>" ); write ( name ); write ( "</td>" );
    }

    /**
     *
     */
    protected String getTotals()
    {
    StringBuilder sb = new StringBuilder();

    sb.append ( "<tr>" );

    sb.append ( "<td>" + Caption.get ( request, "totals" ) + "</td>" );

    for ( int column = 1; column < getColumns (); column ++ )

    { sb.append ( "<td>" ); sb.append ( getTotals ( column ) ); sb.append ( "</td>" ); }

    sb.append( "</tr>" );

    return sb.toString();
    }

    /**
     *
     */
    protected String getTotals ( int column )

    { return DockYard.toMoney ( column_total [ column ] ); }

    /**
     *
     */
    protected void setCaption()
    {
    QueryActionForm qaf = ( QueryActionForm ) QueryActionForm.getActionForm ( request, QueryActionForm.key (), false );

    String report_title  = Caption.get ( request, "report.caption.type", new String[] { Caption.get ( request, report_type ) } );

    String key = DockYard.compareTo ( qaf.getStartDateAsString(), qaf.getEndDateAsString () ) == 0 ? "report.caption.date" : "report.caption.date.range";

    String report_date = Caption.get ( request, key, new String[] { qaf.getStartDateAsString(), qaf.getEndDateAsString () } );

    StringBuilder sb = new StringBuilder();

    sb.append ( "<div id=\"caption-wrapper\">" );

    sb.append ( "<div>" + report_title  + "</div>" );

    sb.append ( "<div>" + report_date   + "</div>" );

    sb.append ( "</div>" );

    setCaption ( sb.toString() );
    }

    /**
     *
     */
    public void setTotal ( int[]  column_total ) { this.column_total = column_total ; }
    public void setType  ( String report_type  ) { this.report_type  = report_type  ; }
}
