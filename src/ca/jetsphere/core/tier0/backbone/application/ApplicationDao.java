package ca.jetsphere.core.tier0.backbone.application;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */

public class ApplicationDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, Application application ) throws Exception
    {
    // PeriodDelete.delete ( jdbc, application.getId() );

    return delete ( jdbc, application, "delete from jet_base_application where application_id = " + application.getId() );
    }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Application application = ( Application ) bolt;

    String s = "insert into jet_base_application "
            + "( application_uuid, application_company_id, application_name, application_notes, application_default, application_period_id, application_role_id, application_closed, application_eula, application_last_update, application_created )"
            + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    ps.setStatement ( s, new String [] { "application_id" } );

    application.setUuid ( UUID.get() ); application.setCreated ( new Timestamp( System.currentTimeMillis() ) ); application.setLastUpdate ( application.getCreated() );

    ps.setString    (  1, application.getUuid       () );
    ps.setInt ( 2, application.getCompanyId () );
    ps.setString ( 3, application.getName () );
    ps.setString    (  4, application.getNotes () );
    ps.setBoolean ( 5, application.getDefault () );
    ps.setInt ( 6, application.getPeriodId () );
    ps.setInt       (  7, application.getRoleId     () );
    ps.setBoolean   (  8, application.getClosed     () );
    ps.setInt       (  9, application.getEula       () );
    ps.setTimestamp ( 10, application.getLastUpdate () );
    ps.setTimestamp ( 11, application.getCreated    () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Application application = ( Application ) bolt;

    String s = "update jet_base_application "
            + "set application_uuid = ?, application_company_id = ?, application_name = ?, application_notes = ?, application_default = ?, application_period_id = ?, application_role_id = ?, application_closed = ?, application_eula = ?, application_last_update = ? "
            + "where application_id = ?";

    ps.setStatement ( s );

    application.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, application.getUuid       () );
    ps.setInt       (  2, application.getCompanyId  () );
    ps.setString    (  3, application.getName       () );
    ps.setString    (  4, application.getNotes      () );
    ps.setBoolean   (  5, application.getDefault    () );
    ps.setInt       (  6, application.getPeriodId   () );
    ps.setInt       (  7, application.getRoleId     () );
    ps.setBoolean   (  8, application.getClosed     () );
    ps.setInt       (  9, application.getEula       () );
    ps.setTimestamp ( 10, application.getLastUpdate () );
    ps.setInt       ( 11, application.getId         () );
    }

}
