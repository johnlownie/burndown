<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodYard" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.transaction.Transaction" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession" %>

<% PeriodSession  periods  = PeriodSession .getInstance ( request ); request.setAttribute ( "periods" , periods .iterator ( PeriodYard.BY_ORDINAL ) ); %>

<div id="page-content">

<div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Transaction"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.transactions.import"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/transaction_import" method="post" styleId="queryForm"></html:form>

<div class="panel-body">

<html:form action="/transaction_import" method="post" styleId="tableForm" enctype="multipart/form-data">

<% TransactionSession transactions = TransactionSession.getInstance ( request ); new TableWriter ( transactions, Transaction.captions_import(), Transaction.fields_import() ).print ( out, request, true ); %>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/import.jsp" flush="true" />

</html:form>

</div>

<div class="panel-footer text-right"></div>

</div>

<div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="add.a.ofx"/></h3></div>

<div class="panel-body">

<form id="dropzone" action="transaction_import.do" class="dropzone">
<input type="hidden" id="dropPeriodId" name="periodId"/>
<input type="hidden" name="id" value="-1"/>
<input type="hidden" name="items" value="true"/>

<div class="dz-default dz-message">

<div class="dz-icon"><i class="fa fa-cloud-upload fa-5x"></i></div>

<div><span class="dz-text">Drop files to upload</span><p class="text-sm text-muted">or click to pick manually</p></div>

</div>

<div class="fallback"><input name="formFile" type="file"></div>

</form>

</div>

</div>

</div>