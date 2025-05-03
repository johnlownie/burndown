$(document).ready(function () {
    $("#table").find("th:last").addClass("all");

    var table = $("#table").DataTable({
        processing: true,
        ajax: {
            url: $("#queryForm").attr("action") + "?json=true",
            type: "GET",
            data: function(d){
                d.companyId         = $("#queryForm select[name='companyId']" ).val();
                d.applicationId     = $("#queryForm select[name='applicationId']" ).val();
                d.periodId          = $("#queryForm select[name='periodId']" ).val();
                d.boardId           = $("#queryForm select[name='boardId']"  ).val();
                d.schoolId          = $("#queryForm select[name='schoolId']" ).val();
                d.gradeId           = $("#queryForm select[name='gradeId']"  ).val();
                d.programId         = $("#queryForm select[name='programId']").val();
                d.studentId         = $("#queryForm select[name='studentId']").val();
                d.startDateAsString = $("#queryForm input:text[name='startDateAsString']").val();
                d.statusId          = $("#queryForm select[name='statusId']").val();
                d.parentId          = $("#queryForm input:hidden[name='parentId']").val();
            }
        },
        paging: true,
        info: true,
        searching: false,
        rowReorder: { update: false },
        responsive: true,
        columnDefs: [ { orderable: true, className: "reorder", targets: 0 }, { orderable: false, targets: "_all" } ],
    })
    .on( "row-reorder", function ( e, diff, edit ) {
        var keys = "", uuids = "";
        for ( var i=0, ien=diff.length; i<ien; i++ ) {
            var uuid = $(diff[i].node).find("td:last button:first").data("id");
            keys += "&keys[]=" + uuid;
            uuids += "&" + uuid + "=" + diff[i].newData;
        }

        $.ajax({
            url: $("#queryForm").attr("action"),
            type: "GET",
            cache: false,
            data: "commit=true" + keys + uuids,
            dataType: "json",
            timeout: 10000
        })
        .success(function(response) {
            if ( response.success ) {
                $("#table").dataTable().api().ajax.reload();
            } else {
                notify ( "danger", "exclamation", "Error", "There was an error setting the ordinals", "floating", 8000 );
            }
        })
        .error(function(x, t, m) {
            notifyError();
        });
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

