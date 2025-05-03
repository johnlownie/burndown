<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.company.Company" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Company"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.companies"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/company_query" method="post" styleId="queryForm"></html:form>

<html:form action="/company_edit" method="post" styleId="tableForm">

<div class="panel-body">

<% CompanySession companies = CompanySession.getInstance ( request ); new TableWriter ( companies, Company.captions(), Company.fields() ).print ( out, request, true ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>