<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.category.Category" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.category.CategorySession" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.category.writer.CategoryWriter" %>

<% CompanySession companies = CompanySession.getInstance ( request ); request.setAttribute ( "companies", companies.iterator ( true ) ); %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Category"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.categories"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<div class="panel-body">

<html:form action="/category_query" method="post" styleId="queryForm"></html:form>

<html:form action="/category_edit" method="post" styleId="treeForm">

<% CategorySession categories = CategorySession.getInstance ( request ); new CategoryWriter ( categories, Category.captions(), Category.fields() ).print ( out, request, true ); %>

</html:form>

</div><div class="panel-footer text-right">

<button type="button" class="btn btn-primary btn-labeled fa fa-expand text-uppercase expandBtn"><bean:message key="expand.all"/></button>

<button type="button" class="btn btn-primary btn-labeled fa fa-compress text-uppercase collapseBtn"><bean:message key="collapse.all"/></button>

</div>

</div></div>
