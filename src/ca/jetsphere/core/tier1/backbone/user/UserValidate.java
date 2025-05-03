package ca.jetsphere.core.tier1.backbone.user;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.tier2.common.Errors;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */

public class UserValidate
{
    /**
     *
     */

    protected void collision ( JDBC jdbc, User user, Errors errors )

    { if ( user.isValid() ) return; if ( exists ( jdbc, user ) ) errors.add ( "error.username.invalid" ); }

    /**
     *
     */

    public boolean exists ( JDBC jdbc, User user )
    {
    User x = UserYard.getByUsername ( jdbc, user.getUsername () );

    return x.isValid();
    }

    /**
     *
     */

    protected void mandatory ( User user, Errors errors )
    {
    if ( DockYard.isWhiteSpace ( user.getUsername() ) ) errors.add ( "username", "error.username.required" );
    if ( DockYard.isWhiteSpace ( user.getUsername() ) ) errors.add ( "username", "error.username.required" );
    if ( DockYard.isWhiteSpace ( user.getUsername() ) ) errors.add ( "username", "error.username.required" );
    }

    /**
     *
     */

    public Errors validate ( JDBC jdbc, User user, HttpServletRequest request, Errors errors )
    {
    mandatory ( user, errors ); if ( ! errors.isEmpty() ) return errors;

    collision ( jdbc, user, errors ); if ( ! errors.isEmpty() ) return errors;

    return errors;
    }

}
