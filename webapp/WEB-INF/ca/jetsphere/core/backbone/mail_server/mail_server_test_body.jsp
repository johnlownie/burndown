<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div id="page-content"><div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="mail.server.test"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/mail_server_test" method="post" styleId="queryForm">

<div class="panel-body"><div class="row">

<div class="form-group">

<label for="to" class="control-label"><bean:message key="email.to"/></label><html:text property="to" styleId="to" styleClass="form-control"/>

</div><div class="form-group">

<label for="subject" class="control-label"><bean:message key="email.subject"/></label><html:text property="subject" styleId="subject" styleClass="form-control"/>

</div><div class="form-group">

<label for="text" class="control-label"><bean:message key="email.message"/></label><html:text property="text" styleId="text" styleClass="form-control"/>

</div>

</div></div>

<div class="panel-footer text-right">

<html:submit styleClass="btn btn-primary text-uppercase" property="send"><bean:message key="send"/></html:submit>

</div>

</html:form>

</div></div>
