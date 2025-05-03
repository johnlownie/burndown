<script type="text/javascript">
    $(document).ready(function() {
        $("#companyId").change(function(){
            var $label = $("#applicationId").parent().siblings().find(".control-label").end();
            $label.isLoading({
                text:       "Loading",
                position:   "inside"
            });

            var dataString = "fetch=true&companyId=" + $("#companyId").val();
            $.ajax({
                url: $("#queryForm").attr("action"),
                type: "GET",
                data: dataString,
                dataType: "html",
                timeout: 14000
            })
            .success(function(content) {
                $("#applicationId").html(content);
            })
            .error(function(x, t, m) {
                notifyError(".modal-content");
            })
            .complete(function() {
                $label.isLoading("hide").html("<bean:message key="application"/>");
            });
        });
    });
</script>