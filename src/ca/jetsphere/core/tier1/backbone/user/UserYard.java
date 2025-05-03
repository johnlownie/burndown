package ca.jetsphere.core.tier1.backbone.user;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.jdbc.QueryYard;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */

public class UserYard
{
    /**
     *
     */

    static public void copy ( User from, User to ) throws Exception
    {
    to.setId        ( from.getId       () );
    to.setUuid ( from.getUuid () );
    to.setUsername  ( from.getUsername () );
    to.setPassword  ( from.getPassword () );
    to.setStatus    ( from.getStatus   () );
    to.setLastLogin ( from.getLastLogin() );
    }

    /**
     *
     */

    static public void delete ( JDBC jdbc, int user_id ) throws Exception

    { User user = new User ( jdbc, user_id ); user.delete ( jdbc ); }

    /**
     *
     */

    static public boolean equals ( User x, User y )

    { return x.getId() == y.getId() && x.getUuid().compareTo ( y.getUuid() ) == 0 &&  x.getUsername().compareTo ( y.getUsername() ) == 0 && x.getCreated().equals ( y.getCreated() ); }

    /**
     *
     */

    static public UserSession get ( JDBC jdbc, HttpServletRequest request, int start, int length, int order_column, String order_dir, String search )
    {
    UserSession users = UserSession.getInstance ( request );

    StringBuilder sb = new StringBuilder();

    sb.append ( "select *" );
    sb.append ( " from jet_base_user" );

    if ( !DockYard.isWhiteSpace ( search ) ) sb.append ( " where (user_username like " + DockYard.quote ( "%" + search + "%" )  + ")" );

    sb.append ( " order by " + User.fields()[ order_column ] ); if ( DockYard.equalsIgnoreCase ( "desc", order_dir ) ) sb.append ( " desc" );
    sb.append ( " limit " + start + ", " + ( start + length ) );

    users.query ( jdbc, sb.toString() );

    return users;
    }

    /**
     *
     */

    static public User getByUsername ( JDBC jdbc, String username )

    { return getUser ( jdbc, "select * from jet_base_user where user_username = ? ", username ); }

    /**
     *
     */

    static public User getByCompanyMemberId ( JDBC jdbc, int company_member_id )
    {
    User user = new User();

    String query = "select jet_base_user.* from jet_base_user inner join jet_base_company_member on company_member_user_id = user_id where company_member_id = " + company_member_id;

    user.query ( jdbc, query );

    return user;
    }

    /**
     *
     */

    static public User getByEmail ( JDBC jdbc, String email )

    { return getUser ( jdbc, "select jet_base_user.* from jet_base_user inner join jet_base_company_member on company_member_user_id = user_id where company_member_email = ?", email ); }

    /**
     *
     */

    static public User getByUuid ( JDBC jdbc, String uuid )

    { return getUser ( jdbc, "select * from jet_base_user where user_uuid = ? ", uuid ); }

    /**
     *
     */

    static public int getCount ( JDBC jdbc )
    {
    String query = "select count(distinct user_id) from jet_base_user";

    return QueryYard.query ( jdbc, query );
    }

    /**
     *
     */

    static public int getCount ( JDBC jdbc, String search )
    {
    StringBuilder sb = new StringBuilder();

    sb.append ( " select count(distinct user_id)" );
    sb.append ( " from jet_base_user" );

    if ( !DockYard.isWhiteSpace ( search ) ) sb.append ( " where (user_username like " + DockYard.quote ( "%" + search + "%" ) + ")" );

    return QueryYard.query ( jdbc, sb.toString() );
    }

    /**
     *
     */

    static public String[] getProfile ( HttpServletRequest request )
    {
    User whoAmI = whoAmI ( request ); String[] profile = new String[] { "", "" };

    if ( whoAmI == null | !whoAmI.isValid() ) return profile;

    CompanyMember companyMember = whoAmI.getCompanyMember();

    if ( companyMember == null || !companyMember.isValid() ) return profile;

    profile[0] = "images/avatars/av" + companyMember.getAvatarId() + ".png";

    profile[1] = DockYard.isWhiteSpace ( companyMember.getLastname() ) || DockYard.isWhiteSpace ( companyMember.getFirstname() ) ? whoAmI.getUsername() : Caption.get ( request, "company.member.name.first.last", new String[]{ DockYard.clipAt ( companyMember.getFirstname(), 20 ), DockYard.clipAt ( companyMember.getLastname(), 20 ) } );

    return profile;
    }

    /**
     *
     */

    static public User getUser ( JDBC jdbc, String query, String parameter )
    {
    PreparedStatement ps = null; ResultSet rs = null;

    try {

        ps = jdbc.prepareStatement ( query ); ps.setString ( 1, parameter ); rs = ps.executeQuery(); return rs.next() ? new User ( jdbc, rs ) : new User();

    } catch ( Exception e ) { Common.trace ( e ); return null; }

    finally { try { if ( rs != null ) rs.close(); } catch ( SQLException e ) { ; } try { if ( ps != null ) ps.close(); } catch ( SQLException e ) { ; } }
    }

    /**
     *
     */

    static public String getUsername ( HttpServletRequest request )

    { User whoAmI = whoAmI ( request ); return whoAmI != null ? whoAmI.getUsername() : ""; }

    /**
     *
     */

    static public boolean isLoggedIn ( HttpServletRequest request )
    {
    User whoAmI = whoAmI ( request );

    return ( whoAmI != null && whoAmI.isValid() );
    }

    /**
     *
     */

    static public boolean isValid ( User user )
    {
    JDBC jdbc = new JDBC();

    try {

        return isValid ( jdbc, user );

    } catch ( Exception e ) { Common.trace ( e ); return false; }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    static public boolean isValid ( JDBC jdbc, User user )
    {
    if ( user == null || ! user.isValid() ) return false;

    User x = new User ( jdbc, user.getId() );

    return equals ( x, user );
    }

    /**
     *
     */

    static public void setLastLogin ( JDBC jdbc, User user ) throws Exception

    { user.setLastLogin ( DockYard.now() ); user.save ( jdbc ); }

    /**
     *
     */

    static public User whoAmI ( HttpServletRequest request )
    {
    HttpSession session = request.getSession ( false );

    return session == null || session.getAttribute ( "[whoAmI]" ) == null ? new User() : ( User ) session.getAttribute ( "[whoAmI]" );
    }

    /**
     *
     */

    static public void whoAmI ( HttpServletRequest request, User user )

    { DockYard.setAttribute ( request, "[whoAmI]", user ); }

}
