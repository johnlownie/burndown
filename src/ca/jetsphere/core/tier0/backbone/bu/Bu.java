package ca.jetsphere.core.tier0.backbone.bu;

import javax.servlet.http.HttpServletRequest;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.Registry;
import ca.jetsphere.core.tier1.tree.Tree;

/**
 *
 */

public class Bu extends ca.jetsphere.core.tier0.backbone.bu.foreign.Bu
{
    /**
     *
     */

    public Bu() { clear(); }

    /**
     *
     */

    public Bu ( JDBC jdbc, int id ) { Registry.get ( jdbc, this, id ); }

    /**
     *
     */

    public void delete ( JDBC jdbc ) throws Exception { Registry.getBuDao().delete ( jdbc, this ); }

    /**
     *
     */

    public String getKey() { return key(); }

    /**
     *
     */

    public String getText ( HttpServletRequest request ) { return null; }

    /**
     *
     */

    public void insert ( JDBC jdbc ) throws Exception { Registry.getBuDao().insert ( jdbc, this ); }

    /**
     *
     */

    public void paste ( Tree tree )

    { Bu parent = ( Bu ) tree; setPeriodId ( parent.getPeriodId() ); pasteBelow ( parent ); }

    /**
     *
     */

    public void setAttributes ( Tree tree ) {}

    /**
     *
     */

    public Tree twin() { return null; }

    /**
     *
     */

    public boolean update ( JDBC jdbc ) throws Exception { return Registry.getBuDao().update ( jdbc, this ); }

}
