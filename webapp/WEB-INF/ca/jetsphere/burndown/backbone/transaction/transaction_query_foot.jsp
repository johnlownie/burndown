<script type="text/javascript">
    $(document).ready(function () {
        $('#duplicates').click(function(e) {
            $('#toggle').val('1');
            $("#table").dataTable().api().ajax.reload();
        });
    });
</script>