package ca.jetsphere.burndown.tier0.backbone.account;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.burndown.tier0.Registry;
import java.sql.ResultSet;

/**
 *
 */
public class Account extends ca.jetsphere.burndown.tier0.backbone.account.foreign.Account
{
    /**
     *
     */
    public Account() { clear(); }

    /**
     *
     */
    public Account ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */
    public void delete ( JDBC jdbc ) throws Exception { Registry.getAccountDao().delete ( jdbc, this ); }

    /**
     *
     */
    public String getKey() { return key(); }

    /**
     *
     */
    public void insert ( JDBC jdbc ) throws Exception { AccountDao accountDao = Registry.getAccountDao(); accountDao.insert ( jdbc, this ); }

    /**
     *
     */
    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getAccountDao().update ( jdbc, this ); }
}
