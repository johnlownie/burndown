<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.role.Role" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role.RoleSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role.common.Type" %>
<%@ page import="java.util.List" %>

<% List types = Type.getTypes ( request ); request.setAttribute ( "types", types ); %>
<% Role role = RoleSession.getSelected ( request ); %>

<html:form action="/role_edit" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="name"/></label>

<div class="col-md-6"><html:text property="name" styleId="name" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="alias"/></label>

<div class="col-md-6"><html:text property="alias" styleId="alias" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="type"/></label>

<div class="col-md-6"><html:select property="type" styleId="type" styleClass="form-control"><html:options collection="types" property="value" labelProperty="label"/></html:select></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="notes"/></label>

<div class="col-md-6"><html:textarea property="notes" styleId="notes" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="state"/></label>

<div class="col-md-6"><div class="checkbox">

<label class="form-checkbox form-normal form-primary form-text"><html:checkbox property="readOnly" styleId="active"/> <bean:message key="read.only"/></label><input type="hidden" name="readOnly" value="false"/>

</div></div>

</div>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>