package ca.jetsphere.core.tier0.backbone.company;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class Company extends ca.jetsphere.core.tier0.backbone.company.foreign.Company
{
    /**
     *
     */

    public Company() { clear(); }

    /**
     *
     */

    public Company ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getCompanyDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getCompanyDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getCompanyDao().update ( jdbc, this ); }

}
