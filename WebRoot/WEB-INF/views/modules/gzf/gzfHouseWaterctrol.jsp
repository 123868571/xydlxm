<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>房屋信息管理</title>
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
			if($("#waterStatus").val() == '0' || $("#waterStatus").val() == null){
				$("#btnWater").val("停用水表");
			}else{
				$("#btnWater").val("开启水表");
			}

			if($("#elecStatus").val() == '0' || $("#elecStatus").val() == null){
				$("#btnElec").val("停用电表");
			}else{
				$("#btnElec").val("开启电表");
			}

		});
		function changeElec(){
			var queryString = '?type=e';
			var action = $('#inputForm').attr("action");
			alert(action);
			action = action + queryString;
			$('#inputForm').attr("action",action);
			$('#inputForm').submit();
			if($("#btnElec").val() == '开启电表') {
				$("#btnElec").val("停用电表");
			}else{
				$("#btnElec").val("开启电表");
			}
		}

		function changeWater(){
			var queryString = '?type=w';
			var action = $('#inputForm').attr("action");
			alert(action);
			action = action + queryString;
			$('#inputForm').attr("action",action);
			$('#inputForm').submit();
			if($("#btnWater").val() == '开启水表') {
				$("#btnWater").val("停用水表");
			}else{
				$("#btnWater").val("开启水表");
			}
		}
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
		<li class="active"><a href="${ctx}/gzf/gzfHouseWater/form?id=${gzfHouseInfo.id}">房屋水电<shiro:hasPermission name="gzf:gzfHouseWater:edit">${not empty gzfHouseInfo.id?'开关':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfHouseInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gzfHouseInfo" action="${ctx}/gzf/gzfHouseWater/changeStatus" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<%-- <div class="control-group">
			<label class="control-label">房屋23232类型：</label>
			<div class="controls">
				<form:select path="houseType" class="input-xlarge required" style="width:285px;">
					<form:options items="${fns:getDictList('house_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>	
		
		<div class="control-group">
			<label class="control-label">房间信息：</label>
			<div class="controls">
				<%-- <form:input path="useArea" htmlEscape="false" class="input-xlarge required number" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span> --%>
				<span class="pad ">
						${gzfHouseInfo.gzfPalaces.gzfVillage.name}
						${gzfHouseInfo.gzfPalaces.name}
						${gzfHouseInfo.buildNum}幢
						${gzfHouseInfo.unit}单元
						${gzfHouseInfo.room}室
					</span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">住户信息：</label>
			<div class="controls">
				<%-- <form:input path="useArea" htmlEscape="false" class="input-xlarge required number" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span> --%>
				<span class="pad ">
						<%-- ${gzfHouseInfo.gzfPalaces.gzfVillage.name} --%>
						${gzfHouseInfo.gzfHousePerson.gzfHouseholdInfo.name}
						<%-- ${gzfHouseInfo.buildNum}号
						${gzfHouseInfo.unit}单
						${gzfHouseInfo.room} --%>
					</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">水表号：</label>
			<div class="controls">
				<form:input path="water" htmlEscape="false" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">水表IP：</label>
			<div class="controls">
				<form:input path="waterIp" htmlEscape="false" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">水表状态：</label>
			<div class="controls">
				<form:hidden path="waterStatus" htmlEscape="false" class="input-xlarge" readonly="true"/>
				<c:choose>
					<c:when test="${gzfHouseInfo.waterStatus == 0 || empty gzfHouseInfo.waterStatus}">
						<input type="text" class="input-xlarge" value="开启" readonly>
					</c:when>
					<c:otherwise>
						<input type="text" class="input-xlarge" value="关闭" readonly>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电表号：</label>
			<div class="controls">
				<form:input path="elec" htmlEscape="false" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电表IP：</label>
			<div class="controls">
				<form:input path="elecIp" htmlEscape="false" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电表状态：</label>
			<div class="controls">
				<form:hidden path="elecStatus" htmlEscape="false" class="input-xlarge" readonly="true"/>
				<c:choose>
					<c:when test="${gzfHouseInfo.elecStatus == 0 || empty gzfHouseInfo.elecStatus}">
						<input type="text" class="input-xlarge" value="开启" readonly>
					</c:when>
					<c:otherwise>
						<input type="text" class="input-xlarge" value="关闭" readonly>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge" />
			</div>
		</div>
		<div class="form-actions">
			<input id="btnElec" class="btn" type="button" value="停用电表" onclick="changeElec()"/>
			<input id="btnWater" class="btn" type="button" value="停用水表" onclick="changeWater()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>