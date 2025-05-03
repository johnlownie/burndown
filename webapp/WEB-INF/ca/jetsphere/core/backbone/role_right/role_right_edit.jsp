<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.action.ActionSession" %>

<% ActionSession actions = ActionSession.getInstance ( request ); request.setAttribute ( "actions", actions.iterator ( true ) ); %>

<html:form action="/role_right_edit" method="post" styleId="editForm" styleClass="form-horizontal">
<html:hidden property="parentId"/><html:hidden property="insertType"/>

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="name"/></label>

<div  class="col-md-6"><html:text property="name" styleId="name" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="action"/></label>

<div  class="col-md-6"><html:select property="actionId" styleId="actionId" styleClass="form-control"><html:option value="-1"><bean:message key="please.select"/></html:option><html:options collection="actions" property="id" labelProperty="name" /></html:select></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="state"/></label>

<div class="col-md-6"><div class="checkbox">

<label class="form-checkbox form-normal form-primary form-text"><html:checkbox property="hidden" styleId="hidden"/> <bean:message key="hidden"/></label><input type="hidden" name="active" value="false"/>

</div></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="notes"/></label>

<div  class="col-md-6"><html:text property="notes" styleId="notes" styleClass="form-control"/></div>

</div>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>
