package ca.jetsphere.core.security.cookie;

import ca.jetsphere.core.common.DockYard;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */

public class CookieYard
{
    /**
     *
     */

    static public void addCookie ( HttpServletResponse response, String name, String value )

    { addCookie ( response, name, value, 0, "", "" ); }

    /**
     *
     */

    static public void addCookie ( HttpServletResponse response, String name, String value, int max_age, String path, String domain )
    {
    Cookie cookie = new Cookie ( name, value );

    cookie.setMaxAge ( max_age );

    cookie.setPath ( DockYard.isWhiteSpace ( path ) ? "/" : path );

    if ( ! DockYard.isWhiteSpace ( domain ) ) cookie.setDomain ( domain );

    response.addCookie ( cookie );
    }

    /**
     *
     */

    static public Cookie getCookie ( HttpServletRequest request, String name )
    {
    Cookie[] cookies = request.getCookies();

    if ( cookies == null ) return null;

    for( int cc=0; cc < cookies.length; cc++)

    { if ( cookies[ cc ].getName().equals ( name ) ) return cookies[ cc ]; }

    return null;
    }

    /**
     *
     */

    static public String getValue ( HttpServletRequest request, String name )
    {
    Cookie cookie = getCookie ( request, name );

    return cookie == null ? "" : cookie.getValue();
    }

    /**
     *
     */

    static public boolean hasCookie ( HttpServletRequest request, String name )
    {
    Cookie[] cookies = request.getCookies();

    for ( int cc=0; cc < cookies.length; cc++)

    { if ( cookies[ cc ].getName().equals ( name ) ) return true; }

    return false;
    }

    /**
     *
     */

    static public boolean isTrue ( HttpServletRequest request, String name )
    {
    Cookie cookie = getCookie ( request, name );

    return cookie == null ? false : DockYard.toBoolean ( cookie.getValue() );
    }

}
