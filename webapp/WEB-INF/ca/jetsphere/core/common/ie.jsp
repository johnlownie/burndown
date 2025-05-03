<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.tier2.common.ActionType" %>

<div class="panel-footer text-right">

<% if ( ! ActionType.isEdit ( request ) ) { %>

<html:submit styleClass="btn btn-success text-uppercase" property="insert"><bean:message key="save"/></html:submit>

<% } else { %>

<html:submit property="update" styleClass="btn btn-success text-uppercase"><bean:message key="save"/></html:submit>

<html:submit property="delete" styleClass="btn btn-danger text-uppercase" styleId="delete"><bean:message key="delete"/></html:submit>

<% } %>

<html:submit styleClass="btn btn-default text-uppercase" property="back"><bean:message key="cancel"/></html:submit>

</div>

<div id="delete-dialog" class="dialog" style="display: none;" title="<bean:message key="delete.confirmation"/>"><bean:message key="delete.confirmation.text"/></div>
