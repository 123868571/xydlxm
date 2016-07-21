<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>小区管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			if($("#gzfVillage").val() != null && $("#gzfVillage").val() != ""){
				var gzfVillageId = $("#gzfVillage").val();
			$.ajax({url : "${ctx}/gzf/gzfHousePerson/findName",data : {gzfVillageId:gzfVillageId},async : true}).done(function(data){
				var obj = eval(data);
				jQuery("#gzfPalacesId").empty();
			   for(var key in obj){
			   		$("#gzfPalacesId").append('<option value="' + obj[key].id + '">' + obj[key].name + '</option>');
			   }
			});
			}
			
			if(($("#gzfVillage").val() != null && $("#gzfVillage").val() != "") && ($("#gzfPalacesId").val() != null && $("#gzfPalacesId").val() != "")){
				var gzfPalacesId = $("#gzfPalacesId").val();
				$.ajax({url : "${ctx}/gzf/gzfHousePerson/findPalace",data : {gzfPalacesId:gzfPalacesId},async : true}).done(function(data){
					var obj = eval(data);
					jQuery("#houseInfo").empty();
				   for(var key in obj){
				   		$("#houseInfo").append('<option value="' + obj[key].id + '">' + obj[key].buildNum + '号楼' + obj[key].unit + '单元' + obj[key].room + '房间' + '</option>');
				   }
				});
			}
			
			
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
			
			$("#gzfVillage").change(function(){
				var gzfVillageId = $("#gzfVillage").val();
				$.ajax({url : "${ctx}/gzf/gzfHousePerson/findName",data : {gzfVillageId:gzfVillageId},async : true}).done(function(data){
					var obj = eval(data);
					jQuery("#gzfPalacesId").empty();
				   for(var key in obj){
				   		$("#gzfPalacesId").append('<option value="' + obj[key].id + '">' + obj[key].name + '</option>');
				   }
				});
																			
				}); 
			
			$("#gzfPalacesId").change(function(){
				var gzfPalacesId = $("#gzfPalacesId").val();
				$.ajax({url : "${ctx}/gzf/gzfHousePerson/findPalace",data : {gzfPalacesId:gzfPalacesId},async : true}).done(function(data){
					var obj = eval(data);
					jQuery("#houseInfo").empty();
				   for(var key in obj){
				   		$("#houseInfo").append('<option value="' + obj[key].id + '">' + obj[key].buildNum + '号楼' + obj[key].unit + '单元' + obj[key].room + '房间' + '</option>');
				   }
				});
																			
				}); 
			
			$("#houseInfo").change(function(){
				var houseInfo = $("#houseInfo").val();
				$.ajax({url : "${ctx}/gzf/gzfHousePerson/findHouse",data : {id:houseInfo},async : true}).done(function(data){
					var obj = eval(data);
				   for(var key in obj){
					   $("#houseType").val(obj[key].houseType);
					   $("#houseBuild").val($("#gzfVillage").val()+$("#gzfPalacesId").val()+$("#houseInfo").val());
					   $("#innerArea").val(obj[key].innerArea);
					   $("#useArea").val(obj[key].useArea);
					   $("#houseProperty").val(obj[key].gzfHousePropertyId);
					   $("#houseRemark").val(obj[key].remarks);
					   $("#gzfHouseInfoId").val(obj[key].id);
				   }
				});
																			
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
	<!-- BEGIN PAGE HEADER-->

	<div class="row-fluid">
	<div class="span12">
	<!-- BEGIN PAGE TITLE & BREADCRUMB-->
	<h6></h6>
	<ul class="breadcrumb pos-rel">
	<li>
	<i class="icon-list" style="font-size:14px;"></i>
	<a id="home" href="#"></a>
	<i class="icon-angle-right"></i>
	</li>
	<li>
	<a id="guide_infotitle" href="#">信息中心</a>
	<i class="icon-angle-right"></i>
	</li>
	<li>
	<a id="guide_district_manage" href="">小区管理</a>
	<i class="icon-angle-right"></i>
	</li>
	<li>
	<a id="" href="">小区添加</a>
	</li>
	<li class="pull-right posa-back">
	<button class="btn blue" id="" onclick="history.go(-1)"><i class="icon-arrow-left" style="color:#fff;"></i> 返回</button>
	</li>
	</ul>
	<!-- END PAGE TITLE & BREADCRUMB-->

	</div>

	</div>

	<!-- END PAGE HEADER-->

	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzf/gzfHousePerson/form">小区<shiro:hasPermission name="gzf:gzfHousePerson:edit">${not empty gzfHousePerson.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfHousePerson:edit">查看</shiro:lacksPermission></a></li>
	</ul> --%><br/>
	<form:form id="inputForm"  modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHouseWardsController/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<input id="gzfHouseInfoId" name="gzfHouseInfoId" type="hidden" value=""/>
		<form:input path="gzfHouseholdInfoId" type="hidden"/>	
		<%-- 
		<div class="control-group">
			<label class="control-label">建筑年代：</label>
			<div class="controls">
				<input name="" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate required"
					value="<fmt:formatDate value="${gzfVillage.buildYear}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
			 --%>
		<div class="control-group">
			<label class="control-label">房屋信息：</label>
			<div class="controls">
			<form:select id="gzfVillage" path="" class="input-medium">
					<%-- <form:option value="" label="---请选择小区---"/> --%>
					 <form:options items="${gzfVillage}" itemLabel="name" itemValue="id" htmlEscape="false"/> 
				</form:select>
				<form:select id="gzfPalacesId" path="" class="input-medium required">
					<form:option value="" label="---请选择苑---" selected="true"/>
					<%-- <form:options items="" itemLabel="" itemValue="" htmlEscape="false"/> --%>
				</form:select>
				
				<div>
					<form:select id="houseInfo" path="" class="input-medium required">
					<form:option value="" label="---请选择楼层---"/>
					<%-- <form:options items="" itemLabel="" itemValue="" htmlEscape="false"/> --%>
				</form:select>
				</div>
			</div>
			
		</div>
		<div class="control-group">
			<label class="control-label">合同日期：</label>
			<div class="controls">
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				-<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div> 
		</div>
		<div class="control-group">
			<label class="control-label">房租生效日期：</label>
			<div class="controls">
				-<input name="effectiveDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${effectiveDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房租类型：</label>
			<div class="controls">
				<form:input id="houseType" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房租地址：</label>
			<div class="controls">
				<form:input id="houseBuild" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">套内面积：</label>
			<div class="controls">
				<form:input id="innerArea" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用面积：</label>
			<div class="controls">
				<form:input id="useArea" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房屋属性：</label>
			<div class="controls">
				<form:input id="houseProperty" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房屋备注：</label>
			<div class="controls">
				<form:input id="houseRemark" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfHousePerson:edit">
			<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 绑定</button></shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

	<script>
	$(document).ready(function(){
	$("#new_btn1").click(function(){
	$("#old_yuan").hide();
	$("#new_input").show();
	});
	});
	</script>
	<script>
	$(document).ready(function(){
	$("#new_btn2").click(function(){
	$("#old_lou").hide();
	$("#new_input2").show();
	});
	});
	</script>
</body>
</html>