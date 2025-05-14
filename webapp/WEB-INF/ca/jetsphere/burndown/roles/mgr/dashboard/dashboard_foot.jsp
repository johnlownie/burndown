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
            getTransactionData("export", row.label);
        });
        
        var bar = Morris.Bar({
            element: 'by-month',
            data: [{nb: 0}],
            xkey: 'month',
            ykeys: ['amount'],
            labels: ['Amount'],
            stacked: true
        }).on('click', function(i, row){
            getTransactionData("fetch", row.month);
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

        function getData() {
            $.ajax({
                url: $("#queryForm").attr("action"),
                data: $("#queryForm").serialize() + "&report=true",
                dataType: "json",
                type: "GET",
                timeout: 10000
            })
            .success(function(response) {
                donut.setData(response.categoryData);
                bar.setData(response.monthData);
            })
            .error(function(x, t, m) {
                notifyError();
            });
        }

        function getTransactionData(action, row_data) {
            $.ajax({
                url: $("#queryForm").attr("action"),
                data: $("#queryForm").serialize() + "&" + action + "=true&topic=" + row_data,
                dataType: "json",
                type: "GET",
                timeout: 10000
            })
            .success(function(response) {
                $("#table").dataTable().api().ajax.reload();
            })
            .error(function(x, t, m) {
                notifyError();
            });
        }
        
        getData();
    });
</script>
