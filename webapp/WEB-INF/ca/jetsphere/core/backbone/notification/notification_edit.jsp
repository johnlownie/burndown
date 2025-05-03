<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.user.UserSession" %>
<%@ page import="java.util.List" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.notification.NotificationYard" %>

<% UserSession users = UserSession.getInstance ( request ); request.setAttribute ( "users", users.iterator ( true ) ); %>
<% List types = NotificationYard.getTypes(); request.setAttribute ( "types", types ); %>

<html:form action="/notification_edit" method="post" styleId="editForm" styleClass="form-horizontal">
<html:hidden property="timer" value="5000"/>

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="user"/></label>

<div class="col-md-6"><html:select property="userId" styleId="periodId" styleClass="form-control chosenselect"><html:option value="-1"><bean:message key="all.users"/></html:option><html:options collection="users" property="id" labelProperty="name" /></html:select></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="type"/></label>

<div class="col-md-6"><html:select property="type" styleId="type" styleClass="form-control"><html:option value=""><bean:message key="please.select"/></html:option><html:options name="types"/></html:select></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="icon"/></label>

<div class="col-md-6"><html:text property="icon" styleId="icon" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="title"/></label>

<div class="col-md-6"><html:text property="title" styleId="title" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="message"/></label>

<div class="col-md-6"><html:text property="message" styleId="message" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="container"/></label>

<div class="col-md-6"><html:text property="container" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="show.for"/></label>

<div class="col-md-6"><html:text property="timer" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="show.for"/></label>

<div class="col-md-6"><html:text property="roleId" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="show.for"/></label>

<div class="col-md-6"><html:text property="buId" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="show.for"/></label>

<div class="col-md-6"><html:text property="url" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="show.for"/></label>

<div class="col-md-6"><html:text property="until" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="show.for"/></label>

<div class="col-md-6"><html:text property="hasRead" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="show.for"/></label>

<div class="col-md-6"><html:text property="active" styleClass="form-control"/></div>

</div>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>

<jsp:include page="notification_edit_script.jsp" flush="true"/>
