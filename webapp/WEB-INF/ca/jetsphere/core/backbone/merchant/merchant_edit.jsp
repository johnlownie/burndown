<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/merchant_edit" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="company"/></label>

<div class="col-md-6"><html:text property="name" styleId="name" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="folder"/></label>

<div class="col-md-6"><html:text property="folder" styleId="folder" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="merchant.api.url"/></label>

<div class="col-md-6"><html:text property="apiUrl" styleId="apiUrl" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="merchant.api.login"/></label>

<div class="col-md-6"><html:text property="apiLogin" styleId="apiLogin" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="merchant.api.password"/></label>

<div class="col-md-6"><html:text property="apiPassword" styleId="apiPassword" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="merchant.keystore.password"/></label>

<div class="col-md-6"><html:text property="keystorePassword" styleId="keystorePassword" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="default"/></label>

<div class="col-md-6"><div class="checkbox">

<label class="form-checkbox form-normal form-primary form-text"><html:checkbox property="default" styleId="default" styleClass="form-control"/></label><input type="hidden" name="default" value="false"/>

</div></div>

</div>

</div>

<jsp:include page="merchant_edit_script.jsp" flush="true"/>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>