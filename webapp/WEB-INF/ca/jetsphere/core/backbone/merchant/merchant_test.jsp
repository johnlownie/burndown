<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/merchant_test" method="post" styleId="editForm" styleClass="form-horizontal">

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="company"/></label>

<div class="col-md-6"><html:text property="name" styleId="name" styleClass="form-control"/></div>

</div><div class="form-group">

<div class="col-md-6 col-md-offset-4"><button type="button" id="run" class="btn btn-success"><bean:message key="run"/></button></div>

</div><div class="form-group">

<div class="col-md-6 col-md-offset-4"><textarea id="result" class="form-control" rows="12" readonly="readonly"></textarea></div>

</div>

</div>

<div class="clearfix modal-footer text-right">

<button type="button" class="btn btn-default" data-dismiss="modal"><bean:message key="close"/></button>

</div>

<jsp:include page="merchant_test_script.jsp" flush="true"/>

</html:form>