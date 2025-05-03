package ca.jetsphere.core.common;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.bolt.BoltMap;
import org.apache.struts.util.LabelValueBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 *
 */

public class DockYard
{
    /**
     *
     */

    static public String caption ( String s )
    {
    if ( isWhiteSpace ( s ) ) return "";

    return Caption.get ( s.toLowerCase().replaceAll ( "'", "." ).replaceAll ( " ", "." ).replaceAll ( "&nbsp;", "." ).replaceAll ( "/", "." ).replaceAll ( "-", "." ) );
    }

    /**
     *
     */

    static public String caption ( HttpServletRequest request, String s )
    {
    if ( isWhiteSpace ( s ) ) return "";

    String original = s; String substituted = s.toLowerCase().replaceAll ( "'", "." ).replaceAll ( " ", "." ).replaceAll ( "&nbsp;", "." ).replaceAll ( "/", "." ).replaceAll ( "-", "." ).replaceAll ( ":", "" );

    String translated = Caption.get ( request, substituted );

    return compareTo ( substituted, translated ) == 0 ? original : translated;
    }

    /**
     *
     */

    static public String clipAt ( String s, int cc )
    {
    if ( s != null && s.length() > cc ) s = s.substring ( 0, cc ) + "&nbsp;...";

    return s;
    }

    /**
     *
     */

    static public int compareTo ( Date d1, Date d2 )

    { if ( d1 == null ) return d2 == null ? 0 : -1; else if ( d2 == null ) return 1; return d1.compareTo ( d2 ); }

    /**
     *
     */

    static public int compareTo ( int i1, int i2 )

    { return new Integer ( i1 ).compareTo( new Integer( i2 ) ); }

    /**
     *
     */

    static public int compareTo ( String s1, String s2 )

    { if ( s1 == null ) return s2 == null ? 0 : -1; else if ( s2 == null ) return 1; return s1.compareTo( s2 ); }

    /**
     *
     */

    static public boolean contains ( String s1, String s2 ) { return s1 != null ? s1.indexOf ( s2 ) > -1 : false; }

    /**
     *
     */

    static public boolean contains ( String[] s1, String s2 )
    {
    if ( isWhiteSpace ( s1 ) || isWhiteSpace ( s2 ) || s1.length == 0 ) return false;

    for ( int cc = 0; cc < s1.length; cc ++ )

        if ( compareTo ( s1 [ cc ], s2 ) == 0 ) return true;

    return false;
    }

    /**
     *
     */

    static public boolean contains ( String s1, String[] s2 )
    {
    if ( isWhiteSpace ( s1 ) || isWhiteSpace ( s2 ) || s2.length == 0 ) return false;

    for ( String s : s2 ) if ( contains ( s1, s )  ) return true;

    return false;
    }

    /**
     *
     */

    static public boolean contains ( int[] s1, int s2 )
    {
    if ( s1 == null || s1.length == 0 ) return false;

    for ( int cc = 0 ; cc < s1.length; cc ++ )

        if ( compareTo ( s1 [ cc ], s2 ) == 0 ) return true;

    return false;
    }

    /**
     *
     */

    static public boolean equals ( int i1, int i2 ) { return i1 == i2; }

    /**
     *
     */

    static public boolean equalsIgnoreCase ( String s1, String s2 )

    { if ( s1 == null ) return s2 == null ? true : false; else if ( s2 == null ) return false; return s1.equalsIgnoreCase ( s2 ); }

    /**
     *
     */

    static public Object getAttribute ( HttpServletRequest request, String s )

    { return getAttribute( request, s, true ); }

    /**
     *
     */

    static public Object getAttribute ( HttpServletRequest request, String s, boolean isSession )
    {
    if ( ! isSession ) return request.getAttribute ( s );

    HttpSession session = request.getSession ( false );

    if ( session == null ) return null;

    return session.getAttribute ( s );
    }

    /**
     *
     */

    static public String getDecimalFormat ( double decimal, String format )
    {
    DecimalFormat df = new DecimalFormat ( format );

    try {

        return df.format ( decimal );

    } catch ( IllegalArgumentException e ) { return "" + decimal; }

    }

    /**
     *
     */

    static public String getHref ( String url ) { return getHref ( url, url, true ); }

    /**
     *
     */

    static public String getHref ( String text, String url, boolean new_window )
    {
    if ( isWhiteSpace ( url ) ) return ""; if ( isWhiteSpace ( text ) ) text = url;

    return "<a href=\"" + url + "\"" + ( new_window ? " target=\"_blank\"" : "" ) + ">" + clipAt ( text, 40 ) + "</a>";
    }

    /**
     *
     */

    static public String getIp ( HttpServletRequest request )
    {
    if ( request == null ) return "";

    return request.getHeader ( "X-FORWARDED-FOR" ) == null ? request.getRemoteAddr() : request.getHeader ( "X-FORWARDED-FOR" );
    }

    /**
     *
     */

    static public List getLabelValue ( HttpServletRequest request, List<String> list )
    {
    List labelValue = new ArrayList();

    for ( String item : list )

    { labelValue.add ( new LabelValueBean ( item, item ) ); }

    return labelValue;
    }

    /**
     *
     */

    static public String getNumberFormat ( int number )
    {
    NumberFormat nf = NumberFormat.getInstance ( Locale.CANADA );

    return nf.format( number );
    }

    /**
     *
     */

    static public int getObjectSize ( Object o )
    {
    if ( o == null ) return 0;

    try {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        ObjectOutputStream p = new ObjectOutputStream ( stream );

        p.writeObject ( o );

        return stream.size();

    } catch ( NotSerializableException ns ) { if ( ns.getClass().getName().startsWith ( "com.jet" ) || ns.getClass().getName().startsWith ( "com.blue7" ) ) Common.trace ( ns ); return 0;

    } catch ( Exception e ) { Common.trace ( e ); return 0; }

    }

    /**
     *
     */

    static public String getOptions ( HttpServletRequest request, List<String> list )

    { return getOptions ( request, list, "" ); }

    /**
     *
     */

    static public String getOptions ( HttpServletRequest request, Map<String, String> map )

    { return getOptions ( request, map, 0, "please.select" ); }

    /**
     *
     */

    static public String getOptions ( HttpServletRequest request, List<String> list, String selected )

    { return getOptions ( request, list, selected, "" ); }

    /**
     *
     */

    static public String getOptions ( HttpServletRequest request, List<String> list, String selected, String blank )
    {
    StringBuilder sb = new StringBuilder();

    if ( ! isWhiteSpace ( blank ) ) sb.append ( "<option value=\"\">" + Caption.get ( request, blank ) + "</option>" );

    for ( String item: list )

    { sb.append ( "<option" + ( item.equalsIgnoreCase ( selected ) ? " selected=\"selected\"" : "" ) + ">" + Caption.get ( request, item ) + "</option>" ); }

    return sb.toString();
    }

    /**
     *
     */

    static public String getOptions ( HttpServletRequest request, Map<String, String> map, int selected, String blank )
    {
    StringBuilder sb = new StringBuilder();

    if ( ! isWhiteSpace ( blank ) ) sb.append ( "<option value=\"\">" + Caption.get ( request, blank ) + "</option>" );

    for ( Map.Entry<String, String> item: map.entrySet() )

    { sb.append ( "<option value=\"" + item.getKey() + "\"" + ( DockYard.toInteger ( item.getKey() ) == selected ? " selected=\"selected\"" : "" ) + ">" + Caption.get ( request, item.getValue() ) + "</option>" ); }

    return sb.toString();
    }

    /**
     *
     */

    static public String getOptions ( HttpServletRequest request, BoltMap boltMap )

    { return getOptions ( request, boltMap, null, "please.select" ); }

    /**
     *
     */

    static public String getOptions ( HttpServletRequest request, BoltMap boltMap, String blank )

    { return getOptions ( request, boltMap, null, blank ); }

    /**
     *
     */

    static public String getOptions ( HttpServletRequest request, BoltMap boltMap, Comparator comparator, String blank )

    { return getOptions ( request, boltMap, comparator, -1, blank ); }

    /**
     *
     */

    static public String getOptions ( HttpServletRequest request, BoltMap boltMap, Comparator comparator, int selected, String blank )
    {
    StringBuilder sb = new StringBuilder();

    if ( ! isWhiteSpace ( blank ) ) sb.append ( "<option value=\"\">" + Caption.get ( request, blank ) + "</option>" );

    Iterator it = comparator == null ? boltMap.iterator ( true ) : boltMap.iterator ( comparator );

    while ( it.hasNext () )

    { Bolt bolt = ( Bolt ) it.next (); sb.append ( "<option value=\"" + bolt.getId () + "\" " + ( selected == bolt.getId() ? " selected=\"selected\"" : "" ) + ">" + bolt.getName () + "</option>" ); }

    return sb.toString();
    }

    /**
     *
     */

    static public String getParameter ( HttpServletRequest request, String s )

    { return isWhiteSpace ( request, s ) ? "" : request.getParameter ( s ); }

    /**
     *
     */

    static public String[] getParameterValues ( HttpServletRequest request, String s )

    { return request.getParameterValues ( s ); }

    /**
     *
     */

    static public String getRemaining ( String s, String delimiter )

    { return isWhiteSpace ( s ) ? "" : s.substring ( s.indexOf( delimiter ) + 1 ); }

    /**
     *
     */

    static public List getSessionInfo ( HttpSession session )
    {
    List list = new ArrayList();

    Enumeration en = session.getAttributeNames();

    while ( en.hasMoreElements() )
    {
    String s = ( String ) en.nextElement(); Object o = session.getAttribute ( s );

    String size = "" + getObjectSize ( o ) ;

    list.add ( new LabelValueBean( s, size ) );
    }

    Collections.sort ( list );

    return list;
    }

    /**
     *
     */

    static public String getToken ( String s, int index ) { return getToken ( s, index, ":" ); }

    /**
     *
     */

    static public String getToken ( String s, int index, String delimiter )
    {
    int cc = 0;

    if ( index < 1 ) return s;

    StringTokenizer st = new StringTokenizer ( s, delimiter );

    while ( st.hasMoreTokens() )

    { cc++; String token = st.nextToken(); if ( cc == index ) return token; }

    return "";
    }

    /**
     *
     */

    static public int getTokenCount ( String s ) { return getTokenCount ( s, ":" ); }

    /**
     *
     */

    static public int getTokenCount ( String s, String delimiter )
    {
    int cc = 0;

    StringTokenizer st = new StringTokenizer ( s, delimiter );

    while ( st.hasMoreTokens() ) { cc++; st.nextToken(); }

    return cc;
    }

    /**
     *
     */

    static public String[] getTokens ( String tokens )
    {
    int count = getTokenCount ( tokens, ":" );

    if ( count == 0 ) return null;

    String[] strings = new String [ count ];

    for ( int cc = 0; cc < count; cc ++ )

        strings [ cc ] = getToken ( tokens, cc + 1, ":" );

    return strings;
    }

    /**
     *
     */

    static public List getTokens ( String tokens, String delimiter )
    {
    List list = new ArrayList();

    StringTokenizer st = new StringTokenizer ( tokens, delimiter );

    while ( st.hasMoreTokens() ) list.add ( st.nextToken() );

    return list;
    }

    /**
     *
     */

    static public String getTokenStartsWith ( String s, String key, String separator )
    {
    StringTokenizer st = new StringTokenizer ( s, separator );

    while ( st.hasMoreTokens() )

    { String token = st.nextToken().trim(); if ( token.startsWith ( key ) ) return token; }

    return "";
    }

    /**
     *
     */

    static public String getURL ( HttpServletRequest request, String s )

    { return getURL ( request, s, "" ); }

    /**
     *
     */

    static public String getURL ( HttpServletRequest request, String path, String s )
    {
    try {

        String file = request.getContextPath() + "/" + path + s;

        URL url = request.getServerPort() == 80 ?  new URL ( request.getScheme(), request.getServerName(), file ) : new URL ( request.getScheme(), request.getServerName(), request.getServerPort(), file );

        return url.toString();

    } catch ( Exception e ) { Common.trace ( e ); return ""; }

    }

    /**
     *
     */

    static public boolean hasTokenStartsWith ( String s, String key, String separator )
    {
    StringTokenizer st = new StringTokenizer ( s, separator );

    while ( st.hasMoreTokens() )

    { String token = st.nextToken().trim(); if ( token.startsWith ( key ) ) return true; }

    return false;
    }

    /**
     *
     */

    static public String hhmmss ( long ms )
    {
    if ( ms < 0 ) return "--:--:--";

    ms = ms / 1000; long ss = ms % 60; ms = ms / 60; long mm = ms % 60; ms = ms / 60; long hh = ms % 60;

    return ( hh < 10 ? "0" : "" ) + hh + ":" + ( mm < 10 ? "0" : "" ) + mm + ":" + ( ss < 10 ? "0" : "" ) + ss;
    }

    /**
     *
     */

    static public boolean isInsertUpdateDelete ( String s )

    {  s = s.toLowerCase(); return s.indexOf ( "insert " ) >= 0 || s.indexOf ( "update " ) >= 0 || s.indexOf ( "delete " ) >= 0; }

    /**
     *
     */

    static public <T> List<T> intersection ( List<T> list1, List<T> list2 )
    {
    List<T> list = new ArrayList<T>();

    for ( T t : list1 )
        
    { if ( list2.contains ( t ) ) list.add ( t ); }

    return list;
    }

    /**
     *
     */

    static public boolean isStaging ( HttpServletRequest request )

    { return contains ( request.getServerName(), new String[] { "localhost", "demo", "staging", "lowknee" } ); }

    /**
     *
     */

    static public boolean isWhiteSpace ( String s ) { return s == null || trimAll ( s ).length() == 0; }

    /**
     *
     */

    static public boolean isWhiteSpace ( HttpServletRequest request, String s )
    {
    StringTokenizer st = new StringTokenizer ( s, ":" );

    while ( st.hasMoreTokens() )

        try {  if ( ! isWhiteSpace ( request.getParameter ( st.nextToken() ) ) ) return false; } catch ( Exception e ) { return true; }

    return true;
    }

    /**
     *
     */

    static public boolean isWhiteSpace ( String[] s )
    {
    if ( s == null ) return true;

    for ( int cc = 0; cc < s.length; cc ++ )

        if ( ! isWhiteSpace ( s [ cc ] ) ) return false;

    return true;
    }

    /**
     *
     */

    static public String join ( String[] s ) { return join ( s, "," ); }

    /**
     *
     */

    static public String join ( String[] s, String delimiter )
    {
    if ( DockYard.isWhiteSpace ( s ) ) return "";

    StringBuilder sb = new StringBuilder();

    for ( int cc = 0; cc < s.length; cc ++ )

        if ( ! isWhiteSpace ( s [ cc ] ) ) { if ( sb.length() > 0 ) sb.append ( delimiter ); sb.append ( s [ cc ] ); }

    return sb.toString();
    }

    /**
     *
     */

    static public Timestamp now() { return new Timestamp ( System.currentTimeMillis() ); }

    /**
     *
     */

    static public String quote ( String s )
    {
    if ( s == null ) return "''";

    s = s.replace ( '"', '\'' ); s = s.replaceAll ( "'", "''" ); s = s.replace ( '^', '\\' ); s = s.replace ( '\n', ' ' ); s = s.replace ( '\r', ' ' );

    return "'" + s + "'";
    }

    /**
     *
     */

    static public void setAttribute ( HttpServletRequest request, String s, Object o )

    { setAttribute ( request, s, o, true ); }

    /**
     *
     */

    static public void setAttribute ( HttpServletRequest request, String s, Object o, boolean inSession )

    { if ( inSession ) request.getSession().setAttribute ( s, o ); else request.setAttribute ( s, o ); }

    /**
     *
     */

    static public void sleep ( long time )

    { try { Thread.sleep ( time ); } catch ( InterruptedException e ) {} }

    /**
     *
     */

    static public String strip ( String s ) { return strip ( s, false ); }

    /**
     *
     */

    static public String strip ( String s, boolean cr )
    {
    if ( s == null ) return "";

    StringBuilder sb = new StringBuilder ( s.length() );

    for ( int cc = 0; cc < s.length(); cc ++ )
    {
        char c = s.charAt ( cc );

        if ( Character.getType ( c ) == Character.CONTROL ) continue;

        sb.append ( c );
    }

    return sb.toString().trim();
    }

    /**
     *
     */

    static public boolean toBoolean ( String s )
    {
    try {

        return ( "true".equalsIgnoreCase ( s ) || "1".equalsIgnoreCase ( s ) || "on".equalsIgnoreCase ( s ) || "yes".equalsIgnoreCase ( s ) ) ? true : false;

    } catch ( Exception e ) { return false; }

    }

    /**
     *
     */

    static public boolean toBoolean ( HttpServletRequest request, String s )

    { return toBoolean ( getParameter (  request, s ) ); }

    /**
     *
     */

    static public int toInt ( Object o )
    {
    if ( o == null ) return 0;

    if ( o.getClass() == Integer   .class ) return toInteger ( ( Integer    ) o );

    if ( o.getClass() == String    .class ) return toInteger ( ( String     ) o );

    if ( o.getClass() == Boolean   .class ) return toInteger ( ( Boolean    ) o );

    if ( o.getClass() == Long      .class ) return toInteger ( ( Long       ) o );

    if ( o.getClass() == BigDecimal.class ) return toInteger ( ( BigDecimal ) o );

    return 0;
    }

    /**
     *
     */

    static public Double toDouble ( String s ) { try { return Double.parseDouble ( s ); } catch ( Exception e ) { return 0.0; } }

    /**
     *
     */

    static public int toInteger ( Integer i ) { return i.intValue(); }

    /**
     *
     */

    static public int toInteger ( BigDecimal bd ) { return bd.intValue(); }

    /**
     *
     */

    static public int toInteger ( Boolean b ) { return b.booleanValue() ? 1 : 0; }

    /**
     *
     */

    static public int toInteger ( Long l ) { return l.intValue(); }

    /**
     *
     */

    static public int toInteger ( Object o )

    { try { return toInteger ( ( String ) o ); } catch ( Exception e ) { return -1 ; } }

    /**
     *
     */

    static public int toInteger ( String s )

    { try { return Integer.parseInt ( s ); } catch ( Exception e ) { return -1; } }


    /**
     *
     */

    static public int toInteger ( HttpServletRequest request, String s )

    { return toInteger ( request.getParameter ( s ) ); }

    /**
     *
     */

    static public String toMoney ( int cents )

    { return new DecimalFormat ( "###,###,###,##0.00" ).format ( ( double ) cents / 100 ); }
    /**
     *
     */

    static public String toFormattedMoney ( int cents )

    { return new DecimalFormat ( "$###,###,###,##0.00;($###,###,###,##0.00)" ).format ( ( double ) cents / 100 ); }

    /**
     *
     */

    static public double toPercent ( int numerator, int denominator )
    {
    try { return ( numerator * 100.0 ) / denominator; } catch ( Exception e ) { return 0.0; }
    }

    /**
     *
     */

    static public String toString ( boolean b ) { return b ? "true" : "false"; }

    /**
     *
     */

    static public String toString ( int i ) { return Integer.toString ( i ); }

    /**
     *
     */

    static public String toString ( double d ) { return Double.toString ( d ); }

    /**
     *
     */

    static public List toList ( Object [] o )
    {
    List list = new ArrayList();

    if ( o != null )

        for ( int cc = 1; cc < o.length; cc ++ )

            list.add ( o [ cc ] );

    return list;
    }

    /**
     *
     */

    static public String toLowerCase ( String s )
    {
    if ( isWhiteSpace ( s ) ) return s;

    try {

        return s.toLowerCase();

    } catch ( Exception e ) { return ""; }

    }

    /**
     *
     */

    static public String trimAll ( String s ) { return s != null ? s.replaceAll ( "[\\u00A0]", "" ).trim() : ""; }

    /**
     *
     */

    static public <T> List<T> union ( List<T> l1, List<T> l2 )
    {
    Set<T> set = new HashSet<T>();

    set.addAll ( l1 ); set.addAll ( l2 );

    return new ArrayList<T> ( set );
    }

    /**
     *
     */

    static public String zeroPad ( long i, int width )

    { return zeroPad ( Long.toString ( i ), width ); }

    /**
     *
     */

    static public String zeroPad ( String s, int width )
    {
    StringBuilder sb = new StringBuilder ( "" );

    for ( int cc = 0; cc < width - s.length (); cc++ ) sb.append ( "0" );

    sb.append ( s );

    return sb.toString();
    }

}
