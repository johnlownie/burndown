package ca.jetsphere.core.bolt;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.MyActionForm;
import ca.jetsphere.core.common.Tag;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.Statement;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 */

abstract public class BoltMap implements Serializable
{
    protected Map map = new LinkedHashMap(); Bolt selected;

    /**
     *
     */

    public void clear() { map.clear(); }

    public boolean containsKey ( Object key ) { return map.containsKey ( key ); }

    public boolean containsValue ( Object value ) { return map.containsValue ( value ); }

    public Set entrySet() { return map.entrySet (); }

    public boolean equals ( Object o ) { return map.equals ( o ); }

    public Bolt get ( Object key ) { return ( Bolt ) map.get ( key ); }

    public int hashCode() { return map.hashCode(); }

    public boolean isEmpty() { return map.isEmpty (); }

    public Set keySet() { return map.keySet (); }

    public Object put ( Object key, Object value ) { return map.put ( key, value ); }

    public void putAll ( Map t ) { map.putAll ( t ); }

    public Object remove ( Object key ) { return map.remove ( key ); }

    public int size() { return map.size (); }

    public Collection values() { return map.values (); }

    public void add ( Bolt bolt ) { put ( bolt ); }

    public void add ( int id, Bolt bolt ) { put ( id, bolt ); }

    public void add ( BoltMap bolts ) { add ( bolts.list ( false ) ); }

    abstract public void add ( JDBC jdbc, ResultSet rs ) throws Exception;

    public void add ( List list ) { putAll ( list ); }

    public boolean contains ( Bolt bolt ) { return get ( bolt.getId() ) != null; }

    public void clearSelected() { selected = null; }

    public void clearSelected ( HttpServletRequest request ) {}

    public void delete ( JDBC jdbc ) throws Exception { Iterator it = iterator(); while ( it.hasNext() ) { Bolt bolt = ( Bolt ) it.next(); bolt.delete ( jdbc ); } }

    public void find ( JDBC jdbc, Bolt bolt ) { clear(); if ( bolt != null ) find ( jdbc, bolt.getId() ); }

    public void find ( JDBC jdbc, Bolt bolt1, Bolt bolt2 ) { clear(); if ( bolt1 != null && bolt1 != null ) find ( jdbc, bolt1.getId(), bolt2.getId() ); }

    public void find ( JDBC jdbc, int id ) {}

    public void find ( JDBC jdbc, int id1, int id2 ) {}

    public void find ( JDBC jdbc, int id1, int id2, int id3 ) {}

//    public void foreign ( JDBC jdbc ) throws Exception { Iterator it = iterator(); while ( it.hasNext() ) { Bolt bolt = ( Bolt ) it.next(); bolt.foreign ( jdbc ); bolt.getPayload ( jdbc ); } }

    public Bolt get ( int key ) { return ( Bolt ) get ( new Integer ( key ) ); }

    public Bolt get1st ( java.util.Comparator comparator ) {
        List list = list ( comparator  );

        return ! list.isEmpty() ? ( Bolt ) list.get ( 0 ) : null;
    }

    public Bolt get1st() { List list = list ( true  ); return ! list.isEmpty() ? ( Bolt ) list.get ( 0 ) : null; }

    public Bolt getAny() { List list = list ( false ); return ! list.isEmpty() ? ( Bolt ) list.get ( 0 ) : null; }

    public Bolt getBolt ( int key ) { return ( Bolt ) getBolt ( new Integer ( key ) ); }

    public Bolt getBolt ( Integer key ) { try { return ( Bolt ) map.get ( key ); } catch ( Exception e ) { return null; } }

    public Bolt getBolt ( String key ) { return getBolt ( new Integer ( key ) ); }

    public Bolt getBoltByName ( String name ) { Iterator it = iterator(); while ( it.hasNext() ) { Bolt bolt = ( Bolt ) it.next(); if ( DockYard.compareTo ( bolt.getName(), name ) == 0 ) return bolt; } return null; }

    public Bolt getBoltByUuid ( String uuid ) { Iterator it = iterator(); while ( it.hasNext() ) { Bolt bolt = ( Bolt ) it.next(); if ( DockYard.compareTo ( bolt.getUuid (), uuid ) == 0 ) return bolt; } return null; }

    public BoltMap getBoltMap() { return this; }

    public BoltComparator getComparator() { return new BoltComparator(); }

    public int getId ( HttpServletRequest request ) { return getId ( request, getKey() ); }

    public String[] getIdsAsString() { String[] ids = new String [ size() ]; int cc = 0; Iterator it = iterator(); while ( it.hasNext() ) { Bolt bolt = ( Bolt ) it.next(); ids [ cc++ ] = DockYard.toString ( bolt.getId() ); } return ids; }

    static public int getId ( HttpServletRequest request, String key ) { return DockYard.toInteger ( request.getParameter ( Tag.get ( key ) ) ); }

    abstract public String getKey();

    public void getPayload ( JDBC jdbc ) { Iterator it = iterator(); while ( it.hasNext() ) { Bolt bolt = ( Bolt ) it.next(); bolt.getPayload ( jdbc ); } }

    public void getPayload ( JDBC jdbc, int id1, int id2 ) { Iterator it = iterator(); while ( it.hasNext() ) { Bolt bolt = ( Bolt ) it.next(); bolt.getPayload ( jdbc, id1, id2 ); } }

    public Bolt getSelected() { return selected; }

    public int getSelectedId() { return selected != null ? selected.getId() : -1; }

    public boolean isSelected ( Bolt bolt ) { return DockYard.equals ( getSelectedId(), bolt.getId() ); }

    public Iterator iterator() { return iterator ( false ); }

    public Iterator iterator ( boolean sort ) { return list ( sort ).iterator(); }

    public Iterator iterator ( java.util.Comparator comparator ) { return list ( comparator ).iterator(); }

    public List list ( boolean sort ) { List list = new ArrayList ( map.values() ); if ( sort ) Collections.sort ( list ); return list; }

    public List list ( java.util.Comparator comparator ) { List list = new ArrayList ( map.values() ); Collections.sort ( list, comparator ); return list; }

    public List list ( String [] ids ) { List list = new ArrayList(); if ( ids != null ) for ( int cc = 0; cc < ids.length; cc ++ ) list.add ( getBolt ( ids [ cc ] ) ); return list; }

    public void merge ( BoltMap bolts ) throws Exception { Iterator it = bolts.iterator ( false ); while ( it.hasNext() ) { Bolt bolt = ( Bolt ) it.next(); if ( ! contains ( bolt ) ) add ( bolt ); } }

    public void put ( Bolt bolt ) { put ( new Integer ( bolt.getId() ), bolt ); }

//  public void put ( Bolt bolt ) { put ( size(), bolt ); }

    public void putAll ( List list ) { Iterator it = list.iterator(); while ( it.hasNext() ) put ( ( Bolt ) it.next() ); }

    public void remove ( int key ) { map.remove ( new Integer ( key ) ); }

    public void remove ( Bolt bolt ) { remove ( bolt.getId () ); }

    public Bolt search ( String name ) { Iterator it = iterator(); while ( it.hasNext() ) { Bolt bolt = ( Bolt ) it.next(); if ( DockYard.compareTo ( bolt.getName(), name ) == 0 ) return bolt; } return null; }

    public void set ( BoltMap bolts ) { this.map = bolts.map; this.selected = bolts.selected; }

    public void set ( List list ) { map.clear(); add ( list ); }

    public void set1st() { setSelected ( get1st () ); }

    public void setSelected ( Bolt selected ) { this.selected = selected; }

    public void setSelected ( int selected ) { setSelected ( get ( selected ) ); }

    static public List sort ( BoltMap bolts, String [] sort, boolean ascend ) { List list = bolts.list ( false ); Collections.sort ( list, new BoltComparator ( sort, ascend ) ); return list; }

    static public List sort ( BoltMap bolts, String sort, boolean ascend ) { String [] s = { sort }; return sort ( bolts, s, ascend ); }

    public List sublist ( int at, int to ) { List list = list ( true ); if ( at < 0 ) at = 0; if ( to < at || to >= list.size() ) to = list.size()-1; return sublist ( list, at, to ); }

    public List sublist ( List list, int at, int to ) { List sublist = new ArrayList(); for ( int cc = at; cc < to + 1; cc ++ ) sublist.add ( list.get ( cc ) ); return sublist; }

    /**
     *
     */

    public BoltMap() { super(); }

    public BoltMap ( JDBC jdbc, int id ) { this(); query ( jdbc, id ); }

    public BoltMap ( JDBC jdbc, String query ) { this(); query ( jdbc, query ); }

    public void query ( JDBC jdbc, String query ) { query ( jdbc, query, true ); }

    /**
     *
     */

    public void query ( JDBC jdbc, String query, boolean clear )
    {
    Statement statement = null; ResultSet rs = null;

    try {

        if ( clear ) clear();

        statement = jdbc.createStatement(); rs = statement.executeQuery ( query );

        while ( rs.next() ) add ( jdbc, rs );

    } catch ( Exception e ) { Common.trace ( query.toString() ); Common.trace ( this, e ); }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( statement != null ) statement.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    public void deleteX ( JDBC jdbc, int id ) throws Exception

    { clear(); query ( jdbc, id ); Iterator it = iterator ( false ); while ( it.hasNext() ) ( ( Bolt ) it.next() ).delete ( jdbc ); }

    /**
     *
     */

    public void query ( JDBC jdbc, int id ) { find ( jdbc, id ); }

    /**
     *
     */

    public void query ( JDBC jdbc, int id1, int id2 ) { find ( jdbc, id1, id2 ); }

    /**
     *
     */

    public void query ( JDBC jdbc, int id1, int id2, int id3 ) { find ( jdbc, id1, id2, id3 ); }

    /**
     *
     */

    static public int getSelectedX ( HttpServletRequest request, String key ) { return DockYard.toInteger ( request.getSession().getAttribute ( key ) ); }

    /**
     *
     */

    public void options ( HttpServletRequest request, boolean blank )

    { int id = getId ( request ); if ( id > 0 ) setQualifiedSelected ( request, id ); else if ( blank ) clearSelected ( request ); else if ( ! isEmpty() && ( getSelected() == null || ! getSelected().isValid() || ! contains ( getSelected() ) ) ) setQualifiedSelected ( request, get1st() ); }

    /**
     *
     */

    public void set1st ( HttpServletRequest request ) { setQualifiedSelected ( request, get1st() );  }

    /**
     *
     */

    public void setActionForm ( HttpServletRequest request, Bolt bolt )

    {  if ( bolt != null ) request.getSession().setAttribute ( MyActionForm.getActionFormName ( bolt.getKey() ), bolt ); else setSelected ( null ); }

    /**
     *
     */

    public void setNonQualifiedSelected ( HttpServletRequest request, Bolt bolt )

    { setSelected ( bolt ); setActionForm ( request, bolt ); }

    /**
     *
     */

    public void setNonQualifiedSelected ( HttpServletRequest request, int edit ) { setNonQualifiedSelected ( request, get ( edit ) );  }

    /**
     *
     */

    public void setQualifiedSelected ( HttpServletRequest request, Bolt bolt )
    {
    if ( bolt != null && bolt.isValid() ) add ( bolt );

//    if ( bolt != null && isEmpty() ) add ( bolt );

    if ( bolt == null ) bolt = getSelected();

    if ( bolt != null && bolt.getId() > 0 && ! contains ( bolt ) ) bolt = get1st();

    setNonQualifiedSelected ( request, bolt );
    }

    /**
     *
     */

    public void setQualifiedSelected ( HttpServletRequest request, int edit ) { setQualifiedSelected ( request, get ( edit ) );  }

    /**
     *
     */

    public void setQualifiedSelected ( HttpServletRequest request, String uuid )
    {
    Bolt bolt = getBoltByUuid ( uuid );

    setQualifiedSelected ( request, bolt );
    }

}
