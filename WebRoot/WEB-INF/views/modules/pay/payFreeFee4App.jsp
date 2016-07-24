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
		});

		function dealBankInfo(radio){
			if(radio=="1"){
				$("#bankInfo").hide();
			}else{
				$("#bankInfo").show();
			}
		}
	</script>
	</head>
	<body>
		<div id="recharge" class="row-fluid">
			<div class="span12">
				<div class="portlet">
					<div class="portlet-title bottom-line" style="border-bottom: #f5f5f5 1px solid;">
						<div class="caption"><i class="icon-edit"></i>帐户充值</div>
					</div>
					<div class="portlet-body form">
						<div class="tabbable tabbable-custom tabbable-full-width">
							<div class="tab-pane profile-classic row-fluid">
							<form:form id="inputForm" modelAttribute="gzfPayment" action="${ctx}/pay/gzfAccountPublic/pay" method="post" class="form-horizontal">
								<form:hidden path="accountId"/>
								<form:hidden path="custId"/>
								<form:hidden path="spePaymentId"/>
								<form:hidden path="phoneNo"/>
								<ul class="unstyled span6 ">
									<li>
										<span class="pad padWidth">充值时间：</span>
										<span class="pad">${fns:getDate("yyyy-MM-dd HH:mm")}</span>
									</li>
									<li>
										<span class="pad padWidth pull-left">充值方式：</span>
										<div class="controls" style="margin:5px 0 0;">
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
										<span class="pad padWidth pull-left">充值金额：</span>
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