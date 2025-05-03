package ca.jetsphere.core.tier1.backbone.period;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.CalendarYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.bu.BuYard;

import java.sql.ResultSet;

/**
 *
 */

public class Period extends ca.jetsphere.core.tier0.backbone.period.Period
{
    /**
     *
     */

    public Period() { super(); }

    /**
     *
     */

    public Period ( JDBC jdbc, int period_id ) { super ( jdbc, period_id ); }

    /**
     *
     */

    public Period ( JDBC jdbc, int application_id, String name ) { this(); Bolt.name ( jdbc, this, application_id, name ); }

    /**
     *
     */

    public Period ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "name", "code", "type", "closed", "last.update", "actions" }; }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception

    { BuYard.delete ( jdbc, getId() ); super.delete ( jdbc ); }

    /**
     *
     */

    static public String [] fields()

    { return new String [] { "period_name", "period_code", "period_type_id", "period_closed", "period_last_update", "period_uuid" }; }

    /**
     *
     */

    public String getEndDateAsString    () { return CalendarYard.getDateTimeFormat ( getEndDate  (), "yyyy-MM-dd" ); }
    public String getStartDateAsString  () { return CalendarYard.getDateTimeFormat ( getStartDate(), "yyyy-MM-dd" ); }

    /**
     *
     */

    public void setEndDateAsString    ( String date ) { setEndDate   ( CalendarYard.getDate ( date, "yyyy-MM-dd" ) ); }
    public void setStartDateAsString  ( String date ) { setStartDate ( CalendarYard.getDate ( date, "yyyy-MM-dd" ) ); }

}