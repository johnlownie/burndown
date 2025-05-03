<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("#fullname").attr("placeholder", "<bean:message key="your.first.and.last.name"/>");
        $("#fullname").focus();
        $("#username").attr("placeholder", "<bean:message key="email.address"/>");
        $("#phone").attr("placeholder", "<bean:message key="your.phone.number"/>");
        $("#confirmUsername").attr("placeholder", "<bean:message key="confirm.email.address"/>");
        $("#password").attr("placeholder", "<bean:message key="password"/>");
        $("#confirmPassword").attr("placeholder", "<bean:message key="confirm.password"/>");
        $("#username").keypress(function( e ) { if (e.which === 32) return false; });
    });
</script>