package ca.jetsphere.core.tier0.backbone.company_member;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class CompanyMemberMap extends BoltMap
{
    /**
     *
     */

    public CompanyMemberMap() { super(); }

    /**
     *
     */

    public CompanyMemberMap ( JDBC jdbc, int id )  { this(); query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int company_id )

    { query ( jdbc, "select * from jet_base_company_member" + ( company_id > 0 ? " where company_member_company_id = " + company_id : "" ) ); }

    /**
     *
     */

    public CompanyMember getCompanyMember ( int id ) {  return ( CompanyMember ) get ( id ); }

    /**
     *
     */

    public String getKey() { return CompanyMember.key(); }

}
