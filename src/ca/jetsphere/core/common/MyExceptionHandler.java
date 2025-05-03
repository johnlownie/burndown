package ca.jetsphere.core.common;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */

public class MyExceptionHandler extends ExceptionHandler
{
    /**
     *
     */

    public ActionForward execute  ( Exception ex, ExceptionConfig ec, ActionMapping mapping, ActionForm formInstance, HttpServletRequest request, HttpServletResponse response ) throws ServletException

    { Common.trace ( this, ex ); return new ActionForward ( "/500.do" ); }

}
