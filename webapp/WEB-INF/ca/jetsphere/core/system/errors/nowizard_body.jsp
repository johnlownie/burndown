<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.company.Company" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>

<% Company company = CompanySession.getSelected ( request ); %>

<div id="page-content"><div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="no.wizard.head"/></h3></div>

<div class="panel-body"><p><bean:message key="no.wizard.text" arg0="<%= company.getName() %>"/></p>

</div>

</div></div>