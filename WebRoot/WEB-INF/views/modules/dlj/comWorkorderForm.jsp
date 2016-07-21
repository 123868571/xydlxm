<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工单分配管理</title>
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
		<li><a href="${ctx}/dlj/comWorkorder/">工单分配列表</a></li>
		<li class="active"><a href="${ctx}/dlj/comWorkorder/form?id=${comWorkorder.id}">工单分配<shiro:hasPermission name="dlj:comWorkorder:edit">${not empty comWorkorder.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dlj:comWorkorder:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="comWorkorder" action="${ctx}/dlj/comWorkorder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="code" value="${code}"/>
		<form:hidden path="wotype" value="0" />
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">派工人签名：</label>
			<div class="controls">
				<form:input path="createBy" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作开始时间：</label>
			<div class="controls">
				<input name="startdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${comWorkorder.startdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					&nbsp;&nbsp;
					<form:input path="starttime" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作结束时间：</label>
			<div class="controls">
				<input name="enddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${comWorkorder.enddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					&nbsp;&nbsp;
					<form:input path="endtime" htmlEscape="false" maxlength="50" length="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作人员：</label>
			<div class="controls">
				<form:input path="staffids" htmlEscape="false" maxlength="500" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">绑定GPS：</label>
			<div class="controls">
				<form:input path="gpsids" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作任务：</label>
			<div class="controls">
				<form:textarea path="ordercontent" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注意事项：</label>
			<div class="controls">
				<form:textarea path="orderwarning" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dlj:comWorkorder:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>