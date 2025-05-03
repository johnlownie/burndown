package ca.jetsphere.core.tier2.backbone.email;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanySession;
import ca.jetsphere.core.tier1.backbone.email.Email;
import ca.jetsphere.core.tier1.backbone.email.EmailSession;
import ca.jetsphere.core.tier1.backbone.email.EmailYard;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.common.QueryActionForm;
import ca.jetsphere.core.tier2.table.DataTableWriter;

import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;

/**
 *
 */

public class EmailDoAction extends AbstractDoAction
{
    /**
     *
     */

    public String getKey() { return Email.key(); }

    /**
     *
     */

    public ActionForward json ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        QueryActionForm qaf = ( QueryActionForm ) store.getForm();

        EmailSession emails = EmailSession.query ( jdbc, store.getRequest(), EmailYard.MASS, false );

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        DataTableWriter dataTableWriter = new DataTableWriter ( emails, Email.mass_captions(), Email.mass_fields() );

        dataTableWriter.print ( out, store.getRequest() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm(); Company company = CompanySession.getSelected ( store.getRequest () );

    return getForward ( store );
    }
    /**
     *
     */

    public ActionForward send ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        String uuid = DockYard.getParameter ( store.getRequest (), "csrf" ); boolean success = true;

        if ( DockYard.isWhiteSpace ( uuid ) ) success = false;

        EmailSession emails = EmailSession.getInstance ( store.getRequest() ); Email email = ( Email ) emails.getBoltByUuid ( uuid );

        if ( email == null || !email.isValid() ) success = false;

        if ( success )

        { email.setStatusId ( EmailYard.OUTBOX ); email.save ( jdbc ); }

        store.getResponse().setContentType ( "application/json" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JSONObject jsonObject = new JSONObject();

        jsonObject.put ( "success", success );

        out.write ( jsonObject.toString() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

}
