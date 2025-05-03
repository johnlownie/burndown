package ca.jetsphere.core.tier0.backbone.bu_member.bean;

import java.sql.ResultSet;
import java.sql.Timestamp;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

/**
 *
 */

abstract public class BuMember extends Bolt implements IBean
{
    int       bu_member_id                ;
    String    bu_member_uuid              ;
    int       bu_member_bu_id             ;
    int       bu_member_company_member_id ;
    String    bu_member_role_ids          ;
    Timestamp bu_member_last_update       ;
    Timestamp bu_member_created           ;

    /**
     *
     */

    public void clear()
    {
    setId              (  -1  );
    setUuid            (  ""  );
    setBuId            (  -1  );
    setCompanyMemberId (  -1  );
    setRoleIds         (  ""  );
    setLastUpdate      ( null );
    setCreated         ( null );
    }

    /**
     *
     */

    public void copy ( BuMember buMember )
    {
    setId              ( buMember.getId              () );
    setUuid            ( buMember.getUuid            () );
    setBuId            ( buMember.getBuId            () );
    setCompanyMemberId ( buMember.getCompanyMemberId () );
    setRoleIds         ( buMember.getRoleIds         () );
    setLastUpdate      ( buMember.getLastUpdate      () );
    setCreated         ( buMember.getCreated         () );
    }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId              ( rs.getInt       ( "bu_member_id"                ) );
    setUuid            ( rs.getString    ( "bu_member_uuid"              ) );
    setBuId            ( rs.getInt       ( "bu_member_bu_id"             ) );
    setCompanyMemberId ( rs.getInt       ( "bu_member_company_member_id" ) );
    setRoleIds         ( rs.getString    ( "bu_member_role_ids"          ) );
    setLastUpdate      ( rs.getTimestamp ( "bu_member_last_update"       ) );
    setCreated         ( rs.getTimestamp ( "bu_member_created"           ) );
    }

    /**
     *
     */

    public int       getId              () { return bu_member_id                ; }
    public String    getUuid            () { return bu_member_uuid              ; }
    public int       getBuId            () { return bu_member_bu_id             ; }
    public int       getCompanyMemberId () { return bu_member_company_member_id ; }
    public String    getRoleIds         () { return bu_member_role_ids          ; }
    public Timestamp getLastUpdate      () { return bu_member_last_update       ; }
    public Timestamp getCreated         () { return bu_member_created           ; }

    /**
     *
     */

    static public String key() { return "bu_member"; }

    /**
     *
     */

    public void setId              ( int       bu_member_id                ) { this.bu_member_id                = bu_member_id                ; }
    public void setUuid            ( String    bu_member_uuid              ) { this.bu_member_uuid              = bu_member_uuid              ; }
    public void setBuId            ( int       bu_member_bu_id             ) { this.bu_member_bu_id             = bu_member_bu_id             ; }
    public void setCompanyMemberId ( int       bu_member_company_member_id ) { this.bu_member_company_member_id = bu_member_company_member_id ; }
    public void setRoleIds         ( String    bu_member_role_ids          ) { this.bu_member_role_ids          = bu_member_role_ids          ; }
    public void setLastUpdate      ( Timestamp bu_member_last_update       ) { this.bu_member_last_update       = bu_member_last_update       ; }
    public void setCreated         ( Timestamp bu_member_created           ) { this.bu_member_created           = bu_member_created           ; }

}
