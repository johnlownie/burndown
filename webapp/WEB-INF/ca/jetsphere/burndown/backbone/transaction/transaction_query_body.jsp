<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodYard" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.transaction.Transaction" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession" %>

<% PeriodSession  periods  = PeriodSession .getInstance ( request ); request.setAttribute ( "periods" , periods .iterator ( PeriodYard.BY_ORDINAL ) ); %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Transaction"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.transactions"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<div class="panel-body">

<div class="row">

<html:form action="/transaction_query" method="post" styleId="queryForm">

<div class="col-sm-12"><div class="form-group"><label class="control-label"><bean:message key="period"/></label><html:select property="periodId" styleId="periodId" styleClass="form-control"><html:options collection="periods" property="id" labelProperty="name" /></html:select></div></div>

</html:form>

</div>

<html:form action="/transaction_edit" method="post" styleId="tableForm">

<% TransactionSession transactions = TransactionSession.getInstance ( request ); new TableWriter ( transactions, Transaction.captions(), Transaction.fields() ).print ( out, request, true ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>
