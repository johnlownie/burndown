package ca.jetsphere.core.tier2.tabs;

import ca.jetsphere.core.common.DockYard;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;

/**
 *
 */

public class TabBarWriter
{
    /**
     *
     */

    static public void bootstrap ( Writer writer, HttpServletRequest request ) throws IOException
    {
    TabBar tabBar = TabYard.getTabBar ( request );

    if ( tabBar == null ) return;

    bootstrap ( writer, request, tabBar.getTabs(), 0 );
    }

    /**
     *
     */

    static public void bootstrap ( Writer writer, HttpServletRequest request, TabSet tabs, int level ) throws IOException
    {
    writer.write ( "<ul" );

    writer.write ( level == 0 ? " class=\"nav navbar-nav\"" : level == 1 ? " class=\"dropdown-menu multi-level\"" : " class=\"dropdown-menu\" role=\"menu\"" );

    writer.write ( ">" );

    for ( int cc = 0; cc < tabs.getCount(); cc ++ )
    {
    TabSet tab = tabs.get ( cc ); boolean hasChild = tab.getCount() > 0;

    writer.write ( "<li" ); if ( hasChild && level > 0 ) writer.write(" class=\"dropdown-submenu\""); writer.write ( ">" );

    String href = DockYard.isWhiteSpace ( tab.getAction() ) ? "#" : request.getContextPath() + tab.getAction();

    writer.write ( "<a href=\"" + href + "\"" );

    if ( hasChild ) writer.write ( " class=\"dropdown-toggle\" data-toggle=\"dropdown\"" );

    writer.write ( ">" );

    writer.write ( tab.getText ( request ) );

    if ( hasChild && level == 0 ) writer.write ( "<b class=\"caret\"></b>" );

    writer.write ( "</a>" );

    if ( hasChild ) bootstrap ( writer, request, tab, level + 1 );

    writer.write ( "</li>" );
    }

    writer.write ( "</ul>" );
    }

    /**
     *
     */

    static public void nifty ( Writer writer, HttpServletRequest request ) throws IOException
    {
    TabBar tabBar = TabYard.getTabBar ( request );

    if ( tabBar == null ) return;

    nifty ( writer, request, tabBar.getTabs() );
    }

    /**
     *
     */

    static public void nifty ( Writer writer, HttpServletRequest request, TabSet tabs ) throws IOException
    {
    writer.write ( "<ul id=\"mainnav-menu\" class=\"list-group\">" );

    for ( int cc = 0; cc < tabs.getCount(); cc ++ )
    {
    TabSet tab = tabs.get ( cc ); boolean hasChild = tab.getCount() > 0; boolean hasAction = !DockYard.isWhiteSpace ( tab.getAction() );

    if ( tab.isHidden() ) continue;

    writer.write ( "<li" ); if ( !hasAction ) writer.write ( " class=\"list-header\"" ); writer.write ( ">" );

    if ( hasAction ) writer.write ( "<a href=\"" + request.getContextPath() + tab.getAction() + "\">" );

    if ( !DockYard.isWhiteSpace ( tab.getToolTip() ) ) writer.write ( "<i class=\"fa fa-" + tab.getToolTip() + "\"></i>" );

    writer.write ( "<span class=\"menu-title\">" ); writer.write ( tab.getText ( request ) ); writer.write ( "</span>" );

    if ( hasAction ) writer.write ( "</a>" );

    writer.write ( "</li>" );

    if ( hasChild ) nifty ( writer, request, tab, 1 );
    }

    writer.write ( "</ul>" );
    }

    /**
     *
     */

    static public void nifty ( Writer writer, HttpServletRequest request, TabSet tabs, int level ) throws IOException
    {
    if ( level > 1 ) writer.write ( "<ul class=\"collapse\">" );

    for ( int cc = 0; cc < tabs.getCount(); cc ++ )
    {
    TabSet tab = tabs.get ( cc ); boolean hasChild = tab.getCount() > 0;

    if ( tab.isHidden() ) continue;

    if ( DockYard.equalsIgnoreCase ( "divider", tab.getName() ) ) { writer.write ( "<li class=\"list-divider\"></li>" ); continue; }

    writer.write ( "<li" ); if ( tab.isSelected() ) writer.write ( hasChild ? " class=\"active-sub active\"" : " class=\"active-link\"" ); writer.write ( ">" );

    String href = DockYard.isWhiteSpace ( tab.getAction() ) ? "#" : request.getContextPath() + tab.getAction();

    if ( level > 0 || ! hasChild ) writer.write ( "<a href=\"" + href + "\">" );

    if ( !DockYard.isWhiteSpace ( tab.getToolTip() ) ) writer.write ( "<i class=\"fa fa-" + tab.getToolTip() + "\"></i>" );

    writer.write ( "<span class=\"menu-title\">" ); writer.write ( tab.getText ( request ) ); writer.write ( "</span>" );

    if ( hasChild && level > 0 ) writer.write ( "<i class=\"arrow\"></i>" );

    if ( level > 0 ) writer.write ( "</a>" );

    if ( hasChild ) nifty ( writer, request, tab, level + 1 );

    writer.write ( "</li>" );
    }

    if ( level > 1 ) writer.write ( "</ul>" );
    }

    /**
     *
     */

    static public void print ( Writer writer, HttpServletRequest request ) throws IOException
    {
    TabBar tabBar = TabYard.getTabBar ( request );

    if ( tabBar == null ) return;

    print ( writer, request, tabBar.getTabs (), 0 );
    }

    /**
     *
     */

    static public void print ( Writer writer, HttpServletRequest request, TabSet tabs, int level ) throws IOException
    {
    writer.write ( "<ul" ); if ( level == 0 ) writer.write ( " id=\"nav\"" ); writer.write ( " class=\"level" + level + "\"" ); writer.write ( ">" );

    for ( int cc = 0; cc < tabs.getCount(); cc ++ )
    {
    TabSet tab = tabs.get ( cc ); boolean hasChild = tab.getCount() > 0;

    writer.write ( "<li" ); if ( hasChild ) writer.write(" class=\"child\""); writer.write ( ">" );

    if ( DockYard.isWhiteSpace ( tab.getAction() ) ) writer.write ( "<span class=\"separator\">" );

    else writer.write ( "<a href=\"" + request.getContextPath() + tab.getAction() + "\">" );

    writer.write ( "<span>" ); writer.write ( tab.getText ( request ) ); writer.write ( "</span>" );

    writer.write ( DockYard.isWhiteSpace ( tab.getAction() ) ? "</span>" : "</a>" );

    if ( hasChild ) print ( writer, request, tab, level + 1 );

    writer.write ( "</li>" );
    }

    writer.write ( "</ul>" );
    }

}
