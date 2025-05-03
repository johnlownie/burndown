<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role.RoleSession" %>

<% CompanySession companies = CompanySession.getInstance ( request ); request.setAttribute ( "companies", companies.iterator ( true ) ); %>
<% PeriodSession periods    = PeriodSession.getInstance  ( request ); request.setAttribute ( "periods"  , periods  .iterator ( true ) ); %>
<% RoleSession roles        = RoleSession.getInstance    ( request ); request.setAttribute ( "roles"    , roles    .iterator ( true ) ); %>

<html:form action="/application_edit" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="name"/></label>

<div class="col-md-6"><html:text property="name" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="default.period"/></label>

<div class="col-md-6"><html:select property="periodId" styleId="periodId" styleClass="form-control"><html:options collection="periods" property="id" labelProperty="name" /></html:select></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="default.role"/></label>

<div class="col-md-6"><html:select property="roleId" styleId="roleId" styleClass="form-control"><html:options collection="roles" property="id" labelProperty="name" /></html:select></div>

</div>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>