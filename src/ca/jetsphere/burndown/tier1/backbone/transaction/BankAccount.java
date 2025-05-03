package ca.jetsphere.burndown.tier1.backbone.transaction;

/**
 *
 */
public class BankAccount
{
    private String bank_id;
    private String account_id;
    private String account_type;
    
    /**
     * Getters and Setters
     */
    public String getBankId     () { return this.bank_id     ; }
    public String getAccountId  () { return this.account_id  ; }
    public String getAccountType() { return this.account_type; }
    
    public void setBankId      ( String bank_id      ) { this.bank_id      = bank_id     ; }
    public void setAccountId   ( String account_id   ) { this.account_id   = account_id  ; }
    public void setAccountType ( String account_type ) { this.account_type = account_type; }
}
