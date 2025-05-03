package ca.jetsphere.core.tier1.backbone.brand;

import ca.jetsphere.core.jdbc.JDBC;

import java.sql.ResultSet;

/**
 *
 */

public class Brand extends ca.jetsphere.core.tier0.backbone.brand.Brand
{
    /**
     *
     */

    public Brand() { super (); }

    /**
     *
     */

    public Brand ( JDBC jdbc, int brand_id ) { super ( jdbc, brand_id ); }

    /**
     *
     */

    public Brand ( JDBC jdbc, ResultSet rs ) throws Exception { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "name", "url", "logo", "background", "copyright", "default", "last.update", "actions" }; }

    /**
     *
     */

    static public String [] fields()

    { return new String [] { "brand_name", "brand_url", "brand_logo", "brand_background", "brand_copyright", "brand_default", "brand_last_update", "brand_uuid" }; }

}