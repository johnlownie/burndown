$(document).ready(function() {
    $(".input-group.date").datepicker({
        autoclose: true,
        clearBtn: true,
        format   : "yyyy-mm",
        orientation: "bottom auto",
        startView: "months",
        minViewMode: "months"
    }).on("changeDate", function(e) {
        $(this).closest("form").formValidation("revalidateField", $(this).children("input").attr("name"));
    });
});