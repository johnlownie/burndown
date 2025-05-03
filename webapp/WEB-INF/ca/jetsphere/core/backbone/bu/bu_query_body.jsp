<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.application.ApplicationSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodSession" %>

<% ApplicationSession applications = ApplicationSession.getInstance ( request ); request.setAttribute ( "applications", applications.iterator ( true ) ); %>
<% PeriodSession      periods      = PeriodSession     .getInstance ( request ); request.setAttribute ( "periods"     , periods     .iterator ( true ) ); %>

<div id="page-content"><div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.bus"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/bu_query" method="post" styleId="queryForm">

<div class="panel-body">

<div class="row">

<div class="col-sm-6"><div class="form-group"><label class="control-label"><bean:message key="application"/></label><html:select property="applicationId" styleClass="form-control" onchange="submit();"><html:option value="-1"><bean:message key="all.applications"/></html:option><html:options collection="applications" property="id" labelProperty="name" /></html:select></div></div>

<div class="col-sm-6"><div class="form-group"><label class="control-label"><bean:message key="period"/></label><html:select property="periodId" styleClass="form-control" onchange="submit();"><html:option value="-1"><bean:message key="all.periods"/></html:option><html:options collection="periods" property="id" labelProperty="name" /></html:select></div></div>

</div>

<div id="row"><div id="treeview" class="col-sm-12"></div></div>

</div>

<div class="panel-footer text-right">

<button type="button" id="btn-expand-all" class="btn btn-primary btn-labeled fa fa-expand text-uppercase"><bean:message key="expand"/></button>

<button type="button" id="btn-collapse-all" class="btn btn-primary btn-labeled fa fa-compress text-uppercase"><bean:message key="collapse"/></button>

</div>

</html:form>

</div></div>