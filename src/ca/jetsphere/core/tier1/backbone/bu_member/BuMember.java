package ca.jetsphere.core.tier1.backbone.bu_member;

import java.sql.ResultSet;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.bu.Bu;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMember;
import ca.jetsphere.core.tier1.backbone.role.RoleYard;

/**
 *
 */

public class BuMember extends ca.jetsphere.core.tier0.backbone.bu_member.BuMember
{
    Bu bu_member_bu; CompanyMember bu_member_company_member; String[] bu_member_roles;

    /**
     *
     */

    public BuMember() { super(); }

    /**
     *
     */

    public BuMember ( JDBC jdbc, int bu_member_id ) { super( jdbc, bu_member_id ); }

    /**
     *
     */

    public BuMember ( int bu_id, int company_member_id )

    { this(); setBuId ( bu_id ); setCompanyMemberId ( company_member_id ); }

    /**
     *
     */

    public BuMember ( JDBC jdbc, String query ) { this (); query ( jdbc, query ); }

    /**
     *
     */

    public BuMember ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "bu", "bu.member", "role", "last.update", "actions" }; }

    /**
     *
     */

    public int compareTo ( Object o )
    {
    BuMember buMember = ( BuMember ) o;

    CompanyMember cm1 = ( CompanyMember ) getCompanyMember(); CompanyMember cm2 = ( CompanyMember ) buMember.getCompanyMember();

    if ( cm1 == null || cm2 == null ) return 0;

    int cc = DockYard.compareTo ( cm1.getLastname(), cm2.getLastname() ); if ( cc != 0 ) return cc; return DockYard.compareTo ( cm1.getFirstname(), cm2.getFirstname() );
    }

    /**
     *
     */

    static public String [] fields() { return new String [] { "bu_member_bu_id", "bu_member_company_member_id", "bu_member_roles", "bu_member_last_update", "bu_member_uuid" }; }

    /**
     *
     */

    public void foreign ( JDBC jdbc ) throws Exception

    { setBu ( new Bu ( jdbc, getBuId() ) ); setCompanyMember ( new CompanyMember ( jdbc, getCompanyMemberId () ) ); setRoles ( getRoleIds ().split ( "\\s*,\\s*" ) ); }

    /**
     *
     */

    public Object get ( String s )
    {
    if ( "bu_member_bu_id"                .equals ( s ) ) return getBu().getName();

    if ( "bu_member_company_member_id"    .equals ( s ) ) return getCompanyMember().getName();

    if ( "bu_member_company_member_email" .equals ( s ) ) return getCompanyMember().getEmail();

    if ( "bu_member_roles"                .equals ( s ) ) return RoleYard.getRoleNames ( getRoleIds() );

    return super.get( s );
    }

    /**
     *
     */

    public Bu            getBu           () { return bu_member_bu             ; }
    public CompanyMember getCompanyMember() { return bu_member_company_member ; }
    public String[]      getRoles        () { return bu_member_roles          ; }

    /**
     *
     */

//    public void save ( JDBC jdbc ) throws Exception { setRoleIds ( String.join ( ", ", getRoles() ) ); super.save ( jdbc ); }

    /**
     *
     */

    public void setBu            ( Bu            bu_member_bu             ) { this.bu_member_bu             = bu_member_bu             ; }
    public void setCompanyMember ( CompanyMember bu_member_company_member ) { this.bu_member_company_member = bu_member_company_member ; }
    public void setRoles         ( String[]      bu_member_roles          ) { this.bu_member_roles          = bu_member_roles          ; }
}
