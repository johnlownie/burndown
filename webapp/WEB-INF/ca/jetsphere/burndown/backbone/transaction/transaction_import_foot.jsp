<script type="text/javascript">
    $(document).ready(function () {
        $("#dropPeriodId").val($("#periodId").val());

        $("#periodId").change(function(){
            $("#dropPeriodId").val($("#periodId").val());
        });

        Dropzone.options.dropzone = {
            paramName: "formFile",
            autoProcessQueue: true,
            uploadMultiple: false,
            //parallelUploads: 25,
            //maxFiles: 25,

            init: function() {
                this.on("queuecomplete", function(progress) { $("#table").dataTable().api().ajax.reload(); });
                this.on("complete", function(file) { this.removeFile(file); });
            }
        };
    });
</script>