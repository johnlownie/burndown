package ca.jetsphere.burndown.tier0.backbone.transaction.bean;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.IBean;
import java.sql.Date;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 */
abstract public class Transaction extends Bolt implements IBean
{
    int       transaction_id           ;
    String    transaction_uuid         ;
    int       transaction_period_id    ;
    int       transaction_account_id   ;
    int       transaction_category_id  ;
    String    transaction_name         ;
    int       transaction_type         ;
    int       transaction_amount       ;
    String    transaction_memo         ;
    String    transaction_fitid        ;
    Date      transaction_date         ;
    Timestamp transaction_last_update  ;
    Timestamp transaction_created      ;

    /**
     *
     */
    public void clear()
    {
    setId          (  -1  );
    setUuid        (  ""  );
    setPeriodId    (  -1  );
    setAccountId   (  -1  );
    setCategoryId  (   1  );
    setName        (  ""  );
    setType        (  -1  );
    setAmount      (   0  );
    setMemo        (  ""  );
    setFitId       (  ""  );
    setDate        ( null );
    setLastUpdate  ( null );
    setCreated     ( null );
    }

    /**
     *
     */
    public void copy ( Bolt transaction )
    {
    setId         (( ( Transaction ) transaction ).getId         () );
    setUuid       (( ( Transaction ) transaction ).getUuid       () );
    setPeriodId   (( ( Transaction ) transaction ).getPeriodId   () );
    setAccountId  (( ( Transaction ) transaction ).getAccountId  () );
    setCategoryId (( ( Transaction ) transaction ).getCategoryId () );
    setName       (( ( Transaction ) transaction ).getName       () );
    setType       (( ( Transaction ) transaction ).getType       () );
    setAmount     (( ( Transaction ) transaction ).getAmount     () );
    setMemo       (( ( Transaction ) transaction ).getMemo       () );
    setFitId      (( ( Transaction ) transaction ).getFitId      () );
    setDate       (( ( Transaction ) transaction ).getDate       () );
    setLastUpdate (( ( Transaction ) transaction ).getLastUpdate () );
    setCreated    (( ( Transaction ) transaction ).getCreated    () );
    }

    /**
     *
     */
    public void get ( ResultSet rs ) throws Exception
    {
    setId         ( rs.getInt       ( "transaction_id"           ) );
    setUuid       ( rs.getString    ( "transaction_uuid"         ) );
    setPeriodId   ( rs.getInt       ( "transaction_period_id"    ) );
    setAccountId  ( rs.getInt       ( "transaction_account_id"   ) );
    setCategoryId ( rs.getInt       ( "transaction_category_id"  ) );
    setName       ( rs.getString    ( "transaction_name"         ) );
    setType       ( rs.getInt       ( "transaction_type"         ) );
    setAmount     ( rs.getInt       ( "transaction_amount"       ) );
    setMemo       ( rs.getString    ( "transaction_memo"         ) );
    setFitId      ( rs.getString    ( "transaction_fitid"        ) );
    setDate       ( rs.getDate      ( "transaction_date"         ) );
    setLastUpdate ( rs.getTimestamp ( "transaction_last_update"  ) );
    setCreated    ( rs.getTimestamp ( "transaction_created"      ) );
    }

    /**
     *
     */
    static public String key() { return "transaction"; }

    /**
     *
     */
    public int       getId         () { return transaction_id           ; }
    public String    getUuid       () { return transaction_uuid         ; }
    public int       getPeriodId   () { return transaction_period_id    ; }
    public int       getAccountId  () { return transaction_account_id   ; }
    public int       getCategoryId () { return transaction_category_id  ; }
    public String    getName       () { return transaction_name         ; }
    public int       getType       () { return transaction_type         ; }
    public int       getAmount     () { return transaction_amount       ; }
    public String    getMemo       () { return transaction_memo         ; }
    public String    getFitId      () { return transaction_fitid        ; }
    public Date      getDate       () { return transaction_date         ; }
    public Timestamp getLastUpdate () { return transaction_last_update  ; }
    public Timestamp getCreated    () { return transaction_created      ; }

    /**
     *
     */

    public void setId         ( int       transaction_id           ) { this.transaction_id           = transaction_id           ; }
    public void setUuid       ( String    transaction_uuid         ) { this.transaction_uuid         = transaction_uuid         ; }
    public void setPeriodId   ( int       transaction_period_id    ) { this.transaction_period_id    = transaction_period_id    ; }
    public void setAccountId  ( int       transaction_account_id   ) { this.transaction_account_id   = transaction_account_id   ; }
    public void setCategoryId ( int       transaction_category_id  ) { this.transaction_category_id  = transaction_category_id  ; }
    public void setName       ( String    transaction_name         ) { this.transaction_name         = transaction_name         ; }
    public void setType       ( int       transaction_type         ) { this.transaction_type         = transaction_type         ; }
    public void setAmount     ( int       transaction_amount       ) { this.transaction_amount       = transaction_amount       ; }
    public void setMemo       ( String    transaction_memo         ) { this.transaction_memo         = transaction_memo         ; }
    public void setFitId      ( String    transaction_fitid        ) { this.transaction_fitid        = transaction_fitid        ; }
    public void setDate       ( Date      transaction_date         ) { this.transaction_date         = transaction_date         ; }
    public void setLastUpdate ( Timestamp transaction_last_update  ) { this.transaction_last_update  = transaction_last_update  ; }
    public void setCreated    ( Timestamp transaction_created      ) { this.transaction_created      = transaction_created      ; }
}
