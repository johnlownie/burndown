package ca.jetsphere.core.tier1.backbone.company_member;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.company_member.CompanyMemberMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */

public class CompanyMemberSession extends CompanyMemberMap implements ISessionObject
{
    /**
     *
     */

    public CompanyMemberSession() { super(); }

    /**
     *
     */

    public CompanyMemberSession ( JDBC jdbc, int company_id )  { this(); query ( jdbc, company_id ); }

    /**
     *
     */

    public CompanyMemberSession ( JDBC jdbc, String query ) { this(); query ( jdbc, query ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new CompanyMember ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return CompanyMember.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new CompanyMember() ); }

    /**
     *
     */

    public String [] fields() { return CompanyMember.fields(); }

    /**
     *
     */

    static public CompanyMemberSession getInstance ( HttpServletRequest request )

    { return ( CompanyMemberSession ) SessionCache.getSessionObject ( request, CompanyMember.key(), CompanyMemberSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public CompanyMember getSelected ( HttpServletRequest request ) { return ( CompanyMember ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public CompanyMemberSession query ( JDBC jdbc, HttpServletRequest request, int company_id, boolean blank )

    { CompanyMemberSession session = getInstance ( request ); session.find ( jdbc, company_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, CompanyMember selected ) { getInstance ( request ).setNonQualifiedSelected ( request, selected ); }

}

