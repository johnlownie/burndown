<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="/category_edit" method="post" styleId="editForm" styleClass="form-horizontal">
<html:hidden property="parentId"/><html:hidden property="insertType"/>

<div class="panel-body">

<div class="form-group">

<label class="col-md-4 control-label"><bean:message key="name"/></label>

<div class="col-md-6"><html:text property="name" styleId="name" styleClass="form-control"/></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="category.included"/></label>

<div class="col-md-6"><div class="checkbox">

<label class="form-checkbox form-normal form-primary form-text"><html:checkbox property="included" styleId="active"/><input type="hidden" name="included" value="false"/>

</div></div>

</div><div class="form-group">

<label class="col-md-4 control-label"><bean:message key="category.fixed"/></label>

<div class="col-md-6"><div class="checkbox">

<label class="form-checkbox form-normal form-primary form-text"><html:checkbox property="fixed" styleId="active"/><input type="hidden" name="fixed" value="false"/>

</div></div>

</div>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/modal.jsp" flush="true" />

</html:form>