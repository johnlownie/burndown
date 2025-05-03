<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.system.log.Log" %>
<%@ page import="ca.jetsphere.core.tier1.system.log.LogSession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<div id="page-content"><div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.logs"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/log_query" method="post" styleId="queryForm">

<div class="panel-body">

<% LogSession logs = LogSession.getInstance ( request ); new TableWriter ( logs, Log.captions(), Log.fields() ).print ( out, request ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>
