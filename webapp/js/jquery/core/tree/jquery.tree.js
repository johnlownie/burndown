$(document).ready(function () {
    $("#queryForm").on("change", function(event) {
        event.preventDefault();
        console.log("Here");
        $.ajax({
                url: $("#queryForm").attr("action"),
                type: "GET",
                data: $("#queryForm").serialize() + "&fetch=true",
                dataType: "json",
                timeout: 30000
            })
            .success ( function(response) {
                if (response.success) {
                    $("#table").dataTable().api().ajax.reload();
                } else {
                    notifyError("#page-content");
                }
            })
            .error(function(x, t, m) {
                notifyError("#page-content");
            });
            $("#table").treetable();
        return false;
    });

    $(".expandBtn").on("click", function() {
        onExpandCollapse("close=true&expand=true");
    });

    $(".collapseBtn").on("click", function() {
        onExpandCollapse("close=true&expand=false");
    });
});

$(document).on("click", ".editBtn", function(event) {
    event.preventDefault();
    onAddEdit({
        title: $(".panel").attr("data-title"),
        dataString: "edit=true&csrf=" + $(this).attr("data-id"),
        size: $(".panel").attr("modal-size") == "" ? "modal60" : $(".panel").attr("modal-size")
    });
})
.on("click", ".cutBtn", function(event) {
    event.preventDefault();
    onCopyPaste({
        dataString: "delete=true&csrf=" + $(this).attr("data-id")
    });
})
.on("click", ".copyBtn", function(event) {
    event.preventDefault();
    onCopyPaste({
        dataString: "copy=true&csrf=" + $(this).attr("data-id")
    });
})
.on("click", ".pasteBtn", function(event) {
    event.preventDefault();
    onCopyPaste({
        dataString: "paste=true&csrf=" + $(this).attr("data-id")
    });
})
.on("click", ".insertB4Btn", function(event) {
    event.preventDefault();
    onAddEdit({
        title: $(".panel").attr("data-title"),
        dataString: "insert=true&before=true&csrf=" + $(this).attr("data-id"),
        size: $(".panel").attr("modal-size") == "" ? "modal60" : $(".panel").attr("modal-size")
    });
})
.on("click", ".insertA4Btn", function(event) {
    event.preventDefault();
    onAddEdit({
        title: $(".panel").attr("data-title"),
        dataString: "insert=true&before=false&csrf=" + $(this).attr("data-id"),
        size: $(".panel").attr("modal-size") == "" ? "modal60" : $(".panel").attr("modal-size")
    });
})
.on("click", ".insertChild", function(event) {
    event.preventDefault();
    onAddEdit({
        title: $(".panel").attr("data-title"),
        dataString: "insert=true&child=true&csrf=" + $(this).attr("data-id"),
        size: $(".panel").attr("modal-size") == "" ? "modal60" : $(".panel").attr("modal-size")
    });
})
.on("mouseout", ".node", function() {
    $(this).removeClass("node-hovered");
})
.on("mouseover", ".node", function() {
    $(this).addClass("node-hovered");
})
.on("click", ".node-icon-ce", function() {
    onExpandCollapse("export=true&nodeId=" + $(this).closest("tr").data("id"));
});

function onAddEdit (data) {
    var size = data.size == undefined || data.size == "" ? "modal70" : data.size;

    $.ajax({
        url: $("#treeForm").attr("action"),
        type: "GET",
        cache: false,
        data: data.dataString,
        dataType: "html",
        timeout: 10000
    })
    .success(function (content) {
        var box = bootbox.dialog({
            title: data.title,
            message: content,
            className: size,
            show: false
        })
        .on("shown.bs.modal", function () {
            $("#editForm").show().formValidation("resetForm");
            $("#editForm :input:visible:enabled:first").focus();
        })
        .on("hide.bs.modal", function (e) {
        })
        .modal("show");
    })
    .error(function (x, t, m) {
        notifyError();
    });
}

function onCopyPaste (data) {
    $.ajax({
        url: $("#treeForm").attr("action"),
        type: "GET",
        cache: false,
        data: data.dataString,
        dataType: "json",
        timeout: 10000
    })
    .success(function (response) {
        if (response.success) {
            $("#table").dataTable().api().ajax.reload();
        } else {
            notifyError();
        }
    })
    .error(function (x, t, m) {
        notifyError();
    });
}

function onExpandCollapse(dataString) {
    $.ajax({
        url: $("#queryForm").attr("action"),
        type: "GET",
        data: dataString,
        dataType: "json",
        timeout: 30000
    })
    .success (function (response) {
        if (response.success) {
            $("#table").dataTable().api().ajax.reload();
        } else {
            notifyError("#page-content");
        }
    })
    .error(function (x, t, m) {
        notifyError("#page-content");
    });
}