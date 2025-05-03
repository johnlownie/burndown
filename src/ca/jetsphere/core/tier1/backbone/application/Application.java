package ca.jetsphere.core.tier1.backbone.application;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.period.Period;
import ca.jetsphere.core.tier1.backbone.period.PeriodYard;
import ca.jetsphere.core.tier1.backbone.role.Role;
import ca.jetsphere.core.tier1.backbone.role.RoleYard;

import java.sql.ResultSet;

/**
 *
 */

public class Application extends ca.jetsphere.core.tier0.backbone.application.Application
{
    String application_period_name  ;
    String application_role_name    ;

    /**
     *
     */

    public Application() { super(); }

    /**
     *
     */

    public Application ( JDBC jdbc, int application_id ) { super ( jdbc, application_id ); }

    /**
     *
     */

    public Application ( JDBC jdbc, ResultSet rs ) throws Exception { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "name", "company", "default.period", "default.role", "closed", "last.update", "actions" }; }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception
    {
    PeriodYard .delete ( jdbc, getId() );

    RoleYard   .delete ( jdbc, getId() );

    super.delete ( jdbc );
    }

    /**
     *
     */

    static public String [] fields()

    { return new String [] { "application_name", "application_company_id", "application_period_id", "application_role_id", "application_closed", "application_last_update", "application_uuid" }; }

    /**
     *
     */

    public void foreign ( JDBC jdbc ) throws Exception

    { setCompany ( new Company ( jdbc, getCompanyId() ) ); setPeriodName ( new Period ( jdbc, getPeriodId() ).getName() ); setRoleName ( new Role ( jdbc, getRoleId() ).getName() ); }

    /**
     *
     */

    public Object get ( String s )
    {
    if ( "application_company_id".equals ( s ) ) return getCompany().getName();

    if ( "application_period_id" .equals ( s ) ) return getPeriodName();

    if ( "application_role_id"   .equals ( s ) ) return getRoleName  ();

    return super.get ( s );
    }

    /**
     *
     */

    public String getPeriodName () { return application_period_name  ; }
    public String getRoleName   () { return application_role_name    ; }

    /**
     *
     */

    public void setPeriodName  ( String application_period_name  ) { this.application_period_name  = application_period_name  ; }
    public void setRoleName    ( String application_role_name    ) { this.application_role_name    = application_role_name    ; }

}