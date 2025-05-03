package ca.jetsphere.core.bolt.rs;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class ResultSetBoltList extends ResultSetBoltMap
{
    List list = new ArrayList();

    /**
     *
     */

    public ResultSetBoltList() { super(); }

    /**
     *
     */

    public ResultSetBoltList ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    public ResultSetBoltList ( JDBC jdbc, String query ) throws Exception

    { this(); if ( ! DockYard.isWhiteSpace ( query ) ) query ( jdbc, query ); }

    /**
     *
     */

    public void add ( Bolt bolt )

    { super.add ( bolt ); list.add ( bolt ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception

    { add ( new ResultSetBolt ( size(), getCaptions(), rs ) ); }

    /**
     *
     */

    public void clear() { super.clear(); list.clear(); }

    /**
     *
     */

    public List list ( boolean sort ) { return list; }

}
