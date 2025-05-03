package ca.jetsphere.core.jdbc;

import ca.jetsphere.core.bolt.rs.ResultSetYard;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.Pair;
import net.sf.json.JSONArray;

import javax.naming.NamingException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */

public class QueryYard
{
    /**
     *
     */

    static public int delete ( JDBC jdbc, String query ) throws NamingException, SQLException

    { return update ( jdbc, query ); }

    /**
     *
     */

    static public List getIntList ( JDBC jdbc, String query, int field )
    {
    Statement statement = null; ResultSet rs = null; ArrayList list = new ArrayList();

    try {

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        while ( rs.next () ) list.add ( rs.getInt ( field ) );

        return list;

    } catch ( Exception e ) { Common.trace ( e ); return list; }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    static public List getIntList ( JDBC jdbc, String query, List parameters, int field )
    {
    PreparedStatement ps = null; ResultSet rs = null; ArrayList list = new ArrayList(); int cc = 1;

    try {

        ps = jdbc.prepareStatement ( query );

        Iterator it = parameters.iterator();

        while ( it.hasNext() )
        {
        Pair pair = ( Pair ) it.next();

        if ( "date"  .equalsIgnoreCase ( ( String ) pair.getKey() ) ) ps.setDate   ( cc, ( Date    ) pair.getValue() );

        if ( "int"   .equalsIgnoreCase ( ( String ) pair.getKey() ) ) ps.setInt    ( cc, ( Integer ) pair.getValue() );

        if ( "string".equalsIgnoreCase ( ( String ) pair.getKey() ) ) ps.setString ( cc, ( String  ) pair.getValue() );

        cc++;
        }

        rs = ps.executeQuery();

        while ( rs.next () ) list.add ( rs.getInt ( field ) );

        return list;

    } catch ( Exception e ) { Common.trace ( e ); return list; }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( ps != null ) ps.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    static public JSONArray getJson ( JDBC jdbc, String query )
    {
    Statement statement = null; ResultSet rs = null;

    try {

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        return ResultSetYard.get ( rs );

    } catch ( Exception e ) { Common.trace ( e ); return new JSONArray(); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    static public List getList ( JDBC jdbc, String query, int field )
    {
    Statement statement = null; ResultSet rs = null; ArrayList list = new ArrayList();

    try {

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        while ( rs.next () ) list.add ( rs.getString ( field ) );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }

    return list;
    }

    /**
     *
     */

    static public Map getMap ( String query )
    {
    JDBC jdbc = new JDBC();

    try {

        return getMap ( jdbc, query, 1, 2 );

    } catch ( Exception e ) { Common.trace ( e ); return new LinkedHashMap(); }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    static public Map<String, String> getMap ( JDBC jdbc, String query, int key, int value )
    {
    Statement statement = null; ResultSet rs = null; LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

    try {

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        while ( rs.next () ) map.put ( rs.getString ( key ), rs.getString ( value ) );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }

    return map;
    }

    /**
     *
     */

    static public List getStringList ( String query, int size )
    {
    JDBC jdbc = new JDBC();

    try {

        return getStringList ( jdbc, query, size );

    } catch ( Exception e ) { Common.trace ( e ); return null; }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    static public List getStringList ( JDBC jdbc, String query, int size )
    {
    Statement statement = null; ResultSet rs = null; List list = new ArrayList();

    try {

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        while ( rs.next () )
        {
        String[] s = new String [ size ];

        for ( int cc = 0; cc < size; cc++ ) s [ cc ] = rs.getString ( cc + 1 );

        list.add ( s );
        }

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }

    return list;
    }

    /**
     *
     */

    static public Map getStringMap ( JDBC jdbc, String query, int size )
    {
    Statement statement = null; ResultSet rs = null; HashMap map = new HashMap(); int index = 0;

    try {

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        while ( rs.next () )
        {
        String[] array = new String [ size ];

        for ( int cc = 0; cc < size; cc++ ) array [ cc ] = rs.getString ( cc + 1 );

        map.put ( index++, array );
        }

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }

    return map;
    }

    /**
     *
     */

    static public Map<Integer, String> getIntStringMap ( JDBC jdbc, String query, int key, int value )
    {
    Statement statement = null; ResultSet rs = null; LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();

    try {

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        while ( rs.next () ) map.put ( rs.getInt ( key ), rs.getString ( value ) );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }

    return map;
    }

    /**
     *
     */

    static public int query ( String query )
    {
    JDBC jdbc = new JDBC();

    try {

        return query ( jdbc, query );

    } catch ( Exception e ) { Common.trace ( e ); return 0; }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    
    static public int query ( JDBC jdbc, String query )
    {
    Statement statement = null; ResultSet rs = null;

    try {

        statement = jdbc.createStatement();

        rs = statement.executeQuery ( query );

        return rs.next() ? rs.getInt ( 1 ) : 0;

    } catch ( Exception e ) { Common.trace ( e ); return 0; }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    static public String query ( String query, int field )
    {
    JDBC jdbc = new JDBC();

    try {

        return query ( jdbc, query, field );

    } catch ( Exception e ) { Common.trace ( e ); return ""; }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    static public String query ( JDBC jdbc, String query, int field )
    {
    Statement statement = null; ResultSet rs = null;

    try {

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        if ( rs.next () ) return rs.getString ( field );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }

    return "";
    }

    /**
     *
     */

    static public double queryDbl ( String query )
    {
    JDBC jdbc = new JDBC();

    try {

        return queryDbl ( jdbc, query );

    } catch ( Exception e ) { Common.trace ( e ); return 0; }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    static public int[] counts ( JDBC jdbc, String query, int size )
    {
    Statement statement = null; ResultSet rs = null; int[] counts = new int [ size ];

    try {

        statement = jdbc.createStatement();

        rs = statement.executeQuery ( query );

        if ( rs.next() ) { for ( int cc = 0; cc < size; cc++ ) counts [ cc ] = rs.getInt ( cc + 1 ); }

        return counts;

    } catch ( Exception e ) { Common.trace ( e ); return counts; }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    static public double queryDbl ( JDBC jdbc, String query )
    {
    Statement statement = null; ResultSet rs = null;

    try {

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        if ( rs.next() ) return rs.getDouble ( 1 );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }

    return 0;
    }

    /**
     *
     */

    static public Timestamp queryTs ( JDBC jdbc, String query )
    {
    Statement statement = null; ResultSet rs = null;

    try {

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        if ( rs.next() ) return rs.getTimestamp ( 1 );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }

    return null;
    }

    /**
     *
     */

    static public int size ( JDBC jdbc, String query )
    {
    Statement statement = null; ResultSet rs = null;

    try {

        statement = jdbc.createStatement();

        rs = statement.executeQuery ( query );

	rs.last();

	return rs.getRow();

    } catch ( Exception e ) { Common.trace ( e ); return 0; }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    static public int update ( JDBC jdbc, String query ) throws NamingException, SQLException
    {
    Statement statement = null;

    try {

        statement = jdbc.createStatement(); return statement.executeUpdate ( query );

    } catch ( SQLException e ) { Common.trace ( e ); throw e; }

    finally { try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    static public void update ( JDBC jdbc, String query, boolean repeat ) throws Exception
    {
    Statement statement = null;

    try {

        statement = jdbc.createStatement();

        for (;;) { if ( statement.executeUpdate ( query ) == 0 ) break; if ( ! repeat ) break; }

    } catch ( Exception e ) { Common.trace ( e ); throw e; }

    finally { try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }
    }

}
