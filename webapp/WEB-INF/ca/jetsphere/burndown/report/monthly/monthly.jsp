<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/WEB-INF/templates/default/site.jsp" flush="true">

<tiles:put name="style" value="/WEB-INF/ca/jetsphere/burndown/report/monthly/monthly_style.jsp"/>

<tiles:put name="head" value="/WEB-INF/ca/jetsphere/burndown/report/monthly/monthly_head.jsp"/>

<tiles:put name="body" value="/WEB-INF/ca/jetsphere/burndown/report/monthly/monthly_body.jsp"/>

<tiles:put name="script" value="/WEB-INF/ca/jetsphere/burndown/report/monthly/monthly_script.jsp"/>

<tiles:put name="foot" value="/WEB-INF/ca/jetsphere/burndown/report/monthly/monthly_foot.jsp"/>

</tiles:insert>
