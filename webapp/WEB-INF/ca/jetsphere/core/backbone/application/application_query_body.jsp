<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.application.Application" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.application.ApplicationSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<% CompanySession companies = CompanySession.getInstance ( request ); request.setAttribute ( "companies", companies.iterator ( true ) ); %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Application"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.applications"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/application_query" method="post" styleId="queryForm">

<div class="panel-body">

<div class="col-sm-12"><div class="form-group"><label class="control-label"><bean:message key="company"/></label><html:select property="companyId" styleClass="form-control"><html:option value=""><bean:message key="all.companies"/></html:option><html:options collection="companies" property="id" labelProperty="name" /></html:select></div></div>

</div>

</html:form>

<html:form action="/application_edit" method="post" styleId="tableForm">

<div class="panel-body">

<% ApplicationSession applications = ApplicationSession.getInstance ( request ); new TableWriter ( applications, Application.captions(), Application.fields() ).print ( out, request, true ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>