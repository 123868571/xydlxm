<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

    <head>

        <meta charset="utf-8" />

        <title>人员报表</title>

        <meta content="width=device-width, initial-scale=1.0" name="viewport" />

        <meta content="" name="description" />

        <meta content="" name="author" />

        <!-- BEGIN GLOBAL MANDATORY STYLES -->

        <link href="${ctxStatic}/myMedia/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/style-metro.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/style.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/style-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="${ctxStatic}/myMedia/css/switch.css" rel="stylesheet" type="text/css"/>
        <link href="${ctxStatic}/myMedia/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

        <link href="${ctxStatic}/myMedia/css/uniform.default.css" rel="stylesheet" type="text/css"/>


        <!-- END GLOBAL MANDATORY STYLES -->

        <!-- BEGIN PAGE LEVEL STYLES -->

        <link href="${ctxStatic}/myMedia/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/daterangepicker.css" rel="stylesheet" type="text/css" />

        <link href="${ctxStatic}/myMedia/css/fullcalendar.css" rel="stylesheet" type="text/css"/>

        <link href="${ctxStatic}/myMedia/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>

        <link href="${ctxStatic}/myMedia/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>

        <!-- END PAGE LEVEL STYLES -->

        <link rel="shortcut icon" href="${ctxStatic}/myMedia/image/favicon.ico" />

    </head>

    <!-- END HEAD -->

    <!-- BEGIN BODY -->

    <body>

        <!-- BEGIN PAGE HEADER-->

        <div class="row-fluid">

            <div class="span12">

                <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                <h6></h6>

                <ul class="breadcrumb">

                    <%--<li>--%>

                        <%--<i class="icon-home"></i>--%>

                        <%--<a href="index.html">概况总览</a>--%>

                    <%--</li>--%>

                    <li class="pull-right no-text-shadow">

                        <div id="dashboard-report-range" class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive" data-tablet="" data-desktop="tooltips" data-placement="top" >

                            <i class="icon-calendar"></i>

                            <span></span>

                            <i class="icon-angle-down"></i>

                        </div>

                    </li>

                </ul>

                <!-- END PAGE TITLE & BREADCRUMB-->

            </div>

        </div>

        <!-- END PAGE HEADER-->

        <div class="page-container row-fluid">

                    <!-- BEGIN SIDEBAR -->

                    <div class="page-sidebar nav-collapse collapse">

                        <!-- BEGIN SIDEBAR MENU -->
                        <!--left.html-->
                        <!-- END SIDEBAR MENU -->

                    </div>

                    <!-- BEGIN PAGE -->

                    <div class="page-content">

                        <!-- BEGIN PAGE CONTAINER-->

                        <div class="container-fluid">

                            <!-- BEGIN PAGE HEADER-->

                            <div class="row-fluid">

                                <div class="span12">

                                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                                    <h6></h6>

                                    <ul class="breadcrumb">

                                        <li>

                                            <i class="icon-home"></i>

                                            <a id="home" href="#"></a>

                                            <i class="icon-angle-right"></i>

                                        </li>

                                        <li>

                                            <a id="guide_data_report"></a>

                                            <i class="icon-angle-right"></i>

                                        </li>

                                        <li>
                                            <a id="guide_staff_report" href=""></a>

                                    </ul>

                                    <!-- END PAGE TITLE & BREADCRUMB-->

                                </div>

                            </div>

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

                                                                <input style="width:100px;" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" value="2015-11-15" /><span class="add-on"><i class="icon-calendar"></i></span>

                                                            </div>-
                                                            <div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">

                                                                <input style="width:100px;" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" value="2015-11-15" /><span class="add-on"><i class="icon-calendar"></i></span>

                                                            </div>

                                                            <div class="dropd margin-r-10  pull-left">
                                                                <select style="width:130px;">
                                                                    <option>所有小区</option>
                                                                    <option>漓江水花园</option>
                                                                    <option>新都庄园</option>
                                                                </select>
                                                            </div>
                                                            <div class="dropd margin-r-10  pull-left">
                                                                <select style="width:130px;">
                                                                    <option>所有苑</option>
                                                                    <option>如意苑</option>
                                                                    <option>兴达苑</option>
                                                                </select>
                                                            </div>

                                                        </div>

                                                        <div class="portlet-title pull-right">
                                                            <a href="javascript:tab1show();" class="btn blue"><i class="icon-plus"></i> 导入数据</a>
                                                            <a href="#" class="btn green"><i class="icon-share"></i> 导出Excel</a>
                                                            <a href="#" class="btn red"><i class=" icon-print"></i> 打印</a>
                                                        </div>

                                                        <div id="info_manage_1">
                                                            <div class="portlet box blue">

                                                                <div class="portlet-title">

                                                                    <div class="caption"><i class="icon-bar-chart"></i>人员统计表显示</div>

                                                                    <div class="tools">

                                                                        <a href="javascript:;" class="reload"></a>

                                                                    </div>

                                                                </div>

                                                                <div class="portlet-body">

                                                                    <h4>显示统计表图</h4>

                                                                    <!--<div id="donut" class="chart">-->
                                                                    <div id="main-degree" class="main-degree" style="height:200px"></div>
                                                                    <div id="main-age" class="main-age" style="height:200px"></div>
                                                                    <div id="main-sex" class="main-sex" style="height:200px"></div>
                                                                    <div id="main-career" class="main-career" style="height:200px"></div>
                                                                    <!--<div id="main-home" class="main-home" style="height:500px"></div>-->
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

        <!-- END PAGE LEVEL SCRIPTS -->

        <script>

            jQuery(document).ready(function() {

                App.init(); // initlayout and core plugins

                Index.init();

                Index.initJQVMAP(); // init index page's custom scripts

                Index.initCalendar(); // init index page's custom scripts

                $.ajax({
                    url: "${ctx}/gzf/main/personReport",
                    data: {},
                    success: function(data){
                        if (data != null && data.length > 0){
                            var activities = [];
                            for(var i = 0; i < data.length; i++){
                                activities.push([data[i].key, data[i].rate]);
                            }
                            Index.initCharts(activities); // init index page's custom scripts
                        }
                    }
                });

                $.ajax({
                    url: "${ctx}/gzf/main/feeReport",
                    data: {},
                    success: function(data){
                        if (data != null && data.length > 0){
                            //Index.initMiniCharts(data); // init index page's custom scripts
                        }
                    }
                });

                Index.initMiniCharts();

//		   Index.initChat();



                Index.initDashboardDaterange();

//		   Index.initIntro();

            });

            function chooseArea(input){
                var pid = $(input).data("id");
                $.ajax({
                    url: "${ctx}/gzf/main/areaList",
                    data: {pid: pid},
                    success: function(data){
                        if (data != null && data.length > 0){
                            $("#city").empty();
                            var tbody = "<tbody>";
                            for(var i = 0; i < data.length; i++){
                                var item = data[i];
                                if (i ==0){
                                    tbody += "<tr class='odd gradeX'>";
                                }
                                tbody += "<td id='area-" + item.id + "-td'><a id='area-" + item.id + "' data-id='" +
                                item.id + "' class='area-control' href='javascript:'>" + item.name + "</a></td>";
                                if (i % 6 == 5){
                                    tbody += "</td></tr>";
                                }else {
                                    tbody += "</td>";
                                }
                            }
                            tbody += "</tbody>";
                            $("#city").html(tbody);
                            $("#province").hide();
                            $("#city").show();
                            $(".area-control").click(function(){
                                chooseArea(this);
                            });
                        }
                    }
                });
            }


            $(document).ready(function() {
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


                $(".area-control").click(function(){
                    chooseArea(this);
                });
            });
        </script>
        <script type="text/javascript">
            // 路径配置
            require.config({
                paths: {
                    echarts: '${ctxStatic}/myMedia/lib/echarts/js'
                }
            });
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
                        var myChartDegree = ec.init(document.getElementById('main-degree'));
                        var myChartAge = ec.init(document.getElementById('main-age'));
                        var myChartSex = ec.init(document.getElementById('main-sex'));
                        var myChartCareer = ec.init(document.getElementById('main-career'));
                        //var myChartHome = ec.init(document.getElementById('main-home'));
                        optionDegree = {
                            title: {
                                text: '学历',
                                subtext: '',
                                x: 'center'
                            },
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                x: 'left',
                                data: ['硕士及以上', '本科', '大专', '其他']
                            },
                            toolbox: {
                                show: true,
                                feature: {
                                    mark: {show: true},
                                    dataView: {show: true, readOnly: false},
                                    magicType: {
                                        show: true,
                                        type: ['pie', 'funnel'],
                                        option: {
                                            funnel: {
                                                x: '25%',
                                                width: '50%',
                                                funnelAlign: 'left',
                                                max: 1548
                                            }
                                        }
                                    },
                                    restore: {show: true},
                                    saveAsImage: {show: true}
                                }
                            },
                            calculable: true,
                            series: [
                                {
                                    name: '学历',
                                    type: 'pie',
                                    radius: '55%',
                                    center: ['50%', '60%'],
                                    data: [
                                        {value: 135, name: '硕士及以上'},
                                        {value: 1548, name: '本科'},
                                        {value: 335, name: '大专'},
                                        {value: 234, name: '其他'}
                                    ]
                                }
                            ]
                        };
                        // 为echarts对象加载数据
                        myChartDegree.setOption(optionDegree);

                        optionAge = {
                            title: {
                                text: '年龄',
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
                                    data: ['0-17岁', '18-23岁', '24-29岁', '30-35岁', '35以上']
                                }
                            ],
                            yAxis: [
                                {
                                    type: 'value'
                                }
                            ],
                            series: [
                                {
                                    name: '年龄段人数',
                                    type: 'bar',
                                    data: [200, 250, 1500, 1200, 1000]
                                }
                            ]
                        };
                        myChartAge.setOption(optionAge);

                        optionSex = {
                            title: {
                                text: '男女比例',
                                subtext: '',
                                x: 'center'
                            },
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                x: 'left',
                                data: ['男', '女']
                            },
                            toolbox: {
                                show: true,
                                feature: {
                                    mark: {show: true},
                                    dataView: {show: true, readOnly: false},
                                    magicType: {
                                        show: true,
                                        type: ['pie'],
                                        option: {
                                            funnel: {
                                                x: '25%',
                                                width: '50%',
                                                funnelAlign: 'left',
                                                max: 1548
                                            }
                                        }
                                    },
                                    restore: {show: true},
                                    saveAsImage: {show: true}
                                }
                            },
                            calculable: true,
                            series: [
                                {
                                    name: '男女比例',
                                    type: 'pie',
                                    radius: ['50%', '70%'],
                                    itemStyle: {
                                        normal: {
                                            label: {
                                                show: false
                                            },
                                            labelLine: {
                                                show: false
                                            }
                                        },
                                        emphasis: {
                                            label: {
                                                show: true,
                                                position: 'center',
                                                textStyle: {
                                                    fontSize: '30',
                                                    fontWeight: 'bold'
                                                }
                                            }
                                        }
                                    },
                                    data: [
                                        {value: 580, name: '男'},
                                        {value: 400, name: '女'}
                                    ]
                                }
                            ]
                        };
                        myChartSex.setOption(optionSex);

                        optionCareer = {
                            title: {
                                text: '职业',
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
                                    data: ['公务员', '企事业', '个体户', '其他']
                                }
                            ],
                            yAxis: [
                                {
                                    type: 'value'
                                }
                            ],
                            series: [
                                {
                                    name: '职业',
                                    type: 'line',
                                    data: [800, 1300, 300, 600]
                                }
                            ]
                        };
                        myChartCareer.setOption(optionCareer);

                        optionHome = {
                            title: {
                                text: '籍贯',
                                subtext: '',
                                x: 'center'
                            },
                            tooltip: {
                                trigger: 'item'
                            },
                            legend: {
                                orient: 'vertical',
                                x: 'left',
                                data: ['男', '女']
                            },
                            dataRange: {
                                min: 0,
                                max: 2500,
                                x: 'left',
                                y: 'bottom',
                                text: ['高', '低'], // 文本，默认为数值文本
                                calculable: true
                            },
                            toolbox: {
                                show: true,
                                orient: 'vertical',
                                x: 'right',
                                y: 'center',
                                feature: {
                                    mark: {show: true},
                                    dataView: {show: true, readOnly: false},
                                    restore: {show: true},
                                    saveAsImage: {show: true}
                                }
                            },
                            roamController: {
                                show: true,
                                x: 'right',
                                mapTypeControl: {
                                    'china': true
                                }
                            },
                            series: [
                                {
                                    name: '男',
                                    type: 'map',
                                    mapType: 'china',
                                    roam: false,
                                    itemStyle: {
                                        normal: {label: {show: true}},
                                        emphasis: {label: {show: true}}
                                    },
                                    data: [
                                        {name: '北京', value: Math.round(Math.random() * 1000)},
                                        {name: '天津', value: Math.round(Math.random() * 1000)},
                                        {name: '上海', value: Math.round(Math.random() * 1000)},
                                        {name: '重庆', value: Math.round(Math.random() * 1000)},
                                        {name: '河北', value: Math.round(Math.random() * 1000)},
                                        {name: '河南', value: Math.round(Math.random() * 1000)},
                                        {name: '云南', value: Math.round(Math.random() * 1000)},
                                        {name: '辽宁', value: Math.round(Math.random() * 1000)},
                                        {name: '黑龙江', value: Math.round(Math.random() * 1000)},
                                        {name: '湖南', value: Math.round(Math.random() * 1000)},
                                        {name: '安徽', value: Math.round(Math.random() * 1000)},
                                        {name: '山东', value: Math.round(Math.random() * 1000)},
                                        {name: '新疆', value: Math.round(Math.random() * 1000)},
                                        {name: '江苏', value: Math.round(Math.random() * 1000)},
                                        {name: '浙江', value: Math.round(Math.random() * 1000)},
                                        {name: '江西', value: Math.round(Math.random() * 1000)},
                                        {name: '湖北', value: Math.round(Math.random() * 1000)},
                                        {name: '广西', value: Math.round(Math.random() * 1000)},
                                        {name: '甘肃', value: Math.round(Math.random() * 1000)},
                                        {name: '山西', value: Math.round(Math.random() * 1000)},
                                        {name: '内蒙古', value: Math.round(Math.random() * 1000)},
                                        {name: '陕西', value: Math.round(Math.random() * 1000)},
                                        {name: '吉林', value: Math.round(Math.random() * 1000)},
                                        {name: '福建', value: Math.round(Math.random() * 1000)},
                                        {name: '贵州', value: Math.round(Math.random() * 1000)},
                                        {name: '广东', value: Math.round(Math.random() * 1000)},
                                        {name: '青海', value: Math.round(Math.random() * 1000)},
                                        {name: '西藏', value: Math.round(Math.random() * 1000)},
                                        {name: '四川', value: Math.round(Math.random() * 1000)},
                                        {name: '宁夏', value: Math.round(Math.random() * 1000)},
                                        {name: '海南', value: Math.round(Math.random() * 1000)},
                                        {name: '台湾', value: Math.round(Math.random() * 1000)},
                                        {name: '香港', value: Math.round(Math.random() * 1000)},
                                        {name: '澳门', value: Math.round(Math.random() * 1000)}
                                    ]
                                },
                                {
                                    name: '女',
                                    type: 'map',
                                    mapType: 'china',
                                    itemStyle: {
                                        normal: {label: {show: true}},
                                        emphasis: {label: {show: true}}
                                    },
                                    data: [
                                        {name: '北京', value: Math.round(Math.random() * 1000)},
                                        {name: '天津', value: Math.round(Math.random() * 1000)},
                                        {name: '上海', value: Math.round(Math.random() * 1000)},
                                        {name: '重庆', value: Math.round(Math.random() * 1000)},
                                        {name: '河北', value: Math.round(Math.random() * 1000)},
                                        {name: '安徽', value: Math.round(Math.random() * 1000)},
                                        {name: '新疆', value: Math.round(Math.random() * 1000)},
                                        {name: '浙江', value: Math.round(Math.random() * 1000)},
                                        {name: '江西', value: Math.round(Math.random() * 1000)},
                                        {name: '山西', value: Math.round(Math.random() * 1000)},
                                        {name: '内蒙古', value: Math.round(Math.random() * 1000)},
                                        {name: '吉林', value: Math.round(Math.random() * 1000)},
                                        {name: '福建', value: Math.round(Math.random() * 1000)},
                                        {name: '广东', value: Math.round(Math.random() * 1000)},
                                        {name: '西藏', value: Math.round(Math.random() * 1000)},
                                        {name: '四川', value: Math.round(Math.random() * 1000)},
                                        {name: '宁夏', value: Math.round(Math.random() * 1000)},
                                        {name: '香港', value: Math.round(Math.random() * 1000)},
                                        {name: '澳门', value: Math.round(Math.random() * 1000)}
                                    ]
                                }
                            ]
                        };
                        //myChartHome.setOption(optionHome);
                    }
            );
        </script>
        <!-- END JAVASCRIPTS -->
    </body>
</html>
