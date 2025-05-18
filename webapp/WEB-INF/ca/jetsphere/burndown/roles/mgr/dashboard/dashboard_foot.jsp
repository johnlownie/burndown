<script type="text/javascript">
    $(document).ready(function () {
        var donut = Morris.Donut({
          element: 'by-category',
          data: [ {value: 0} ],
          formatter: function (x) { return x + "%"},
          colors: [
              '#8fbcbb',
              '#88c0d0',
              '#81a1c1',
              '#5e81ac',
              '#be616a',
              '#5f83af',
              '#a6bf8a',
              '#eacc88',
              '#c19f62',
              '#b490b0',
              '#cf886f'
          ],
          donutType: 'pie'
        }).on('click', function(i, row){
            console.log(row);
            getTransactionData("&category=" + row.label);
        });
        
        var bar = Morris.Bar({
            element: 'by-month',
            data: [{nb: 0}],
            xkey: 'month',
            ykeys: ['discretionary', 'disposable'],
            labels: ['Discretionary', 'Disposable'],
            stacked: true
        }).on('click', function(i, row){
            getTransactionData("&month=" + row.month);
        });

        var table = $("#table").dataTable({
            processing: false,
            ajax: {
                url: $("#queryForm").attr("action") + "?json=true",
                type: "GET",
                data: function(d){
                    d.companyId     = $("#queryForm select[name='companyId']" ).val();
                    d.periodId      = $("#queryForm select[name='periodId']" ).val();
                }
            },
            responsive: true,
            paging: true,
            ordering: true,
            info: true,
            searching: true,
            iDisplayLength: 10,
            rowReorder: true
        });

        function getTransactionData(action) {
            $.ajax({
                url: $("#queryForm").attr("action"),
                data: $("#queryForm").serialize() + "&fetch=true" + action,
                dataType: "json",
                type: "GET",
                timeout: 10000
            })
            .success(function(response) {
                $('#by-category-title').text(response.categoryTitle);
                $('#by-month-title').text(response.monthTitle);
                $('#transactions-title').text(response.transactionsTitle);
                donut.setData(response.categoryData);
                bar.setData(response.monthData);
                $("#table").dataTable().api().ajax.reload();
                if (response.showReset == true) {
                    $('#reset').show();
                } else {
                    $('#reset').hide();
                }
            })
            .error(function(x, t, m) {
                notifyError();
            });
        }
        
        $('#reset').click(function(e) {
            e.preventDefault();
            getTransactionData("");
        });

        $("#queryForm").on("change", function(event) {
            event.preventDefault();
            getTransactionData("");
            $("#table").dataTable().api().ajax.reload();
            return false;
        });
        
        getTransactionData("");
    });
</script>
