package ca.jetsphere.burndown.tier1.backbone.category;

import ca.jetsphere.core.bolt.BoltCopyMap;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.application.Application;
import ca.jetsphere.core.tier1.backbone.application.ApplicationSession;
import ca.jetsphere.core.tier1.backbone.application.ApplicationYard;
import java.util.Iterator;

/**
 *
 */
public class CategoryCopy
{
    BoltCopyMap copyMap;

    /**
     *
     */

    public CategoryCopy ( BoltCopyMap copyMap ) { this.copyMap = copyMap; }

    /**
     *
     */
    public void copy ( JDBC jdbc, int at$application_id, int to$application_id ) throws Exception
    {
    Synchronize.set ( jdbc, at$application_id );

    ApplicationYard.delete ( jdbc, to$application_id );
    
    CategorySession categories = new CategorySession ( jdbc, at$application_id );

    Iterator it = categories.iterator ( false );

    while ( it.hasNext() )

        copy ( jdbc, to$application_id, ( Category ) it.next() );

    square ( jdbc, to$application_id );

    Lineage.set ( jdbc, to$application_id );
    }

    /**
     *
     */
    public void copy ( JDBC jdbc, int to$application_id, Category category ) throws Exception
    {
    int at$category_id = category.getId();

    category.setApplicationId ( to$application_id ); category.copySave ( jdbc );

    int to$category_id = category.getId();

    copyMap.put ( Category.key(), at$category_id, to$category_id );
    }

    /**
     *
     */
    public void square ( JDBC jdbc, int to$application_id ) throws Exception
    {
    CategorySession categories = new CategorySession ( jdbc, to$application_id );

    Iterator it = categories.iterator ( false );

    while ( it.hasNext() )

      square ( jdbc, to$application_id, ( Category ) it.next() );
    }

    /**
     *
     */
    public void square ( JDBC jdbc, int to$application_id, Category category ) throws Exception
    {
    int parent_id = copyMap.get ( Category.key(), category.getParentId() );

    category.setParentId ( parent_id );

    category.save ( jdbc );
    }
}
