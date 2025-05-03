package ca.jetsphere.core.servlet;

import ca.jetsphere.core.common.Common;
import ca.jetsphere.core.tier1.system.knock.Knock;
import org.apache.commons.digester.Digester;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.FormPropertyConfig;
import org.apache.struts.config.MessageResourcesConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.ModuleConfigFactory;
import org.apache.struts.Globals;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 */

public class ActionServlet extends org.apache.struts.action.ActionServlet
{
    /**
     *
     */

    protected ModuleConfig initModuleConfig ( String prefix, String paths ) throws ServletException
    {
    ModuleConfigFactory factoryObject = ModuleConfigFactory.createFactory();

    ModuleConfig config = factoryObject.createModuleConfig ( prefix );

    String mapping = getServletConfig().getInitParameter ( "mapping" );

    if ( mapping != null ) config.setActionMappingClass ( mapping );

    Digester digester = initConfigDigester();

    while ( paths.length() > 0 )
    {
        digester.push ( config );

        String path = null;

        int comma = paths.indexOf ( ',' );

        if ( comma >= 0 ) { path = paths.substring ( 0, comma ).trim(); paths = paths.substring ( comma + 1 ); }  else { path = paths.trim(); paths = ""; }

        if ( path.length () < 1 )  break;

        this.parseModuleConfigFile ( prefix, paths, config, digester, path );
    }

    FormBeanConfig fbs[] = config.findFormBeanConfigs ();

    for ( int i = 0; i < fbs.length; i++ )
    {
    if ( ! fbs[ i ].getDynamic() ) continue;

    FormPropertyConfig includes = fbs[ i ].findFormPropertyConfig ( "includes" );

    if ( includes != null )
    {
    String formBeanToInclude = includes.getInitial ();

    FormBeanConfig includeConfig = config.findFormBeanConfig ( formBeanToInclude );

    FormPropertyConfig[] props = includeConfig.findFormPropertyConfigs ();

    for ( int j = 0; j < props.length; j++ ) { FormPropertyConfig prop = props[ j ]; fbs[ i ].addFormPropertyConfig ( prop ); }
    }

    DynaActionFormClass.createDynaActionFormClass ( fbs[i] );
    }

    return config;
    }

    /**
     *
     */

    protected void initModuleMessageResources ( ModuleConfig config ) throws ServletException
    {
    List fileNames = new ArrayList();

    MessageResourcesConfig resourceConfig = config.findMessageResourcesConfigs()[ 0 ];

    if ( resourceConfig.getFactory() == null || resourceConfig.getParameter() == null )

    { Common.trace ( "NO MESSAGE RESOURCE CONFIGURATION DEFINED!" ); return; }

    String configParam = resourceConfig.getParameter ();

    if ( configParam.indexOf ( "," ) != -1 )
    {
        StringTokenizer tokenizer = new StringTokenizer ( configParam, "," );

        while ( tokenizer.hasMoreTokens() ) { String token = tokenizer.nextToken(); fileNames.add ( token.trim() ); }
    }
    else { fileNames.add ( configParam ); }

    MessageResources resources = new MessageResources ( fileNames );

    getServletContext().setAttribute ( resourceConfig.getKey() + config.getPrefix(), resources );

    Knock.setMessageResources ( getServletContext() );
    }

    /**
     *
     */

    private void parseModuleConfigFile ( String prefix, String paths, ModuleConfig config, Digester digester, String path ) throws UnavailableException
    {
    InputStream input = null;

    try
    {
        URL url = getServletContext().getResource ( path );

        InputSource is = new InputSource ( url.toExternalForm() );

        input = getServletContext().getResourceAsStream ( path );

        is.setByteStream ( input );

        digester.parse ( is );

        getServletContext().setAttribute ( Globals.MODULE_KEY + prefix, config );

    } catch ( MalformedURLException e ) { handleConfigException ( paths, e ); }

    catch ( IOException e ) { handleConfigException ( paths, e ); }

    catch ( SAXException e ) { handleConfigException ( paths, e ); }

    finally {  if ( input != null ) { try { input.close(); } catch ( IOException e ) { throw new UnavailableException ( e.getMessage() ); } } }
    }

    /**
     *
     */

    private void handleConfigException ( String paths, Exception e ) throws UnavailableException

    { throw new UnavailableException ( internal.getMessage ( "configParse", paths ) ); }

}
