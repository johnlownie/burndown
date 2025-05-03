package ca.jetsphere.core.security;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier1.system.knock.Knock;

import javax.servlet.http.HttpServletRequest;
import java.io.Writer;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */

public class LockOut
{
    Timer timer; long millis, watch; boolean lock;

    /**
     *
     */

    public LockOut() { clear(); }

    /**
     *
     */

    public LockOut ( long millis ) { this(); start ( millis ); }

    /**
     *
     */

    class TimeOut extends TimerTask { public void run() { release(); setLock ( true ); Common.trace ( "Server Lock Out" ); } }

    /**
     *
     */

    public void clear() { setTimer ( null ); }

    /**
     *
     */

    public void cancel() { release(); setLock ( false ); }

    /**
     *
     */

    public long elapsed() { return isWait() ? System.currentTimeMillis() - getWatch() : 0; }

    /**
     *
     */

    public String getAlert ( HttpServletRequest request )

    { if ( isWait() && isWarn() ) return Caption.get ( request, "server.will.shut.down.in", getTime ( shutdown() ) ); if ( isLock() ) return Caption.get ( request, "server.is.stopped", getTime ( stopped() ) ); return ""; }

    /**
     *
     */

    public void getMessage ( Writer writer, HttpServletRequest request )
    {
    try {

        if ( isWait() )

            writer.write ( Caption.get ( request, "server.lockout.scheduled.in", getTime ( shutdown() ) ) + "<br><br>" );

    } catch ( Exception e ) { Common.trace ( e ); }

    }

    /**
     *
     */

    public String[] getTime ( long millis )
    {
    millis /= 1000;

    long hours = millis / 3600; millis -= hours * 3600; long minutes = millis / 60; millis -= minutes * 60; long seconds = millis;

    String[] s = { DockYard.zeroPad ( hours, 2 ), DockYard.zeroPad ( minutes, 2 ), DockYard.zeroPad ( seconds, 2 ) };

    return s;
    }

    /**
     *
     */

    public int getWarningMinutes()

    { int minutes = DockYard.toInteger ( Knock.get ( "LOCKOUT-WARNING-MINUTES" ) ); if ( minutes < 0 ) minutes = 15; return minutes; }

    /**
     *
     */

    public boolean isWait() { return timer != null; }

    /**
     *
     */

    public boolean isWarn() { return getWarningMinutes() * 60 * 1000 >= shutdown(); }

    /**
     *
     */

    public void release()
    {
    try {

        if ( getTimer() != null ) getTimer().cancel();

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { clear(); }
    }

    /**
     *
     */

    private void setLock ( boolean lock )

    { this.lock = lock; setWatch ( System.currentTimeMillis() ); }

    /**
     *
     */

    public long shutdown() { return isWait() ? getMillis() - elapsed() : 0; }

    /**
     *
     */

    public synchronized boolean start ( long millis )
    {
    if ( isWait()|| isLock() ) return false;

    setMillis ( millis );

    setTimer ( new Timer() );

    getTimer().schedule ( new TimeOut(), getMillis() );

    setWatch ( System.currentTimeMillis() );

    return true;
    }

    /**
     *
     */

    public long stopped() { return isLock() ? System.currentTimeMillis() - getWatch() : 0; }

    /**
     *
     */

    public long    getMillis() { return millis ; }
    public Timer   getTimer () { return timer  ; }
    public long    getWatch () { return watch  ; }
    public boolean isLock   () { return lock   ; }

    /**
     *
     */

    private void setMillis ( long millis ) { this.millis = millis ; }
    private void setTimer  ( Timer timer ) { this.timer  = timer  ; }
    private void setWatch  ( long watch  ) { this.watch  = watch  ; }

}
