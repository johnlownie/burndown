package ca.jetsphere.core.tier2.common;

import ca.jetsphere.core.common.DockYard;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */

public class ActionStore
{
    ActionMapping       action_store_mapping;
    ActionForm          action_store_form;
    HttpServletRequest  action_store_request;
    HttpServletResponse action_store_response;

    /**
     *
     */

    public ActionStore ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )

    { this.action_store_mapping = mapping; this.action_store_form = form; this.action_store_request = request; this.action_store_response = response; }

    /**
     *
     */

    public ActionForward getForward ( ActionForward forward, String queryString, boolean redirect )

    { return new ActionForward ( forward.getPath() + queryString, redirect ); }

    /**
     *
     */

    public ActionForward getForward ( String forward )
    {
    ActionForward actionForward = getMapping().findForward ( forward );

    return actionForward != null ? actionForward : getMapping().findForward ( "success" );
    }

    /**
     *
     */

    public ActionForward getForward ( String forward, String queryString )

    { ActionForward af = getForward ( forward ); return getForward ( af, queryString, af.getRedirect() ); }

    /**
     *
     */

    public ActionForward getForward ( String forward, String queryString, boolean redirect )

    { return getForward ( getForward ( forward ), queryString, redirect ); }

    /**
     *
     */

    public ActionForward getForward ( String forward, String[] parameters )
    {
    if ( parameters == null || parameters.length == 0 ) return getForward ( forward );

    String query = "";

    for ( int cc=0; cc< parameters.length; cc++ )
    {
    query += cc == 0 ? "?" : "&";

    query += parameters [ cc ] + "=" + DockYard.getParameter ( action_store_request, parameters [cc] );
    }

    return getForward ( forward, query );
    }

    /**
     *
     */

    public ActionForm          getForm    () { return action_store_form          ; }
    public ActionMapping       getMapping () { return this.action_store_mapping  ; }
    public HttpServletRequest  getRequest () { return this.action_store_request  ; }
    public HttpServletResponse getResponse() { return this.action_store_response ; }

    /**
     *
     */

    public void setForm ( ActionForm action_store_form ) { this.action_store_form = action_store_form ; }

}
