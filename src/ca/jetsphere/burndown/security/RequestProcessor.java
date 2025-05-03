package ca.jetsphere.burndown.security;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class RequestProcessor extends ca.jetsphere.core.security.RequestProcessor
{
    /**
     *
     */

    protected boolean isCommon ( HttpServletRequest request )
    {
//    if ( request.getSession ( false ) == null ) try { new SiteDoAction().setSession ( request ); } catch ( Exception e ) {}

    return super.isCommon ( request );
    }

}
