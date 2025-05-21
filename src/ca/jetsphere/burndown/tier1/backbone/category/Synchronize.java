package ca.jetsphere.burndown.tier1.backbone.category;

import java.util.Iterator;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;

/**
 *
 */

public class Synchronize
{
    /**
     *
     */
    static public void set ( JDBC jdbc, int application_id ) throws Exception
    {
    CategorySession categories = new CategorySession ( jdbc, application_id );

    Iterator it = categories.iterator ( false );

    while ( it.hasNext() )
    {
    Category category = ( Category ) it.next();

    if ( category.getParentId() == 0 ) continue ;

    Category parent = new Category ( jdbc, category.getParentId() );

    if ( DockYard.compareTo ( parent.getUuid(), category.getParentUuid() ) == 0 ) continue;

    category.setParentUuid ( parent.getUuid() );

    category.update ( jdbc );
    } }
}
