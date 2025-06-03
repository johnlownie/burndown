$(document).ready(function () {
    var table = $("#table").dataTable({
        processing: false,
        ajax: {
            url: $("#queryForm").attr("action") + "?json=true",
            type: "GET",
            data: function(d){
                d.companyId         = $("#queryForm select[name='companyId']" ).val();
                d.applicationId     = $("#queryForm select[name='applicationId']" ).val();
                d.periodId          = $("#queryForm select[name='periodId']" ).val();
                d.accountId         = $("#queryForm select[name='accountId']" ).val();
                d.categoryId        = $("#queryForm select[name='categoryId']" ).val();
                d.typeId            = $("#queryForm select[name='typeId']" ).val();
                d.roleId            = $("#queryForm select[name='roleId']" ).val();
                d.treeSetId         = $("#queryForm select[name='treeSetId']" ).val();
                d.toggle            = $("#queryForm input[name='toggle']" ).val();
                d.startDateAsString = $("#queryForm input:text[name='startDateAsString']").val();
                d.endDateAsString   = $("#queryForm input:text[name='endDateAsString']").val();
            }
        },
        responsive: true,
        paging: true,
        ordering: true,
        info: true,
        searching: true,
        iDisplayLength: 10,
        rowReorder: true,
        pageLength: 100
    })
    .on("click", ".editBtn", function(event) {
        event.preventDefault();
        onAddEdit({
            title     : $(".panel").attr("data-title"),
            dataString: "edit=true&csrf=" + $(this).attr("data-id"),
            size      : $(".panel").attr("modal-size") == "" ? "modal60" : $(".panel").attr("modal-size")
        });
    })
    .on( "click", ".deleteBtn", function(event) {
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
                            var dataString = "delete=" + id;
                            $.ajax({
                                url: $("#queryForm").attr("action"),
                                type: "POST",
                                data: dataString,
                                dataType: "html",
                                timeout: 10000
                            })
                            .success(function(content) {
                                $("#table").dataTable().api().ajax.reload();
                            })
                            .error(function(x, t, m) {
                                notifyError(".panel");
                            })
                        }
                    }
                },
                cancel: {
                    label: "Cancel",
                    className: "btn-default"
                }
            },
            className: "deleteDlg"
        });
    })
    .on( "click", ".copyBtn", function(event) {
        event.preventDefault();

        var id = $(this).attr("data-id");
        bootbox.dialog({
            message: "Please confirm that you wish to copy this item.",
            buttons: {
                delete: {
                    label: "Copy",
                    className: "btn-success",
                    callback: function (result) {
                        if (result) {
                            var dataString = "copy=true&csrf=" + id;
                            $.ajax({
                                url: $("#queryForm").attr("action"),
                                type: "POST",
                                data: dataString,
                                dataType: "html",
                                timeout: 10000
                            })
                            .success(function(content) {
                                $("#table").dataTable().api().ajax.reload();
                            })
                            .error(function(x, t, m) {
                                notifyError(".panel");
                            })
                        }
                    }
                },
                cancel: {
                    label: "Cancel",
                    className: "btn-default"
                }
            },
            className: "deleteDlg"
        });
    });

    $(".addBtn").on("click", function (event) {
        event.preventDefault();
        onAddEdit({
            title     : $(".panel").attr("data-title"),
            dataString: "edit=true",
            size      : $(".panel").attr("modal-size") == "" ? "modal60" : $(".panel").attr("modal-size")
        });
    });

    $("#queryForm").on("change", function(event) {
        event.preventDefault();
        $("#table").dataTable().api().ajax.reload();
        return false;
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
