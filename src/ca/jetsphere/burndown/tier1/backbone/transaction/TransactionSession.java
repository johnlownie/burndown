package ca.jetsphere.burndown.tier1.backbone.transaction;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.burndown.tier0.backbone.transaction.TransactionMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */
public class TransactionSession extends TransactionMap implements ISessionObject
{
    /**
     *
     */
    public TransactionSession() { super(); }

    /**
     *
     */
    public TransactionSession ( JDBC jdbc, int period_id ) { super ( jdbc, period_id ); }

    /**
     *
     */
    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Transaction ( jdbc, rs ) ); }

    /**
     *
     */
    public String [] captions() { return Transaction.captions(); }

    /**
     *
     */
    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Transaction() ); }

    /**
     *
     */
    public String [] fields() { return Transaction.fields(); }

    /**
     *
     */
    static public TransactionSession getInstance ( HttpServletRequest request )

    { return ( TransactionSession ) SessionCache.getSessionObject ( request, Transaction.key(), TransactionSession.class ); }

    /**
     *
     */
    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */
    static public Transaction getSelected ( HttpServletRequest request ) { return ( Transaction ) getInstance ( request ).getSelected(); }

    /**
     *
     */
    static public TransactionSession query ( JDBC jdbc, HttpServletRequest request, int period_id, boolean blank )

    { TransactionSession session = getInstance ( request ); session.find ( jdbc, period_id ); session.options ( request, blank ); return session; }

    /**
     *
     */
    static public TransactionSession query ( JDBC jdbc, HttpServletRequest request, int period_id, int type_id, int category_id, boolean blank )

    { TransactionSession session = getInstance ( request ); session.find ( jdbc, period_id, type_id, category_id ); session.options ( request, blank ); return session; }

    /**
     *
     */
    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */
    static public void setSelected ( HttpServletRequest request, Transaction selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }
}
