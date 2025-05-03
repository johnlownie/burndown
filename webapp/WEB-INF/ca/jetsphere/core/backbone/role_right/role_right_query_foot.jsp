<script type="text/javascript">
    $(document).ready(function() {
        $("#table").find("th:last").addClass("all");

        var table = $("#table").dataTable({
            processing: true,
            ajax: {
                url: $("#queryForm").attr("action") + "?json=true",
                type: "GET",
                data: function(d){
                    d.applicationId = $("#queryForm select[name='applicationId']" ).val();
                    d.roleId        = $("#queryForm select[name='roleId']" ).val();
                }
            },
            columns: [
                { "data": "role_right_name" },
                { "data": "role_right_action" },
                { "data": "role_right_action_href" },
                { "data": "role_right_ordinal" },
                { "data": "role_right_hidden" },
                { "data": "role_right_uuid" }
            ],
            createdRow: function( row, data, dataIndex ) {
                $(row).attr("data-id", data.role_right_id);
                $(row).attr("data-parent-id", data.role_right_parent_id);
            },
            responsive: false,
            paging: false,
            ordering: false,
            info: false,
            searching: false,
            iDisplayLength: 500
        });

        $("#queryForm").on("change", function(event) {
            event.preventDefault();
            console.log("Here");
            $.ajax({
                url: $("#queryForm").attr("action"),
                type: "GET",
                data: $("#queryForm").serialize() + "&fetch=true",
                dataType: "json",
                timeout: 30000
            })
            .success ( function(response) {
                if (response.success) {
                    $("#table").dataTable().api().ajax.reload();
                } else {
                    notifyError("#page-content");
                }
            })
            .error(function(x, t, m) {
                notifyError("#page-content");
            });
//            $("#table").treetable();
            return false;
        });

        $(".expandBtn").on("click", function() {
            onExpandCollapse("close=true&expand=true");
        });

        $(".collapseBtn").on("click", function() {
            onExpandCollapse("close=true&expand=false");
        });
    });

    $(document).on("click", ".editBtn", function(event) {
        event.preventDefault();
        onAddEdit({
            title: $(".panel").attr("data-title"),
            dataString: "edit=true&csrf=" + $(this).attr("data-id"),
            size: $(".panel").attr("modal-size") == "" ? "modal60" : $(".panel").attr("modal-size")
        });
    })
    .on("click", ".cutBtn", function(event) {
        event.preventDefault();
        onCopyPaste({
            dataString: "delete=true&csrf=" + $(this).attr("data-id")
        });
    })
    .on("click", ".copyBtn", function(event) {
        event.preventDefault();
        onCopyPaste({
            dataString: "copy=true&csrf=" + $(this).attr("data-id")
        });
    })
    .on("click", ".pasteBtn", function(event) {
        event.preventDefault();
        onCopyPaste({
            dataString: "paste=true&csrf=" + $(this).attr("data-id")
        });
    })
    .on("click", ".insertB4Btn", function(event) {
        event.preventDefault();
        onAddEdit({
            title: $(".panel").attr("data-title"),
            dataString: "insert=true&before=true&csrf=" + $(this).attr("data-id"),
            size: $(".panel").attr("modal-size") == "" ? "modal60" : $(".panel").attr("modal-size")
        });
    })
    .on("click", ".insertA4Btn", function(event) {
        event.preventDefault();
        onAddEdit({
            title: $(".panel").attr("data-title"),
            dataString: "insert=true&before=false&csrf=" + $(this).attr("data-id"),
            size: $(".panel").attr("modal-size") == "" ? "modal60" : $(".panel").attr("modal-size")
        });
    })
    .on("click", ".insertChild", function(event) {
        event.preventDefault();
        onAddEdit({
            title: $(".panel").attr("data-title"),
            dataString: "insert=true&before=false&child=true&csrf=" + $(this).attr("data-id"),
            size: $(".panel").attr("modal-size") == "" ? "modal60" : $(".panel").attr("modal-size")
        })
    })
    .on("mouseout", ".node", function() {
        $(this).removeClass("node-hovered");
    })
    .on("mouseover", ".node", function() {
        $(this).addClass("node-hovered");
    })
    .on("click", ".node-icon-ce", function() {
        onExpandCollapse("export=true&nodeId=" + $(this).closest("tr").data("id"));
    });
</script>