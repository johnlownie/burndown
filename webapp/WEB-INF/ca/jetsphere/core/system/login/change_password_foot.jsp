<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("#confirmPassword").attr("placeholder", "<bean:message key="confirm.password"/>");
        $("#password").attr("placeholder", "<bean:message key="password"/>").focus();
    });
</script>