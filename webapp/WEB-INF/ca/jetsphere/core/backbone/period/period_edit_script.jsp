<script type="text/javascript" src="plugins/bootstrap-datepicker/bootstrap-datepicker.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".input-group.date").datepicker({
            autoclose: true,
            format   : "yyyy-mm-dd"
        });

        $("#editForm input:checked").parent("label").addClass("active");
    });
</script>