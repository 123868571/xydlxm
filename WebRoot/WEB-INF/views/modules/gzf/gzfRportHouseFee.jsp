<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ page import="com.paopao.hzgzf.modules.pay.common.PaymentConst" %>
<html>

    <head>

        <meta charset="utf-8" />

        <title>缴费报表 - 房租报表</title>

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
                                                <form:form id="searchForm" modelAttribute="gzfAccount" action="${ctx}/gzf/gzfReport/fee/houseDetail" method="post" class="breadcrumb form-search">
                                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                                    <div id="infomanage-1-add">

                                                        <div class="controls pull-left margin-r-20" style="margin-bottom: 10px">

                                                            <div style="width:140px;" class="input-append date date-picker" data-date="${startDate}" data-date-format="yyyy-mm-dd" data-date-viewmode="years">

                                                                <input style="width:100px;height: 30px;line-height: 30px;" id="start-date" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" name="startDate" value="${startDate}" />
                                                                <span class="add-on" style="height: 15px;line-height: 15px;padding-top: 9px;"><i class="icon-calendar"></i></span>

                                                            </div>
                                                            <div style="width:140px;" class="input-append date date-picker" data-date="${endDate}" data-date-format="yyyy-mm-dd" data-date-viewmode="years">

                                                                <input style="width:100px;height: 30px;line-height: 30px;" id="end-date" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" name="endDate" value="${endDate}" />
                                                                <span class="add-on" style="height: 15px;line-height: 15px;padding-top: 9px;"><i class="icon-calendar"></i></span>

                                                            </div>

                                                            <div style="width:140px;" class="input-append">
                                                                <input id="show-report" style="height: 30px;line-height: 30px;padding-top: 0px" type="submit" value="确定" />
                                                            </div>

                                                            <div class="dropd margin-r-10  pull-left">
                                                                <select id="gzf-villages" name="villageId" style="width:130px;">
                                                                   <option value="0">所有小区</option>
                                                                   <c:forEach var="item" items="${gzfVillageList}" varStatus="s">
                                                                       <option <c:if test="${item.id eq villageId}">selected="selected"</c:if> value="${item.id}">${item.name}</option>
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
                                                            <a id="btnExport" data-type="1" href="javascript:" target="_blank" class="btn green"><i class="icon-share"></i> 导出Excel</a>
                                                            <a href="#" class="btn red hide"><i class=" icon-print"></i> 打印</a>
                                                        </div>

                                                        <div id="info_manage_1">
                                                            <div class="portlet box blue">

                                                                <div class="portlet-title">

                                                                    <div class="caption"><i class="icon-bar-chart"></i>房租缴费详情</div>

                                                                    <div class="tools">

                                                                        <a href="javascript:;" class="reload"></a>

                                                                    </div>

                                                                </div>

                                                                <div class="portlet-body">

                                                                    <h4>房租缴费</h4>

                                                                    <table id="contentTable" class="table table-striped table-bordered table-condensed">
                                                                        <thead>
                                                                            <tr>
                                                                                <th style="text-align:center;">房屋地址</th>
                                                                                <th style="text-align:center;">住房姓名</th>
                                                                                <th style="text-align:center;">住房电话</th>
                                                                                <th style="text-align:center;">房租状态</th>
                                                                                <th style="text-align:center;">欠费金额</th>
                                                                                <th style="text-align:center;">房租到期日期</th>
                                                                                <!-- <shiro:hasPermission name="pay:gzfBankCard:edit"><th>操作</th></shiro:hasPermission> -->
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        <c:forEach items="${page.list}" var="gzfAccount">
                                                                            <tr>
                                                                                <td style="text-align:center;">
                                                                                    <c:choose>
                                                                                        <c:when test="${gzfAccount.housePerson.gzfHouseInfo.gzfPalaces.name !=null}">
                                                                                            ${gzfAccount.housePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name} ${gzfAccount.housePerson.gzfHouseInfo.gzfPalaces.name}苑 ${gzfAccount.housePerson.gzfHouseInfo.buildNum}楼 ${gzfAccount.housePerson.gzfHouseInfo.unit}单元 ${gzfAccount.housePerson.gzfHouseInfo.room}号
                                                                                        </c:when>
                                                                                        <c:otherwise></c:otherwise>
                                                                                    </c:choose>
                                                                                </td>
                                                                                <td style="text-align:center;">
                                                                                    ${gzfAccount.accountName}
                                                                                </td >
                                                                                <td style="text-align:center;">
                                                                                    ${gzfAccount.phoneNo}
                                                                                </td>
                                                                                <td style="text-align:center;">
                                                                                    <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
                                                                                    <c:choose>
                                                                                        <c:when test="${gzfAccount.oweMoney}">
                                                                                            已欠费 - ${gzfAccount.overDay}天
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            正常
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </td>
                                                                                <td style="text-align:center;">
                                                                                    <fmt:formatNumber value="${gzfAccount.fee/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber>
                                                                                </td>
                                                                                <td style="text-align:center;">
                                                                                    <fmt:formatDate value="${gzfAccount.updateDate}" pattern="yyyy-MM-dd"/>
                                                                                </td>
                                                                                <shiro:hasPermission name="pay:gzfPayment:view">
                                                                                <!--
                                                                                    <td style="text-align:center;">
                                                                                        <c:choose>
                                                                                            <c:when test="${gzfAccount.id != null}">
                                                                                                <a href="${ctx}/pay/gzfPayment/list?accountId=${gzfAccount.id}&spePaymentId=<%=PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT%>">缴费历史</a>
                                                                                            </c:when>
                                                                                            <c:otherwise></c:otherwise>
                                                                                        </c:choose>
                                                                                    </td>
                                                                                    -->
                                                                                </shiro:hasPermission>
                                                                            </tr>
                                                                        </c:forEach>
                                                                        </tbody>
                                                                    </table>
                                                                    <div class="pagination">${page}</div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    </form:form>


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

        <script type="text/javascript">
            $(document).ready(function() {
                $("#gzf-villages").change(function(){
                      var villageId = $("#gzf-villages").val();
                      $.ajax({
                            url : "${ctx}/gzf/gzfPalaces/getGzfPalacesByVillage",
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
                // 导出
                $("#btnExport").click(function(){
                    var type = $(this).data("type");
                    var that = this;
                    top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
                        if(v=="ok"){
                            $("#searchForm").attr("action","${ctx}/gzf/gzfReport/export?type=" + type);
                            $("#searchForm").submit();
                            //window.open("${ctx}/gzf/gzfReport/export?type=" + type);
                        }
                    },{buttonsFocus:1});
                    top.$('.jbox-body .jbox-icon').css('top','55px');
                });
            });
        </script>
        <script>
            function doSubmit(){
                $("#searchForm").attr("action","${ctx}/gzf/gzfReport/fee/houseDetail");
                $("#searchForm").submit();
            }
            function page(n,s){
                if(n) $("#pageNo").val(n);
                if(s) $("#pageSize").val(s);
                $("#searchForm").attr("action","${ctx}/gzf/gzfReport/fee/houseDetail");
                $("#searchForm").submit();
                return false;
            }
        </script>
        <!-- END JAVASCRIPTS -->
    </body>
</html>
