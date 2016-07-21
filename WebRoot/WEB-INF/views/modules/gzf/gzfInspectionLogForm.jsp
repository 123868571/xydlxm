<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡查日志管理</title>
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
			$("#gzfVillageId").change(function(){
				var gzfVillageId = $("#gzfVillageId").val();
				$.ajax({url : "${ctx}/gzf/gzfVillage/get",data : {id:gzfVillageId},async : true}).done(function(data){
					var obj = eval(data);
					$("#propertyCompany").val(obj.propertyCompany);
				});
				$.ajax({url : "${ctx}/gzf/gzfVillage/get",data : {id:gzfVillageId},async : true}).done(function(data){
					var obj = eval(data);
				   for(var key in obj){
					   $("#propertyCompany").val(obj[key].propertyCompany);
				   }
				});
			}); 
		});
	</script>
</head>
<body>
	<div id="deviceAdd">
		<div class="row-fluid">
			<div class="span12">
				<div class="portlet">
					<div class="portlet-title bottom-line" style="border-bottom: #f5f5f5 1px solid;">
						<div class="caption"><i class="icon-edit"></i>新增巡查</div>
					</div>
					<div class="portlet-body">
					<form:form id="inputForm" modelAttribute="gzfInspectionLog" action="${ctx}/gzf/gzfInspectionLog/save" method="post" class="form-horizontal">
						<form:hidden path="id"/>
						<form:hidden path="inspectionId"/>
						<sys:message content="${message}"/>
							<div class="control-group">
								<label class="control-label">巡查编号</label>
								<div class="controls">
									<span class="pad">${gzfInspectionLog.inspectionId}</span>
								</div>
							</div>	
							<div class="control-group">
								<label class="control-label">巡查日期</label>
								<div class="controls">
									<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
										<input name="time" type="text" maxlength="20" class="m-wrap m-ctrl-medium date-picker Wdate"
										value="<fmt:formatDate value="${gzfInspectionLog.time}" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width: 100px;"/>
										<span class="add-on"><i class="icon-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">项目名称</label>
								<div class="controls">
									<form:select path="gzfVillageId" class="input-large">
										<form:option value="" label=""/>
										<form:options items="${gzfVillageList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
									</form:select>
									<span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">物业</label>
								<div class="controls">
								<span class="help-inline">
									<input class="input-xlarge" id="propertyCompany" name="propertyCompany" value="${gzfInspectionLog.gzfVillage.propertyCompany}" disabled="disabled">
								</span>
								</div>
							</div>	
							<div class="control-group">
								<label class="control-label">巡查单位</label>
								<div class="controls">
									<form:input path="unit" htmlEscape="false" maxlength="64" class="input-xlarge "/>
									<span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">巡查人</label>
								<div class="controls">
									<form:input path="inspectionName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
									<span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">巡查人电话</label>
								<div class="controls">
									<form:input path="inspectionPhone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
									<span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">项目维护负责人</label>
								<div class="controls">
									<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
									<span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">维护负责人电话</label>
								<div class="controls">
									<form:input path="phone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
									<span class="help-inline"></span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">巡查详情</label>
								<div class="control-group">
									<div class="controls">
										<table class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>巡查类目</th>
													<th>状态</th>
													<th>备注</th>
												</tr>
											</thead>
											<tbody>
												<tr class="odd gradeX">
													<td colspan="3">
														所属类别：<span>机房</span>
													</td>
												</tr>
												<tr class="odd gradeX">
													<td>服务器运行状态</td>
													<td>
														<form:radiobutton path="computerStatus" class="group-checkable" value="0"/>良好
        												<form:radiobutton path="computerStatus" class="group-checkable" value="1"/>不良
													</td>
													<td><form:input path="computerStatusRemarks" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
												</tr>
												<tr class="odd gradeX">
													<td>服务器C盘空间</td>
													<td>
														<form:radiobutton path="computerC" class="group-checkable" value="0"/>良好
        												<form:radiobutton path="computerC" class="group-checkable" value="1"/>不良
													</td>											
													<td><form:input path="computerCRemarks" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
												</tr>
												<tr class="odd gradeX">
													<td>服务器日志备份拷贝</td>
													<td>
														<form:radiobutton path="computerLogbak" class="group-checkable" value="0"/>良好
        												<form:radiobutton path="computerLogbak" class="group-checkable" value="1"/>不良
													</td>											
													<td><form:input path="computerLogbakRemarks" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
												</tr>
												<tr class="odd gradeX">
													<td>问题反馈</td>
													<td colspan="2">
													<form:textarea path="computerFeedback" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
													</td>
												</tr>
												<tr class="odd gradeX">
													<td colspan="3">
														所属类别：<span>住户管理情况</span>
													</td>
												</tr>
												<tr class="odd gradeX">
													<td>单元门口机</td>
													<td>
														<form:radiobutton path="roomMachine" class="group-checkable" value="0"/>良好
        												<form:radiobutton path="roomMachine" class="group-checkable" value="1"/>不良
													</td>
													<td><form:input path="roomMachineRemarks" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
												</tr>
												<tr class="odd gradeX">
													<td>各单元梯控使用情况</td>
													<td>
														<form:radiobutton path="roomControl" class="group-checkable" value="0"/>良好
        												<form:radiobutton path="roomControl" class="group-checkable" value="1"/>不良
													</td>											
													<td><form:input path="roomControlRemarks" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
												</tr>
												<tr class="odd gradeX">
													<td>室内机</td>
													<td>
														<form:radiobutton path="roomIndoor" class="group-checkable" value="0"/>良好
        												<form:radiobutton path="roomIndoor" class="group-checkable" value="1"/>不良
													</td>
													<td><form:input path="roomIndoorRemarks" htmlEscape="false" maxlength="255" class="input-xlarge "/></td>
												</tr>
												<tr class="odd gradeX">
													<td>问题反馈</td>
													<td colspan="2"><form:textarea path="roomFeedback" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="form-actions" style="background:#fff;border-top:none;">
								<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 保存</button>
								<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
							</div>
					</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>