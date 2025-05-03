<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/WEB-INF/templates/default/site.jsp" flush="true">

<tiles:put name="body" value="/WEB-INF/ca/jetsphere/core/system/security/lockout_body.jsp"/>

</tiles:insert>
