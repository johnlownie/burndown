package ca.jetsphere.core.bolt;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.Pair;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.jdbc.Statement;
import ca.jetsphere.core.tier0.Registry;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

/**
 *
 */

abstract public class Bolt extends MetaBolt implements Comparable
{
    /**
     *
     */

    public Bolt() { super(); clear(); }

    /**
     *
     */

    public Bolt ( JDBC jdbc, String query ) { this (); query ( jdbc, query ); }

    /**
     *
     */

    public void clear() {}

    /**
     *
     */

    public int compareTo ( Object o )
    {
    Bolt bolt = ( Bolt ) o;

    return DockYard.compareTo ( getName(), bolt.getName() );
    }

    /**
     *
     */

    public void copy ( Bolt bolt ) {}

    /**
     *
     */

    public void copySave ( JDBC jdbc ) throws Exception { copySave ( jdbc, true ); }

    /**
     *
     */

    public void copySave ( JDBC jdbc, boolean copyUuid ) throws Exception

    { setId ( -1 ); String uuid = getUuid(); save ( jdbc, true ); if ( ! copyUuid ) return; setUuid ( uuid ); save ( jdbc, false ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception {}

    /**
     *
     */

    public void foreign ( JDBC jdbc ) throws Exception {}

    /**
     *
     */

    public void get ( JDBC jdbc, ResultSet rs ) throws Exception

    { get ( rs ); foreign ( jdbc ); }

    /**
     *
     */

    abstract public void get ( ResultSet rs ) throws Exception;

    /**
     *
     */

    static public Bolt getBolt ( Class clazz, int id )
    {
    Object x = null;

    try {

        Constructor c = clazz.getDeclaredConstructor ( new Class[] { int.class } ); x = c.newInstance ( new Object[] { new Integer ( id ) } );

    } catch ( Exception e ) {}

    return ( Bolt ) x;
    }

    /**
     *
     */

    public Timestamp getEvent() { return null; }

    /**
     *
     */

    abstract public int getId();

    /**
     *
     */

    public String getName() { return "???"; }

    /**
     *
     */

    public String getName ( HttpServletRequest request ) { return getName(); }

    /**
     *
     */

    public void getPayload ( JDBC jdbc ) {}

    /**
     *
     */

    public void getPayload ( JDBC jdbc, int id1, int id2 ) {}

    /**
     *
     */

    public String getUuid() { return ""; }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception {}

    /**
     *
     */

    public boolean isValid() { return getId() > -1; }

    /**
     *
     */

    static public boolean isValid ( Bolt bolt ) { return bolt != null ? bolt.isValid() : false; }

    /**
     *
     */

    static public Bolt name ( JDBC jdbc, Bolt bolt, int id, String name )
    {
    try {

        Registry.getAbstractDao ( bolt.getKey() ).name ( jdbc, bolt, id, name );

    } catch ( Exception e ) { Common.trace ( e ); }

    return bolt;

    }

    /**
     *
     */

    public void query ( JDBC jdbc, String query )
    {
    setId ( -1 );

    Statement statement = null; ResultSet rs = null;

    try {

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        if ( rs.next() ) get ( jdbc, rs );

    } catch ( Exception e ) { Common.trace ( query ); Common.trace ( this, e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    public void query ( JDBC jdbc, String query, List parameters )
    {
    setId ( -1 );

    PreparedStatement ps = null; ResultSet rs = null; int cc = 1;

    try {

        ps = jdbc.prepareStatement ( query );

        Iterator it = parameters.iterator();

        while ( it.hasNext() )
        {
            Pair pair = ( Pair ) it.next();

            if ( "date"  .equalsIgnoreCase ( ( String ) pair.getKey() ) ) ps.setDate   ( cc, (Date) pair.getValue() );

            if ( "int"   .equalsIgnoreCase ( ( String ) pair.getKey() ) ) ps.setInt    ( cc, ( Integer ) pair.getValue() );

            if ( "string".equalsIgnoreCase ( ( String ) pair.getKey() ) ) ps.setString ( cc, ( String  ) pair.getValue() );

            cc++;
        }

        rs = ps.executeQuery();

        if ( rs.next() ) get ( jdbc, rs );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( ps != null ) ps.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    public void save ( JDBC jdbc ) throws Exception { save ( jdbc, true ); }

    /**
     *
     */

    public void save ( JDBC jdbc, boolean ts ) throws Exception  { if ( getId() >= 0 ) update ( jdbc ); else insert ( jdbc ); }

    /**
     *
     */

    public void setId ( int id ) {}

    /**
     *
     */

    public void setUuid ( String uuid ) {}

    /**
     *
     */

    static public Bolt uuid ( JDBC jdbc, Bolt bolt, int id, String uuid )
    {
    try {

        Registry.getAbstractDao ( bolt.getKey() ).uuid ( jdbc, bolt, id, uuid );

    } catch ( Exception e ) { Common.trace ( e ); }

    return bolt;

    }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return true; }

    /**
     *
     */

    public boolean validate() { return true; }

    /**
     *
     */

    public Errors validate ( JDBC jdbc, HttpServletRequest request, Errors errors )

    { return errors; }

}
