package ca.jetsphere.core.tier0.backbone.company;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class CompanyMap extends BoltMap
{
    /**
     *
     */

    public CompanyMap() { super(); }

    /**
     *
     */

    public CompanyMap ( JDBC jdbc ) { query ( jdbc, -1 ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int company_id )

    { query ( jdbc, "select * from jet_base_company" + ( company_id > 0 ? " where company_id = " + company_id : "" ) ); }

    /**
     *
     */

    public Company getCompany ( int id ) { return ( Company ) get ( id ); }

    /**
     *
     */

    public String getKey() { return Company.key(); }

}
