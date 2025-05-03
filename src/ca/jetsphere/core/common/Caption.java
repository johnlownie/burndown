package ca.jetsphere.core.common;

import ca.jetsphere.core.servlet.MessageResources;
import ca.jetsphere.core.tier1.system.knock.Knock;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 *
 */

public class Caption
{
    Locale locale; MessageResources resources;

    /**
     *
     */

    public Caption ( HttpServletRequest request ) { this ( getLocale ( request ) ); }

    /**
     *
     */

    public Caption ( Locale locale ) { this.locale = locale; this.resources = Knock.getMessageResources(); }

    /**
     *
     */

    public void clearFormats() { resources.clearFormats(); }

    /**
     *
     */

    static public String get ( HttpServletRequest request, ActionMessage message ) { return get ( request, message.getKey(), message.getValues() ); }

    /**
     *
     */

    static public String get ( HttpServletRequest request, String key ) { return get ( getLocale ( request ), key ); }

    /**
     *
     */

    static public String get ( HttpServletRequest request, String key, Object [] args ) { return get ( getLocale ( request ), key, args ); }

    /**
     *
     */

    static public String get ( Locale locale, String message )  { return new Caption ( locale ).getMessage ( message ); }

    /**
     *
     */

    static public String get ( Locale locale, String message, Object [] args )  { return new Caption ( locale ).getMessage ( message, args ); }

    /**
     *
     */

    static public String get ( String message ) { return get ( Locale.getDefault(), message ); }

    /**
     *
     */

    static public String get ( String message, Object [] args ) { return get ( Locale.getDefault(), message, args ); }

    /**
     *
     */

    static public Locale getLocale ( HttpServletRequest request )
    {
    if ( request.getSession() != null && request.getSession().getAttribute ( "org.apache.struts.action.LOCALE" ) != null ) return ( Locale ) request.getSession().getAttribute ( "org.apache.struts.action.LOCALE" );

    if ( request.getAttribute ( "org.apache.struts.action.LOCALE" ) != null ) return ( Locale ) request.getAttribute ( "org.apache.struts.action.LOCALE" );

    return request.getLocale();
    }

    /**
     *
     */

    public String getMessage ( String s )
    {
    try {

        return resources.getMessage ( locale, s );

    } catch ( Exception e ) { if ( ! "#".equals ( s ) ) Common.debug ( "Lost Resource: " + s ); }

    return s;
    }

    /**
     *
     */

    public String getMessage ( String key, Object [] values )
    {
    try {

        return resources.getMessage ( locale, key, values );

    } catch ( Exception e ) { if ( ! "#".equals ( key ) ) Common.debug ( "Lost Resource: " + key ); }

    return key;
    }

}
