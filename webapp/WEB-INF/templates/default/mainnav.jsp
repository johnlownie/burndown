<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier2.tabs.TabBarWriter" %>

<nav id="mainnav-container">

<div id="mainnav">

<div id="mainnav-shortcut"><ul class="brand-title"><li class="pad-ver"><span class="menu-title">&nbsp;</span></li></ul></div>

<div id="mainnav-menu-wrap"><div class="nano"><div class="nano-content"><% TabBarWriter.nifty ( out, request ); %></div></div></div>

</div>

</nav>
