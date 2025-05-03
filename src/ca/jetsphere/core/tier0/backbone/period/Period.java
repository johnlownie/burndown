package ca.jetsphere.core.tier0.backbone.period;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;

/**
 *
 */

public class Period extends ca.jetsphere.core.tier0.backbone.period.foreign.Period
{
    /**
     *
     */

    public Period() { clear(); }

    /**
     *
     */

    public Period ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getPeriodDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getPeriodDao().insert ( jdbc, this ); }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getPeriodDao().update ( jdbc, this ); }

}
