<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.company_member.CompanyMember" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company_member.CompanyMemberSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<% CompanySession companies = CompanySession.getInstance ( request ); request.setAttribute ( "companies", companies.iterator ( true ) ); %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Company Member"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.company.members"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/company_member_query" method="post" styleId="queryForm"></html:form>

<html:form action="/company_member_edit" method="post" styleId="tableForm">

<div class="panel-body">

<% CompanyMemberSession company_members = CompanyMemberSession.getInstance ( request ); new TableWriter ( company_members, CompanyMember.captions(), CompanyMember.fields() ).print ( out, request, true ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>