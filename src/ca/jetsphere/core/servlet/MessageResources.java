package ca.jetsphere.core.servlet;

import ca.jetsphere.core.common.Common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 *
 */

public class MessageResources extends org.apache.struts.util.MessageResources
{
    protected List bundles; protected HashMap loaded, messages;

    /**
     *
     */

    public MessageResources ( List bundles )

    { super ( null, null ); clear(); this.bundles = bundles; }

    /**
     *
     */

    public void clear()

    { bundles = new ArrayList(); loaded = new HashMap(); messages = new HashMap(); }

    /**
     *
     */

    public void clearFormats() { loaded = new HashMap(); messages = new HashMap(); formats.clear(); }

    /**
     *
     */

    protected ClassLoader getClassLoader()
    {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    if ( classLoader == null ) classLoader = getClass().getClassLoader();

    return classLoader;
    }

    /**
     *
     */

    protected String getFilename ( String localeKey, String bundle )
    {
    String filename = bundle;

    if ( localeKey.length () > 0 ) filename += "_" + localeKey;

    filename += ".properties";

    return filename;
    }

    /**
     *
     */

    protected InputStreamReader getInputStreamReader ( ClassLoader classLoader, String filename )
    {
    InputStreamReader isr = null;

    try { isr = new InputStreamReader ( classLoader.getResourceAsStream ( filename ), "UTF-8" ); } catch ( Exception e ) {}

    if ( isr == null ) Common.debug ( "RESOURCE FILE AWOL: " + filename );

    return isr;
    }

    /**
     *
     */

    public String getMessage ( Locale locale, String key )
    {
    String message = getMessageX ( locale, key );

    if ( message != null ) return message;

    message = getMessageX ( locale, key.toLowerCase() );

    if ( message != null ) return message;

    return key;
    }

    /**
     *
     */

    public String getMessageX ( Locale locale, String key )
    {
    String localeKey = localeKey ( locale );

    String messageKey = messageKey ( localeKey, key.trim().replaceAll ( "  ", "." ).replaceAll ( ": ", "." ).replaceAll ( " / ", "." ).replaceAll ( " \\ ", "." ).replaceAll ( " - ", "." ).replaceAll ( ":", "." ).replaceAll ( "'", "." ).replaceAll ( " ", "." ).replaceAll ( "&nbsp;", "." ).replaceAll ( "/", "." ).replaceAll ( "-", "." ) );

    String message = ( String ) messages.get ( messageKey );

    if ( message != null ) return message;

    message = getMessage ( localeKey, key );

    if ( message != null ) return message;

    if ( ! defaultLocale.equals ( locale ) ) message = getMessage ( localeKey ( defaultLocale ), key );

    if ( message != null ) return message;

    return getMessage ( "", key );
    }

    /**
     *
     */

    public String getMessage ( String localeKey, String key )
    {
    for ( int cc = 0; cc < bundles.size (); cc++ )
    {
    String bundle = ( String ) bundles.get ( cc );

    setMessages ( localeKey, bundle );

    String message = ( String ) messages.get ( messageKey ( localeKey, key ) );

    if ( message != null ) return message;
    }

    return null;
    }

    /**
     *
     */

    protected Properties getProperties ( String filename )
    {
    Properties properties = new Properties();

    try {

        ClassLoader classLoader = getClassLoader();

        InputStreamReader isr = getInputStreamReader ( classLoader, filename );

        if ( isr == null ) return properties;

        properties.load ( isr );

        isr.close();

    } catch ( IOException e ) { Common.trace ( e ); }

    return properties;
    }

    /**
     *
     */

    protected void setMessages ( String localeKey, String bundle )
    {
    String filename = getFilename(localeKey, bundle);

    if ( loaded.get ( filename ) != null ) return;

    loaded.put ( filename, localeKey );

    setMessagesByProperties(localeKey, filename);
    }

    /**
     *
     */

    public void setMessagesByProperties ( String localeKey, String filename )
    {
    Properties properties = getProperties ( filename );

    synchronized ( this.messages )
    {
    Iterator keySet = properties.keySet().iterator();

    while ( keySet.hasNext() )
    {
    String key = ( String ) keySet.next();

    messages.put ( messageKey ( localeKey, key ), properties.getProperty ( key ) );
    } }

    }

}