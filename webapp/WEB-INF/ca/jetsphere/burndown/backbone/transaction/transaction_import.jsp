<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/WEB-INF/templates/default/site.jsp" flush="true">

<tiles:put name="style" value="/WEB-INF/ca/jetsphere/burndown/backbone/transaction/transaction_import_style.jsp"/>

<tiles:put name="head" value="/WEB-INF/ca/jetsphere/burndown/backbone/transaction/transaction_import_head.jsp"/>

<tiles:put name="body" value="/WEB-INF/ca/jetsphere/burndown/backbone/transaction/transaction_import_body.jsp"/>

<tiles:put name="script" value="/WEB-INF/ca/jetsphere/burndown/backbone/transaction/transaction_import_script.jsp"/>

<tiles:put name="foot" value="/WEB-INF/ca/jetsphere/burndown/backbone/transaction/transaction_import_foot.jsp"/>

</tiles:insert>
