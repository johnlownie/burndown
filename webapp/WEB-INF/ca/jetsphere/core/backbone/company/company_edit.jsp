<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/company_edit" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="name"/></label>

<div class="col-md-6"><html:text property="name" styleId="name" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="street"/></label>

<div class="col-md-6"><html:text property="street" styleId="street" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="city"/></label>

<div class="col-md-6"><html:text property="city" styleId="city" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="province"/></label>

<div class="col-md-6"><html:text property="province" styleId="province" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="postal.code"/></label>

<div class="col-md-6"><html:text property="postalCode" styleId="postalCode" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="phone"/></label>

<div class="col-md-6"><html:text property="phone" styleId="phone" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="fax"/></label>

<div class="col-md-6"><html:text property="fax" styleId="fax" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="email"/></label>

<div class="col-md-6"><html:text property="email" styleId="email" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="url"/></label>

<div class="col-md-6"><html:text property="url" styleId="url" styleClass="form-control"/></div>

</div>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>