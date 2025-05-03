<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.company.Company" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>

<% Company company = CompanySession.getSelected ( request ); %>

<!DOCTYPE html>

<html:html locale="true">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

<title><bean:message key="login"/> | <%= company.getName() %> <bean:message key="application.version.name"/></title>

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Tangerine:400"/>
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"/>
<link rel="stylesheet" type="text/css" href="styles/jquery/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="plugins/font-awesome/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="plugins/bootstrap-validator/formValidation.min.css"/>
<link rel="stylesheet" type="text/css" href="styles/templates/default/css/default.min.css"/>
<link rel="stylesheet" type="text/css" href="plugins/reject/css/jquery.reject.css"/>
<tiles:insert attribute="style" ignore="true" flush="false"/>
<tiles:insert attribute="head" ignore="true"/>
</head>

<body class="nifty-ready">

<div id="container" class="cls-container">

<noscript><div style="position: fixed; top: 0px; left: 0px; z-index: 3000; height: 100%; width: 100%; background-color:rgba(0, 0, 0, 0.8);"><p style="margin: 200px 100px;"><bean:message key="error.javascript.not.enabled"/></p></div></noscript>

<div id="bg-overlay" class="bg-img img-default"></div>

<div class="cls-header cls-header-lg"><div class="cls-brand">

<a class="box-inline" href="<%= company.getUrl() %>" target="_blank">

<span class="brand-title"><%= company.getName() %> <span class="text-thin"><bean:message key="login"/></span></span>

</a>

</div></div>

<tiles:insert attribute="body" ignore="true"/>

</div>

<script type="text/javascript" src="js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/jquery/bootstrap.min.js"></script>
<script type="text/javascript" src="styles/templates/default/js/default.min.js"></script>
<script type="text/javascript" src="plugins/reject/js/jquery.reject.js"></script>
<script type="text/javascript" src="plugins/bootstrap-validator/formValidation.min.js"></script>
<script type="text/javascript" src="plugins/bootstrap-validator/framework/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery/core/notification/notification.js"></script>
<tiles:insert attribute="foot" ignore="true"/>
<tiles:insert attribute="script" ignore="true" flush="false"/>
<script type="text/javascript">
    $(document).ready(function () {
        jQuery.ajaxSetup({cache: false});
    });
</script>
</body>

</html:html>
