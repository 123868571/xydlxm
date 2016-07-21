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
		<li class="active"><a href="${ctx}/gzf/gzfHouseWater/form?id=${gzfHouseInfo.id}">房屋水电表<shiro:hasPermission name="gzf:gzfHouseWater:edit">${not empty gzfHouseInfo.water?'修改':'配置'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfHouseInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gzfHouseInfo" action="${ctx}/gzf/gzfHouseWater/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">房屋类型：</label>
			<div class="controls">
				<form:select path="houseType" class="input-xlarge required" style="width:285px;">
					<form:options items="${fns:getDictList('house_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">建筑面积：</label>
			<div class="controls">
				<form:input path="innerArea" htmlEscape="false" class="input-xlarge" readonly="true"/>
				<!-- <span class="help-inline"><font color="red">*</font> </span> -->
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用面积：</label>
			<div class="controls">
				<form:input path="useArea" htmlEscape="false" class="input-xlarge" readonly="true"/>
				<!-- <span class="help-inline"><font color="red">*</font> </span> -->
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">房屋状态：</label>
			<div class="controls">
				<form:select path="houseStat" class="input-xlarge required" style="width:285px;">
					<form:options items="${fns:getDictList('house_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">房屋缩略图:</label>
			<div class="controls">
				<form:hidden id="nameImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge" />
				<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房屋属性：</label>
			<div class="controls">
				<form:select path="gzfHousePropertyId" class="input-xlarge" style="width:285px;">
					<form:options items="${gzfHousePropertyList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		
		<div class="control-group">
			<label class="control-label">水表号：</label>
			<div class="controls">
				<form:input path="water" htmlEscape="false" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">水表及对应的IP：</label>
			<div class="controls">
				<form:input path="waterIp" htmlEscape="false" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电表号：</label>
			<div class="controls">
				<form:input path="elec" htmlEscape="false" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电表及对应的IP：</label>
			<div class="controls">
				<form:input path="elecIp" htmlEscape="false" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfHouseInfo:edit">
			<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 保存</button></shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>