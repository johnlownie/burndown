<script type="text/javascript">
    $(document).ready(function () {
        $("#periodId").on("change", function(event) {
            event.preventDefault();
            var dataString = "fetch=true&periodId=" + $("#periodId").val();
            $.ajax({
                url: $("#queryForm").attr("action"),
                type: "GET",
                data: dataString,
                dataType: "json",
                timeout: 14000
            })
            .success(function(response) {
                if (response.startDate) {
                    $('#startDate').val(response.startDate);
                    $('#endDate').val(response.endDate);
                    $("#table").dataTable().api().ajax.reload();
                }
            })
            .error(function(x, t, m) {
                notifyError(".modal-content");
            });
        });

        $('#startDate').attr('placeholder', '<bean:message key="please.select"/>');
        $('#endDate').attr('placeholder', '<bean:message key="please.select"/>');

        $('#duplicates').click(function(e) {
            $('#toggle').val('1');
            $("#table").dataTable().api().ajax.reload();
        });
    });
</script>