<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.bu.BuSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.bu_member.BuMember" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.bu_member.BuMemberSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.period.PeriodYard" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.role.RoleSession" %>
<%@ page import="ca.jetsphere.core.tier2.table.TableWriter" %>

<% PeriodSession periods = PeriodSession.getInstance ( request ); request.setAttribute ( "periods", periods.iterator ( PeriodYard.BY_ORDINAL ) ); %>
<% BuSession     bus     = BuSession    .getInstance ( request ); request.setAttribute ( "bus"    , bus   .treerator      () ); %>
<% RoleSession   roles   = RoleSession  .getInstance ( request ); request.setAttribute ( "roles"  , roles .iterator ( true ) ); %>

<div id="page-content"><div class="panel">

<div class="panel-heading"><h3 class="panel-title"><bean:message key="show.bu.members"/></h3></div>

<html:messages id="error" message="true"><div class="text-danger text-center pad-ver"><bean:write name="error"/></div></html:messages>

<html:form action="/bu_member_query" method="post" styleId="queryForm">

<div class="panel-body">

<div class="row">

<div class="col-sm-4"><div class="form-group"><label class="control-label"><bean:message key="period"/></label><html:select property="periodId" styleClass="form-control" onchange="submit();"><html:options collection="periods" property="id" labelProperty="name" /></html:select></div></div>

<div class="col-sm-4"><div class="form-group"><label class="control-label"><bean:message key="business.unit"/></label><html:select property="buId" styleClass="form-control" onchange="submit();"><html:option value=""><bean:message key="all.bus"/></html:option><html:options collection="bus" property="id" labelProperty="treeName" /></html:select></div></div>

<div class="col-sm-4"><div class="form-group"><label class="control-label"><bean:message key="roles"/></label><html:select property="roleId" styleClass="form-control" onchange="submit();"><html:option value=""><bean:message key="all.roles"/></html:option><html:options collection="roles" property="id" labelProperty="name" /></html:select></div></div>

</div>

<% BuMemberSession buMembers = BuMemberSession.getInstance ( request ); new TableWriter ( buMembers, BuMember.captions(), BuMember.fields() ).print ( out, request ); %>

</div>

<jsp:include page="/WEB-INF/ca/jetsphere/core/common/query.jsp" flush="true" />

</html:form>

</div></div>