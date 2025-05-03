$(document).ready(function() {
    $("#table").on( "click", ".importBtn", function(event) {
        event.preventDefault();
        var id = $(this).attr("data-id");
        bootbox.dialog({
            message: "Are you sure you wish to import the transactions?",
            buttons: {
                send: {
                    label: "Import",
                    className: "btn-primary",
                    callback: function() {
                        var dataString="imiport=true&csrf=" + id;
                        $.ajax({
                            url: $("#queryForm").attr("action"),
                            type: "POST",
                            data: dataString,
                            dataType: "json",
                            timeout: 10000
                        })
                        .success(function(response) {
                            if (response.success) {
                                notify("success", "check", "Success", "The transactions have been imported", "floating", 3000);
                            } else {
                                notify("warning", "exclamation-triangle", "Failure", "There was an issue importing the transactions", "floating", 3000);
                            }
                        })
                        .error(function(x, t, m) {
                            notifyError();
                        });
                    }
                },
                cancel: {
                    label: "Cancel",
                    className: "btn-default"
                }
            },
            className: "modal60"
        });
    });
});
