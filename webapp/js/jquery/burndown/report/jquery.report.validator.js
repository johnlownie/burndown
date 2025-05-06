$(document).ready(function() {
    $("#queryForm").formValidation({
        framework: "bootstrap",
        excluded: [":disabled"],
        fields: {
            startDateAsString: {
                validators: {
                    notEmpty: {
                        message: "Please select a start date for the report"
                    },
                    date: {
                        format: "YYYY-MM-DD",
                        message: "The date is not a valid"
                    }
                }
            },
            endDateAsString: {
                validators: {
                    notEmpty: {
                        message: "Please select a end date for the report"
                    },
                    date: {
                        format: "YYYY-MM-DD",
                        message: "The date is not a valid"
                    }
                }
            },
            type: {
                validators: {
                    notEmpty: {
                        message: "Please select a report type"
                    }
                }
            }
        }
    });
});