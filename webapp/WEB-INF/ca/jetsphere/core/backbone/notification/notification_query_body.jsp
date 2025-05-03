<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.notification.Notification" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.notification.NotificationSession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Notification"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.notifications"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<div class="panel-body">

<html:form action="/notification_query" method="post" styleId="queryForm">

</html:form>

<html:form action="/notification_edit" method="post" styleId="tableForm">

<% NotificationSession notifications = NotificationSession.getInstance ( request ); new TableWriter ( notifications, Notification.captions(), Notification.fields() ).print ( out, request, true ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>