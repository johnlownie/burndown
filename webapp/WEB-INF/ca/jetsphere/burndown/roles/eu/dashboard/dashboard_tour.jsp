<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="ca.jetsphere.core.tier1.backbone.company.Company" %>
<%@ page import="ca.jetsphere.core.tier1.backbone.company.CompanySession" %>

<% Company company = CompanySession.getSelected ( request ); %>

<script type="text/javascript">
    $(document).ready(function() {
        var tour = new Tour({
            name: "wizard_tour",
            steps: [
                {
                    title: "<bean:message key="dashboard.tour.step.01.head" arg0="<%= company.getName() %>"/>",
                    content: "<bean:message key="dashboard.tour.step.01.body"/>",
                    orphan: true
                },
                {
                    element: "#by-category",
                    title: "<bean:message key="dashboard.tour.step.02.head"/>",
                    content: "<bean:message key="dashboard.tour.step.02.body"/>",
                    placement: 'right'
                },
                {
                    element: "#by-month",
                    title: "<bean:message key="dashboard.tour.step.03.head"/>",
                    content: "<bean:message key="dashboard.tour.step.03.body"/>",
                    placement: 'left'
                },
                {
                    element: "#transaction-panel",
                    title: "<bean:message key="dashboard.tour.step.04.head"/>",
                    content: "<bean:message key="dashboard.tour.step.04.body"/>",
                    placement: 'top'
                },
                {
                    title: "<bean:message key="dashboard.tour.step.05.head"/>",
                    content: "<bean:message key="dashboard.tour.step.05.body"/>",
                    orphan: true
                }
            ]
        });
        tour.init();
        tour.start();

        $(".btnHelp").on("click", function(e) {
            e.preventDefault();
            tour.restart();
            tour.goTo($(this).attr("data-id"));
        });
    });
</script>