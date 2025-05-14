<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.company.Company" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.user.User" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.user.UserYard" %>

<% Company company = CompanySession.getSelected ( request ); User whoAmI = UserYard.whoAmI ( request ); String[] profile = UserYard.getProfile ( request );%>

<header id="navbar">

<div id="navbar-container" class="boxed">

<div class="navbar-header">

<a href="<%= company.getUrl() %>" class="navbar-brand" target="_blank">

<img src="styles/templates/default/images/logo.png" alt="" class="brand-icon">

<div class="brand-title"><i class="psi-home"></i><span class="brand-text"><%= company.getShort() %></span></div>

</a>

</div>

<div class="navbar-content clearfix">

<ul class="nav navbar-top-links pull-left"><li class="tgl-menu-btn"><a class="mainnav-toggle" href="#"><i class="fa fa-navicon fa-lg"></i></a></li></ul>

<% if ( whoAmI != null && whoAmI.isValid() ) { %>

<ul class="nav navbar-top-links pull-right">

<li id="dropdown-user" class="dropdown">

<a href="#" data-toggle="dropdown" class="dropdown-toggle text-right"><span class="pull-right"><img class="img-circle img-user media-object" src="<%= profile[0] %>" alt="Profile Picture"/></span><div class="username hidden-xs"><%= profile[1] %><span class="caret"></span></div></a>

<div class="dropdown-menu dropdown-menu-md dropdown-menu-right with-arrow panel-default">

<ul class="head-list"><li><html:link action="/user_profile"><i class="fa fa-user fa-fw fa-lg"></i><bean:message key="profile"/></html:link></li></ul>

<div class="pad-all text-right"><html:link action="/logout" styleClass="btn btn-success"><i class="fa fa-sign-out fa-fw"></i><bean:message key="logout"/></html:link></div>

</div>

</li>

</ul>

<% } %>

</div>

</div>

</header>