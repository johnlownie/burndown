<!DOCTYPE html>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<html:html locale="true">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

<title><tiles:insert attribute="title" ignore="true" flush="false"/></title>

<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Merriweather:400,400italic">
<link rel="stylesheet" type="text/css" href="styles/jquery/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="styles/templates/default/css/default.min.css">
<link rel="stylesheet" type="text/css" href="styles/templates/themes/theme-dark.min.css">
</head>

<body>

<div id="container" class="cls-container">

<div class="cls-header"><div class="cls-brand">

<a class="box-inline" href="index.jsp"><span class="brand-title">&nbsp;<span class="text-thin">&nbsp;</span></span></a>

</div></div>

<div class="cls-content">

<tiles:insert attribute="body" ignore="true"/>

<br>

<div class="pad-top"><a class="btn-link" href="logout.do">Back to Login</a></div>

</div>


</div>

</div>

<script type="text/javascript" src="js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/jquery/bootstrap.min.js"></script>
<script type="text/javascript" src="styles/templates/default/js/default.min.js"></script>

</body>

</html:html>
