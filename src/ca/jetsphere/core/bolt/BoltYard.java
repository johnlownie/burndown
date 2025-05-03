package ca.jetsphere.core.bolt;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;

/**
 *
 */

public class BoltYard
{
    /**
     *
     */

    static public void batchSave ( JDBC jdbc, BoltMap bolts, AbstractDao dao, int batchSize ) throws Exception
    {
    int count = 0;

    if ( bolts.isEmpty() ) return;

    PreparedStatement ps = jdbc.prepareStatement();

    try {

        Iterator it = bolts.iterator();

        while ( it.hasNext() )

        { Bolt bolt = ( Bolt ) it.next(); dao.update ( jdbc, bolt, ps, true ); ps.addBatch(); if ( ++ count % batchSize == 0 ) ps.executeBatch(); }

        ps.executeBatch();

    } catch ( Exception e ) { throw e; }

    finally { if ( ps != null ) ps.close(); }
    }

    /**
     *
     */

    static public String fieldQuery ( Bolt bolt, int id, String field, String value )

    { return "select * from jet_base_" + bolt.getKey() + " where " + bolt.getKey() + "_" + BoltYard.getContainerKey ( bolt ) + " = " + id + " and " + bolt.getKey() + "_" + field + " = " + DockYard.quote ( value ); }

    /**
     *
     */

    static public String idQuery ( Bolt bolt, int id ) { return "select * from jet_base_" + bolt.getKey() + " where " + bolt.getKey() + "_id = " + id; }

    /**
     *
     */

    static public Object getObject ( ResultSet aResultSet, int column )
    {
    try {

        switch ( aResultSet.getMetaData().getColumnType ( column ) )
        {
        case Types.CHAR          : return aResultSet.getString     ( column );
        case Types.VARCHAR       : return aResultSet.getString     ( column );
        case Types.LONGVARCHAR   : return aResultSet.getString     ( column );
        case Types.VARBINARY     : return aResultSet.getString     ( column );
        case Types.NUMERIC       : return aResultSet.getBigDecimal ( column );
        case Types.DECIMAL       : return aResultSet.getBigDecimal ( column );

        case Types.BIT           : return new Boolean ( aResultSet.getBoolean ( column ) );
        case Types.TINYINT       : return new Integer ( aResultSet.getInt     ( column ) );
        case Types.SMALLINT      : return new Integer ( aResultSet.getInt     ( column ) );
        case Types.INTEGER       : return new Integer ( aResultSet.getInt     ( column ) );
        case Types.BIGINT        : return new Long    ( aResultSet.getLong    ( column ) );
        case Types.REAL          : return new Float   ( aResultSet.getFloat   ( column ) );
        case Types.FLOAT         : return new Double  ( aResultSet.getDouble  ( column ) );
        case Types.DOUBLE        : return new Double  ( aResultSet.getDouble  ( column ) );
        case Types.BINARY        : return new Byte    ( aResultSet.getByte    ( column ) );
        case Types.LONGVARBINARY : return new Byte    ( aResultSet.getByte    ( column ) );

        case Types.DATE          : return aResultSet.getDate      ( column );
        case Types.TIME          : return aResultSet.getTime      ( column );
        case Types.TIMESTAMP     : return aResultSet.getTimestamp ( column );

        default                  : return aResultSet.getString    ( column );
        }

    } catch ( SQLException e ) { trace ( aResultSet, column ); Common.trace ( e ); return new String(); }

    }

    /**
     *
     */

    static private int contains ( String [] sa, String s )
    {
    int count = 0;

    if ( sa != null )

        for ( int cc = 0; cc < sa.length; cc ++ ) if ( sa [ cc ] .equals ( s ) ) count ++;

    return count;
    }

    /**
     *
     */

    static public String [] getCaptions ( ResultSet rs )

    { try { return getCaptions ( rs.getMetaData() ); } catch ( Exception e ) {} return null; }

    /**
     *
     */

    static public String [] getCaptions ( ResultSetMetaData rsmd )
    {
    try {

        String [] captions = new String [ rsmd.getColumnCount() ];

        for ( int cc = 0; cc < rsmd.getColumnCount(); cc ++ ) captions [ cc ] = rsmd.getColumnLabel ( cc + 1 );

        return rename ( captions );

    } catch  ( Exception e ) { Common.trace ( e ); }

    return null;
    }

    /**
     *
     */

    static public Class getContainer ( Bolt bolt )
    {
    try {

        return ( Class ) bolt.getClass().getMethod ( "container", null ).invoke ( null, new Class [] {} );

    } catch ( Exception e ) { Common.trace ( e ); }

    return null;
    }

    /**
     *
     */

    static public String getContainerKey ( Bolt bolt )
    {
    try {

        Class clazz = getContainer ( bolt ); String key = clazz.getName();

        int cc = key.lastIndexOf ( "." ); if ( cc < 0 ) cc = 0; else cc += 1; key = key.substring ( cc );

        StringBuilder sb = new StringBuilder ( key );

        for ( cc = 0; cc < sb.length(); cc ++ )
        {
        char c = sb.charAt ( cc );

        if ( ! Character.isUpperCase ( c ) ) continue; if ( cc > 0 ) sb.insert ( cc ++, "_" ); sb.setCharAt ( cc, Character.toLowerCase ( c ) );
        }

        sb.append ( "_id" );

        return sb.toString();

    } catch ( Exception e ) { Common.trace ( e ); }

    return "";
    }

    /**
     *
     */

    static public String [] rename ( String [] sa )
    {
    if ( sa != null )

        for ( int cc = sa.length - 1; cc >= 0; cc -- )

        { int count = contains ( sa, sa [ cc ] ); if ( count > 1 ) sa [ cc ] += " [" + cc + "]" ; }

    return sa;
    }


    /**
     *
     */

    static public void trace ( ResultSet aResultSet, int column )
    {
    try {

        ResultSetMetaData rsmd = aResultSet.getMetaData();

        Common.trace ( "[ RSMD COLUMN ] " + column );
        Common.trace ( "[ RSMD TYPE   ] " + rsmd.getColumnType   ( column ) );
        Common.trace ( "[ RSMD NAME   ] " + rsmd.getColumnName   ( column ) );
        Common.trace ( "[ RSMD VALUE  ] " + aResultSet.getObject ( column ).toString() );

    } catch ( Exception e ) { Common.trace ( e ); }

    }

}
