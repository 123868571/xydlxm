<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
	<title>房租缴费</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			<%--$("#bankInfo").hide();--%>
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			$("#expireDate").val(addMonth("${gzfPayment.effectDateStr}",1));
		});
		
		function dealBankInfo(radio){
			if(radio=="1"){
				$("#bankInfo").hide();
				try {
				    parent.iFrameHeight();

				} catch(e) {
				   // alert(e.message);
				}
			}else{
				$("#bankInfo").show();
				try {
				    parent.iFrameHeight();

				} catch(e) {
				   // alert(e.message);
				}
			}
		}
		
		function calculateFee(){
			var date1 = "${gzfPayment.effectDateStr}";
			//var date2 = $("#expireDate").val();
			var date2 = $dp.cal.getDateStr();
			var months = getMonths(date1, date2);
			var days = getDays(date1, date2);
			var feeMonthUnit = "${gzfPayment.amountYuan}";
			var fee = feeMonthUnit;
			if(months < 0){
				fee = 0;
			}else{
				var maxDay = getMaxDay(date2);
				fee = (feeMonthUnit * months + feeMonthUnit * (days/maxDay)).toFixed(2);
			}
			$("#needAmount").html(fee);
			$("#amount").val(fee);
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
	$(function() {
	$("#rentPayment").click(function() {
		$("#payMoney").show()
		$("#payInfo").hide()
	})
	$("#save").click(function() {
		$("#payMoney").hide()
		$("#payInfo").show()
	})

	$(".paydetails").click(function() {
		$("#payDetails").show()
		$("#payInfo").hide()
		return false;
	})


/**	$("#cashHold").click(function(){

		var $cashhold = $("#cashHold input:radio:checked")

			if($cashhold.val() == "1") {
				$("#uploadOrder").hide()
				$("#boundBank").hide()
				$("#printOrder").show()
			}else if($cashhold.val() == "2") {
				$("#uploadOrder").show()
				$("#printOrder").hide()
				$("#boundBank").hide()
			}else if($cashhold.val() == "3") {
				$("#boundBank").show()
				$("#printOrder").hide()
				$("#uploadOrder").hide()
			}

		})*/


	})



	$(function(){

		$("button.cal-month").click(function(){
			$(this).next().show()
			$(this).next().css({"position":"absolute","left":"106px","top":"34px","z-index":"10"})
		})

		var $img = $("button.cal-month").next()
		$img.click(function(){
		$img.hide()
		$("button.cal-month").after("<span>12月</span>")
		})

	})
	</script>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/pay/gzfAccount/houseRent">房租信息</a></li>
		<li class="active"><a href="${ctx}/pay/gzfPayment/houseRentPayForm?id=${gzfPayment.id}">房租缴费</a></li>
	</ul><br/> --%>

	<div class="row-fluid ">

		<div class="span12">
			<!-- BEGIN RENT PAYMONEY -->
			<div id="payMoney" class="row-fluid" style="display: block;">

				<div class="span12">

					<div class="portlet">
						<div class="portlet-title bottom-line" style="border-bottom: #f5f5f5 1px solid;">
							<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i>房租缴费</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN CONTNET-->
							<div class="tabbable tabbable-custom tabbable-full-width">
								<div class="tab-pane profile-classic row-fluid">
								<form:form id="inputForm" modelAttribute="gzfPayment" action="${ctx}/pay/gzfPayment/save" method="post" class="form-horizontal">
									<form:hidden path="id"/>
									<form:hidden path="accountId"/>
									<form:hidden path="custId"/>
									<form:hidden path="spePaymentId"/>
									<form:hidden path="effectDateStr"/>
									<form:hidden path="phoneNo"/>
									<ul class="unstyled span6 " style="margin-left:0;">
										<li class="no-padding no-margin">
											<span class="pad padWidth">房费开始日期:</span>
											<div class="pad">${gzfPayment.effectDateStr}</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad padWidth">本次缴费至:</span>
											<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
												<form:input style="width:80px;" path="expireDate" htmlEscape="false" class="m-wrap m-ctrl-medium date-picker" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyy-MM-dd',onpicked:calculateFee})" value="${fns:formatDate(gzfPayment.expireDate,null)}"/>
												<span class="add-on"><i class="icon-calendar"></i></span>
											</div>
										</li>
										<li>
											<span class="pad padWidth pull-left">缴费方式：</span>

											<div id="cashHold" class="controls" style="margin:5px 0 0;">
												<label  class="">
													<form:radiobutton path="payMethod" name="optionsRadios1" value="1" onclick="dealBankInfo(1)"/>现金
												</label>


												<label class="">
													<form:radiobutton path="payMethod" name="optionsRadios1" value="2" onclick="dealBankInfo(2)"/>银行代扣
												</label>
											</div>
										</li>

										<li class="hide" id="bankInfo">
											<div class="clearfix" style="padding:8px 0; border-bottom:solid 1px #f5f5f5">
												<span class="pad padWidth pull-left">缴费单据号：</span>
												<form:input path="certificateCode" placeholder="请输入缴费单的单据号码" class="m-wrap medium pull-left margin-r-10"/>
												<span class="pad"></span>
											</div>
											<div class="clearfix" style="padding:8px 0;">
												<span class="pad padWidth pull-left">上传缴费单：</span>
												<div class="control-group" style="border:none">
													<div class="controls"  style="position:relative;">
														<form:hidden id="certificateImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
														<sys:ckfinder input="certificateImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
													</div>
												</div>
											</div>
										</li>

										<li>
											<span class="pad padWidth">房租月租金:</span>

											<div class="pad">
												${gzfPayment.amountYuan}
											</div>
											<span class="pad">元/月</span>
										</li>

										<li>
											<span class="pad padWidth">需缴金额：</span>
											<%-- <form:input path="amountYuan" htmlEscape="false" maxlength="10" class="input-xlarge required digits" disabled="disabled"/>元
											<span class="help-inline"><font color="red">*</font> </span> --%>
											<div class="pad" id="needAmount">
												${gzfPayment.amountYuan}
											</div>
											<span class="pad">元</span>
											<form:hidden path="amount"/>
										</li>
										
										<%-- <li>
											<span class="pad padWidth">实缴金额：</span>
											<form:input path="amount" htmlEscape="false" maxlength="10" class="input-xlarge required number"/>元
											<span class="help-inline"><font color="red">*</font> </span>
										</li> --%>

										<li>
											<span class="pad padWidth"></span>
											<shiro:hasPermission name="pay:gzfPayment:pay"><button id="btnSubmit" class="btn blue margin-r-20" type="submit"/><i class="icon-ok"></i>确定</shiro:hasPermission>
											<!-- <button id="btnSubmit" class="btn blue margin-r-20" type="submit"/><i class="icon-ok"></i>确定 -->
											<button id="btnCancel" class="btn" type="button" onclick="history.go(-1)"/>取消
										</li>

									</ul>
									</form:form>
									<div id="printOrder" class="span6">
									<div class="item">
										<a class="fancybox-button" data-rel="fancybox-button" title="合同" href="${ctxStatic}/media/image/property-bill.jpg">
											<div align="center" class="zoom">
												<img src="${ctxStatic}/media/image/property-bill.jpg" width="360px" alt="Photo" />
												<div class="zoom-icon"></div>
											</div>
										</a>
										<div class="details">点击浏览合同</div>
									</div>

									<div align="center">
										<button onclick="" type="submit" class="btn green"><i class="icon-print"></i> 打印缴费单</button>
									</div>

								</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
			<!-- END RENT PAYMONEY -->
			<div id="payDetails" class="row-fluid hide">
				<div class="span12">
					<div class="portlet">
						<div class="portlet-title bottom-line" style="border-bottom: #f5f5f5 1px solid;">
							<div class="caption"><i class="icon-edit"></i>缴费详情</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN CONTNET-->
							<div class="tabbable tabbable-custom tabbable-full-width">
								<div class="tab-pane profile-classic row-fluid">
									<ul class="unstyled span6 ">
										<li>
											<span class="pad padWidth">操作时间:</span>
											<div class="pad">2015-12-04 14:32</div>
											<span class="pad"></span>
										</li>
										<li class="no-padding no-margin">
											<span class="pad padWidth">缴费开始日期:</span>
											<div class="pad">2015-11-20</div>
											<span class="pad"></span>
										</li>
										<li class="no-padding no-margin">
											<span class="pad padWidth">缴费截止日期:</span>
											<div class="pad">2016-11-20</div>
											<span class="pad"></span>
										</li>
										<li class="no-padding no-margin">
											<span class="pad padWidth">缴费方式:</span>
											<div class="pad">现金</div>
											<span class="pad"></span>
										</li>
										<li class="no-padding no-margin">
											<span class="pad padWidth">缴费总金额:</span>
											<div class="pad">12,000.00</div>
											<span class="pad">元</span>
										</li>
									</ul>
									<div class="span6">
										<div class="item">
											<a class="fancybox-button" data-rel="fancybox-button" title="合同" href="media/image/property-bill.jpg">
												<div align="center" class="zoom">
													<img src="media/image/property-bill.jpg" width="360px" alt="Photo" />
													<div class="zoom-icon"></div>
												</div>
											</a>
											<div class="details">点击浏览合同</div>
										</div>
										<div align="center">
											<button onclick="" type="submit" class="btn green"><i class="icon-print"></i> 打印缴费单</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<sys:message content="${message}"/>

</body>
</html>