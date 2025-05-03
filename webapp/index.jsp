<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="ca.jetsphere.core.common.DockYard" %>
<%@ page import="ca.jetsphere.core.security.cookie.CookieYard" %>

<% boolean rememberme = DockYard.toBoolean ( CookieYard.getValue ( request, "rememberme" ) ); if ( rememberme ) { %>

<html><body><logic:forward name="login"/></body></html>

<% } else { %>

<html><body><logic:forward name="register"/></body></html>

<% } %>
