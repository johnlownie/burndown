<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div class="panel month">

<div class="panel-heading">
    
<h3 id="by-month-title" class="panel-title">
    
<span><bean:message key="dashboard.by.month"/></span>

<div class="button-wrapper pull-right"><button id="resetMth" class="btn btn-primary"><bean:message key="reset"/></button></div>

</h3>

</div>

<div class="panel-body"><div id="by-month"></div></div>

</div>
