package ca.jetsphere.core.tier0.backbone.company_member.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

abstract public class CompanyMember extends Bolt implements IBean
{
    int       company_member_id           ;
    String    company_member_uuid         ;
    int       company_member_user_id      ;
    int       company_member_company_id   ;
    String    company_member_firstname    ;
    String    company_member_lastname     ;
    String    company_member_street       ;
    String    company_member_city         ;
    String    company_member_province     ;
    String    company_member_postal_code  ;
    String    company_member_home_phone   ;
    String    company_member_mobile_phone ;
    String    company_member_work_phone   ;
    String    company_member_work_fax     ;
    String    company_member_email        ;
    int       company_member_avatar_id    ;
    boolean   company_member_subscribed   ;
    Timestamp company_member_last_update  ;
    Timestamp company_member_created      ;

    /**
     *
     */

    public void clear()
    {
    setId          (  -1  );
    setUuid        (  ""  );
    setUserId      (  -1  );
    setCompanyId   (  -1  );
    setFirstname   (  ""  );
    setLastname    (  ""  );
    setStreet      (  ""  );
    setCity        (  ""  );
    setProvince    (  ""  );
    setPostalCode  (  ""  );
    setHomePhone   (  ""  );
    setMobilePhone (  ""  );
    setWorkPhone   (  ""  );
    setWorkFax     (  ""  );
    setEmail       (  ""  );
    setAvatarId    (  -1  );
    setSubscribed  ( true );
    setLastUpdate  ( null );
    setCreated     ( null );
    }

    /**
     *
     */

    public void copy ( Bolt companyMember )
    {
    setId          ( ( ( CompanyMember ) companyMember ).getId         () );
    setUuid        ( ( ( CompanyMember ) companyMember ).getUuid       () );
    setUserId      ( ( ( CompanyMember ) companyMember ).getUserId     () );
    setCompanyId   ( ( ( CompanyMember ) companyMember ).getCompanyId  () );
    setFirstname   ( ( ( CompanyMember ) companyMember ).getFirstname  () );
    setLastname    ( ( ( CompanyMember ) companyMember ).getLastname   () );
    setStreet      ( ( ( CompanyMember ) companyMember ).getStreet     () );
    setCity        ( ( ( CompanyMember ) companyMember ).getCity       () );
    setProvince    ( ( ( CompanyMember ) companyMember ).getProvince   () );
    setPostalCode  ( ( ( CompanyMember ) companyMember ).getPostalCode () );
    setHomePhone   ( ( ( CompanyMember ) companyMember ).getHomePhone  () );
    setMobilePhone ( ( ( CompanyMember ) companyMember ).getMobilePhone() );
    setWorkPhone   ( ( ( CompanyMember ) companyMember ).getWorkPhone  () );
    setWorkFax     ( ( ( CompanyMember ) companyMember ).getWorkFax    () );
    setEmail       ( ( ( CompanyMember ) companyMember ).getEmail      () );
    setAvatarId    ( ( ( CompanyMember ) companyMember ).getAvatarId   () );
    setSubscribed  ( ( ( CompanyMember ) companyMember ).getSubscribed () );
    setLastUpdate  ( ( ( CompanyMember ) companyMember ).getLastUpdate () );
    setCreated     ( ( ( CompanyMember ) companyMember ).getCreated    () );
    }

    /**
     *
     */

    public void copy ( CompanyMember companyMember )
    {
    setId          ( companyMember.getId          () );
    setUuid        ( companyMember.getUuid        () );
    setUserId      ( companyMember.getUserId      () );
    setCompanyId   ( companyMember.getCompanyId   () );
    setFirstname   ( companyMember.getFirstname   () );
    setLastname    ( companyMember.getLastname    () );
    setStreet      ( companyMember.getStreet      () );
    setCity        ( companyMember.getCity        () );
    setProvince    ( companyMember.getProvince    () );
    setPostalCode  ( companyMember.getPostalCode  () );
    setMobilePhone ( companyMember.getMobilePhone () );
    setHomePhone   ( companyMember.getHomePhone   () );
    setWorkPhone   ( companyMember.getWorkPhone   () );
    setWorkFax     ( companyMember.getWorkFax     () );
    setEmail       ( companyMember.getEmail       () );
    setAvatarId    ( companyMember.getAvatarId    () );
    setSubscribed  ( companyMember.getSubscribed  () );
    setLastUpdate  ( companyMember.getLastUpdate  () );
    setCreated     ( companyMember.getCreated     () );
    }

    /**
     *
     */

    public int       getId          () { return company_member_id           ; }
    public String    getUuid        () { return company_member_uuid         ; }
    public int       getUserId      () { return company_member_user_id      ; }
    public int       getCompanyId   () { return company_member_company_id   ; }
    public String    getFirstname   () { return company_member_firstname    ; }
    public String    getLastname    () { return company_member_lastname     ; }
    public String    getStreet      () { return company_member_street       ; }
    public String    getCity        () { return company_member_city         ; }
    public String    getProvince    () { return company_member_province     ; }
    public String    getPostalCode  () { return company_member_postal_code  ; }
    public String    getHomePhone   () { return company_member_home_phone   ; }
    public String    getMobilePhone () { return company_member_mobile_phone ; }
    public String    getWorkPhone   () { return company_member_work_phone   ; }
    public String    getWorkFax     () { return company_member_work_fax     ; }
    public String    getEmail       () { return company_member_email        ; }
    public int       getAvatarId    () { return company_member_avatar_id    ; }
    public boolean   getSubscribed  () { return company_member_subscribed   ; }
    public Timestamp getLastUpdate  () { return company_member_last_update  ; }
    public Timestamp getCreated     () { return company_member_created      ; }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId          ( rs.getInt       ( "company_member_id"           ) );
    setUuid        ( rs.getString    ( "company_member_uuid"         ) );
    setUserId      ( rs.getInt       ( "company_member_user_id"      ) );
    setCompanyId   ( rs.getInt       ( "company_member_company_id"   ) );
    setFirstname   ( rs.getString    ( "company_member_firstname"    ) );
    setLastname    ( rs.getString    ( "company_member_lastname"     ) );
    setStreet      ( rs.getString    ( "company_member_street"       ) );
    setCity        ( rs.getString    ( "company_member_city"         ) );
    setProvince    ( rs.getString    ( "company_member_province"     ) );
    setPostalCode  ( rs.getString    ( "company_member_postal_code"  ) );
    setHomePhone   ( rs.getString    ( "company_member_home_phone"   ) );
    setMobilePhone ( rs.getString    ( "company_member_mobile_phone" ) );
    setWorkPhone   ( rs.getString    ( "company_member_work_phone"   ) );
    setWorkFax     ( rs.getString    ( "company_member_work_fax"     ) );
    setEmail       ( rs.getString    ( "company_member_email"        ) );
    setAvatarId    ( rs.getInt       ( "company_member_avatar_id"    ) );
    setSubscribed  ( rs.getBoolean   ( "company_member_subscribed"   ) );
    setLastUpdate  ( rs.getTimestamp ( "company_member_last_update"  ) );
    setCreated     ( rs.getTimestamp ( "company_member_created"      ) );
    }

    /**
     *
     */

    static public String key() { return "company_member"; }

    /**
     *
     */

    public void setId          ( int       company_member_id           ) { this.company_member_id           = company_member_id           ; }
    public void setUuid        ( String    company_member_uuid         ) { this.company_member_uuid         = company_member_uuid         ; }
    public void setUserId      ( int       company_member_user_id      ) { this.company_member_user_id      = company_member_user_id      ; }
    public void setCompanyId   ( int       company_member_company_id   ) { this.company_member_company_id   = company_member_company_id   ; }
    public void setFirstname   ( String    company_member_firstname    ) { this.company_member_firstname    = company_member_firstname    ; }
    public void setLastname    ( String    company_member_lastname     ) { this.company_member_lastname     = company_member_lastname     ; }
    public void setStreet      ( String    company_member_street       ) { this.company_member_street       = company_member_street       ; }
    public void setCity        ( String    company_member_city         ) { this.company_member_city         = company_member_city         ; }
    public void setProvince    ( String    company_member_province     ) { this.company_member_province     = company_member_province     ; }
    public void setPostalCode  ( String    company_member_postal_code  ) { this.company_member_postal_code  = company_member_postal_code  ; }
    public void setHomePhone   ( String    company_member_home_phone   ) { this.company_member_home_phone   = company_member_home_phone   ; }
    public void setWorkPhone   ( String    company_member_work_phone   ) { this.company_member_work_phone   = company_member_work_phone   ; }
    public void setMobilePhone ( String    company_member_mobile_phone ) { this.company_member_mobile_phone = company_member_mobile_phone ; }
    public void setWorkFax     ( String    company_member_work_fax     ) { this.company_member_work_fax     = company_member_work_fax     ; }
    public void setEmail       ( String    company_member_email        ) { this.company_member_email        = company_member_email        ; }
    public void setAvatarId    ( int       company_member_avatar_id    ) { this.company_member_avatar_id    = company_member_avatar_id    ; }
    public void setSubscribed  ( boolean   company_member_subscribed   ) { this.company_member_subscribed   = company_member_subscribed   ; }
    public void setLastUpdate  ( Timestamp company_member_last_update  ) { this.company_member_last_update  = company_member_last_update  ; }
    public void setCreated     ( Timestamp company_member_created      ) { this.company_member_created      = company_member_created      ; }

}
