<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/WEB-INF/templates/default/site.jsp" flush="true">

<tiles:put name="style" value="/WEB-INF/ca/jetsphere/burndown/roles/eu/dashboard/dashboard_style.jsp"/>

<tiles:put name="head" value="/WEB-INF/ca/jetsphere/burndown/roles/eu/dashboard/dashboard_head.jsp"/>

<tiles:put name="body" value="/WEB-INF/ca/jetsphere/burndown/roles/eu/dashboard/dashboard_body.jsp"/>

<tiles:put name="script" value="/WEB-INF/ca/jetsphere/burndown/roles/eu/dashboard/dashboard_script.jsp"/>

<tiles:put name="foot" value="/WEB-INF/ca/jetsphere/burndown/roles/eu/dashboard/dashboard_foot.jsp"/>

</tiles:insert>
