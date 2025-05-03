<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.application.ApplicationSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role.Role" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role.RoleSession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<% ApplicationSession applications = ApplicationSession.getInstance ( request ); request.setAttribute ( "applications", applications.iterator ( true ) ); %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Role"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.roles"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/role_query" method="post" styleId="queryForm">

<div class="panel-body">

<div class="row">

<div class="col-sm-12"><div class="form-group"><label class="control-label"><bean:message key="application"/></label><html:select property="applicationId" styleClass="form-control"><html:option value="-1"><bean:message key="all.applications"/></html:option><html:options collection="applications" property="id" labelProperty="name" /></html:select></div></div>

</div>

</html:form>

<html:form action="/role_edit" method="post" styleId="tableForm">

<% RoleSession roles = RoleSession.getInstance ( request ); new TableWriter ( roles, Role.captions(), Role.fields() ).print ( out, request, true ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>