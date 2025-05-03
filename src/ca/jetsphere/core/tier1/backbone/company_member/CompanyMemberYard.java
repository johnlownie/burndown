package ca.jetsphere.core.tier1.backbone.company_member;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier1.backbone.user.common.Status;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

/**
 *
 */

public class CompanyMemberYard
{
    /**
     *
     */

    static public void delete ( JDBC jdbc, int company_id ) throws Exception
    {
    CompanyMemberSession companyMembers = new CompanyMemberSession ( jdbc, company_id );

    Iterator it = companyMembers.iterator ( false );

    while ( it.hasNext() )

    { CompanyMember companyMember = ( CompanyMember ) it.next(); companyMember.delete ( jdbc ); }

    }

    /**
     *
     */

    static public CompanyMember getByEmail ( JDBC jdbc, String email )

    { return getCompanyMember ( jdbc, "select * from jet_base_company_member where company_member_email = ? ", email ); }

    /**
     *
     */

    static public CompanyMember getByUserId ( JDBC jdbc, int user_id )

    { return getCompanyMember ( jdbc, "select * from jet_base_company_member where company_member_user_id = ? ", user_id ); }

    /**
     *
     */

    static public CompanyMember getByUuid ( JDBC jdbc, String uuid )

    { return getCompanyMember ( jdbc, "select * from jet_base_company_member where company_member_uuid = ? ", uuid ); }

    /**
     *
     */

    static public CompanyMember getCompanyMember ( JDBC jdbc, String query, int parameter )
    {
    PreparedStatement ps = null; ResultSet rs = null;

    try {

        ps = jdbc.prepareStatement ( query ); ps.setInt ( 1, parameter ); rs = ps.executeQuery(); return rs.next() ? new CompanyMember ( jdbc, rs ) : new CompanyMember();

    } catch ( Exception e ) { Common.trace ( e ); return null; }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( ps != null ) ps.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    static public CompanyMember getCompanyMember ( JDBC jdbc, String query, String parameter )
    {
    PreparedStatement ps = null; ResultSet rs = null;

    try {

        ps = jdbc.prepareStatement ( query ); ps.setString(1, parameter); rs = ps.executeQuery(); return rs.next() ? new CompanyMember ( jdbc, rs ) : new CompanyMember();

    } catch ( Exception e ) { Common.trace ( e ); return null; }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( ps != null ) ps.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    static public void setActiveMembers ( JDBC jdbc, HttpServletRequest request, int company_id )
    {
    CompanyMemberSession members = CompanyMemberSession.getInstance ( request );

    members.query ( jdbc, "select * from jet_base_company_member inner join jet_base_user on user_id = company_member_user_id where company_member_company_id = " + company_id + " and user_status = " + Status.ACTIVE );
    }

}
