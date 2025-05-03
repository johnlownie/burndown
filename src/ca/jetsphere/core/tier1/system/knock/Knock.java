package ca.jetsphere.core.tier1.system.knock;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.servlet.MessageResources;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.HashMap;

/**
 *
 */

public class Knock
{
    static HashMap map = new HashMap();

    /**
     *
     */

    static public String get ( String s )

    { try { return ( String ) map.get ( s ); } catch ( Exception e ) { Common.debug ( "[ Knock ] Not Found: " + s ); return ""; } }

    /**
     *
     */

    static public HashMap getMap() { return map; }

    /**
     *
     */

    static public String getMessage ( String s )

    { try { return map.get ( s ) != null ? ( String ) map.get ( s ) : Caption.get ( s ); } catch ( Exception e ) { return ""; } }

    /**
     *
     */

    static public MessageResources getMessageResources() { return ( MessageResources ) map.get ( "MESSAGE_RESOURCES" ); }

    /**
     *
     */

    static public void set ( String key, String value ) { map.put ( key, value ); }

    /**
     *
     */

    static public void set ( ServletConfig servletConfig )

    { setInitParameters ( servletConfig ); setInitParameters ( servletConfig.getServletContext() ); }

    /**
     *
     */

    static private void setInitParameters ( ServletContext servletContext )
    {
    map.put ( "APPLICATION_PATH"                 , servletContext.getRealPath ( "/" ) );

    map.put ( "ATTACHMENT_UPLOAD_DIRECTORY_PATH" , servletContext.getRealPath ( get ( "ATTACHMENT_UPLOAD_DIRECTORY" ) ) );

    map.put ( "CONTEXT_PATH"                     , servletContext.getContextPath() );

    map.put ( "IMAGE_DIRECTORY"                  , "/images" );

    map.put ( "IMAGE_DIRECTORY_PATH"             , servletContext.getRealPath ( "/images" ) );

    map.put ( "IMAGE_UPLOAD_DIRECTORY_PATH"      , servletContext.getRealPath ( get ( "IMAGE_UPLOAD_DIRECTORY" ) ) );

    map.put ( "PDF_FORMS_DIRECTORY_PATH"         , servletContext.getRealPath ( get ( "PDF_FORMS_DIRECTORY" ) ) );

    map.put ( "PUBLIC_KEY_STORE_PATH"            , servletContext.getRealPath ( get ( "PUBLIC_KEY_STORE" ) ) );

    map.put ( "WEB_INF"                          , servletContext.getRealPath ( "/WEB-INF" ) );

    map.put ( "GATEWAY_PATH"                     , servletContext.getRealPath ( "/WEB-INF/ca/jetsphere/base/merchant/gateway" ) );
    }

    /**
     *
     */

    static private void setInitParameters ( ServletConfig servletConfig )
    {
    Enumeration e = servletConfig.getInitParameterNames();

    while ( e.hasMoreElements() ) { String key = ( String ) e.nextElement(); map.put ( key, servletConfig.getInitParameter ( key ) ); }
    }

    /**
     *
     */

    static public void setMessageResources ( ServletContext servletContext )

    { map.put ( "MESSAGE_RESOURCES", servletContext.getAttribute ( "org.apache.struts.action.MESSAGE" ) ); }

}
