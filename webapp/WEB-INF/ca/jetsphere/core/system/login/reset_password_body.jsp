<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div class="cls-content">

<div class="cls-content-sm panel">

<div class="panel-body main-body">

<p class="pad-btm"><bean:message key="reset.password.text"/></p>

<html:form action="/reset_password" method="post" styleId="resetForm">

<label for="username" class="sr-only"><bean:message key="username"/></label>

<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-envelope"></i></div>

<html:text property="username" styleId="username" styleClass="form-control"/>

</div></div>

<div class="form-group text-right">

<html:submit property="submit" styleClass="btn btn-success text-uppercase"><bean:message key="reset.password"/></html:submit>

</div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

</html:form>

</div>

</div>

<div class="cls-content-sm panel" style="margin-top: 10px;"><div class="panel-body">

<html:link action="/login" styleClass="btn-link mar-rgt" style="color: #515151;"><bean:message key="back.to.login"/></html:link>

</div></div>

</div>
