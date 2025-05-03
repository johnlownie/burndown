package ca.jetsphere.core.common;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.apache.log4j.Appender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;

/**
 *
 */

public class Common
{
    final static Logger logger = Logger.getLogger ( Common.class );

    /**
     *
     */

    static public Appender getAppender ( String logger, String appender )

    { try { return org.apache.log4j.Logger.getLogger ( logger ).getAppender ( appender ); } catch ( Exception e ) { return null; } }

    /**
     *
     */

    static public String getLogFile ( String logger, String appender )

    { try { return ( ( DailyRollingFileAppender ) getAppender ( logger, appender ) ).getFile (); } catch ( Exception e ) { return ""; } }

    /**
     *
     */

    static public String getLogPath()

    { try { File file = new File ( getLogFile ( "ca.jetsphere", "dest2" ) ); return file.getParent (); } catch ( Exception e ) { return ""; } }

    /**
     *
     */

    static public void debug ( String s )

    { logger.debug ( s );  }

    /**
     *
     */

    static public void info ( String s )

    { logger.info ( s ); }

    /**
     *
     */

    static public void info ( Object o, String s )

    { logger.info ( o.getClass().getName() + ": " + s ); }

    /**
     *
     */

    static public void json ( HttpServletResponse response, String message )
    {
    try {

        response.setContentType ( "application/json" ); response.setCharacterEncoding ( "UTF-8" );

        PrintWriter out = response.getWriter();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "message", Caption.get ( response.getLocale(), message ) ); jsonObject.put ( "success", false );

        out.print ( jsonObject );

    } catch ( Exception e ) { trace ( e ); }

    }

    /**
     *
     */

    static public void trace ( Exception e )

    { logger.fatal ( "", e );  }

    /**
     *
     */

    static public void trace ( Object o, Exception e )

    { trace ( o.getClass().getName(), e );  }

    /**
     *
     */

    static public void trace ( String s, Exception e )

    { logger.fatal ( s + " : " + e.getMessage(), e ); }

    /**
     *
     */

    static public void trace ( HttpServletRequest request )
    {
    Enumeration e;

    Common.trace ( request.toString() );

    e = request.getParameterNames();

    while ( e.hasMoreElements() ) logger.fatal ( ( ( String ) e.nextElement() ) );

    e = request.getAttributeNames();

    while ( e.hasMoreElements() ) logger.fatal ( ( ( String ) e.nextElement() ) );

    logger.fatal ( "queryString = " + request.getQueryString() );
    }

    /**
     *
     */

    static public void trace ( String s )

    { logger.fatal ( s );  }

}
