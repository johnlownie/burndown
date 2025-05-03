<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.brand.Brand" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier2.common.ActionType" %>

<div class="panel-heading"><h3 class="panel-title"><%= ActionType.title ( request, Brand.key() ) %></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<% CompanySession companies = CompanySession.getInstance ( request ); request.setAttribute ( "companies", companies.iterator ( true ) ); %>

<div class="row">

<html:form action="/brand_edit" method="post">

<div class="form-group">

<label for="companyId" class="control-label"><bean:message key="company"/></label><html:select property="companyId" styleId="companyId" styleClass="form-control"><html:options collection="companies" property="id" labelProperty="name" /></html:select>

</div><div class="form-group">

<label for="name" class="control-label"><bean:message key="name"/></label><html:text property="name" styleId="name" styleClass="form-control"/>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/ie.jsp" flush="true" />

</html:form>

</div>
