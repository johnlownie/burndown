<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="ca.jetsphere.core.common.DockYard" %>

<script type="text/javascript">
    $(document).ready(function () {
        $("#table").on("click", ".testBtn", function(event) {
            event.preventDefault();
            onTest({
                title     : 'Change Meal Date',
                dataString: "test=true&csrf=" + $(this).attr("data-id"),
                size      : $(".panel").attr("modal-size") == "" ? "modal60" : $(".panel").attr("modal-size")
            });
        });
    });

    function onTest (data) {
        var size = data.size == undefined || data.size == "" ? "modal70" : data.size;

        $.ajax({
            url: $("#tableForm").attr("action"),
            type: "GET",
            cache: false,
            data: data.dataString,
            dataType: "html",
            timeout: 10000
        })
        .success(function(content) {
            var box = bootbox.dialog({
                title: data.title,
                message: content,
                className: size,
                show: false
            })
            .on("shown.bs.modal", function() {
                $("#editForm").show().formValidation("resetForm");
                $("#editForm :input:visible:enabled:first").focus();
            })
            .on("hide.bs.modal", function(e) {
            })
            .modal("show");
        })
        .error(function(x, t, m) {
            notifyError();
        });
    }
</script>