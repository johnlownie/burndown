package ca.jetsphere.core.tier1.backbone.bu_member;

import java.sql.ResultSet;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.ISessionObject;
import ca.jetsphere.core.common.SessionCache;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier0.backbone.bu_member.BuMemberMap;

/**
 *
 */

public class BuMemberSession extends BuMemberMap implements ISessionObject
{
    /**
     *
     */

    public BuMemberSession() { super(); }

    /**
     *
     */

    public BuMemberSession ( JDBC jdbc, int bu_id ) { super ( jdbc, bu_id ); }

    /**
     *
     */

    public BuMemberSession ( JDBC jdbc, String query ) { this(); query ( jdbc, query ); }

    /**
     *
     */

    public void add ( JDBC jdbc, ResultSet rs ) throws Exception { add ( new BuMember ( jdbc, rs ) ); }

    /**
     *
     */

    public Comparator byFirstname()
    {

    return new Comparator() {

    public int compare ( Object o1, Object o2 )
    {
    BuMember t1 = ( BuMember ) o1;

    BuMember t2 = ( BuMember ) o2;

    try {

      return DockYard.compareTo ( t1.getCompanyMember().getFirstname(), t2.getCompanyMember().getFirstname() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    return 0;

    } }; }

    /**
     *
     */

    public Comparator byLastname()
    {

    return new Comparator() {

    public int compare ( Object o1, Object o2 )
    {
    BuMember t1 = ( BuMember ) o1;

    BuMember t2 = ( BuMember ) o2;

    try {

      return DockYard.compareTo ( t1.getCompanyMember().getLastname(), t2.getCompanyMember().getLastname() );

    } catch ( Exception e ) { Common.trace ( this, e ); }

    return 0;

    } }; }

    /**
     *
     */

    public String [] captions() { return BuMember.captions(); }

    /**
     *
     */

    public void clearSelected ( HttpServletRequest request ) { setNonQualifiedSelected ( request, new BuMember() ); }

    /**
     *
     */

    public String [] fields() { return BuMember.fields(); }

    /**
     *
     */

    static public BuMemberSession getInstance ( HttpServletRequest request )

    { return ( BuMemberSession ) SessionCache.getSessionObject ( request, BuMember.key(), BuMemberSession.class ); }

    /**
     *
     */

    static public int getRequestedId ( HttpServletRequest request ) { return getInstance ( request ).getId ( request ); }

    /**
     *
     */

    static public BuMember getSelected ( HttpServletRequest request ) { return ( BuMember ) getInstance ( request ).getSelected(); }

    /**
     *
     */

    static public BuMemberSession query ( JDBC jdbc, HttpServletRequest request, int period_id, int bu_id, boolean blank )

    { BuMemberSession session = getInstance ( request ); session.find ( jdbc, period_id, bu_id ); session.options ( request, blank ); return session; }

    /**
     *
     */

    static public void setRequested ( HttpServletRequest request ) { getInstance ( request ).setQualifiedSelected ( request, getRequestedId ( request ) ); }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, BuMember selected ) { getInstance ( request ).setQualifiedSelected ( request, selected ); }

    /**
     *
     */

    public void query ( JDBC jdbc, int bu_id ) { query ( jdbc, "select * from os_bu_member where bu_member_bu_id = " + bu_id ); }

}

