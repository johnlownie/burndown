package ca.jetsphere.core.tier1.backbone.company;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.action.ActionYard;
import ca.jetsphere.core.tier1.backbone.application.ApplicationYard;
import ca.jetsphere.core.tier1.backbone.company_member.CompanyMemberYard;
import ca.jetsphere.core.tier1.backbone.policy.PolicyYard;

import java.sql.ResultSet;

/**
 *
 */

public class Company extends ca.jetsphere.core.tier0.backbone.company.Company
{
    /**
     *
     */

    public Company() { super(); }

    /**
     *
     */

    public Company ( JDBC jdbc, int company_id ) { super ( jdbc, company_id ); }

    /**
     *
     */

    public Company ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions()

    { return new String [] { "name", "street", "city", "province", "postal.code", "url", "email", "last.update", "actions" }; }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception
    {
    CompanyMemberYard.delete ( jdbc, getId() );

    ApplicationYard  .delete ( jdbc, getId() );

    ActionYard       .delete ( jdbc, getId() );

    PolicyYard       .delete ( jdbc, getId() );

    super.delete ( jdbc );
    }

    /**
     *
     */

    static public String [] fields()

    { return new String [] { "company_name", "company_street", "company_city", "company_province", "company_postal_code", "company_url", "company_email", "company_last_update", "company_uuid" }; }

    /**
     *
     */

    public String getKey() { return key(); }

}
