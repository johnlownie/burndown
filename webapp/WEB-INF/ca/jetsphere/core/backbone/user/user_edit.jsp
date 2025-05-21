<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="java.util.List" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.application.ApplicationSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role.RoleSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.user.common.Status" %>

<% ApplicationSession applications = ApplicationSession.getInstance ( request ); request.setAttribute ( "applications", applications.iterator ( true ) ); %>
<% CompanySession companies = CompanySession.getInstance ( request ); request.setAttribute ( "companies", companies.iterator ( true ) ); %>
<% RoleSession roles = RoleSession.getInstance ( request ); request.setAttribute ( "roles", roles.iterator ( true ) ); %>
<% List statuses = Status.get ( request, "" ); request.setAttribute ( "statuses", statuses ); %>

<html:form action="/user_edit" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="username"/></label>

<div class="col-md-6"><html:text property="username" styleId="username" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="password"/></label>

<div class="col-md-6"><html:password property="newPassword" styleId="newPassword" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="confirm.password"/></label>

<div class="col-md-6"><html:password property="confirmPassword" styleId="confirmPassword" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="status"/></label>

<div class="col-md-6"><html:select property="status" styleId="status" styleClass="form-control"><html:options collection="statuses" property="value" labelProperty="label"/></html:select></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="roles"/></label>

<div class="col-md-6"><html:select property="roles" styleId="roles" styleClass="form-control chosenselect" multiple="multiple" titleKey="please.select"><html:options collection="roles" property="id" labelProperty="name" /></html:select></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="company"/></label>

<div class="col-md-6"><html:select property="companyId" styleId="companyId" styleClass="form-control"><html:options collection="companies" property="id" labelProperty="name" /></html:select></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="application"/></label>

<div class="col-md-6"><html:select property="applicationId" styleId="applicationId" styleClass="form-control"><html:options collection="applications" property="id" labelProperty="name" /></html:select></div>

</div>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>

<jsp:include page="user_edit_script.jsp" flush="true"/>