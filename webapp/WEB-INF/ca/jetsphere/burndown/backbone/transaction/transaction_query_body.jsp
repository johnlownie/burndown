<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.burndown.tier1.backbone.account.AccountSession" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.category.CategorySession" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.category.CategoryYard" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.transaction.Transaction" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodYard" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<% PeriodSession  periods  = PeriodSession .getInstance ( request ); request.setAttribute ( "periods" , periods .iterator ( PeriodYard.BY_ORDINAL ) ); %>
<% AccountSession accounts = AccountSession.getInstance ( request ); request.setAttribute ( "accounts", accounts.iterator() ); %>
<% CategorySession categories = CategorySession.getInstance ( request ); request.setAttribute ( "categories", categories.iterator ( CategoryYard.BY_ORDINAL ) ); %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Transaction"/>">

<div class="panel-heading">
    
<h3 class="panel-title">
    
<span><bean:message key="show.transactions"/></span>

<div class="button-wrapper pull-right"><button id="duplicates" class="btn btn-primary"><bean:message key="check.for.duplicates"/></button></div>

</h3>

</div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<div class="panel-body">

<div class="row">

<html:form action="/transaction_query" method="post" styleId="queryForm">
<html:hidden property="toggle" styleId="toggle" value="0"/>

<div class="col-sm-3"><div class="form-group"><label class="control-label"><bean:message key="period"/></label><html:select property="periodId" styleId="periodId" styleClass="form-control"><html:options collection="periods" property="id" labelProperty="name" /></html:select></div></div>

<div class="col-md-3"><div class="form-group"><label class="control-label"><bean:message key="account"/></label><html:select property="accountId" styleId="accountId" styleClass="form-control"><html:option value="-1"><bean:message key="account.all"/></html:option><html:options collection="accounts" property="id" labelProperty="nameNumber" /></html:select></div></div>

<div class="col-md-3"><div class="form-group"><label class="control-label"><bean:message key="category"/></label><html:select property="categoryId" styleId="categoryId" styleClass="form-control"><html:option value="-1"><bean:message key="category.all"/></html:option><html:options collection="categories" property="id" labelProperty="treeName" /></html:select></div></div>

<div class="col-sm-3"><div class="form-group"><label class="control-label"><bean:message key="type"/></label><html:select property="typeId" styleId="typeId" styleClass="form-control"><html:option value="-1"><bean:message key="transaction.type.all"/></html:option><html:option value="1"><bean:message key="transaction.type.debit"/></html:option><html:option value="2"><bean:message key="transaction.type.credit"/></html:option></html:select></div></div>

</html:form>

</div>

<html:form action="/transaction_edit" method="post" styleId="tableForm">

<% TransactionSession transactions = TransactionSession.getInstance ( request ); new TableWriter ( transactions, Transaction.captions(), Transaction.fields() ).print ( out, request, true ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>
