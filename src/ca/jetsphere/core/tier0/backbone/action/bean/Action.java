package ca.jetsphere.core.tier0.backbone.action.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

abstract public class Action extends Bolt implements IBean
{
    int       action_id          ;
    String    action_uuid        ;
    int       action_company_id  ;
    String    action_name        ;
    String    action_href        ;
    String    action_tooltip     ;
    String    action_notes       ;
    boolean   action_public      ;
    Timestamp action_last_update ;
    Timestamp action_created     ;

    /**
     *
     */

    public void clear()
    {
    setId         (  -1   );
    setUuid       (  ""   );
    setCompanyId  (  -1   );
    setName       (  ""   );
    setHref       (  ""   );
    setToolTip    (  ""   );
    setNotes      (  ""   );
    setPublic     ( false );
    setLastUpdate ( null  );
    setCreated    ( null  );
    }

    /**
     *
     */

    public void copy ( Bolt action )
    {
    setId         ( ( ( Action ) action ).getId        () );
    setUuid       ( ( ( Action ) action ).getUuid      () );
    setCompanyId  ( ( ( Action ) action ).getCompanyId () );
    setName       ( ( ( Action ) action ).getName      () );
    setHref       ( ( ( Action ) action ).getHref      () );
    setToolTip    ( ( ( Action ) action ).getToolTip   () );
    setNotes      ( ( ( Action ) action ).getNotes     () );
    setPublic     ( ( ( Action ) action ).getPublic    () );
    setLastUpdate ( ( ( Action ) action ).getLastUpdate() );
    setCreated    ( ( ( Action ) action ).getCreated   () );
    }

    /**
     *
     */

    public int       getId         () { return action_id          ; }
    public String    getUuid       () { return action_uuid        ; }
    public int       getCompanyId  () { return action_company_id  ; }
    public String    getName       () { return action_name        ; }
    public String    getHref       () { return action_href        ; }
    public String    getToolTip    () { return action_tooltip     ; }
    public String    getNotes      () { return action_notes       ; }
    public boolean   getPublic     () { return action_public      ; }
    public Timestamp getLastUpdate () { return action_last_update ; }
    public Timestamp getCreated    () { return action_created     ; }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "action_id"          ) );
    setUuid       ( rs.getString    ( "action_uuid"        ) );
    setCompanyId  ( rs.getInt       ( "action_company_id"  ) );
    setName       ( rs.getString    ( "action_name"        ) );
    setHref       ( rs.getString    ( "action_href"        ) );
    setToolTip    ( rs.getString    ( "action_tooltip"     ) );
    setNotes      ( rs.getString    ( "action_notes"       ) );
    setPublic     ( rs.getBoolean   ( "action_public"      ) );
    setLastUpdate ( rs.getTimestamp ( "action_last_update" ) );
    setCreated    ( rs.getTimestamp ( "action_created"     ) );
    }

    /**
     *
     */

    static public String key() { return "action"; }

    /**
     *
     */

    public void setId         ( int       action_id          ) { this.action_id          = action_id          ; }
    public void setUuid       ( String    action_uuid        ) { this.action_uuid        = action_uuid        ; }
    public void setCompanyId  ( int       action_company_id  ) { this.action_company_id  = action_company_id  ; }
    public void setName       ( String    action_name        ) { this.action_name        = action_name        ; }
    public void setHref       ( String    action_href        ) { this.action_href        = action_href        ; }
    public void setToolTip    ( String    action_tooltip     ) { this.action_tooltip     = action_tooltip     ; }
    public void setNotes      ( String    action_notes       ) { this.action_notes       = action_notes       ; }
    public void setPublic     ( boolean   action_public      ) { this.action_public      = action_public      ; }
    public void setLastUpdate ( Timestamp action_last_update ) { this.action_last_update = action_last_update ; }
    public void setCreated    ( Timestamp action_created     ) { this.action_created     = action_created     ; }

}
