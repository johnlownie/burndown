<script type="text/javascript">
    $(document).ready(function() {
	$("#run").on("click", function(event) {
            event.preventDefault();
            onRun();
	});
    });

    function onRun() {
        $.ajax({
            url: $("#editForm").attr("action"),
            type: "GET",
            cache: false,
            data: "fetch=true",
            dataType: "json",
            timeout: 10000
        })
        .success(function(response) {
	    $("#result").html(response.result);
        })
        .error(function(x, t, m) {
            notifyError();
        })
        .complete(
	);
    }
</script>
