package ca.jetsphere.core.tier1.backbone.action;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;

import java.util.Iterator;

/**
 *
 */

public class ActionYard
{
    /**
     *
     */

    static public void delete ( JDBC jdbc, int company_id ) throws Exception
    {
    ActionSession actions = new ActionSession ( jdbc, company_id );

    Iterator it = actions.iterator ( false );

    while ( it.hasNext() )

    { Action action = ( Action ) it.next(); action.delete ( jdbc ); }

    }

    /**
     *
     */

    static public Action getByHref ( JDBC jdbc, String href )
    {
    Action action = new Action();

    String query = "select * from jet_base_action where action_href = " + DockYard.quote ( href );

    action.query ( jdbc, query );

    return action;
    }

}
