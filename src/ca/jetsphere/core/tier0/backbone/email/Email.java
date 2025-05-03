package ca.jetsphere.core.tier0.backbone.email;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class Email extends ca.jetsphere.core.tier0.backbone.email.foreign.Email
{
    /**
     *
     */

    public Email() { clear(); }

    /**
     *
     */

    public Email ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getEmailDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getEmailDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getEmailDao().update ( jdbc, this ); }

}
