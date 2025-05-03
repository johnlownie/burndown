package ca.jetsphere.core.tier0.backbone.brand;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class Brand extends ca.jetsphere.core.tier0.backbone.brand.foreign.Brand
{
    /**
     *
     */

    public Brand() { clear(); }

    /**
     *
     */

    public Brand ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getBrandDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getBrandDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getBrandDao().update ( jdbc, this ); }

}