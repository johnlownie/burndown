$(document).ready(function() {
    $("#table").on( "click", ".emailBtn", function(event) {
        event.preventDefault();
        var id = $(this).attr("data-id");
        bootbox.dialog({
            message: "Are you sure you wish to resend the Confirmation email?",
            buttons: {
                send: {
                    label: "Send",
                    className: "btn-primary",
                    callback: function() {
                        var dataString="send=true&csrf=" + id;
                        $.ajax({
                            url: $("#queryForm").attr("action"),
                            type: "POST",
                            data: dataString,
                            dataType: "json",
                            timeout: 10000
                        })
                        .success(function(response) {
                            if (response.success) {
                                notify("success", "check", "Success", "The confirmation email has been resent", "floating", 3000);
                            } else {
                                notify("warning", "exclamation-triangle", "Failure", "The confirmation email could not be resent", "floating", 3000);
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
