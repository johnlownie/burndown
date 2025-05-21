package ca.jetsphere.burndown.tier0.backbone.category.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.tier1.tree.Tree;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */
abstract public class Category extends Tree
{
    int       category_id             ;
    String    category_uuid           ;
    int       category_application_id ;
    int       category_parent_id      ;
    String    category_parent_uuid    ;
    int       category_depth          ;
    String    category_lineage        ;
    int       category_ordinal        ;
    String    category_name           ;
    boolean   category_included       ;
    boolean   category_discretionary  ;
    Timestamp category_last_update    ;
    Timestamp category_created        ;

    /**
     *
     */
    public void clear()
    {
    setId            (  -1   );
    setUuid          (  ""   );
    setApplicationId (  -1   );
    setParentId      (  -1   );
    setParentUuid    (  ""   );
    setDepth         (   0   );
    setLineage       (  ""   );
    setOrdinal       (  -1   );
    setName          (  ""   );
    setIncluded      ( false );
    setDiscretionary ( false );
    setLastUpdate    ( null  );
    setCreated       ( null  );
    }

    /**
     *
     */
    public void copy ( Bolt category )
    {
    setId            ( ( ( Category ) category ).getId            () );
    setUuid          ( ( ( Category ) category ).getUuid          () );
    setApplicationId ( ( ( Category ) category ).getApplicationId () );
    setParentId      ( ( ( Category ) category ).getParentId      () );
    setParentUuid    ( ( ( Category ) category ).getParentUuid    () );
    setDepth         ( ( ( Category ) category ).getDepth         () );
    setLineage       ( ( ( Category ) category ).getLineage       () );
    setOrdinal       ( ( ( Category ) category ).getOrdinal       () );
    setName          ( ( ( Category ) category ).getName          () );
    setIncluded      ( ( ( Category ) category ).getIncluded      () );
    setDiscretionary ( ( ( Category ) category ).getDiscretionary () );
    setLastUpdate    ( ( ( Category ) category ).getLastUpdate    () );
    setCreated       ( ( ( Category ) category ).getCreated       () );
    }

    /**
     *
     */
    public void get ( ResultSet rs ) throws Exception
    {
    setId            ( rs.getInt       ( "category_id"             ) );
    setUuid          ( rs.getString    ( "category_uuid"           ) );
    setApplicationId ( rs.getInt       ( "category_application_id" ) );
    setParentId      ( rs.getInt       ( "category_parent_id"      ) );
    setParentUuid    ( rs.getString    ( "category_parent_uuid"    ) );
    setDepth         ( rs.getInt       ( "category_depth"          ) );
    setLineage       ( rs.getString    ( "category_lineage"        ) );
    setOrdinal       ( rs.getInt       ( "category_ordinal"        ) );
    setName          ( rs.getString    ( "category_name"           ) );
    setIncluded      ( rs.getBoolean   ( "category_included"       ) );
    setDiscretionary ( rs.getBoolean   ( "category_discretionary"  ) );
    setLastUpdate    ( rs.getTimestamp ( "category_last_update"    ) );
    setCreated       ( rs.getTimestamp ( "category_created"        ) );
    }

    /**
     *
     */
    static public String key() { return "category"; }

    /**
     *
     */
    public int       getId            () { return category_id             ; }
    public String    getUuid          () { return category_uuid           ; }
    public int       getApplicationId () { return category_application_id ; }
    public int       getParentId      () { return category_parent_id      ; }
    public String    getParentUuid    () { return category_parent_uuid    ; }
    public int       getDepth         () { return category_depth          ; }
    public String    getLineage       () { return category_lineage        ; }
    public int       getOrdinal       () { return category_ordinal        ; }
    public String    getName          () { return category_name           ; }
    public Boolean   getIncluded      () { return category_included       ; }
    public Boolean   getDiscretionary () { return category_discretionary  ; }
    public Timestamp getLastUpdate    () { return category_last_update    ; }
    public Timestamp getCreated       () { return category_created        ; }

    /**
     *
     */
    public void setId            ( int       category_id             ) { this.category_id             = category_id             ; }
    public void setUuid          ( String    category_uuid           ) { this.category_uuid           = category_uuid           ; }
    public void setApplicationId ( int       category_application_id ) { this.category_application_id = category_application_id ; }
    public void setParentId      ( int       category_parent_id      ) { this.category_parent_id      = category_parent_id      ; }
    public void setParentUuid    ( String    category_parent_uuid    ) { this.category_parent_uuid    = category_parent_uuid    ; }
    public void setDepth         ( int       category_depth          ) { this.category_depth          = category_depth          ; }
    public void setLineage       ( String    category_lineage        ) { this.category_lineage        = category_lineage        ; }
    public void setOrdinal       ( int       category_ordinal        ) { this.category_ordinal        = category_ordinal        ; }
    public void setName          ( String    category_name           ) { this.category_name           = category_name           ; }
    public void setIncluded      ( Boolean   category_included       ) { this.category_included       = category_included       ; }
    public void setDiscretionary ( Boolean   category_discretionary  ) { this.category_discretionary  = category_discretionary  ; }
    public void setLastUpdate    ( Timestamp category_last_update    ) { this.category_last_update    = category_last_update    ; }
    public void setCreated       ( Timestamp category_created        ) { this.category_created        = category_created        ; }
}
