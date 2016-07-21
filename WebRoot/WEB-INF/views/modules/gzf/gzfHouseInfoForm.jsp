<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>房屋信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#gzfPalacesId").change(function(){
				var gzfPalacesId = $("#gzfPalacesId").val();
				$.ajax({url : "${ctx}/gzf/gzfHouseInfo/findName",data : {gzfPalacesId:gzfPalacesId},async : true}).done(function(data){
					var obj = eval(data);
					$("#buildNum").empty();
					if(obj.maxBuildNum != null){
						$("#buildNum").append('<option value="0">---请选择楼栋---</option>');
						for(var i = 1;i<=obj.maxBuildNum;i++){
							$("#buildNum").append('<option value="' + i + '">' + i + '</option>');
						}
					}
					$("#unit").empty();
					if(obj.maxUnitNum != null){
						$("#unit").append('<option value="0">---请选择单元---</option>');
						for(var j =1;j<=obj.maxUnitNum;j++){
							$("#unit").append('<option value="' + j + '">' + j + '</option>');
						}
					}
					$("#room").empty();
					if(obj.maxRoomNum != null){
						$("#room").append('<option value="0">---请选择房间号---</option>');
						for(var l = 1;l<=obj.maxFloorNum;l++){
							var a = l;
							if(l<10){
								a = "0" + l;
							}
							for(var j = 1;j<=obj.maxRoomNum;j++){
								var b = j;
								if(j<10){
									 b = "0" + j;
								}
								$("#room").append('<option value="' + a+b + '">' + a+b + '</option>');
							}
						}
					}
					/* jQuery("#gzfPalacesId").empty();
				   for(var key in obj){
				   		$("#gzfPalacesId").append('<option value="' + obj[key].id + '">' + obj[key].name + '</option>');
				   } */
				});
																			
				}); 
			$("#buildNum").on('change',function(){
				$('#unit').trigger('change');
			});
			$("#unit").on('change',function(){
				var gzfPalacesId = $("#gzfPalacesId").val();
				var buildNum = $("#buildNum").val();
				var unit = $("#unit").val();
				
				
				$.ajax({url : "${ctx}/gzf/gzfHouseInfo/findName",data : {gzfPalacesId:gzfPalacesId},async : true}).done(function(data){
					var obj = eval(data);
					$("#room").empty();
					if(obj.maxRoomNum != null){
						$("#room").append('<option value="0">---请选择房间号---</option>');
						for(var l = 1;l<=obj.maxFloorNum;l++){
							var a = l;
							if(l<10){
								a = "0" + l;
							}
							for(var j = 1;j<=obj.maxRoomNum;j++){
								var b = j;
								if(j<10){
									 b = "0" + j;
								}
								$("#room").append('<option value="' + a+b + '">' + a+b + '</option>');
							}
						}
					}
					/* jQuery("#gzfPalacesId").empty();
				   for(var key in obj){
				   		$("#gzfPalacesId").append('<option value="' + obj[key].id + '">' + obj[key].name + '</option>');
				   } */
				});
				
				
				$.ajax({url : "${ctx}/gzf/gzfHouseInfo/removeRoom",data : {gzfPalacesId:gzfPalacesId,buildNum:buildNum,unit:unit},async : true}).done(function(data){
					var obj = eval(data);
				   for(var key in obj){
					   $("#room option[value="+obj[key].room+"]").remove();
					}
				});
			});
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
		<li class="active"><a href="${ctx}/gzf/gzfHouseInfo/form?id=${gzfHouseInfo.id}">房屋信息<shiro:hasPermission name="gzf:gzfHouseInfo:edit">${not empty gzfHouseInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfHouseInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gzfHouseInfo" action="${ctx}/gzf/gzfHouseInfo/save" method="post" class="form-horizontal">
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
			<label class="control-label">房屋地址：</label>
			<div class="controls">
				<form:select path="gzfPalacesId" class="input-medium" style="margin-right:20px;">
					<form:option value="" label="---请选择苑---"/>
					<form:options items="${gzfPalacesList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<form:select id="buildNum" path="buildNum" class="input-medium required">
					<form:option value="" label="---请选择楼栋---"/>
					<form:options items="${buildNumList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span style="margin-right:20px;">号楼</span>
				<form:select id="unit" path="unit" class="input-medium required">
					<form:option value="" label="---请选择单元---"/>
					<form:options items="${unitList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span style="margin-right:20px;">单元</span>
				<form:select id="room" path="room" class="input-medium required">
					<form:option value="" label="---请选择房间号---"/>
					<form:options items="${roomList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span>房间</span>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">建筑面积：</label>
			<div class="controls">
				<form:input path="innerArea" htmlEscape="false" class="input-xlarge required number" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用面积：</label>
			<div class="controls">
				<form:input path="useArea" htmlEscape="false" class="input-xlarge required number" />
				<span class="help-inline"><font color="red">*</font> </span>
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
		<div class="control-group">
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
		</div>
		<div class="control-group">
			<label class="control-label">房屋特性：</label>
			<div class="controls">
				<form:select path="personal" class="input-xlarge required"
					style="width:285px;">
					<form:options items="${fns:getDictList('apply_form_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
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