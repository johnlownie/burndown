package ca.jetsphere.core.tier2.table;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.Writer;

/**
 *
 */

public class DataTableWriter
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

    public DataTableWriter ( TableModel datatable_table_model )

    { this.datatable_table_model = datatable_table_model; }

    /**
     *
     */

    public DataTableWriter ( BoltMap bolts, String[] headers, String[] fields )

    { this ( new TableModel ( bolts, headers, fields ) ); this.datatable_json_object = new JSONObject (); }

    /**
     *
     */

    protected JSONArray getData ( int type )
    {
    JSONArray jsonArray = new JSONArray();

    for ( int row = 0; row < getLength(); row ++ )
    {
    switch ( type )
    {
    case OBJECT_DATA : getObjectData ( jsonArray, row ); break;

    case ARRAY_DATA  :

    default          : getArrayData  ( jsonArray, row );
    } }

    return jsonArray;
    }

    /**
     *
     */

    protected void getArrayData ( JSONArray array, int row )
    {
    JSONArray jsonArray = new JSONArray();

    for ( int column = 0; column < getTableModel().getColumns (); column ++ )
    {
    Object o = getRowColumn ( row, column );

    String s = o != null ? o.toString() : "";

    jsonArray.add ( s );
    }

    array.add ( jsonArray );
    }

    /**
     *
     */

    protected void getObjectData ( JSONArray array, int row )
    {
    JSONObject jsonObject = new JSONObject();

    for ( int column = 0; column < getTableModel().getColumns (); column ++ )
    {
    String caption = getTableModel().getCaption ( column );

    String title = DockYard.getToken ( caption, 1, "|" ).replaceAll ( "\\.", "_" );

    Object o = getRowColumn ( row, column );

    String s = o != null ? o.toString() : "";

    jsonObject.put ( title, s );
    }

    array.add ( jsonObject );
    }

    /**
     *
     */

    protected String getClass ( String caption )
    {
    for ( int cc=1; cc <= DockYard.getTokenCount ( caption, "|" ); cc++ )
    {
    String token = DockYard.getToken ( caption, cc, "|" );

    if ( ! token.startsWith ( "class" ) ) continue;

    return DockYard.getToken ( token, 2 );
    }

    return "";
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

    protected Object getRowColumn ( int row, int column )

    { return getTableModel().getRowColumn ( row, column, request.getLocale () ); }

    /**
     *
     */

    public void print ( Writer writer, HttpServletRequest request )
    {
    try {

        this.writer = writer; this.request = request; this.datatable_length = getTableModel().getRows();

        getJsonObject().put ( "data", getData ( ARRAY_DATA ) );

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
        getJsonObject().put ( "data", getData ( ARRAY_DATA ) );

        getJsonObject().write ( writer );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    }

}
