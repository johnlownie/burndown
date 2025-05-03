package ca.jetsphere.core.tier2.table;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.bolt.BoltComparator;
import ca.jetsphere.core.bolt.BoltMap;

import java.util.List;
import java.util.Locale;

/**
 *
 */

public class TableModel
{
    BoltMap  table_model_bolts   ;
    String[] table_model_headers ;
    String[] table_model_fields  ;
    List     table_model_list    ;

    /**
     *
     */

    public TableModel ( BoltMap table_model_bolts, String[] table_model_headers, String[] table_model_fields )

    { setBolts ( table_model_bolts ); setHeaders( table_model_headers ); setFields ( table_model_fields ); setList ( getList ( false ) ); }

    /**
     *
     */

    public String getCaption ( int column ) { return hasColumn ( column ) ? getHeaders() [ column ] : ""; }

    /**
     *
     */

    protected String getColumn ( int column ) { return hasColumn ( column ) ? getFields () [ column ] : null; }

    /**
     *
     */

    public int getColumns() { return getFields() != null ? getFields().length : 0; }

    /**
     *
     */

    public BoltComparator getComparator() { return getBolts().getComparator(); }

    /**
     *
     */

    public String getField ( int column ) { return hasColumn ( column ) ? getFields() [ column ] : ""; }

    /**
     *
     */

    protected List getList ( boolean sort ) { return getBolts().list ( sort ); }

    /**
     *
     */

    public Object getObject ( Bolt bolt, String s ) { return bolt != null ? bolt.get ( s ) : null; }

    /**
     *
     */

    public Bolt getRow ( int row ) { return hasRow ( row ) ? ( Bolt ) getList().get ( row ) : null; }

    /**
     *
     */

    public Object getRowColumn ( int row, int column, Locale locale )

    { Bolt bolt = getRow ( row ); if ( bolt != null ) bolt.setLocale ( locale ); return getObject ( bolt, getField ( column ) ); }

    /**
     *
     */

    public int getRows() { return getList() != null ? getList().size() : 0;  }

    /**
     *
     */

    public boolean hasColumn ( int column ) { return column >= 0 && column < getColumns(); }

    /**
     *
     */

    protected boolean hasRow ( int row ) { return row >= 0 && row < getRows(); }

    /**
     *
     */

    public BoltMap  getBolts   () { return table_model_bolts   ; }
    public String[] getHeaders () { return table_model_headers ; }
    public String[] getFields  () { return table_model_fields  ; }
    public List     getList    () { return table_model_list    ; }

    /**
     *
     */

    public void setBolts   ( BoltMap  table_model_bolts   ) { this.table_model_bolts   = table_model_bolts   ; }
    public void setHeaders ( String[] table_model_headers ) { this.table_model_headers = table_model_headers ; }
    public void setFields  ( String[] table_model_fields  ) { this.table_model_fields  = table_model_fields  ; }
    public void setList    ( List     table_model_list    ) { this.table_model_list    = table_model_list    ; }

}
