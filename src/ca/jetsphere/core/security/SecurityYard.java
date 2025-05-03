package ca.jetsphere.core.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 *
 */

public class SecurityYard
{

    /**
     *
     */

    static public void clearSession ( HttpServletRequest request )
    {
    HttpSession session = request.getSession();

    if ( session == null ) return;

    Enumeration e = session.getAttributeNames();

    while ( e.hasMoreElements() )

        session.removeAttribute ( ( String ) e.nextElement() );
    }

}
