<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/action_edit" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="name"/></label>

<div class="col-md-6"><html:text property="name" styleId="name" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="href"/></label>

<div class="col-md-6"><html:text property="href" styleId="href" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="tooltip"/></label>

<div class="col-md-6"><html:text property="toolTip" styleId="toolTip" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="notes"/></label>

<div class="col-md-6"><html:textarea property="notes" styleId="notes" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="state"/></label>

<div class="col-md-6"><div class="checkbox">

<label class="form-checkbox form-normal form-primary form-text"><html:checkbox property="public" styleId="active"/> <bean:message key="public"/></label><input type="hidden" name="public" value="false"/>

</div></div>

</div>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>

<jsp:include page="action_edit_script.jsp" flush="true"/>
