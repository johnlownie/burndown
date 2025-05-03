<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.common.DockYard" %>

<footer id="footer">

<% if ( DockYard.isStaging ( request ) ) { %>

<div class="pad-lft text-xs"><bean:message key="for.demonstration.purposes.only.long"/>&nbsp;<div id="clock" style="display: inline;"></div></div>

<% } else { %>

<div class="hide-fixed pull-right pad-rgt text-xs"><bean:message key="application.version.name"/>&nbsp;<bean:message key="application.version.view"/>.<bean:message key="application.version.build"/></div>

<p class="pad-lft text-xs"><bean:message key="application.version.copyright"/></p>

<% } %>

</footer>
