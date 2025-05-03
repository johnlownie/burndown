$(document).ready(function () {
    $("#table").find("th:last").addClass("all");

    var table = $("#table").dataTable({
        processing: true,
        ajax: {
            url : $("#queryForm").attr("action") + "?json=true",
            type: "POST",
            data: function(d){
                d.periodId  = $("#queryForm select[name='periodId']" ).val();
                d.sessionId = $("#queryForm select[name='sessionId']"  ).val();
                d.boardId   = $("#queryForm select[name='boardId']"  ).val();
                d.schoolId  = $("#queryForm select[name='schoolId']" ).val();
                d.gradeId   = $("#queryForm select[name='gradeId']"  ).val();
                d.programId = $("#queryForm select[name='programId']").val();
            }
        },
        responsive: true,
        paging: true,
        ordering: true,
        info: true,
        searching: true,
        iDisplayLength: 10
    });

    $(".addBtn").on("click", function (e) {
        e.preventDefault();
        onAddEdit($(".panel").attr("data-title"), "edit=true");
    });

    table.on("click", ".editBtn", function(event) {
        event.preventDefault();
        onAddEdit($(".panel").attr("data-title"), "edit=true&csrf=" + $(this).attr("data-id"));
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

    $("#queryForm").on("change", "select", function(e) {
        e.preventDefault();
        $("#table").dataTable().api().ajax.reload();
        return false;
    });
});

function onAddEdit( title, dataString ) {
    $.ajax({
        url: $("#tableForm").attr("action"),
        type: "GET",
        data: dataString,
        dataType: "html",
        timeout: 10000
    })
    .success(function(content) {
        var box = bootbox.dialog({
            title: title,
            message: content,
            className: "modal70",
            show: false
        })
        .on("shown.bs.modal", function() {
            $("#editForm").show().formValidation("resetForm");
            $("#editForm :input:visible:enabled:first").focus();
            $("#calendar").fullCalendar("render");
        })
        .on("hide.bs.modal", function(e) {
        })
        .modal("show");
    })
    .error(function(x, t, m) {
        notifyError();
    });
}

