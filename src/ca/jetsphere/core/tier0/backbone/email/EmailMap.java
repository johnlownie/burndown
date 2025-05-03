package ca.jetsphere.core.tier0.backbone.email;

import ca.jetsphere.core.bolt.BoltMap;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

abstract public class EmailMap extends BoltMap
{
    /**
     *
     */

    public EmailMap() { super(); }

    /**
     *
     */

    public EmailMap ( JDBC jdbc, int id ) { query ( jdbc, id ); }

    /**
     *
     */

    public void find ( JDBC jdbc, int email_type_id )

    { query ( jdbc, "select * from jet_base_email" + ( email_type_id > 0 ? " where email_type_id = " + email_type_id : "" ) ); }

    /**
     *
     */

    public Email getEmail ( int id ) { return ( Email ) get ( id ); }

    /**
     *
     */

    public String getKey() { return Email.key(); }

}
