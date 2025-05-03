<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.brand.Brand" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.brand.BrandSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<% CompanySession companies = CompanySession.getInstance ( request ); request.setAttribute ( "companies", companies.iterator ( true ) ); %>

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.brands"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/brand_query" method="post" styleId="queryForm">

<div class="panel">

<div class="actions"><p><bean:message key="company"/><br><html:select property="companyId" onchange="submit();"><html:option value="-1"><bean:message key="all.companies"/></html:option><html:options collection="companies" property="id" labelProperty="name" /></html:select></p></div>

<% BrandSession brands = BrandSession.getInstance ( request ); new TableWriter ( brands, Brand.captions(), Brand.fields() ).print ( out, request ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>
