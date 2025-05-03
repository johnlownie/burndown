<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/user_profile" method="post" styleId="aboutForm">

<div id="password-panel" class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="about.me"/></h3></div>

<div class="panel-body">

<div class="form-group">

<label class="control-label"><bean:message key="first.name"/></label><html:text property="firstname" styleId="firstname" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="last.name"/></label><html:text property="lastname" styleId="lastname" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="address"/></label><html:text property="street" styleId="address" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="city"/></label><html:text property="city" styleId="city" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="province"/></label><html:text property="province" styleId="province" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="postal.code"/></label><html:text property="postalCode" styleId="postalCode" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="phone"/></label><html:text property="mobilePhone" styleId="mobilePhone" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="email"/></label><html:text property="email" styleId="email" styleClass="form-control"/>

</div><div class="form-group">

<label class="control-label"><bean:message key="newsletters.and.reminders"/></label>

<div class="checkbox">

<label class="form-normal form-primary form-text"><html:checkbox property="subscribed" styleId="active"/> <bean:message key="subscribed"/></label><input type="hidden" name="active" value="false"/>

</div>

</div>

</div>

<div class="panel-footer text-right"><html:submit styleClass="btn btn-success text-uppercase" property="update"><bean:message key="save"/></html:submit></div>

</div>

</html:form>
