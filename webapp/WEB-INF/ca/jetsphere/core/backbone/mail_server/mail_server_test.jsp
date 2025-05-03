<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/WEB-INF/templates/default/site.jsp" flush="true">

<tiles:put name="style" value="/WEB-INF/ca/jetsphere/core/backbone/mail_server/mail_server_test_style.jsp"/>

<tiles:put name="body" value="/WEB-INF/ca/jetsphere/core/backbone/mail_server/mail_server_test_body.jsp"/>

<tiles:put name="script" value="/WEB-INF/ca/jetsphere/core/backbone/mail_server/mail_server_test_script.jsp"/>

</tiles:insert>
