package ca.jetsphere.core.tier0.backbone.company_member;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class CompanyMember extends ca.jetsphere.core.tier0.backbone.company_member.foreign.CompanyMember
{
    /**
     *
     */

    public CompanyMember() { clear(); }

    /**
     *
     */

    public CompanyMember ( JDBC jdbc, int id ) { Registry.get(jdbc, this, id); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getCompanyMemberDao().delete(jdbc, this); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public String getName() { return getFirstname() + " " + getLastname(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getCompanyMemberDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getCompanyMemberDao().update ( jdbc, this ); }

}
