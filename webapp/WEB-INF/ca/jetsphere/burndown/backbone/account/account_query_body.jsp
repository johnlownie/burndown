<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.burndown.tier1.backbone.account.Account" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.account.AccountSession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Account"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.accounts"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<div class="panel-body">

<div class="row">

<html:form action="/account_query" method="post" styleId="queryForm"></html:form>

</div>

<html:form action="/account_edit" method="post" styleId="tableForm">

<% AccountSession accounts = AccountSession.getInstance ( request ); new TableWriter ( accounts, Account.captions(), Account.fields() ).print ( out, request, true ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>
