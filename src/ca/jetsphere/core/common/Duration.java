package ca.jetsphere.core.common;

import java.util.Locale;

/**
 *
 */

public class Duration
{
    int dd, hh, mm, ss;

    /**
     *
     */

    public Duration ( long ms )
    {
    ss = ( int ) ms / 1000;

    mm = ss / 60; if ( mm != 0 ) ss %= ( mm * 60 );

    hh = mm / 60; if ( hh != 0 ) mm %= ( hh * 60 );

    dd = hh / 24; if ( dd != 0 ) hh %= ( dd * 24 );
    }

    /**
     *
     */

    public String get ( Locale locale )

    { return dd + " " + Caption.get ( locale, dd != 1 ? "days" : "day" ) + ", " + hh + ":" + DockYard.zeroPad ( mm, 2 ) + ":" + DockYard.zeroPad ( ss, 2 ); }

    /**
     *
     */

    static public long now() { return System.currentTimeMillis(); }

}
