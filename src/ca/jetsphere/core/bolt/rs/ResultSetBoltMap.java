package ca.jetsphere.core.bolt.rs;

import ca.jetsphere.core.bolt.BoltComparator;
import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.bolt.BoltYard;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */

public class ResultSetBoltMap extends BoltMap
{
    SQLException exception; String [] captions;

    /**
     *
     */

    public ResultSetBoltMap() { super(); }

    /**
     *
     */

    public ResultSetBoltMap ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    public ResultSetBoltMap ( JDBC jdbc, String query )

    { this(); if ( ! DockYard.isWhiteSpace ( query ) ) query ( jdbc, query ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception

    { add ( new ResultSetBolt ( size(), getCaptions(), rs ) ); }

    /**
     *
     */

    public void get ( JDBC jdbc, ResultSet rs ) throws Exception
    {
    setCaptions ( BoltYard.getCaptions ( rs ) );

    while ( rs.next() ) add ( jdbc, rs );
    }

    /**
     *
     */

    public String getCaption ( int cc ) { return captions != null && captions.length > cc ? captions [ cc ] : ""; }

    /**
     *
     */

    public String [] getCaptions() { return captions; }

    /**
     *
     */

    static public int getColumns ( ResultSetBoltMap rsbl )

    { return rsbl.getFields() != null ? rsbl.getFields().length : 0; }

    /**
     *
     */

    public BoltComparator getComparator() { return new ResultSetComparator(); }

    /**
     *
     */

    public SQLException getException() { return exception; }

    /**
     *
     */

    static public String getField ( ResultSetBoltMap rsbl, int column )

    { return column >= 0 && column < getColumns ( rsbl ) ? rsbl.getFields() [ column ] : ""; }

    /**
     *
     */

    public String [] getFields() { return captions; }

    /**
     *
     */

    public String getKey() { return ResultSetBolt.key(); }

    /**
     *
     */

    public boolean hasExceptioned() { return exception != null; }

    /**
     *
     */

    public void query ( JDBC jdbc, String query )
    {
    Statement statement = null; ResultSet rs = null;

    try {

        clear();

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        get ( jdbc, rs );

    } catch ( SQLException e ) { setException ( e ); Common.trace ( this, e ); } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }

    setCaptions();
    }

    /**
     *
     */

    public void queryNoClear ( JDBC jdbc, String query )
    {
    Statement statement = null; ResultSet rs = null;

    try {

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        get ( jdbc, rs );

    } catch ( SQLException e ) { setException ( e ); Common.trace ( this, e ); } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }

    setCaptions();
    }

    /**
     *
     */

    public void setCaption ( int cc, String s )
    {
    if ( captions.length < cc || s == null ) return;

    captions [ cc ] = s;
    }

    /**
     *
     */

    protected void setCaptions()
    {
    if ( captions == null ) return;

    String s [] = new String [ captions.length ];

    for ( int cc = 0; cc < captions.length; cc ++ ) s [ cc ] = captions [ cc ];

    this.captions = s;
    }

    /**
     *
     */

    public void setCaptions ( String [] captions ) { this.captions = captions; }

    /**
     *
     */

    public void setException ( SQLException exception ) { this.exception = exception; }

}
