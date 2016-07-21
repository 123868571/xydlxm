<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>维修修管理管理</title>
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
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/gzf/gzfMaintenance/">维修修管理列表</a></li>
		<li class="active"><a href="${ctx}/gzf/gzfMaintenance/form?id=${gzfMaintenance.id}">维修修管理<shiro:hasPermission name="gzf:gzfMaintenance:edit">${not empty gzfMaintenance.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfMaintenance:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/> --%>
	
	<div id="repairInfo" class="row-fluid">
		<div class="span12">				
			<div class="portlet">
				<div class="portlet-title bottom-line" style="border-bottom: #f5f5f5 1px solid;">
					<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i>维修详情</div>
				</div>
				<div class="portlet-body">
					<div class="span6">								
						<form:form id="inputForm" modelAttribute="gzfMaintenance" action="${ctx}/gzf/gzfMaintenance/save" method="post" class="form-horizontal">
							<form:hidden path="id"/>
							<sys:message content="${message}"/>
							<div class="control-group ">
								<label class="control-label ">报修内容：</label>
								<div class="controls ">
									<span class="pad ">
										<form:input path="gzfRepairManagementId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
									</span>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">维修状态：</label>
								<div class="controls ">
									<span class="pad ">
										<form:input path="type" htmlEscape="false" maxlength="64" class="input-xlarge "/>
									</span>
								</div>
							</div>
							<%--<div class="control-group ">
								<label class="control-label ">维修单位：</label>
								<div class="controls ">
									<span class="pad ">
										<form:input path="repairNum" htmlEscape="false" maxlength="11" class="input-xlarge "/>
									</span>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">报修时间</label>
								<div class="controls ">
									<div class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
										<input name="time" type="text" readonly="readonly" maxlength="20" class="m-wrap m-ctrl-medium date-picker Wdate "
											value="<fmt:formatDate value="${gzfRepairManagement.time}" pattern="yyyy-MM-dd HH:mm:ss"/>"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
											<span class="add-on"><i class="icon-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label "维修状态：</label>
								<div class="controls ">
									<span class="pad ">
										<form:input path="gzfHouseholdInfoId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
									</span>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">维修人：</label>
								<div class="controls ">
									<span class="pad ">
										<form:input path="gzfHouseholdInfoId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
									</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label ">报修项目</label>
								<div class="controls ">
									<span class="pad ">
										<form:input path="repairType" htmlEscape="false" maxlength="64" class="input-xlarge "/>
									</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">故障原因及解决办法:</label>
								<div class="controls" style="overflow:visible">
									<span class="pad">
										<form:textarea path="content" htmlEscape="false" rows="4" maxlength="64" class="input-xxlarge "/>
									</span>
								</div>
							</div> --%>
							<div class="form-actions save-cancle ">
								<shiro:hasPermission name="gzf:gzfMaintenance:edit">
								<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 保存</button></shiro:hasPermission>
								<button id="btnCancel" class="btn blue margin-r-20 "><i class="icon-arrow-left"></i> 返回</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>	
		</div>
	</div>
	
	<%-- <form:form id="inputForm" modelAttribute="gzfMaintenance" action="${ctx}/gzf/gzfMaintenance/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">报修内容：</label>
			<div class="controls">
				<form:input path="gzfRepairManagementId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维修状态：</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfMaintenance:edit"><button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 保存</button></shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form> --%>
</body>
</html>