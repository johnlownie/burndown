<script type="text/javascript">
    $(document).ready(function() {
        $("#table").find("th:last").addClass("all");

        var table = $("#table").dataTable({
            processing: true,
            ajax: {
                url: $("#queryForm").attr("action") + "?json=true",
                type: "GET"
            },
            columns: [
                { "data": "category_name" },
                { "data": "category_included" },
                { "data": "category_discretionary" },
                { "data": "category_uuid" }
            ],
            createdRow: function( row, data, dataIndex ) {
                $(row).attr("data-id", data.category_id);
                $(row).attr("data-parent-id", data.category_parent_id);
            },
            responsive: false,
            paging: false,
            ordering: false,
            info: false,
            searching: false,
            iDisplayLength: 500
        });
            $("#table").treetable();
    });
</script>