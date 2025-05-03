<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/company_member_edit" method="post" styleId="editForm">

<div class="row"><div class="col-sm-6">

<div class="form-group">

<label class="control-label"><bean:message key="first.name"/></label><html:text property="firstname" styleId="firstname" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="last.name"/></label><html:text property="lastname" styleId="lastname" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="email"/></label><html:text property="email" styleId="email" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="avatar"/></label><ul id="avatar-list" class="list-inline">

<li><label class="control-label radio"><html:radio property="avatarId" value="1"/><img class="img-sm img-circle" src="images/avatars/av1.png"/></label></li>
<li><label class="control-label radio"><html:radio property="avatarId" value="2"/><img class="img-sm img-circle" src="images/avatars/av2.png"/></label></li>
<li><label class="control-label radio"><html:radio property="avatarId" value="3"/><img class="img-sm img-circle" src="images/avatars/av3.png"/></label></li>
<li><label class="control-label radio"><html:radio property="avatarId" value="4"/><img class="img-sm img-circle" src="images/avatars/av4.png"/></label></li>
<li><label class="control-label radio"><html:radio property="avatarId" value="5"/><img class="img-sm img-circle" src="images/avatars/av5.png"/></label></li>
<li><label class="control-label radio"><html:radio property="avatarId" value="6"/><img class="img-sm img-circle" src="images/avatars/av6.png"/></label></li>

</ul>

</div>

</div><div class="col-sm-6">

<div class="form-group">

<label class="control-label"><bean:message key="address"/></label><html:text property="street" styleId="street" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="city"/></label><html:text property="city" styleId="city" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="province"/></label><html:text property="province" styleId="province" styleClass="form-control"/>

<label class="control-label"><bean:message key="postal.code"/></label><html:text property="postalCode" styleId="postalCode" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="home.phone"/></label><html:text property="homePhone" styleId="homePhone" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="mobile.phone"/></label><html:text property="mobilePhone" styleId="mobilePhone" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="work.phone"/></label><html:text property="workPhone" styleId="workPhone" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="work.fax"/></label><html:text property="workFax" styleId="workFax" styleClass="form-control"/>

</div>

</div></div><div class="row">

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</div>

</html:form>
