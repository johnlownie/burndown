$(document).ready(function() {
    $("#table").on("click", ".listBtn", function(event){
        entries({
            title     : $(this).attr("data-title"),
            dataString: "fetch=true&csrf=" + $(this).attr("data-id"),
            size      : $(".panel").attr("modal-size") == "" ? "modal70" : $(".panel").attr("modal-size")
        });
    });

    $("#table").on("click", ".calculatorBtn", function(event){
        entries({
            title: $(this).attr("data-title"),
            dataString: "export=true&csrf=" + $(this).attr("data-id"),
            size: $(".panel").attr("modal-size") == "" ? "modal70" : $(".panel").attr("modal-size")
        });
    });

    $("#table").on("click", ".calendarBtn", function(event){
        entries({
            title     : $(this).attr("data-title"),
            dataString: "import=true&csrf=" + $(this).attr("data-id"),
            size      : $(".panel").attr("modal-size") == "" ? "modal70" : $(".panel").attr("modal-size")
        });
    });

});

function entries(data) {
    var size = data.size == undefined || data.size == "" ? "modal70" : data.size;

    $.ajax({
        url: $("#queryForm").prop("action"),
        type: "POST",
        data: data.dataString,
        dataType: "html",
        timeout: 10000
    })
    .success(function(content) {
        bootbox.dialog({
            title: data.title,
            message: content,
            buttons: {
                cancel: {
                    label: "OK",
                    className: "btn-primary"
                }
            },
            className: size,
            show: false
        })
        .on("shown.bs.modal", function() {
            $("#entries").hide();
            $("#entries").dataTable({
                "responsive": true,
                "paging"    : false,
                "ordering"  : false,
                "info"      : false,
                "searching" : false
            });
            $("#entries").show();
        })
        .on("hide.bs.modal", function(e) {
        })
        .modal("show");
    })
    .error(function(x, t, m) {
        notifyError();
    });
}