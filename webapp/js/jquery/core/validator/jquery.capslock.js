$(document).ready(function() {
    $("[type=password]").keypress(function(e) {
        var $password = $(this),
            tooltipVisible = $(".tooltip").is(":visible"),
            s = String.fromCharCode(e.which);

        if ( s.toUpperCase() === s && s.toLowerCase() !== s && !e.shiftKey ) {
            if (!tooltipVisible)
                $password.attr("title", "Caps Lock is On");
                $password.tooltip("show");
        } else {
            if (tooltipVisible)
                $password.attr("title", "");
                $password.tooltip("hide");
        }

        $password.blur(function(e) {
            $password.tooltip("hide");
        });
    });
});
