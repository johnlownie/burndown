<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div id="page-content"><div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="log"/></h3></div>

<html:form action="/log_edit" method="post">

<div class="panel-body">

<p><html:textarea property="text" rows="50" cols="200"/></p>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/ie.jsp" flush="true" />

</html:form>

</div></div>
