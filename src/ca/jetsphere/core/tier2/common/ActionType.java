package ca.jetsphere.core.tier2.common;

import ca.jetsphere.core.bolt.Bolt;
import ca.jetsphere.core.common.Caption;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class ActionType
{
    /**
     *
     */

    static public ActionForm getActionForm ( HttpServletRequest request, String key )

    { return ( ActionForm ) request.getSession().getAttribute ( "[" + key + "]" ); }

    /**
     *
     */

    static public boolean isEdit ( HttpServletRequest request )
    {
    String s = ( String ) request.getSession().getAttribute ( "edit" );

    return "true".equals ( s );
    }

    /**
     *
     */

    static public boolean isEdit ( HttpServletRequest request, String key )
    {
    Bolt bolt = ( Bolt ) getActionForm ( request, key );

    boolean isValid = bolt != null && bolt.getId() >= 0;

    request.getSession().setAttribute ( "edit", isValid ? "true" : "false" );

    return isValid;
    }

    /**
     *
     */

    static public String keyify ( String key )
    {
    if ( key == null ) return "";

    StringBuilder sb = new StringBuilder ( key );

    for ( int cc = 1; cc < sb.length(); cc ++ ) if ( Character.isUpperCase ( sb.charAt ( cc ) ) ) sb.insert ( cc ++ , '.' );

    return sb.toString();
    }

    /**
     *
     */

    static public String title ( HttpServletRequest request, String key )
    {
    String title = isEdit ( request, key ) ? "edit" : "insert";

//  title += '.' + keyify ( key );

    title += '.' + key.replace ( '_', '.' );

    return Caption.get ( request, title );
    }

}
