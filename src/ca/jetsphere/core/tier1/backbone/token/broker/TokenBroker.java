package ca.jetsphere.core.tier1.backbone.token.broker;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import ca.jetsphere.core.tier1.backbone.user.common.Status;

/**
 *
 */

public class TokenBroker implements Runnable
{
    /**
     *
     */
    @Override
    public void run() { try { trace(); whirl(); } catch ( Exception e ) { Common.trace ( e ); } }

    /**
     *
     */

    public void trace() {}

    /**
     *
     */

    public void whirl() throws Exception
    {
    JDBC jdbc = new JDBC ( this );

    try {

        whirl ( jdbc, "delete from jet_base_token where token_expiry < now()" );

        whirl ( jdbc, "delete jet_base_user.* from jet_base_user left join jet_base_token on token_identifier = user_id where user_status = "+ Status.PENDING + " and isnull(token_id)" );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    public void whirl ( JDBC jdbc, String query )

    { try { QueryYard.delete ( jdbc, query ); } catch ( Exception e ) { Common.trace ( this, e ); } }

}
