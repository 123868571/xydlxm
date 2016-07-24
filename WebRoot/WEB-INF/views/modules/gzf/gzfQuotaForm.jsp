<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>额度限制管理</title>
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
		<li><a href="${ctx}/gzf/gzfQuota/">额度限制列表</a></li>
		<li class="active"><a href="${ctx}/gzf/gzfQuota/form?id=${gzfQuota.id}">额度限制<shiro:hasPermission name="gzf:gzfQuota:edit">${not empty gzfQuota.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfQuota:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gzfQuota" action="${ctx}/gzf/gzfQuota/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">小区名称：</label>
			<div class="controls">
				<form:select path="gzfVillageId" class="input-large required">
					<form:option value="" label=""/>
					<form:options items="${gzfVillageList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">苑名称：</label>
			<div class="controls">
				<form:select id="gzfPalacesId" path="gzfPalacesId" class="input-large required">
					<form:option value="" label=""/>
					<form:options items="${gzfPalacesList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房租：</label>
			<div class="controls">
				<form:input path="maxRent" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline">个月<font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">水费：</label>
			<div class="controls">
				<form:input path="maxWater" htmlEscape="false" class="input-xlarge required number"/>
				<span class="help-inline">元<font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电费：</label>
			<div class="controls">
				<form:input path="maxElec" htmlEscape="false" class="input-xlarge required number"/>
				<span class="help-inline">元<font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfQuota:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>