package ca.jetsphere.core.tier1.backbone.company_member;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import ca.jetsphere.core.tier1.backbone.company.Company;

import java.sql.ResultSet;

/**
 *
 */

public class CompanyMember extends ca.jetsphere.core.tier0.backbone.company_member.CompanyMember
{
    Company company; String username;

    /**
     *
     */

    public CompanyMember() { super(); }

    /**
     *
     */

    public CompanyMember ( CompanyMember companyMember ) { this(); copy ( companyMember ); }

    /**
     *
     */

    public CompanyMember ( JDBC jdbc, int company_member_id ) { super ( jdbc, company_member_id ); }

    /**
     *
     */

    public CompanyMember ( JDBC jdbc, ResultSet rs ) throws Exception { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "first.name", "last.name", "email", "username", "last.update", "actions" }; }

    /**
     *
     */

    public int compareTo ( Object o )
    {
    CompanyMember member = ( CompanyMember ) o;

    int cc = DockYard.compareTo ( getLastname().toLowerCase(), member.getLastname().toLowerCase() ); if ( cc == 0 ) cc = DockYard.compareTo ( getFirstname().toLowerCase(), member.getFirstname().toLowerCase() );

    return cc != 0 ? cc : DockYard.compareTo ( getLastname(), member.getLastname() );
    }

    /**
     *
     */

    static public String [] fields() { return new String [] { "company_member_firstname", "company_member_lastname", "company_member_email", "company_member_user_id", "company_member_last_update", "company_member_uuid" }; }

    /**
     *
     */

    public void foreign ( JDBC jdbc ) throws Exception

    { setCompany ( new Company ( jdbc, getCompanyId() ) ); setUsername ( jdbc, getUserId() ); }

    /**
     *
     */

    public Object get ( String s )
    {
    if ( "company_member_user_id" .equals ( s ) ) return getUsername();

    return super.get ( s );
    }

    /**
     *
     */

    public Company getCompany  () { return company  ; }
    public String  getUsername () { return username ; }

    /**
     *
     */

    public String getAddress() { return getStreet() + " " + getCity() + "," + getProvince(); }

    /**
     *
     */

    public String getFirstLast() { return getFirstname() + " " + getLastname(); }

    /**
     *
     */

    public void setCompany  ( Company company  ) { this.company  = company  ; }
    public void setUsername ( String  username ) { this.username = username ; }

    /**
     *
     */

    public void setUsername ( JDBC jdbc, int user_id )
    {
    String query = "select user_username from jet_base_user where user_id = " + user_id;

    setUsername ( QueryYard.query ( jdbc, query, 1 ) );
    }

}
