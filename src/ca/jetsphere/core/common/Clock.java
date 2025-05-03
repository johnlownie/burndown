package ca.jetsphere.core.common;

import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 */

public class Clock extends ActionForm
{
    String key; int hours, minutes, seconds; String format;

    /**
     *
     */

    public Clock() { this ( "clock" ); }

    /**
     *
     */

    public Clock ( String key ) { setKey ( key ); setFormat ( "hhmmss" ); clear(); }

    /**
     *
     */

    public Clock ( String key, String format, boolean now ) { this ( key ); if ( now ) set ( now() ); setFormat ( format ); }

    /**
     *
     */

    public Clock ( String key, HttpServletRequest request ) { this ( key ); options ( request ); }

    /**
     *
     */

    public Clock ( String key, Timestamp ts ) { this ( key ); set ( ts ); }

    /**
     *
     */

    public Clock ( String key, Timestamp ts, String format ) { this ( key ); set ( ts ); setFormat ( format ); }

    /**
     *
     */

    public void clear() { setHours ( 0 ); setMinutes ( 15 ); setSeconds ( 0 ); }

    /**
     *
     */

    public int getHours   () { return hours   ; }
    public int getMinutes () { return minutes ; }
    public int getSeconds () { return seconds ; }

    /**
     *
     */

    public String getKey() { return key; }

    /**
     *
     */

    public long getMillis() { return ( ( ( getHours() * 60 + getMinutes() ) * 60 ) + getSeconds() ) * 1000; }

    /**
     *
     */

    private void hh ( Writer writer, boolean enabled, boolean submit )
    {
    try {

        writer.write ( "<div class=\"col-md-4\"><select name='" + getKey() + ":hours' class=\"form-control\"" + ( enabled ? "" : " disabled" ) + ( submit ? " onChange='submit()'" : "" ) + ">" );

        for ( int hh = 0; hh < 24; hh ++ )
        {
        boolean isSelected = getHours() == hh;

        writer.write ( "<option " + ( isSelected ? "selected " : "" ) + ">" + ( hh < 10 ? "0" : "" ) + hh + " </option>" );
        }

        writer.write ( "</select></div>" );

    } catch ( Exception e ) { Common.trace ( e ); }

    }

    /**
     *
     */

    private void mm ( Writer writer, boolean enabled, boolean submit )
    {
    try {

        writer.write ( "<div class=\"col-md-4\"><select name='" + getKey() + ":minutes' class=\"form-control\"" + ( enabled ? "" : " disabled" ) + ( submit ? " onChange='submit()'" : "" ) + ">" );

        for ( int mm = 0; mm < 60; mm ++ )
        {
        boolean isSelected = getMinutes() == mm;

        writer.write ( "<option " + ( isSelected ? "selected " : "" ) + ">" + ( mm < 10 ? "0" : "" ) + mm + " </option>" );
        }

        writer.write ( "</select></div>" );

    } catch ( Exception e ) { Common.trace ( e ); }

    }

    /**
     *
     */

    private long now() { return System.currentTimeMillis(); }

    /**
     *
     */

    public void options ( HttpServletRequest request )
    {
    int hh = DockYard.toInteger ( request, getKey() + ":hours"   ); if ( hh < 0 ) hh = 0; setHours   ( hh );

    int mm = DockYard.toInteger ( request, getKey() + ":minutes" ); if ( mm < 0 ) mm = 0; setMinutes ( mm );

    int ss = DockYard.toInteger ( request, getKey() + ":seconds" ); if ( ss < 0 ) ss = 0; setSeconds ( ss );
    }

    /**
     *
     */

    public void print ( Writer writer, HttpServletRequest request, boolean enabled, boolean submit ) throws Exception
    {
    if ( format.indexOf ( "hh" ) > -1 ) { hh ( writer, enabled, submit ); }

    if ( format.indexOf ( "mm" ) > -1 ) { mm ( writer, enabled, submit ); }

    if ( format.indexOf ( "ss" ) > -1 ) { ss ( writer, enabled, submit ); }
    }

    /**
     *
     */

    public void set ( Calendar calendar )

    { setHours ( calendar.get ( Calendar.HOUR_OF_DAY ) ); setMinutes ( calendar.get ( Calendar.MINUTE ) ); setSeconds ( calendar.get ( Calendar.SECOND ) ); }

    /**
     *
     */

    public void set ( long t )
    {
    Calendar calendar = Calendar.getInstance();

    calendar.setTimeInMillis ( t );

    set ( calendar );
    }

    /**
     *
     */

    public void set ( Timestamp ts )

    { if ( ts != null ) set ( ts.getTime() ); else clear(); }

    /**
     *
     */

    public void setFormat ( String format ) { this.format = format; }

    /**
     *
     */

    public void setHours   ( int hours   ) { this.hours   = hours   ; }
    public void setMinutes ( int minutes ) { this.minutes = minutes ; }
    public void setSeconds ( int seconds ) { this.seconds = seconds ; }

    /**
     *
     */

    public void setKey ( String key ) { this.key = key; }

    /**
     *
     */

    private void ss ( Writer writer, boolean enabled, boolean submit )
    {
    try {

        writer.write ( "<div class=\"col-md-4\"><select name='" + getKey() + ":seconds' class=\"form-control\"" + ( enabled ? "" : " disabled" ) + ( submit ? " onChange='submit()'" : "" ) + ">" );

        for ( int ss = 0; ss < 60; ss ++ )
        {
        boolean isSelected = getSeconds() == ss;

        writer.write ( "<option " + ( isSelected ? "selected " : "" ) + ">" + ( ss < 10 ? "0" : "" ) + ss + " </option>" );
        }

        writer.write ( "</select></div>" );

    } catch ( Exception e ) { Common.trace ( e ); }

    }

}