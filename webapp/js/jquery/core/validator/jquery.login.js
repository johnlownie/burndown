$(document).ready(function() {
    $("#loginForm").formValidation({
        framework: "bootstrap",
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: "The email address is required and cannot be empty"
                    },
                    blank: {}
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: "The password is required and cannot be empty"
                    },
                    blank: {}
                }
            }
        }
    })
    .on("success.field.fv", function(e, data) {
        var $parent = data.element.parents(".form-group");
        $parent.removeClass("has-success");
    })
    .on("success.form.fv", function(e) {
        e.preventDefault();

        var $form = $(e.target),
            fv    = $form.data("formValidation"),
            data  = "fetch=true&" + $form.serialize();

        $.ajax({
            url: $form.attr("action"),
            type: "POST",
            data: data,
            dataType: "json",
            timeout: 10000
        }).success(function(response) {
            if (response.success) {
                window.location.href = response.url;
            } else {
                if (response.pending) {
                    $(".panel-body").html(response.content);
                } else {
                    $form.find("input[type=password]").val("");
                    fv.resetForm();
                    $("#messages").html(response.fields).show();
                    $("#username").focus();
                }
            }
        })
        .error(function(x, t, m) {
            fv.resetForm();
            notifyError();
        });
    });
});