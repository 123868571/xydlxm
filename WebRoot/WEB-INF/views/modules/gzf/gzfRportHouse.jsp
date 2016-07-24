<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

    <head>

        <meta charset="utf-8" />

        <title>房源报表</title>

        <meta content="width=device-width, initial-scale=1.0" name="viewport" />

        <meta content="" name="description" />

        <meta content="" name="author" />

        <!-- BEGIN GLOBAL MANDATORY STYLES -->

        <link href="${ctxStatic}/myMedia/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/bootstrap-tree.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/daterangepicker.css" rel="stylesheet" type="text/css"/>
        <link href="${ctxStatic}/myMedia/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/style-metro.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/style.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/style-responsive.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

        <link href="${ctxStatic}/myMedia/css/uniform.default.css" rel="stylesheet" type="text/css"/>


        <!-- END GLOBAL MANDATORY STYLES -->

        <!-- BEGIN PAGE LEVEL STYLES -->


        <!-- END PAGE LEVEL STYLES -->

        <link rel="shortcut icon" href="${ctxStatic}/myMedia/image/favicon.ico" />

    </head>

    <!-- END HEAD -->

    <!-- BEGIN BODY -->

    <body>

        <!-- BEGIN PAGE HEADER-->



        <!-- END PAGE HEADER-->

        <div class="page-container row-fluid">

                    <!-- BEGIN SIDEBAR -->



                    <!-- BEGIN PAGE -->

                    <div class="page-content" style="margin-left: 0;">

                        <!-- BEGIN PAGE CONTAINER-->

                        <div class="container-fluid">

                            <!-- BEGIN PAGE HEADER-->



                            <!-- END PAGE HEADER-->

                            <!-- BEGIN PAGE CONTENT-->

                            <div class="row-fluid ">

                                <div class="span12">

                                    <div class="portlet-body">

                                        <div class="row-fluid">

                                            <div class="span12">

                                                <!--BEGIN CONTENT-->

                                                <div class="tab-pane active" id="tab_1_1">

                                                    <div id="infomanage-1-add">

                                                        <div class="controls pull-left margin-r-20">

                                                            <div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">

                                                                <input style="width:100px;height: 30px;line-height: 30px;" id="start-date" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" value="2015-11-15" />
                                                                <span class="add-on" style="height: 15px;line-height: 15px;padding-top: 9px;"><i class="icon-calendar"></i></span>

                                                            </div>
                                                            <div style="width:140px;" class="input-append date date-picker" data-date="2016-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">

                                                                <input style="width:100px;height: 30px;line-height: 30px;" id="end-date" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" value="2016-11-15" />
                                                                <span class="add-on" style="height: 15px;line-height: 15px;padding-top: 9px;"><i class="icon-calendar"></i></span>

                                                            </div>

                                                            <div style="width:140px;" class="input-append">
                                                                <input id="show-report" style="height: 30px;line-height: 30px;padding-top: 0px" type="button" value="生成报表" />
                                                            </div>

                                                            <div class="dropd margin-r-10  pull-left">
                                                                <select id="gzf-villages" style="width:130px;">
                                                                   <option value="0">所有小区</option>
                                                                   <c:forEach var="item" items="${gzfVillageList}" varStatus="s">
                                                                       <option value="${item.id}">${item.name}</option>
                                                                   </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="dropd margin-r-10  pull-left">
                                                                <select id="gzf-palaces" style="width:130px;">
                                                                    <option value="0">所有苑</option>
                                                                </select>
                                                            </div>

                                                        </div>

                                                        <div class="portlet-title pull-right">
                                                            <a href="javascript:tab1show();" class="btn blue hide"><i class="icon-plus"></i> 导入数据</a>
                                                            <a href="#" class="btn green hide"><i class="icon-share"></i> 导出Excel</a>
                                                            <a href="#" class="btn red hide"><i class=" icon-print"></i> 打印</a>
                                                        </div>

                                                        <div id="info_manage_1">
                                                            <div class="portlet box blue" style="overflow: hidden;background-color:white !important; ">

                                                                <div class="portlet-title">

                                                                    <div class="caption"><i class="icon-bar-chart"></i>房源统计表显示</div>

                                                                    <div class="tools">

                                                                        <a href="javascript:;" class="reload"></a>

                                                                    </div>

                                                                </div>

                                                                <div class="portlet-body">

                                                                    <h4>显示统计表图</h4>

                                                                    <!--<div id="donut" class="chart">-->
                                                                    <div id="main-age" class="main-age" style="height:200px"></div>
                                                                    <!--</div>-->
                                                                </div>

                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div id="infomanage-1-add">

                                                        <div class="controls pull-left margin-r-20">

                                                            <div style="width:;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
                                                                <select name="report-year" id="report-year" class="report-year">
                                                                    <option value="">- 请选择年份 -</option>
                                                                </select>
                                                            </div>

                                                            <div style="width:140px;" class="input-append">
                                                                <input id="show-report-controll" style="height: 30px;line-height: 30px;padding-top: 0px" type="button" value="生成报表" />
                                                            </div>

                                                            <div class="dropd margin-r-10  pull-left">
                                                                <select id="gzf-villages-select" style="width:130px;">
                                                                   <option value="0">所有小区</option>
                                                                   <c:forEach var="item" items="${gzfVillageList}" varStatus="s">
                                                                       <option value="${item.id}">${item.name}</option>
                                                                   </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="dropd margin-r-10  pull-left">
                                                                <select id="gzf-palaces-select" style="width:130px;">
                                                                    <option value="0">所有苑</option>
                                                                </select>
                                                            </div>

                                                        </div>

                                                        <div class="portlet-title pull-right">
                                                            <a href="javascript:tab1show();" class="btn blue hide"><i class="icon-plus"></i> 导入数据</a>
                                                            <a href="#" class="btn green hide"><i class="icon-share"></i> 导出Excel</a>
                                                            <a href="#" class="btn red hide"><i class=" icon-print"></i> 打印</a>
                                                        </div>

                                                        <div id="info_manage_1">
                                                            <div class="portlet box blue" style="overflow: hidden;background-color:white !important; ">

                                                                <div class="portlet-title">

                                                                    <div class="caption"><i class="icon-bar-chart"></i>房屋出租统计表显示</div>

                                                                    <div class="tools">

                                                                        <a href="javascript:;" class="reload"></a>

                                                                    </div>

                                                                </div>

                                                                <div class="portlet-body" id="house-report-detail">

                                                                    <h4>显示统计表图</h4>

                                                                    <!--<div id="donut" class="chart">-->

                                                                    <!--</div>-->
                                                                </div>

                                                            </div>
                                                        </div>

                                                    </div>

                                                </div>

                                                <!--END CONTENT-->

                                            </div>

                                            <div class="space10 visible-phone"></div>

                                        </div>

                                    </div>

                                    <!-- END INLINE TABS PORTLET-->

                                </div>

                            </div>

                            <!-- END PAGE CONTENT-->

                        </div>

                        <!-- END PAGE CONTAINER-->

                    </div>

                    <!-- END PAGE -->

                </div>

                <!-- END CONTAINER -->


        <!-- BEGIN FOOTER -->

        <div class="">



        </div>

        <!-- END FOOTER -->

        <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

        <!-- BEGIN CORE PLUGINS -->

        <script src="${ctxStatic}/myMedia/js/jquery-1.10.1.min.js" type="text/javascript"></script>

        <script src="${ctxStatic}/jquery/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

        <!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

        <script src="${ctxStatic}/myMedia/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

        <script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>

        <!--[if lt IE 9]>

        <script src="${ctxStatic}/myMedia/js/excanvas.min.js"></script>

        <script src="${ctxStatic}/myMedia/js/respond.min.js"></script>

        <![endif]-->

        <script src="${ctxStatic}/myMedia/js/jquery.slimscroll.min.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.blockui.min.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.cookie.min.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.uniform.min.js" type="text/javascript" ></script>

        <!-- END CORE PLUGINS -->

        <!-- BEGIN PAGE LEVEL PLUGINS -->

        <script src="${ctxStatic}/myMedia/js/jquery.vmap.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.vmap.russia.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.vmap.world.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.vmap.europe.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.vmap.germany.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.vmap.usa.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.vmap.sampledata.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.flot.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.flot.resize.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.pulsate.min.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/date.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/moment.min.js" type="text/javascript"></script>
        <script src="${ctxStatic}/myMedia/js/daterangepicker.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.gritter.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/fullcalendar.min.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.easy-pie-chart.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/jquery.sparkline.min.js" type="text/javascript"></script>

        <!-- END PAGE LEVEL PLUGINS -->

        <!-- BEGIN PAGE LEVEL SCRIPTS -->

        <script src="${ctxStatic}/myMedia/js/app.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/js/index.js" type="text/javascript"></script>


        <script src="${ctxStatic}/myMedia/lib/echarts/js/echarts.js"></script>
        <script src="${ctxStatic}/myMedia/js/custom.js"></script>

        <!-- END PAGE LEVEL SCRIPTS -->

        <script>

            function showStatisAge(ele, title, dataX, dataLegend, dataV, type){
                // 使用
                require(
                        [
                            'echarts',
                            'echarts/chart/pie',
                            'echarts/chart/bar',
                            'echarts/chart/funnel',
                            'echarts/chart/line',
                            'echarts/chart/gauge',
                            'echarts/chart/map'
                        ],
                        function(ec) {
                            // 基于准备好的dom，初始化echarts图表
                            myChartAge = ec.init(document.getElementById(ele));
                            optionAge = {
                                title: {
                                    text: title,
                                    subtext: '',
                                    x: 'center'
                                },
                                tooltip: {
                                    trigger: 'axis'
                                },
                                toolbox: {
                                    show: true,
                                    feature: {
                                        mark: {show: true},
                                        dataView: {show: true, readOnly: false},
                                        magicType: {show: true, type: ['line', 'bar']},
                                        restore: {show: true},
                                        saveAsImage: {show: true}
                                    }
                                },
                                calculable: true,
                                xAxis: [
                                    {
                                        type: 'category',
                                        data: dataX
                                    }
                                ],
                                yAxis: [
                                    {
                                        type: 'value'
                                    }
                                ],
                                series: [
                                    {
                                        name: title,
                                        type: 'bar',
                                        itemStyle: {
                                            normal: {
                                                label: {
                                                    show: true
                                                }
                                            }
                                        },
                                        data: dataV
                                    }
                                ]
                            };
                            myChartAge.setOption(optionAge);
                        }
                );
            }

            function showStatisHouse(ele, title, data, type){
                // 使用
                require(
                        [
                            'echarts',
                            'echarts/chart/pie',
                            'echarts/chart/bar',
                            'echarts/chart/funnel',
                            'echarts/chart/line',
                            'echarts/chart/gauge',
                            'echarts/chart/map'
                        ],
                        function(ec) {
                            // 基于准备好的dom，初始化echarts图表
                            myChartAge = ec.init(document.getElementById(ele));
                            optionAge = {
                                 title : {
                                        text: data[0].title,
                                        subtext: ''
                                },
                                tooltip : {
                                    trigger: 'axis',
                                    formatter: function (params,ticket,callback) {
                                        var res = params[0].name;
                                        for (var i = 0, l = params.length; i < l; i++) {
                                            res += '<br/>' + params[i].seriesName + ' : ' + params[i].value;
                                        }
                                        var rate = 0;
                                        var total = parseInt(params[0].data);
                                        var rent = parseInt(params[1].data);
                                        if (params.length > 2){
                                            total = parseInt(params[2].data);
                                            rent = parseInt(params[0].data);
                                        }
                                        if (total > 0){
                                            rate = (rent / total).toFixed(2) * 100;
                                        }else {
                                            rate = 0;
                                        }
                                        res += "<br />出租率: " + rate + "%";
                                        setTimeout(function (){
                                            // 仅为了模拟异步回调
                                            callback(ticket, res);
                                        }, 1000)
                                        return 'loading';
                                    }
                                },
                                legend: {
                                    data: [data[0].name,data[1].name]
                                },
                                toolbox: {
                                    show : true,
                                    feature : {
                                        mark : {show: true},
                                        dataView : {show: true, readOnly: false},
                                        magicType : {show: true, type: ['line', 'bar']},
                                        restore : {show: true},
                                        saveAsImage : {show: true}
                                    }
                                },
                                calculable : true,
                                xAxis : [
                                    {
                                        type : 'category',
                                        data : data[0].xAxis
                                    }
                                ],
                                yAxis : [
                                    {
                                        type : 'value'
                                    }
                                ],
                                series : [
                                    {
                                        name:data[0].name,
                                        type:'bar',
                                        itemStyle: {
                                            normal: {
                                                label: {
                                                    show: true
                                                }
                                            }
                                        },
                                        data: data[0].data
                                    },
                                    {
                                        name:data[1].name,
                                        type:'bar',
                                        itemStyle: {
                                            normal: {
                                                label: {
                                                    show: true
                                                }
                                            }
                                        },
                                        data: data[1].data
                                    }
                                ]
                            };
                            if (data.length > 2){
                                optionAge.series = [
                                   {
                                       name:data[0].name,
                                       type:'bar',
                                       itemStyle: {
                                           normal: {
                                               label: {
                                                   show: true
                                               }
                                           }
                                       },
                                       data: data[0].data
                                   },
                                   {
                                       name:data[1].name,
                                       type:'bar',
                                       itemStyle: {
                                           normal: {
                                               label: {
                                                   show: true
                                               }
                                           }
                                       },
                                       data: data[1].data
                                   },{
                                        name:data[2].name,
                                        type:'bar',
                                        itemStyle: {
                                            normal: {
                                                label: {
                                                    show: true
                                                }
                                            }
                                        },
                                        data: data[2].data
                                    }
                               ];
                               optionAge.legend = {
                                    data: [data[0].name,data[1].name,data[2].name]
                               };
                            }
                            myChartAge.setOption(optionAge);
                        }
                );
            }

            function houseReport(villageId, palacesId, startDate, endDate){
                var type = "bar";
                $.ajax({
                    url: "${ctx}/gzf/gzfReport/houseReport/house",
                    data: {villageId: villageId, palacesId:palacesId, startDate:startDate, endDate:endDate},
                    success: function(data){
                        showStatisHouse('main-age', '房屋数', data, type); // init index page's custom scripts
                    }
                });
            }

            function buildingReportByYear(villageId, palacesId, year){
                $.ajax({
                    url: "${ctx}/gzf/gzfReport/houseReport/building",
                    data: {villageId: villageId, palacesId:palacesId, year:year},
                    success: function(data){
                        $("#house-report-detail").empty();
                        var type = "bar";
                        var eleIdPrefix = "main-age-";
                        for (var i = 0; i < data.length; i++){
                            var item = '<div id="' + eleIdPrefix + i + '" class="main-age" style="height:200px"></div>';
                            $("#house-report-detail").append(item);
                        }

                        for(var i = 0; i < data.length; i++){
                            var dataValues = data[i];
                            var eleId = eleIdPrefix + i;
                            showStatisHouse(eleId, '详情', dataValues, type); // init index page's custom scripts
                        }
                    }
                });
            }

            $(document).ready(function() {
                // 路径配置
                require.config({
                    paths: {
                        echarts: '${ctxStatic}/myMedia/lib/echarts/js'
                    }
                });
                var villageId = $("#gzf-villages").val();
                var palacesId = $("#gzf-palaces").val();
                var startDate = $("#start-date").val();
                var endDate = $("#end-date").val();
                houseReport(villageId, 0, startDate, endDate);

                $("#sh").click(function() {
                    $("#sh").css("background", "#FFB848")
                    $("<i class='icon-ok'></i>").appendTo("td#sh");

                    $("#sumnumber").text("100")
                });

                $("#js").click(function() {
                    $("#js").css("background", "#FFB848")
                    $("<i class='icon-ok'></i>").appendTo("td#js");
                    $("#sumnumber").text("500")
                });
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                var villageUrl = "${ctx}/gzf/gzfPalaces/getGzfPalacesByVillage";
                $("#gzf-villages").change(function(){
                      var villageId = $("#gzf-villages").val();
                      $.ajax({
                            url : villageUrl,
                            data : {villageId:villageId},
                            async : true
                      }).done(function(data){
                          var obj = eval(data);
                          jQuery("#gzf-palaces").empty();
                          $("#gzf-palaces").append('<option value="0">所有苑</option>');
                          for(var key in obj){
                              $("#gzf-palaces").append('<option value="' + obj[key].id + '">' + obj[key].name + '</option>');
                          }
                      });
                });
                $("#gzf-palaces").change(function(){
                    var gzfPalacesId = $("#gzf-palaces").val();
                });
                $("#show-report").click(function(){
                    var villageId = $("#gzf-villages").val();
                    var palacesId = $("#gzf-palaces").val();
                    var startDate = $("#start-date").val();
                    var endDate = $("#end-date").val();
                    houseReport(villageId, 0, startDate, endDate);
                });
                initReportPalaces(villageUrl);
                $("#show-report-controll").click(function(){
                    var villageId = $("#gzf-villages-select").val();
                    var palacesId = $("#gzf-palaces-select").val();
                    var year = $("#report-year").val();
                    buildingReportByYear(villageId, palacesId, year);
                });
            });
        </script>
        <!-- END JAVASCRIPTS -->
    </body>
</html>
