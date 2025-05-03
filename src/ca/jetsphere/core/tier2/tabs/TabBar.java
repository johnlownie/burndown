package ca.jetsphere.core.tier2.tabs;

import ca.jetsphere.core.common.DockYard;
import org.apache.struts.action.ActionForm;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 */

public class TabBar extends ActionForm implements Serializable
{
    TabSet tabs; String level0, level1;

    /**
     *
     */

    public TabBar ( TabSet tabs )

    { super(); this.tabs = tabs; setLevel0 ( tabs ); setLevel1 ( tabs.get ( getLevel0() ) ); }

    /**
     *
     */

    public String getForward()

    { return getForward ( tabs.get ( DockYard.isWhiteSpace ( getLevel1() ) ? getLevel0() : getLevel1() ) ); }

    /**
     *
     */

    public String getForward ( TabSet tabs )
    {
    if ( ! DockYard.isWhiteSpace ( tabs.getAction() ) ) return tabs.getAction();

    Iterator it = tabs.iterator();

    while ( it.hasNext() )

    { TabSet child = ( TabSet ) it.next(); String action = getForward ( child );  if ( ! DockYard.isWhiteSpace ( action ) ) return action; }

    return "/420.do";
    }

    /**
     *
     */

    public boolean setLevel0 ( TabSet tabs )
    {
    if ( ! DockYard.isWhiteSpace ( tabs.getUuid() ) && ! tabs.isHidden() ) { setLevel0 ( tabs.getUuid() ); return true; }

    Iterator it = tabs.iterator();

    while ( it.hasNext() )

    { TabSet child = ( TabSet ) it.next(); boolean hasAction = setLevel0 ( child ); if ( hasAction ) return true; }

    return false;
    }

    /**
     *
     */

    public boolean setLevel1 ( TabSet tabs )
    {
    if ( tabs == null ) return false;

    Iterator it = tabs.iterator();

    while ( it.hasNext() )

    { TabSet child = ( TabSet ) it.next(); if ( ! DockYard.isWhiteSpace ( child.getAction() ) ) { setLevel1 ( child.getUuid() ); return true; } if ( setLevel1 ( child ) ) return true; }

    return false;
    }

    /**
     *
     */

    public String getLevel0 () { return level0 ; }
    public String getLevel1 () { return level1 ; }
    public TabSet getTabs   () { return tabs   ; }

    /**
     *
     */

    public void setLevel0 ( String level0 ) { this.level0 = level0; }
    public void setLevel1 ( String level1 ) { this.level1 = level1; }

}
