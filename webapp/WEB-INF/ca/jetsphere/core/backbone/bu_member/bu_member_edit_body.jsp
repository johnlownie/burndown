<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.bu.BuSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.bu_member.BuMember" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company_member.CompanyMemberSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role.RoleSession" %>
<%@ page import="ca.jetsphere.core.tier2.common.ActionType" %>

<% BuSession bus = BuSession.getInstance ( request ); request.setAttribute ( "bus", bus.treerator() ); %>
<% CompanyMemberSession companyMembers = CompanyMemberSession.getInstance ( request ); request.setAttribute ( "companyMembers", companyMembers.iterator ( true ) ); %>
<% RoleSession roles = RoleSession.getInstance ( request ); request.setAttribute ( "roles", roles.iterator ( true ) );  %>

<div id="page-content"><div class="panel">

<div class="panel-heading"><h3 class="panel-title"><%= ActionType.title ( request, BuMember.key () ) %></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/bu_member_edit" method="post">

<div class="panel-body"><div class="row">

<div class="form-group">

<label for="buId" class="control-label"><bean:message key="business.unit"/></label>

<html:select property="buId" styleId="buId" styleClass="form-control selectpicker"><html:options collection="bus" property="id" labelProperty="name" /></html:select>

</div><div class="form-group">

<label for="companyMemberId" class="control-label"><bean:message key="company.member"/></label>

<html:select property="companyMemberId" styleId="companyMemberId" styleClass="form-control selectpicker"><html:options collection="companyMembers" property="id" labelProperty="name" /></html:select>

</div><div class="form-group">

<label for="roles" class="control-label"><bean:message key="roles"/></label>

<html:select property="roles" styleId="roles" styleClass="form-control chosenselect" multiple="multiple" titleKey="please.select"><html:options collection="roles" property="id" labelProperty="name" /></html:select>

</div>

</div></div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/ie.jsp" flush="true" />

</html:form>

</div></div>