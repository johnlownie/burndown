<%@ page import="ca.jetsphere.core.common.DockYard" %>
<%@ page import="ca.jetsphere.core.tier1.system.knock.Knock" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<div class="cls-content">

<div class="cls-content-sm panel">

<div class="panel-body">

<% if ( DockYard.isStaging ( request ) ) { %><p><bean:message key="for.demonstration.purposes.only"/></p><% } %>

<p class="pad-btm"><bean:message key="create.an.account"/></p>

<html:form action="/registerwe" styleId="regForm" method="post">

<input type="hidden" name="siteKey" id="siteKey" value="<%= Knock.get ( "SITEKEY" ) %>"/>

<div class="form-group">

<div class="input-group"><div class="input-group-addon"><i class="fa fa-male"></i></div>

<label class="sr-only"><bean:message key="your.first.and.last.name"/></label>

<html:text property="fullname" styleId="fullname" styleClass="form-control"/>

</div>

</div><div class="form-group">

<div class="input-group"><div class="input-group-addon"><i class="fa fa-envelope"></i></div>

<label class="sr-only"><bean:message key="username"/></label>

<html:text property="username" styleId="username" styleClass="form-control"/>

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

</div><div class="form-group">

<div id="captchaContainer"></div>

</div><div class="form-group text-right">

<html:submit property="update" styleClass="btn btn-primary text-uppercase"><bean:message key="sign.up"/></html:submit>

</div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

</html:form>

</div>

</div>

<div class="pad-ver">

<bean:message key="already.have.an.account"/> <html:link action="/login" styleClass="btn-link"><bean:message key="sign.in"/></html:link>

</div>

</div>
