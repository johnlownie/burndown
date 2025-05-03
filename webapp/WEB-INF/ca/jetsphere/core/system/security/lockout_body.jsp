<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.common.Clock" %>
<%@ page import="ca.jetsphere.core.security.LockOut" %>
<%@ page import="ca.jetsphere.core.security.RequestProcessor" %>

<% LockOut lockout = RequestProcessor.getLockOut(); boolean enabled = ! ( lockout.isWait() || lockout.isLock() ); String key = enabled ? "start" : "cancel"; %>

<div id="page-content"><div class="panel">

<html:form action="/lockout" method="post" styleId="queryForm" styleClass="form-horizontal">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="lock.out.server"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<div class="panel-body"><div class="row">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="start.time"/></label>

<div class="col-md-6"><% Clock clock = ( Clock ) request.getSession().getAttribute ( "[clock]" ); clock.print ( out, request, enabled, false ); %></div>

</div>

</div></div>

<div class="panel-footer text-right"><html:submit styleClass="btn btn-primary text-uppercase" property="<%= key%>"><bean:message key="<%= key%>"/></html:submit></div>

</html:form>

</div></div>
