package ca.jetsphere.core.spreadsheet;

import ca.jetsphere.core.common.DockYard;
import jxl.Cell;

import java.util.ArrayList;

/**
 *
 */

public class Sheet
{
    jxl.Sheet sheet;

    /**
     *
     */

    public Sheet() { super(); clear(); }

    /**
     *
     */

    public Sheet ( jxl.Sheet sheet ) { super(); setSheet (  sheet ); }

    /**
     *
     */

    public void clear() { setSheet ( null ); }

    /**
     *
     */

    public Cell getCell ( int column, int row ) { return sheet.getCell ( column, row ); }

    /**
     *
     */

    public int getColumns() { return sheet != null ? sheet.getColumns() : 0; }

    /**
     *
     */

    public Cell[] getRow ( int row ) { return sheet.getRow ( row ); }
    /**
     *
     */

    public String[] getRow ( Cell[] row )
    {
    String[] array = new String [ row.length ];

    for ( int cc = 0; cc < row.length; cc++ )

        array [ cc ] = row [ cc ].getContents();

    return array;
    }

    /**
     *
     */

    public int getRows() { return sheet != null ? sheet.getRows() : 0; }

    /**
     *
     */

    public jxl.Sheet getSheet() { return sheet; }

    /**
     *
     */

    public ArrayList getValues ( int row, int column )
    {
    ArrayList list = new ArrayList();

    for ( int cc = row + 1; cc < getRows(); cc ++ )
    {
    Cell cell = sheet.getCell ( column, row );

    if ( DockYard.isWhiteSpace ( cell.getContents() ) ) continue;

    int tokens = DockYard.getTokenCount ( cell.getContents(), "," );

    for ( int dd = 1; dd < tokens + 1; dd ++ )
    {
    String token = DockYard.getToken ( cell.getContents(), dd, "," ).trim();

    if ( ! list.contains ( token ) ) list.add ( token );

    } }

    return list;
    }

    /**
     *
     */

    public void setSheet ( jxl.Sheet sheet ) { this.sheet = sheet; }

}
