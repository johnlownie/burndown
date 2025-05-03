package ca.jetsphere.core.tier2.system.knock;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.action.Action;
import ca.jetsphere.core.tier1.system.knock.Knock;
import ca.jetsphere.core.tier1.system.knock.KnockForm;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;
import org.apache.struts.action.ActionForward;

/**
 *
 */

public class KnockEditAction extends AbstractEditAction
{

    /**
     *
     */

    public ActionForward edit ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    KnockForm knockForm = ( KnockForm ) store.getForm(); knockForm.clear();

    String key = DockYard.getParameter ( store.getRequest(), "csrf" );

    if ( !DockYard.isWhiteSpace ( key ) ) { knockForm.setKey ( key ); knockForm.setValue ( Knock.get ( key ) ); }

    return query ( jdbc, store, errors );
    }

    /**
     *
     */

    public ActionForward jupdate ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    try {

        KnockForm knockForm = ( KnockForm ) store.getForm();

        Knock.set ( knockForm.getKey(), knockForm.getValue() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { return null; }
    }

    /**
     *
     */

    public String getKey() { return "[knock]"; }

}
