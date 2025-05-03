<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<%@ page import="ca.jetsphere.core.common.DockYard" %>
<%@ page import="ca.jetsphere.core.security.RequestProcessor" %>

<% if ( RequestProcessor.getLockOut().isWait() || RequestProcessor.getLockOut().isLock() ) { %>

<div id="page-alert"><div class="alert-wrap in"><div class="alert alert-warning" role="alert"><button class="close" type="button"><i class="fa fa-times-circle"></i></button><div class="media">

<% if ( RequestProcessor.getLockOut().isWait() ) { %><bean:message key="server.will.shut.down.in"/>&nbsp;<div id="countdown" style="display: inline;"></div><% } else { %><bean:message key="server.locked.out"/><% } %>

</div></div></div></div>

<% } %>
