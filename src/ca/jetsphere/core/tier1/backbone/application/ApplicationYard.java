package ca.jetsphere.core.tier1.backbone.application;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.QueryYard;
import ca.jetsphere.core.tier1.backbone.company.Company;
import ca.jetsphere.core.tier1.backbone.company.CompanyYard;
import ca.jetsphere.core.tier1.backbone.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * 
 */

public class ApplicationYard
{
    /**
     *
     */

    static public void delete ( JDBC jdbc, int company_id ) throws Exception
    {
    ApplicationSession applications = new ApplicationSession ( jdbc, company_id );

    Iterator it = applications.iterator ( true );

    while ( it.hasNext() )

    { Application application = ( Application ) it.next(); application.delete ( jdbc ); }

    }

    /**
     *
     */

    static public Application getDefaultApplication ( JDBC jdbc, HttpServletRequest request ) throws Exception
    {
    Company company = CompanyYard.getDefault ( jdbc ); Application application = getDefaultApplication ( jdbc, company.getId() );

    if ( application.isValid() ) return application; else ApplicationSession.query ( jdbc, request, company.getId(), false );

    return ApplicationSession.getSelected ( request );
    }

    /**
     *
     */

    static public Application getDefaultApplication ( JDBC jdbc, int company_id )
    {
    Application application = new Application();

    application.query ( jdbc, "select * from jet_base_application where application_company_id = " + company_id + " and application_default = 1" );

    return application;
    }
    
    /**
     * 
     */
    
    static public String getName ( int application_id )
    {
    String query = "select application_name from jet_base_application where application_id = " + application_id;
    
    return QueryYard.query ( query, 1 );
    }
    
    /**
     * 
     */
    static public boolean hasPeriod ( JDBC jdbc, int application_id, int period_id )
    {
    String query = "select count(1) from jet_base_period where period_id = " + period_id + " and period_application_id = " + application_id;
    
    return QueryYard.query ( jdbc, query ) > 0;
    }

    /**
     *
     */

    static public void setApplications ( JDBC jdbc, HttpServletRequest request, User user )
    {
    ApplicationSession applications = ApplicationSession.getInstance ( request );
    
    StringBuffer sb = new StringBuffer();

    sb.append ( "select distinct jet_base_application.* from jet_base_user" );
    sb.append ( " inner join jet_base_role on find_in_set (role_id, user_role_ids)" );
    sb.append ( " inner join jet_base_application on application_company_id = role_company_id" );
    sb.append ( " where user_id = " + user.getId() );

    applications.query ( jdbc, sb.toString() );
    }

    /**
     *
     */

    static public void setDefaultApplication ( JDBC jdbc, HttpServletRequest request, User user ) throws Exception
    {
    Application application = new Application ( jdbc, user.getApplicationId() );
    
    if ( !application.isValid() ) application = getDefaultApplication ( jdbc, request );
    
    ApplicationSession.setSelected ( request, application ); 
    }

}
