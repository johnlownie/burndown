package ca.jetsphere.core.tier1.backbone.user.common;

import ca.jetsphere.core.common.Caption;
import ca.jetsphere.core.common.DockYard;
import org.apache.struts.util.LabelValueBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class Status 
{
    /**
     *
     */

    static final public int ACTIVE  = 0x00;

    static final public int FROZEN  = 0x01;

    static final public int CLOSED  = 0x02;

    static final public int PENDING = 0x03;

    /**
     *
     */

    static public String get ( int status )
    {
    switch ( status )
    {
    case ACTIVE  : return "account.status.active"  ;

    case FROZEN  : return "account.status.frozen"  ;

    case CLOSED  : return "account.status.closed"  ;

    case PENDING : return "account.status.pending" ;
    }

    return "account.status.unknown";
    }

    /**
     *
     */

    static public String get ( HttpServletRequest request, int status ) { return Caption.get ( request, get ( status ) ); }

    /**
     *
     */

    static public int get ( HttpServletRequest request ) { return DockYard.toInteger ( request, key() ); }

    /**
     *
     */

    static public List get ( HttpServletRequest request, String blank )
    {
    List list = new ArrayList();

    if ( ! DockYard.isWhiteSpace ( blank ) ) list.add ( new LabelValueBean( blank, "-1" ) );

    list.add ( new LabelValueBean ( get ( request, ACTIVE  ), "" + ACTIVE  ) );
    list.add ( new LabelValueBean ( get ( request, FROZEN  ), "" + FROZEN  ) );
    list.add ( new LabelValueBean ( get ( request, CLOSED  ), "" + CLOSED  ) );
    list.add ( new LabelValueBean ( get ( request, PENDING ), "" + PENDING ) );

    return list;
    }

    /**
     *
     */

    static public int inverse ( HttpServletRequest request, String status )
    {
    if ( DockYard.compareTo ( get ( request, ACTIVE  ), status ) == 0 ) return ACTIVE  ;

    if ( DockYard.compareTo ( get ( request, FROZEN  ), status ) == 0 ) return FROZEN  ;

    if ( DockYard.compareTo ( get ( request, CLOSED  ), status ) == 0 ) return CLOSED  ;

    if ( DockYard.compareTo ( get ( request, PENDING ), status ) == 0 ) return PENDING ;

    return -1;
    }

    /**
     *
     */

    static public String key() { return "status"; }

}
