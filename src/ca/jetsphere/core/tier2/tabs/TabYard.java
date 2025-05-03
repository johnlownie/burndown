package ca.jetsphere.core.tier2.tabs;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.common.UUID;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.action.Action;
import ca.jetsphere.core.tier1.backbone.action.ActionYard;
import ca.jetsphere.core.tier1.backbone.role.Role;
import ca.jetsphere.core.tier1.backbone.role.RoleSession;
import ca.jetsphere.core.tier1.backbone.role.RoleYard;
import ca.jetsphere.core.tier1.backbone.role_right.RoleRight;
import ca.jetsphere.core.tier1.backbone.role_right.RoleRightSession;
import ca.jetsphere.core.tier1.backbone.role_right.RoleRightYard;
import ca.jetsphere.core.tier1.backbone.user.User;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 *
 */

public class TabYard
{
    /**
     *
     */

    static public void clearSelected ( TabSet tabs )
    {
    for ( int cc = 0; cc < tabs.getCount (); cc++ )

    { TabSet tab = tabs.get ( cc ); tab.setSelected ( false ); if ( tab.getCount() > 0 ) clearSelected ( tab ); }

    }

    /**
     *
     */

    static public ActionForward getForward ( HttpServletRequest request )
    {
    try {

        TabBar tabBar = getTabBar ( request );

        return new ActionForward ( tabBar.getForward() );

    } catch ( Exception e ) { Common.trace ( e ); }

    return new ActionForward ( "/403.do" );
    }

    /**
     *
     */

    static public TabSet getTab ( RoleRight roleRight )

    { return new TabSet ( UUID.get(), roleRight.getName(), roleRight.getAction().getHref(), roleRight.getNotes(), roleRight.getHidden() ); }

    /**
     *
     */

    static public TabBar getTabBar ( HttpServletRequest request )

    { return ( TabBar ) request.getSession().getAttribute ( "[navbar]" ); }

    /**
     *
     */

    static public TabSet getTabs ( JDBC jdbc, HttpServletRequest request, User user ) throws Exception
    {
    TabSet tabs = new TabSet();

    RoleSession roles = RoleYard.getRoles ( jdbc, request, user );

    Iterator it = roles.iterator ( true );

    while ( it.hasNext() )
    {
    Role role = ( Role ) it.next();

    merge ( tabs, getTabs ( RoleRightYard.getRights ( jdbc, role.getId() ), roles.size() > 0 ) );
    }

    return tabs;
    }

    /**
     *
     */

    static public TabSet getTabs ( RoleRightSession rights, boolean addRoot )

    { return getTabs ( new TabSet(), ( RoleRight ) rights.getRoot(), addRoot ); }

    /**
     *
     */

    static public TabSet getTabs ( TabSet parent, RoleRight roleRight, boolean addRoot )
    {
    if ( roleRight == null ) return parent;

    if ( ( addRoot || ! roleRight.isRoot() ) && roleRight.isLeaf() )

    { parent.add ( getTab ( roleRight ) ); return parent; }

    TabSet child = ( addRoot || ! roleRight.isRoot() ) ? new TabSet ( UUID.get(), roleRight.getName(), "", roleRight.getNotes(), roleRight.getHidden() ) : parent;

    for ( int ith = 0; ith < roleRight.getChildCount(); ith ++ )

        getTabs ( child, ( RoleRight ) roleRight.getChild ( ith ), addRoot );

    if ( parent != child ) parent.add ( child );

    return parent;
    }

    /**
     *
     */

    static public boolean hasAction ( HttpServletRequest request, ActionMapping mapping )
    {
    JDBC jdbc = new JDBC();

    try {

        String href = request.getServletPath().substring ( 0, request.getServletPath ().indexOf ( "." ) );

        Action action = ActionYard.getByHref ( jdbc, href + ".do" );

        if ( action != null && action.isValid() && action.getPublic() ) return true;

        boolean hasAction = hasAction ( jdbc, request, href ); boolean hasMapping = hasMapping ( jdbc, request, mapping );

        return hasAction || hasMapping; // || true;

    } catch ( Exception e ) { Common.trace ( e ); return false; }

    finally { jdbc.close(); }
    }

    /**
     *
     */

    static public boolean hasAction ( JDBC jdbc, HttpServletRequest request, String action ) throws Exception
    {
    RoleSession roles = RoleYard.getRoles ( jdbc, request );

    Iterator it = roles.iterator ( true );

    while ( it.hasNext() )
    {
    Role role = ( Role ) it.next();

    RoleRightSession roleRights = new RoleRightSession ( jdbc, role.getId() );

    if ( roleRights == null || roleRights.size() == 0 ) continue;

    if ( hasAction ( ( RoleRight ) roleRights.getRoot(), action ) ) return true;
    }

    return false;
    }

    /**
     *
     */

    static public boolean hasAction ( RoleRight roleRight, String action )
    {
    if ( roleRight.isLeaf() )

        return DockYard.contains ( roleRight.getAction().getHref(), action );

    for ( int cc = 0; cc < roleRight.getChildCount(); cc ++ )

        if ( hasAction ( ( RoleRight ) roleRight.getChild ( cc ), action ) ) return true;

    return false;
    }

    /**
     *
     */

    static public boolean hasMapping ( JDBC jdbc, HttpServletRequest request, ActionMapping mapping ) throws Exception
    {
    String forwards [] = mapping.findForwards ();

    for ( int cc = 0; cc < forwards.length; cc ++ )

        if ( hasAction ( jdbc, request, mapping.findForward ( forwards [ cc ] ).getPath() ) ) return true;

    return false;
    }

    /**
     *
     */

    static public void merge ( TabSet t0, TabSet t1 )
    {
    Iterator it = t1.iterator();

    while ( it.hasNext() ) t0.add ( ( TabSet ) it.next() );
    }

    /**
     *
     */

    static public void setSelected ( HttpServletRequest request, ActionMapping mapping )
    {
    TabBar tabBar = TabYard.getTabBar( request );

    if ( tabBar == null ) return;

    clearSelected ( tabBar.getTabs() );

    String action = request.getServletPath().substring ( 0, request.getServletPath ().indexOf ( "." ) );

    if ( ! setSelected ( tabBar.getTabs(), action) ) setSelected ( tabBar.getTabs(), mapping );
    }

    /**
     *
     */

    static public boolean setSelected ( TabSet tabs, String action )
    {
    for ( int cc = 0; cc < tabs.getCount (); cc++ )
    {
    TabSet tab = tabs.get ( cc );

    if ( tab.getAction().contains ( action ) )

    { tab.setSelected ( true ); tab.getParent().setSelected ( true ); return true; }

    if ( tab.getCount () > 0 ) if ( setSelected ( tab, action ) ) return true;
    }

    return false;
    }

    /**
     *
     */

    static public boolean setSelected ( TabSet tabs, ActionMapping mapping )
    {
    for ( int cc = 0; cc < tabs.getCount (); cc++ )
    {
    TabSet tab = tabs.get ( cc );

    for ( String s: mapping.findForwards() )
    {
    String path = mapping.findForward ( s ).getPath();

    if ( DockYard.isWhiteSpace ( tab.getAction() ) || ! DockYard.contains ( path, tab.getAction() ) ) continue;

    tab.setSelected ( true ); tab.getParent().setSelected ( true );

    return true;
    }

    if ( tab.getCount () > 0 ) if ( setSelected ( tab, mapping ) ) return true;
    }

    return false;
    }

    /**
     *
     */

    static public void setTabs ( JDBC jdbc, HttpServletRequest request, User user) throws Exception
    {
    TabSet tabSet = getTabs ( jdbc, request, user );

    TabBar tabBar = new TabBar ( tabSet );

    TabYard.setTabBar ( request, tabBar );
    }
    /**
     *
     */

    static public void setTabBar ( HttpServletRequest request, TabBar tabBar )

    { request.getSession().setAttribute ( "[navbar]", tabBar ); }

}
