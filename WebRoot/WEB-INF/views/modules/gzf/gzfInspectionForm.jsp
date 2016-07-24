<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡查设备管理</title>
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
		<li><a href="${ctx}/gzf/gzfInspection/">巡查设备列表</a></li>
		<li class="active"><a href="${ctx}/gzf/gzfInspection/form?id=${gzfInspection.id}">巡查设备<shiro:hasPermission name="gzf:gzfInspection:edit">${not empty gzfInspection.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfInspection:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gzfInspection" action="${ctx}/gzf/gzfInspection/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:select path="gzfVillageId" class="input-large">
					<form:option value="" label=""/>
					<form:options items="${gzfVillageList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<a href="${ctx}/gzf/gzfInspectionCategory/form?id=${gzfInspection.id}">添加类别</a>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡查类别：</label>
			<div class="controls">
				<form:checkboxes path="gzfInspectionCategoryId" items="${gzfInspectionCategoryList}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡查类目：</label>
			<div class="controls">
				<form:checkboxes path="gzfInspectionDetailIds" items="${gzfInspectionDetailList}" itemLabel="name" itemValue="id" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfInspection:edit"><input id="btnSubmit" class="btn blue" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>