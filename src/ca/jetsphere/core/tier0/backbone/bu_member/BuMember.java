package ca.jetsphere.core.tier0.backbone.bu_member;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class BuMember extends ca.jetsphere.core.tier0.backbone.bu_member.foreign.BuMember
{
    /**
     *
     */

    public BuMember() { clear(); }

    /**
     *
     */

    public BuMember ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getBuMemberDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getBuMemberDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getBuMemberDao().update ( jdbc, this ); }

}
