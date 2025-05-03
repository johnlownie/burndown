<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.application.ApplicationSession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.Company" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.notification.NotificationYard" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.user.User" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.user.UserYard" %>

<% Company company = CompanySession.getSelected ( request ); User whoAmI = UserYard.whoAmI ( request );%>
<% ApplicationSession applications = ApplicationSession.getInstance ( request ); request.setAttribute ( "applications", applications.list ( true ) ); %>

<!DOCTYPE html>

<html:html locale="true">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

<title><%= company.getName() %> <bean:message key="application.version.name"/> (<%= UserYard.getUsername ( request ) %>)</title>

<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" media="all"/>
<link rel="stylesheet" type="text/css" href="styles/jquery/bootstrap.min.css" media="all"/>
<link rel="stylesheet" type="text/css" href="plugins/font-awesome/css/font-awesome.min.css" media="all"/>
<link rel="stylesheet" type="text/css" href="plugins/switchery/switchery.min.css" media="all"/>
<link rel="stylesheet" type="text/css" href="plugins/pace/pace.min.css" media="all"/>
<link rel="stylesheet" type="text/css" href="plugins/bootstrap-validator/formValidation.min.css"/>
<link rel="stylesheet" type="text/css" href="styles/templates/default/css/default.min.css" media="all"/>
<link rel="stylesheet" type="text/css" href="styles/templates/themes/theme-dark.min.css" media="all"/>
<tiles:insert attribute="style" ignore="true" flush="false"/>
<tiles:insert attribute="head" ignore="true"/>
</head>

<body>

<div id="container" class="effect mainnav-lg navbar-fixed">

<jsp:include page="navbar.jsp"/>

<div class="boxed">

<div id="content-container"><jsp:include page="page-alert.jsp" flush="true"/><tiles:insert attribute="body" ignore="true"/></div>

<jsp:include page="mainnav.jsp"/>

<aside id="aside-container"><div id="aside"><tiles:insert attribute="aside" ignore="true"/></div></aside>

</div>

<jsp:include page="footer.jsp"/>

<button id="scroll-top" class="btn"><i class="fa fa-chevron-up"></i></button>

</div>

<script type="text/javascript" src="js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery/bootstrap.min.js"></script>
<script type="text/javascript" src="plugins/bootbox/bootbox.min.js"></script>
<script type="text/javascript" src="plugins/bootstrap-select/bootstrap-select.min.js"></script>
<script type="text/javascript" src="plugins/switchery/switchery.min.js"></script>
<script type="text/javascript" src="plugins/pace/pace.min.js"></script>
<script type="text/javascript" src="plugins/cookie/js.cookie.min.js"></script>
<script type="text/javascript" src="plugins/bootstrap-validator/formValidation.min.js"></script>
<script type="text/javascript" src="plugins/bootstrap-validator/framework/bootstrap.min.js"></script>
<script type="text/javascript" src="styles/templates/default/js/default.min.js"></script>
<script type="text/javascript" src="js/jquery/core/notification/notification.js"></script>
<tiles:insert attribute="script" ignore="true" flush="false"/>
<script type="text/javascript">
    $(document).ready(function () {
        jQuery.ajaxSetup({cache: false}); $('[data-toggle="tooltip"]').tooltip();
    });

    $(window).on("load", function() {
        var fvisit  = setTimeout(function(){
            <%= NotificationYard.get ( request, whoAmI.getId() )%>
            clearTimeout(fvisit);
        }, 3000);
    });
</script>
<tiles:insert attribute="foot" ignore="true"/>
<tiles:insert attribute="tour" ignore="true"/>
<tiles:insert attribute="json" ignore="true"/>
<jsp:include page="analytics.jsp" flush="true"/>

</body>

</html:html>
