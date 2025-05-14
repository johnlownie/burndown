package ca.jetsphere.burndown.tier0.backbone.account.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;
import java.sql.Date;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */
abstract public class Account extends Bolt implements IBean
{
    enum Type { DEBIT, CREDIT }

    int       account_id          ;
    String    account_uuid        ;
    int       account_company_id  ;
    String    account_name        ;
    String    account_number      ;
    Timestamp account_last_update ;
    Timestamp account_created     ;

    /**
     *
     */
    public void clear()
    {
    setId          (  -1  );
    setUuid        (  ""  );
    setCompanyId   (  -1  );
    setName        (  ""  );
    setNumber      (  ""  );
    setLastUpdate  ( null );
    setCreated     ( null );
    }

    /**
     *
     */
    public void copy ( Bolt account )
    {
    setId         (( ( Account ) account ).getId         () );
    setUuid       (( ( Account ) account ).getUuid       () );
    setCompanyId  (( ( Account ) account ).getCompanyId  () );
    setName       (( ( Account ) account ).getName       () );
    setNumber     (( ( Account ) account ).getNumber     () );
    setLastUpdate (( ( Account ) account ).getLastUpdate () );
    setCreated    (( ( Account ) account ).getCreated    () );
    }

    /**
     *
     */
    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "account_id"          ) );
    setUuid       ( rs.getString    ( "account_uuid"        ) );
    setCompanyId  ( rs.getInt       ( "account_company_id"  ) );
    setName       ( rs.getString    ( "account_name"        ) );
    setNumber     ( rs.getString    ( "account_number"      ) );
    setLastUpdate ( rs.getTimestamp ( "account_last_update" ) );
    setCreated    ( rs.getTimestamp ( "account_created"     ) );
    }

    /**
     *
     */
    static public String key() { return "account"; }

    /**
     *
     */
    public int       getId         () { return account_id          ; }
    public String    getUuid       () { return account_uuid        ; }
    public int       getCompanyId  () { return account_company_id  ; }
    public String    getName       () { return account_name        ; }
    public String    getNumber     () { return account_number      ; }
    public Timestamp getLastUpdate () { return account_last_update ; }
    public Timestamp getCreated    () { return account_created     ; }

    /**
     *
     */

    public void setId         ( int       account_id          ) { this.account_id          = account_id          ; }
    public void setUuid       ( String    account_uuid        ) { this.account_uuid        = account_uuid        ; }
    public void setCompanyId  ( int       account_company_id  ) { this.account_company_id  = account_company_id  ; }
    public void setName       ( String    account_name        ) { this.account_name        = account_name        ; }
    public void setNumber     ( String    account_number      ) { this.account_number      = account_number      ; }
    public void setLastUpdate ( Timestamp account_last_update ) { this.account_last_update = account_last_update ; }
    public void setCreated    ( Timestamp account_created     ) { this.account_created     = account_created     ; }
}
