<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/change_password" method="post" styleId="passwordForm">

<div id="password-panel" class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="change.password"/></h3></div>

<div class="panel-body">

<div class="form-group">

<label for="password" class="control-label"><bean:message key="current.password"/></label><html:password property="password" styleId="password" styleClass="form-control"/>

</div><div class="form-group">

<label for="newPassword" class="control-label"><bean:message key="new.password"/></label><html:password property="newPassword" styleId="newPassword" styleClass="form-control"/>

</div><div class="form-group">

<label for="newPassword" class="control-label"><bean:message key="confirm.password"/></label><html:password property="confirmPassword" styleId="confirmPassword" styleClass="form-control"/>

</div>

</div>

<div class="panel-footer text-right"><html:submit styleClass="btn btn-success text-uppercase" property="update"><bean:message key="save"/></html:submit></div>

</div>

</html:form>
