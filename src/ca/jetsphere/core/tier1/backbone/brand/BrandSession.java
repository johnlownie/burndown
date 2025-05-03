package ca.jetsphere.core.tier1.backbone.brand;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.brand.BrandMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class BrandSession extends BrandMap implements ISessionObject
{
    /**
     *
     */

    public BrandSession () { super(); }

    /**
     *
     */

    public BrandSession ( JDBC jdbc, int company_id ) { super ( jdbc, company_id ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Brand ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return Brand.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Brand() ); }

    /**
     *
     */

    public String [] fields() { return Brand.fields(); }

    /**
     *
     */

    static public BrandSession getInstance ( HttpServletRequest request )

    { return ( BrandSession ) SessionCache.getSessionObject ( request, Brand.key (), BrandSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public Brand getSelected ( HttpServletRequest request ) { return ( Brand ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public BrandSession query ( JDBC jdbc, HttpServletRequest request, int company_id, boolean blank )

    { BrandSession session = getInstance ( request ); session.find ( jdbc, company_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, Brand selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}
