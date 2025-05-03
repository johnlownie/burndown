package ca.jetsphere.core.tier1.backbone.company;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.company.CompanyMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class CompanySession extends CompanyMap implements ISessionObject
{
    /**
     *
     */

    public CompanySession() { super(); }

    /**
     *
     */

    public CompanySession ( JDBC jdbc ) { super ( jdbc ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Company ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return Company.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Company() ); }

    /**
     *
     */

    public String [] fields() { return Company.fields(); }

    /**
     *
     */

    static public CompanySession getInstance ( HttpServletRequest request )

    { return ( CompanySession ) SessionCache.getSessionObject ( request, Company.key(), CompanySession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public Company getSelected ( HttpServletRequest request ) { return ( Company ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public CompanySession query ( JDBC jdbc, HttpServletRequest request, int company_id, boolean blank )

    { CompanySession session = getInstance ( request ); session.find ( jdbc, company_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, Company selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}
