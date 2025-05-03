<%@ page import="ca.jetsphere.core.common.DockYard" %>
<%@ page import="ca.jetsphere.core.security.RequestProcessor" %>

<% if ( DockYard.isStaging ( request ) ) { %>
<script type="text/javascript" src="plugins/countdown/jquery.countdown.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        function getMidnightFromNow() {
            var midnight = new Date(); midnight.setHours( 24 ); midnight.setMinutes( 0 ); midnight.setSeconds( 0 ); midnight.setMilliseconds( 0 );
            return midnight;
        }

        $("#clock").countdown(getMidnightFromNow(), function(event) {
            $(this).html(event.strftime("%H:%M:%S"));
        });
    });
</script>
<% } else { %>
<script type="text/javascript">
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-6644436-8', 'auto');
    ga('send', 'pageview');

</script>
<% }%>
<% if ( RequestProcessor.getLockOut().isWait() ) { %>
<script type="text/javascript" src="plugins/countdown/jquery.countdown.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#countdown").countdown(<%= ( RequestProcessor.getLockOut().getWatch() + RequestProcessor.getLockOut().shutdown() ) %>, function(event) {
            $(this).html(event.strftime("%H:%M:%S"));
        });
    });
</script>
<% } %>
