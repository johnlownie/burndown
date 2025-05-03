<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/WEB-INF/templates/default/site.jsp" flush="true">

<tiles:put name="style" value="/WEB-INF/ca/jetsphere/burndown/backbone/category/category_query_style.jsp"/>

<tiles:put name="body" value="/WEB-INF/ca/jetsphere/burndown/backbone/category/category_query_body.jsp"/>

<tiles:put name="script" value="/WEB-INF/ca/jetsphere/burndown/backbone/category/category_query_script.jsp"/>

</tiles:insert>
