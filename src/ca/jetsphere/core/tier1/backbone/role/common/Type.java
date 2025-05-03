package ca.jetsphere.core.tier1.backbone.role.common;

import ca.jetsphere.core.common.Caption;
import org.apache.struts.util.LabelValueBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 *
 */

public class Type
{
    static final public int SYSTEM  = 0x01;
    static final public int SITE    = 0x02;
    static final public int COMPANY = 0x04;
    static final public int BU      = 0x08;

    /**
     *
     */

    static public String getType ( int type )
    {
    if ( type == SYSTEM  ) return "system"  ;

    if ( type == SITE    ) return "site"    ;

    if ( type == COMPANY ) return "company" ;

    if ( type == BU      ) return "bu"      ;

    return "unknown";
    }

    /**
     *
     */

    static public ArrayList getTypes ( HttpServletRequest request )
    {
    ArrayList list = new ArrayList();

    list.add ( new LabelValueBean ( Caption.get ( request, "system"  ), "" + SYSTEM  ) );

    list.add ( new LabelValueBean ( Caption.get ( request, "site"    ), "" + SITE    ) );

    list.add ( new LabelValueBean ( Caption.get ( request, "company" ), "" + COMPANY ) );

    list.add ( new LabelValueBean ( Caption.get ( request, "bu"      ), "" + BU      ) );

    return list;
    }

}
