package ca.jetsphere.core.common;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class SessionCache implements Serializable
{
    Map map = new HashMap();

    /**
     *
     */

    public SessionCache ( HttpServletRequest request ) { setSessionCache ( request, this ); }

    /**
     *
     */

    public void add ( ISessionObject iso ) { map.put ( iso.getKey(), iso ); }

    /**
     *
     */

    public void add ( Object key, ISessionObject iso ) { map.put ( key, iso ); }

    /**
     *
     */

    public ISessionObject getSessionObject ( String key ) { return ( ISessionObject ) map.get ( key ); }

    /**
     *
     */

    static public String key() { return "cache"; }

    /**
     *
     */

    static public SessionCache getSessionCache ( HttpServletRequest request )
    {
    SessionCache cache = ( SessionCache ) request.getSession().getAttribute ( key() );

    if ( cache == null ) cache = new SessionCache ( request );

    return cache;
    }

    /**
     *
     */

    static public ISessionObject getSessionObject ( HttpServletRequest request, String key, Class clazz )
    {
    try {

        SessionCache cache = getSessionCache ( request );

        ISessionObject iso = cache.getSessionObject ( key );

        if ( iso != null ) return iso;

        iso = ( ISessionObject ) clazz.newInstance();

        cache.add ( key, iso );

        return iso;

    } catch ( Exception e ) { Common.trace ( e ); return null; }

    }

    /**
     *
     */

    static public ISessionObject getSessionObject ( HttpServletRequest request, String key ) { return getSessionCache ( request ).getSessionObject ( key ); }

    /**
     *
     */

    public void remove ( String key ) { add ( key, null ); }

    /**
     *
     */

    private void setSessionCache ( HttpServletRequest request, SessionCache cache )

    { if ( request.getSession().getAttribute ( key() ) == null ) request.getSession().setAttribute ( key(), cache ); }

}
