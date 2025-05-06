package ca.jetsphere.core.tier2.tree;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.tier1.tree.OpenSet;
import ca.jetsphere.core.tier1.tree.Tree;
import ca.jetsphere.core.tier1.tree.TreeMap;
import ca.jetsphere.core.tier1.tree.TreeSession;
import ca.jetsphere.core.tier2.table.TableModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.Writer;

/**
 *
 */

public class DataTableTreeWriter
{
    final public int ARRAY_DATA     = 0x01;
    final public int OBJECT_DATA    = 0x02;
    final public int OBJECT_COLUMNS = 0x03;

    protected Writer writer; protected HttpServletRequest request;

    TableModel datatable_table_model  ;
    JSONObject datatable_json_object  ;
    int        datatable_draw         ;
    int        datatable_start        ;
    int        datatable_length       ;


    /**
     *
     */

    public DataTableTreeWriter ( TableModel datatable_table_model )

    { this.datatable_table_model = datatable_table_model; }

    /**
     *
     */

    public DataTableTreeWriter ( TreeSession trees, String[] headers, String[] fields )

    { this ( new TableModel ( trees, headers, fields ) ); this.datatable_json_object = new JSONObject (); }

    /**
     *
     */

    protected void branch ( JSONArray array, OpenSet openSet, Tree tree )
    {
    leaf ( array, openSet, tree );

    if ( !openSet.contains ( tree.getId() ) ) return;

    for ( int i = 0; i < tree.getChildCount(); i++ )
    {
    Tree child = tree.getChild ( i );

    if ( child.isLeaf() ) leaf ( array, openSet, child ); else branch ( array, openSet, child );
    }

    }

    /**
     *
     */

    protected JSONArray getData ( OpenSet openSet, Tree tree )
    {
    JSONArray jsonArray = new JSONArray();

    if ( tree == null ) return jsonArray;

    if ( tree.isLeaf() ) leaf ( jsonArray, openSet, tree ); else branch ( jsonArray, openSet, tree );

    return jsonArray;
    }

    /**
     *
     */

    protected void leaf ( JSONArray array, OpenSet openSet, Tree tree )
    {
    JSONObject jsonObject = new JSONObject();

    jsonObject.put ( "DT_RowClass", "node node-" + ( openSet.contains ( tree.getId() ) ? "expanded" : "collapsed" )  + " node-draggable ui-draggable ui-droppable" );

    for ( int column = 0; column < getTableModel().getColumns (); column ++ )
    {
    Object o = getTableModel().getObject ( tree, getTableModel().getField ( column ) );

    String s = o != null ? o.toString() : "";

    jsonObject.put ( getTableModel().getField ( column ), s );
    }

    array.add ( jsonObject );
    }

    /**
     *
     */

    public JSONObject getJsonObject() { return this.datatable_json_object ; }
    public TableModel getTableModel() { return this.datatable_table_model ; }
    public int        getDraw      () { return this.datatable_draw        ; }
    public int        getStart     () { return this.datatable_start       ; }
    public int        getLength    () { return this.datatable_length      ; }

    /**
     *
     */

    public void print ( Writer writer, HttpServletRequest request, OpenSet openSet )
    {
    try {

        this.writer = writer; this.request = request; this.datatable_length = getTableModel().getRows();

        JSONArray jsonArray =  getData ( openSet, ( ( TreeMap ) getTableModel().getBolts() ).getRoot() );

        getJsonObject().put ( "data", jsonArray );

        getJsonObject().write ( writer );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    }

    /**
     *
     */

    public void print ( Writer writer, HttpServletRequest request, int dt_draw, int dt_count, int dt_filtered, int dt_start, int dt_length )
    {
    try {

        this.writer = writer; this.request = request; this.datatable_draw = dt_draw; this.datatable_start = dt_start; this.datatable_length = dt_length;

        getJsonObject().put ( "draw", dt_draw );
        getJsonObject().put ( "recordsTotal", dt_count );
        getJsonObject().put ( "recordsFiltered", dt_filtered );
//        getJsonObject().put ( "data", getData ( ARRAY_DATA ) );

        getJsonObject().write ( writer );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    }

}
