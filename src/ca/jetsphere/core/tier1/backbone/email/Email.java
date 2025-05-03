package ca.jetsphere.core.tier1.backbone.email;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.bu.Bu;
import org.apache.struts.upload.FormFile;

import java.sql.ResultSet;
import java.util.Locale;

/**
 *
 */

public class Email extends ca.jetsphere.core.tier0.backbone.email.Email
{
    FormFile email_form_file   ;
    String   email_to_bu_name  ;
    String   email_cc_bu_name  ;
    String   email_bcc_bu_name ;

    /**
     *
     */

    public Email() { super(); }

    /**
     *
     */

    public Email ( JDBC jdbc, int email_id ) throws Exception { super ( jdbc, email_id ); }

    /**
     *
     */

    public Email ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions     () { return new String [] { "email.from", "email.to", "email.cc", "email.bcc", "email.subject", "email.message", "email.attachment", "last.update", "actions" }; }
    static public String [] mass_captions() { return new String [] { "school", "class", "email.subject", "status", "actions" }; }

    /**
     *
     */

    static public String [] fields() { return new String [] { "email_from", "email_to", "email_cc", "email_bcc", "email_subject", "email_message", "email_attachment", "email_last_update", "email_uuid" }; }
    static public String [] mass_fields() { return new String [] { "email_cc_bu_id", "email_bcc_bu_id", "email_subject", "email_status_id", "email_uuid" }; }

    /**
     *
     */

    public void foreign ( JDBC jdbc )

    { setToBuName ( new Bu ( jdbc, getToBuId() ).getShortName() ); setCcBuName ( new Bu ( jdbc, getCcBuId() ).getShortName() ); setBccBuName ( new Bu ( jdbc, getBccBuId() ).getName() ); }

    /**
     *
     */

    public Object get ( String s )
    {
    if ( "email_cc_bu_id"  .equals ( s ) ) return getCcBuName();

    if ( "email_bcc_bu_id" .equals ( s ) ) return DockYard.isWhiteSpace ( getCcBuName() ) ? "" : DockYard.isWhiteSpace ( getBccBuName() ) ? Caption.get ( Locale.CANADA, "all.grades" ): getBccBuName();

    if ( "email_status_id" .equals ( s ) ) return EmailYard.getLabel ( DockYard.toInt ( super.get ( s ) ) );

    if ( "email_uuid"  .equals ( s ) ) return getEditDeleteSendBox ( getUuid() );

    return super.get ( s );
    }

    /**
     *
     */

    protected String getEditDeleteSendBox ( String s )
    {
    if ( DockYard.isWhiteSpace ( s ) ) return "";

    return

    "<button type=\"button\" class=\"btn btn-sm btn-default add-tooltip editBtn\" data-toggle=\"tooltip\" data-placement=\"top\" data-id=\"" + s + "\" data-original-title=\"Edit\" data-container=\"body\"><i class=\"fa fa-pencil\"></i></button>" +

    "<button type=\"button\" class=\"btn btn-sm btn-danger add-tooltip deleteBtn\" data-toggle=\"tooltip\" data-placement=\"top\" data-id=\"" + s + "\" data-original-title=\"Delete\" data-container=\"body\"><i class=\"fa fa-times\"></i></button>" +

    "<button type=\"button\" class=\"btn btn-sm btn-info add-tooltip emailBtn\" data-toggle=\"tooltip\" data-id=\"" + s + "\" data-original-title=\"Email\" data-container=\"body\"><i class=\"fa fa-lg fa-envelope\"></i></button>";

    }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public String getName () { return getPersonal(); }

    /**
     *
     */

    public FormFile getFormFile () { return email_form_file   ; }
    public String   getToBuName () { return email_to_bu_name  ; }
    public String   getCcBuName () { return email_cc_bu_name  ; }
    public String   getBccBuName() { return email_bcc_bu_name ; }

    /**
     *
     */

    public void setFormFile  ( FormFile email_form_file  ) { this.email_form_file   = email_form_file   ; }
    public void setToBuName  ( String email_to_bu_name   ) { this.email_to_bu_name  = email_to_bu_name  ; }
    public void setCcBuName  ( String email_cc_bu_name   ) { this.email_cc_bu_name  = email_cc_bu_name  ; }
    public void setBccBuName ( String email_bcc_bu_name  ) { this.email_bcc_bu_name = email_bcc_bu_name ; }

}
