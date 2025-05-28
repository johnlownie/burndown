package ca.jetsphere.burndown.tier0.backbone.account.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */
abstract public class Account extends Bolt implements IBean
{
    enum Type { DEBIT, CREDIT }

    int       account_id             ;
    String    account_uuid           ;
    int       account_application_id ;
    String    account_name           ;
    String    account_type           ;
    String    account_number         ;
    String    account_bank_id        ;
    String    account_url            ;
    Timestamp account_last_update    ;
    Timestamp account_created        ;

    /**
     *
     */
    public void clear()
    {
    setId            (  -1  );
    setUuid          (  ""  );
    setApplicationId (  -1  );
    setName          (  ""  );
    setType          (  ""  );
    setNumber        (  ""  );
    setBankId        (  ""  );
    setUrl           (  ""  );
    setLastUpdate    ( null );
    setCreated       ( null );
    }

    /**
     *
     */
    public void copy ( Bolt account )
    {
    setId            (( ( Account ) account ).getId            () );
    setUuid          (( ( Account ) account ).getUuid          () );
    setApplicationId (( ( Account ) account ).getApplicationId () );
    setName          (( ( Account ) account ).getName          () );
    setType          (( ( Account ) account ).getType          () );
    setNumber        (( ( Account ) account ).getNumber        () );
    setBankId        (( ( Account ) account ).getBankId        () );
    setUrl           (( ( Account ) account ).getUrl           () );
    setLastUpdate    (( ( Account ) account ).getLastUpdate    () );
    setCreated       (( ( Account ) account ).getCreated       () );
    }

    /**
     *
     */
    public void get ( ResultSet rs ) throws Exception
    {
    setId            ( rs.getInt       ( "account_id"             ) );
    setUuid          ( rs.getString    ( "account_uuid"           ) );
    setApplicationId ( rs.getInt       ( "account_application_id" ) );
    setName          ( rs.getString    ( "account_name"           ) );
    setType          ( rs.getString    ( "account_type"           ) );
    setNumber        ( rs.getString    ( "account_number"         ) );
    setBankId        ( rs.getString    ( "account_bank_id"        ) );
    setUrl           ( rs.getString    ( "account_url"            ) );
    setLastUpdate    ( rs.getTimestamp ( "account_last_update"    ) );
    setCreated       ( rs.getTimestamp ( "account_created"        ) );
    }

    /**
     *
     */
    static public String key() { return "account"; }

    /**
     *
     */
    public int       getId            () { return account_id             ; }
    public String    getUuid          () { return account_uuid           ; }
    public int       getApplicationId () { return account_application_id ; }
    public String    getName          () { return account_name           ; }
    public String    getType          () { return account_type           ; }
    public String    getNumber        () { return account_number         ; }
    public String    getBankId        () { return account_bank_id        ; }
    public String    getUrl           () { return account_url            ; }
    public Timestamp getLastUpdate    () { return account_last_update    ; }
    public Timestamp getCreated       () { return account_created        ; }

    /**
     *
     */
    public void setId            ( int       account_id             ) { this.account_id             = account_id             ; }
    public void setUuid          ( String    account_uuid           ) { this.account_uuid           = account_uuid           ; }
    public void setApplicationId ( int       account_application_id ) { this.account_application_id = account_application_id ; }
    public void setName          ( String    account_name           ) { this.account_name           = account_name           ; }
    public void setType          ( String    account_type           ) { this.account_type           = account_type           ; }
    public void setNumber        ( String    account_number         ) { this.account_number         = account_number         ; }
    public void setBankId        ( String    account_bank_id        ) { this.account_bank_id        = account_bank_id        ; }
    public void setUrl           ( String    account_url            ) { this.account_url            = account_url            ; }
    public void setLastUpdate    ( Timestamp account_last_update    ) { this.account_last_update    = account_last_update    ; }
    public void setCreated       ( Timestamp account_created        ) { this.account_created        = account_created        ; }
}
