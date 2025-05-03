package ca.jetsphere.core.tier1.system.log;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier1.system.knock.Knock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

public class Log extends Bolt
{
    int       log_id          ;
    String    log_name        ;
    String    log_text        ;
    long      log_size        ;
    String    log_url         ;
    Timestamp log_last_update ;

    /**
     *
     */

    public Log() { super (); }

    /**
     *
     */

    public Log ( int id, File file ) throws Exception

    { setId ( id ); setName ( file.getName() ); setSize ( file.length() ); setUrl ( file.getCanonicalPath() ); setLastUpdate ( new Timestamp ( file.lastModified() ) ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "name", "size", "last.update", "actions" }; }

    /**
     *
     */

    public void clear()
    {
    setId         (  -1  );
    setName       (  ""  );
    setText       (  ""  );
    setSize       (   0  );
    setUrl        (  ""  );
    setLastUpdate ( null );
    }

    /**
     *
     */

    static public String [] fields() { return new String [] { "log_name", "log_size", "log_last_update", "log_uuid" }; }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception {}

    /**
     *
     */

    public Object get ( String s )
    {
    if ( DockYard.compareTo ( "log_uuid", s ) == 0 ) return getEditDeleteBox ( "" + getId() );

    if ( DockYard.compareTo ( "log_size", s ) == 0 ) return DockYard.getDecimalFormat ( getSize(), "###,###,##0" );

    return super.get ( s );
    }

    /**
     *
     */

    public int       getId         () { return log_id          ; }
    public String    getName       () { return log_name        ; }
    public String    getText       () { return log_text        ; }
    public long      getSize       () { return log_size        ; }
    public String    getUrl        () { return log_url         ; }
    public Timestamp getLastUpdate () { return log_last_update ; }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    static public String key() { return "log"; }

    /**
     *
     */

    static public String getLogName() throws Exception
    {
    BufferedReader reader = null;

    try {

        String line = "";

        String path = Knock.get ( "WEB_INF" ) + File.separator + "classes";

        File file = new File ( path, "log4j.properties" );

        reader = new BufferedReader ( new FileReader ( file ) );

        while ( ( line = reader.readLine() ) != null )
        {
        int cc = line.indexOf ( "/logs/" ); if ( cc == -1 ) continue; line = line.substring ( cc + 6 ); cc = line.indexOf ( ".log" ); if ( cc != -1 ) line = line.substring ( 0, cc );

        return line;
        }

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { if ( reader != null ) reader.close(); }

    return null;
    }

    /**
     *
     */

    public void setId         ( int       log_id          ) { this.log_id          = log_id          ; }
    public void setName       ( String    log_name        ) { this.log_name        = log_name        ; }
    public void setText       ( String    log_text        ) { this.log_text        = log_text        ; }
    public void setSize       ( long      log_size        ) { this.log_size        = log_size        ; }
    public void setUrl        ( String    log_url         ) { this.log_url         = log_url         ; }
    public void setLastUpdate ( Timestamp log_last_update ) { this.log_last_update = log_last_update ; }

    /**
     *
     */

    public void setText() throws Exception
    {
    byte[] encoded = Files.readAllBytes ( Paths.get ( getUrl () ) );

    setText ( new String ( encoded, "UTF-8" ) );
    }
}
