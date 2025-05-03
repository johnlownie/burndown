package ca.jetsphere.core.bolt.rs;

import ca.jetsphere.core.jdbc.JDBC;

import java.sql.ResultSet;
import java.util.Iterator;

/**
 *
 */

public class CsvResultSetBoltList extends ResultSetBoltList
{
    /**
     *
     */

    public CsvResultSetBoltList()

    { super(); }

    /**
     *
     */

    public CsvResultSetBoltList ( JDBC jdbc, ResultSet rs ) throws Exception

    { super ( jdbc, rs ); }

    /**
     *
     */

    public CsvResultSetBoltList ( ResultSetBoltMap rsbm ) throws Exception

    { super(); add ( rsbm ); }

    /**
     *
     */

    public CsvResultSetBoltList ( JDBC jdbc, String query ) throws Exception

    { super ( jdbc, query ); }

    /**
     *
     */

    public void add ( ResultSetBoltMap rsbm ) throws Exception
    {
    Iterator it = rsbm.iterator();

    while ( it.hasNext() )
    {
    ResultSetBolt bolt = ( ResultSetBolt ) it.next();

    add ( new CsvResultSetBolt ( bolt.getId(), bolt.getCaptions(), bolt.getMap() ) );
    }

    setCaptions ( rsbm.getCaptions() );
    }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception

    { add ( new CsvResultSetBolt ( size(), getCaptions(), rs ) ); }

}
