package ca.jetsphere.core.tier0.backbone.bu.bean;

import java.sql.ResultSet;
import java.sql.Timestamp;

import ca.jetsphere.core.tier1.tree.Tree;

/**
 *
 */

abstract public class Bu extends Tree implements Comparable
{

    int       bu_id          ;
    String    bu_uuid        ;
    int       bu_period_id   ;
    int       bu_parent_id   ;
    String    bu_parent_uuid ;
    int       bu_depth       ;
    String    bu_lineage     ;
    int       bu_ordinal     ;
    int       bu_type_id     ;
    String    bu_name        ;
    String    bu_short_name  ;
    String    bu_address     ;
    String    bu_city        ;
    String    bu_province    ;
    String    bu_postal_code ;
    String    bu_phone       ;
    String    bu_fax         ;
    String    bu_url         ;
    String    bu_notes       ;
    Timestamp bu_last_update ;
    Timestamp bu_created     ;

    /**
     *
     */

    public void clear()
    {
    setId         (  -1  );
    setUuid       (  ""  );
    setPeriodId   (  -1  );
    setParentId   (  -1  );
    setParentUuid (  ""  );
    setDepth      (  -1  );
    setLineage    (  ""  );
    setOrdinal    (  -1  );
    setTypeId     (  -1  );
    setName       (  ""  );
    setShortName  (  ""  );
    setAddress    (  ""  );
    setCity       (  ""  );
    setProvince   (  ""  );
    setPostalCode (  ""  );
    setPhone      (  ""  );
    setFax        (  ""  );
    setUrl        (  ""  );
    setNotes      (  ""  );
    setLastUpdate ( null );
    setCreated    ( null );
    }

    /**
     *
     */

    public void copy ( Bu bu )
    {
    setId         ( bu.getId         () );
    setUuid       ( bu.getUuid       () );
    setPeriodId   ( bu.getPeriodId   () );
    setParentId   ( bu.getParentId   () );
    setParentUuid ( bu.getParentUuid () );
    setDepth      ( bu.getDepth      () );
    setLineage    ( bu.getLineage    () );
    setOrdinal    ( bu.getOrdinal    () );
    setTypeId     ( bu.getTypeId     () );
    setName       ( bu.getName       () );
    setShortName  ( bu.getShortName  () );
    setAddress    ( bu.getAddress    () );
    setCity       ( bu.getCity       () );
    setProvince   ( bu.getProvince   () );
    setPostalCode ( bu.getPostalCode () );
    setPhone      ( bu.getPhone      () );
    setFax        ( bu.getFax        () );
    setUrl        ( bu.getUrl        () );
    setNotes      ( bu.getNotes      () );
    setLastUpdate ( bu.getLastUpdate () );
    setCreated    ( bu.getCreated    () );
    }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "bu_id"          ) );
    setUuid       ( rs.getString    ( "bu_uuid"        ) );
    setPeriodId   ( rs.getInt       ( "bu_period_id"   ) );
    setParentId   ( rs.getInt       ( "bu_parent_id"   ) );
    setParentUuid ( rs.getString    ( "bu_parent_uuid" ) );
    setDepth      ( rs.getInt       ( "bu_depth"       ) );
    setLineage    ( rs.getString    ( "bu_lineage"     ) );
    setOrdinal    ( rs.getInt       ( "bu_ordinal"     ) );
    setTypeId     ( rs.getInt       ( "bu_type_id"     ) );
    setName       ( rs.getString    ( "bu_name"        ) );
    setShortName  ( rs.getString    ( "bu_short_name"  ) );
    setAddress    ( rs.getString    ( "bu_address"     ) );
    setCity       ( rs.getString    ( "bu_city"        ) );
    setProvince   ( rs.getString    ( "bu_province"    ) );
    setPostalCode ( rs.getString    ( "bu_postal_code" ) );
    setPhone      ( rs.getString    ( "bu_phone"       ) );
    setFax        ( rs.getString    ( "bu_fax"         ) );
    setUrl        ( rs.getString    ( "bu_url"         ) );
    setNotes      ( rs.getString    ( "bu_notes"       ) );
    setLastUpdate ( rs.getTimestamp ( "bu_last_update" ) );
    setCreated    ( rs.getTimestamp ( "bu_created"     ) );
    }

    /**
     *
     */

    static public String key() { return "bu"; }

    /**
     *
     */

    public int       getId         () { return bu_id          ; }
    public String    getUuid       () { return bu_uuid        ; }
    public int       getPeriodId   () { return bu_period_id   ; }
    public int       getParentId   () { return bu_parent_id   ; }
    public String    getParentUuid () { return bu_parent_uuid ; }
    public int       getDepth      () { return bu_depth       ; }
    public String    getLineage    () { return bu_lineage     ; }
    public int       getOrdinal    () { return bu_ordinal     ; }
    public int       getTypeId     () { return bu_type_id     ; }
    public String    getName       () { return bu_name        ; }
    public String    getShortName  () { return bu_short_name  ; }
    public String    getAddress    () { return bu_address     ; }
    public String    getCity       () { return bu_city        ; }
    public String    getProvince   () { return bu_province    ; }
    public String    getPostalCode () { return bu_postal_code ; }
    public String    getPhone      () { return bu_phone       ; }
    public String    getFax        () { return bu_fax         ; }
    public String    getUrl        () { return bu_url         ; }
    public String    getNotes      () { return bu_notes       ; }
    public Timestamp getLastUpdate () { return bu_last_update ; }
    public Timestamp getCreated    () { return bu_created     ; }

    /**
     *
     */

    public void setId         ( int       bu_id          ) { this.bu_id          = bu_id          ; }
    public void setUuid       ( String    bu_uuid        ) { this.bu_uuid        = bu_uuid        ; }
    public void setPeriodId   ( int       bu_period_id   ) { this.bu_period_id   = bu_period_id   ; }
    public void setParentId   ( int       bu_parent_id   ) { this.bu_parent_id   = bu_parent_id   ; }
    public void setParentUuid ( String    bu_parent_uuid ) { this.bu_parent_uuid = bu_parent_uuid ; }
    public void setDepth      ( int       bu_depth       ) { this.bu_depth       = bu_depth       ; }
    public void setLineage    ( String    bu_lineage     ) { this.bu_lineage     = bu_lineage     ; }
    public void setOrdinal    ( int       bu_ordinal     ) { this.bu_ordinal     = bu_ordinal     ; }
    public void setTypeId     ( int       bu_type_id     ) { this.bu_type_id     = bu_type_id     ; }
    public void setName       ( String    bu_name        ) { this.bu_name        = bu_name        ; }
    public void setShortName  ( String    bu_short_name  ) { this.bu_short_name  = bu_short_name  ; }
    public void setAddress    ( String    bu_address     ) { this.bu_address     = bu_address     ; }
    public void setCity       ( String    bu_city        ) { this.bu_city        = bu_city        ; }
    public void setProvince   ( String    bu_province    ) { this.bu_province    = bu_province    ; }
    public void setPostalCode ( String    bu_postal_code ) { this.bu_postal_code = bu_postal_code ; }
    public void setPhone      ( String    bu_phone       ) { this.bu_phone       = bu_phone       ; }
    public void setFax        ( String    bu_fax         ) { this.bu_fax         = bu_fax         ; }
    public void setUrl        ( String    bu_url         ) { this.bu_url         = bu_url         ; }
    public void setNotes      ( String    bu_notes       ) { this.bu_notes       = bu_notes       ; }
    public void setLastUpdate ( Timestamp bu_last_update ) { this.bu_last_update = bu_last_update ; }
    public void setCreated    ( Timestamp bu_created     ) { this.bu_created     = bu_created     ; }

}
