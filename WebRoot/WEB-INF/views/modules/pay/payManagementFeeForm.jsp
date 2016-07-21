<%@page import="com.paopao.hzgzf.modules.pay.common.PaymentConst"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
	<title>物业缴费</title>
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
			initExpireDate();
			dealConsuption();
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
		
		function pay(){
			var beginDate = "${gzfPayment.effectDateStr}";
			var sum = $("#num").val();
			var date = addMonth(beginDate,sum);
			$("#expireDate").val(date);
			$("#inputForm").submit();
		}
		
		function initExpireDate(){
			dealExpireDate(1);
		}
		
		function dealExpireDate(amount){
			var beginDate = "${gzfPayment.effectDateStr}";
			var date = addMonth(beginDate,amount);
			$("#expireDate").val(date);
		}
		
		function dealFee(){
			sum();
			var a = parseInt($("#num").val());
			dealExpireDate(a);
		}
		
		function disNum(){
			var a = parseInt($("#num").val());
			a--;
			$("#num").val(a);
		/**	if(a==1){
				//$("#minus").attr("disabled","disabled");
				$('#minus').attr('disabled',true);
				$('#minus').click(function(){
					
				});
			}*/
			sum();
			dealExpireDate(a);
		}


		function incNum(){
			var a = parseInt($("#num").val());
			a++;
			$("#num").val(a);
		/**	if (a!=1){
	            //$('#minus').attr('disabled',false);
				$("#minus").removeAttr("disabled");
				$('#minus').click(function(){
					disNum();
				});
				//$("#minus").attr("disabled","");
	        }*/
			sum();
			dealExpireDate(a);
		}

		function sum(){
			var sum = $("#num").val();
		/**	var yuans = document.getElementsByName("yuan");
			var s =0;
			for(var i = 0; i<3;i++){
				s += Number(yuans[i].innerHTML)
			}
			sum.innerHTML = s.toFixed(2);*/
			var feeMonthUnit = "${gzfPayment.amountYuan}";
			var fee = Number(feeMonthUnit * sum).toFixed(2);
			$("#amount").val(fee);
			$("#totalMngFee").html(fee);
			
			var consumUnitPrice = "${consumPrice}";
			var consumFee = 0;
			if(consumUnitPrice != null){
				consumFee = Number(consumUnitPrice * sum).toFixed(2);
				$("#extendAmount").val(consumFee);
				$("#needExtendAmount").html(consumFee);
			}
			if($("#consum").is(':checked')){
				fee = (Number(fee) + Number(consumFee)).toFixed(2);
			}
			$("#needAmount").html(fee);
		}
		
		function dealConsuption(){
			var extendSpec = "";
			if($("#consum").is(':checked')){
				extendSpec = $("#consum").val();
			}
			$("#extendSpecPaymentId").val(extendSpec);
			sum();
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
	<style type="text/css">
		.number-input{width: 30px;text-align: center;}
		.opera-amount-sign{text-decoration: none; color: #000000; cursor: pointer;}
		.no-minus {
		    border-right-color: transparent;
		    left: 0px;
		}
	</style>
</head>
<body>
	<div class="row-fluid ">

		<div class="span12">
			<!-- BEGIN RENT PAYMONEY -->
			<div id="payMoney" class="row-fluid" style="display: block;">

				<div class="span12">

					<div class="portlet">
						<div class="portlet-title bottom-line" style="border-bottom: #f5f5f5 1px solid;">
							<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i>物业缴费</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN CONTNET-->
							<div class="tabbable tabbable-custom tabbable-full-width">
								<div class="tab-pane profile-classic row-fluid">
								<form:form id="inputForm" modelAttribute="gzfPayment" action="${ctx}/pay/gzfPayment/save" method="post" class="form-horizontal">
									<form:hidden path="id"/>
									<form:hidden path="accountId"/>
									<form:hidden path="spePaymentId"/>
									<form:hidden path="effectDateStr"/>
									<form:hidden path="expireDate"/>
									<form:hidden path="phoneNo"/>
									<form:hidden path="custId"/>
									<form:hidden path="extendSpecPaymentId"/>
									<form:hidden path="extendAmount"/>
									<ul class="unstyled span6 " style="margin-left:0;">
										<li class="no-padding no-margin">
											<span class="pad padWidth">物业费开始日期:</span>
											<div class="pad">
												${gzfPayment.effectDateStr}
											</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad padWidth">缴费月份:</span>
											<%-- <div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
												<form:input style="width:80px;" path="expireDate" htmlEscape="false" class="m-wrap m-ctrl-medium date-picker"/>
												<input id="monthAmount" value="1"/>
												<span class="add-on"><i class="icon-calendar"></i></span>
											</div> --%>
											<div class="pad">
												<span  onclick="disNum()" id="minus" class="opera-amount-sign">-</span >
												<!-- <input type="button" id="minus" value="-" onclick="disNum(this)"/> -->
									        	<input id="num" class="number-input" value="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'');dealFee();" onafterpaste="this.value=this.value.replace(/[^\d]/g,'');dealFee();"  />
									        	<span  onclick="incNum()" id="plus" class="opera-amount-sign">+</span >
									        	<!-- <input type="button" id="plus" value="-" onclick="incNum(this)"/> -->
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
											<span class="pad padWidth">月物业金额:</span>

											<div class="pad">
												${gzfPayment.amountYuan}
											</div>
											<span class="pad">元/月</span>
										</li>
										
										<li>
											<span class="pad padWidth">总物业金额:</span>

											<div class="pad" id="totalMngFee">
												${gzfPayment.amountYuan}
											</div>
											<span class="pad">元/月</span>
										</li>
										
										<c:choose>
											<c:when test="${consumPrice != 0 }">
												<li>
													<span class="pad padWidth pull-left">是否交能耗费：</span>
													<div id="cashHold" class="controls" style="margin:5px 0 0;">
														<label  class="">
															<input type="checkbox" id="consum" value="<%=PaymentConst.SPECIAL_PAYMENT.CONSUMPTION %>" onclick="dealConsuption()" checked="checked"/>能耗费
														</label>
													</div>
												</li>
												<li>
													<span class="pad padWidth">月能耗费用:</span>
													<div class="pad">
														${consumPrice}
													</div>
													<span class="pad">元/月</span>
												</li>
												
												<li>
													<span class="pad padWidth">总能耗费用:</span>
													<div class="pad" id="needExtendAmount">
														${gzfPayment.extendAmount}
													</div>
													<span class="pad">元/月</span>
												</li>
											</c:when>
											<c:otherwise></c:otherwise>
										</c:choose>
										
										<li>
											<span class="pad padWidth pull-left">需缴金额：</span>
											<%-- <form:input path="amountYuan" htmlEscape="false" maxlength="10" class="input-xlarge required digits" disabled="disabled"/>元
											<span class="help-inline"><font color="red">*</font> </span> --%>
											<div class="pad" id="needAmount">
												${gzfPayment.amountYuan}
											</div>
											<span class="pad">元</span>
											<form:hidden path="amount"/>
										</li>
										
										<%-- <li>
											<span class="pad padWidth pull-left">实缴金额：</span>
											<form:input path="amount" htmlEscape="false" maxlength="10"  class="input-xlarge required number"/>元
											<span class="help-inline"><font color="red">*</font> </span>
										</li> --%>

										<li>
											<span class="pad padWidth"></span>
											<shiro:hasPermission name="pay:gzfPayment:pay"><button id="btnSubmit" class="btn blue margin-r-20" type="button" onclick="pay()"/><i class="icon-ok"></i>确定</shiro:hasPermission>
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