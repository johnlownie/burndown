package ca.jetsphere.core.tier0;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.bolt.BoltYard;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.jdbc.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */

abstract public class AbstractDao
{
    /**
     *
     */

    public AbstractDao() {}

    /**
     *
     */

    protected boolean delete ( JDBC jdbc, Bolt bolt, String query ) throws Exception
    {
    Statement statement = null;

    try {

        Common.debug ( "[ AbstractDao:delete ] ( >> ) " + bolt.getKey() ); Common.debug ( " [ Query ] " + query ); statement = jdbc.createStatement(); int rowCount = statement.executeUpdate ( query ); Common.debug ( "[ AbstractDao:delete ] ( << ) " + rowCount  ); return rowCount > 0;

    } catch ( SQLException e ) { Common.trace ( e ); throw e; }

    finally { try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt ) throws Exception
    {
    PreparedStatement ps = jdbc.prepareStatement();

    try {

        insert$ ( jdbc, bolt, ps );

    } catch ( Exception e ) { throw e; }

    finally { if ( ps != null ) ps.close(); }
    }

    /**
     *
     */

    public void insert$ ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    ResultSet rs = null;

    try {

        insert ( jdbc, bolt, ps ); ps.executeUpdate(); rs = ps.getGeneratedKeys();

        if ( rs.next() ) bolt.setId ( rs.getInt ( 1 ) ); // else throw new Exception ( "AutoIndex" );

    } catch ( Exception e ) { throw e; }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    abstract public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception;

    /**
     *
     */

    public void name ( JDBC jdbc, Bolt bolt, int id, String name ) { bolt.query ( jdbc, BoltYard.fieldQuery ( bolt, id, "name", name ) ); }

    /**
     *
     */

    public void uuid ( JDBC jdbc, Bolt bolt, int id, String uuid ) { bolt.query ( jdbc, BoltYard.fieldQuery ( bolt, id, "uuid", uuid ) ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc, Bolt bolt ) { return update ( jdbc, bolt, true ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc, Bolt bolt, boolean ts )
    {
    PreparedStatement ps = null;

    try {

        ps = jdbc.prepareStatement(); return update$ ( jdbc, bolt, ps, ts );

    } catch ( Exception e ) { Common.trace ( e ); return false; }

    finally { try { ps.close(); } catch ( Exception e ) {} }
    }

    /**
     *
     */

    public boolean update$ ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    update ( jdbc, bolt, ps, ts );

    int count = ps.executeUpdate();

    return count > 0;
    }

    /**
     *
     */

    abstract public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception;
}
