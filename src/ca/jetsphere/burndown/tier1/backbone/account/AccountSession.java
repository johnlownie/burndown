package ca.jetsphere.burndown.tier1.backbone.account;

import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.burndown.tier0.backbone.account.AccountMap;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *
 */
public class AccountSession extends AccountMap implements ISessionObject
{
    /**
     *
     */
    public AccountSession() { super(); }

    /**
     *
     */
    public AccountSession ( JDBC jdbc, int company_id ) { super ( jdbc, company_id ); }

    /**
     *
     */
    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new Account ( jdbc, rs ) ); }

    /**
     *
     */
    public String [] captions() { return Account.captions(); }

    /**
     *
     */
    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new Account() ); }

    /**
     *
     */
    public String [] fields() { return Account.fields(); }

    /**
     *
     */
    static public AccountSession getInstance ( HttpServletRequest request )

    { return ( AccountSession ) SessionCache.getSessionObject ( request, Account.key(), AccountSession.class ); }

    /**
     *
     */
    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */
    static public Account getSelected ( HttpServletRequest request ) { return ( Account ) getInstance ( request ).getSelected(); }

    /**
     *
     */
    static public AccountSession query ( JDBC jdbc, HttpServletRequest request, int company_id, boolean blank )

    { AccountSession session = getInstance ( request ); session.find ( jdbc, company_id ); session.options ( request, blank ); return session; }

    /**
     *
     */
    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */
    static public void setSelected ( HttpServletRequest request, Account selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }
}
