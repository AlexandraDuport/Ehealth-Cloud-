<!-- New HTML5 article tag -->

<article id="analyse-form-fill" data-role="page" data-theme="a">
<%-- 	<!-- Header -->
    <jsp:include page='art-header.html'/>	
	<!-- /Header -->
	 --%>
 	<div class="ui-content" role="main">
        
      
           <!-- History -->
           <div class="wrap">             
            <div><img src="img/Material Icons_e0dd(0)_128.png" style="float:right"/><p>History Chart</p></div>
            <div id="HistoryChart" style="height:50%;"></div>
           </div>
     </div>


     <!-- Footer -->
    <jsp:include page='art-footer.html'/>
    <!-- /Footer -->
</article>
<!-- HEART BEAT CHART -->
   <script>   $(function () {
	    $(document).ready(function () {
	        Highcharts.setOptions({
	            global: {
	                useUTC: false
	            }
	        });

	        $('#heartBeatChartHistory').highcharts({
	            chart: {
	                type: '',
	                animation: Highcharts.svg, // don't animate in old IE
	                marginRight: 10,
	                events: {
	                    load: function () {

	                        // set up the updating of the chart each second
	                        var series = this.series[0];
	                        setInterval(function () {
	                            var x = (new Date()).getTime(), // current time
	                                y = Math.random();
	                            series.addPoint([x, y], true, true);
	                        }, 1000);
	                    }
	                }
	            },
	            title: {
	                text: 'Heart Beat'
	            },
	            xAxis: {
	                type: 'datetime',
	                tickPixelInterval: 150
	            },
	            yAxis: {
	                title: {
	                    text: 'Value'
	                },
	                plotLines: [{
	                    value: 0,
	                    width: 1,
	                    color: '#808080'
	                }]
	            },
	            tooltip: {
	                formatter: function () {
	                    return '<b>' + this.series.name + '</b><br/>' +
	                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
	                        Highcharts.numberFormat(this.y, 2);
	                }
	            },
	            legend: {
	                enabled: false
	            },
	            exporting: {
	                enabled: false
	            },
	            series: [{
	                name: 'Random data',
	                data: (function () {
	                    // generate an array of random data
	                    var data = [],
	                        time = (new Date()).getTime(),
	                        i;

	                    for (i = -19; i <= 0; i += 1) {
	                        data.push({
	                            x: time + i * 1000,
	                            //ATTRAPER LES DONNEES DU JSON ICI
	                            y: Math.random()
	                        });
	                    }
	                    return data;
	                }())
	            }]
	        });
	    });
	});</script>
	
	
	
<!-- Anomaly number CHART -->
   <script>  $(function () {
	    $('#HistoryChart').highcharts({
	        chart: {
	            type: 'bar'
	        },
	        title: {
	            text: 'Anomaly number by month'
	        },
	        xAxis: {
	            categories: ['December', 'November', 'October', 'September', 'August'],
	            title: {
	                text: null
	            }
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: 'number of anomalies',
	                align: 'high'
	            },
	            labels: {
	                overflow: 'justify'
	            }
	        },
	        plotOptions: {
	            bar: {
	                dataLabels: {
	                    enabled: true
	                }
	            }
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'top',
	            x: -40,
	            y: 80,
	            floating: true,
	            borderWidth: 1,
	            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
	            shadow: true
	        },
	        credits: {
	            enabled: false
	        },
	        series: [{
	            name: 'Heart rate',
	            data: [2, 5, 2, 0, 0]
	        }, {
	            name: 'Respiration Rate',
	            data: [3, 6, 1, 1, 2]
	        }, {
	            name: 'O2 Saturation',
	            data: [1, 4, 5, 6, 5]
	        }, {
	            name: 'Temperature',
	            data: [5, 6, 5, 4, 5]
	        }]
	    });
	});</script>	
