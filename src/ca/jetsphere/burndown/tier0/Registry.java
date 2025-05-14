package ca.jetsphere.burndown.tier0;

import ca.jetsphere.burndown.tier0.backbone.account.AccountDao;
import ca.jetsphere.burndown.tier0.backbone.category.CategoryDao;
import ca.jetsphere.burndown.tier0.backbone.transaction.TransactionDao;
import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */
public class Registry extends ca.jetsphere.core.tier0.Registry
{
    private static ApplicationContext context;

    /**
     *
     */
    static
    {
    try {

        Registry.context = new ApplicationContext();

    } catch ( Exception e ) { Common.trace(e); }

    }

    /**
     *
     */
    static public void get ( JDBC jdbc, Bolt bolt, int id ) { bolt.query ( jdbc, "select * from jet_burndown_" + bolt.getKey() + " where " + bolt.getKey() + "_id = " + id ); }

    /**
     *
     */
    public static AccountDao     getAccountDao    () { return ( AccountDao     ) context.getBean ( "account"    , AccountDao    .class ); }
    public static CategoryDao    getCategoryDao   () { return ( CategoryDao    ) context.getBean ( "category"   , CategoryDao   .class ); }
    public static TransactionDao getTransactionDao() { return ( TransactionDao ) context.getBean ( "transaction", TransactionDao.class ); }
}
