<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<script type="text/javascript">
    $(document).ready(function() {
        $("#startDate").attr("placeholder", "<bean:message key="please.select"/>");
        $("#endDate").attr("placeholder", "<bean:message key="please.select"/>");

        $(".btnPrint").click(function(){
            $(".table-responsive").printArea({
                mode: "iframe",
                print: "table",
                extraHead: "<meta charset=\"utf-8\" />,<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>",
                retainAttr: ["id", "class", "style"]
            });
        });

        $(".btnDownload").click(function(){
            var dataString = $("#queryForm").serialize() + "&export=true";
            $.ajax({
                url: $("#queryForm").attr("action"),
                type: "POST",
                data: dataString,
                dataType: "html",
                timeout: 14000
            })
            .success(function(content) {
                var url='data:application/vnd.ms-excel,' + escape(content) ;
                var link = document.getElementById("downloadLink");
                link.setAttribute("href", url);
                link.setAttribute("download", "Monthly.csv");
                link.click();
            })
            .error(function(x, t, m) {
                notifyError(".modal-content");
            });
        });
    });
</script>