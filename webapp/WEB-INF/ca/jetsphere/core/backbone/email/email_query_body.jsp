<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.email.Email" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.email.EmailSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodSession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<% PeriodSession  periods = PeriodSession.getInstance ( request ); request.setAttribute ( "periods"  , periods  .iterator ( true ) ); %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Email"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.emails"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/email_query" method="post" styleId="queryForm">

</html:form>

<html:form action="/email_edit" method="post" styleId="tableForm">

<div class="panel-body">

<% EmailSession emails = EmailSession.getInstance ( request ); new TableWriter ( emails, Email.captions(), Email.fields() ).print ( out, request, true ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>
