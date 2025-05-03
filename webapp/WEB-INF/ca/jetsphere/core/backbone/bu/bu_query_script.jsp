<script type="text/javascript" src="plugins/bootstrap-treeview/bootstrap-treeview.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        var $tree;

        $.ajax({
            url: $("#queryForm").attr("action"),
            type: "GET",
            data: "fetch=true",
            dataType: "json",
            timeout: 14000
        })
        .success(function(response) {
            $tree = $("#treeview").treeview({
                data: response
            });
        })
        .error(function(x, t, m) {
            notifyError(".modal-content");
        });

        $("#btn-expand-all").on("click", function (e) {
            var levels = $("#select-expand-all-levels").val();
            $tree.treeview("expandAll", { levels: 9, silent: false });
        });

        $("#btn-collapse-all").on("click", function (e) {
            $tree.treeview("collapseAll", { silent: false });
        });
    });
</script>