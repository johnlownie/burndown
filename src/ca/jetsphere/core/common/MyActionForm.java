package ca.jetsphere.core.common;

import ca.jetsphere.core.tier1.backbone.token.Token;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 *
 */

abstract public class MyActionForm extends ActionForm
{
    HttpServletRequest request   ;

    /**
     *
     */

    abstract public void clear();

    public void clear ( String key, HttpServletRequest request ) { getActionForm ( key, request ).clear(); }

    static MyActionForm getActionForm ( String key, HttpServletRequest request ) { return ( MyActionForm ) request.getSession().getAttribute ( getActionFormName ( key ) ); }

    static public String getActionFormName ( String key ) { return "[" + key + "]"; }

    /**
     *
     */

    public int getId() { return -1; }

    abstract public String getKey();

    public Locale getLocale() { return request.getLocale(); }

    public String getName() { return ""; }

    /**
     *
     */

    public HttpServletRequest getRequest  () { return request   ; }

    /**
     *
     */

    public void setRequest ( MyActionForm af ) { setRequest ( af.getRequest() ); }

    /**
     *
     */

    public void setRequest  ( HttpServletRequest request   ) { this.request   = request   ; }

    /**
     *
     */

    static public ActionForm getActionForm ( HttpServletRequest request, String key )

    { return getActionForm ( request, key, true ); }

    /**
     *
     */

    static public ActionForm getActionForm ( HttpServletRequest request, String key, boolean inSession )

    { return ( ActionForm ) DockYard.getAttribute ( request, getActionFormName ( key ), inSession ); }

    /**
     *
     */

    public void setActionForm ( HttpServletRequest request )

    { request.getSession().setAttribute ( getActionFormName ( getKey() ), this ); }

    /**
     *
     */

    public void setSelected ( HttpServletRequest request )

    { setSelected ( request, getKey() ); }

    /**
     *
     */

    public void setSelected ( HttpServletRequest request, String key )

    { request.getSession().setAttribute ( key, "" + getId() ); request.getSession().setAttribute ( getActionFormName ( key ), this ); }

}
