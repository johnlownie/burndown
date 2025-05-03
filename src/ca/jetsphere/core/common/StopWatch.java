package ca.jetsphere.core.common;

/**
 *
 */

public class StopWatch
{
    long start, last;

    /**
     *
     */

    public StopWatch() { reset(); }

    /**
     *
     */

    public long getElapsed() { long elapsed = now(); elapsed -= start; return elapsed; }

    /**
     *
     */

    public String hhmmss() { return hhmmss ( getElapsed() ); }

    /**
     *
     */

    static public String hhmmss ( long elapsed ) { return DockYard.hhmmss ( elapsed ); }

    /**
     *
     */

    public long last() { long elapsed = now(); elapsed -= last; last = now(); return elapsed; }

    /**
     *
     */

    public long now() { return System.currentTimeMillis(); }

    /**
     *
     */

    public long reset() { return start = last = now(); }

    /**
     *
     */

    public String trace() { return trace ( last() ); }

    /**
     *
     */

    static public String trace ( long elapsed )

    { return hhmmss ( elapsed ) + " ( ms ) " + elapsed; }

    /**
     *
     */

    public void trace ( String s ) { Common.trace ( "[ " + s + " ] " + trace ( getElapsed() ) ); }

}

