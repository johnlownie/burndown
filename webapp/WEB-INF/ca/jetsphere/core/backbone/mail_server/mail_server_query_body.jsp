<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.mail_server.MailServer" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.mail_server.MailServerSession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<% CompanySession companies = CompanySession.getInstance ( request ); request.setAttribute ( "companies", companies.iterator ( true ) ); %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Mail Server"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.mail.servers"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/mail_server_query" method="post" styleId="queryForm"></html:form>

<html:form action="/mail_server_edit" method="post" styleId="tableForm">

<div class="panel-body">

<% MailServerSession mail_servers = MailServerSession.getInstance ( request ); new TableWriter ( mail_servers, MailServer.captions(), MailServer.fields() ).print ( out, request, true ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>
