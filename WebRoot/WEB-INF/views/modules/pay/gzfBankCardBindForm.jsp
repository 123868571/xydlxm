<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>银行卡绑定</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			if("${oper}" == "modify"){
				
			}
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
		});
	</script>
	<style type="text/css">
	a:link {color: #000;text-decoration:none;}
	a:visited {color: #000}
	a:hover {color: #000;text-decoration:underline;}
	a:active {color: #000}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pay/accountBank/form?id=${accountBank.id}"><shiro:hasPermission name="pay:gzfBankCard:edit">${oper=='modify'?'修改':'添加'}</shiro:hasPermission>银行卡绑定<shiro:lacksPermission name="pay:gzfBankCard:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="accountBank" action="${ctx}/pay/accountBank/add" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">开户行：</label>
			<div class="controls">
				<form:select id="bankId" path="bankId" class="input-xlarge" style="width:285px;">
					<form:options items="${fns:getDictList('bank')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="bankName" htmlEscape="false" maxlength="255" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行卡号：</label>
			<div class="controls">
				<form:input path="cardNo" htmlEscape="false" maxlength="255" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">绑定类型：</label>
			<div class="controls">
				<form:checkboxes items="${fns:getDictList('remind_type')}" path="acctItemTypeList" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pay:gzfBankCard:edit"><input id="btnSubmit" class="btn blue" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn blue" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>