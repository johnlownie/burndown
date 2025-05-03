<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div class="cls-content">

<div class="cls-content-sm panel">

<div class="panel-body">

<p><bean:message key="reminder.notification"/></p>

<p class="pad-btm"><bean:message key="reminder.notification.text"/></p>

</div>

</div>

<div class="pad-ver"><html:link action="/login" styleClass="btn-link mar-rgt"><bean:message key="back.to.login"/></html:link></div>

</div>
