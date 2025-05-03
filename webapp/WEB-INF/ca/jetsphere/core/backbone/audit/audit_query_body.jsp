<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.audit.Audit" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.audit.AuditSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.user.UserSession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<% UserSession users = UserSession.getInstance ( request ); request.setAttribute ( "users", users.iterator ( true ) ); %>

<div id="page-content"><div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.audits"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/audit_query" method="post" styleId="queryForm">

<div class="panel-body">

<div class="row">

<div class="form-group"><label class="control-label"><bean:message key="users"/></label><html:select property="userId" styleClass="form-control" onchange="submit();"><html:option value="-1"><bean:message key="all.users"/></html:option><html:options collection="users" property="id" labelProperty="name" /></html:select></div>

</div>

<% AuditSession audits = AuditSession.getInstance ( request ); new TableWriter ( audits, Audit.captions(), Audit.fields() ).print ( out, request ); %>

</div>

</html:form>

</div></div>
