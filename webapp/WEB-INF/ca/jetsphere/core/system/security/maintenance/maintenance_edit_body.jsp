<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div id="page-content"><div class="panel">

<html:form action="/maintenance_edit" method="post" styleId="queryForm" styleClass="form-horizontal">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="maintenance.mode"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<div class="panel-body"><div class="row">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="mode"/></label>

<div class="col-md-6"><select name="maintenance-mode" class="form-control"><option value="0">NORMAL OPERATION</option><option value="1">NO MORE LOGINS</option><option value="2">NO ORDERING</option><option value="3">SUSPENSION</option></select></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="message"/></label>

<div class="col-md-6"><input type="text" name="maintenance-message" class="form-control"/></div>

</div>

</div></div>

<div class="panel-footer text-right"><button type="submit" class="btn btn-primary text-uppercase"><bean:message key="save"/></button></div>

</html:form>

</div></div>
