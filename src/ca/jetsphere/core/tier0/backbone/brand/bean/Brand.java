package ca.jetsphere.core.tier0.backbone.brand.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * 
 */

abstract public class Brand extends Bolt implements IBean
{
    int       brand_id          ;
    String    brand_uuid        ;
    int       brand_company_id  ;
    String    brand_name        ;
    String    brand_url         ;
    String    brand_logo        ;
    String    brand_background  ;
    String    brand_copyright   ;
    boolean   brand_default     ;
    Timestamp brand_last_update ;
    Timestamp brand_created     ;

    /**
     *
     */

    public void clear()
    {
    setId         (  -1   );
    setUuid       (  ""   );
    setCompanyId  (  -1   );
    setName       (  ""   );
    setUrl        (  ""   );
    setLogo       (  ""   );
    setBackground (  ""   );
    setCopyright  (  ""   );
    setDefault    ( false );
    setLastUpdate ( null  );
    setCreated    ( null  );
    }

    /**
     *
     */

    public int       getId         () { return brand_id          ; }
    public String    getUuid       () { return brand_uuid        ; }
    public int       getCompanyId  () { return brand_company_id  ; }
    public String    getName       () { return brand_name        ; }
    public String    getUrl        () { return brand_url         ; }
    public String    getLogo       () { return brand_logo        ; }
    public String    getBackground () { return brand_background  ; }
    public String    getCopyright  () { return brand_copyright   ; }
    public boolean   getDefault    () { return brand_default     ; }
    public Timestamp getLastUpdate () { return brand_last_update ; }
    public Timestamp getCreated    () { return brand_created     ; }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "brand_id"          ) );
    setUuid       ( rs.getString    ( "brand_uuid"        ) );
    setCompanyId  ( rs.getInt       ( "brand_company_id"  ) );
    setName       ( rs.getString    ( "brand_name"        ) );
    setUrl        ( rs.getString    ( "brand_url"         ) );
    setLogo       ( rs.getString    ( "brand_logo"        ) );
    setBackground ( rs.getString    ( "brand_background"  ) );
    setCopyright  ( rs.getString    ( "brand_copyright"   ) );
    setDefault    ( rs.getBoolean   ( "brand_default"     ) );
    setLastUpdate ( rs.getTimestamp ( "brand_last_update" ) );
    setCreated    ( rs.getTimestamp ( "brand_created"     ) );
    }

    /**
     *
     */

    static public String key() { return "action"; }

    /**
     *
     */

    public void setId         ( int       brand_id          ) { this.brand_id          = brand_id          ; }
    public void setUuid       ( String    brand_uuid        ) { this.brand_uuid        = brand_uuid        ; }
    public void setCompanyId  ( int       brand_company_id  ) { this.brand_company_id  = brand_company_id  ; }
    public void setName       ( String    brand_name        ) { this.brand_name        = brand_name        ; }
    public void setUrl        ( String    brand_url         ) { this.brand_url         = brand_url         ; }
    public void setLogo       ( String    brand_logo        ) { this.brand_logo        = brand_logo        ; }
    public void setBackground ( String    brand_background  ) { this.brand_background  = brand_background  ; }
    public void setCopyright  ( String    brand_copyright   ) { this.brand_copyright   = brand_copyright   ; }
    public void setDefault    ( boolean   brand_default     ) { this.brand_default     = brand_default     ; }
    public void setLastUpdate ( Timestamp brand_last_update ) { this.brand_last_update = brand_last_update ; }
    public void setCreated    ( Timestamp brand_created     ) { this.brand_created     = brand_created     ; }

}
