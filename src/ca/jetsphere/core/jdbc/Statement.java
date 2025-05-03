package ca.jetsphere.core.jdbc;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */

public class Statement
{
    java.sql.Connection connection; java.sql.Statement statement;

    /**
     *
     */

    public Statement ( java.sql.Connection connection )

    { this.connection = connection; }

    /**
     *
     */

    public void close() throws SQLException { if ( statement != null ) statement.close(); }

    /**
     *
     */

    public boolean closed() { return statement == null; }

    /**
     *
     */

    public ResultSet executeQuery ( String sql ) throws SQLException

    { setReadOnly ( sql ); if ( closed() ) open(); return statement.executeQuery ( sql ); }

    /**
     *
     */

    public int executeUpdate ( String sql ) throws SQLException

    { setReadOnly ( sql ); if ( closed() ) open(); return statement.executeUpdate ( sql ); }

    /**
     *
     */

    protected void finalize() throws SQLException { close(); }

    /**
     *
     */

    public void open() throws SQLException

    { this.statement = connection.createStatement(); }

    /**
     *
     */

    public void setReadOnly ( String sql ) throws SQLException
    {
//    connection.setReadOnly ( DockYard.isInsertUpdateDelete ( sql ) ? false : true );

    Common.debug ( ( connection.isReadOnly() ? "[ STATEMENT READONLY ]" : "[ STATEMENT READ/WRITE ]" ) + " QUERY: " + sql );
    }

}
