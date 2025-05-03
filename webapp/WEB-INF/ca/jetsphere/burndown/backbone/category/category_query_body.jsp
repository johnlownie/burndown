<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.category.Category" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.category.CategorySession" %>

<% CompanySession companies = CompanySession.getInstance ( request ); request.setAttribute ( "companies", companies.iterator ( true ) ); %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Category"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.categories"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/category_query" method="post" styleId="queryForm"></html:form>

<html:form action="/category_edit" method="post" styleId="tableForm">

<div class="panel-body">

<% CategorySession categories = CategorySession.getInstance ( request ); new TableWriter ( categories, Category.captions(), Category.fields() ).print ( out, request, true ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>
