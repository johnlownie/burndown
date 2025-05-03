<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/mail_server_edit" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="mail.host"/></label>

<div class="col-md-6"><html:text property="host" styleId="host" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="mail.type"/></label>

<div class="col-md-6"><html:text property="type" styleId="type" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="mail.port"/></label>

<div class="col-md-6"><html:text property="port" styleId="port" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="login"/></label>

<div class="col-md-6"><html:text property="login" styleId="login" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="password"/></label>

<div class="col-md-6"><html:password property="password" styleId="password" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="mail.tls"/></label>

<div class="col-md-6"><div class="checkbox">

<label class="form-normal form-primary form-text"><html:checkbox property="tls"/></label><input type="hidden" name="tls" value="false"/>

</div></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="mail.ssl"/></label>

<div class="col-md-6"><div class="checkbox">

<label class="form-normal form-primary form-text"><html:checkbox property="ssl"/></label><input type="hidden" name="ssl" value="false"/>

</div></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="default"/></label>

<div class="col-md-6"><div class="checkbox">

<label class="form-normal form-primary form-text"><html:checkbox property="default"/></label><input type="hidden" name="default" value="false"/>

</div></div>

</div>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>