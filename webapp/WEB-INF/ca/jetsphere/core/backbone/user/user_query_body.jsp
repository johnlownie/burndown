<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="java.util.List" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role.RoleSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.user.User" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.user.UserSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.user.common.Status" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<% CompanySession companies = CompanySession.getInstance ( request ); request.setAttribute ( "companies", companies.iterator ( true ) ); %>
<% RoleSession    roles     = RoleSession   .getInstance ( request ); request.setAttribute ( "roles"    , roles    .iterator ( true ) ); %>
<% List statuses = Status.get ( request, "" ); request.setAttribute ( "statuses", statuses ); %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="User"/>">

<html:form action="/user_query" method="post" styleId="queryForm">

<html:hidden property="role" styleId="role" value=""/>

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.users"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<div class="panel-body">

</html:form>

<html:form action="/user_edit" method="post" styleId="tableForm">

<% UserSession users = UserSession.getInstance ( request ); new TableWriter ( users, User.captions(), User.fields() ).print ( out, request, true ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>
