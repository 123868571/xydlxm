	<%@ page contentType="text/html;charset=UTF-8" %>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<%@ page import="com.paopao.hzgzf.modules.pay.common.PaymentConst" %>
	<html>
	<head>
	<title>房租管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			setFirstAndLastMonthDayAndSec("beginTime", "endTime", false);
			
			$("#houseForm").find("div.span3:eq(3)").hover(function(){
				 
				$("#div_hover_show_01").remove();

				var FEE_WATER=$(this).attr("FEE_WATER");
				var FEE_POWER=$(this).attr("FEE_POWER");
				var FEE_GAS=$(this).attr("FEE_GAS");

				var div="<div id='div_hover_show_01' style=' position:absolute;top:90px;left:200px;z-index:9999;background-color:white;border:1px solid #c0c0c0;'>"
					div+="<div style='padding:10px;'>";
				div+="<div> <span>水费</span> <span>"+FEE_WATER+"</span> </div>";
				div+="<div> <span>电费</span> <span>"+FEE_POWER+"</span> </div>";
				div+="<div> <span>燃气费</span> <span>"+FEE_GAS+"</span> </div>";
				div+="</div>";
					div+="</div>";
					
				$(div).appendTo($(this));
			},function(){
				
				$("#div_hover_show_01").remove();
				
			});
			
			$("#search").click(function(){
				getBalanceRecord();
			});
		});
		
		function getBalanceRecord(){
			var url = "${ctx}/pay/balance/listJson";
			var beginTime =$("#beginTime").val();
			var endTime = $("#endTime").val();
			var pageSize = $("#pageSize").val()
			var pageNo = $("#pageNo").val();
			var spePaymentId = $("#spePaymentId").val();
			
			var operType = $("#operType").val();
			var acctItemTypeId=$("#acctItemTypeId").val();
			
			var datas = "accountId=${gzfBalanceRecord.accountId}&spePaymentId="+spePaymentId+"&acctItemTypeId="+acctItemTypeId+"&beginTime="
				+beginTime+"&endTime="+endTime + "&pageSize="+pageSize + "&pageNo="+ pageNo+"&operType="+operType;
			
			postJson(url, datas,function(state, json){
				if (state == "success") {
					var list = json.pageList.list;
					var init_html=$("#T_tabletpl").html();
					var template = Handlebars.compile(init_html);
					var rs_html=template(list);
					$("#resText").html(rs_html);
					$("#pagination").html(json.pageHtml);
					$("#pageSize").val(json.pageList.pageSize);
					$("#pageNo").val(json.pageList.pageNo);
					$("#totalIncomeSpan").html("￥"+json.totalIncome);
					$("#totalExtendSpan").html("￥"+json.totalExtend);
				}else{
					//alert("出错了！！:"+data.msg);
				}
			}); 
		}
		
		
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#search").click();
			return false;
		}
		
		function dealMore(){
			var obj = $("#more");
			if(obj.is(':hidden')){
				obj.show();
			} else{
				obj.hide();
			}
		}
		
	</script>
	<script>
	jQuery(document).ready(function() {
		App.init();
		TableManaged.init();
	});
	$.get("left.html",function(data){
   		$(".page-sidebar").html(data);
		});
	$.get("bottom.html",function(data){
   		$(".bot").html(data);
	});
	//控制选择不同帐户对应的显示不同帐户的数据
	$(function(){
		$(".pricing").eq(0).addClass("pricing-active")
		             .eq(0).prepend("<div class='active-arrow'></div>");
		                  
		var arrowLeft = ($(".pricing").outerWidth()-$(".active-arrow").outerWidth())/2
        $(".active-arrow").css("left",arrowLeft)

		$(".pricing").on("click", function (e) {
			//stopEvent(e);
			$(this).addClass("pricing-active")
			            .prepend("<div class='active-arrow'></div>")
           $(".active-arrow").css("left",arrowLeft)   					           
			$(this).siblings().removeClass("pricing-active")
			                  .children(".active-arrow").hide()
				
        	if ($(this).hasClass("pricing-active")) {
	        	var moneyname = $(".pricing-head").children("h3").eq($(this).index()).text()
	        	$("#billDet").text(moneyname+"明细")
	        	             .prepend("<i class='icon-reorder'></i>");
	        	var phoneNo = $("#phoneNo").val();
	        	if(phoneNo == ""){
	        		return false;
	        	}
	     	    var index = $(this).index();
	     	  	var spePaymentId = "<%=PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL%>";
	        	var acctItemTypeId = "";
	        	if(index==1){
	        		spePaymentId = "<%=PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT%>";
	        		acctItemTypeId = "<%=PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT%>";
	        	}else if(index==2){
	        		spePaymentId = "<%=PaymentConst.SPECIAL_PAYMENT.MANAGEMENT%>";
	        		acctItemTypeId = "<%=PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT%>";
	        	}else if(index==3){
	        		spePaymentId = "<%=PaymentConst.SPECIAL_PAYMENT.WATER%>";
	        		acctItemTypeId = "<%=PaymentConst.ACCT_ITEM_TYPE.WATER%>";
	        	}
	        	$("#spePaymentId").val(spePaymentId);
	        	$("#acctItemTypeId").val(acctItemTypeId);
	        	getBalanceRecord();
	        }
			e.stopPropagation();
        });
			$(".pricing").eq(0).trigger("click");
	});

	//阻止事件冒泡
	function stopEvent(e){
	    if(e && e.stopPropagation ){
	        e.stopPropagation();
	    }else{
	        window.event.cancelBubble = true;
		}
	}
		
	//转入账户窗口
	$(function(){
       	//var $abtn = $(".pricing-footer").children("a").filter(":contains('转入')");
       	var $abtn = $(".pricing-footer").children("a");
       	$abtn.on("click",function(e){
       		//stopEvent(e);
       		e.stopPropagation();
       		var src = $(this).data("url");
       		window.location.href=src;
       	})
	}); 
	
	function changeOperType(val){
		$("#operType").val(val);
		getBalanceRecord();
	}
	
	</script>
	</head>
		<body>
		
		<div id="prePayment" class="row-fluid">
			<div class="span12">
				<!-- BEGIN INLINE NOTIFICATIONS PORTLET-->
				<div class="portlet">
					<div class="portlet-body">
						<div class="row-fluid">
							<!-- Pricing -->
							<div class="row-fluid">
								<form:form id="searchForm" modelAttribute="gzfAccount" action="${ctx}/pay/gzfAccount/getBalanceInfo" method="post" class="form-search">
									<div class="input-append">
										<label class="flo-left">手机号码：</label>
										<form:input path="phoneNo" htmlEscape="false" maxlength="100" class="input-medium" placeholder="请输入手机号查询" style="padding:6px;"/>
									</div>
									<div class="input-append">
										<label class="flo-left">身份证号：</label>
										<form:input path="cardid" htmlEscape="false" maxlength="100" class="input-medium" placeholder="请输入身份证号查询" style="padding:6px;"/>
									</div>
									<div class="input-append">
										<button type="submit" class="btn blue"><i class="icon-search"></i>搜索</button>
									</div>
									<div class="input-append">
										<label class="flo-left">姓名：${gzfAccount.accountName}</label>
										<%-- <form:input path="accountName" htmlEscape="false" maxlength="60" class="input-medium" readonly="true"/> --%>
									</div>
									<%-- <div class="input-append">
										<label class="flo-left">住户地址：</label>
										<form:input path="cardid" htmlEscape="false" maxlength="100" class="input-medium" readonly="true"style="padding:6px;"/>
									</div> --%>
								</form:form>
							</div>
							<sys:message content="${message}"/>
							<div class="row-fluid margin-bottom-40">
								<form:form id="houseForm" style="margin:0" modelAttribute="gzfPayment" action="${ctx}/pay/gzfAccount/payManagementFeeForm?accountId=${gzfBalanceRecord.accountId}&amount=${freeBalance}" method="post" class="form-horizontal">
								<div class="span3 pricing hover-effect">
									<div class="pricing-head">
										<h3 class="moneyName">帐户余额</h3>
										<h4><i>￥</i><fmt:formatNumber value="${freeBalance/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber><span></span></h4>
									</div>
									<div class="pricing-footer">
										<p>您可以执行如下操作：<br>
											1、点击“转入”转入交费金额<br>
											2、点击其它区域查看费用明细
										</p>
										<a href="javascript:;" data-url="${ctx}/pay/gzfAccount/payFreeFee?accountId=${gzfBalanceRecord.accountId}&spePaymentId=<%=PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL %>&phoneNo=${gzfAccount.phoneNo}&custId=${gzfAccount.custId}" class="recharge btn green">
											充值 <i class="m-icon-swapright m-icon-white"></i>
										</a>  
									</div>
								</div>
								<div class="span3 pricing hover-effect">
									<div class="pricing-head">
										<h3>房租费用</h3>
										<h4><i>￥</i><fmt:formatNumber value="${houseRentBalance/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber><span></span></h4>
									</div>
									<div class="pricing-footer">
										<p>您可以执行如下操作：<br>
											1、点击“转入”转入交费金额<br>
											2、点击其它区域查看费用明细
										</p>
										<a href="javascript:;" data-url="${ctx}/pay/gzfAccount/transferForm?destAcctId=${gzfBalanceRecord.accountId}&srcSpecPaymentId=<%=PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL %>&destSpecPaymentId=<%=PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT %>&totalFreeFee=${freeBalance}&phoneNo=${gzfAccount.phoneNo}&custId=${gzfAccount.custId}" class="btn green">
											转入 <i class="m-icon-swapright m-icon-white"></i>
										</a>  
									</div>
								</div>
								<div class="span3 pricing hover-effect">
									<div class="pricing-head pricing-head-active">
										<h3>物业费用</h3>
										<h4><i>￥</i><fmt:formatNumber value="${managementBalance/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber><span></span></h4>
									</div>
									<div class="pricing-footer">
										<p>您可以执行如下操作：<br>
											1、点击“转入”转入交费金额<br>
											2、点击其它区域查看费用明细
										</p>
										<a href="javascript:;" data-url="${ctx}/pay/gzfAccount/transferForm?destAcctId=${gzfBalanceRecord.accountId}&srcSpecPaymentId=<%=PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL %>&destSpecPaymentId=<%=PaymentConst.SPECIAL_PAYMENT.MANAGEMENT %>&totalFreeFee=${freeBalance}&phoneNo=${gzfAccount.phoneNo}&custId=${gzfAccount.custId}" class="btn green">
											转入 <i class="m-icon-swapright m-icon-white"></i>
										</a>  
									</div>
								</div>
								<div class="span3 pricing hover-effect" FEE_WATER="${waterBalance/100}" FEE_POWER="${electricityBalance/100}" FEE_GAS="${naturalgasBalance/100}">
									<div class="pricing-head">
										<h3>水电燃气费</h3>
										<h4><i>￥</i><fmt:formatNumber value="${(waterBalance+electricityBalance+naturalgasBalance)/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber><span></span></h4>
									</div>
									<div class="pricing-footer">
										<p>您可以执行如下操作：<br>
											1、点击“转入”转入交费金额<br>
											2、点击其它区域查看费用明细
										</p>
										<a href="javascript:;" data-url="${ctx}/pay/gzfAccount/transferForm?destAcctId=${gzfBalanceRecord.accountId}&srcSpecPaymentId=<%=PaymentConst.SPECIAL_PAYMENT.NO_SPECIAL %>&destSpecPaymentId=<%=PaymentConst.SPECIAL_PAYMENT.WATER %>&totalFreeFee=${freeBalance}&phoneNo=${gzfAccount.phoneNo}&custId=${gzfAccount.custId}" class="btn green">
											转入 <i class="m-icon-swapright m-icon-white"></i>
										</a>  
									</div>
								</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
				<!-- END INLINE NOTIFICATIONS PORTLET-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN INLINE NOTIFICATIONS PORTLET-->
						<div class="portlet">
							<div class="portlet-title">
								<div id="billDet" class="caption" style="color: rgb(77, 144, 254);"><span>明细</span></div>
							</div>							
							<form:form id="searchFormm" modelAttribute="gzfBalanceRecord" action="${ctx}/pay/balance/listJson" method="post">
							<div class="portlet-title">
								<form:hidden path="accountId"/>
								<form:hidden path="spePaymentId"/>
								<form:hidden path="operType"/>
								<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
								<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
								<div class="billdet-title inline" style="font-size:16px;color: #666;">
									<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
										<form:input path="beginTime" htmlEscape="false" style="width:100px;" class="Wdate m-wrap m-ctrl-medium date-picker" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',realDateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,isShowClear:false})" value="${fns:getDate('yyyy-MM-dd')}"/>
										<span class="add-on"><i class="icon-calendar"></i></span>
									</div>&nbsp;&nbsp;-&nbsp;&nbsp;
									<div style="width:140px;" class="input-append date date-picker margin-r-10" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
										<form:input path="endTime" htmlEscape="false" style="width:100px;" class="Wdate m-wrap m-ctrl-medium date-picke" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',realDateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,isShowClear:false})" value="${fns:getDate('yyyy-MM-dd')}"/>
										<span class="add-on"><i class="icon-calendar"></i></span>
									</div>
										<button type="button" id="search" class="btn blue" style="font-family:'Microsoft YaHei';font-size:12px;"><i class="icon-search"></i> 搜索</button>
									收入：<span style="font-size:16px;color: green;" id="totalIncomeSpan">￥<fmt:formatNumber value="${totalIncome/100}" maxFractionDigits="2"></fmt:formatNumber></span>
									支出：<span style="font-size:16px;color:red;" id="totalExtendSpan">￥<fmt:formatNumber value="${totalExtend/100}" maxFractionDigits="2"></fmt:formatNumber></span>
								</div>
								<div class="tools inline">
									<a href="javascript:;" onclick="dealMore()" class="collapse" style="width:80px;background:url() no-repeat 100%;">更多筛选</a>
								</div>
							</div>
							<div class="portlet-body" id="more">
								<div class="control-group pull-left">
									<label class="control-label inline">资金流向：</label>
									<div class="expense controls inline">
										<label class="">
											<input type="radio"  name="oper" value="" onclick="changeOperType('')" checked="checked"/> 全部
											<%-- <form:radiobutton path="operType" value="" onclick="getBalanceRecord()"/> 全部	 --%>
										</label>
										<label class="">
											<input type="radio"  name="oper" value="1" onclick="changeOperType(1)"/> 收入
											<%-- <form:radiobutton path="operType" value="1" onclick="getBalanceRecord()"/> 收入 --%>
										</label>
										<label class="">
											<input type="radio" name="oper" value="2" onclick="changeOperType(2)"/> 支出
											<%-- <form:radiobutton path="operType" value="2" onclick="getBalanceRecord()"/> 支出 --%>
										</label>
									</div>
								</div>
								&nbsp;
								<div class="expense-type control-group pull-left">
									<label class="control-label inline">支出类型：</label>
									<div class="controls inline">
										<form:select path="acctItemTypeId" onchange="getBalanceRecord()">
											<form:option value="">全部</form:option>
											<form:option value="1">房租费</form:option>
											<form:option value="2">物业费</form:option>
											<form:option value="4">水费</form:option>
											<form:option value="5">电费</form:option>
											<form:option value="6">天燃气费</form:option>
										</form:select>
									</div>
								</div>
							</div>
							</form:form>
								<div class="row-fluid">
									<!-- BILLING DETAILS -->
									<table class="table table-bordered table-striped table-hover">
										<thead>
											<tr>
												<th>流水号</th>
												<th>日期</th>
												<th class="hidden-480">操作类型</th>
												<th class="hidden-480">收入支出(元)</th>
												<th>帐户余额(元)</th>
											</tr>
										</thead>
										<tbody id="resText">
										<c:forEach items="${page.list}" var="gzfBalanceRecord">
											<tr>
												<td>${gzfBalanceRecord.paymentId}</td>
												<td>
													<fmt:formatDate value="${gzfBalanceRecord.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
												</td>
												<td class="hidden-480">
													${fns:getDictLabel(gzfBalanceRecord.optCode, 'optCode', '')}
												</td>
												<td class="hidden-480">
													<fmt:formatNumber value="${gzfBalanceRecord.amount/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber>
												</td>
												<td>
													<fmt:formatNumber value="${gzfBalanceRecord.balance/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber>
												</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
									<div id="pagination" class="pagination">${payList}</div>
								</div>
						</div>
						<!-- END INLINE NOTIFICATIONS PORTLET-->
					</div>
				</div>
			</div>
		</div>
		
		<script id="T_tabletpl" type="text/x-handlebars-template">
			<table>
			{{#each this}}
			<tr>
				<td>
		    		{{paymentId}}
				</td>		    	
				<td>
		    		{{updateDate}}
				</td>
				<td>
					{{optCode}}
				</td>
				<td>
					{{amount}}
				</td>
				<td>
					{{totalBalance}}
				</td>
			</tr>
			{{/each}}
		</table>
		</script>

		</body>
		</html>