package ca.jetsphere.core.tier2.table;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;

import javax.servlet.http.HttpServletRequest;
import java.io.Writer;

/**
 *
 */

public class TableWriter
{
    TableModel table_model_model   ;
    String     table_model_caption ;

    protected Writer writer; protected HttpServletRequest request; boolean headers_only;

    /**
     *
     */

    public TableWriter ( TableModel table_model_model ) { setModel ( table_model_model ); setCaption ( "" ); }

    /**
     *
     */

    public TableWriter ( BoltMap bolts, String[] headers, String[] fields )

    { this ( new TableModel ( bolts, headers, fields ) ); }

    /**
     *
     */

    protected void captions()
    {
    if ( DockYard.isWhiteSpace ( getCaption() ) ) return;

    write ( "<caption>" + getCaption() + "</caption>" );
    }

    /**
     *
     */

    protected void column ( int row, int column )
    {
    Object o = getRowColumn ( row, column ); String s = "";

    if ( o == null ) { write ( "<td></td>" ); return; }

    s = o.toString();

    write ( "<td>" ); entry ( s ); write ( "</td>" );
    }

    /**
     *
     */

    protected void columns ( int row )

    { for ( int column = 0; column < getColumns(); column ++ ) column ( row, column ); }

    /**
     *
     */

    protected void emptySet()

    { write ( "<tr><td colspan=\"20\">" + Caption.get ( request, "error.report.no.items" ) + "</td></tr>" ); }

    /**
     *
     */

    protected void entry ( String s )

    { write ( DockYard.isWhiteSpace ( s ) ? "&nbsp;" : s ); }

    /**
     *
     */

    protected void footer()

    { write ( "<tfoot><tr>" ); for ( int column = 0; column < getColumns (); column ++ ) write ( "<td></td>" ); write ( "</tr></tfoot>" ); }

    /**
     *
     */

    protected String getCaption ( HttpServletRequest request, int column )

    { return Caption.get ( request, getModel().getCaption ( column ) ); }

    /**
     *
     */

    protected int getColumns() { return getModel().getColumns(); }


    /**
     *
     */

    protected Object getRowColumn ( int row, int column )

    { return getModel().getRowColumn ( row, column, request.getLocale() ); }

    /**
     *
     */

    protected String getStyleClass() { return " class=\"table\""; }

    /**
     *
     */

    protected void header ( int column )

    { write ( "<th>" ); write ( getCaption ( request, column ) ); write ( "</th>" ); }

    /**
     *
     */

    protected void headers()

    { write ( "<thead><tr>" ); for ( int column = 0; column < getColumns(); column ++ ) header ( column ); write ( "</tr></thead>" ); }

    /**
     *
     */

    public void print ( Writer writer, HttpServletRequest request )

    { print ( writer, request, "table" ); }

    /**
     *
     */

    public void print ( Writer writer, HttpServletRequest request, boolean headers_only )

    { print ( writer, request, "table", headers_only ); }

    /**
     *
     */

    public void print ( Writer writer, HttpServletRequest request, String table_id )

    { print ( writer, request, table_id, false ); }

    /**
     *
     */

    public void print ( Writer writer, HttpServletRequest request, String table_id, boolean headers_only )
    {
    this.writer = writer; this.request = request; this.headers_only = headers_only;

    try {

        table ( table_id );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    }

    /**
     *
     */

    protected void row ( int row )

    { write ( "<tr>" ); columns ( row ); write ( "</tr>" ); }

    /**
     *
     */

    protected void rows()

    { write ( "<tbody>" ); if ( ( getModel().getHeaders() == null || getModel().getHeaders().length == 0 ) && getModel().getRows() == 0 ) emptySet(); for ( int row = 0; row < getModel().getRows(); row ++ ) row ( row ); write ( "</tbody>" ); }

    /**
     *
     */

    protected void setCaption() {}

    /**
     *
     */

    public void table ( String table_id )

    { setCaption(); write ( "<div class=\"table-responsive\"><table id=\"" + table_id + "\" class=\"table table-striped table-hover nowrap\" cellspacing=\"0\">" ); captions(); headers(); if ( !headers_only ) rows(); footer(); write ( "</table></div>" ); }

    /**
     *
     */

    protected void write ( String s ) { try { if ( writer != null ) writer.write ( s ); } catch ( Exception e ) { Common.trace ( this, e ); } }

    /**
     *
     */

    public TableModel getModel  () { return table_model_model   ; }
    public String     getCaption() { return table_model_caption ; }

    /**
     *
     */

    public void setModel   ( TableModel table_model_model   ) { this.table_model_model   = table_model_model   ; }
    public void setCaption ( String     table_model_caption ) { this.table_model_caption = table_model_caption ; }

}
