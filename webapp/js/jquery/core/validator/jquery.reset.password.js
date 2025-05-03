$(document).ready(function() {
    $("#resetForm").formValidation({
        framework: "bootstrap",
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: "The email address is required and cannot be empty"
                    },
                    emailAddress: {
                        message: "The input is not a valid email address"
                    },
                    blank: {}
                }
            }
        }
    })
    .on("success.form.fv", function(e) {
        e.preventDefault();

        var $form = $(e.target),
            fv    = $form.data("formValidation"),
            data  = "fetch=true&" + $form.serialize();

        $.ajax({
            url: $form.attr("action"),
            data: data,
            dataType: "json",
            timeout: 10000
        }).success(function(response) {
            if (response.success) {
                $(".main-body").html(response.content);
            } else {
                for (var field in response.fields) {
                    fv.updateMessage(field, "blank", response.fields[field]).updateStatus(field, "INVALID", "blank");
                }
            }
        })
        .error(function(x, t, m) {
            fv.resetForm();
            notifyError();
        });
    });
});