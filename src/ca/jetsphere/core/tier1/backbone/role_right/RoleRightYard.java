package ca.jetsphere.core.tier1.backbone.role_right;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */

public class RoleRightYard
{
    /**
     *
     */

    static public void delete ( JDBC jdbc, int role_id ) throws Exception
    {
    RoleRightSession roleRights = new RoleRightSession ( jdbc, role_id );

    Iterator it = roleRights.iterator ( false );

    while ( it.hasNext() )

    { RoleRight roleRight = ( RoleRight ) it.next(); roleRight.delete ( jdbc ); }

    }

    /**
     *
     */

    static public int getCount ( JDBC jdbc, int role_id, int parent_id )

    { return QueryYard.query ( jdbc, "select count(1) from jet_base_role_right where role_right_role_id = " + role_id + " and role_right_parent_id = " + parent_id ); }

    /**
     *
     */

    static public List<String> getHrefs ( JDBC jdbc, int role_id )
    {
    ArrayList<String> list = new ArrayList<String>();

    RoleRightSession rights = getRights ( jdbc, role_id );

    Iterator it = rights.iterator ( true );

    while ( it.hasNext() )

    { RoleRight roleRight = ( RoleRight ) it.next(); list.add ( roleRight.getAction().getHref() ); }

    return list;
    }

    /**
     *
     */

    static public RoleRightSession getRights ( JDBC jdbc, int role_id )

    { return new RoleRightSession ( jdbc, role_id ); }


}
