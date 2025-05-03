package ca.jetsphere.core.tier2.backbone.email;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.bu.BuSession;
import ca.jetsphere.core.tier1.backbone.bu.BuYard;
import ca.jetsphere.core.tier1.backbone.email.Email;
import ca.jetsphere.core.tier1.backbone.email.EmailYard;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 *
 */

public class EmailEditAction extends AbstractEditAction
{
    /**
     *
     */

    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        int cc_bu_id = DockYard.toInteger ( store.getRequest (), "ccBuId" );

        if ( cc_bu_id <= 0 ) return null;

        BuSession grades = BuYard.getByParent( jdbc, store.getRequest(), cc_bu_id );

        store.getResponse().setContentType ( "text/html" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter();

        if ( cc_bu_id > 0 ) out.write ( DockYard.getOptions ( store.getRequest(), grades, BuYard.BY_ORDINAL, "all.grades" ) );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public String getKey() { return Email.key(); }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception

    { Email email = ( Email ) bolt; email.setTypeId ( EmailYard.MASS ); email.setStatusId ( EmailYard.DRAFT ); }

}
