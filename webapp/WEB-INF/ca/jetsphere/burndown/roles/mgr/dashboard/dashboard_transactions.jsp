<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.burndown.tier1.backbone.transaction.Transaction" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="transactions"/></h3></div>

<div class="panel-body">

<% TransactionSession transactions = TransactionSession.getInstance ( request ); new TableWriter ( transactions, Transaction.captions_dashboard(), Transaction.fields_dashboard() ).print ( out, request, true ); %>
    
</div>

</div>
