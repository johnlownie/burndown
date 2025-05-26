<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/account_edit" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="name"/></label>

<div class="col-md-6"><html:text property="name" styleId="name" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="account.type"/></label>

<div class="col-md-6"><html:text property="type" styleId="type" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="account.number"/></label>

<div class="col-md-6"><html:text property="number" styleId="number" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="account.bank.id"/></label>

<div class="col-md-6"><html:text property="bankId" styleId="bankId" styleClass="form-control"/></div>

</div>

</div>

<jsp:include page="account_edit_script.jsp" flush="true"/>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>
