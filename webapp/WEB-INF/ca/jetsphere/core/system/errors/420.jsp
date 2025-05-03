<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/WEB-INF/templates/default/site.jsp" flush="true">

<tiles:put name="title" value="Error 420" type="String"/>

<tiles:put name="body" value="/WEB-INF/ca/jetsphere/core/system/errors/420_body.jsp"/>

</tiles:insert>
