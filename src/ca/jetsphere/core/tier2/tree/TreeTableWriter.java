package ca.jetsphere.core.tier2.tree;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier2.table.TableModel;
import ca.jetsphere.core.tier1.tree.Tree;
import ca.jetsphere.core.tier1.tree.TreeMap;
import ca.jetsphere.core.tier1.tree.TreeSession;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.io.Writer;

/**
 *
 */

public class TreeTableWriter implements Serializable
{
    TableModel table_model_model   ;
    String     table_model_caption ;

    Writer writer; HttpServletRequest request; boolean headers_only;

    /**
     *
     */

    public TreeTableWriter ( TableModel table_model_model ) { setModel ( table_model_model ); setCaption ( "" ); }

    /**
     *
     */

    public TreeTableWriter ( TreeSession trees, String[] headers, String[] fields )

    { this ( new TableModel ( trees, headers, fields ) ); }

    /**
     *
     */

    protected void branch ( Tree tree )
    {
    leaf ( tree );

    for ( int i = 0; i < tree.getChildCount(); i++ )

    { Tree child = tree.getChild ( i ); if ( child.isLeaf() ) leaf ( child ); else branch ( child ); }

    }

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

    protected void column ( Tree tree, int column )
    {
    Object o = getModel().getObject ( tree, getModel().getField ( column ) ); String s = "";

    if ( o == null ) { write ( "<td></td>" ); return; }

    s = o.toString();

    write ( "<td>" ); write ( DockYard.isWhiteSpace ( s ) ? "&nbsp;" : s ); write ( "</td>" );
    }

    /**
     *
     */

    protected void columns ( Tree tree )

    { for ( int column = 0; column < getColumns(); column ++ ) column ( tree, column ); }

    /**
     *
     */

    protected void emptySet()

    { write ( "<tr><td colspan=\"20\">" + Caption.get ( request, "error.report.no.items" ) + "</td></tr>" ); }

    /**
     *
     */

    protected void footer() {}

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

    protected void leaf ( Tree tree )

    { write ( "<tr data-tt-id=\"" + tree.getId() + "\"" + ( tree.getParentId() > 0 ? " data-tt-parent-id=\"" + tree.getParentId() + "\"" : "" ) + ">" ); columns ( tree ); write ( "</tr>" ); }

    /**
     *
     */

    public void print ( Writer writer, HttpServletRequest request ) { print ( writer, request, false ); }

    /**
     *
     */

    public void print ( Writer writer, HttpServletRequest request, boolean headers_only )
    {
    this.writer = writer; this.request = request; this.headers_only = headers_only;

    tree ( ( ( TreeMap ) getModel().getBolts() ).getRoot(), "table" );
    }

    /**
     *
     */

    protected void rows ( Tree tree )
    {
    write ( "<tbody>" );

    if ( tree == null ) emptySet(); else if ( tree.isLeaf() ) leaf ( tree ); else branch ( tree );

    write ( "</tbody>" );
    }

    /**
     *
     */

    protected void setCaption() {}

    /**
     *
     */

    protected void tree ( Tree tree, String table_id )
    {
    setCaption();

    write ( "<div class=\"table-responsive\">" );

    write ( "<table id=\"" + table_id + "\" class=\"table table-striped table-hover nowrap\">" );

    captions(); headers();

    if ( !headers_only ) { rows ( tree ); }

    footer();

    write ( "</table></div>" );
    }

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
