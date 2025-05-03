$(document).ready(function () {
    $("#table").on( "click", ".clearBtn", function(event) {
        event.preventDefault();

        var id = $(this).attr("data-id");
        bootbox.dialog({
            message: "Please confirm that you wish to clear this item.",
            buttons: {
                delete: {
                    label: "Clear",
                    className: "btn-purple",
                    callback: function (result) {
                        if (result) {
                            var dataString = "delete=" + id;
                            $.ajax({
                                url: $("#queryForm").attr("action"),
                                type: "POST",
                                data: dataString,
                                dataType: "html",
                                timeout: 10000
                            })
                            .success(function(content) {
                                $("#table").dataTable().api().ajax.reload();
                            })
                            .error(function(x, t, m) {
                                notifyError(".panel");
                            })
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
});