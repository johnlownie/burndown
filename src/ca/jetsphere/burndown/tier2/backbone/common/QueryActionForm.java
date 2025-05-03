package ca.jetsphere.burndown.tier2.backbone.common;

import ca.jetsphere.core.common.CalendarYard;

import java.util.Date;

/**
 *
 */
public class QueryActionForm extends ca.jetsphere.core.tier2.common.QueryActionForm
{
    int     query_form_period_id  ;
    int     query_form_status_id  ;
    int     query_form_type_id    ;
    boolean query_form_toggle     ;
    boolean query_form_yes_no     ;
    String  query_form_day        ;
    Date    query_form_start_date ;
    Date    query_form_end_date   ;

    /**
     *
     */
    public void clear()
    {
    setPeriodId  (  -1   );
    setStatusId  (   1   );
    setTypeId    (  -1   );
    setToggle    ( false );
    setYesNo     ( false );
    setDay       (  ""   );
    setStartDate ( null  );
    setEndDate   ( null  );

    super.clear();
    }

    /**
     *
     */
    public String getKey() { return "bb_query_form"; }

    /**
     *
     */
    public int     getPeriodId  () { return query_form_period_id  ; }
    public int     getStatusId  () { return query_form_status_id  ; }
    public int     getTypeId    () { return query_form_type_id    ; }
    public boolean getToggle    () { return query_form_toggle     ; }
    public boolean getYesNo     () { return query_form_yes_no     ; }
    public String  getDay       () { return query_form_day        ; }
    public Date    getStartDate () { return query_form_start_date ; }
    public Date    getEndDate   () { return query_form_end_date   ; }

    /**
     *
     */
    public String getStartDateAsString() { return CalendarYard.getDateTimeFormat ( getStartDate(), "yyyy-MM-dd" ); }
    public String getEndDateAsString  () { return CalendarYard.getDateTimeFormat ( getEndDate  (), "yyyy-MM-dd" ); }

    /**
     *
     */

//    public void reset ( ActionMapping mapping, HttpServletRequest request ) { clear(); }

    /**
     *
     */
    public void setPeriodId  ( int     query_form_period_id  ) { this.query_form_period_id  = query_form_period_id  ; }
    public void setStatusId  ( int     query_form_status_id  ) { this.query_form_status_id  = query_form_status_id  ; }
    public void setTypeId    ( int     query_form_type       ) { this.query_form_type_id    = query_form_type       ; }
    public void setToggle    ( boolean query_form_toggle     ) { this.query_form_toggle     = query_form_toggle     ; }
    public void setYesNo     ( boolean query_form_yes_no     ) { this.query_form_yes_no     = query_form_yes_no     ; }
    public void setDay       ( String  query_form_day        ) { this.query_form_day        = query_form_day        ; }
    public void setStartDate ( Date    query_form_start_date ) { this.query_form_start_date = query_form_start_date ; }
    public void setEndDate   ( Date    query_form_end_date   ) { this.query_form_end_date   = query_form_end_date   ; }

    /**
     *
     */
    public void setStartDateAsString ( String date ) { setStartDate ( CalendarYard.getDate ( date, date != null && date.length() == 7 ? "yyyy-MM" : "yyyy-MM-dd" ) ); }
    public void setEndDateAsString   ( String date ) { setEndDate   ( CalendarYard.getDate ( date, "yyyy-MM-dd" ) ); }
}
