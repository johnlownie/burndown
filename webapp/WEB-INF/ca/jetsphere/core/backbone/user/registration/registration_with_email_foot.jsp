<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("#fullname").attr("placeholder", "<bean:message key="your.first.and.last.name"/>");
        $("#fullname").focus();
        $("#username").attr("placeholder", "<bean:message key="email.address"/>");
        $("#password").attr("placeholder", "<bean:message key="password"/>");
        $("#confirmPassword").attr("placeholder", "<bean:message key="confirm.password"/>");
    });
</script>