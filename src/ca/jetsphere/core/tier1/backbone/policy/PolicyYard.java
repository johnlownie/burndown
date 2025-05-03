package ca.jetsphere.core.tier1.backbone.policy;

import java.util.Iterator;
import java.util.List;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class PolicyYard
{
    /**
     *
     */

    static public boolean contains ( List list, String s )
    {
    Iterator it = list.iterator();

    while ( it.hasNext() )

    if ( DockYard.compareTo ( ( String ) it.next (), s ) == 0 ) return true;

    return false;
    }

    /**
     *
     */

    static public int get ( HttpServletRequest request, String name ) throws Exception
    {
    PolicySession policies = PolicySession.getInstance ( request );

    Policy policy = ( Policy ) policies.getBoltByName ( name );

    return policy != null && policy.isValid() ? policy.getValue(): -1;
    }

    /**
     *
     */

    static public void delete ( JDBC jdbc, int company_id ) throws Exception
    {
    PolicySession policies = new PolicySession ( jdbc, company_id );

    Iterator it = policies.iterator ( true );

    while ( it.hasNext() )

    { Policy policy = ( Policy ) it.next(); policy.delete ( jdbc ); }

    }

}
