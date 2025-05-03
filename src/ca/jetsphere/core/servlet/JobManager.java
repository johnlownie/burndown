package ca.jetsphere.core.servlet;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.email.broker.SendMailBroker;
import ca.jetsphere.core.tier1.backbone.token.broker.TokenBroker;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 */

@WebListener
public class JobManager implements ServletContextListener
{
    private ScheduledExecutorService scheduler;

    /**
     *
     */
    @Override
    public void contextInitialized ( ServletContextEvent event )
    {
    ServletContext servletContext = event.getServletContext();

    setContext ( servletContext );

    scheduler = Executors.newSingleThreadScheduledExecutor();

    scheduler.scheduleAtFixedRate ( ( SendMailBroker ) servletContext.getAttribute( "SendMail Broker" ), 15, 30, TimeUnit.SECONDS );

    scheduler.scheduleAtFixedRate ( ( TokenBroker    ) servletContext.getAttribute( "Token Broker"    ), 1, 1, TimeUnit.MINUTES );
    }

    /**
     *
     */
    @Override
    public void contextDestroyed ( ServletContextEvent event )
    {
    scheduler.shutdownNow();

    ClassLoader cl = Thread.currentThread().getContextClassLoader();

    Enumeration<Driver> drivers = DriverManager.getDrivers();

    while ( drivers.hasMoreElements() )
    {
    Driver driver = drivers.nextElement();

    if ( driver.getClass().getClassLoader() != cl ) continue;

    try {

        Common.trace("Deregistering JDBC driver: " + driver.toString());

        DriverManager.deregisterDriver(driver);

    } catch (SQLException ex) {}

    }

    }

    /**
     *
     */

    public void setContext ( ServletContext servletContext )
    {
    SendMailBroker sendMailBroker = new SendMailBroker(); servletContext.setAttribute ( "SendMail Broker", sendMailBroker );

    TokenBroker    tokenBroker    = new TokenBroker   (); servletContext.setAttribute ( "Token Broker"   , tokenBroker    );
    }

}
