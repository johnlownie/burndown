package ca.jetsphere.core.common;

import ca.jetsphere.core.tier1.system.knock.Knock;
import org.apache.struts.util.LabelValueBean;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

import static java.time.temporal.TemporalAdjusters.*;

/**
 *
 */

public class CalendarYard
{
    /**
     * 
     */
    
    static public Comparator BY_WEEK = new Comparator<Date>()
    {
    @Override
    public int compare ( Date o1, Date o2 )
    
    { return DockYard.compareTo ( getWeekOfYear ( o1 ), getWeekOfYear ( o2 ) ); }
    
    };
    
    /**
     *
     */

    static public DayOfWeek dayOfWeek()

    { LocalDate now = LocalDate.now(); return now.getDayOfWeek(); }

    /**
     *
     */

    static public int dayOfWeek ( String date, String format )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( format );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    return localDate.getDayOfWeek().getValue();
    }

    /**
     *
     */

    static public String getClosestDay ( String[] dates, String date )
    {
    if ( DockYard.isWhiteSpace ( date ) || dates == null || dates.length == 0 ) return null;

    int weekNumber = getWeekNumber ( date ); int minDays = Integer.MAX_VALUE; String minDate = "";

    for ( String d : dates )
    {
    if ( weekNumber != getWeekNumber ( d ) ) continue;

    int numDays = getNumDays ( d, date );

    if ( numDays < minDays ) { minDays = numDays; minDate = d; }
    }

    return minDate;
    }

    /**
     *
     */

    static public List getDaysOfWeek ( String date )
    {
    List list = new ArrayList();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    LocalDate startDate = localDate.with ( DayOfWeek.MONDAY ); LocalDate endDate = localDate.with ( DayOfWeek.FRIDAY ).plusDays ( 1 );

    while ( startDate.isBefore ( endDate ) )

    { String d = startDate.format ( formatter ); if ( DockYard.compareTo ( date, d ) != 0 ) list.add ( startDate.format ( formatter ) ); startDate = startDate.plusDays ( 1 ); }

    return list;
    }

    /**
     *
     */

    static public Date daysFromDate ( Date date, int days )
    {
    if ( date == null ) date = new Date();

    Calendar calendar = Calendar.getInstance();

    calendar.setTime ( date );

    calendar.add ( Calendar.DATE, days );

    return calendar.getTime();
    }

    /**
     *
     */

    static public String formatDate ( String in_date, String in_format, String out_format )
    {
    try {

        if ( DockYard.isWhiteSpace ( in_date ) ) return "";

        Date date = new SimpleDateFormat ( in_format ).parse ( in_date );

        return new SimpleDateFormat ( out_format ).format ( date );

    } catch ( Exception e ) { Common.trace ( e ); return in_date; }

    }

    /**
     *
     */

    static public String getCurrentYearMonth()
    {
    DateFormat dateFormat = new SimpleDateFormat ( "yyyy-MM" );

    Calendar calendar = Calendar.getInstance();

    return dateFormat.format ( calendar.getTime() );
    }

    /**
     *
     */

    static public Date getDate ( String s, String format )
    {
    try {

        DateFormat df = new SimpleDateFormat ( format );

        return df.parse ( s.substring ( 0, format.length() ) );

    } catch ( Exception e ) { Common.trace ( "Error - unable to parse " + s + " (" + format + ")" ); return null; }

    }

    /**
     *
     */

    static public int getDateAsInt ( String s, String format )
    {
    try {

        Date date = getDate ( s, format ); String fDate = new SimpleDateFormat ( "MMdd" ).format ( date );

        return DockYard.toInteger ( fDate );

    } catch ( Exception e ) { return -1; }

    }

    /**
     *
     */

    static public String getDateFormatDateTime ( Locale locale, Date date, int dateFormat, int timeFormat )
    {
    try {

        DateFormat df = DateFormat.getDateTimeInstance ( dateFormat, timeFormat, locale );

        df.setTimeZone ( getTimeZone() );

        return df.format ( date );

    } catch ( Exception e ) { return ""; }

    }

    /**
     *
     */

    static public String getDateFormatDateTime ( Locale locale, Date date, int dateFormat )

    { return getDateFormatDateTime ( locale, date, dateFormat, dateFormat ); }

    /**
     *
     */

    static public String getDateFormatDateTimeMedium ( Locale locale, Date date ) { return getDateFormatDateTime ( locale, date, DateFormat.MEDIUM ); }
    /**
     *
     */

    static public String getDateTimeFormat ( Date date )

    { return getDateTimeFormat ( getSimpleDateFormat ( "yyyy-MM-dd HH:mm:ss z" ), date ); }

    /**
     *
     */

    static public String getDateTimeFormat ( Date date, String s )

    { if ( date == null ) return ""; return DockYard.isWhiteSpace ( s ) ? getDateTimeFormat ( date ) : getDateTimeFormat ( getSimpleDateFormat ( s ), date ); }

    /**
     *
     */

    static public String getDateTimeFormat ( SimpleDateFormat sdf, Date date )
    {
    try {

        return sdf.format ( date );

    } catch ( Exception e ) { return ""; }

    }

    /**
     *
     */

    static public String getDayOfWeek ( String date )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    return localDate.getDayOfWeek().name();
    }

    /**
     *
     */

    static public String getDayNext ( String date, int days )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    localDate = localDate.plusDays ( days );

    return localDate.format ( formatter );
    }
    /**
     *
     */

    static public String getDayPrev ( String date, int days )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    localDate = localDate.minusDays ( days );

    return localDate.format ( formatter );
    }

    /**
     *
     */

    static public List getDayList ( HttpServletRequest request, String blank )
    {
    List list = new ArrayList();

    if ( ! DockYard.isWhiteSpace ( blank ) ) list.add ( new LabelValueBean ( blank, "-1" ) );

    list.add ( new LabelValueBean ( Caption.get ( request, "day.monday"    ), "" + DayOfWeek.MONDAY   .getValue() ) );
    list.add ( new LabelValueBean ( Caption.get ( request, "day.tuesday"   ), "" + DayOfWeek.TUESDAY  .getValue() ) );
    list.add ( new LabelValueBean ( Caption.get ( request, "day.wednesday" ), "" + DayOfWeek.WEDNESDAY.getValue() ) );
    list.add ( new LabelValueBean ( Caption.get ( request, "day.thursday"  ), "" + DayOfWeek.THURSDAY .getValue() ) );
    list.add ( new LabelValueBean ( Caption.get ( request, "day.friday"    ), "" + DayOfWeek.FRIDAY   .getValue() ) );
    list.add ( new LabelValueBean ( Caption.get ( request, "day.saturday"  ), "" + DayOfWeek.SATURDAY .getValue() ) );
    list.add ( new LabelValueBean ( Caption.get ( request, "day.sunday"    ), "" + DayOfWeek.SUNDAY   .getValue() ) );

    return list;
    }

    /**
     *
     */

    static public List getDaysAround ( String date )
    {
    List dates = new ArrayList(); DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate initial = LocalDate.parse ( date, formatter );

    LocalDate startDate = initial.with ( firstDayOfMonth() ); LocalDate endDate = initial.with ( lastDayOfMonth() );

    long days = Period.between ( startDate, endDate ).getDays();

    for ( long cc = 0; cc < days; cc++ )

    { LocalDate d = startDate.plusDays ( cc );  dates.add ( d ); }

    return dates;
    }

    /**
     *
     */

    static public List getDays ( String start_date, String end_date )
    {
    List list = new ArrayList<String>();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate startDate = LocalDate.parse ( start_date, formatter ); LocalDate endDate = LocalDate.parse( end_date, formatter );

    while ( startDate.isBefore ( endDate ) || startDate.isEqual ( endDate ) )

    { list.add ( startDate.format ( formatter ) ); startDate = startDate.plusDays ( 1 ); }

    return list;
    }

    /**
     *
     */

    static public String getDaysFromDate ( String date, int days )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    localDate = localDate.plusDays ( days );

    return localDate.format ( formatter );
    }

    /**
     *
     */

    static public String getDaysFromNow ( int days )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate localDate = LocalDate.now();

    localDate = localDate.plusDays ( days );

    return localDate.format ( formatter );
    }

    /**
     *
     */

    static public String getFirstDayOfMonth ( String date )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate initial = LocalDate.parse ( date, formatter );

    LocalDate lastDay = initial.with( firstDayOfMonth() );

    return lastDay.format ( formatter );
    }

    /**
     *
     */

    static public String getFirstDayOfYear ( String date )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate initial = LocalDate.parse ( date, formatter );

    LocalDate lastDay = initial.with( firstDayOfYear() );

    return lastDay.format ( formatter );
    }

    /**
     *
     */

    static public String getFridayOnlyDelivery()
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDateTime now = LocalDateTime.now();

    LocalDateTime nextFriday = now.with ( TemporalAdjusters.next ( DayOfWeek.FRIDAY ) );

    LocalDateTime followingFriday = nextFriday.with ( TemporalAdjusters.next ( DayOfWeek.FRIDAY ) );

    switch ( now.getDayOfWeek() )
    {
    case TUESDAY  : return now.getHour() < 23 ? nextFriday.format ( formatter ) : followingFriday.format ( formatter );
    case WEDNESDAY:
    case THURSDAY : return followingFriday.format ( formatter );
    default       : return nextFriday.format ( formatter );
    }
    
    }

    /**
     *
     */

    static public String getLastDayOfMonth ( String date )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate initial = LocalDate.parse ( date, formatter );

    LocalDate lastDay = initial.with( lastDayOfMonth() );

    return lastDay.format ( formatter );
    }

    /**
     *
     */

    static public String getLastDayOfYear ( String date )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate initial = LocalDate.parse ( date, formatter );

    LocalDate lastDay = initial.with( lastDayOfYear() );

    return lastDay.format ( formatter );
    }

    /**
     *
     */

    static public String getMondayOfDate ( String date, String format )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( format );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    LocalDate firstMonday = localDate.with ( DayOfWeek.MONDAY );

    return firstMonday.format ( formatter );
    }
    
    /**
     * 
     */
    
    static public String getMondayOfDate ( Date date, String format )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( format );

    LocalDate localDate = date.toInstant().atZone ( ZoneId.systemDefault() ).toLocalDate();
    
    LocalDate firstMonday = localDate.with ( DayOfWeek.MONDAY );
    
    return firstMonday.format ( formatter );
    }

    /**
     *
     */

    static public List getMondays ( HttpServletRequest request )
    {
    ArrayList list = new ArrayList(); DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "MMMM d" );

    LocalDate startDate = LocalDate.of ( 2017, 9, 1 ); LocalDate endDate = LocalDate.of ( 2018, 6, 30 );

    LocalDate thisMonday = startDate.with ( TemporalAdjusters.firstInMonth ( DayOfWeek.MONDAY ) );

    while ( thisMonday.isBefore ( endDate ) )

    { list.add ( new LabelValueBean ( Caption.get ( request, "menu.availability.week.of", new String[] { thisMonday.format ( formatter ) } ), thisMonday.toString () ) ); thisMonday = thisMonday.plusWeeks ( 1 ); }

    return list;
    }

    /**
     * 
     */
    
    static public List<String> getMonthsBetween ( String start_date, String end_date, String in_format, String out_format )
    {
    List<String> monthsList = new ArrayList<>(); DateFormat dateFormat = getSimpleDateFormat ( "MMM" );
    
    Date startDate = getDate ( start_date, "yyyy-MM-dd" ); Date endDate = getDate ( end_date, "yyyy-MM-dd" );

    Calendar calendar = Calendar.getInstance(); calendar.setTime ( startDate );

    try {
        
        while ( calendar.getTime().getTime() <= endDate.getTime() )

        { monthsList.add ( dateFormat.format ( calendar.getTime() ) ); calendar.add ( Calendar.MONTH, 1 ); }
    
    } catch ( Exception e ) { Common.trace ( e ); }
    
    finally { return monthsList; }
    }
    
    /**
     *
     */

    static public String getNextDay ( String date )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    localDate = localDate.plusDays ( 1 );

    return localDate.format ( formatter );
    }
    
    /**
     *
     */

    static public String getNextMonth ( String date )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    localDate = localDate.plusMonths ( 1 );

    return localDate.format ( formatter );
    }

    /**
     *
     */

    static public String[] getNextWeek ( String date )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" ); String [] days = new String[] { "", "", "", "", "" };

    LocalDate localDate = LocalDate.parse ( date, formatter );

    localDate = localDate.plusWeeks ( 1 ).with ( DayOfWeek.MONDAY ); days [ 0 ] = localDate.format ( formatter );

    localDate = localDate.with ( DayOfWeek.TUESDAY   ); days [ 1 ] = localDate.format ( formatter );

    localDate = localDate.with ( DayOfWeek.WEDNESDAY ); days [ 2 ] = localDate.format ( formatter );

    localDate = localDate.with ( DayOfWeek.THURSDAY  ); days [ 3 ] = localDate.format ( formatter );

    localDate = localDate.with ( DayOfWeek.FRIDAY    ); days [ 4 ] = localDate.format ( formatter );

    return days;
    }

    /**
     *
     */

    static public String getNextDayofWeek ( String s, int dow )
    {
    if ( DockYard.isWhiteSpace ( s ) ) return "";

    Date date = getDate ( s, "yyyy-MM-dd" );

    Calendar cal = Calendar.getInstance(); cal.setTime ( date );

    int weekday = cal.get ( Calendar.DAY_OF_WEEK );

    int days = ( weekday != dow ) ? ( Calendar.SATURDAY - dow + 3 ) % 7 : 0;

    cal.add ( Calendar.DAY_OF_YEAR, days );

    String format = new SimpleDateFormat ( "yyyy-MM-dd" ).format ( cal.getTime() );

    return format;
    }

    /**
     *
     */

    static public int getNumDays ( String start_date, String end_date )
    {
    try {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

        LocalDate startDate = LocalDate.parse ( start_date, formatter ); LocalDate endDate = LocalDate.parse ( end_date, formatter );

        return DockYard.toInteger ( Duration.between ( startDate.atTime ( 0, 0 ), endDate.atTime ( 0, 0 ) ).toDays() );

    } catch ( Exception e ) { Common.trace ( e ); return 0; }

    }

    /**
     *
     */

    static public String getRegExp ( String start_date, String end_date )
    {
    StringBuilder sb = new StringBuilder();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate startDate = LocalDate.parse ( start_date, formatter ); LocalDate endDate = LocalDate.parse ( end_date, formatter );

    while ( ! startDate.isAfter ( endDate ) )
    {
    if ( sb.length() > 0 ) sb.append ( "|" );

    sb.append ( startDate.format ( formatter ) );

    startDate = startDate.plusDays ( 1 );
    }

    return sb.toString();
    }

    /**
     *
     */

    static public String getSameWeek ( String[] dates, String date )
    {
    if ( DockYard.isWhiteSpace ( date ) || dates == null || dates.length == 0 ) return null;

    int weekNumber = getWeekNumber ( date );

    for ( String d : dates )

    { if ( weekNumber == getWeekNumber ( d ) ) return d; }

    return null;
    }

    /**
     *
     */

    static public SimpleDateFormat getSimpleDateFormat ( String sdf )
    {
    SimpleDateFormat df = new SimpleDateFormat ( sdf );

    df.setTimeZone ( getTimeZone() );

    return df;
    }

    /**
     *
     */

    static public String getSaturdayOfDate ( String date, String format )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( format );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    if ( localDate.getDayOfWeek() == DayOfWeek.SATURDAY ) return localDate.format ( formatter );

    LocalDate saturday = localDate.with ( TemporalAdjusters.next ( DayOfWeek.SATURDAY ) );

    return saturday.format ( formatter );
    }

    /**
     *
     */

    static public String getSundayOfDate ( String date, String format )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( format );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    if ( localDate.getDayOfWeek() == DayOfWeek.SUNDAY ) return localDate.format ( formatter );

    LocalDate sunday = localDate.with ( TemporalAdjusters.previous ( DayOfWeek.SUNDAY ) );

    return sunday.format ( formatter );
    }

    /**
     *
     */

    static public java.util.TimeZone getTimeZone() { return TimeZone.getTimeZone ( Knock.get ( "TIMEZONE" ) ); }

    /**
     *
     */

    static public String getTwoDayDelivery()
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDateTime now = LocalDateTime.now();

    LocalDateTime nextThursday = now.with ( TemporalAdjusters.next ( DayOfWeek.THURSDAY ) );

    LocalDateTime nextTuesday = now.with ( TemporalAdjusters.next ( DayOfWeek.TUESDAY ) );

    LocalDateTime followingTuesday = nextTuesday.with ( TemporalAdjusters.next ( DayOfWeek.TUESDAY ) );

    switch ( now.getDayOfWeek() )
    {
    case THURSDAY  :
    case FRIDAY    :
    case SATURDAY  :
    case SUNDAY    : return nextThursday.format ( formatter );
    case MONDAY    : return followingTuesday.format ( formatter );
    case TUESDAY   :
    case WEDNESDAY : return nextTuesday.format( formatter );
    default        : return nextThursday.format ( formatter );
    }
    
    }

    /**
     *
     */

    static public String getUniqueDaysOfWeek ( String[] dates )
    {
    StringBuilder sb = new StringBuilder(); Set<String> set = new HashSet<String>();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    Arrays.sort ( dates );

    for ( String date : dates )

    { LocalDate localDate = LocalDate.parse ( date, formatter ); set.add ( localDate.getDayOfWeek().toString().substring ( 0, 3 ) ); }

    for ( String day : set )

    { if ( sb.length() > 0 ) sb.append ( ", " ); sb.append ( day ); }

    return sb.toString();
    }

    /**
     *
     */

    static public String getWeekday ( int weekday )
    {
    String dayNames[] = new DateFormatSymbols().getWeekdays();

    return weekday >= Calendar.SUNDAY && weekday <= Calendar.SATURDAY ? dayNames [ weekday ] : "";
    }


    /**
     *
     */

    static public int getWeekNumber ( String date )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate initial = LocalDate.parse ( date, formatter );

    WeekFields weekFields = WeekFields.of ( Locale.getDefault() );

    return initial.get ( weekFields.weekOfWeekBasedYear () );
    }

    /**
     * 
     */
    
    static public List<LabelValueBean> getWeeksOfDates ( HttpServletRequest request, List<Date> dates )
    {
    List<LabelValueBean> weeks = new ArrayList<LabelValueBean>();
    
    Collections.sort ( dates, BY_WEEK ); int current = -1;
    
    for ( Date date: dates )
    {
    int week_of_year = getWeekOfYear ( date );
    
    if ( current != getWeekOfYear ( date ) )
        
    { weeks.add ( new LabelValueBean ( Caption.get ( "week.of", new String[] { getMondayOfDate ( date, "MMM dd" ) } ), "" + week_of_year ) ); current = week_of_year; }
    
    }
    
    return weeks;
    }
    
    /**
     * 
     */
    
    static public int getWeekOfYear ( Date date )
    {
    Calendar calendar = Calendar.getInstance();
    
    calendar.setTime ( date );
    
    return calendar.get ( Calendar.WEEK_OF_YEAR );
    }
    
    /**
     *
     */

    static public boolean isAfter ( String date )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate localDate = LocalDate.parse ( date, formatter ); LocalDate now = LocalDate.now();

    return now.isAfter ( localDate );
    }

    /**
     *
     */

    static public boolean isAfter ( String start_date, String end_date )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate startDate = LocalDate.parse ( start_date, formatter ); LocalDate endDate = LocalDate.parse ( end_date, formatter );

    return startDate.isAfter ( endDate );
    }

    /**
     *
     */

    static public boolean isAfter ( String date, String format, int days )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( format );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    LocalDate now = LocalDate.now(); LocalDate then = now.plusDays ( days );

    return localDate.isAfter ( then );
    }

    /**
     *
     */

    static public boolean isBefore ( String date )

    { return isBefore ( date, "yyyy-MM-dd" ); }

    /**
     *
     */

    static public boolean isBefore ( String date, String format )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( format );

    LocalDate localDate = LocalDate.parse ( date, formatter ); LocalDate now = LocalDate.now();

    return localDate.isBefore ( now );
    }

    /**
     *
     */

    static public boolean isBetween ( String date, String start_date, String end_date )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate localDate = LocalDate.parse ( date, formatter ); LocalDate startDate = LocalDate.parse ( start_date, formatter ); LocalDate endDate = LocalDate.parse ( end_date, formatter );

    return ! ( localDate.isBefore ( startDate ) || localDate.isAfter ( endDate ) );
    }


    /**
     *
     */

    static public boolean isMonday() { return dayOfWeek() == DayOfWeek.MONDAY; }

    /**
     *
     */

    static public boolean isDaysBefore ( int weekday, int days_apart  )

    { return weekday - ( dayOfWeek().getValue() % 7 ) == days_apart; }

    /**
     *
     */

    static public boolean isWeekday ( String date )
    {
    if ( DockYard.isWhiteSpace ( date ) ) return false;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    DayOfWeek dayOfWeek = localDate.getDayOfWeek();

    return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    /**
     *
     */

    static public boolean isWithin ( String date, String format, int days )
    {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( format );

    LocalDate localDate = LocalDate.parse ( date, formatter );

    LocalDate now = LocalDate.now(); LocalDate then = now.plusDays ( days );

    return now.isBefore ( localDate ) && localDate.isBefore ( then );
    }

    /**
     *
     */

    static public String now() { return now ( "yyyy-MM-dd" ); }

    /**
     *
     */

    static public String now ( String format )
    {
    LocalDate localDate = LocalDate.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( format );

    return localDate.format ( formatter );
    }

}
