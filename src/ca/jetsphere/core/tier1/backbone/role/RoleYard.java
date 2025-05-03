package ca.jetsphere.core.tier1.backbone.role;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.bu_member.BuMember;
import ca.jetsphere.core.tier1.backbone.bu_member.BuMemberSession;
import ca.jetsphere.core.tier1.backbone.bu_member.BuMemberYard;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMember;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMemberYard;
import ca.jetsphere.core.tier1.backbone.period.Period;
import ca.jetsphere.core.tier1.backbone.period.PeriodSession;
import ca.jetsphere.core.tier1.backbone.role.common.Type;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */

public class RoleYard
{
    /**
     *
     */

    static public void delete ( JDBC jdbc, int application_id ) throws Exception
    {
    RoleSession roles = new RoleSession ( jdbc, application_id );

    Iterator it = roles.iterator ( true );

    while ( it.hasNext() )

    { Role role = ( Role ) it.next(); role.delete ( jdbc ); }

    }

    /**
     *
     */

    static public Role emptyRole()
    {
    Role role = new Role();

    role.setName ( "empty.role" );

    return role;
    }

    /**
     *
     */

    static public RoleSession getBuRoles ( JDBC jdbc, HttpServletRequest request, User user ) throws Exception
    {
    RoleSession roles = new RoleSession();

    Period period = PeriodSession.getSelected( request );

    CompanyMember companyMember = CompanyMemberYard.getByUserId ( jdbc, user.getId () );

    BuMemberSession buMembers = BuMemberYard.get ( jdbc, period.getId(), companyMember.getId() );

    Iterator it = buMembers.iterator ( false );

    while ( it.hasNext() )
    {
    BuMember buMember = ( BuMember ) it.next();

    String[] role_ids = buMember.getRoleIds ().split ( "\\|" );

    for ( String role_id: role_ids )

    { Role role = new Role ( jdbc, DockYard.toInt ( role_id ) ); if ( role != null && role.isValid() ) roles.add ( role ); }

    }

    return roles;
    }

    /**
     *
     */

    static public RoleSession getBuRoles ( JDBC jdbc, HttpServletRequest request ) throws Exception
    {
    Application application = ApplicationSession.getSelected ( request );

    String query = "select * from jet_base_role where role_application_id = " + application.getId() + " and role_type = " + Type.BU ;

    return query ( jdbc, request, query );
    }

    /**
     *
     */

    static public RoleSession getCompanyRoles ( JDBC jdbc, HttpServletRequest request, boolean common ) throws Exception
    {
    Application application = ApplicationSession.getSelected ( request );

    String query = "select * from jet_base_role";

    query += common ? " where role_type <= " + Type.COMPANY : " where role_application_id = " + application.getId() + " and role_type = " + Type.COMPANY;

    return query ( jdbc, request, query );
    }

    /**
     *
     */

    static public Role getMemberRole ( JDBC jdbc )
    {
    Role role = new Role();

    String query = "select * from jet_base_role where role_alias = " + DockYard.quote ( "MEMBER" );

    role.query ( jdbc, query );

    return role;
    }

    /**
     *
     */

    static public String getRoleNames ( String role_ids )
    {
    if ( DockYard.isWhiteSpace ( role_ids ) ) return "";

    String query = "select group_concat(role_name) from jet_base_role where role_id in (" + role_ids + ")";

    return QueryYard.query ( query, 1 );
    }

    /**
     *
     */

    static public RoleSession getRoles ( JDBC jdbc, HttpServletRequest request ) throws Exception
    {
    RoleSession roles = getRoles ( jdbc, request, UserYard.whoAmI ( request ) );

    if ( roles.isEmpty() ) roles.add ( emptyRole() );

    return roles;
    }

    /**
     *
     */

    static public RoleSession getRoles ( JDBC jdbc, HttpServletRequest request, User user ) throws Exception
    {
    RoleSession roles = new RoleSession();

    roles.add ( getUserRoles ( jdbc, request, user ) ); roles.add ( getBuRoles ( jdbc, request, user ) );

    return roles;
    }

    /**
     *
     */

    static public RoleSession getUserRoles ( JDBC jdbc, HttpServletRequest request, User user ) throws Exception
    {
    RoleSession roles = new RoleSession();

    for ( String s : user.getRoles() )
    {
    int role_id = DockYard.toInteger ( s );

    Role role = new Role ( jdbc, role_id );

    if ( role != null && role.isValid() ) roles.add ( role );
    }

    Role member = getMemberRole ( jdbc ); roles.add ( member );

    return roles;
    }

    /**
     *
     */

    static public RoleSession getRoles ( JDBC jdbc, HttpServletRequest request, int application_id, int role_type ) throws Exception

    { return getRoles ( jdbc, application_id, RoleSession.getInstance ( request ), role_type ); }

    /**
     *
     */

    static public RoleSession getRoles ( JDBC jdbc, int application_id, RoleSession roles, int role_type ) throws Exception
    {
    roles.query ( jdbc, getQuery ( application_id, role_type ) );

    return roles;
    }

    /**
     *
     */

    static public String getQuery ( int application_id, int role_type )

    { return getQuery( application_id, role_type, "" ); }

    /**
     *
     */

    static public String getQuery ( int application_id, int role_type, String role_alias )

    { return "select * from jet_base_role where ( role_application_id = -1 or role_application_id = " + application_id + " ) and " + getQuery ( role_type ) + ( ! DockYard.isWhiteSpace ( role_alias ) ? " and role_alias like '" + role_alias + "%'" : "" ); }

    /**
     *
     */

    static public String getQuery ( int role_type )
    {
    StringBuilder sb = new StringBuilder();

    sb.append ( " ( " );

    if ( ( role_type & Type.SYSTEM    ) != 0 ) sb.append ( " OR role_type = " + Type.SYSTEM  );

    if ( ( role_type & Type.SITE      ) != 0 ) sb.append ( " OR role_type = " + Type.SITE    );

    if ( ( role_type & Type.COMPANY   ) != 0 ) sb.append ( " OR role_type = " + Type.COMPANY );

    if ( ( role_type & Type.BU        ) != 0 ) sb.append ( " OR role_type = " + Type.BU      );

    sb.append ( " ) " );

    String s = sb.toString();

    s = s.replaceFirst ( "OR", "" );

    return s;
    }

    /**
     *
     */

    static public boolean hasRole ( HttpServletRequest request, User user, String [] aliases )
    {
    JDBC jdbc = new JDBC();

    try {

        return hasRole ( getRoles ( jdbc, request, user ), aliases );

    } catch ( Exception e ) { Common.trace ( e ); return false; }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    static public boolean hasRole ( JDBC jdbc, HttpServletRequest request, User user, String [] aliases )
    {
    try {

        return hasRole ( getRoles ( jdbc, request, user ), aliases );

    } catch ( Exception e ) { Common.trace ( e ); return false; }

    }

    /**
     *
     */

    static public boolean hasRole ( RoleSession roles, String [] aliases ) throws Exception
    {
    Iterator it = roles.iterator();

    while ( it.hasNext() )

    { Role role = ( Role ) it.next(); if ( DockYard.contains ( aliases, role.getAlias() )  ) return true; }

    return false;
    }

    /**
     *
     */

    static public void inclusiveFilter ( RoleSession roles, int type )
    {
    Iterator it = roles.iterator ( false );

    while ( it.hasNext() )
    {
    Role role = ( Role ) it.next();

    if ( role.getType() != type ) roles.remove ( role );
    }

    }

    /**
     *
     */

    static public void inclusiveFilter ( RoleSession roles, String alias )
    {
    Iterator it = roles.iterator ( false );

    while ( it.hasNext() )
    {
    Role role = ( Role ) it.next();

    if ( ! role.getAlias().startsWith ( alias ) ) roles.remove ( role );
    }

    }

    /**
     *
     */

    static public RoleSession query ( JDBC jdbc, HttpServletRequest request, String query ) throws Exception

    { RoleSession roles = RoleSession.getInstance ( request ); roles.query ( jdbc, query ); return roles; }

}
