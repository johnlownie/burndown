package ca.jetsphere.core.security;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.tier1.system.knock.Knock;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;
import ca.jetsphere.core.tier2.tabs.TabYard;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */

public class RequestProcessor extends org.apache.struts.action.RequestProcessor
{
    static LockOut lockout = new LockOut();

    /**
     *
     */

    protected boolean _403 ( HttpServletRequest request, HttpServletResponse response, ActionMapping mapping ) throws IOException
    {
    if ( isAdministrator ( request ) ) { TabYard.setSelected ( request, mapping ); return true; }

    if ( TabYard.hasAction ( request, mapping ) ) { TabYard.setSelected ( request, mapping ); return isUnLocked ( request, response ); }

    return redirect ( request, response, "/403.do" );
    }

    /**
     *
     */

    static public LockOut getLockOut() { return lockout; }

    /**
     *
     */

    protected boolean isActive ( HttpServletRequest request )
    {
    User user = UserYard.whoAmI ( request );

    return user != null ? UserYard.isValid ( user ) : false;
    }

    /**
     *
     */

    protected boolean isAdministrator ( HttpServletRequest request )
    {
    User user = UserYard.whoAmI ( request );

    return user != null && user.isValid() ? user.isAdministrator() : false;
    }

    /**
     *
     */

    protected boolean isCommon ( HttpServletRequest request )
    {
    if ( DockYard.compareTo ( request.getServletPath(), "/403.do"             ) == 0 ) return true;

    if ( DockYard.compareTo ( request.getServletPath(), "/404.do"             ) == 0 ) return true;

    if ( DockYard.compareTo ( request.getServletPath(), "/420.do"             ) == 0 ) return true;

    if ( DockYard.compareTo ( request.getServletPath(), "/500.do"             ) == 0 ) return true;

    if ( DockYard.compareTo ( request.getServletPath(), "/login.do"           ) == 0 ) return true;

    if ( DockYard.compareTo ( request.getServletPath(), "/logout.do"          ) == 0 ) return true;

    if ( DockYard.compareTo ( request.getServletPath(), "/maintenance.do"     ) == 0 ) return true;

    if ( DockYard.compareTo ( request.getServletPath(), "/nowizard.do"        ) == 0 ) return true;

    if ( DockYard.compareTo ( request.getServletPath(), "/register.do"        ) == 0 ) return true;

    if ( DockYard.compareTo ( request.getServletPath(), "/reset_password.do"  ) == 0 ) return true;

    if ( DockYard.compareTo ( request.getServletPath(), "/timeout.do"         ) == 0 ) return true;

    if ( DockYard.compareTo ( request.getServletPath(), "/unsubscribe.do"     ) == 0 ) return true;

    return false;
    }

    /**
     *
     */

    protected boolean isUnLocked ( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
    if ( getLockOut().isLock() ) return redirect ( request, response, "/lockedout.do" );

    return true; /* update ( request ); */
    }

    /**
     *
     */

    protected void processLocale ( HttpServletRequest request, HttpServletResponse response )
    {
    try {

        if ( request.getCharacterEncoding() == null ) request.setCharacterEncoding ( Knock.get ( "PARAMETER-ENCODING" ) );

    } catch ( Exception e ) {}

    }

    /**
     *
     */

    protected boolean processRoles ( HttpServletRequest request, HttpServletResponse response, ActionMapping mapping ) throws IOException, ServletException
    {
    if ( isCommon ( request ) ) return true ;

    if ( ! isActive ( request ) ) return redirect ( request, response, "/timeout.do" );

    return _403 ( request, response, mapping );
    }

    /**
     *
     */

    protected boolean redirect ( HttpServletRequest request, HttpServletResponse response, String page ) throws IOException
    {
    response.sendRedirect ( request.getContextPath() + page );

    return false;
    }

}