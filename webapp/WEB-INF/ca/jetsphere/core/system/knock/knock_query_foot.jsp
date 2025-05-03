<script type="application/javascript">
    $(document).ready(function () {
        $(".table").on("click", ".editBtn", function(event) {
            event.preventDefault();
            onAddEdit({
                title     : $(".panel").attr("data-title"),
                dataString: "edit=true&csrf=" + $(this).attr("data-id"),
                size      : $(".panel").attr("modal-size") == "" ? "modal60" : $(".panel").attr("modal-size")
            });
        });
    });

    function onAddEdit (data) {
        var size = data.size == undefined || data.size == "" ? "modal70" : data.size;

        $.ajax({
            url: $("#tableForm").attr("action"),
            type: "GET",
            cache: false,
            data: data.dataString,
            dataType: "html",
            timeout: 10000
        })
        .success(function(content) {
            var box = bootbox.dialog({
                title: data.title,
                message: content,
                className: size,
                show: false
            })
            .on("shown.bs.modal", function() {
                $("#editForm").show().formValidation("resetForm");
                $("#editForm :input:visible:enabled:first").focus();
            })
            .on("hide.bs.modal", function(e) {
            })
            .modal("show");
        })
        .error(function(x, t, m) {
            notifyError();
        });
    }
    function initValidation() {
        $("#editForm").formValidation({
            framework: "bootstrap",
            excluded: [":disabled"],
//            feedbackIcons: faIcon,
            fields: {
                key: {
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
                },
                value: {
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
                    $("#table").dataTable().api().ajax.reload();
                }
            })
            .error(function(x, t, m) {
                notifyError();
            });
        });
    }
</script>