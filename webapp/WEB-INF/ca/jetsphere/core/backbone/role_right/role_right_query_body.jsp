<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role.RoleSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role_right.RoleRight" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role_right.RoleRightSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role_right.writer.RoleRightWriter" %>

<% CompanySession     companies    = CompanySession    .getInstance ( request ); request.setAttribute ( "companies"   , companies   .iterator ( true ) ); %>
<% RoleSession        roles        = RoleSession       .getInstance ( request ); request.setAttribute ( "roles"       , roles       .iterator ( true ) ); %>

<div id="page-content"><div class="panel" data-title="<bean:message key="add.edit.bolt" arg0="Role Right"/>">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.role.rights"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<div class="panel-body">

<html:form action="/role_right_query" method="post" styleId="queryForm">

<div class="row">

<div class="col-sm-6"><div class="form-group"><label class="control-label"><bean:message key="role"/></label><html:select property="roleId" styleId="roleId" styleClass="form-control"><html:option value=""><bean:message key="please.select"/></html:option><html:options collection="roles" property="id" labelProperty="name" /></html:select></div></div>

</div>

</html:form>

<html:form action="/role_right_edit" method="post" styleId="treeForm">

<% RoleRightSession roleRights = RoleRightSession.getInstance ( request ); new RoleRightWriter ( roleRights, RoleRight.captions(), RoleRight.fields() ).print ( out, request, true ); %>

</html:form>

</div><div class="panel-footer text-right">

<button type="button" class="btn btn-primary btn-labeled fa fa-expand text-uppercase expandBtn"><bean:message key="expand.all"/></button>

<button type="button" class="btn btn-primary btn-labeled fa fa-compress text-uppercase collapseBtn"><bean:message key="collapse.all"/></button>

</div>

</div></div>
