package ca.jetsphere.core.bolt.rs;

import java.sql.ResultSet;
import java.util.Map;

/**
 *
 */

public class CsvResultSetBolt extends ResultSetBolt
{
    /**
     *
     */

    public CsvResultSetBolt() { super(); }

    /**
     *
     */

    protected CsvResultSetBolt ( int id, String[] captions, Map map ) throws Exception

    { super ( id, captions, map ); }

    /**
     *
     */

    public CsvResultSetBolt ( int id, String[] captions, ResultSet rs ) throws Exception

    { super ( id, captions, rs ); }

}
