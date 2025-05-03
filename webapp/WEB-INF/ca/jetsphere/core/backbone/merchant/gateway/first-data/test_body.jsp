<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="ca.jetsphere.core.common.DockYard" %>
<%@ page import="java.util.List" %>

<% List items = ( List ) DockYard.getAttribute ( request, "test_response" ); %>

<div id="page-content"><div class="row">

<div class="col-lg-6"><div class="panel">

<html:form action="/merchant_test" method="post" styleId="queryForm">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="merchant.test.request"/></h3></div>

<div class="panel-body">

<div class="form-group">

<label for="copy" class="control-label"></label><html:textarea property="command" styleId="copy" styleClass="form-control" rows="20"/>

</div>

</div>

<div class="panel-footer text-right">

<button type="submit" class="btn btn-primary btn-labeled fa fa-check text-uppercase" value="vkey" name="vkey"><bean:message key="verify"/></button>

</div>

</html:form>

</div></div>

<div class="col-lg-6"><div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="merchant.test.results"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<div class="panel-body">

<div class="table-responsive"><table class="table table-striped">

<thead><tr><th>Key</th><th>Value</th></tr></thead><tbody>

<% if ( items != null ) { %>

<logic:iterate id="item" collection="<%= items.iterator() %>">

<tr><td><bean:write name="item" property="key"/></td><td><bean:write name="item" property="value"/></td></tr>

</logic:iterate>

<% }%>

</tbody></table></div>

</div>

</div></div>

</div></div>
