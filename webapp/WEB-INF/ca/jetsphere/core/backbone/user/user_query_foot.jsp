<script type="text/javascript">
    $(document).ready(function() {
        $(".chosenselect").chosen({width:"100%"});

        var table = $("#table").dataTable( {
            "ajax": $("#queryForm").attr("action") + "?json=true",
            iDisplayLength: 15,
            "language": {
                "paginate": {
                    "previous": '<i class="fa fa-angle-left"></i>',
                    "next": '<i class="fa fa-angle-right"></i>'
                }
            },
            "dom": '<"toolbar">frtip'
        });

        table.on("click", ".editBtn", function(event) {
            var dataString = "json=true&id=" + $(this).attr("data-id");
            $.ajax({
                url:$("#editForm").attr("action"),
                type: "GET",
                data: dataString,
                dataType: "json"
            })
            .success(function(response) {
                $("#editForm")
                        .find('[name="uuid"]'     ).val(response.user_uuid      ).end()
                        .find('[name="username"]' ).val(response.user_username  ).end()
                        .find('[name="status"]'   ).val(response.user_status    ).end()
                        .find('[name="companyId"]').val(response.user_company_id).end();
                var box = bootbox.dialog({
                    title: "Add/Edit User",
                    message: $("#editForm"),
                    show: false
                })
                .on("shown.bs.modal", function() {
                    $("#editForm").show().formValidation("resetForm");
                })
                .on("hide.bs.modal", function(e) {
                    $("#editForm").hide().appendTo("body");
                })
                .modal("show");

                box.find("#userName").focus();
                });
        });

        table.on( "click", ".deleteBtn", function(event) {
            event.preventDefault();
            var id = $(this).attr("data-id");
            bootbox.dialog({
                message: "Please confirm that you wish to delete this item.",
                buttons: {
                    delete: {
                        label: "Delete",
                        className: "btn-danger",
                        callback: function (result) {
                            if (result) {
                                $("#delete").val(id);
                                $("#queryForm").submit();
                            }
                        }
                    },
                    cancel: {
                        label: "Cancel",
                        className: "btn-default"
                    }
                }
            });
        });

        $("#editForm").formValidation({
            framework: "bootstrap",
            icon: {
                valid: "glyphicon glyphicon-ok",
                invalid: "glyphicon glyphicon-remove",
                validating: "glyphicon glyphicon-refresh"
            },
            fields: {
                username: {
                    validators: {
                        notEmpty: {
                            message: "The username is required"
                        }
                    }
                }
            }
        })
        .on("success.form.fv", function(e) {
            e.preventDefault();

            var $form = $(e.target),
                id    = $form.find('[name="uuid"]').val(),
                dataString = $form.serialize() + ( id == "" ? "&insert=true": "&update=true" );
            console.debug (dataString);
            $.ajax({
                url: $form.attr("action"),
                method: "POST",
                data: dataString,
                dataType: "html"
            }).success(function(response) {
                $form.parents(".bootbox").modal("hide");
                table.api().ajax.reload();
                bootbox.alert("The user profile is updated");
            });
        });
    });
</script>