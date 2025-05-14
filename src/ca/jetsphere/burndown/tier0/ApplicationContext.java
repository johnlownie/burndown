package ca.jetsphere.burndown.tier0;

import ca.jetsphere.burndown.tier0.backbone.account.AccountDao;
import ca.jetsphere.burndown.tier0.backbone.category.CategoryDao;
import ca.jetsphere.burndown.tier0.backbone.transaction.TransactionDao;
import ca.jetsphere.core.tier0.AbstractDao;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class ApplicationContext
{
    static Map context = new HashMap();

    /**
     *
     */

    public ApplicationContext()
    {
    context.put ( "account"     , new AccountDao    () );
    context.put ( "category"    , new CategoryDao   () );
    context.put ( "transaction" , new TransactionDao() );
    }

    /**
     *
     */

    public AbstractDao getBean ( String key, Class clazz ) { return ( AbstractDao ) context.get ( key ); }

}
