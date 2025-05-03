package ca.jetsphere.core.tier1.backbone.bu;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.bu.BuMap;
import ca.jetsphere.core.tier1.tree.Tree;

/**
 *
 */

public class BuSession extends BuMap implements ISessionObject
{
    /**
     *
     */

    public BuSession() { super(); }

    /**
     *
     */

    public BuSession ( JDBC jdbc, int period_id ) { this(); find ( jdbc, period_id ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Bu ( jdbc, rs ) ); }

    /**
     *
     */

    public String [] captions() { return Bu.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Bu() ); }

    /**
     *
     */

    public String [] fields() { return Bu.fields(); }

    /**
     *
     */

    public BuSession getBonsai ( BuSession bonsai, List list ) { return ( BuSession ) super.getBonsai(bonsai, list); }

    /**
     *
     */

    public void paste ( Tree parent, Tree child )
    {

    }

    /**
     *
     */

    static public BuSession getInstance ( HttpServletRequest request )

    { return ( BuSession ) SessionCache.getSessionObject ( request, Bu.key(), BuSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public Bu getSelected ( HttpServletRequest request ) { return ( Bu ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public BuSession query ( JDBC jdbc, HttpServletRequest request, int period_id, boolean blank )

    { BuSession session = getInstance ( request ); session.find ( jdbc, period_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, Bu selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

}
