<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.common.Caption" %>
<%@ page import="ca.jetsphere.core.common.DockYard" %>

<% String caption = DockYard.toBoolean ( request, "verified" ) ? "account.verified" : DockYard.isStaging ( request ) ? "sign.in.to.your.account.demo" : "sign.in.to.your.account"; %>

<div class="cls-content">

<div class="cls-content-sm panel">

<div class="panel-body">

<% if ( DockYard.isStaging ( request ) ) { %><p><bean:message key="for.demonstration.purposes.only"/></p><% } %>

<p class="pad-btm"><%= Caption.get ( request, caption ) %></p>

<html:form action="/login" method="post" styleId="loginForm">

<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-envelope"></i></div>

<label for="username" class="sr-only"><bean:message key="email.address"/></label>

<html:text property="username" styleId="username" styleClass="form-control"/>

</div></div>

<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-asterisk"></i></div>

<label for="password" class="sr-only"><bean:message key="password"/></label>

<html:password property="password" styleId="password" styleClass="form-control"/>

</div></div>

<div class="form-group text-right">

<html:submit property="submit" styleClass="btn btn-success text-uppercase"><bean:message key="log.in"/></html:submit>

</div><div class="form-group">

<div id="messages" class="text-danger text-center pad-ver" style="display: none;"></div>

</div><div class="form-group mar-no">

<html:link action="/reset_password" styleClass="btn-link mar-rgt text-xs" style="color: #515151;"><bean:message key="forgot.password"/> ?</html:link>

</div>

</html:form>

</div>

</div>

<div class="cls-content-sm panel" style="margin-top: 10px;"><div class="panel-body">

<html:link action="/register" styleClass="btn-link" style="color: #515151;"><bean:message key="dont.have.an.account"/>&nbsp;<bean:message key="click.here.caps"/></html:link>

</div></div>

<div class="pad-ver mar-top">

</div>

</div>
