package ca.jetsphere.core.tier1.common.calendar_event;

import ca.jetsphere.core.common.CalendarYard;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 */

public class CalendarEventYard
{
    /**
     *
     */

    static public boolean contains ( List dates, String date )
    {
    try {

        Date d = CalendarYard.getDate ( date, "yyyy-MM-dd" );

        Iterator it = dates.iterator();

        while ( it.hasNext() )
        {
        CalendarEvent event = ( CalendarEvent ) it.next();

        Date start = CalendarYard.getDate ( event.getStart(), "yyyy-MM-dd" ); Date end = CalendarYard.getDate ( event.getEnd(), "yyyy-MM-dd" );

        if ( d.equals ( start ) || ( d.after ( start ) && d.before ( end ) ) ) return true;
        }

        return false;

    } catch ( Exception e ) { Common.trace ( e ); return true; }

    }

    /**
     *
     */

    static public List filterDates ( List set, List subSet )
    {
    List copy = new ArrayList ( subSet );

    Iterator it = copy.iterator ();

    while ( it.hasNext() )

    { CalendarEvent calendarEvent = ( CalendarEvent ) it.next(); if ( contains ( set, calendarEvent.getStart () ) ) it.remove(); }

    return copy;
    }

    /**
     *
     */

    static public List unionDates ( List set, List subSet )
    {
    List copy = new ArrayList ( subSet );

    Iterator it = copy.iterator ();

    while ( it.hasNext() )

    { CalendarEvent calendarEvent = ( CalendarEvent ) it.next(); if ( ! contains ( set, calendarEvent.getStart () ) ) it.remove(); }

    return copy;
    }

    /**
     *
     */

    static public CalendarEvent get ( String start_date, String end_date )
    {
    CalendarEvent event = new CalendarEvent();

    event.setStart ( start_date ); event.setEnd ( end_date );

    event.setBackgroundColor ( "#ddd" ); event.setBorderColor ( "#ddd" );

    event.setOverlap ( false ); event.setRendering ( "background" );

    return event;
    }

    /**
     *
     */

    static public List get ( List dates )
    {
    List list = new ArrayList(); DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    Iterator it = dates.iterator ();

    while ( it.hasNext () )
    {
    LocalDate date = ( LocalDate ) it.next(); LocalDate nextDay = date.plusDays ( 1 );

    CalendarEvent event = get ( date.format ( formatter ), nextDay.format ( formatter ) );

    list.add ( event );
    }

    return list;
    }

    /**
     *
     */

    static public List getBackgroundEvents ( List<String> days, String color, boolean ignore_weekends )
    {
    List list = new ArrayList<CalendarEvent>();

    for ( String day: days )
    {
    if ( DockYard.isWhiteSpace ( day ) || (ignore_weekends && !CalendarYard.isWeekday ( day ) ) ) continue;

    list.add ( getBackgroundEvent ( "", day, CalendarYard.getNextDay ( day ), color ) );
    }

    return list;
    }

    /**
     *
     */

    static public CalendarEvent getBackgroundEvent ( String title, String start_date, String end_date, String color )
    {
    CalendarEvent event = new CalendarEvent();

    event.setTitle ( title );

    event.setStart ( start_date ); event.setEnd ( end_date );

    event.setBackgroundColor ( color ); event.setBorderColor ( color );

    event.setRendering ( "background" );

    return event;
    }

    /**
     *
     */

    static public List getUnavailableDates ( int cutoff )
    {
    List list = new ArrayList(); String start = "2017-09-04";

    list.add ( get ( "2017-01-01", CalendarYard.getDaysFromNow ( cutoff ) ) );

    return list;
    }

    /**
     *
     */

    static public List getSCUnavailableDates ( int cutoff )
    {
    List list = new ArrayList(); String start = "2017-09-04"; String next_monday = nextMonday ( "yyyy-MM-dd", cutoff > Calendar.SUNDAY && cutoff < Calendar.SATURDAY ? cutoff + 1 : Calendar.THURSDAY );

    list.add ( get ( "2017-01-01", CalendarYard.isAfter ( start, next_monday )? start : next_monday ) );

    return list;
    }

    /**
     *
     */

    public static Calendar nextDayOfWeek ( int dow, int cutoff )
    {
    Calendar calendar = Calendar.getInstance(); int today = calendar.get ( Calendar.DAY_OF_WEEK );

    int base = ( today <= cutoff ) ? 7 : 14;

    int diff = dow - today;

    if ( !( diff > 1 ) ) diff += base;

    calendar.add ( Calendar.DAY_OF_MONTH, diff );

    return calendar;
    }

    /**
     *
     */

    public static String nextMonday ( String format, int cutoff )

    { return CalendarYard.getDateTimeFormat ( CalendarYard.daysFromDate ( nextDayOfWeek ( Calendar.MONDAY, cutoff ).getTime(), -1 ), format ); }

    /**
     *
     */

    static public List noCommon ( List<String> available_days, List<CalendarEvent> unavailable_days )
    {
    List list = new ArrayList();

    for ( String date : available_days )

        { if ( ! contains ( unavailable_days, date ) ) list.add ( date ); }

    return list;
    }

}
