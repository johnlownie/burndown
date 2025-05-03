$(document).ready(function () {
    $("#table").find("th:last").addClass("all");

    var table = $("#table").dataTable({
        processing: true,
        serverSide: true,
        ajax: {
            url : $("#queryForm").attr("action") + "?json=true",
            type: "GET",
            data: function(d){
                d.companyId     = $("#queryForm select[name='companyId']" ).val();
                d.eventId       = $("#queryForm select[name='eventId']" ).val();
                d.aircraftId    = $("#queryForm select[name='aircraftId']" ).val();
                d.flightId      = $("#queryForm select[name='flightId']" ).val();
                d.airportFromId = $("#queryForm select[name='airportFromid']" ).val();
                d.airportToId   = $("#queryForm select[name='airportToId']"  ).val();
            }
        },
        responsive: true,
        paging: true,
        ordering: true,
        info: true,
        searching: true,
        iDisplayLength: 10
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

    $("#queryForm").on("change", function(event) {
        event.preventDefault();
        $("#table").dataTable().api().ajax.reload();
        return false;
    });

    $(".addBtn").on("click", function (event) {
        event.preventDefault();
        onAddEdit({
            title     : $(".panel").attr("data-title"),
            dataString: "edit=true",
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