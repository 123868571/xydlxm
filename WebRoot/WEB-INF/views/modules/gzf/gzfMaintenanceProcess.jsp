<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>维修修管理管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.form-horizontal .control-label {width:150px;}
		.form-horizontal .controls {margin-left:170px;}
	</style>
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
	<div id="addRepairitem" class="row-fluid">
		<div class="span12">   
			<div class="portlet">
				<div class="portlet-title bottom-line" style="border-bottom: #f5f5f5 1px solid;">
					<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i>维修反馈</div>
				</div>
				<div class="portlet-body">
					<div class="span8">								
						<form:form id="inputForm" modelAttribute="gzfMaintenance" action="${ctx}/gzf/gzfMaintenance/save" method="post" class="form-horizontal">
							<form:hidden path="id"/>
							<form:hidden path="processStatus"/>
							<sys:message content="${message}"/>	
							<div class="control-group ">
								<label class="control-label ">维修单位</label>
								<div class="controls ">
									<span class="pad ">
										${gzfMaintenance.gzfHouseInfo.gzfPalaces.gzfVillage.name}
										${gzfMaintenance.gzfHouseInfo.gzfPalaces.name}
										${gzfMaintenance.gzfHouseInfo.buildNum}号
										${gzfMaintenance.gzfHouseInfo.unit}单
										${gzfMaintenance.gzfHouseInfo.room}
									</span>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">维修时间</label>
								<div class="controls ">
									<div style="width:140px; " class="input-append date date-picker " data-date="2015-11-15 " data-date-format="yyyy-mm-dd " data-date-viewmode="years ">
										<input style="width:120px; " name="repairTime" type="text" maxlength="20" class="m-wrap m-ctrl-medium date-picker Wdate "
											value="<fmt:formatDate value="${gzfMaintenance.repairTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
											<span class="add-on "><i class="icon-calendar "></i></span>
									</div>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">维修状态</label>
								<div class="controls ">
									<form:select path="maintenanceId" class="input-xlarge required" style="width:285px;">
										<form:options items="${fns:getDictList('repair')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
									</form:select>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">维修人</label>
								<div class="controls ">
									<label class="radio line">
										<input type="radio" name="optionsRadios2" value="option1" />
										<form:input path="repairName" htmlEscape="false" placeholder="请输入维修人姓名 " maxlength="11" class="m-wrap small"/>
										<span class="help-inline "></span>
									</label>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">维修电话</label>
								<div class="controls ">
									<form:input path="repairPhone" htmlEscape="false" maxlength="11" class="m-wrap small"/>
									<span class="help-inline "></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">维修项目</label>
										<div class="controls ">
											${gzfMaintenance.gzfRepairProject.name}
										</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">故障原因及解决办法</label>
								<div class="control-group ">
									<div class="controls ">
										<form:textarea path="repairContent" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
									</div>
								</div>
							</div>
							<div class="form-actions save-cancle ">
								<shiro:hasPermission name="gzf:gzfMaintenance:edit">
								<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 保存</button></shiro:hasPermission>
								<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
							</div>
						</form:form>
					</div>
					<%-- <div class="span6">
						<div class="item">
							<a class="fancybox-button" data-rel="fancybox-button" title="合同" href="${ctxStatic}/media/image/property-bill.jpg">
								<div align="center" class="zoom">
									<img src="media/image/property-bill.jpg" width="360px" alt="Photo" />
									<div class="zoom-icon"></div>
								</div>
							</a>
							<div class="details">点击浏览合同</div>
						</div>
						<div align="center">
							<button onclick="" type="submit" class="btn green"><i class="icon-print"></i> 打印维修单</button>
						</div>
					</div> --%>
				</div>
			</div>
		</div>
	</div>
	
	<%-- <form:form id="inputForm" modelAttribute="gzfMaintenance" action="${ctx}/gzf/gzfMaintenance/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">维修单位：</label>
			<div class="controls">
				${gzfMaintenance.gzfHouseInfo.gzfPalaces.gzfVillage.name}
				${gzfMaintenance.gzfHouseInfo.gzfPalaces.name}
				${gzfMaintenance.gzfHouseInfo.buildNum}号
				${gzfMaintenance.gzfHouseInfo.unit}单
				${gzfMaintenance.gzfHouseInfo.room}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维修时间：</label>
			<div class="controls">
				<input name="repairTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${gzfMaintenance.repairTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维修状态：</label>
			<div class="controls">
				<form:select path="maintenanceId" class="input-xlarge required" style="width:285px;">
					<form:options items="${fns:getDictList('repair')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维修人：</label>
			<div class="controls">
				<form:input path="repairName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维修电话：</label>
			<div class="controls">
				<form:input path="repairPhone" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维修项目：</label>
			<div class="controls">
				<form:select multiple="true" id="repairProcessType" path="repairProcessType" class="input-xlarge" style="width:285px;">
					<form:options items="${repairTypeList}" itemLabel="name" itemValue="name" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">故障原因及解决办法：</label>
			<div class="controls">
				<form:textarea path="repairContent" htmlEscape="false" rows="4" maxlength="64" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfMaintenance:edit"><button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 保存</button></shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form> --%>
</body>
</html>