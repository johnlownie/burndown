package ca.jetsphere.core.jdbc;

import ca.jetsphere.core.common.Common;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

/**
 *
 */

public class Status
{
    Object object; static int open = 0;

    /**
     *
     */

    public Status ( Object object ) { this.object = object; }

    /**
     *
     */

    protected String at()

    { StringBuilder sb = new StringBuilder ( this.toString() ); int cc = sb.indexOf ( "@" ); return cc > 0 ? sb.substring ( cc ) : sb.toString(); }

    /**
     *
     */

    protected synchronized void close() throws SQLException { open --; trace ( false ); }


    /**
     *
     */

    protected synchronized void open () throws NamingException, SQLException { open ++; trace ( true  ); }

    /**
     *
     */

    public HttpServletRequest getRequest()

    { return ( object != null && object instanceof HttpServletRequest ) ? ( HttpServletRequest ) object : null; }

    /**
     *
     */

    protected void trace ( boolean isOpen )

    { Common.debug ( "JDBC: " + at() + ( isOpen ? " [ OPEN  ] " : " [ CLOSE ] " ) + open + " ( " + trace() + " )" ); }

    /**
     *
     */

    protected String trace()
    {
    if ( object == null || ! ( object instanceof HttpServletRequest ) ) return "???";

    return object != null ? object.getClass().getName() : "???";
    }

}
