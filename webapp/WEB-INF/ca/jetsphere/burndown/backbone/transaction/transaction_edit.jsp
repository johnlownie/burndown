<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.burndown.tier1.backbone.category.CategorySession" %>
<%@ page import="ca.jetsphere.burndown.tier1.backbone.category.CategoryYard" %>

<% CategorySession categories = CategorySession.getInstance ( request ); request.setAttribute ( "categories", categories.iterator ( CategoryYard.BY_ORDINAL ) ); %>

<html:form action="/transaction_edit" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="date"/></label>

<div class="col-md-6"><div class="input-group date"><html:text property="dateAsString" styleId="date" styleClass="form-control" size="12" readonly="false"/><span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span></div></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="name"/></label>

<div class="col-md-6"><html:text property="name" styleId="name" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="type"/></label>

<div class="col-sm-6"><html:select property="type" styleId="type" styleClass="form-control"><html:option value="-1"><bean:message key="please.select"/></html:option><html:option value="1"><bean:message key="transaction.type.debit"/></html:option><html:option value="2"><bean:message key="transaction.type.credit"/></html:option></html:select></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="transaction.amount"/></label>

<div class="col-md-6"><html:text property="amount" styleId="amount" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="category"/></label>

<div class="col-md-6"><html:select property="categoryId" styleId="categoryId" styleClass="form-control chosenselect"><html:option value="-1"><bean:message key="please.select"/></html:option><html:options collection="categories" property="id" labelProperty="treeName" /></html:select></div>

</div>

</div>

<jsp:include page="transaction_edit_script.jsp" flush="true"/>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>
