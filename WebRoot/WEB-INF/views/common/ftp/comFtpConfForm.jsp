<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ftp服务器路径配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ftp/comFtpConf/">ftp服务器路径配置列表</a></li>
		<li class="active"><a href="${ctx}/ftp/comFtpConf/form?id=${comFtpConf.id}">ftp服务器路径配置<shiro:hasPermission name="ftp:comFtpConf:edit">${not empty comFtpConf.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ftp:comFtpConf:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="comFtpConf" action="${ctx}/ftp/comFtpConf/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">ftp编码：</label>
			<div class="controls">
				<form:input path="ftpCode" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子类型：</label>
			<div class="controls">
				<form:input path="subClass" htmlEscape="false" maxlength="50" class="input-xlarge " placeholder="不同的业务模块上传到位置不一样"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">远程路径：</label>
			<div class="controls">
				<form:input path="remotePath" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ftp:comFtpConf:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>