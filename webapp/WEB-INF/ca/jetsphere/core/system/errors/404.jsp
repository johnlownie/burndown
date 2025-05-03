<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.user.User" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.user.UserYard" %>

<% User whoAmI = UserYard.whoAmI ( request ); if ( whoAmI != null && whoAmI.isValid() ) { %>

<tiles:insert page="/WEB-INF/templates/default/site.jsp" flush="true">

<tiles:put name="title" value="Error 404" type="String"/>

<tiles:put name="body" value="/WEB-INF/ca/jetsphere/core/system/errors/404_body_panel.jsp"/>

</tiles:insert>

<% } else { %>

<tiles:insert page="/WEB-INF/templates/default/plain.jsp" flush="true">

<tiles:put name="title" value="Error 404" type="String"/>

<tiles:put name="body" value="/WEB-INF/ca/jetsphere/core/system/errors/404_body.jsp"/>

</tiles:insert>

<% } %>
