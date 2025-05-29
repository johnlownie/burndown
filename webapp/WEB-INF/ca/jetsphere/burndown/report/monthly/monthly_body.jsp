<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="ca.jetsphere.burndown.tier1.report.monthly.MonthlyWriter" %>
<%@ page import="ca.jetsphere.core.bolt.rs.ResultSetBoltMap" %>
<%@ page import="ca.jetsphere.core.bolt.rs.ResultSetBolt" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodYard" %>

<% PeriodSession  periods  = PeriodSession .getInstance ( request ); request.setAttribute ( "periods" , periods .iterator ( PeriodYard.BY_ORDINAL ) ); %>
<% ResultSetBoltMap rsbm = ( ResultSetBoltMap ) request.getAttribute ( ResultSetBolt.key() ); %>

<div id="page-content">

<div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="report.monthly"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/report_monthly" method="post" styleId="queryForm">

<div class="panel-body">

<div class="col-sm-3"><div class="form-group"><label class="control-label"><bean:message key="period"/></label><html:select property="periodId" styleId="periodId" styleClass="form-control"><html:options collection="periods" property="id" labelProperty="name" /></html:select></div></div>

<div class="col-sm-3"><div class="form-group"><label class="control-label"><bean:message key="type"/></label><html:select property="typeId" styleId="typeId" styleClass="form-control"><html:option value="-1"><bean:message key="transaction.type.all"/></html:option><html:option value="1"><bean:message key="transaction.type.debit"/></html:option><html:option value="2"><bean:message key="transaction.type.credit"/></html:option></html:select></div></div>

<div class="col-sm-3"><div class="form-group"><label class="control-label"><bean:message key="start.date"/></label><div class="input-group date"><html:text property="startDateAsString" styleId="startDate" styleClass="form-control" size="12" readonly="false"/><span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span></div></div></div>

<div class="col-sm-3"><div class="form-group"><label class="control-label"><bean:message key="end.date"/></label><div class="input-group date"><html:text property="endDateAsString" styleId="endDate" styleClass="form-control" size="12" readonly="false"/><span class="input-group-addon"><i class="fa fa-calendar fa-lg"></i></span></div></div></div>

</div>

<div class="panel-footer text-right">

<button type="button" class="btn btn-info btn-labeled fa fa-print text-uppercase btnPrint" value="true" name="print"<% if ( rsbm == null ) { %> disabled="disabled" <% } %>><bean:message key="print"/></button>

<button type="submit" class="btn btn-primary btn-labeled fa fa-table text-uppercase" value="true" name="report"><bean:message key="report"/></button>

</div>

</html:form>

</div>

<logic:notEmpty name="result_set">

<div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="report.monthly"/></h3></div>

<div class="panel-body">

<%

if ( rsbm != null && rsbm.getCaptions() != null && rsbm.getFields() != null )

{ new MonthlyWriter ( rsbm, rsbm.getCaptions(), rsbm.getFields(), "report.monthly" ).print ( out, request ); }

%>

</div></div>

</logic:notEmpty>

</div>