package ca.jetsphere.core.tier2.tabs;

import ca.jetsphere.core.common.DockYard;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */

public class TabSet implements Comparable, Serializable
{
    TabSet  tabset_parent   ;
    String  tabset_uuid     ;
    String  tabset_name     ;
    String  tabset_action   ;
    String  tabset_tooltip  ;
    boolean tabset_hidden   ;
    boolean tabset_selected ;
    List    tabset_list     ;

    /**
     *
     */

    public TabSet() { clear(); }

    /**
     *
     */

    public TabSet ( String uuid, String name, String action, String tooltip, boolean hidden )

    { this(); setUuid ( uuid ); setName ( name ); setAction ( action ); setToolTip ( tooltip ); setHidden ( hidden ); }

    /**
     *
     */

    public void clear()
    {
    setParent   ( null  );
    setUuid     ( ""    );
    setName     ( ""    );
    setAction   ( ""    );
    setToolTip  ( ""    );
    setSelected ( false );
    setHidden   ( false );
    setList     ( new ArrayList() );
    }

    /**
     *
     */

    public int compareTo ( Object o )

    { return DockYard.compareTo ( getName(), ( ( TabSet ) o ).getName() ); }

    /**
     *
     */

    public void add ( TabSet tabSet )

    { tabSet.setParent ( this ); getList().add ( tabSet ); }

    /**
     *
     */

    public TabSet get ( int cc )

    { return cc >= 0 && cc < getList().size() ? ( TabSet ) getList().get ( cc ) : null; }

    /**
     *
     */

    public TabSet get ( String uuid )
    {
    if ( uuid == null || uuid.equals ( getUuid() ) ) return this;

    Iterator it = getList().iterator();

    while ( it.hasNext() )

    { TabSet child = ( TabSet ) it.next(); TabSet found = child.get ( uuid ); if ( found != null ) return found; }

    return null;
    }

    /**
     *
     */

    public int getCount() { return getList().size(); }

    /**
     *
     */

    public String getText ( HttpServletRequest request )

    { return DockYard.caption ( request, getName() ); }

    /**
     *
     */

    public String getText ( HttpServletRequest request, boolean tooltip )
    {
    String s = DockYard.caption ( request, getName() );

    if ( ! tooltip ) return s;

    return "<span>" + s + "</span>" + ( DockYard.isWhiteSpace ( getToolTip() ) ? "" : "<p>" + getToolTip() + "</p>" );
    }

    /**
     *
     */

    public Iterator iterator() { return getList().iterator(); }

    /**
     *
     */

    public void remove ( String name )
    {
    Iterator it = getList().iterator();

    while ( it.hasNext() )
    {
    TabSet tab = ( TabSet ) it.next();

    if ( DockYard.compareTo ( tab.getName(), name ) == 0 ) it.remove(); }
    }

    /**
     *
     */

    public TabSet  getParent  () { return tabset_parent   ; }
    public String  getUuid    () { return tabset_uuid     ; }
    public String  getName    () { return tabset_name     ; }
    public String  getAction  () { return tabset_action   ; }
    public String  getToolTip () { return tabset_tooltip  ; }
    public boolean isSelected () { return tabset_selected ; }
    public boolean isHidden   () { return tabset_hidden   ; }
    public List    getList    () { return tabset_list     ; }

    /**
     *
     */

    public void setParent   ( TabSet  tabset_parent   ) { this.tabset_parent   = tabset_parent   ; }
    public void setUuid     ( String  tabset_uuid     ) { this.tabset_uuid     = tabset_uuid     ; }
    public void setName     ( String  tabset_name     ) { this.tabset_name     = tabset_name     ; }
    public void setAction   ( String  tabset_action   ) { this.tabset_action   = tabset_action   ; }
    public void setToolTip  ( String  tabset_tooltip  ) { this.tabset_tooltip  = tabset_tooltip  ; }
    public void setSelected ( boolean tabset_selected ) { this.tabset_selected = tabset_selected ; }
    public void setHidden   ( boolean tabset_hidden   ) { this.tabset_hidden   = tabset_hidden   ; }
    public void setList     ( List    tabset_list     ) { this.tabset_list     = tabset_list     ; }

}
