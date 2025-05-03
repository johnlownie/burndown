$(document).ready(function() {
    $("#table").find("th:last").addClass("all");

    var table = $("#table").dataTable({
        responsive: false,
        iDisplayLength: 10
    });

    table.on( "click", ".deleteBtn", function(event) {
        event.preventDefault();
        var id = $(this).attr("data-id");
        bootbox.dialog({
            message: "Please confirm that you wish to delete this item.",
            buttons: {
                delete: {
                    label: "Delete",
                    className: "btn-danger",
                    callback: function (result) {
                        if (result) {
                            $("#delete").val(id);
                            $("#queryForm").submit();
                        }
                    }
                },
                cancel: {
                    label: "Cancel",
                    className: "btn-default"
                }
            },
            className: "deleteDlg"
        });
    });

    table.on("click", ".copyBtn", function(event) {
        $("#copy").val($(this).attr("data-id"));
        $("#queryForm").submit();
    });
});
