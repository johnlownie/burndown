<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.user.User" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.user.UserYard" %>
<%@ page import="ca.jetsphere.core.common.DockYard" %>
<%@ page import="ca.jetsphere.core.common.CalendarYard" %>

<% User whoAmI = UserYard.whoAmI ( request ); String[] profile =  UserYard.getProfile ( request );%>
<% String address = ! DockYard.isWhiteSpace ( whoAmI.getCompanyMember().getCity() ) ? whoAmI.getCompanyMember().getCity() + ", " + whoAmI.getCompanyMember().getProvince() : ""; %>

<div class="nano has-scrollbar">

<div class="nano-content" tabindex="0" style="right: -17px;">

<div class="text-center pad-all">

<div class="pad-ver"><img class="img-lg img-border img-circle img-user" src="<%= profile[0] %>" alt="Profile Picture"/></div>

<h4 class="text-lg text-overflow mar-no"><%= whoAmI.getCompanyMember().getName() %></h4>

<p class="text-sm"><%= whoAmI.getCompanyMember().getEmail() %></p>

</div>

<hr/>

<ul class="list-group bg-trans">

<% if ( ! DockYard.isWhiteSpace ( address ) ) { %>
<li class="list-group-item list-item-sm"><i class="fa fa-home fa-fw"></i><%= whoAmI.getCompanyMember().getCity() + ", " + whoAmI.getCompanyMember().getProvince() %></li>
<% } %>

<li class="list-group-item list-item-sm"><bean:message key="member.since" arg0='<%= CalendarYard.getDateTimeFormat ( whoAmI.getCreated(), "MMM dd, yyyy" ) %>'/></li>

</ul>

<div class="nano-pane" style="display: none;"><div class="nano-slider" style="height: 20px; transform: translate(0px, 0px);"></div></div>

</div>

</div>