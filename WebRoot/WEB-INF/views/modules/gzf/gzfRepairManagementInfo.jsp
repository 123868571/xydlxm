<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报修管理详情</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.checkbox1 span {color:rgb(102, 102, 102);padding:8px 27px 0 0;}
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
	<div id="repairInfo" class="row-fluid">
		<div class="span12">				
			<div class="portlet">
				<div class="portlet-title bottom-line" style="border-bottom: #f5f5f5 1px solid;">
					<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i>报修详情</div>
				</div>
				<div class="portlet-body">
					<div class="span6">			
	<form:form id="inputForm" modelAttribute="gzfRepairManagement" action="${ctx}/gzf/gzfRepairManagement/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="repairNum" value="${gzfRepairManagement.repairNum}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">报修单号：</label>
			<div class="controls">
				${gzfRepairManagement.repairNum}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报修项目：</label>
			<div class="controls">
				${gzfRepairManagement.gzfRepairProject.name}
			</div>
		</div>
		<div class="control-group ">
			<label class="control-label ">报修时间</label>
			<div class="controls ">
				<div class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
					<input name="time" type="text" readonly="readonly" maxlength="20" class="m-wrap m-ctrl-medium date-picker Wdate "
						value="<fmt:formatDate value="${gzfRepairManagement.time}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width:245px"/>
						<span class="add-on"><i class="icon-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="control-group ">
			<label class="control-label ">房屋地址</label>
				<div class="controls ">
					${gzfRepairManagement.gzfHouseInfo.gzfPalaces.gzfVillage.name}
					${gzfRepairManagement.gzfHouseInfo.gzfPalaces.name}
					${gzfRepairManagement.gzfHouseInfo.buildNum}号
					${gzfRepairManagement.gzfHouseInfo.unit}单元
					${gzfRepairManagement.gzfHouseInfo.room}室
				</div>
		</div>
		<div class="control-group ">
			<label class="control-label ">户主姓名</label>
			<div class="controls ">
				<span class="pad ">
					${gzfRepairManagement.gzfHouseholdInfo.name}
				</span>
			</div>
		</div>
		<div class="control-group ">
			<label class="control-label ">户住电话</label>
			<div class="controls ">
				<span class="pad ">
					<%-- ${gzfRepairManagement.gzfHouseholdInfo.phone} --%>
					${gzfRepairManagement.gzfHouseholdInfo.phone }
				</span>
			</div>
		</div>
		<div class="control-group ">
			<label class="control-label ">报修人</label>
			<div class="controls ">
				<span class="pad ">
					${gzfRepairManagement.name}
				</span>
			</div>
		</div>
		<div class="control-group ">
				<label class="control-label ">报修人电话</label>
			<div class="controls ">
				<span class="pad ">
					${gzfRepairManagement.phone}
				</span>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">故障现象</label>
			<div class="controls" style="overflow:visible">
				<span class="pad">
					<form:textarea path="content" htmlEscape="false" rows="4" maxlength="64" class="input-xxlarge "/>
				</span>
			</div>
		</div>
		<div class="form-actions save-cancle ">
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