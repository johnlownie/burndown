package ca.jetsphere.core.tier0.backbone.period;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.jdbc.PreparedStatement;
import ca.jetsphere.core.tier0.AbstractDao;

import java.sql.Timestamp;

/**
 *
 */

public class PeriodDao extends AbstractDao
{
    /**
     *
     */

    public boolean delete ( JDBC jdbc, Period period ) throws Exception

    { return delete ( jdbc, period, "delete from jet_base_period where period_id = " + period.getId() ); }

    /**
     *
     */

    public void insert ( JDBC jdbc, Bolt bolt, PreparedStatement ps ) throws Exception
    {
    Period period = ( Period ) bolt;

    String s = "insert into jet_base_period "
            + "( period_uuid, period_application_id, period_name, period_code, period_start_date, period_end_date, period_notes, period_type_id, period_ordinal, period_closed, period_last_update, period_created )"
            + "values (?,?,?,?,?,?,?,?,?,?,?,?)";

    ps.setStatement ( s, new String [] { "period_id" } );

    period.setUuid ( UUID.get() ); period.setCreated ( new Timestamp( System.currentTimeMillis() ) ); period.setLastUpdate ( period.getCreated() );

    ps.setString    (  1, period.getUuid          () );
    ps.setInt       (  2, period.getApplicationId () );
    ps.setString    (  3, period.getName          () );
    ps.setString    (  4, period.getCode          () );
    ps.setDate      (  5, new java.sql.Date       ( period.getStartDate().getTime() ) );
    ps.setDate      (  6, new java.sql.Date       ( period.getEndDate  ().getTime() ) );
    ps.setString    (  7, period.getNotes         () );
    ps.setInt       (  8, period.getTypeId        () );
    ps.setInt       (  9, period.getOrdinal       () );
    ps.setBoolean   ( 10, period.getClosed        () );
    ps.setTimestamp ( 11, period.getLastUpdate    () );
    ps.setTimestamp ( 12, period.getCreated       () );
    }

    /**
     *
     */

    public void update ( JDBC jdbc, Bolt bolt, PreparedStatement ps, boolean ts ) throws Exception
    {
    Period period = ( Period ) bolt;

    String s = "update jet_base_period "
            + "set period_uuid = ?, period_application_id = ?, period_name = ?, period_code = ?, period_start_date = ?, period_end_date = ?, period_notes = ?, period_type_id = ?, period_ordinal = ?, period_closed = ?, period_last_update = ? "
            + "where period_id = ?";

    ps.setStatement ( s );

    period.setLastUpdate ( new Timestamp ( System.currentTimeMillis() ) );

    ps.setString    (  1, period.getUuid          () );
    ps.setInt       (  2, period.getApplicationId () );
    ps.setString    (  3, period.getName          () );
    ps.setString    (  4, period.getCode          () );
    ps.setDate      (  5, new java.sql.Date       ( period.getStartDate().getTime() ) );
    ps.setDate      (  6, new java.sql.Date       ( period.getEndDate  ().getTime() ) );
    ps.setString    (  7, period.getNotes         () );
    ps.setInt       (  8, period.getTypeId        () );
    ps.setInt       (  9, period.getOrdinal       () );
    ps.setBoolean   ( 10, period.getClosed        () );
    ps.setTimestamp ( 11, period.getLastUpdate    () );
    ps.setInt       ( 12, period.getId            () );
    }

}
