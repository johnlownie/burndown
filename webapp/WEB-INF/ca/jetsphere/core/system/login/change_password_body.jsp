<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.common.DockYard" %>

<div class="cls-content">

<div class="cls-content-sm panel">

<div class="panel-body">

<p class="pad-btm"><bean:message key="change.password.text"/></p>

<html:form action="/reset_password" method="post" styleId="changePasswordForm">

<html:hidden property="uuid" value='<%= DockYard.getParameter( request, "vkey" )%>'/>

<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-asterisk"></i></div>

<label for="password" class="sr-only"><bean:message key="new.password"/></label>

<html:password property="newPassword" styleId="password" styleClass="form-control"/>

</div></div>

<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-asterisk"></i></div>

<label for="confirmPassword" class="sr-only"><bean:message key="confirm.password"/></label>

<html:password property="confirmPassword" styleId="confirmPassword" styleClass="form-control"/>

</div></div>

<div class="form-group text-right">

<html:submit property="update" styleClass="btn btn-success text-uppercase"><bean:message key="save"/></html:submit>

</div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

</html:form>

</div>

</div>

<div class="pad-ver">

<html:link action="/login" styleClass="btn-link mar-rgt"><bean:message key="back.to.login"/></html:link>

</div>

</div>
