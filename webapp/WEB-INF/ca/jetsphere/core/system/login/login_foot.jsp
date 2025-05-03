<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("#username").attr("placeholder", "<bean:message key="email.address"/>");
        $("#password").attr("placeholder", "<bean:message key="password"/>");
        $("#username").focus();

        $("#password").on("focus", function() {
            $("#messages").hide();
        });
    });
</script>