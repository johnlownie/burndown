<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div id="category-panel" class="panel">
    
<div class="panel-heading">
    
<h3 id="by-category-title" class="panel-title">
    
<span><bean:message key="mgr.dashboard.by.category"/></span>

<div class="button-wrapper pull-right"><button id="resetCat" class="btn btn-primary"><bean:message key="reset"/></button></div>

</h3>

</div>

<div class="panel-body"><div id="by-category"></div></div>

</div>
