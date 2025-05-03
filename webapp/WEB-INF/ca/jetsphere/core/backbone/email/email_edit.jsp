<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/email_edit" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="email.from"/></label>

<div class="col-md-6"><html:text property="from" styleId="from" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="email.to"/></label>

<div class="col-md-6"><html:text property="to" styleId="to" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="email.cc"/></label>

<div class="col-md-6"><html:text property="cc" styleId="cc" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="email.bcc"/></label>

<div class="col-md-6"><html:text property="bcc" styleId="bcc" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="email.subject"/></label>

<div class="col-md-6"><html:text property="subject" styleId="subject" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="email.message"/></label>

<div class="col-md-6"><html:text property="message" styleId="message" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="email.attachment"/></label>

<div class="col-md-6"><html:text property="attachment" styleId="attachment" styleClass="form-control"/></div>

</div>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>