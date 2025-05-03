package ca.jetsphere.core.tier2.calendar;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier2.common.AbstractAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;

import ca.jetsphere.core.tier1.common.calendar_event.CalendarEvent;

import com.google.gson.Gson;

import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class CalendarDoAction extends AbstractAction
{
    /**
     *
     */

    public String getKey() { return CalendarEvent.key(); }

    /**
     *
     */

    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    List list = new ArrayList();

    CalendarEvent calendarEvent1 = new CalendarEvent();
    calendarEvent1.setId    ( 1                  );
    calendarEvent1.setStart ( "2015-02-28"       );
    calendarEvent1.setEnd   ( "2015-02-29"       );
    calendarEvent1.setTitle ( "Task in Progress" );

    CalendarEvent calendarEvent2 = new CalendarEvent();
    calendarEvent2.setId    ( 2                  );
    calendarEvent2.setStart ( "2015-03-26"       );
    calendarEvent2.setEnd   ( "2015-05-28"       );
    calendarEvent2.setTitle ( "Task in Progress" );

    list.add ( calendarEvent1 );
    list.add ( calendarEvent2 );

    store.getResponse().setContentType ( "application/json" );
    store.getResponse().setCharacterEncoding ( "UTF-8" );
    PrintWriter out = store.getResponse().getWriter();
    out.write ( new Gson().toJson ( list ) );

    return null;
    }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception

    { return getForward ( store ); }

    }
