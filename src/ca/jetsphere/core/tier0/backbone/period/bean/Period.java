package ca.jetsphere.core.tier0.backbone.period.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 */

abstract public class Period extends Bolt implements IBean
{
    int       period_id             ;
    String    period_uuid           ;
    int       period_application_id ;
    String    period_name           ;
    String    period_code           ;
    Date      period_start_date     ;
    Date      period_end_date       ;
    String    period_notes          ;
    int       period_type_id        ;
    int       period_ordinal        ;
    boolean   period_closed         ;
    Timestamp period_last_update    ;
    Timestamp period_created        ;

    /**
     *
     */

    public void clear()
    {
    setId            (  -1   );
    setUuid          (  ""   );
    setApplicationId (  -1   );
    setName          (  ""   );
    setCode          (  ""   );
    setStartDate     (  null );
    setEndDate       (  null );
    setNotes         (  ""   );
    setTypeId        (  -1   );
    setOrdinal       (  -1   );
    setClosed        ( false );
    setLastUpdate    ( ( Timestamp ) null );
    setCreated       ( null );
    }

    /**
     *
     */

    public void copy ( Bolt period )
    {
    setId            ( ( ( Period ) period ).getId           () );
    setUuid          ( ( ( Period ) period ).getUuid         () );
    setApplicationId ( ( ( Period ) period ).getApplicationId() );
    setName          ( ( ( Period ) period ).getName         () );
    setCode          ( ( ( Period ) period ).getCode         () );
    setStartDate     ( ( ( Period ) period ).getStartDate    () );
    setEndDate       ( ( ( Period ) period ).getEndDate      () );
    setNotes         ( ( ( Period ) period ).getNotes        () );
    setTypeId        ( ( ( Period ) period ).getTypeId       () );
    setOrdinal       ( ( ( Period ) period ).getOrdinal      () );
    setClosed        ( ( ( Period ) period ).getClosed       () );
    setLastUpdate    ( ( ( Period ) period ).getLastUpdate   () );
    setCreated       ( ( ( Period ) period ).getCreated      () );
    }

    /**
     *
     */

    public void copy ( Period period )
    {
    setId            ( period.getId            () );
    setUuid          ( period.getUuid          () );
    setApplicationId ( period.getApplicationId () );
    setName          ( period.getName          () );
    setCode          ( period.getCode          () );
    setStartDate     ( period.getStartDate     () );
    setEndDate       ( period.getEndDate       () );
    setNotes         ( period.getNotes         () );
    setTypeId        ( period.getTypeId        () );
    setOrdinal       ( period.getOrdinal       () );
    setClosed        ( period.getClosed        () );
    setLastUpdate    ( period.getLastUpdate    () );
    setCreated       ( period.getCreated       () );
    }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId            ( rs.getInt       ( "period_id"             ) );
    setUuid          ( rs.getString    ( "period_uuid"           ) );
    setApplicationId ( rs.getInt       ( "period_application_id" ) );
    setName          ( rs.getString    ( "period_name"           ) );
    setCode          ( rs.getString    ( "period_code"           ) );
    setStartDate     ( rs.getDate      ( "period_start_date"     ) );
    setEndDate       ( rs.getDate      ( "period_end_date"       ) );
    setNotes         ( rs.getString    ( "period_notes"          ) );
    setTypeId        ( rs.getInt       ( "period_type_id"        ) );
    setOrdinal       ( rs.getInt       ( "period_ordinal"        ) );
    setClosed        ( rs.getBoolean   ( "period_closed"         ) );
    setLastUpdate    ( rs.getTimestamp ( "period_last_update"    ) );
    setCreated       ( rs.getTimestamp ( "period_created"        ) );
    }

    /**
     *
     */

    static public String key() { return "period"; }

    /**
     *
     */

    public int       getId            () { return period_id             ; }
    public String    getUuid          () { return period_uuid           ; }
    public int       getApplicationId () { return period_application_id ; }
    public String    getName          () { return period_name           ; }
    public String    getCode          () { return period_code           ; }
    public Date      getStartDate     () { return period_start_date     ; }
    public Date      getEndDate       () { return period_end_date       ; }
    public String    getNotes         () { return period_notes          ; }
    public int       getTypeId        () { return period_type_id        ; }
    public int       getOrdinal       () { return period_ordinal        ; }
    public boolean   getClosed        () { return period_closed         ; }
    public Timestamp getLastUpdate    () { return period_last_update    ; }
    public Timestamp getCreated       () { return period_created        ; }

    /**
     *
     */

    public void setId            ( int       period_id             ) { this.period_id             = period_id             ; }
    public void setUuid          ( String    period_uuid           ) { this.period_uuid           = period_uuid           ; }
    public void setApplicationId ( int       period_application_id ) { this.period_application_id = period_application_id ; }
    public void setName          ( String    period_name           ) { this.period_name           = period_name           ; }
    public void setCode          ( String    period_code           ) { this.period_code           = period_code           ; }
    public void setStartDate     ( Date      period_start_date     ) { this.period_start_date     = period_start_date     ; }
    public void setEndDate       ( Date      period_end_date       ) { this.period_end_date       = period_end_date       ; }
    public void setNotes         ( String    period_notes          ) { this.period_notes          = period_notes          ; }
    public void setTypeId        ( int       period_type_id        ) { this.period_type_id        = period_type_id        ; }
    public void setOrdinal       ( int       period_ordinal        ) { this.period_ordinal        = period_ordinal        ; }
    public void setClosed        ( boolean   period_closed         ) { this.period_closed         = period_closed         ; }
    public void setLastUpdate    ( Timestamp period_last_update    ) { this.period_last_update    = period_last_update    ; }
    public void setCreated       ( Timestamp period_created        ) { this.period_created        = period_created        ; }

}
