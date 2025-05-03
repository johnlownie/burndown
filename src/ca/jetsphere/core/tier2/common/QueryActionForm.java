package ca.jetsphere.core.tier2.common;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.IBean;
import ca.jetsphere.core.tier1.tree.OpenSet;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.Timestamp;
import org.apache.struts.upload.FormFile;

/**
 *
 */

public class QueryActionForm extends Bolt implements IBean
{
    int      query_form_application_id    ;
    int      query_form_bu_id             ;
    int      query_form_bu_member_id      ;
    int      query_form_bu_set_id         ;
    int      query_form_company_id        ;
    int      query_form_company_member_id ;
    int      query_form_period_id         ;
    int      query_form_role_id           ;
    int      query_form_user_id           ;

    String   query_form_command           ;
    String   query_form_copy              ;
    String   query_form_delete            ;
    String   query_form_edit              ;
    String   query_form_export            ;
    String   query_form_fetch             ;
    String   query_form_role              ;
    String[] query_form_selected          ;

    int      query_form_dt_draw           ;
    int      query_form_dt_count          ;
    int      query_form_dt_start          ;
    int      query_form_dt_length         ;
    int      query_form_dt_filtered       ;
    int      query_form_dt_order_column   ;
    String   query_form_dt_order_dir      ;
    String   query_form_dt_search         ;
    String   query_form_dt_filter         ;

    OpenSet  query_form_open_set          ;
    FormFile query_form_form_file         ;

    /**
     *
     */

    public void clear()
    {
    setApplicationId   ( -1 );
    setBuId            ( -1 );
    setBuMemberId      ( -1 );
    setBuSetId         ( -1 );
    setCompanyId       ( -1 );
    setCompanyMemberId ( -1 );
    setPeriodId        ( -1 );
    setRoleId          ( -1 );
    setUserId          ( -1 );

    setCommand         ( "" );
    setCopy            ( "" );
    setDelete          ( "" );
    setEdit            ( "" );
    setExport          ( "" );
    setFetch           ( "" );
    setRole            ( "" );
    setSelected        ( new String[] {} );

    setDtDraw          ( -1 );
    setDtCount         ( -1 );
    setDtStart         ( -1 );
    setDtLength        ( -1 );
    setDtFiltered      ( -1 );
    setDtOrderColumn   ( -1 );
    setDtOrderDir      ( "" );
    setDtSearch        ( "" );
    setDtFilter        ( "" );

    setOpenSet         ( new OpenSet() );
    setFormFile        ( null );
    }

    /**
     *
     */

    static public String key() { return "bb_query"; }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception {}


    /**
     *
     */

    public void setDts ( HttpServletRequest request )
    {
    setDtDraw ( DockYard.toInteger ( request, "draw" ) );

    setDtStart ( getDtDraw() > 0 ? DockYard.toInteger ( request, "start"  ) : 0 );

    setDtLength ( DockYard.toInteger ( request, "length" ) );

//    setDtLength ( getDtDraw() > 0 ? Math.min ( getDtCount() - getDtStart(), getDtLength() ) : getDtCount() );

    setDtOrderColumn ( DockYard.toInteger ( request, "order[0][column]" ) );

    setDtOrderDir ( DockYard.getParameter ( request, "order[0][dir]" ) );

    setDtSearch ( DockYard.getParameter ( request, "search[value]" ) );
    }

    /**
     *
     */

    public int       getId              () { return -1                           ; }

    public int       getApplicationId   () { return query_form_application_id    ; }
    public int       getBuId            () { return query_form_bu_id             ; }
    public int       getBuMemberId      () { return query_form_bu_member_id      ; }
    public int       getBuSetId         () { return query_form_bu_set_id         ; }
    public int       getCompanyId       () { return query_form_company_id        ; }
    public int       getCompanyMemberId () { return query_form_company_member_id ; }
    public int       getPeriodId        () { return query_form_period_id         ; }
    public int       getRoleId          () { return query_form_role_id           ; }
    public int       getUserId          () { return query_form_user_id           ; }

    public String    getCommand         () { return query_form_command           ; }
    public String    getCopy            () { return query_form_copy              ; }
    public String    getDelete          () { return query_form_delete            ; }
    public String    getEdit            () { return query_form_edit              ; }
    public String    getExport          () { return query_form_export            ; }
    public String    getFetch           () { return query_form_fetch             ; }
    public String    getRole            () { return query_form_role              ; }
    public String[]  getSelected        () { return query_form_selected          ; }

    public int       getDtDraw          () { return query_form_dt_draw           ; }
    public int       getDtCount         () { return query_form_dt_count          ; }
    public int       getDtStart         () { return query_form_dt_start          ; }
    public int       getDtLength        () { return query_form_dt_length         ; }
    public int       getDtFiltered      () { return query_form_dt_filtered       ; }
    public int       getDtOrderColumn   () { return query_form_dt_order_column   ; }
    public String    getDtOrderDir      () { return query_form_dt_order_dir      ; }
    public String    getDtSearch        () { return query_form_dt_search         ; }
    public String    getDtFilter        () { return query_form_dt_filter         ; }

    public OpenSet   getOpenSet         () { return query_form_open_set          ; }
    public FormFile  getFormFile        () { return query_form_form_file         ; }

    public Timestamp getLastUpdate      () { return null                         ; }
    public Timestamp getCreated         () { return null                         ; }

    /**
     *
     */

    public void setApplicationId   ( int      query_form_application_id    ) { this.query_form_application_id    = query_form_application_id    ; }
    public void setBuId            ( int      query_form_bu_id             ) { this.query_form_bu_id             = query_form_bu_id             ; }
    public void setBuMemberId      ( int      query_form_bu_member_id      ) { this.query_form_bu_member_id      = query_form_bu_member_id      ; }
    public void setBuSetId         ( int      query_form_bu_set_id         ) { this.query_form_bu_set_id         = query_form_bu_set_id         ; }
    public void setCompanyId       ( int      query_form_company_id        ) { this.query_form_company_id        = query_form_company_id        ; }
    public void setCompanyMemberId ( int      query_form_company_member_id ) { this.query_form_company_member_id = query_form_company_member_id ; }
    public void setPeriodId        ( int      query_form_period_id         ) { this.query_form_period_id         = query_form_period_id         ; }
    public void setRoleId          ( int      query_form_role_id           ) { this.query_form_role_id           = query_form_role_id           ; }
    public void setUserId          ( int      query_form_user_id           ) { this.query_form_user_id           = query_form_user_id           ; }

    public void setCommand         ( String   query_form_command           ) { this.query_form_command           = query_form_command           ; }
    public void setCopy            ( String   query_form_copy              ) { this.query_form_copy              = query_form_copy              ; }
    public void setDelete          ( String   query_form_delete            ) { this.query_form_delete            = query_form_delete            ; }
    public void setEdit            ( String   query_form_edit              ) { this.query_form_edit              = query_form_edit              ; }
    public void setExport          ( String   query_form_export            ) { this.query_form_export            = query_form_export            ; }
    public void setFetch           ( String   query_form_fetch             ) { this.query_form_fetch             = query_form_fetch             ; }
    public void setRole            ( String   query_form_role              ) { this.query_form_role              = query_form_role              ; }
    public void setSelected        ( String[] query_form_selected          ) { this.query_form_selected          = query_form_selected          ; }

    public void setDtDraw          ( int      query_form_dt_draw           ) { this.query_form_dt_draw           = query_form_dt_draw           ; }
    public void setDtCount         ( int      query_form_dt_count          ) { this.query_form_dt_count          = query_form_dt_count          ; }
    public void setDtStart         ( int      query_form_dt_start          ) { this.query_form_dt_start          = query_form_dt_start          ; }
    public void setDtLength        ( int      query_form_dt_length         ) { this.query_form_dt_length         = query_form_dt_length         ; }
    public void setDtFiltered      ( int      query_form_dt_filtered       ) { this.query_form_dt_filtered       = query_form_dt_filtered       ; }
    public void setDtOrderColumn   ( int      query_form_dt_order_column   ) { this.query_form_dt_order_column   = query_form_dt_order_column   ; }
    public void setDtOrderDir      ( String   query_form_dt_order_dir      ) { this.query_form_dt_order_dir      = query_form_dt_order_dir      ; }
    public void setDtSearch        ( String   query_form_dt_search         ) { this.query_form_dt_search         = query_form_dt_search         ; }
    public void setDtFilter        ( String   query_form_dt_filter         ) { this.query_form_dt_filter         = query_form_dt_filter         ; }

    public void setOpenSet         ( OpenSet  query_form_open_set          ) { this.query_form_open_set          = query_form_open_set          ; }
    public void setFormFile        ( FormFile query_form_form_file         ) { this.query_form_form_file         = query_form_form_file         ; }

    public void setLastUpdate      ( Timestamp lastUpdate ) {}
    public void setCreated         ( Timestamp created    ) {}
}
