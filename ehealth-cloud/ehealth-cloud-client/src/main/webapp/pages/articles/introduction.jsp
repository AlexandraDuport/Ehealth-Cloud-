<article id="intro-art" data-role="page" data-title="Home">
     <!-- Header -->
	<jsp:include page='art-header.html'/>	

     <div class="ui-content" role="main">
         <div class="wrap">             
             <p>Welcome to eHealth Cloud.....</p>  
             <p>Check your connection with the FHE server now : </p>
             <div id="checkInternet"></div>
              <img src="img/click-here-200px.png" id="checkConnect"  class="mobileicon"/>  
           <!--   <img src="img/touch_64.png" id="checkSQlConnectId"  class="mobileicon"/>  -->
                  
         </div>
         <!-- HEART -->
         <div class="wrap">  
            <div><img src="img/ecg128.png" style="float:right"/> <p>Heart rate</p></div>
           			<div id="heartBeatChart"></div>
		</div>
           <!-- RESPIRATORY -->
           <div class="wrap">             
            <div><img src="img/respiration 128p.png" style="float:right"/> <p>Respiratory Rate</p></div>
           <div id="respiratoryRateChart"></div>
           </div>
           <!-- O2 -->
           <div class="wrap">             
            <div><img src="img/satO2_128p.png" style="float:right"/> <p>O2 Saturation</p></div>
           <div id="SatO2Chart"></div>
           </div>
           <!-- TEMPERATURE -->
           <div class="wrap">             
            <div><img src="img/temp128.png" style="float:right"/> <p>Temperature</p></div>
           <div id="TemperatureChart"></div>
           </div>
     </div>
     

     
    <!-- Footer -->
    <jsp:include page='art-footer.html'/>
    <!-- /Footer -->
 </article>
 
 <script>
function showGraph(data) {
  if (data=="") {
	  log.info('pas de connexion');
    return;
  } 
  if (window.XMLHttpRequest) {
    // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
  } else { // code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function() {
    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
      data=xmlhttp.responseText;
    }
  }
  xmlhttp.open("GET","introduction.jsp?q="+data,true);
  xmlhttp.send();
}
</script>

<!--  <script>
  public function getValeurs(data) {
        $sql = "SELECT valeur"
        . "FROM data ";
        return $this->_db->query($sql)->fetchAll();
    }
 
  </script>
 -->
 
<!-- HEART BEAT CHART -->
   <script> 
     $(function () {
	    $(document).ready(function () {
	        Highcharts.setOptions({
	            global: {
	                useUTC: false
	            }
	        });

	        $('#heartBeatChart').highcharts({
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
	                            //ATTENTION A CHANGER PEUT ETRE
	                                y = Math.random()*10+80;
	                           // y: showGraph('bpm'),
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
	                            y: Math.random()*10+80
		                         //  y: showGraph('bpm')
	                        });
	                    }
	                    return data;
	                }())
	            }]
	        });
	    });
	});</script>
	
	
	
<!-- Respiratory CHART -->
   <script>   $(function () {
	    $(document).ready(function () {
	        Highcharts.setOptions({
	            global: {
	                useUTC: false
	            }
	        });

	        $('#respiratoryRateChart').highcharts({
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
	                                y = Math.random()*10+10;
	                            series.addPoint([x, y], true, true);
	                        }, 1000);
	                    }
	                }
	            },
	            title: {
	                text: 'Respiratory Rate'
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
	                            y: Math.random()*10+10
	                        });
	                    }
	                    return data;
	                }())
	            }]
	        });
	    });
	});</script>	
	


<!-- SatO2 CHART -->
   <script>   $(function () {
	    $(document).ready(function () {
	        Highcharts.setOptions({
	            global: {
	                useUTC: false
	            }
	        });

	        $('#SatO2Chart').highcharts({
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
	                                y = Math.random()*10+90;
	                            series.addPoint([x, y], true, true);
	                        }, 1000);
	                    }
	                }
	            },
	            title: {
	                text: 'O2 Saturation'
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
	                            y: Math.random()*10+90
	                        });
	                    }
	                    return data;
	                }())
	            }]
	        });
	    });
	});</script>	
	
	
	
	<!-- Temperature CHART -->
   <script>   $(function () {
	    $(document).ready(function () {
	        Highcharts.setOptions({
	            global: {
	                useUTC: false
	            }
	        });

	        $('#TemperatureChart').highcharts({
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
	                                y = Math.random()+37;
	                            series.addPoint([x, y], true, true);
	                        }, 1000);
	                    }
	                }
	            },
	            title: {
	                text: 'Temperature'
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
	                            y: Math.random()+37
	                        });
	                    }
	                    return data;
	                }())
	            }]
	        });
	    });
	});</script>	
	