<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/WEB-INF/templates/default/login.jsp" flush="true">

<tiles:put name="style" value="/WEB-INF/ca/jetsphere/core/backbone/user/registration/registration_with_email_style.jsp"/>

<tiles:put name="head" value="/WEB-INF/ca/jetsphere/core/backbone/user/registration/registration_with_email_head.jsp"/>

<tiles:put name="body" value="/WEB-INF/ca/jetsphere/core/backbone/user/registration/registration_with_email_body.jsp"/>

<tiles:put name="script" value="/WEB-INF/ca/jetsphere/core/backbone/user/registration/registration_with_email_script.jsp"/>

<tiles:put name="foot" value="/WEB-INF/ca/jetsphere/core/backbone/user/registration/registration_with_email_foot.jsp"/>

</tiles:insert>
