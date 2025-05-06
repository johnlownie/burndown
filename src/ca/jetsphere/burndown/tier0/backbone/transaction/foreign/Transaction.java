package ca.jetsphere.burndown.tier0.backbone.transaction.foreign;

import ca.jetsphere.burndown.tier0.backbone.category.Category;
import ca.jetsphere.core.jdbc.JDBC;

/**
 * 
 */
abstract public class Transaction extends ca.jetsphere.burndown.tier0.backbone.transaction.bean.Transaction
{
    Category category;
    
    /**
     *
     */

    static public Class container() { return Category.class; }

    /**
     *
     */
    public void foreign ( JDBC jdbc ) throws Exception

    { setCategory ( new Category ( jdbc, getCategoryId() ) ); }

    /**
     *
     */
    public Category getCategory() { return category; }

    /**
     *
     */
    public void setCategory ( Category category ) { this.category = category; }
}
