	<%@ page contentType="text/html;charset=UTF-8" %>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<%@ page import="com.paopao.hzgzf.modules.pay.common.PaymentConst" %>
	<html>
	<head>
	<title>帐户充值</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					//比较余额和转入金额
					var totalFreeFee = "${totalFreeFee}";
					var amount = $("#amount").val();
					if(parseInt(amount) > parseInt(totalFreeFee)){
						$("#messageBox").text("转入金额不能大于帐户余额!");
						return false;
					}
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
			if("${gzfAcctTransfer.destSpecPaymentId}"=="<%=PaymentConst.SPECIAL_PAYMENT.WATER%>"){
				$("input[name=specId][value=1]").attr("checked",'checked');
				$("#destSpecPaymentId").val("<%=PaymentConst.SPECIAL_PAYMENT.WATER%>");
				$("#billDet").text("转入水费费用").prepend("<i class='icon-edit'></i>");
				$("#feeType").show();
			}else if("${gzfAcctTransfer.destSpecPaymentId}"=="<%=PaymentConst.SPECIAL_PAYMENT.MANAGEMENT%>"){
				$("#billDet").text("转入物业费费用").prepend("<i class='icon-edit'></i>");
			}
		});
	
		function changeSpecId(o){
			var feeName = "房租";
			if(o=="1"){
				$("#destSpecPaymentId").val("<%=PaymentConst.SPECIAL_PAYMENT.WATER%>");
				feeName="水费";
			}else if(o=="2"){
				$("#destSpecPaymentId").val("<%=PaymentConst.SPECIAL_PAYMENT.ELECTRICITY%>");
				feeName="电费";
			}else if(o=="3"){
				$("#destSpecPaymentId").val("<%=PaymentConst.SPECIAL_PAYMENT.NATURALGAS%>");
				feeName="天然气费";
			}
			$("#billDet").text("转入"+feeName+"费用").prepend("<i class='icon-edit'></i>");
		}
	</script>
	</head>
	<body>
		<div id="chargeInto" class="row-fluid">
			<div class="span12">
				<div class="portlet">
					<div class="portlet-title bottom-line" style="border-bottom: #f5f5f5 1px solid;">
						<div id="billDet" class="caption"><i class="icon-edit"></i>转入房租费用</div>
					</div>
					<div class="portlet-body form">
						<div class="tabbable tabbable-custom tabbable-full-width">
							<div class="tab-pane profile-classic row-fluid">
							<form:form id="inputForm" modelAttribute="gzfAcctTransfer" action="${ctx}/pay/gzfPayment/transfer" method="post" class="form-horizontal">
								<form:hidden path="srcAcctId"/>
								<form:hidden path="destAcctId"/>
								<form:hidden path="srcSpecPaymentId"/>
								<form:hidden path="destSpecPaymentId"/>
								<form:hidden path="optCode"/>
								<form:hidden path="phoneNo"/>
								<form:hidden path="custId"/>
								<ul class="unstyled span6 ">
									<li>
									<span class="pad padWidth">转入时间：</span>
									<span class="pad">${fns:getDate("yyyy-MM-dd HH:mm")}</span>
									</li>
									<li>
										<span class="pad padWidth">帐户余额：</span>
										<div class="pad padFont"><fmt:formatNumber value="${gzfAcctTransfer.totalFreeFee/100 }" maxFractionDigits="2" pattern=""></fmt:formatNumber></div>
										<span class="pad">元</span>
									</li>
									<li id="feeType" style="display:none">
										<span class="pad padWidth pull-left">费用类型：</span>
										<div class="controls" style="margin:5px 0 0;">
											<label  class="">
												<input type="radio" name="specId" value="1" onclick="changeSpecId(1)"/>水费
											</label>
											<label class="">
												<input type="radio" name="specId" value="2" onclick="changeSpecId(2)"/>电费
											</label>
											<label class="">
												<input type="radio" name="specId" value="3" onclick="changeSpecId(3)"/>天然气费
											</label>
										</div>
									</li>
									<li>
										<span class="pad padWidth pull-left">转入金额：</span>
										<form:input path="amount" placeholder="请输入缴费金额 " class="m-wrap small pull-left margin-r-10 required number"/>
										<span class="pad">元</span>
									</li>
									<li>
										<span class="pad padWidth"></span>
										<button id="save" type="submit" class="btn blue margin-r-20 "><i class="icon-ok"></i>确定</button>
										<button id="cancleInto" type="button" class="btn" onclick="history.go(-1)">取消</button>
									</li>
								</ul>
							</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<sys:message content="${message}"/>
	</body>
	</html>