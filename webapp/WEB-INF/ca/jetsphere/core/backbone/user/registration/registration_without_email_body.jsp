<%@ page import="ca.jetsphere.core.common.DockYard" %>
<%@ page import="ca.jetsphere.core.tier1.system.knock.Knock" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<div class="cls-content">

<div class="cls-content-sm panel"><div class="panel-body">

<html:link action="/login" styleClass="btn-link text-bold" style="color: #515151;"><bean:message key="already.have.an.account"/>&nbsp;<bean:message key="click.here.caps"/></html:link>

</div></div>

<div class="cls-content-sm panel" style="margin-top: 10px;">

<div class="panel-body main-body">

<% if ( DockYard.isStaging ( request ) ) { %><p><bean:message key="for.demonstration.purposes.only"/></p><% } %>

<p class="pad-no"><bean:message key="sign.up.to.create.an.account"/></p>

<p class="text-xs text-right text-danger"><bean:message key="error.all.fields.are.required"/></p>

<html:form action="/register" styleId="regForm" method="post">

<input type="hidden" name="siteKey" id="siteKey" value="<%= Knock.get ( "SITEKEY" ) %>"/>

<div class="form-group">

<div class="input-group"><div class="input-group-addon"><i class="fa fa-male"></i></div>

<label class="sr-only"><bean:message key="your.first.and.last.name"/></label>

<html:text property="fullname" styleId="fullname" styleClass="form-control"/>

</div>

</div><div class="form-group">

<div class="input-group"><div class="input-group-addon"><i class="fa fa-phone"></i></div>

<label class="sr-only"><bean:message key="mobile.phone"/></label>

<html:text property="phone" styleId="phone" styleClass="form-control"/>

</div>

</div><div class="form-group">

<div class="input-group"><div class="input-group-addon"><i class="fa fa-envelope"></i></div>

<label class="sr-only"><bean:message key="username"/></label>

<html:text property="username" styleId="username" styleClass="form-control"/>

</div>

</div><div class="form-group">

<div class="input-group"><div class="input-group-addon"><i class="fa fa-envelope"></i></div>

<label class="sr-only"><bean:message key="confirm.email.address"/></label>

<html:text property="confirmUsername" styleId="confirmUsername" styleClass="form-control"/>

</div>

</div><div class="form-group">

<div class="input-group"><div class="input-group-addon"><i class="fa fa-asterisk"></i></div>

<label class="sr-only"><bean:message key="password"/></label>

<html:password property="newPassword" styleId="password" styleClass="form-control"/>

</div>

</div><div class="form-group">

<div class="input-group"><div class="input-group-addon"><i class="fa fa-asterisk"></i></div>

<label class="sr-only"><bean:message key="confirm.password"/></label>

<html:password property="confirmPassword" styleId="confirmPassword" styleClass="form-control"/>

</div>

</div>

<div class="form-group"><div id="captchaContainer"></div></div>

<div class="form-group text-right">

<html:submit property="update" styleClass="btn btn-success text-uppercase"><bean:message key="sign.up"/></html:submit>

</div><div class="form-group">

<p class="text-xs text-center"><bean:message key="registration.useage"/></p>

</div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

</html:form>

</div>

</div>

</div>
