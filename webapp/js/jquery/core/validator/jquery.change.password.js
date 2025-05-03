$(document).ready(function() {
    $("#passwordForm").formValidation({
        framework: "bootstrap",
        message: "This value is not valid",
        fields: {
            password: {
                validators: {
                    notEmpty: {
                        message: "The password is required and cannot be empty"
                    },
                    blank: {}
                }
            },
            newPassword: {
                validators: {
                    notEmpty: {
                        message: "The new password is required and cannot be empty"
                    },
                    stringLength: {
                        min: 6,
                        max: 32,
                        message: "The password must be between 6 and 32 characters in length."
                    },
                    blank: {}
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: "The confirm password is required and cannot be empty"
                    },
                    identical: {
                        field: "newPassword",
                        message: "The password and its confirm are not the same"
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
            data  = "update=true&" + $form.serialize();

        $.ajax({
            url: $form.attr("action"),
            data: data,
            dataType: "json",
            timeout: 20000
        }).success(function(response) {
            if (response.success) {
                fv.resetForm();
                $form.find("input[type=password]").val("");
                notify("success", "check", "Success", "Your password has been changed", "floating", 3000);
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

    $(".btnSave").on("click", function(e) {
        e.preventDefault();

        var $form = $("#avatarForm"),
            data  = "json=true&" + $form.serialize(),
            $image = $form.find("input[name=avatarId]:checked").val();

        $.ajax({
            url: $form.attr("action"),
            data: data,
            dataType: "json",
            timeout: 20000
        }).success(function(response) {
            if (response.success) {
                var newSrc = $(".img-user").attr("src").replace(/\d+/, $image);
                $(".img-user").attr("src", newSrc);
            }
        })
        .error(function(x, t, m) {
            notifyError();
        });
    });

    $("#aboutForm").formValidation({
        framework: "bootstrap",
        message: "This value is not valid",
        fields: {
            firstname: {
                validators: {
                    notEmpty: {
                        message: "The first name is required and cannot be empty"
                    },
                    blank: {}
                }
            },
            lastname: {
                validators: {
                    notEmpty: {
                        message: "The last name is required and cannot be empty"
                    },
                    blank: {}
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: "The last name is required and cannot be empty"
                    },
                    emailAddress: {
                        message: "The input is not a valid email address"
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
            name  = $("#firstname").val() + " " + $("#lastname").val(),
            email = $("#email").val(),
            data  = "json=true&" + $form.serialize();

        $.ajax({
            url: $form.attr("action"),
            data: data,
            dataType: "json",
            timeout: 20000
        }).success(function(response) {
            if (response.success) {
                fv.resetForm();
                $("#aside h4.text-lg, div.username").html(name);
                $("#aside p.text-sm").html(email);
                notify("success", "check", "Success", "Your information has been changed", "floating", 3000);
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
    
    $("#changePasswordForm").formValidation({
        framework: "bootstrap",
        message: "This value is not valid",
        fields: {
            newPassword: {
                validators: {
                    notEmpty: {
                        message: "The new password is required and cannot be empty"
                    },
                    stringLength: {
                        min: 6,
                        max: 32,
                        message: "The password must be between 6 and 32 characters in length."
                    },
                    blank: {}
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: "The confirm password is required and cannot be empty"
                    },
                    identical: {
                        field: "newPassword",
                        message: "The password and its confirm are not the same"
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
            data  = "update=true&" + $form.serialize();

        $.ajax({
            url: $form.attr("action"),
            data: data,
            dataType: "json",
            timeout: 20000
        }).success(function(response) {
            if (response.success) {
                fv.resetForm();
                $form.find("input[type=password]").val("");
                notify("success", "check", "Success", "Your password has been changed", "floating", 3000);
                window.location.href="login.do"
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