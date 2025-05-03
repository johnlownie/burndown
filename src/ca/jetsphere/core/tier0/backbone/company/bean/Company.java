package ca.jetsphere.core.tier0.backbone.company.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */

abstract public class Company extends Bolt implements IBean
{
    int       company_id          ;
    String    company_uuid        ;
    String    company_name        ;
    String    company_street      ;
    String    company_city        ;
    String    company_province    ;
    String    company_postal_code ;
    String    company_phone       ;
    String    company_fax         ;
    String    company_url         ;
    String    company_email       ;
    boolean   company_default     ;
    Timestamp company_last_update ;
    Timestamp company_created     ;

    /**
     *
     */

    public void clear()
    {
    setId         ( -1 );
    setUuid       ( "" );
    setName       ( "" );
    setStreet     ( "" );
    setCity       ( "" );
    setProvince   ( "" );
    setPostalCode ( "" );
    setPhone      ( "" );
    setFax        ( "" );
    setUrl        ( "" );
    setEmail      ( "" );
    setDefault    ( false );
    setLastUpdate ( null );
    setCreated    ( null );
    }

    /**
     *
     */

    public void copy ( Bolt company )
    {
    setId         ( ( ( Company ) company ).getId        () );
    setUuid       ( ( ( Company ) company ).getUuid      () );
    setName       ( ( ( Company ) company ).getName      () );
    setStreet     ( ( ( Company ) company ).getStreet    () );
    setCity       ( ( ( Company ) company ).getCity      () );
    setProvince   ( ( ( Company ) company ).getProvince  () );
    setPostalCode ( ( ( Company ) company ).getPostalCode() );
    setPhone      ( ( ( Company ) company ).getPhone     () );
    setFax        ( ( ( Company ) company ).getFax       () );
    setUrl        ( ( ( Company ) company ).getUrl       () );
    setEmail      ( ( ( Company ) company ).getEmail     () );
    setDefault    ( ( ( Company ) company ).getDefault   () );
    setLastUpdate ( ( ( Company ) company ).getLastUpdate() );
    setCreated    ( ( ( Company ) company ).getCreated   () );
    }

    /**
     *
     */

    public int       getId         () { return company_id          ; }
    public String    getUuid       () { return company_uuid        ; }
    public String    getName       () { return company_name        ; }
    public String    getStreet     () { return company_street      ; }
    public String    getCity       () { return company_city        ; }
    public String    getProvince   () { return company_province    ; }
    public String    getPostalCode () { return company_postal_code ; }
    public String    getPhone      () { return company_phone       ; }
    public String    getFax        () { return company_fax         ; }
    public String    getUrl        () { return company_url         ; }
    public String    getEmail      () { return company_email       ; }
    public boolean   getDefault    () { return company_default     ; }
    public Timestamp getLastUpdate () { return company_last_update ; }
    public Timestamp getCreated    () { return company_created     ; }

    /**
     *
     */

    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "company_id"          ) );
    setUuid       ( rs.getString    ( "company_uuid"        ) );
    setName       ( rs.getString    ( "company_name"        ) );
    setStreet     ( rs.getString    ( "company_street"      ) );
    setCity       ( rs.getString    ( "company_city"        ) );
    setProvince   ( rs.getString    ( "company_province"    ) );
    setPostalCode ( rs.getString    ( "company_postal_code" ) );
    setPhone      ( rs.getString    ( "company_phone"       ) );
    setFax        ( rs.getString    ( "company_fax"         ) );
    setUrl        ( rs.getString    ( "company_url"         ) );
    setEmail      ( rs.getString    ( "company_email"       ) );
    setDefault    ( rs.getBoolean   ( "company_default"     ) );
    setLastUpdate ( rs.getTimestamp ( "company_last_update" ) );
    setCreated    ( rs.getTimestamp ( "company_created"     ) );
    }

    /**
     *
     */

    static public String key() { return "company"; }

    /**
     *
     */

    public void setId         ( int       company_id          ) { this.company_id          = company_id          ; }
    public void setUuid       ( String    company_uuid        ) { this.company_uuid        = company_uuid        ; }
    public void setName       ( String    company_name        ) { this.company_name        = company_name        ; }
    public void setStreet     ( String    company_street      ) { this.company_street      = company_street      ; }
    public void setCity       ( String    company_city        ) { this.company_city        = company_city        ; }
    public void setProvince   ( String    company_province    ) { this.company_province    = company_province    ; }
    public void setPostalCode ( String    company_postal_code ) { this.company_postal_code = company_postal_code ; }
    public void setPhone      ( String    company_phone       ) { this.company_phone       = company_phone       ; }
    public void setFax        ( String    company_fax         ) { this.company_fax         = company_fax         ; }
    public void setUrl        ( String    company_url         ) { this.company_url         = company_url         ; }
    public void setEmail      ( String    company_email       ) { this.company_email       = company_email       ; }
    public void setDefault    ( boolean   company_default     ) { this.company_default     = company_default     ; }
    public void setLastUpdate ( Timestamp company_last_update ) { this.company_last_update = company_last_update ; }
    public void setCreated    ( Timestamp company_created     ) { this.company_created     = company_created     ; }

}
