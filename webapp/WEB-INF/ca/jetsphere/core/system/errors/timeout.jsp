<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/WEB-INF/templates/default/plain.jsp" flush="true">

<tiles:put name="title" value="Session Timeout" type="String"/>

<tiles:put name="body" value="/WEB-INF/ca/jetsphere/core/system/errors/timeout_body.jsp"/>

</tiles:insert>
