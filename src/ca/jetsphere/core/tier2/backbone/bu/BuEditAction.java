/**
 * COPYRIGHT 2011-2012 DAKOTA GRC, INC.
 */

package ca.jetsphere.core.tier2.backbone.bu;

import javax.servlet.http.HttpServletRequest;

import ca.jetsphere.core.tier1.backbone.bu.BuSession;
import org.apache.struts.action.ActionForward;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.bu.Bu;
import ca.jetsphere.core.tier1.backbone.bu.BuYard;
import ca.jetsphere.core.tier2.common.AbstractEditAction;
import ca.jetsphere.core.tier2.common.ActionStore;
import ca.jetsphere.core.tier2.common.Errors;

/**
 *
 */

public class BuEditAction extends AbstractEditAction
{
    /**
     *
     */

    public ActionForward delete ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    Bu bu = ( Bu ) store.getForm();

    bu.delete ( jdbc );

    return new ActionForward ( store.getForward ( "success" ).getPath() + "?select=" + bu.getParentId() );
    }

    /**
     *
     */

    public ActionForward edit ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    Bu bu = ( Bu ) store.getForm();

    BuSession.setSelected ( store.getRequest(), bu );

    return store.getForward ( "failure" );
    }

    /**
     *
     */

    public String getKey() { return Bu.key(); }

    /**
     *
     */

    public ActionForward query ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception

    { return store.getForward ( "failure" ); }

    /**
     *
     */

    public void setup ( JDBC jdbc, HttpServletRequest request, Bolt bolt, Errors errors ) throws Exception
    {
    Bu bu = ( Bu ) bolt;

    Bu parent = new Bu ( jdbc, bu.getParentId() );

    if ( parent == null || ! parent.isValid() ) return;

    bu.setPeriodId    ( parent.getPeriodId()  );

    bu.setParentUuid ( parent.getUuid() );

    bu.setDepth      ( parent.getDepth() + 1 );

    bu.setLineage    ( parent.getLineage() + parent.getId() + "/" );

    if ( bu.getOrdinal() < 0 ) bu.setOrdinal ( BuYard.getCount ( parent.getPeriodId(), parent.getId() ) );
    }

    /**
     *
     */

    public ActionForward update ( JDBC jdbc, ActionStore store, Errors errors ) throws Exception
    {
    Bu bu = ( Bu ) store.getForm();

    ActionForward forward = super.update ( jdbc, store, errors );

    if ( "failure" .equals ( forward.getName() ) ) return forward;

    return new ActionForward ( store.getForward ( "success" ).getPath() + "?select=" + bu.getId(), true );
    }

}
