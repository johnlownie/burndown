<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.application.ApplicationSession" %>

<% ApplicationSession applications = ApplicationSession.getInstance ( request ); request.setAttribute ( "applications", applications.iterator ( true ) ); request.setAttribute ( "toApplications", applications.iterator ( true ) ); %>

<div id="page-content"><div class="panel">

<html:form action="/category_copy" method="post" styleId="queryForm">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="copy.categories"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<div class="panel-body">
    
<div class="row">
        
<div class="col-sm-6">
    
<div class="col-sm-6"><div class="form-group"><label class="control-label"><bean:message key="from.application"/></label><html:select property="applicationId" styleId="applicationId" styleClass="form-control"><html:option value=""><bean:message key="please.select"/></html:option><html:options collection="applications" property="id" labelProperty="name" /></html:select></div></div>
    
</div><div class="col-sm-6">
    
<div class="col-sm-6"><div class="form-group"><label class="control-label"><bean:message key="to.application"/></label><html:select property="roleId" styleId="roleId" styleClass="form-control"><html:option value=""><bean:message key="please.select"/></html:option><html:options collection="toApplications" property="id" labelProperty="name" /></html:select></div></div>
        
</div>
    
</div>
    
</div><div class="panel-footer text-right">

<div class="panel-footer text-right"><button type="submit" class="btn btn-primary btn-labeled fa fa-copy text-uppercase" value="insert" name="insert"><bean:message key="copy"/></button></div>

</div>

</html:form>

</div></div>