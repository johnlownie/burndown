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
            getTransactionData("&category=" + row.label);
        });
        
        var bar = Morris.Bar({
            element: 'by-month',
            data: [{nb: 0}],
            xkey: 'month',
            ykeys: ['fixed', 'discretionary'],
            labels: ['Fixed', 'Discretionary'],
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
                    d.companyId = $("#queryForm select[name='companyId']" ).val();
                    d.periodId  = $("#queryForm select[name='periodId']" ).val();
                    d.typeId    = $("#typeId" ).val();
                }
            },
            responsive: true,
            paging: true,
            ordering: true,
            info: true,
            searching: true,
            iDisplayLength: 10,
            rowReorder: true,
            pageLength: 50
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
                $('#by-category-title span').text(response.categoryTitle);
                $('#by-month-title span').text(response.monthTitle);
                $('#transactions-title span').text(response.transactionsTitle);
                donut.setData(response.categoryData);
                bar.setData(response.monthData);
                $("#table").dataTable().api().ajax.reload();
                if (response.showCatReset) {
                    $('#resetCat').show();
                } else {
                    $('#resetCat').hide();
                }
                if (response.showMthReset) {
                    $('#resetMth').show();
                } else {
                    $('#resetMth').hide();
                }
            })
            .error(function(x, t, m) {
                notifyError();
            });
        }
        
        $('#resetCat, #resetMth').click(function(e) {
            e.preventDefault();
            getTransactionData("&reset=true");
        });
        
        $('#btnAll').click(function(e) {
            $('#typeId').val('');
            $("#table").dataTable().api().ajax.reload();
        });
        
        $('#btnFixed').click(function(e) {
            $('#typeId').val('1');
             $("#table").dataTable().api().ajax.reload();
       });
        
        $('#btnDiscretionary').click(function(e) {
            $('#typeId').val('2');
            $("#table").dataTable().api().ajax.reload();
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
