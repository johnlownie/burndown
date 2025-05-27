<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.burndown.tier1.backbone.transaction.Transaction" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<div class="panel">

<div class="panel-heading">
    
<h3 id="transactions-title" class="panel-title">
    
<span><bean:message key="mgr.dashboard.by.transactions.debit"/></span>

<div class="button-wrapper pull-right">
<button id="all" class="btn btn-primary"><bean:message key="all"/></button>
<button id="necessity" class="btn btn-primary"><bean:message key="necessity"/></button>
<button id="discretionary" class="btn btn-primary"><bean:message key="discretionary"/></button>
</div>

</h3>

</div>

<div class="panel-body">

<% TransactionSession transactions = TransactionSession.getInstance ( request ); new TableWriter ( transactions, Transaction.captions_dashboard(), Transaction.fields_dashboard() ).print ( out, request, true ); %>
    
</div>

</div>
