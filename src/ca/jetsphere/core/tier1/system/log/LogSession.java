package ca.jetsphere.core.tier1.system.log;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.AbstractDao;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.ResultSet;

/**
 *
 */

public class LogSession extends BoltMap implements ISessionObject
{
    /**
     *
     */

    public LogSession() { super(); }

    /**
     *
     */

    public void add ( File file ) throws Exception { add ( new Log ( size(), file ) ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) {}

    /**
     *
     */

    public String [] captions() { return Log.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setSelected ( request, new Log() ); }

    /**
     *
     */

    public String [] fields() { return Log.fields(); }

    /**
     *
     */

    public AbstractDao getDao() { return null; }

    /**
     *
     */

    static public LogSession getInstance ( HttpServletRequest request )

    { return ( LogSession ) SessionCache.getSessionObject ( request, Log.key (), LogSession.class ); }

    /**
     *
     */

    public String getKey() { return Log.key(); }

    /**
     *
     */

    public Log getLog ( int log_id ) { return ( Log ) get ( log_id ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public Log getSelected ( HttpServletRequest request ) { return ( Log ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public LogSession query ( HttpServletRequest request, boolean blank )

    { LogSession session = getInstance ( request ); session.clear(); session.query ( Common.getLogPath() ); session.options ( request, blank ); return session; }

    /**
     *
     */

    public void query ( String path )
    {
    if ( DockYard.isWhiteSpace ( path ) ) return;

    try {

        clear();

        File logs = new File ( path );

        Common.trace ( "[ LOG FILE PATH ] " + path );

        if ( ! logs.exists() || ! logs.isDirectory() ) return;

        String name = Log.getLogName();

        Common.trace ( "[ LOG FILE NAME ] " + name );

        String list[] = logs.list();

        for ( int cc = 0; cc < list.length; cc ++ )
        {
            Common.debug ( "[ LOG FILE SCAN ] " + list [ cc ] );

            File file = new File ( path, list [ cc ] );

            if ( file.isFile() && ! file.isDirectory() )

                add ( file );
        }

    } catch ( Exception e ) { Common.trace ( e ); }

    }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, Log selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}
