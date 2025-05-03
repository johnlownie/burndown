package ca.jetsphere.core.security.maintenance;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 */

public class MaintenanceModeFilter implements Filter
{
    public static final int MODE_NORMAL_OPERATION = 0x00;
    public static final int MODE_NO_MORE_LOGINS   = 0x01;
    public static final int MODE_NO_WIZARDS       = 0x02;
    public static final int MODE_SUSPENSION       = 0x03;

    protected int    mode    = MODE_NORMAL_OPERATION;
    protected String message = "";

    /**
     *
     */

    public void init ( FilterConfig filterConfig ) throws ServletException {}

    /**
     *
     */

    public void doFilter ( ServletRequest request, ServletResponse response, FilterChain filterChain ) throws IOException, ServletException
    {
    HttpServletRequest httpRequest  = ( HttpServletRequest ) request; String path = httpRequest.getServletPath(); User whoAmI = UserYard.whoAmI ( httpRequest );

    if ( request.getParameter ( "maintenance-mode" ) != null ) { setMaintenance ( request, response, filterChain ); filterChain.doFilter ( request, response ); return; }

    if ( mode == MODE_NORMAL_OPERATION || "/logout.do".equals ( path ) || DockYard.contains ( path, ".css" ) || DockYard.contains ( path, ".js" ) || ( whoAmI != null && whoAmI.isValid() && whoAmI.isAdministrator() ) )

    { filterChain.doFilter ( request, response ); return; }

    if ( mode == MODE_NO_MORE_LOGINS && "/login.do".equals ( path ) ) { request.getRequestDispatcher ( "/maintenance.do" ).forward ( request, response ); return; }

    if ( mode == MODE_NO_WIZARDS && DockYard.contains ( path, "wizard" ) ) { request.getRequestDispatcher ( "/nowizard.do" ).forward ( request, response ); return; }

    if ( mode == MODE_SUSPENSION ) { request.getRequestDispatcher ( "/maintenance.do" ).forward ( request, response ); return; }

    filterChain.doFilter ( request, response );
    }

    /**
     *
     */

    public void setMaintenance ( ServletRequest request, ServletResponse response, FilterChain filterChain ) throws IOException, ServletException

    { mode = Integer.parseInt ( request.getParameter ( "maintenance-mode" ) ); message = request.getParameter ( "maintenance-message" ); }

    /**
     *
     */

    public void destroy() {}

}
