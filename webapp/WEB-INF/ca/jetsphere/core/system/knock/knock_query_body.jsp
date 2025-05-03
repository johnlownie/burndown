<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.action.Action" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.action.ActionSession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>
<%@ page import="ca.jetsphere.core.tier1.system.knock.Knock" %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Knock"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.knocks"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/knock_query" method="post" styleId="queryForm"></html:form>

<html:form action="/knock_edit" method="post" styleId="tableForm">

<div class="panel-body">

<div class="table-responsive"><table class="table table-striped table-hover">

<thead><tr><th><bean:message key="action"/></th><th><bean:message key="key"/></th><th><bean:message key="value"/></th></tr></thead>

<tbody>

<logic:iterate id="knock" collection="<%= Knock.getMap() %>"><tr>

<td><button type="button" class="btn btn-sm btn-default add-tooltip editBtn" data-id="<bean:write name="knock" property="key"/>"><i class="fa fa-pencil"></i></button></td>

<td><bean:write name="knock" property="key"/></td>

<td><bean:write name="knock" property="value"/></td>

</tr></logic:iterate>

</tbody>

</table></div>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>