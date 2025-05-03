package ca.jetsphere.core.servlet;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.tier1.system.knock.Knock;
import ca.jetsphere.core.jdbc.JDBC;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 */

public class InitializationServlet extends HttpServlet
{
    /**
     *
     */

    public InitializationServlet() { super (); }

    /**
     *
     */

    public void init ( ServletConfig config ) throws ServletException
    {
    super.init ( config );

    JDBC jdbc = new JDBC();

    try {

        Knock.set ( config );

    } catch ( Exception e ) { Common.trace ( e ); }

    finally { jdbc.close(); }
    }

}
