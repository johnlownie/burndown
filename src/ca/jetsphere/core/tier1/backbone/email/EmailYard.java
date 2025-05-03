package ca.jetsphere.core.tier1.backbone.email;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import org.apache.struts.util.LabelValueBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */

public class EmailYard
{
    static final public int SYSTEM = 0x01;
    static final public int MASS   = 0x02;

    static final public int DRAFT  = 0x01;
    static final public int OUTBOX = 0x02;
    static final public int SENT   = 0x03;

    /**
     *
     */

    static public EmailSession get ( JDBC jdbc )
    {
    EmailSession emails = new EmailSession();

    String query = "select * from jet_base_email where email_status_id = " + OUTBOX;

    emails.query ( jdbc, query );

    return emails;
    }

    /**
     *
     */

    static public Email getPending ( JDBC jdbc )
    {
    Email pending = new Email();

    String query = "select * from jet_base_email where email_id = 1 and email_status_id = " + DRAFT;

    pending.query ( jdbc, query );

    return pending;
    }

    /**
     *
     */

    static public String getLabel ( int status )
    {
    String style;

    switch ( status )
    {
    case DRAFT  : style = "info"    ; break;

    case OUTBOX : style = "primary" ; break;

    case SENT   : style = "success" ; break;

    default     : style = "warning" ; break;
    }

    return "<span class=\"label label-table label-" + style + "\">" + Caption.get ( getStatus ( status ) ) + "</span>";
    }

    /**
     *
     */

    static public List getList ( HttpServletRequest request )
    {
    List list = new ArrayList();

    list.add ( new LabelValueBean ( Caption.get ( request, getStatus ( DRAFT  ) ), "" + DRAFT  ) );
    list.add ( new LabelValueBean ( Caption.get ( request, getStatus ( OUTBOX ) ), "" + OUTBOX ) );

    return list;
    }

    /**
     *
     */

    static public String getType ( int email_type_id )
    {
    switch ( email_type_id )
    {
    case SYSTEM : return "system" ;
    case MASS   : return "mass"   ;
    default     : return "???"    ;
    } }

    /**
     *
     */

    static public String getStatus ( int email_status_id )
    {
    switch ( email_status_id )
    {
    case DRAFT  : return "draft"  ;
    case OUTBOX : return "outbox" ;
    case SENT   : return "sent"   ;
    default     : return "???"    ;
    } }

}
