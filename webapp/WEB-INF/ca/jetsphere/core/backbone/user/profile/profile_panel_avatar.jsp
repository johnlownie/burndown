<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/user_profile" method="post" styleId="avatarForm">

<div id="password-panel" class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="change.avatar"/></h3></div>

<div class="panel-body">

<div class="form-group"><ul id="avatar-list" class="list-inline">

<li><label class="control-label radio"><html:radio property="avatarId" value="1"/><img class="img-sm img-circle" src="images/avatars/av1.png"/></label></li>
<li><label class="control-label radio"><html:radio property="avatarId" value="2"/><img class="img-sm img-circle" src="images/avatars/av2.png"/></label></li>
<li><label class="control-label radio"><html:radio property="avatarId" value="3"/><img class="img-sm img-circle" src="images/avatars/av3.png"/></label></li>
<li><label class="control-label radio"><html:radio property="avatarId" value="4"/><img class="img-sm img-circle" src="images/avatars/av4.png"/></label></li>
<li><label class="control-label radio"><html:radio property="avatarId" value="5"/><img class="img-sm img-circle" src="images/avatars/av5.png"/></label></li>
<li><label class="control-label radio"><html:radio property="avatarId" value="6"/><img class="img-sm img-circle" src="images/avatars/av6.png"/></label></li>

</ul></div>

</div>

<div class="panel-footer text-right"><html:submit styleClass="btn btn-success text-uppercase btnSave" property="update"><bean:message key="save"/></html:submit></div>

</div>

</html:form>
