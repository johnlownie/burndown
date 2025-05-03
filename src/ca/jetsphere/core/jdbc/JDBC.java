package ca.jetsphere.core.jdbc;

import java.sql.SQLException;

/**
 *
 */

public class JDBC extends Connection
{
    static public final String PREFIX = "xue8p_";

    /**
     *
     */

    public JDBC() { this ( null ); }

    /**
     *
     */

    public JDBC ( Object object ) { super ( object ); }

    /**
     *
     */

    protected void finalize() throws SQLException { close(); }

}
