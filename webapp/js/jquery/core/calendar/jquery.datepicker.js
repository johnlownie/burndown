$(document).ready(function() {
    $(".input-group.date").datepicker({
        autoclose: true,
        clearBtn: true,
        format   : "yyyy-mm-dd",
        orientation: "bottom auto"
    }).on("changeDate", function(e) {
        $(this).closest("form").formValidation("revalidateField", $(this).children("input").attr("name"));
    });
});
