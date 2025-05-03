<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/knock_edit" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="key"/></label>

<div class="col-md-6"><html:text property="key" styleId="key" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="value"/></label>

<div class="col-md-6"><html:text property="value" styleId="value" styleClass="form-control"/></div>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>
