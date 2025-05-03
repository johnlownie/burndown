$(document).ready(function() {
    $("#table").on("click", ".roleBtn", function(event) {
        var dataString = "role=true&csrf=" + $(this).attr("data-id");
        $.ajax({
            url: "user_role_edit.do",
            type: "POST",
            data: dataString,
            dataType: "html",
            timeout: 10000
        })
        .success(function (content) {
            bootbox.dialog({
                title: "Add/Edit Role",
                size: "small",
                message: content,
                buttons: {
                    success: {
                        label: "Save",
                        className: "btn-success",
                        callback: function () {
                            dataString = "save=true&" + $("#roleForm").serialize();
                            $.ajax({
                                url: "user_role_edit.do",
                                type: "POST",
                                data: dataString,
                                dataType: "html",
                                timeout: 10000
                            })
                            .success(function (content) {
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
                }
            })
        })
        .error(function(x, t, m) {
            notifyError();
        });
    });
});