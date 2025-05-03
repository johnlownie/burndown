package ca.jetsphere.core.bolt.rs;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.bolt.BoltYard;
import ca.jetsphere.core.common.DockYard;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */

public class ResultSetBolt extends Bolt
{
    Map map; int id; String [] captions;

    /**
     *
     */

    public ResultSetBolt() { super(); this.map = new HashMap(); }

    /**
     *
     */

    protected ResultSetBolt ( int id, String[] captions, Map map ) throws Exception

    { this(); this.id = id; this.captions = captions; this.map = map; }

    /**
     *
     */

    public ResultSetBolt ( int id, String[] captions, ResultSet rs ) throws Exception

    { this(); setId ( rs, id ); this.captions = captions; get ( rs ); }

    /**
     *
     */

    public int compareTo ( Object o )
    {
    Bolt bolt = ( Bolt ) o;

    return DockYard.compareTo ( getId(), bolt.getId() );
    }

    /**
     *
     */

    public void copy ( Bolt bolt )

    { this. id = ( ( ResultSetBolt ) bolt ).getId(); this.map = ( ( ResultSetBolt ) bolt ).getMap(); this.captions = ( ( ResultSetBolt ) bolt ).getCaptions(); }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    for ( int cc = 0; cc < captions.length; cc ++ )

        map.put ( captions [ cc ], BoltYard.getObject ( rs, cc + 1 ) );
    }

    /**
     *
     */

    public Object get ( int column )

    { return column >= 0 && column < captions.length ? map.get ( captions [ column ] ) : null; }

    /**
     *
     */

    public Object get ( String s )
    {
    Object o = map.get ( s );

    if ( o != null && o.getClass() == Double.class ) { DecimalFormat df = new DecimalFormat ( "#.00" ); return df.format ( o ); }

    return map.get ( s );
    }

    /**
     *
     */

    public String[] getCaptions() { return captions; }

    /**
     *
     */

    public int getId() { return id; }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public Map getMap() { return map; }

    /**
     *
     */

    public Object getColumn0() { return get ( 0 ); }
    public Object getColumn1() { return get ( 1 ); }
    public Object getColumn2() { return get ( 2 ); }
    public Object getColumn3() { return get ( 3 ); }
    public Object getColumn4() { return get ( 4 ); }

    /**
     *
     */

    static public String key() { return "result_set"; }

    /**
     *
     */

    public List list()
    {
    ArrayList list = new ArrayList();

    if ( captions != null )

        for ( int cc = 0; cc < captions.length; cc ++ )

            list.add ( get ( captions [ cc ] ) );

    return list;
    }

    /**
     *
     */

    public void setId ( int id ) { this.id = id; }

    /**
     *
     */

    public void setId ( ResultSet rs, int size )
    {
    try {

        int id = rs.getInt ( "#" );

        setId ( id );

    } catch ( Exception e ) { setId ( size ); }

    }

}
