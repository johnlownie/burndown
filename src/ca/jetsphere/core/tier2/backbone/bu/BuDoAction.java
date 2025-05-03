package ca.jetsphere.core.tier2.backbone.bu;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.bu.Bu;
import ca.jetsphere.core.tier1.backbone.bu.BuSession;
import ca.jetsphere.core.tier2.common.AbstractDoAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import ca.jetsphere.core.tier2.common.QueryActionForm;

import ca.jetsphere.core.tier2.tree.JsonWriter;
import org.apache.struts.action.ActionForward;

import java.io.PrintWriter;

/**
 *
 */

public class BuDoAction extends AbstractDoAction
{
    /**
     *
     */

    public ActionForward fetch ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        int id = DockYard.toInteger ( store.getRequest(), "id" );

        BuSession bus = BuSession .getInstance ( store.getRequest() );

        store.getResponse().setContentType ( "text/html" ); store.getResponse().setCharacterEncoding ( "UTF-8" );

        PrintWriter out = store.getResponse().getWriter(); JsonWriter jsonWriter = new JsonWriter ( bus );

        String s = jsonWriter.get ( store.getRequest(), id );

        out.write ( s );

    } catch ( Exception e ) { Common.trace ( e ); }
        
    finally { return null; }
    }
    
    /**
     *
     */

    public String getKey() { return Bu.key(); }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    QueryActionForm qaf = ( QueryActionForm ) store.getForm();

    BuSession.query ( jdbc, store.getRequest(), qaf.getPeriodId(), false );

    return getForward ( store );
    }

}
