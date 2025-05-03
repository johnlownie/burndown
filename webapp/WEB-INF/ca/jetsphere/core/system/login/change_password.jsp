<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/WEB-INF/templates/default/login.jsp" flush="true">

<tiles:put name="style" value="/WEB-INF/ca/jetsphere/core/system/login/change_password_style.jsp"/>

<tiles:put name="body" value="/WEB-INF/ca/jetsphere/core/system/login/change_password_body.jsp"/>

<tiles:put name="script" value="/WEB-INF/ca/jetsphere/core/system/login/change_password_script.jsp"/>

<tiles:put name="foot" value="/WEB-INF/ca/jetsphere/core/system/login/change_password_foot.jsp"/>

</tiles:insert>
