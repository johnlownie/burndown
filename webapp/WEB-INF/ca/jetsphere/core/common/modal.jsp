<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="ca.jetsphere.core.common.DockYard" %>

<div class="clearfix modal-footer text-right">

<html:submit styleClass="btn btn-success text-uppercase" property="jupdate" styleId="jupdate"><bean:message key="save"/></html:submit>

<% if ( ! DockYard.isWhiteSpace ( request, "id" ) ) { %>

<html:submit property="delete" styleClass="btn btn-danger text-uppercase" styleId="delete"><bean:message key="delete"/></html:submit>

<% } %>

<button type="button" class="btn btn-default" data-dismiss="modal"><bean:message key="cancel"/></button>

</div>

<script type="text/javascript">$(document).ready(function() { initValidation(); });</script>
