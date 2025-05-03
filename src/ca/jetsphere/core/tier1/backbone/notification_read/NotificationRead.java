package ca.jetsphere.core.tier1.backbone.notification_read;

import ca.jetsphere.core.jdbc.JDBC;

import java.sql.ResultSet;

/**
 *
 */

public class NotificationRead  extends ca.jetsphere.core.tier0.backbone.notification_read.NotificationRead
{

    /**
     *
     */

    public NotificationRead() { super(); }

    /**
     *
     */

    public NotificationRead ( JDBC jdbc, int notification_read_id ) throws Exception { super ( jdbc, notification_read_id ); }

    /**
     *
     */

    public NotificationRead ( JDBC jdbc, ResultSet rs ) throws Exception

    { this(); get ( jdbc, rs ); }

    /**
     *
     */

    static public String [] captions() { return new String [] { "id", "id", "actions" }; }

    /**
     *
     */

    static public String [] fields()

    { return new String [] { "notification_read_notification_id", "notification_read_company_member_id", "notification_uuid" }; }

    /**
     *
     */

    public String getKey() { return key(); }
}
