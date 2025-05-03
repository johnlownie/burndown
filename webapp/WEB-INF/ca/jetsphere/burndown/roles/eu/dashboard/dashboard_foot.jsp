<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("#calendar").fullCalendar({
            theme: false,
            editable: false,
            weekends: false,
            weekNumbers: false,
            aspectRatio: 1,
            header: {left: "prev today", center: "title", right: "today next"},
            events: "my_dashboard.do?json=true",
            eventClick: function(calEvent, jsEvent, view) {
                modal (calEvent);
            },
            loading: function( isLoading, view ) {
                if(isLoading) {
                    $( "#calendar .fc-view-container" ).isLoading({
                        text:       "Loading",
                        position:   "overlay"
                    });
                } else {
                    $( "#calendar .fc-view-container" ).isLoading( "hide" );
                }
            }
        });
    });

    function modal ( calEvent ) {
        var dataString = "attach=true&id=" + calEvent.id;
        $.ajax({
            url:"my_calendar_query.do",
            type: "GET",
            data: dataString,
            dataType: "json",
            timeout: 10000
        })
        .success(function(response) {
            if ( response.success ) {
                bootbox.dialog({
                    title: calEvent.title + " - " + calEvent.start.format(),
                    message: response.content,
                    buttons: {
                        cancel: {
                            label: "OK",
                            className: "btn-primary"
                        }
                    },
                    show: false
                })
                .modal("show");
            };
        })
        .error(function(x, t, m) {
            notifyError("#calendar");
        });
    }
</script>
