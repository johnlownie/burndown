<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div id="page-title"><h1 class="page-header text-overflow"><bean:message key="dashboard"/></h1></div>

<div id="page-content">

<jsp:include page="dashboard_students.jsp" flush="true"/>

<jsp:include page="dashboard_order.jsp" flush="true"/>

<jsp:include page="dashboard_order_entries.jsp" flush="true"/>

<jsp:include page="dashboard_calendar.jsp" flush="true"/>

</div>
