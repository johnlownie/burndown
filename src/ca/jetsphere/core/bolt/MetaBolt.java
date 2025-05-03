package ca.jetsphere.core.bolt;

import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.MyActionForm;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Locale;

/**
 *
 */

abstract public class MetaBolt extends MyActionForm
{
    Locale locale;

    /**
     *
     */

    protected String get ( Boolean b )

    { return b.booleanValue() ? "<img src='images/table/red-check.gif'>" : ""; }

    /**
     *
     */

    public Object get ( String s )
    {
    try {

        Field field = getField ( this, s ); field.setAccessible ( true );

        if ( s.endsWith ( "_uuid" ) ) return getEditDeleteBox ( ( String ) field.get ( this ) );

        if ( field.getType() == boolean.class ) return get ( ( Boolean ) field.get ( this ) );

        if ( field.getType() == Timestamp.class ) return CalendarYard.getDateFormatDateTimeMedium ( locale, ( Timestamp ) field.get ( this ) );

        return field.get ( this );

    } catch ( Exception e ) { return s; }

    }

    /**
     *
     */

    public Object get ( String s, Locale locale ) { setLocale ( locale ); return get ( s ); }

    /**
     *
     */

    protected String getCalculator ( String s, String title )
    {
    if ( DockYard.isWhiteSpace ( s ) ) return "";

    return "<a href=\"#anchor\" class=\"add-tooltip calculatorBtn\" data-toggle=\"tooltip\" data-id=\"" + s + "\" data-title=\"" + title + "\" data-container=\"body\" data-original-title=\"Click to View\"><i class=\"fa fa-calculator fa-lg\"></i></a>";
    }

    /**
     *
     */

    protected String getCalendar ( String s, String title )
    {
    if ( DockYard.isWhiteSpace ( s ) ) return "";

    return "<a href=\"#anchor\" class=\"add-tooltip calendarBtn\" data-toggle=\"tooltip\" data-id=\"" + s + "\" data-title=\"" + title + "\" data-container=\"body\" data-original-title=\"Click to View\"><i class=\"fa fa-calendar fa-lg\"></i></a>";
    }

    /**
     *
     */

    protected String getList ( String s, String title )
    {
    if ( DockYard.isWhiteSpace ( s ) ) return "";

    return "<a href=\"#anchor\" class=\"add-tooltip listBtn\" data-toggle=\"tooltip\" data-id=\"" + s + "\" data-title=\"" + title + "\" data-container=\"body\" data-original-title=\"Click to View\"><i class=\"fa fa-list-ul fa-lg\"></i></a>";
    }

    /**
     *
     */

    protected String getList ( String s, String title, int size )
    {
    if ( DockYard.isWhiteSpace ( s ) ) return "";

    return "<a href=\"#anchor\" class=\"add-tooltip listBtn\" data-toggle=\"tooltip\" data-id=\"" + s + "\" data-title=\"" + title + "\" data-container=\"body\" data-original-title=\"Click to View\"><i class=\"fa fa-list-ul fa-lg\"> [" + size + "]</i></a>";
    }

    /**
     *
     */

    protected String getDeleteBox ( String s )
    {
    if ( DockYard.isWhiteSpace ( s ) ) return "";

    return "<button type=\"button\" class=\"btn btn-sm btn-danger add-tooltip deleteBtn\" data-toggle=\"tooltip\" data-placement=\"top\" data-id=\"" + s + "\" data-original-title=\"Delete\" data-container=\"body\"><i class=\"fa fa-times\"></i></button>";
    }

    /**
     *
     */

    protected String getEditClearBox ( String s )
    {
    if ( DockYard.isWhiteSpace ( s ) ) return "";

    return

    "<button type=\"button\" class=\"btn btn-sm btn-default add-tooltip editBtn\" data-toggle=\"tooltip\" data-placement=\"top\" data-id=\"" + s + "\" data-original-title=\"Edit\" data-container=\"body\"><i class=\"fa fa-pencil\"></i></button>" +

    "<button type=\"button\" class=\"btn btn-sm btn-purple add-tooltip clearBtn\" data-toggle=\"tooltip\" data-placement=\"top\" data-id=\"" + s + "\" data-original-title=\"Clear\" data-container=\"body\"><i class=\"fa fa-eraser\"></i></button>";
    }

    /**
     *
     */

    protected String getEditDeleteBox ( String s )
    {
    if ( DockYard.isWhiteSpace ( s ) ) return "";

    return

    "<button type=\"button\" class=\"btn btn-sm btn-default add-tooltip editBtn\" data-toggle=\"tooltip\" data-placement=\"top\" data-id=\"" + s + "\" data-original-title=\"Edit\" data-container=\"body\"><i class=\"fa fa-pencil\"></i></button>" +

    "<button type=\"button\" class=\"btn btn-sm btn-danger add-tooltip deleteBtn\" data-toggle=\"tooltip\" data-placement=\"top\" data-id=\"" + s + "\" data-original-title=\"Delete\" data-container=\"body\"><i class=\"fa fa-times\"></i></button>";
    }

    /**
     *
     */

    protected String getEditCopyDeleteBox ( String s )
    {
    if ( DockYard.isWhiteSpace ( s ) ) return "";

    return

    "<button type=\"button\" class=\"btn btn-sm btn-default add-tooltip editBtn\" data-toggle=\"tooltip\" data-id=\"" + s + "\" data-original-title=\"Edit\" data-container=\"body\"><i class=\"fa fa-pencil\"></i></button>" +

    "<button type=\"button\" class=\"btn btn-sm btn-info add-tooltip copyBtn\" data-toggle=\"tooltip\" data-id=\"" + s + "\" data-original-title=\"Copy\" data-container=\"body\"><i class=\"fa fa-copy\"></i></button>" +

    "<button type=\"button\" class=\"btn btn-sm btn-danger add-tooltip deleteBtn\" data-toggle=\"tooltip\" data-id=\"" + s + "\" data-original-title=\"Delete\" data-container=\"body\"><i class=\"fa fa-times\"></i></button>";
    }

    /**
     *
     */

    public Field getField ( Object o, String s )
    {
    Class c = o.getClass();

    while ( c != null )

        try {

            return c.getDeclaredField ( s );

        } catch ( Exception e ) { c = c.getSuperclass(); }

    return null;
    }

    /**
     *
     */

    public Locale getLocale() { return locale; }

    /**
     *
     */

    abstract public String getKey();

    /**
     *
     */

    protected String getUrl ( Integer i )
    {
    if ( DockYard.isWhiteSpace ( DockYard.toString ( i ) ) ) return "";

    return "<a class=\"pencil\" href=\"#" + i + "\"><img src=\"images/table/empty.gif\" ></a >";
    }

    /**
     *
     */

    protected String getUrl ( String s )
    {
    if ( DockYard.isWhiteSpace ( s ) ) return "";

    return "<a class=\"pencil\" href=\"#" + s + "\"><img src=\"images/table/empty.gif\" ></a >";
    }

    /**
     *
     */

    public void set ( String s, Object o )
    {
    try {

        Field field = getField ( this, s );

        field.setAccessible ( true );

        field.set ( this, o );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    }

    /**
     *
     */

    public void setLocale ( Locale locale ) { this.locale = locale; }

}