<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.burndown.tier1.backbone.transaction.Transaction" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.transaction.TransactionSession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<div class="panel">
<input type="hidden" name="typeId" id="typeId" value=""/>

<div class="panel-heading">
    
<h3 id="transactions-title" class="panel-title">
    
<span><bean:message key="mgr.dashboard.by.transactions.debit"/></span>

<div class="btn-group pull-right" role="group">
    
<input id="btnAll" class="btn-check" type="radio" name="btnradio" autocomplete="off" checked="">
<label class="btn btn-outline-primary" for ="btnAll">All</label>
    
<input id="btnFixed" class="btn-check" type="radio" name="btnradio" autocomplete="off">
<label class="btn btn-outline-primary" for ="btnFixed">Fixed</label>
    
<input id="btnDiscretionary" class="btn-check" type="radio" name="btnradio" autocomplete="off">
<label class="btn btn-outline-primary" for ="btnDiscretionary">Discretionary</label>
    
</div>

</h3>

</div>

<div class="panel-body">

<% TransactionSession transactions = TransactionSession.getInstance ( request ); new TableWriter ( transactions, Transaction.captions_dashboard(), Transaction.fields_dashboard() ).print ( out, request, true ); %>
    
</div>

</div>
