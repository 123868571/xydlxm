<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

    <head>

        <meta charset="utf-8" />

        <title>概况总览</title>

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

        <div id="dashboard">

            <!-- BEGIN DASHBOARD STATS -->



            <!-- END DASHBOARD STATS -->

            <div class="clearfix"></div>

            <div class="row-fluid">

                <div class="span6">

                    <!-- BEGIN REGIONAL STATS PORTLET-->




                    <div class="portlet">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-globe"></i>权限总览</div>

                            <div class="tools">

                                <a href="" class="margin-r-20">省</a>
                                <a href="" class="margin-r-20">市</a>
                                <a href="" class="margin-r-20">区</a>

                                <a href="" class="margin-r-20"><i class="icon-arrow-left"></i> 返回</a>
                                <input type="checkbox"/>开关

                            </div>

                            <table id="province" class="table table-striped table-bordered table-hover">
                                <tbody>
                                <c:forEach var="item" items="${areaList}" varStatus="s">
                                    <c:if test="${s.index % 6 eq 0}">
                                    <tr class="odd gradeX">
                                        <td id="area-${item.id}-td"><a id="area-${item.id}" data-id="${item.id}" class="area-control" href="javascript:">${item.name}</a></td>
                                    </c:if>
                                    <c:if test="${s.index % 6 ge 1 and s.index % 6 lt 5}">
                                        <td id="area-${item.id}-td"><a id="area-${item.id}" data-id="${item.id}" class="area-control" href="javascript:">${item.name}</a></td>
                                    </c:if>
                                    <c:if test="${s.index % 6 eq 5}">
                                        <td id="area-${item.id}-td"><a id="area-${item.id}" data-id="${item.id}" class="area-control" href="javascript:">${item.name}</a></td>
                                    </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>



                            <table id="city" class="table table-striped table-bordered table-hover hide">
                                <tbody>
                                    <tr class="odd gradeX">
                                        <td><a href="area_map.html" target="_self">杭州市</a></td>
                                        <td>建德市</td>
                                        <td>富阳市</td>
                                        <td>临安市</td>
                                        <td>宁波市</td>
                                        <td>余姚市</td>
                                    </tr>
                                </tbody>
                            </table>




                            <div class="caption"><i class="icon-globe"></i>操作向导</div>
                            <div class="tools">

                                <a href="" class="reload"></a>

                                <a href="province_map.html" target="_self">省权限</a>
                                <a href="city_map.html" target="_self">市权限</a>
                                <a href="#" target="_self">区权限1空</a>
                                <a href="area_map.html" target="_self">区权限2</a>
                                <a href="province_map.html" target="_self">小区管理空</a>

                            </div>
                        </div>

                        <div class="portlet-body">

                            <div id="region_statistics_loading">

                                <img src="${ctxStatic}/myMedia/image/loading.gif" alt="loading" />

                            </div>

                            <div id="region_statistics_content" style="height: 600px;">

<!--
                                <div id="vmap_world" class="vmaps chart hide"></div>

                                <div id="vmap_usa" class="vmaps chart hide"></div>

                                <div id="vmap_europe" class="vmaps chart hide"></div>

                                <div id="vmap_russia" class="vmaps chart hide"></div>

                                <div id="vmap_germany" class="vmaps chart hide"></div>
-->
                            </div>

                        </div>

                    </div>


                    <!-- END REGIONAL STATS PORTLET-->

                </div>

                <div class="span6">

                    <div class="row-fluid">

                        <c:forEach items="${mainBaseReport}" var="item" varStatus="s" >
                            <c:if test="${s.index % 2 eq 1 or s.index eq 0}" >
                                <div class="span3 responsive " data-tablet="span6" data-desktop="span3">
                            </c:if>
                            <c:if test="${s.index % 2 eq 0 and s.index gt 0}">
                                <div class="span3 responsive " data-tablet="span6 fix-offset" data-desktop="span3">
                            </c:if>
                                <div class="dashboard-stat ${item.className}">
                                    <div class="visual">
                                        <i class="icon-${item.key}"></i>
                                    </div>
                                    <div class="details">
                                        <div id="sumnumber number-${item.key}" class="number">
                                            ${item.value}
                                        </div>
                                        <div class="desc">
                                            ${item.title}
                                        </div>
                                    </div>
                                    <a style="display: ;" class="more" href="${ctx}${item.url}" target="_self">
                                        查看详情 <i class="m-icon-swapright m-icon-white"></i>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>


                    <!-- BEGIN PORTLET-->

                    <div class="portlet solid light-grey bordered">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-user"></i>人员统计</div>

                            <div class="tools">

                            </div>

                        </div>

                        

                        <div class="portlet-body">

                            <div id="site_activities_loading">

                                <img src="${ctxStatic}/myMedia/image/loading.gif" alt="loading" />

                            </div>

                            <div id="site_activities_content" style="width:500px;height:200px;" class="hide">

                                <div id="site_activities" style="height:120px;"></div>

                            </div>

                        </div>

                    </div>

                    <!-- END PORTLET-->

                    <!-- BEGIN PORTLET-->

                    <div class="portlet box purple">

                        <div class="portlet-title">

                            <div class="caption"><i class="icon-bar-chart"></i>缴费统计</div>

                            <div class="tools">

                                <a href="" class="reload"></a>

                            </div>

                        </div>

                        <div class="portlet-body">

                            <div class="row-fluid">

                                <div class="span4">

                                    <div class="easy-pie-chart">

                                        <div class="number transactions"  data-percent="${rentReport.value}"><span>+${rentReport.value}</span>%</div>

                                        <a class="title" href="#">${rentReport.title}<i class="m-icon-swapright"></i></a>

                                    </div>

                                </div>

                                <div class="margin-bottom-10 visible-phone"></div>

                                <div class="span4">

                                    <div class="easy-pie-chart">

                                        <div class="number visits"  data-percent="${propertyReport.value}"><span>+${propertyReport.value}</span>%</div>

                                        <a class="title" href="#">${propertyReport.title}<i class="m-icon-swapright"></i></a>

                                    </div>

                                </div>

                                <div class="margin-bottom-10 visible-phone"></div>

                                <div class="span4">

                                    <div class="easy-pie-chart">

                                        <div class="number bounce"  data-percent="${waterAndElecReport.value}"><span>+${waterAndElecReport.value}</span>%</div>

                                        <a class="title" href="#">${waterAndElecReport.title}<i class="m-icon-swapright"></i></a>

                                    </div>

                                </div>

                            </div>

                        </div>

                    </div>

                    <!-- END PORTLET-->

                </div>

            </div>


            <div class="row-fluid">
            <c:if test="${showAlert}">

                <div class="span6">

                    <!-- BEGIN PORTLET-->

                    <div class="portlet paddingless">

                        <div class="portlet-title line">

                            <div class="caption"><i class="icon-legal"></i>报修未处理</div>

                            <div class="tools">
                                <a href="" class="reload"></a>
                            </div>

                        </div>

                        <div class="portlet-body">

                            <!--BEGIN TABS-->

                            <div class="tab-pane active" id="tab_1_1">

                                <div class="scroller" data-height="170px" data-always-visible="1" data-rail-visible="0">

                                    <ul class="feeds">

                                        <li>

                                            <div class="col1">

                                                <div class="cont">

                                                    <div class="cont-col1">

                                                        <div class="label label-warning">

                                                            <i class="icon-wrench"></i>

                                                        </div>

                                                    </div>

                                                    <div class="cont-col2">

                                                        <div class="desc">

                                                            漓江山水花园如意苑15号2单门口机刷卡不起作用了

                                                        </div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="col2">

                                                <div class="date">

                                                    刚才

                                                </div>

                                            </div>

                                        </li>

                                        <li>

                                            <a href="#">

                                                <div class="col1">

                                                    <div class="cont">

                                                        <div class="cont-col1">

                                                            <div class="label label-important">

                                                                <i class="icon-wrench"></i>

                                                            </div>

                                                        </div>

                                                        <div class="cont-col2">

                                                            <div class="desc">

                                                                新都庄园广安苑1号4单1245室内机电源有问题

                                                            </div>

                                                        </div>

                                                    </div>

                                                </div>

                                                <div class="col2">

                                                    <div class="date">

                                                        20分钟前

                                                    </div>

                                                </div>

                                            </a>

                                        </li>

                                        <li>

                                            <div class="col1">

                                                <div class="cont">

                                                    <div class="cont-col1">

                                                        <div class="label label-info">

                                                            <i class="icon-wrench"></i>

                                                        </div>

                                                    </div>

                                                    <div class="cont-col2">

                                                        <div class="desc">

                                                            东方城市花园红枫苑1号3单2309室内机没声音

                                                        </div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="col2">

                                                <div class="date">

                                                    24分钟前

                                                </div>

                                            </div>

                                        </li>

                                        <li>

                                            <div class="col1">

                                                <div class="cont">

                                                    <div class="cont-col1">

                                                        <div class="label label-info">

                                                            <i class="icon-wrench"></i>

                                                        </div>

                                                    </div>

                                                    <div class="cont-col2">

                                                        <div class="desc">

                                                            东方城市花园红枫苑1号3单2309室内机没声音

                                                        </div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="col2">

                                                <div class="date">

                                                    24分钟前

                                                </div>

                                            </div>

                                        </li>

                                        <li>

                                            <div class="col1">

                                                <div class="cont">

                                                    <div class="cont-col1">

                                                        <div class="label label-info">

                                                            <i class="icon-wrench"></i>

                                                        </div>

                                                    </div>

                                                    <div class="cont-col2">

                                                        <div class="desc">

                                                            东方城市花园红枫苑1号3单2309室内机没声音

                                                        </div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="col2">

                                                <div class="date">

                                                    24分钟前

                                                </div>

                                            </div>

                                        </li>


                                    </ul>

                                </div>

                            </div>

                            <!--END TABS-->

                        </div>

                    </div>

                    <!-- END PORTLET-->

                </div>


                <!-- BEGIN URGENT WARNING -->
                <div class="span6">

                    <!-- BEGIN PORTLET-->

                    <div class="portlet paddingless">

                        <div class="portlet-title line">

                            <div class="caption"><i class="icon-bell"></i>报警提醒</div>

                            <div class="tools">
                                <a href="" class="reload"></a>
                            </div>

                        </div>

                        <div class="portlet-body">

                            <!--BEGIN TABS-->

                            <div class="tab-pane active" id="tab_1_1">

                                <div class="scroller" data-height="170px" data-always-visible="1" data-rail-visible="0">

                                    <ul class="feeds">

                                        <li>

                                            <div class="col1">

                                                <div class="cont">

                                                    <div class="cont-col1">

                                                        <div class="label label-warning">

                                                            <i class="icon-bell"></i>

                                                        </div>

                                                    </div>

                                                    <div class="cont-col2">

                                                        <div class="desc">

                                                            漓江山水花园如意苑15号2单门口机刷卡不起作用了

                                                        </div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="col2">

                                                <div class="date">

                                                    刚才

                                                </div>

                                            </div>

                                        </li>

                                        <li>

                                            <a href="#">

                                                <div class="col1">

                                                    <div class="cont">

                                                        <div class="cont-col1">

                                                            <div class="label label-warning">

                                                                <i class="icon-bell"></i>

                                                            </div>

                                                        </div>

                                                        <div class="cont-col2">

                                                            <div class="desc">

                                                                新都庄园广安苑1号4单1245室内机电源有问题

                                                            </div>

                                                        </div>

                                                    </div>

                                                </div>

                                                <div class="col2">

                                                    <div class="date">

                                                        20分钟前

                                                    </div>

                                                </div>

                                            </a>

                                        </li>

                                        <li>

                                            <div class="col1">

                                                <div class="cont">

                                                    <div class="cont-col1">

                                                        <div class="label label-warning">

                                                            <i class="icon-bell"></i>

                                                        </div>

                                                    </div>

                                                    <div class="cont-col2">

                                                        <div class="desc">

                                                            东方城市花园红枫苑1号3单2309室内机没声音

                                                        </div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="col2">

                                                <div class="date">

                                                    24分钟前

                                                </div>

                                            </div>

                                        </li>


                                        <li>

                                            <div class="col1">

                                                <div class="cont">

                                                    <div class="cont-col1">

                                                        <div class="label label-important">

                                                            <i class="icon-bell"></i>

                                                        </div>

                                                    </div>

                                                    <div class="cont-col2">

                                                        <div class="desc">

                                                            东方城市花园红枫苑1号3单2309室内机没声音

                                                        </div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="col2">

                                                <div class="date">

                                                    24分钟前

                                                </div>

                                            </div>

                                        </li>

                                        <li>

                                            <div class="col1">

                                                <div class="cont">

                                                    <div class="cont-col1">

                                                        <div class="label label-important">

                                                            <i class="icon-bell"></i>

                                                        </div>

                                                    </div>

                                                    <div class="cont-col2">

                                                        <div class="desc">

                                                            东方城市花园红枫苑1号3单2309室内机没声音

                                                        </div>

                                                    </div>

                                                </div>

                                            </div>

                                            <div class="col2">

                                                <div class="date">

                                                    24分钟前

                                                </div>

                                            </div>

                                        </li>

                                    </ul>

                                </div>

                            </div>

                            <!--END TABS-->

                        </div>

                    </div>

                    <!-- END PORTLET-->

                </div>

                <!-- END URGENT WARNING -->
                </c:if>

            </div>

            <div class="clearfix"></div>

        </div>


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

        <script src="${ctxStatic}/myMedia/js/index.js" type="text/javascript"></script>

        <script src="${ctxStatic}/myMedia/lib/echarts/js/echarts.js"></script>

        <!-- END PAGE LEVEL SCRIPTS -->

        <script>

            function showStatisDegree(ele, title, dataX, dataLegend, dataV, type){
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
                            myChartDegree = ec.init(document.getElementById(ele));
                            optionDegree = {
                                title: {
                                    text: title,
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
                                    data: dataLegend
                                },
                                toolbox: {
                                    show: false,
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
                                        name: title,
                                        type: type,
                                        radius: '55%',
                                        center: ['50%', '60%'],
                                        data: dataV
                                    }
                                ]
                            };
                            // 为echarts对象加载数据
                            myChartDegree.setOption(optionDegree);
                        }
                );
            }

            function showMap(){
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
                        var myChartHome = ec.init(document.getElementById('region_statistics_content'));
                        optionHome = {
                            title: {
                                text: '地图',
                                subtext: '',
                                x: 'center'
                            },
                            tooltip: {
                                trigger: 'item'
                            },
                            toolbox: {
                                show: false,
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
                                    name: '省/直辖市',
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
                                }
                            ]
                        };
                        myChartHome.setOption(optionHome);
                        $('#region_statistics_loading').hide();
                        $('#region_statistics_content').show();
                    }
                );
            }

            jQuery(document).ready(function() {
                // 路径配置
                require.config({
                    paths: {
                        echarts: '${ctxStatic}/myMedia/lib/echarts/js'
                    }
                });
                App.init(); // initlayout and core plugins

                Index.init();

                //Index.initJQVMAP(); // init index page's custom scripts

                Index.initCalendar(); // init index page's custom scripts

                // 显示地图
                showMap();



                $.ajax({
                    url: "${ctx}/gzf/gzfReport/personReport/degree",
                    data: {villageId: 0},
                    success: function(data){
                        if (data != null && data.length > 0){
                            var dataX = [];
                            var dataLegend = [];
                            var dataV = [];
                            var type = 'pie';
                            for(var i = 0; i < data.length; i++){
                                dataLegend.push(data[i].title);
                                dataV.push({value: data[i].value, name: data[i].title});
                            }
                            showStatisDegree('site_activities_content', '学历', dataX, dataLegend, dataV, type); // init index page's custom scripts
                            $('#site_activities_loading').hide();
                            $('#site_activities_content').show();
                        }
                    }
                });
/*
                $.ajax({
                    url: "${ctx}/gzf/main/feeReport",
                    data: {},
                    success: function(data){
                        if (data != null && data.length > 0){
                            //Index.initMiniCharts(data); // init index page's custom scripts
                        }
                    }
                });
*/

                Index.initMiniCharts();

//		        //Index.initChat();

                //Index.initDashboardDaterange();

//		        Index.initIntro();

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
                                if ((i % 6) ==0){
                                    tbody += "<tr class='odd gradeX'>";
                                }
                                tbody += "<td id='area-" + item.id + "-td'><a id='area-" + item.id + "' data-id='" +
                                item.id + "' class='area-control' href='javascript:'>" + item.name + "</a>";
                                if (i % 6 == 5 || i == data.length - 1){
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

            function showStatis(ele, title, dataX, dataV){
                // 使用
                require(
                        [
                            'echarts',
                            'echarts/chart/bar',
                        ],
                        function(ec) {
                            // 基于准备好的dom，初始化echarts图表
                            var myChartAge = ec.init(document.getElementById(ele));
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
                                    show: false,
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
                                        data: dataX.data
                                    }
                                ],
                                yAxis: [
                                    {
                                        type: 'value'
                                    }
                                ],
                                series: [
                                    {
                                        name: dataV.title,
                                        type: 'bar',
                                        data: dataV.data
                                    }
                                ]
                            };
                            myChartAge.setOption(optionAge);
                        }
                );
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
        <!-- END JAVASCRIPTS -->
    </body>
</html>
