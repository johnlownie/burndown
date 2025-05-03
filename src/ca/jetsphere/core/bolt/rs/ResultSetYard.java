package ca.jetsphere.core.bolt.rs;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Iterator;

/**
 *
 */

public class ResultSetYard
{
    /**
     *
     */

    static public JSONArray get ( ResultSet resultSet ) throws SQLException
    {
    JSONArray json = new JSONArray(); ResultSetMetaData rsmd = resultSet.getMetaData();

    while ( resultSet.next() )
    {
    JSONObject obj = new JSONObject();

    for ( int i = 1; i < rsmd.getColumnCount() + 1; i++ )
    {
    String column_name = rsmd.getColumnName ( i );

         if ( rsmd.getColumnType ( i ) == java.sql.Types.ARRAY     ) { obj.put ( column_name, resultSet.getArray     ( column_name ) ); }

    else if ( rsmd.getColumnType ( i ) == java.sql.Types.BIGINT    ) { obj.put ( column_name, resultSet.getInt       ( column_name ) ); }

    else if ( rsmd.getColumnType ( i ) == java.sql.Types.BOOLEAN   ) { obj.put ( column_name, resultSet.getBoolean   ( column_name ) ); }

    else if ( rsmd.getColumnType ( i ) == java.sql.Types.BLOB      ) { obj.put ( column_name, resultSet.getBlob      ( column_name ) ); }

    else if ( rsmd.getColumnType ( i ) == java.sql.Types.DOUBLE    ) { obj.put ( column_name, resultSet.getDouble    ( column_name ) ); }
         
    else if ( rsmd.getColumnType ( i ) == java.sql.Types.FLOAT     ) { obj.put ( column_name, resultSet.getFloat     ( column_name ) ); }
         
    else if ( rsmd.getColumnType ( i ) == java.sql.Types.INTEGER   ) { obj.put ( column_name, resultSet.getInt       ( column_name ) ); }

    else if ( rsmd.getColumnType ( i ) == java.sql.Types.NVARCHAR  ) { obj.put ( column_name, resultSet.getNString   ( column_name ) ); }

    else if ( rsmd.getColumnType ( i ) == java.sql.Types.VARCHAR   ) { obj.put ( column_name, resultSet.getString    ( column_name ) ); }

    else if ( rsmd.getColumnType ( i ) == java.sql.Types.TINYINT   ) { obj.put ( column_name, resultSet.getInt       ( column_name ) ); }

    else if ( rsmd.getColumnType ( i ) == java.sql.Types.SMALLINT  ) { obj.put ( column_name, resultSet.getInt       ( column_name ) ); }

    else if ( rsmd.getColumnType ( i ) == java.sql.Types.DATE      ) { obj.put ( column_name, resultSet.getDate      ( column_name ) ); }

    else if ( rsmd.getColumnType ( i ) == java.sql.Types.TIMESTAMP ) { obj.put ( column_name, resultSet.getTimestamp ( column_name ) ); }

    else { obj.put ( column_name, resultSet.getObject ( column_name ) ); }
    }

    json.add ( obj );
    }

    return json;
    }

    /**
     *
     */

    static public ResultSetBolt get ( ResultSetBoltMap resultSetBoltMap, String column, int value )
    {
    Iterator it = resultSetBoltMap.iterator();

    while ( it.hasNext() )
    {
    ResultSetBolt resultSetBolt = ( ResultSetBolt ) it.next();

    if ( resultSetBolt.get ( column ).equals( value ) ) return resultSetBolt;
    }

    return null;
    }

}
