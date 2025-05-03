function initValidation() {
    $("#editForm").formValidation({
        framework: "bootstrap",
        excluded: [":disabled"],
//            feedbackIcons: faIcon,
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: 'This field is required and cannot be empty'
                    },
                    stringLength: {
                        min: 1,
                        max: 100,
                        message: 'This field cannot be more that 100 characters long'
                    }
                }
            }
        }
    })
    .on("success.field.bv", function(e, data) {
        var $parent = data.element.parents(".form-group");
        $parent.removeClass("has-success");
    })
    .on("success.form.fv", function(e) {
        e.preventDefault();

        var $form = $(e.target),
            dataString = $form.serialize() + "&jupdate=true";
        $.ajax({
            url: $("#editForm").attr("action"),
            method: "POST",
            data: dataString,
            dataType: "json",
            timeout: 30000
        })
        .success(function(response) {
            if (response.success) {
                $form.parents(".bootbox").modal("hide");
                $("tr[data-id=" + response.id + "] td:first-child span.node-name").html(response.name);
            }
        })
        .error(function(x, t, m) {
            notifyError();
        });
    });
}