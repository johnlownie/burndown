$(document).ready(function() {
    $("#regForm").formValidation({
        framework: "bootstrap",
        message: "This value is not valid",
        addOns: {
            reCaptcha2: {
                element: "captchaContainer",
                language: "en",
                theme: "light",
                siteKey: $("#siteKey").val(),
                timeout: 120,
                message: "Please click the box beside the \"I am not a robot\" message"
            }
        },
        fields: {
            fullname: {
                validators: {
                    notEmpty: {
                        message: 'A full name is required and cannot be empty'
                    },
                    blank: {}
                }
            },
            username: {
                validators: {
                    notEmpty: {
                        message: 'An email address is required and cannot be empty'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    },
                    regexp: {
                        regexp: '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
                        message: 'The value is not a valid email address'
                    },
                    blank: {}
                }
            },
            newPassword: {
                validators: {
                    notEmpty: {
                        message: 'A password is required and cannot be empty'
                    },
                    stringLength: {
                        min: 6,
                        max: 32,
                        message: 'The password must be between 6 and 32 characters in length'
                    },
                    blank: {}
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: 'The conform password is required and cannot be empty'
                    },
                    identical: {
                        field: 'newPassword',
                        message: 'The password and its confirm are not the same'
                    },
                    blank: {}
                }
            }
        }
    }).on("success.field.bv", function(e, data) {
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
            data: data,
            dataType: "json",
            timeout: 20000
        }).success(function(response) {
            if (response.success) {
                $(".main-body").html(response.content);
            } else {
                for (var field in response.fields) {
                    fv.updateMessage(field, "blank", response.fields[field])
                      .updateStatus(field, "INVALID", "blank");
                }
            }
        })
        .error(function(x, t, m) {
            fv.resetForm();
            notifyError();
        });
    });
});