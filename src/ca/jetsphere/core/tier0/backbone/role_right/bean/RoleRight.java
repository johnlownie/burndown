package ca.jetsphere.core.tier0.backbone.role_right.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.tier1.tree.Tree;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

abstract public class RoleRight extends Tree
{
    int       role_right_id          ;
    String    role_right_uuid        ;
    int       role_right_role_id     ;
    int       role_right_parent_id   ;
    String    role_right_parent_uuid ;
    int       role_right_depth       ;
    String    role_right_lineage     ;
    int       role_right_ordinal     ;
    String    role_right_name        ;
    int       role_right_action_id   ;
    boolean   role_right_hidden      ;
    String    role_right_notes       ;
    Timestamp role_right_last_update ;
    Timestamp role_right_created     ;

    /**
     *
     */

    public void clear()
    {
    setId         (  -1   );
    setUuid       (  ""   );
    setRoleId     (  -1   );
    setParentId   (  -1   );
    setParentUuid (  ""   );
    setDepth      (  -1   );
    setLineage    (  ""   );
    setOrdinal    (  -1   );
    setName       (  ""   );
    setActionId   (  -1   );
    setHidden     ( false );
    setNotes      (  ""   );
    setLastUpdate ( null  );
    setCreated    ( null  );
    }

    /**
     *
     */

    public void copy ( Bolt roleRight )
    {
    setId         ( ( ( RoleRight ) roleRight ).getId        () );
    setUuid       ( ( ( RoleRight ) roleRight ).getUuid      () );
    setRoleId     ( ( ( RoleRight ) roleRight ).getRoleId    () );
    setParentId   ( ( ( RoleRight ) roleRight ).getParentId  () );
    setParentUuid ( ( ( RoleRight ) roleRight ).getParentUuid() );
    setDepth      ( ( ( RoleRight ) roleRight ).getDepth     () );
    setLineage    ( ( ( RoleRight ) roleRight ).getLineage   () );
    setOrdinal    ( ( ( RoleRight ) roleRight ).getOrdinal   () );
    setName       ( ( ( RoleRight ) roleRight ).getName      () );
    setActionId   ( ( ( RoleRight ) roleRight ).getActionId  () );
    setHidden     ( ( ( RoleRight ) roleRight ).getHidden    () );
    setNotes      ( ( ( RoleRight ) roleRight ).getNotes     () );
    setLastUpdate ( ( ( RoleRight ) roleRight ).getLastUpdate() );
    setCreated    ( ( ( RoleRight ) roleRight ).getCreated   () );
    }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "role_right_id"          ) );
    setUuid       ( rs.getString    ( "role_right_uuid"        ) );
    setRoleId     ( rs.getInt       ( "role_right_role_id"     ) );
    setParentId   ( rs.getInt       ( "role_right_parent_id"   ) );
    setParentUuid ( rs.getString    ( "role_right_parent_uuid" ) );
    setDepth      ( rs.getInt       ( "role_right_depth"       ) );
    setLineage    ( rs.getString    ( "role_right_lineage"     ) );
    setOrdinal    ( rs.getInt       ( "role_right_ordinal"     ) );
    setName       ( rs.getString    ( "role_right_name"        ) );
    setActionId   ( rs.getInt       ( "role_right_action_id"   ) );
    setHidden     ( rs.getBoolean   ( "role_right_hidden"      ) );
    setNotes      ( rs.getString    ( "role_right_notes"       ) );
    setLastUpdate ( rs.getTimestamp ( "role_right_last_update" ) );
    setCreated    ( rs.getTimestamp ( "role_right_created"     ) );
    }

    /**
     *
     */

    static public String key() { return "role_right"; }

    /**
     *
     */

    public int       getId         () { return role_right_id          ; }
    public String    getUuid       () { return role_right_uuid        ; }
    public int       getRoleId     () { return role_right_role_id     ; }
    public int       getParentId   () { return role_right_parent_id   ; }
    public String    getParentUuid () { return role_right_parent_uuid ; }
    public int       getDepth      () { return role_right_depth       ; }
    public String    getLineage    () { return role_right_lineage     ; }
    public int       getOrdinal    () { return role_right_ordinal     ; }
    public String    getName       () { return role_right_name        ; }
    public int       getActionId   () { return role_right_action_id   ; }
    public boolean   getHidden     () { return role_right_hidden      ; }
    public String    getNotes      () { return role_right_notes       ; }
    public Timestamp getLastUpdate () { return role_right_last_update ; }
    public Timestamp getCreated    () { return role_right_created     ; }

    /**
     *
     */

    public void setId         ( int       role_right_id          ) { this.role_right_id          = role_right_id          ; }
    public void setUuid       ( String    role_right_uuid        ) { this.role_right_uuid        = role_right_uuid        ; }
    public void setRoleId     ( int       role_right_role_id     ) { this.role_right_role_id     = role_right_role_id     ; }
    public void setParentId   ( int       role_right_parent_id   ) { this.role_right_parent_id   = role_right_parent_id   ; }
    public void setParentUuid ( String    role_right_parent_uuid ) { this.role_right_parent_uuid = role_right_parent_uuid ; }
    public void setDepth      ( int       role_right_depth       ) { this.role_right_depth       = role_right_depth       ; }
    public void setLineage    ( String    role_right_lineage     ) { this.role_right_lineage     = role_right_lineage     ; }
    public void setOrdinal    ( int       role_right_ordinal     ) { this.role_right_ordinal     = role_right_ordinal     ; }
    public void setName       ( String    role_right_name        ) { this.role_right_name        = role_right_name        ; }
    public void setActionId   ( int       role_right_action_id   ) { this.role_right_action_id   = role_right_action_id   ; }
    public void setHidden     ( boolean tree_collapsed) { this.role_right_hidden      = tree_collapsed; }
    public void setNotes      ( String    role_right_notes       ) { this.role_right_notes       = role_right_notes       ; }
    public void setLastUpdate ( Timestamp role_right_last_update ) { this.role_right_last_update = role_right_last_update ; }
    public void setCreated    ( Timestamp role_right_created     ) { this.role_right_created     = role_right_created     ; }

}