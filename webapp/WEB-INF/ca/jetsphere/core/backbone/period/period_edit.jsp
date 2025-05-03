<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.application.ApplicationSession" %>

<% ApplicationSession applications = ApplicationSession.getInstance ( request ); request.setAttribute ( "applications", applications.iterator ( true ) ); %>

<link rel="stylesheet" type="text/css" href="plugins/bootstrap-datepicker/bootstrap-datepicker3.min.css" media="all"/>

<html:form action="/period_edit" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="name"/></label>

<div class="col-md-6"><html:text property="name" styleId="name" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="application"/></label>

<div class="col-md-6"><html:select property="applicationId" styleId="applicationId" styleClass="form-control"><html:options collection="applications" property="id" labelProperty="name" /></html:select></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="code"/></label>

<div class="col-md-6"><html:text property="code" styleId="code" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="start.date"/></label>

<div class="col-md-6 input-group date"><html:text property="startDateAsString" styleId="startDate" styleClass="form-control" size="12" readonly="false"/><span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="end.date"/></label>

<div class="col-md-6 input-group date"><html:text property="endDateAsString" styleId="endDate" styleClass="form-control" size="12" readonly="false"/><span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="type"/></label>

<div class="col-md-6"><html:select property="typeId" styleClass="form-control"><html:option value=""><bean:message key="please.select"/></html:option><html:option value="1"><bean:message key="period.type.school.calendar"/></html:option><html:option value="2"><bean:message key="period.type.grocery.box"/></html:option><html:option value="3"><bean:message key="period.type.company"/></html:option><html:option value="4"><bean:message key="period.type.large.menu"/></html:option></html:select></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="notes"/></label>

<div class="col-md-6"><html:textarea property="notes" styleId="notes" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="closed"/></label>

<div class="col-md-6"><div class="checkbox">

<label class="form-checkbox form-normal form-primary form-text"><html:checkbox property="closed"/></label><input type="hidden" name="closed" value="false"/>

</div></div>

</div>

</div>

<jsp:include page="period_edit_script.jsp" flush="true"/>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>
