<#import "../parts/common.ftl" as c>
<@c.page>
   <@c.admin "Статистика магазина">

      <div class="container" style="text-align: center">
         <div id="sum_chart" style="width: 900px; height: 500px; margin: 1rem auto 3rem"></div>
         <div id="category_chart" style="width: 900px; height: 500px; margin: 0 auto"></div>

      </div>

   </@c.admin>
   <@c.script>
      <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
      <script type="text/javascript">
         google.charts.load('current', {'packages':['line']});
         google.charts.load('current', {'packages':['corechart']});
         google.charts.setOnLoadCallback(drawChart);

         function drawChart() {

            var data = new google.visualization.DataTable();
            data.addColumn('number', 'ноябрь - декабрь');
            data.addColumn('number', 'Реализованные заказы');
            data.addColumn('number', 'Отменённые заказы');
            data.addColumn('number', 'Заказы в обработке');

            data.addRows([${sumStatistics}]);

            var options = {
               chart: {
                  title: 'Суммарная стоимость заказов по дням',
                  subtitle: 'в рублях'
               },
               width: 950,
               height: 500,
               axes: {
                  x: {
                     0: {side: 'bottom'}
                  }
               }
            };


            var chart = new google.charts.Line(document.getElementById('sum_chart'));
            chart.draw(data, google.charts.Line.convertOptions(options));

            var data = google.visualization.arrayToDataTable([
               ['Категория', 'Количество заказанных товаров'],
               ${categoryStatistics}
            ]);

            var options = {
               title: 'Доли заказанных товаров по категориям'
            };

            var chart = new google.visualization.PieChart(document.getElementById('category_chart'));

            chart.draw(data, options);
         }
      </script>
   </@c.script>
</@c.page>