<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodYard" %>

<% PeriodSession  periods  = PeriodSession .getInstance ( request ); request.setAttribute ( "periods" , periods .iterator ( PeriodYard.BY_ORDINAL ) ); %>

<div id="page-title">

<h1 class="page-header text-overflow"><bean:message key="dashboard"/></h1>

<div class="searchbx">

<html:form action="/mgr_dashboard" method="post" styleId="queryForm" styleClass="form-horizontal">

<div class="input-group custom-search-form" style="width: 100%;">

<div class="col-md-4"><html:select property="periodId" styleId="periodId" styleClass="form-control"><html:options collection="periods" property="id" labelProperty="name" /></html:select></div>

</div>

</html:form>

</div>

</div>

<div id="page-content">

<div class="row">

<div class="col-sm-4">

<jsp:include page="dashboard_by_category.jsp" flush="true"/>

</div><div class="col-sm-8">
    
<jsp:include page="dashboard_by_month.jsp" flush="true"/>

</div></div>

</div>
