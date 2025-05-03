<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.bu.Bu" %>
<%@ page import="ca.jetsphere.core.tier2.common.ActionType" %>

<div class="panel-heading"><h3 class="panel-title"><%= ActionType.title ( request, Bu.key() ) %></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<div class="row">

<html:form action="/bu_edit" method="post">

<html:hidden property="parentId" value='<%= request.getParameter ( "parent" ) %>'/>

<div class="form-group">

<label for="name" class="control-label"><bean:message key="name"/></label><html:text property="name" styleId="name" styleClass="form-control"/>

</div><div class="form-group">

<label for="address" class="control-label"><bean:message key="address"/></label><html:text property="address" styleId="address" styleClass="form-control"/>

</div><div class="form-group">

<label for="city" class="control-label"><bean:message key="city"/></label><html:text property="city" styleId="city" styleClass="form-control"/>

</div><div class="form-group">

<label for="province" class="control-label"><bean:message key="province"/></label><html:text property="province" styleId="province" styleClass="form-control"/>

</div><div class="form-group">

<label for="postalCode" class="control-label"><bean:message key="postal.code"/></label><html:text property="postalCode" styleId="postalCode" styleClass="form-control"/>

</div><div class="form-group">

<label for="phone" class="control-label"><bean:message key="phone"/></label><html:text property="phone" styleId="phone" styleClass="form-control"/>

</div><div class="form-group">

<label for="fax" class="control-label"><bean:message key="fax"/></label><html:text property="fax" styleId="fax" styleClass="form-control"/>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/ie.jsp" flush="true" />

</html:form>

</div>
