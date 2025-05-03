package ca.jetsphere.core.tier0.backbone.brand;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class BrandMap extends BoltMap
{
    /**
     *
     */

    public BrandMap() { super(); }

    /**
     *
     */

    public BrandMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int company_id )

    { query ( jdbc, "select * from jet_brand" + ( company_id > 0 ? " where brand_company_id = " + company_id : "" ) ); }

    /**
     *
     */

    public String getKey() { return Brand.key (); }

}
