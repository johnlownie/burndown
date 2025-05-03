<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("#username").attr("placeholder", "<bean:message key="email"/>");
        $("#username").focus();

    });
</script>