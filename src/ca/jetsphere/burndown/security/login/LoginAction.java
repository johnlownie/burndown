package ca.jetsphere.burndown.security.login;

import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier1.backbone.period.PeriodSession;
import ca.jetsphere.core.tier1.backbone.period.PeriodYard;
import ca.jetsphere.core.tier1.backbone.role.RoleYard;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;
import ca.jetsphere.core.tier2.common.ActionStore;
import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class LoginAction extends ca.jetsphere.core.security.login.LoginAction
{
    /**
     *
     */

    public ActionForward getForward ( ActionStore store )
    {
    PeriodSession periods = PeriodSession.getInstance ( store.getRequest() );

    if ( PeriodYard.openCount( periods ) == 1 ) return super.getForward ( store );

    User whoAmI = UserYard.whoAmI ( store.getRequest() );

//    if ( !RoleYard.hasRole ( store.getRequest(), whoAmI, new String[] { "BD EU" } ) )
        
    return super.getForward ( store );

//    return store.getForward ( "period" );
    }
}
