<script type="text/javascript">
    $(document).ready(function () {
        $('#startDate').attr('placeholder', '<bean:message key="please.select"/>');
        $('#endDate').attr('placeholder', '<bean:message key="please.select"/>');

        $('#duplicates').click(function(e) {
            $('#toggle').val('1');
            $("#table").dataTable().api().ajax.reload();
        });
    });
</script>